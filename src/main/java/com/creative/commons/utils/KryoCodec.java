package com.creative.commons.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.*;
import com.google.common.collect.Lists;
import org.objenesis.strategy.StdInstantiatorStrategy;
import sun.util.calendar.ZoneInfo;

/**
 * @author von gosling
 */
public abstract class KryoCodec {
    private static final List<Class<?>> classList = Lists.newArrayList();
    private static final List<Serializer<?>> serializerList = Lists.newArrayList();
    private static final List<Integer> idList = Lists.newArrayList();
    private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();

            kryo.register(byte[].class);
            kryo.register(char[].class);
            kryo.register(short[].class);
            kryo.register(int[].class);
            kryo.register(long[].class);
            kryo.register(float[].class);
            kryo.register(double[].class);
            kryo.register(boolean[].class);
            kryo.register(String[].class);
            kryo.register(Object[].class);
            kryo.register(KryoSerializable.class);
            kryo.register(BigInteger.class);
            kryo.register(BigDecimal.class);
            kryo.register(Class.class);
            kryo.register(Date.class);
            //kryo.register(Enum.class);
            kryo.register(EnumSet.class);
            kryo.register(Currency.class);
            kryo.register(StringBuffer.class);
            kryo.register(StringBuilder.class);
            kryo.register(Collections.EMPTY_LIST.getClass());
            kryo.register(Collections.EMPTY_MAP.getClass());
            kryo.register(Collections.EMPTY_SET.getClass());
            kryo.register(Collections.singletonList(null).getClass());
            kryo.register(Collections.singletonMap(null, null).getClass());
            kryo.register(Collections.singleton(null).getClass());
            kryo.register(TreeSet.class);
            kryo.register(Collection.class);
            kryo.register(TreeMap.class);
            kryo.register(Map.class);
            kryo.register(TimeZone.class);
            kryo.register(Calendar.class);
            kryo.register(Locale.class);

            kryo.register(BitSet.class);
            kryo.register(HashMap.class);
            kryo.register(Timestamp.class);
            kryo.register(ZoneInfo.class);
            kryo.register(ArrayList.class);

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
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
            return kryo;
        }
    };

    public static synchronized void registerClass(Class<?> className, Serializer<?> serializer,
                                                  int id) {
        classList.add(className);
        serializerList.add(serializer);
        idList.add(id);
    }

    public static synchronized void register(Class<?> className) {
        getKryo().register(className);
    }

    public static Kryo getKryo() {
        return kryos.get();
    }

    public static Object decode(byte[] bytes) throws Exception {
        Input input = new Input(bytes);
        return getKryo().readClassAndObject(input);
    }

    public static byte[] encode(Object object) throws Exception {
        //4K
        Output output = new Output(4096);
        getKryo().writeClassAndObject(output, object);
        return output.toBytes();
    }
}
