package com.gmail.benrcarver.serverlessnamenode.io.serializer;

import com.gmail.benrcarver.serverlessnamenode.conf.Configuration;
import com.gmail.benrcarver.serverlessnamenode.fs.CommonConfigurationKeys;
import com.gmail.benrcarver.serverlessnamenode.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A factory for {@link Serialization}s.
 * </p>
 */
public class SerializationFactory {
    static final Logger LOG =
            LoggerFactory.getLogger(SerializationFactory.class.getName());

    private List<Serialization<?>> serializations = new ArrayList<Serialization<?>>();

    /**
     * <p>
     * Serializations are found by reading the <code>io.serializations</code>
     * property from <code>conf</code>, which is a comma-delimited list of
     * classnames.
     * </p>
     */
    public SerializationFactory(Configuration conf) {
        super(conf);
        for (String serializerName : conf.getTrimmedStrings(
                CommonConfigurationKeys.IO_SERIALIZATIONS_KEY,
                new String[]{WritableSerialization.class.getName(),
                        AvroSpecificSerialization.class.getName(),
                        AvroReflectSerialization.class.getName()})) {
            add(conf, serializerName);
        }
    }

    @SuppressWarnings("unchecked")
    private void add(Configuration conf, String serializationName) {
        try {
            Class<? extends Serialization> serializionClass =
                    (Class<? extends Serialization>) conf.getClassByName(serializationName);
            serializations.add((Serialization)
                    ReflectionUtils.newInstance(serializionClass, getConf()));
        } catch (ClassNotFoundException e) {
            LOG.warn("Serialization class not found: ", e);
        }
    }

    public <T> Serializer<T> getSerializer(Class<T> c) {
        Serialization<T> serializer = getSerialization(c);
        if (serializer != null) {
            return serializer.getSerializer(c);
        }
        return null;
    }

    public <T> Deserializer<T> getDeserializer(Class<T> c) {
        Serialization<T> serializer = getSerialization(c);
        if (serializer != null) {
            return serializer.getDeserializer(c);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> Serialization<T> getSerialization(Class<T> c) {
        for (Serialization serialization : serializations) {
            if (serialization.accept(c)) {
                return (Serialization<T>) serialization;
            }
        }
        return null;
    }
}