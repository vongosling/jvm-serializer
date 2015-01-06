package com.creative.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Locale;

import com.caucho.hessian.io.*;

/**
 * @author von gosling
 */
public abstract class HessianCodec {

    private static final SerializerFactory serializerFactory =  SerializerFactory.createDefault();

    static {
        ExtSerializerFactory extFactory = new ExtSerializerFactory();
        extFactory.addSerializer(Locale.class, LocaleSerializer.create());
        extFactory.addSerializer(BigDecimal.class, new StringValueSerializer());
        extFactory.addDeserializer(BigDecimal.class,new BigDecimalDeserializer());
        serializerFactory.addFactory(extFactory);
    }

    public static Serializable decode(byte[] array) throws IOException {
        Object obj;
        ByteArrayInputStream bais = new ByteArrayInputStream(array);
        Hessian2Input hi = new Hessian2Input(bais);
        hi.setSerializerFactory(serializerFactory);
        hi.setCloseStreamOnClose(true);
        hi.startMessage();
        obj = hi.readObject();
        hi.completeMessage();

        hi.close();
        return (Serializable) obj;
    }

    public static byte[] encode(Object data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output ho = new Hessian2Output(baos);
        ho.setSerializerFactory(serializerFactory);
        ho.setCloseStreamOnClose(true);
        ho.startMessage();
        ho.writeObject(data);
        ho.completeMessage();

        ho.close();
        return baos.toByteArray();
    }
}
