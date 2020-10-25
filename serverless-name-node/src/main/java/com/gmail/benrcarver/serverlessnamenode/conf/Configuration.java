package com.gmail.benrcarver.serverlessnamenode.conf;

import com.gmail.benrcarver.serverlessnamenode.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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
     * Sentinel value to store negative cache results in {@link #CACHE_CLASSES}.
     */
    private static final Class<?> NEGATIVE_CACHE_SENTINEL =
            NegativeCacheSentinel.class;

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
            org.apache.hadoop.io.Text.writeString(out, (String) item.getKey());
            org.apache.hadoop.io.Text.writeString(out, (String) item.getValue());
            WritableUtils.writeCompressedStringArray(out, updatingResource != null ?
                    updatingResource.get(item.getKey()) : null);
        }
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        clear();
        int size = WritableUtils.readVInt(in);
        for(int i=0; i < size; ++i) {
            String key = org.apache.hadoop.io.Text.readString(in);
            String value = org.apache.hadoop.io.Text.readString(in);
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
}
