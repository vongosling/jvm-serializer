package com.creative.commons.utils;

import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author von gosling
 */
public abstract class KryoCodec {
    private static final List<Class<?>> classList = new ArrayList<Class<?>>();
    private static final List<Serializer<?>> serializerList = new ArrayList<Serializer<?>>();
    private static final List<Integer> idList = new ArrayList<Integer>();
    private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            int size = idList.size();
            for (int i = 0; i < size; i++) {
                kryo.register(classList
                                .get(i),
                        serializerList
                                .get(i),
                        idList.get(i));
            }
            kryo.setRegistrationRequired(true);
            kryo.setReferences(false);
            return kryo;
        }
    };

    public static synchronized void registerClass(Class<?> className, Serializer<?> serializer,
                                                  int id) {
        classList.add(className);
        serializerList.add(serializer);
        idList.add(id);
    }

    public static Kryo getKryo() {
        return kryos.get();
    }

    public static Object decode(byte[] bytes) throws Exception {
        Input input = new Input(bytes);
        return getKryo().readClassAndObject(input);
    }

    public static byte[] encode(Object object) throws Exception {
        Output output = new Output(256);
        getKryo().register(object.getClass());
        getKryo().writeClassAndObject(output, object);
        return output.toBytes();
    }
}
