package com.gmail.benrcarver.serverlessnamenode.conf;

import com.gmail.benrcarver.serverlessnamenode.fs.CommonConfigurationKeys;
import com.gmail.benrcarver.serverlessnamenode.io.Text;
import com.gmail.benrcarver.serverlessnamenode.io.Writable;
import com.gmail.benrcarver.serverlessnamenode.io.WritableUtils;
import com.gmail.benrcarver.serverlessnamenode.util.StringInterner;
import com.gmail.benrcarver.serverlessnamenode.util.StringUtils;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.collections.map.UnmodifiableMap;
import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.ctc.wstx.api.ReaderConfig;
import com.ctc.wstx.io.StreamBootstrapper;
import com.ctc.wstx.io.SystemId;
import com.ctc.wstx.stax.WstxInputFactory;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import static com.sun.scenario.Settings.set;

public class Configuration implements Iterable<Map.Entry<String,String>>,
        Writable {
    private static final Logger LOG =
            LoggerFactory.getLogger(Configuration.class);

    private static final Logger LOG_DEPRECATION =
            LoggerFactory.getLogger(
                    "org.apache.hadoop.conf.Configuration.deprecation");
    private static final Set<String> TAGS = ConcurrentHashMap.newKeySet();

    private boolean quietmode = true;

    private static final String DEFAULT_STRING_CHECK =
            "testingforemptydefaultvalue";

    private static boolean restrictSystemPropsDefault = false;
    private boolean restrictSystemProps = restrictSystemPropsDefault;
    private boolean allowNullValueProperties = false;

    private static final int MAX_SUBST = 20;

    private static final int SUB_START_IDX = 0;
    private static final int SUB_END_IDX = SUB_START_IDX + 1;

    /**
     * Sentinel value to store negative cache results in {@link #CACHE_CLASSES}.
     */
    private static final Class<?> NEGATIVE_CACHE_SENTINEL =
            NegativeCacheSentinel.class;

    /**
     * A unique class which is used as a sentinel value in the caching
     * for getClassByName. {@link Configuration#getClassByNameOrNull(String)}
     */
    private static abstract class NegativeCacheSentinel {}

    private static class Resource {
        private final Object resource;
        private final String name;
        private final boolean restrictParser;

        public Resource(Object resource) {
            this(resource, resource.toString());
        }

        public Resource(Object resource, boolean useRestrictedParser) {
            this(resource, resource.toString(), useRestrictedParser);
        }

        public Resource(Object resource, String name) {
            this(resource, name, getRestrictParserDefault(resource));
        }

        public Resource(Object resource, String name, boolean restrictParser) {
            this.resource = resource;
            this.name = name;
            this.restrictParser = restrictParser;
        }

        public String getName(){
            return name;
        }

        public Object getResource() {
            return resource;
        }

        public boolean isParserRestricted() {
            return restrictParser;
        }

        @Override
        public String toString() {
            return name;
        }

        private static boolean getRestrictParserDefault(Object resource) {
            /*if (resource instanceof String || !UserGroupInformation.isInitialized()) {
                return false;
            }
            UserGroupInformation user;
            try {
                user = UserGroupInformation.getCurrentUser();
            } catch (IOException e) {
                throw new RuntimeException("Unable to determine current user", e);
            }
            return user.getRealUser() != null;*/

            return false;
        }
    }

    /**
     * List of configuration resources.
     */
    private ArrayList<Resource> resources = new ArrayList<Resource>();

    /**
     * The value reported as the setting resource when a key is set
     * by code rather than a file resource by dumpConfiguration.
     */
    static final String UNKNOWN_RESOURCE = "Unknown";


    /**
     * List of configuration parameters marked <b>final</b>.
     */
    private Set<String> finalParameters = Collections.newSetFromMap(
            new ConcurrentHashMap<String, Boolean>());

    private boolean loadDefaults = true;

    /**
     * Configuration objects
     */
    private static final WeakHashMap<Configuration,Object> REGISTRY =
            new WeakHashMap<Configuration,Object>();

    /**
     * Map to hold properties by there tag groupings.
     */
    private final Map<String, Properties> propertyTagsMap =
            new ConcurrentHashMap<>();

    /**
     * List of default Resources. Resources are loaded in the order of the list
     * entries
     */
    private static final CopyOnWriteArrayList<String> defaultResources =
            new CopyOnWriteArrayList<String>();

    private static final Map<ClassLoader, Map<String, WeakReference<Class<?>>>>
            CACHE_CLASSES = new WeakHashMap<ClassLoader, Map<String, WeakReference<Class<?>>>>();

    /**
     * Stores the mapping of key to the resource which modifies or loads
     * the key most recently. Created lazily to avoid wasting memory.
     */
    private volatile Map<String, String[]> updatingResource;

    private Properties properties;
    private Properties overlay;
    private ClassLoader classLoader;

    @Override
    public void write(DataOutput out) throws IOException {
        Properties props = getProps();
        WritableUtils.writeVInt(out, props.size());
        for(Map.Entry<Object, Object> item: props.entrySet()) {
            Text.writeString(out, (String) item.getKey());
            Text.writeString(out, (String) item.getValue());
            WritableUtils.writeCompressedStringArray(out, updatingResource != null ?
                    updatingResource.get(item.getKey()) : null);
        }
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        clear();
        int size = WritableUtils.readVInt(in);
        for(int i=0; i < size; ++i) {
            String key = Text.readString(in);
            String value = Text.readString(in);
            set(key, value);
            String sources[] = WritableUtils.readCompressedStringArray(in);
            if (sources != null) {
                putIntoUpdatingResource(key, sources);
            }
        }
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return null;
    }

    /**
     * Clears all keys from the configuration.
     */
    public void clear() {
        getProps().clear();
        getOverlay().clear();
    }

    /**
     * A pending addition to the global set of deprecated keys.
     */
    public static class DeprecationDelta {
        private final String key;
        private final String[] newKeys;
        private final String customMessage;

        DeprecationDelta(String key, String[] newKeys, String customMessage) {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(newKeys);
            Preconditions.checkArgument(newKeys.length > 0);
            this.key = key;
            this.newKeys = newKeys;
            this.customMessage = customMessage;
        }

        public DeprecationDelta(String key, String newKey, String customMessage) {
            this(key, new String[] { newKey }, customMessage);
        }

        public DeprecationDelta(String key, String newKey) {
            this(key, new String[] { newKey }, null);
        }

        public String getKey() {
            return key;
        }

        public String[] getNewKeys() {
            return newKeys;
        }

        public String getCustomMessage() {
            return customMessage;
        }
    }

    /**
     * Class to keep the information about the keys which replace the deprecated
     * ones.
     *
     * This class stores the new keys which replace the deprecated keys and also
     * gives a provision to have a custom message for each of the deprecated key
     * that is being replaced. It also provides method to get the appropriate
     * warning message which can be logged whenever the deprecated key is used.
     */
    private static class DeprecatedKeyInfo {
        private final String[] newKeys;
        private final String customMessage;
        private final AtomicBoolean accessed = new AtomicBoolean(false);

        DeprecatedKeyInfo(String[] newKeys, String customMessage) {
            this.newKeys = newKeys;
            this.customMessage = customMessage;
        }

        private final String getWarningMessage(String key) {
            return getWarningMessage(key, null);
        }

        /**
         * Method to provide the warning message. It gives the custom message if
         * non-null, and default message otherwise.
         * @param key the associated deprecated key.
         * @param source the property source.
         * @return message that is to be logged when a deprecated key is used.
         */
        private String getWarningMessage(String key, String source) {
            String warningMessage;
            if(customMessage == null) {
                StringBuilder message = new StringBuilder(key);
                if (source != null) {
                    message.append(" in " + source);
                }
                message.append(" is deprecated. Instead, use ");
                for (int i = 0; i < newKeys.length; i++) {
                    message.append(newKeys[i]);
                    if(i != newKeys.length-1) {
                        message.append(", ");
                    }
                }
                warningMessage = message.toString();
            }
            else {
                warningMessage = customMessage;
            }
            return warningMessage;
        }

        boolean getAndSetAccessed() {
            return accessed.getAndSet(true);
        }

        public void clearAccessed() {
            accessed.set(false);
        }
    }

    /**
     * The set of all keys which are deprecated.
     *
     * DeprecationContext objects are immutable.
     */
    private static class DeprecationContext {
        /**
         * Stores the deprecated keys, the new keys which replace the deprecated keys
         * and custom message(if any provided).
         */
        private final Map<String, DeprecatedKeyInfo> deprecatedKeyMap;

        /**
         * Stores a mapping from superseding keys to the keys which they deprecate.
         */
        private final Map<String, String> reverseDeprecatedKeyMap;

        /**
         * Create a new DeprecationContext by copying a previous DeprecationContext
         * and adding some deltas.
         *
         * @param other   The previous deprecation context to copy, or null to start
         *                from nothing.
         * @param deltas  The deltas to apply.
         */
        @SuppressWarnings("unchecked")
        DeprecationContext(DeprecationContext other, DeprecationDelta[] deltas) {
            HashMap<String, DeprecatedKeyInfo> newDeprecatedKeyMap =
                    new HashMap<String, DeprecatedKeyInfo>();
            HashMap<String, String> newReverseDeprecatedKeyMap =
                    new HashMap<String, String>();
            if (other != null) {
                for (Map.Entry<String, DeprecatedKeyInfo> entry :
                        other.deprecatedKeyMap.entrySet()) {
                    newDeprecatedKeyMap.put(entry.getKey(), entry.getValue());
                }
                for (Map.Entry<String, String> entry :
                        other.reverseDeprecatedKeyMap.entrySet()) {
                    newReverseDeprecatedKeyMap.put(entry.getKey(), entry.getValue());
                }
            }
            for (DeprecationDelta delta : deltas) {
                if (!newDeprecatedKeyMap.containsKey(delta.getKey())) {
                    DeprecatedKeyInfo newKeyInfo =
                            new DeprecatedKeyInfo(delta.getNewKeys(), delta.getCustomMessage());
                    newDeprecatedKeyMap.put(delta.key, newKeyInfo);
                    for (String newKey : delta.getNewKeys()) {
                        newReverseDeprecatedKeyMap.put(newKey, delta.key);
                    }
                }
            }
            this.deprecatedKeyMap =
                    UnmodifiableMap.decorate(newDeprecatedKeyMap);
            this.reverseDeprecatedKeyMap =
                    UnmodifiableMap.decorate(newReverseDeprecatedKeyMap);
        }

        Map<String, DeprecatedKeyInfo> getDeprecatedKeyMap() {
            return deprecatedKeyMap;
        }

        Map<String, String> getReverseDeprecatedKeyMap() {
            return reverseDeprecatedKeyMap;
        }
    }

    /**
     * Adds a set of deprecated keys to the global deprecations.
     *
     * This method is lockless.  It works by means of creating a new
     * DeprecationContext based on the old one, and then atomically swapping in
     * the new context.  If someone else updated the context in between us reading
     * the old context and swapping in the new one, we try again until we win the
     * race.
     *
     * @param deltas   The deprecations to add.
     */
    public static void addDeprecations(DeprecationDelta[] deltas) {
        DeprecationContext prev, next;
        do {
            prev = deprecationContext.get();
            next = new DeprecationContext(prev, deltas);
        } while (!deprecationContext.compareAndSet(prev, next));
    }

    /**
     * Adds the deprecated key to the global deprecation map.
     * It does not override any existing entries in the deprecation map.
     * This is to be used only by the developers in order to add deprecation of
     * keys, and attempts to call this method after loading resources once,
     * would lead to <tt>UnsupportedOperationException</tt>
     *
     * If a key is deprecated in favor of multiple keys, they are all treated as
     * aliases of each other, and setting any one of them resets all the others
     * to the new value.
     *
     * If you have multiple deprecation entries to add, it is more efficient to
     * use #addDeprecations(DeprecationDelta[] deltas) instead.
     *
     * @param key
     * @param newKeys
     * @param customMessage
     * @deprecated use {@link #addDeprecation(String key, String newKey,
            String customMessage)} instead
     */
    @Deprecated
    public static void addDeprecation(String key, String[] newKeys,
                                      String customMessage) {
        addDeprecations(new DeprecationDelta[] {
                new DeprecationDelta(key, newKeys, customMessage)
        });
    }

    /**
     * Adds the deprecated key to the global deprecation map.
     * It does not override any existing entries in the deprecation map.
     * This is to be used only by the developers in order to add deprecation of
     * keys, and attempts to call this method after loading resources once,
     * would lead to <tt>UnsupportedOperationException</tt>
     *
     * If you have multiple deprecation entries to add, it is more efficient to
     * use #addDeprecations(DeprecationDelta[] deltas) instead.
     *
     * @param key
     * @param newKey
     * @param customMessage
     */
    public static void addDeprecation(String key, String newKey,
                                      String customMessage) {
        addDeprecation(key, new String[] {newKey}, customMessage);
    }

    /**
     * Adds the deprecated key to the global deprecation map when no custom
     * message is provided.
     * It does not override any existing entries in the deprecation map.
     * This is to be used only by the developers in order to add deprecation of
     * keys, and attempts to call this method after loading resources once,
     * would lead to <tt>UnsupportedOperationException</tt>
     *
     * If a key is deprecated in favor of multiple keys, they are all treated as
     * aliases of each other, and setting any one of them resets all the others
     * to the new value.
     *
     * If you have multiple deprecation entries to add, it is more efficient to
     * use #addDeprecations(DeprecationDelta[] deltas) instead.
     *
     * @param key Key that is to be deprecated
     * @param newKeys list of keys that take up the values of deprecated key
     * @deprecated use {@link #addDeprecation(String key, String newKey)} instead
     */
    @Deprecated
    public static void addDeprecation(String key, String[] newKeys) {
        addDeprecation(key, newKeys, null);
    }

    /**
     * Adds the deprecated key to the global deprecation map when no custom
     * message is provided.
     * It does not override any existing entries in the deprecation map.
     * This is to be used only by the developers in order to add deprecation of
     * keys, and attempts to call this method after loading resources once,
     * would lead to <tt>UnsupportedOperationException</tt>
     *
     * If you have multiple deprecation entries to add, it is more efficient to
     * use #addDeprecations(DeprecationDelta[] deltas) instead.
     *
     * @param key Key that is to be deprecated
     * @param newKey key that takes up the value of deprecated key
     */
    public static void addDeprecation(String key, String newKey) {
        addDeprecation(key, new String[] {newKey}, null);
    }

    /**
     * checks whether the given <code>key</code> is deprecated.
     *
     * @param key the parameter which is to be checked for deprecation
     * @return <code>true</code> if the key is deprecated and
     *         <code>false</code> otherwise.
     */
    public static boolean isDeprecated(String key) {
        return deprecationContext.get().getDeprecatedKeyMap().containsKey(key);
    }

    private static String getDeprecatedKey(String key) {
        return deprecationContext.get().getReverseDeprecatedKeyMap().get(key);
    }

    private static DeprecatedKeyInfo getDeprecatedKeyInfo(String key) {
        return deprecationContext.get().getDeprecatedKeyMap().get(key);
    }

    void logDeprecation(String message) {
        LOG_DEPRECATION.info(message);
    }

    /**
     * Returns alternative names (non-deprecated keys or previously-set deprecated keys)
     * for a given non-deprecated key.
     * If the given key is deprecated, return null.
     *
     * @param name property name.
     * @return alternative names.
     */
    private String[] getAlternativeNames(String name) {
        String altNames[] = null;
        DeprecatedKeyInfo keyInfo = null;
        DeprecationContext cur = deprecationContext.get();
        String depKey = cur.getReverseDeprecatedKeyMap().get(name);
        if(depKey != null) {
            keyInfo = cur.getDeprecatedKeyMap().get(depKey);
            if(keyInfo.newKeys.length > 0) {
                if(getProps().containsKey(depKey)) {
                    //if deprecated key is previously set explicitly
                    List<String> list = new ArrayList<String>();
                    list.addAll(Arrays.asList(keyInfo.newKeys));
                    list.add(depKey);
                    altNames = list.toArray(new String[list.size()]);
                }
                else {
                    altNames = keyInfo.newKeys;
                }
            }
        }
        return altNames;
    }

    /**
     * Load a class by name.
     *
     * @param name the class name.
     * @return the class object.
     * @throws ClassNotFoundException if the class is not found.
     */
    public Class<?> getClassByName(String name) throws ClassNotFoundException {
        Class<?> ret = getClassByNameOrNull(name);
        if (ret == null) {
            throw new ClassNotFoundException("Class " + name + " not found");
        }
        return ret;
    }

    /**
     * Get the value of the <code>name</code> property
     * as an array of <code>Class</code>.
     * The value of the property specifies a list of comma separated class names.
     * If no such property is specified, then <code>defaultValue</code> is
     * returned.
     *
     * @param name the property name.
     * @param defaultValue default value.
     * @return property value as a <code>Class[]</code>,
     *         or <code>defaultValue</code>.
     */
    public Class<?>[] getClasses(String name, Class<?> ... defaultValue) {
        String valueString = getRaw(name);
        if (null == valueString) {
            return defaultValue;
        }
        String[] classnames = getTrimmedStrings(name);
        try {
            Class<?>[] classes = new Class<?>[classnames.length];
            for(int i = 0; i < classnames.length; i++) {
                classes[i] = getClassByName(classnames[i]);
            }
            return classes;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the comma delimited values of the <code>name</code> property as
     * an array of <code>String</code>s, trimmed of the leading and trailing whitespace.
     * If no such property is specified then an empty array is returned.
     *
     * @param name property name.
     * @return property value as an array of trimmed <code>String</code>s,
     *         or empty array.
     */
    public String[] getTrimmedStrings(String name) {
        String valueString = get(name);
        return StringUtils.getTrimmedStrings(valueString);
    }

    /**
     * Get the value of the <code>name</code> property as a <code>Class</code>.
     * If no such property is specified, then <code>defaultValue</code> is
     * returned.
     *
     * @param name the class name.
     * @param defaultValue default value.
     * @return property value as a <code>Class</code>,
     *         or <code>defaultValue</code>.
     */
    public Class<?> getClass(String name, Class<?> defaultValue) {
        String valueString = getTrimmed(name);
        if (valueString == null)
            return defaultValue;
        try {
            return getClassByName(valueString);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the value of the <code>name</code> property as a <code>Class</code>
     * implementing the interface specified by <code>xface</code>.
     *
     * If no such property is specified, then <code>defaultValue</code> is
     * returned.
     *
     * An exception is thrown if the returned class does not implement the named
     * interface.
     *
     * @param name the class name.
     * @param defaultValue default value.
     * @param xface the interface implemented by the named class.
     * @return property value as a <code>Class</code>,
     *         or <code>defaultValue</code>.
     */
    public <U> Class<? extends U> getClass(String name,
                                           Class<? extends U> defaultValue,
                                           Class<U> xface) {
        try {
            Class<?> theClass = getClass(name, defaultValue);
            if (theClass != null && !xface.isAssignableFrom(theClass))
                throw new RuntimeException(theClass+" not "+xface.getName());
            else if (theClass != null)
                return theClass.asSubclass(xface);
            else
                return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the value of the <code>name</code> property as a <code>List</code>
     * of objects implementing the interface specified by <code>xface</code>.
     *
     * An exception is thrown if any of the classes does not exist, or if it does
     * not implement the named interface.
     *
     * @param name the property name.
     * @param xface the interface implemented by the classes named by
     *        <code>name</code>.
     * @return a <code>List</code> of objects implementing <code>xface</code>.
     */
    @SuppressWarnings("unchecked")
    public <U> List<U> getInstances(String name, Class<U> xface) {
        List<U> ret = new ArrayList<U>();
        Class<?>[] classes = getClasses(name);
        for (Class<?> cl: classes) {
            if (!xface.isAssignableFrom(cl)) {
                throw new RuntimeException(cl + " does not implement " + xface);
            }
            ret.add((U)ReflectionUtils.newInstance(cl, this));
        }
        return ret;
    }

    /**
     * Set the value of the <code>name</code> property to the name of a
     * <code>theClass</code> implementing the given interface <code>xface</code>.
     *
     * An exception is thrown if <code>theClass</code> does not implement the
     * interface <code>xface</code>.
     *
     * @param name property name.
     * @param theClass property value.
     * @param xface the interface implemented by the named class.
     */
    public void setClass(String name, Class<?> theClass, Class<?> xface) {
        if (!xface.isAssignableFrom(theClass))
            throw new RuntimeException(theClass+" not "+xface.getName());
        set(name, theClass.getName());
    }

    /**
     * Set the <code>value</code> of the <code>name</code> property. If
     * <code>name</code> is deprecated or there is a deprecated name associated to it,
     * it sets the value to both names. Name will be trimmed before put into
     * configuration.
     *
     * @param name property name.
     * @param value property value.
     */
    public void set(String name, String value) {
        set(name, value, null);
    }

    /**
     * Set the <code>value</code> of the <code>name</code> property. If
     * <code>name</code> is deprecated, it also sets the <code>value</code> to
     * the keys that replace the deprecated key. Name will be trimmed before put
     * into configuration.
     *
     * @param name property name.
     * @param value property value.
     * @param source the place that this configuration value came from
     * (For debugging).
     * @throws IllegalArgumentException when the value or name is null.
     */
    public void set(String name, String value, String source) {
        Preconditions.checkArgument(
                name != null,
                "Property name must not be null");
        Preconditions.checkArgument(
                value != null,
                "The value of property %s must not be null", name);
        name = name.trim();
        DeprecationContext deprecations = deprecationContext.get();
        if (deprecations.getDeprecatedKeyMap().isEmpty()) {
            getProps();
        }
        getOverlay().setProperty(name, value);
        getProps().setProperty(name, value);
        String newSource = (source == null ? "programmatically" : source);

        if (!isDeprecated(name)) {
            putIntoUpdatingResource(name, new String[] {newSource});
            String[] altNames = getAlternativeNames(name);
            if(altNames != null) {
                for(String n: altNames) {
                    if(!n.equals(name)) {
                        getOverlay().setProperty(n, value);
                        getProps().setProperty(n, value);
                        putIntoUpdatingResource(n, new String[] {newSource});
                    }
                }
            }
        }
        else {
            String[] names = handleDeprecation(deprecationContext.get(), name);
            String altSource = "because " + name + " is deprecated";
            for(String n : names) {
                getOverlay().setProperty(n, value);
                getProps().setProperty(n, value);
                putIntoUpdatingResource(n, new String[] {altSource});
            }
        }
    }

    void logDeprecationOnce(String name, String source) {
        DeprecatedKeyInfo keyInfo = getDeprecatedKeyInfo(name);
        if (keyInfo != null && !keyInfo.getAndSetAccessed()) {
            LOG_DEPRECATION.info(keyInfo.getWarningMessage(name, source));
        }
    }

    /**
     * Checks for the presence of the property <code>name</code> in the
     * deprecation map. Returns the first of the list of new keys if present
     * in the deprecation map or the <code>name</code> itself. If the property
     * is not presently set but the property map contains an entry for the
     * deprecated key, the value of the deprecated key is set as the value for
     * the provided property name.
     *
     * @param deprecations deprecation context
     * @param name the property name
     * @return the first property in the list of properties mapping
     *         the <code>name</code> or the <code>name</code> itself.
     */
    private String[] handleDeprecation(DeprecationContext deprecations,
                                       String name) {
        if (null != name) {
            name = name.trim();
        }
        // Initialize the return value with requested name
        String[] names = new String[]{name};
        // Deprecated keys are logged once and an updated names are returned
        DeprecatedKeyInfo keyInfo = deprecations.getDeprecatedKeyMap().get(name);
        if (keyInfo != null) {
            if (!keyInfo.getAndSetAccessed()) {
                logDeprecation(keyInfo.getWarningMessage(name));
            }
            // Override return value for deprecated keys
            names = keyInfo.newKeys;
        }
        // If there are no overlay values we can return early
        Properties overlayProperties = getOverlay();
        if (overlayProperties.isEmpty()) {
            return names;
        }
        // Update properties and overlays with reverse lookup values
        for (String n : names) {
            String deprecatedKey = deprecations.getReverseDeprecatedKeyMap().get(n);
            if (deprecatedKey != null && !overlayProperties.containsKey(n)) {
                String deprecatedValue = overlayProperties.getProperty(deprecatedKey);
                if (deprecatedValue != null) {
                    getProps().setProperty(n, deprecatedValue);
                    overlayProperties.setProperty(n, deprecatedValue);
                }
            }
        }
        return names;
    }

    private void handleDeprecation() {
        LOG.debug("Handling deprecation for all properties in config...");
        DeprecationContext deprecations = deprecationContext.get();
        Set<Object> keys = new HashSet<Object>();
        keys.addAll(getProps().keySet());
        for (Object item: keys) {
            LOG.debug("Handling deprecation for " + (String)item);
            handleDeprecation(deprecations, (String)item);
        }
    }

    private static DeprecationDelta[] defaultDeprecations =
            new DeprecationDelta[] {
                    new DeprecationDelta("topology.script.file.name",
                            CommonConfigurationKeys.NET_TOPOLOGY_SCRIPT_FILE_NAME_KEY),
                    new DeprecationDelta("topology.script.number.args",
                            CommonConfigurationKeys.NET_TOPOLOGY_SCRIPT_NUMBER_ARGS_KEY),
                    new DeprecationDelta("hadoop.configured.node.mapping",
                            CommonConfigurationKeys.NET_TOPOLOGY_CONFIGURED_NODE_MAPPING_KEY),
                    new DeprecationDelta("topology.node.switch.mapping.impl",
                            CommonConfigurationKeys.NET_TOPOLOGY_NODE_SWITCH_MAPPING_IMPL_KEY),
                    new DeprecationDelta("dfs.df.interval",
                            CommonConfigurationKeys.FS_DF_INTERVAL_KEY),
                    new DeprecationDelta("fs.default.name",
                            CommonConfigurationKeys.FS_DEFAULT_NAME_KEY),
                    new DeprecationDelta("dfs.umaskmode",
                            CommonConfigurationKeys.FS_PERMISSIONS_UMASK_KEY),
                    new DeprecationDelta("dfs.nfs.exports.allowed.hosts",
                            CommonConfigurationKeys.NFS_EXPORTS_ALLOWED_HOSTS_KEY)
            };

    /**
     * The global DeprecationContext.
     */
    private static AtomicReference<DeprecationContext> deprecationContext =
            new AtomicReference<DeprecationContext>(
                    new DeprecationContext(null, defaultDeprecations));

    /**
     * This is a manual implementation of the following regex
     * "\\$\\{[^\\}\\$\u0020]+\\}". It can be 15x more efficient than
     * a regex matcher as demonstrated by HADOOP-11506. This is noticeable with
     * Hadoop apps building on the assumption Configuration#get is an O(1)
     * hash table lookup, especially when the eval is a long string.
     *
     * @param eval a string that may contain variables requiring expansion.
     * @return a 2-element int array res such that
     * eval.substring(res[0], res[1]) is "var" for the left-most occurrence of
     * ${var} in eval. If no variable is found -1, -1 is returned.
     */
    private static int[] findSubVariable(String eval) {
        int[] result = {-1, -1};

        int matchStart;
        int leftBrace;

        // scanning for a brace first because it's less frequent than $
        // that can occur in nested class names
        //
        match_loop:
        for (matchStart = 1, leftBrace = eval.indexOf('{', matchStart);
            // minimum left brace position (follows '$')
             leftBrace > 0
                     // right brace of a smallest valid expression "${c}"
                     && leftBrace + "{c".length() < eval.length();
             leftBrace = eval.indexOf('{', matchStart)) {
            int matchedLen = 0;
            if (eval.charAt(leftBrace - 1) == '$') {
                int subStart = leftBrace + 1; // after '{'
                for (int i = subStart; i < eval.length(); i++) {
                    switch (eval.charAt(i)) {
                        case '}':
                            if (matchedLen > 0) { // match
                                result[SUB_START_IDX] = subStart;
                                result[SUB_END_IDX] = subStart + matchedLen;
                                break match_loop;
                            }
                            // fall through to skip 1 char
                        case ' ':
                        case '$':
                            matchStart = i + 1;
                            continue match_loop;
                        default:
                            matchedLen++;
                    }
                }
                // scanned from "${"  to the end of eval, and no reset via ' ', '$':
                //    no match!
                break match_loop;
            } else {
                // not a start of a variable
                //
                matchStart = leftBrace + 1;
            }
        }
        return result;
    }

    /**
     * Attempts to repeatedly expand the value {@code expr} by replacing the
     * left-most substring of the form "${var}" in the following precedence order
     * <ol>
     *   <li>by the value of the environment variable "var" if defined</li>
     *   <li>by the value of the Java system property "var" if defined</li>
     *   <li>by the value of the configuration key "var" if defined</li>
     * </ol>
     *
     * If var is unbounded the current state of expansion "prefix${var}suffix" is
     * returned.
     * <p>
     * This function also detects self-referential substitutions, i.e.
     * <pre>
     *   {@code
     *   foo.bar = ${foo.bar}
     *   }
     * </pre>
     * If a cycle is detected then the original expr is returned. Loops
     * involving multiple substitutions are not detected.
     *
     * @param expr the literal value of a config key
     * @return null if expr is null, otherwise the value resulting from expanding
     * expr using the algorithm above.
     * @throws IllegalArgumentException when more than
     * {@link Configuration#MAX_SUBST} replacements are required
     */
    private String substituteVars(String expr) {
        if (expr == null) {
            return null;
        }
        String eval = expr;
        for(int s = 0; s < MAX_SUBST; s++) {
            final int[] varBounds = findSubVariable(eval);
            if (varBounds[SUB_START_IDX] == -1) {
                return eval;
            }
            final String var = eval.substring(varBounds[SUB_START_IDX],
                    varBounds[SUB_END_IDX]);
            String val = null;
            if (!restrictSystemProps) {
                try {
                    if (var.startsWith("env.") && 4 < var.length()) {
                        String v = var.substring(4);
                        int i = 0;
                        for (; i < v.length(); i++) {
                            char c = v.charAt(i);
                            if (c == ':' && i < v.length() - 1 && v.charAt(i + 1) == '-') {
                                val = getenv(v.substring(0, i));
                                if (val == null || val.length() == 0) {
                                    val = v.substring(i + 2);
                                }
                                break;
                            } else if (c == '-') {
                                val = getenv(v.substring(0, i));
                                if (val == null) {
                                    val = v.substring(i + 1);
                                }
                                break;
                            }
                        }
                        if (i == v.length()) {
                            val = getenv(v);
                        }
                    } else {
                        val = getProperty(var);
                    }
                } catch (SecurityException se) {
                    LOG.warn("Unexpected SecurityException in Configuration", se);
                }
            }
            if (val == null) {
                val = getRaw(var);
            }
            if (val == null) {
                return eval; // return literal ${var}: var is unbound
            }

            final int dollar = varBounds[SUB_START_IDX] - "${".length();
            final int afterRightBrace = varBounds[SUB_END_IDX] + "}".length();
            final String refVar = eval.substring(dollar, afterRightBrace);

            // detect self-referential values
            if (val.contains(refVar)) {
                return expr; // return original expression if there is a loop
            }

            // substitute
            eval = eval.substring(0, dollar)
                    + val
                    + eval.substring(afterRightBrace);
        }
        throw new IllegalStateException("Variable substitution depth too large: "
                + MAX_SUBST + " " + expr);
    }

    String getenv(String name) {
        return System.getenv(name);
    }

    String getProperty(String key) {
        return System.getProperty(key);
    }

    /**
     * Get the value of the <code>name</code> property as a trimmed <code>String</code>,
     * <code>null</code> if no such property exists.
     * If the key is deprecated, it returns the value of
     * the first key which replaces the deprecated key and is not null
     *
     * Values are processed for <a href="#VariableExpansion">variable expansion</a>
     * before being returned.
     *
     * @param name the property name.
     * @return the value of the <code>name</code> or its replacing property,
     *         or null if no such property exists.
     */
    public String getTrimmed(String name) {
        String value = get(name);

        if (null == value) {
            return null;
        } else {
            return value.trim();
        }
    }

    /**
     * Get the value of the <code>name</code> property as a trimmed <code>String</code>,
     * <code>defaultValue</code> if no such property exists.
     * See @{Configuration#getTrimmed} for more details.
     *
     * @param name          the property name.
     * @param defaultValue  the property default value.
     * @return              the value of the <code>name</code> or defaultValue
     *                      if it is not set.
     */
    public String getTrimmed(String name, String defaultValue) {
        String ret = getTrimmed(name);
        return ret == null ? defaultValue : ret;
    }

    /**
     * Get the value of the <code>name</code> property, without doing
     * <a href="#VariableExpansion">variable expansion</a>.If the key is
     * deprecated, it returns the value of the first key which replaces
     * the deprecated key and is not null.
     *
     * @param name the property name.
     * @return the value of the <code>name</code> property or
     *         its replacing property and null if no such property exists.
     */
    public String getRaw(String name) {
        String[] names = handleDeprecation(deprecationContext.get(), name);
        String result = null;
        for(String n : names) {
            result = getProps().getProperty(n);
        }
        return result;
    }

    /**
     * Get the value of the <code>name</code> property, <code>null</code> if
     * no such property exists. If the key is deprecated, it returns the value of
     * the first key which replaces the deprecated key and is not null.
     *
     * Values are processed for <a href="#VariableExpansion">variable expansion</a>
     * before being returned.
     *
     * @param name the property name, will be trimmed before get value.
     * @return the value of the <code>name</code> or its replacing property,
     *         or null if no such property exists.
     */
    public String get(String name) {
        String[] names = handleDeprecation(deprecationContext.get(), name);
        String result = null;
        for(String n : names) {
            result = substituteVars(getProps().getProperty(n));
        }
        return result;
    }

    /**
     * Get the comma delimited values of the <code>name</code> property as
     * a collection of <code>String</code>s, trimmed of the leading and trailing whitespace.
     * If no such property is specified then empty <code>Collection</code> is returned.
     *
     * @param name property name.
     * @return property value as a collection of <code>String</code>s, or empty <code>Collection</code>
     */
    public Collection<String> getTrimmedStringCollection(String name) {
        String valueString = get(name);
        if (null == valueString) {
            Collection<String> empty = new ArrayList<String>();
            return empty;
        }
        return StringUtils.getTrimmedStringCollection(valueString);
    }

    /**
     * Add tags defined in HADOOP_TAGS_SYSTEM, HADOOP_TAGS_CUSTOM.
     * @param prop
     */
    public void addTags(Properties prop) {
        // Get all system tags
        try {
            if (prop.containsKey(CommonConfigurationKeys.HADOOP_TAGS_SYSTEM)) {
                String systemTags = prop.getProperty(CommonConfigurationKeys
                        .HADOOP_TAGS_SYSTEM);
                TAGS.addAll(Arrays.asList(systemTags.split(",")));
            }
            // Get all custom tags
            if (prop.containsKey(CommonConfigurationKeys.HADOOP_TAGS_CUSTOM)) {
                String customTags = prop.getProperty(CommonConfigurationKeys
                        .HADOOP_TAGS_CUSTOM);
                TAGS.addAll(Arrays.asList(customTags.split(",")));
            }

            if (prop.containsKey(CommonConfigurationKeys.HADOOP_SYSTEM_TAGS)) {
                String systemTags = prop.getProperty(CommonConfigurationKeys
                        .HADOOP_SYSTEM_TAGS);
                TAGS.addAll(Arrays.asList(systemTags.split(",")));
            }
            // Get all custom tags
            if (prop.containsKey(CommonConfigurationKeys.HADOOP_CUSTOM_TAGS)) {
                String customTags = prop.getProperty(CommonConfigurationKeys
                        .HADOOP_CUSTOM_TAGS);
                TAGS.addAll(Arrays.asList(customTags.split(",")));
            }

        } catch (Exception ex) {
            LOG.trace("Error adding tags in configuration", ex);
        }

    }

    private void loadResources(Properties properties,
                               ArrayList<Resource> resources,
                               boolean quiet) {
        if(loadDefaults) {
            for (String resource : defaultResources) {
                loadResource(properties, new Resource(resource, false), quiet);
            }
        }

        for (int i = 0; i < resources.size(); i++) {
            Resource ret = loadResource(properties, resources.get(i), quiet);
            if (ret != null) {
                resources.set(i, ret);
            }
        }
        this.addTags(properties);
    }

    private static class ParsedItem {
        String name;
        String key;
        String value;
        boolean isFinal;
        String[] sources;

        ParsedItem(String name, String key, String value,
                   boolean isFinal, String[] sources) {
            this.name = name;
            this.key = key;
            this.value = value;
            this.isFinal = isFinal;
            this.sources = sources;
        }
    }

    private void overlay(Properties to, Properties from) {
        for (Map.Entry<Object, Object> entry: from.entrySet()) {
            to.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Print a warning if a property with a given name already exists with a
     * different value
     */
    private void checkForOverride(Properties properties, String name, String attr, String value) {
        String propertyValue = properties.getProperty(attr);
        if (propertyValue != null && !propertyValue.equals(value)) {
            LOG.warn(name + ":an attempt to override final parameter: " + attr
                    + ";  Ignoring.");
        }
    }

    private void loadProperty(Properties properties, String name, String attr,
                              String value, boolean finalParameter, String[] source) {
        if (value != null || allowNullValueProperties) {
            if (value == null) {
                value = DEFAULT_STRING_CHECK;
            }
            if (!finalParameters.contains(attr)) {
                properties.setProperty(attr, value);
                if (source != null) {
                    putIntoUpdatingResource(attr, source);
                }
            } else {
                // This is a final parameter so check for overrides.
                checkForOverride(this.properties, name, attr, value);
                if (this.properties != properties) {
                    checkForOverride(properties, name, attr, value);
                }
            }
        }
        if (finalParameter && attr != null) {
            finalParameters.add(attr);
        }
    }

    private XMLStreamReader parse(InputStream is, String systemIdStr,
                                  boolean restricted) throws IOException, XMLStreamException {
        if (!quietmode) {
            LOG.debug("parsing input stream " + is);
        }
        if (is == null) {
            return null;
        }
        SystemId systemId = SystemId.construct(systemIdStr);
        ReaderConfig readerConfig = XML_INPUT_FACTORY.createPrivateConfig();
        if (restricted) {
            readerConfig.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        }
        return XML_INPUT_FACTORY.createSR(readerConfig, systemId,
                StreamBootstrapper.getInstance(null, systemId, is), false, true);
    }

    private XMLStreamReader2 getStreamReader(Resource wrapper, boolean quiet)
            throws XMLStreamException, IOException {
        Object resource = wrapper.getResource();
        boolean isRestricted = wrapper.isParserRestricted();
        XMLStreamReader2 reader = null;
        if (resource instanceof URL) {                  // an URL resource
            reader  = (XMLStreamReader2)parse((URL)resource, isRestricted);
        } else if (resource instanceof String) {        // a CLASSPATH resource
            URL url = getResource((String)resource);
            reader = (XMLStreamReader2)parse(url, isRestricted);
        } else if (resource instanceof Path) {          // a file resource
            // Can't use FileSystem API or we get an infinite loop
            // since FileSystem uses Configuration API.  Use java.io.File instead.
            File file = new File(((Path)resource).toUri().getPath())
                    .getAbsoluteFile();
            if (file.exists()) {
                if (!quiet) {
                    LOG.debug("parsing File " + file);
                }
                reader = (XMLStreamReader2)parse(new BufferedInputStream(
                                new FileInputStream(file)), ((Path)resource).toString(),
                        isRestricted);
            }
        } else if (resource instanceof InputStream) {
            reader = (XMLStreamReader2)parse((InputStream)resource, null,
                    isRestricted);
        }
        return reader;
    }

    private Resource loadResource(Properties properties,
                                  Resource wrapper, boolean quiet) {
        String name = UNKNOWN_RESOURCE;
        try {
            Object resource = wrapper.getResource();
            name = wrapper.getName();
            boolean returnCachedProperties = false;

            if (resource instanceof InputStream) {
                returnCachedProperties = true;
            } else if (resource instanceof Properties) {
                overlay(properties, (Properties)resource);
            }

            XMLStreamReader2 reader = getStreamReader(wrapper, quiet);
            if (reader == null) {
                if (quiet) {
                    return null;
                }
                throw new RuntimeException(resource + " not found");
            }
            Properties toAddTo = properties;
            if(returnCachedProperties) {
                toAddTo = new Properties();
            }

            List<ParsedItem> items = new Parser(reader, wrapper, quiet).parse();
            for (ParsedItem item : items) {
                loadProperty(toAddTo, item.name, item.key, item.value,
                        item.isFinal, item.sources);
            }
            reader.close();

            if (returnCachedProperties) {
                overlay(properties, toAddTo);
                return new Resource(toAddTo, name, wrapper.isParserRestricted());
            }
            return null;
        } catch (IOException e) {
            LOG.error("error parsing conf " + name, e);
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            LOG.error("error parsing conf " + name, e);
            throw new RuntimeException(e);
        }
    }

    protected synchronized Properties getProps() {
        if (properties == null) {
            properties = new Properties();
            Map<String, String[]> backup = updatingResource != null ?
                    new ConcurrentHashMap<String, String[]>(updatingResource) : null;
            loadResources(properties, resources, quietmode);

            if (overlay != null) {
                properties.putAll(overlay);
                if (backup != null) {
                    for (Map.Entry<Object, Object> item : overlay.entrySet()) {
                        String key = (String) item.getKey();
                        String[] source = backup.get(key);
                        if (source != null) {
                            updatingResource.put(key, source);
                        }
                    }
                }
            }
        }
        return properties;
    }

    private synchronized Properties getOverlay() {
        if (overlay==null){
            overlay=new Properties();
        }
        return overlay;
    }

    /**
     * Load a class by name, returning null rather than throwing an exception
     * if it couldn't be loaded. This is to avoid the overhead of creating
     * an exception.
     *
     * @param name the class name
     * @return the class object, or null if it could not be found.
     */
    public Class<?> getClassByNameOrNull(String name) {
        Map<String, WeakReference<Class<?>>> map;

        synchronized (CACHE_CLASSES) {
            map = CACHE_CLASSES.get(classLoader);
            if (map == null) {
                map = Collections.synchronizedMap(
                        new WeakHashMap<String, WeakReference<Class<?>>>());
                CACHE_CLASSES.put(classLoader, map);
            }
        }

        Class<?> clazz = null;
        WeakReference<Class<?>> ref = map.get(name);
        if (ref != null) {
            clazz = ref.get();
        }

        if (clazz == null) {
            try {
                clazz = Class.forName(name, true, classLoader);
            } catch (ClassNotFoundException e) {
                // Leave a marker that the class isn't found
                map.put(name, new WeakReference<Class<?>>(NEGATIVE_CACHE_SENTINEL));
                return null;
            }
            // two putters can race here, but they'll put the same class
            map.put(name, new WeakReference<Class<?>>(clazz));
            return clazz;
        } else if (clazz == NEGATIVE_CACHE_SENTINEL) {
            return null; // not found
        } else {
            // cache hit
            return clazz;
        }
    }

    private void putIntoUpdatingResource(String key, String[] value) {
        Map<String, String[]> localUR = updatingResource;
        if (localUR == null) {
            synchronized (this) {
                localUR = updatingResource;
                if (localUR == null) {
                    updatingResource = localUR = new ConcurrentHashMap<>(8);
                }
            }
        }
        localUR.put(key, value);
    }

    /**
     * Parser to consume SAX stream of XML elements from a Configuration.
     */
    private class Parser {
        private final XMLStreamReader2 reader;
        private final Resource wrapper;
        private final String name;
        private final String[] nameSingletonArray;
        private final boolean isRestricted;
        private final boolean quiet;

        DeprecationContext deprecations = deprecationContext.get();

        private StringBuilder token = new StringBuilder();
        private String confName = null;
        private String confValue = null;
        private String confInclude = null;
        private String confTag = null;
        private boolean confFinal = false;
        private boolean fallbackAllowed = false;
        private boolean fallbackEntered = false;
        private boolean parseToken = false;
        private List<String> confSource = new ArrayList<>();
        private List<ParsedItem> results = new ArrayList<>();

        Parser(XMLStreamReader2 reader,
               Resource wrapper,
               boolean quiet) {
            this.reader = reader;
            this.wrapper = wrapper;
            this.name = wrapper.getName();
            this.nameSingletonArray = new String[]{ name };
            this.isRestricted = wrapper.isParserRestricted();
            this.quiet = quiet;

        }

        List<ParsedItem> parse() throws IOException, XMLStreamException {
            while (reader.hasNext()) {
                parseNext();
            }
            return results;
        }

        private void handleStartElement() throws XMLStreamException, IOException {
            switch (reader.getLocalName()) {
                case "property":
                    handleStartProperty();
                    break;

                case "name":
                case "value":
                case "final":
                case "source":
                case "tag":
                    parseToken = true;
                    token.setLength(0);
                    break;
                case "include":
                    handleInclude();
                    break;
                case "fallback":
                    fallbackEntered = true;
                    break;
                case "configuration":
                    break;
                default:
                    break;
            }
        }

        private void handleStartProperty() {
            confName = null;
            confValue = null;
            confFinal = false;
            confTag = null;
            confSource.clear();

            // First test for short format configuration
            int attrCount = reader.getAttributeCount();
            for (int i = 0; i < attrCount; i++) {
                String propertyAttr = reader.getAttributeLocalName(i);
                if ("name".equals(propertyAttr)) {
                    confName = StringInterner.weakIntern(
                            reader.getAttributeValue(i));
                } else if ("value".equals(propertyAttr)) {
                    confValue = StringInterner.weakIntern(
                            reader.getAttributeValue(i));
                } else if ("final".equals(propertyAttr)) {
                    confFinal = "true".equals(reader.getAttributeValue(i));
                } else if ("source".equals(propertyAttr)) {
                    confSource.add(StringInterner.weakIntern(
                            reader.getAttributeValue(i)));
                } else if ("tag".equals(propertyAttr)) {
                    confTag = StringInterner
                            .weakIntern(reader.getAttributeValue(i));
                }
            }
        }

        private void handleInclude() throws XMLStreamException, IOException {
            // Determine href for xi:include
            confInclude = null;
            int attrCount = reader.getAttributeCount();
            List<ParsedItem> items;
            for (int i = 0; i < attrCount; i++) {
                String attrName = reader.getAttributeLocalName(i);
                if ("href".equals(attrName)) {
                    confInclude = reader.getAttributeValue(i);
                }
            }
            if (confInclude == null) {
                return;
            }
            if (isRestricted) {
                throw new RuntimeException("Error parsing resource " + wrapper
                        + ": XInclude is not supported for restricted resources");
            }
            // Determine if the included resource is a classpath resource
            // otherwise fallback to a file resource
            // xi:include are treated as inline and retain current source
            URL include = getResource(confInclude);
            if (include != null) {
                Resource classpathResource = new Resource(include, name,
                        wrapper.isParserRestricted());
                // This is only called recursively while the lock is already held
                // by this thread, but synchronizing avoids a findbugs warning.
                synchronized (Configuration.this) {
                    XMLStreamReader2 includeReader =
                            getStreamReader(classpathResource, quiet);
                    if (includeReader == null) {
                        throw new RuntimeException(classpathResource + " not found");
                    }
                    items = new Parser(includeReader, classpathResource, quiet).parse();
                }
            } else {
                URL url;
                try {
                    url = new URL(confInclude);
                    url.openConnection().connect();
                } catch (IOException ioe) {
                    File href = new File(confInclude);
                    if (!href.isAbsolute()) {
                        // Included resources are relative to the current resource
                        File baseFile = new File(name).getParentFile();
                        href = new File(baseFile, href.getPath());
                    }
                    if (!href.exists()) {
                        // Resource errors are non-fatal iff there is 1 xi:fallback
                        fallbackAllowed = true;
                        return;
                    }
                    url = href.toURI().toURL();
                }
                Resource uriResource = new Resource(url, name,
                        wrapper.isParserRestricted());
                // This is only called recursively while the lock is already held
                // by this thread, but synchronizing avoids a findbugs warning.
                synchronized (Configuration.this) {
                    XMLStreamReader2 includeReader =
                            getStreamReader(uriResource, quiet);
                    if (includeReader == null) {
                        throw new RuntimeException(uriResource + " not found");
                    }
                    items = new Parser(includeReader, uriResource, quiet).parse();
                }
            }
            results.addAll(items);
        }

        void handleEndElement() throws IOException {
            String tokenStr = token.toString();
            switch (reader.getLocalName()) {
                case "name":
                    if (token.length() > 0) {
                        confName = StringInterner.weakIntern(tokenStr.trim());
                    }
                    break;
                case "value":
                    if (token.length() > 0) {
                        confValue = StringInterner.weakIntern(tokenStr);
                    }
                    break;
                case "final":
                    confFinal = "true".equals(tokenStr);
                    break;
                case "source":
                    confSource.add(StringInterner.weakIntern(tokenStr));
                    break;
                case "tag":
                    if (token.length() > 0) {
                        confTag = StringInterner.weakIntern(tokenStr);
                    }
                    break;
                case "include":
                    if (fallbackAllowed && !fallbackEntered) {
                        throw new IOException("Fetch fail on include for '"
                                + confInclude + "' with no fallback while loading '"
                                + name + "'");
                    }
                    fallbackAllowed = false;
                    fallbackEntered = false;
                    break;
                case "property":
                    handleEndProperty();
                    break;
                default:
                    break;
            }
        }

        void handleEndProperty() {
            if (confName == null || (!fallbackAllowed && fallbackEntered)) {
                return;
            }
            String[] confSourceArray;
            if (confSource.isEmpty()) {
                confSourceArray = nameSingletonArray;
            } else {
                confSource.add(name);
                confSourceArray = confSource.toArray(new String[confSource.size()]);
            }

            // Read tags and put them in propertyTagsMap
            if (confTag != null) {
                readTagFromConfig(confTag, confName, confValue, confSourceArray);
            }

            DeprecatedKeyInfo keyInfo =
                    deprecations.getDeprecatedKeyMap().get(confName);

            if (keyInfo != null) {
                keyInfo.clearAccessed();
                for (String key : keyInfo.newKeys) {
                    // update new keys with deprecated key's value
                    results.add(new ParsedItem(
                            name, key, confValue, confFinal, confSourceArray));
                }
            } else {
                results.add(new ParsedItem(name, confName, confValue, confFinal,
                        confSourceArray));
            }
        }

        void parseNext() throws IOException, XMLStreamException {
            switch (reader.next()) {
                case XMLStreamConstants.START_ELEMENT:
                    handleStartElement();
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (parseToken) {
                        char[] text = reader.getTextCharacters();
                        token.append(text, reader.getTextStart(), reader.getTextLength());
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    handleEndElement();
                    break;
                default:
                    break;
            }
        }
    }
}
