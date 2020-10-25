package com.gmail.benrcarver.serverlessnamenode.io.serializer;

import com.gmail.benrcarver.serverlessnamenode.conf.Configuration;
import com.gmail.benrcarver.serverlessnamenode.conf.Configured;
import com.gmail.benrcarver.serverlessnamenode.io.Writable;
import com.gmail.benrcarver.serverlessnamenode.util.ReflectionUtils;

import java.io.*;

/**
 * A {@link Serialization} for {@link Writable}s that delegates to
 * {@link Writable#write(java.io.DataOutput)} and
 * {@link Writable#readFields(java.io.DataInput)}.
 */
public class WritableSerialization extends Configured
	implements Serialization<Writable> {
        static class WritableDeserializer extends Configured
                implements Deserializer<Writable> {

            private Class<?> writableClass;
            private DataInputStream dataIn;

            public WritableDeserializer(Configuration conf, Class<?> c) {
                setConf(conf);
                this.writableClass = c;
            }

            @Override
            public void open(InputStream in) {
                if (in instanceof DataInputStream) {
                    dataIn = (DataInputStream) in;
                } else {
                    dataIn = new DataInputStream(in);
                }
            }

            @Override
            public Writable deserialize(Writable w) throws IOException {
                Writable writable;
                if (w == null) {
                    writable
                            = (Writable) ReflectionUtils.newInstance(writableClass, getConf());
                } else {
                    writable = w;
                }
                writable.readFields(dataIn);
                return writable;
            }

            @Override
            public void close() throws IOException {
                dataIn.close();
            }

        }

        static class WritableSerializer extends Configured implements
                Serializer<Writable> {

            private DataOutputStream dataOut;

            @Override
            public void open(OutputStream out) {
                if (out instanceof DataOutputStream) {
                    dataOut = (DataOutputStream) out;
                } else {
                    dataOut = new DataOutputStream(out);
                }
            }

            @Override
            public void serialize(Writable w) throws IOException {
                w.write(dataOut);
            }

            @Override
            public void close() throws IOException {
                dataOut.close();
            }

        }

        @Override
        public boolean accept (Class < ? > c){
        return Writable.class.isAssignableFrom(c);
    }

        @Override
        public Serializer<Writable> getSerializer (Class < Writable > c) {
        return new WritableSerializer();
    }

        @Override
        public Deserializer<Writable> getDeserializer (Class < Writable > c) {
        return new WritableDeserializer(getConf(), c);
    }

    }
}
