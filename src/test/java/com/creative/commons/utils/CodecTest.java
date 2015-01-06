package com.creative.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.caucho.hessian.io.*;
import com.creative.model.Certificates;
import com.creative.model.Father;
import com.creative.model.Message;
import com.creative.model.Son;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Von Gosling
 */
public class CodecTest {
    protected Message msg1 = new Message(
            "Most message-oriented middleware (MOM) products treat messages as lightweight entities that consist of a header and a body. The header contains fields used for message routing and identification; the body contains the application data being sent.");
    protected Message msg2 = new Message(new Father());
    protected Message msg3 = new Message(new Son());

    protected Map<String, Object> props = Maps.newHashMap();

    protected Date now = Calendar.getInstance().getTime();
    protected BigDecimal money = BigDecimal.valueOf(110.13);
    protected TimeZone tz = Calendar.getInstance().getTimeZone();
    protected Timestamp tt = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());

    //intx CompileThreshold=10000
    protected final int warmupIter = 12000;
    protected final int testIter = 5000;

    @Before
    public void init() {
        props.put("boolean", Boolean.TRUE);
        props.put("byte", Byte.MAX_VALUE);
        props.put("short", Short.MAX_VALUE);
        props.put("int", Integer.MAX_VALUE);
        props.put("long", Long.MAX_VALUE);
        props.put("float", Float.MAX_EXPONENT);
        props.put("double", Double.MAX_EXPONENT);
        props.put("char", Character.MAX_VALUE);
        props.put("String", String.valueOf("VonGosling"));
        props.put("trait", Lists.newArrayListWithCapacity(1000));
        props.put("case", Objects.toString(msg1));

        //Some particular types
        props.put("bigDecimal", money);
        props.put("date", now);
        props.put("timesZone", tz);
        props.put("timestamp", tt);

        msg1.setProperties(props);
    }

    @Test
    public void javaCodecTest() throws Throwable {
        Father father = new Father();
        Father fatherCopy = (Father) javaEncodeAndDecode(father);

        Assert.assertEquals(father, fatherCopy);
    }


    @Test
    public void javaCodecMultiTest() throws Exception {
        for (int i = 1; i < warmupIter; i++) {
            javaEncodeAndDecode(msg1);
            javaEncodeAndDecode(msg2);
            javaEncodeAndDecode(msg3);
        }
        Stopwatch watch = Stopwatch.createStarted();
        for (int i = 1; i < testIter; i++) {
            javaEncodeAndDecode(msg1);
            javaEncodeAndDecode(msg2);
            javaEncodeAndDecode(msg3);
        }
        watch.stop();
        System.out.println(String.format("Java serializer-deserializer costs: %d ms",
                watch.elapsed(TimeUnit.MILLISECONDS)));
    }

    private Object javaEncodeAndDecode(Object msg) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(msg);
        oos.close();
        baos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        ois.close();
        bais.close();

        return obj;
    }

    @After
    public void destroy() {
        props.clear();
    }
}
