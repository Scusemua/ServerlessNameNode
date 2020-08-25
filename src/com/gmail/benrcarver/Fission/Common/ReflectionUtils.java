package com.gmail.benrcarver.Fission.Common;

import com.gmail.benrcarver.Fission.ServerlessNameNode.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectionUtils {
    private static final Class<?>[] EMPTY_ARRAY = new Class[]{};

    /**
     * Cache of constructors for each class. Pins the classes so they
     * can't be garbage collected until ReflectionUtils can be collected.
     */
    private static final Map<Class<?>, Constructor<?>> CONSTRUCTOR_CACHE =
            new ConcurrentHashMap<Class<?>, Constructor<?>>();

    /**
     * Check and set 'configuration' if necessary.
     *
     * @param theObject object for which to set configuration
     * @param conf Configuration
     */
    public static void setConf(Object theObject, Configuration conf) {
        if (conf != null) {
            if (theObject instanceof Configurable) {
                ((Configurable) theObject).setConf(conf);
            }
            setJobConf(theObject, conf);
        }
    }

    /**
     * This code is to support backward compatibility and break the compile
     * time dependency of core on mapred.
     * This should be made deprecated along with the mapred package HADOOP-1230.
     * Should be removed when mapred package is removed.
     */
    private static void setJobConf(Object theObject, Configuration conf) {
        //If JobConf and JobConfigurable are in classpath, AND
        //theObject is of type JobConfigurable AND
        //conf is of type JobConf then
        //invoke configure on theObject
        try {
            Class<?> jobConfClass =
                    conf.getClassByNameOrNull("org.apache.hadoop.mapred.JobConf");
            if (jobConfClass == null) {
                return;
            }

            Class<?> jobConfigurableClass =
                    conf.getClassByNameOrNull("org.apache.hadoop.mapred.JobConfigurable");
            if (jobConfigurableClass == null) {
                return;
            }
            if (jobConfClass.isAssignableFrom(conf.getClass()) &&
                    jobConfigurableClass.isAssignableFrom(theObject.getClass())) {
                Method configureMethod =
                        jobConfigurableClass.getMethod("configure", jobConfClass);
                configureMethod.invoke(theObject, conf);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in configuring object", e);
        }
    }

    /** Create an object for the given class and initialize it from conf
     *
     * @param theClass class of which an object is created
     * @param conf Configuration
     * @return a new object
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> theClass, Configuration conf) {
        T result;
        try {
            Constructor<T> meth = (Constructor<T>) CONSTRUCTOR_CACHE.get(theClass);
            if (meth == null) {
                meth = theClass.getDeclaredConstructor(EMPTY_ARRAY);
                meth.setAccessible(true);
                CONSTRUCTOR_CACHE.put(theClass, meth);
            }
            result = meth.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setConf(result, conf);
        return result;
    }
}
