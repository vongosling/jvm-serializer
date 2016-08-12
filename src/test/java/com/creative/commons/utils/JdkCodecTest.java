package com.creative.commons.utils;

import com.creative.model.Father;
import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.*;

/**
 * @author xinyuzhou.zxy
 */
public class JdkCodecTest extends CodecTest{
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

    @Benchmark
    public void javaCodecMultiTest() throws Exception {
        javaEncodeAndDecode(msg1);
        javaEncodeAndDecode(msg2);
        javaEncodeAndDecode(msg3);
    }

    @Test
    public void javaCodecTest() throws Throwable {
        Father father = new Father();
        Father fatherCopy = (Father) javaEncodeAndDecode(father);

        Assert.assertEquals(father, fatherCopy);
    }

    @Test
    public void javaCodecSizeTest() throws Throwable {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(msg1);
        System.out.println(baos.toByteArray().length);
        oos.close();
        baos.close();
    }

}
