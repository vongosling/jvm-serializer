package com.creative.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Von Gosling
 */
public class HessianCodecTest {
    Message             obj   = new Message(
                                      "Most message-oriented middleware (MOM) products treat messages as lightweight entities that consist of a header and a body. The header contains fields used for message routing and identification; the body contains the application data being sent.");
    Map<String, Object> props = Maps.newHashMap();

    @Before
    public void init() {
        props.put("boolean", Boolean.TRUE);
        props.put("byte", Byte.MAX_VALUE);
        props.put("short", Short.MAX_VALUE);
        props.put("int", Integer.MAX_VALUE);
        props.put("long", Long.MAX_VALUE);
        props.put("float", Float.MAX_EXPONENT);
        props.put("double", Double.MAX_EXPONENT);
        props.put("String", String.valueOf("VonGosling"));
        props.put("trait", Lists.newArrayListWithCapacity(1000));
        props.put("case", Objects.toString(obj));

        obj.setProperties(props);
    }

    @Test
    public void testHessianSerializer() throws Throwable {
        Stopwatch watch = Stopwatch.createStarted();
        SerializerFactory sf = new SerializerFactory();
        for (int i = 1; i < 100001; i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(baos);
            output.setSerializerFactory(sf);
            output.setCloseStreamOnClose(true);
            output.startMessage();
            output.writeObject(obj);
            output.completeMessage();

            output.close();
            //baos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            Hessian2Input input = new Hessian2Input(bis);
            input.setSerializerFactory(sf);
            input.setCloseStreamOnClose(true);
            input.startMessage();
            input.readObject();
            input.completeMessage();

            input.close();
            //bis.close();
        }
        watch.stop();
        System.out.println(String.format("Hessian serializer-deserializer costs: %d ms",
                watch.elapsed(TimeUnit.MILLISECONDS)));

    }

    @Test
    public void testExternalizer() throws IOException, ClassNotFoundException {
        Stopwatch watch = Stopwatch.createStarted();
        for (int i = 1; i < 100001; i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            baos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            ois.readObject();
            ois.close();
            bais.close();
        }
        watch.stop();
        System.out.println(String.format("Java externalizer costs: %d ms",
                watch.elapsed(TimeUnit.MILLISECONDS)));
    }

    @After
    public void destroy() {
        props.clear();
    }
}
