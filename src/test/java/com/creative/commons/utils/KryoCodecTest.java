package com.creative.commons.utils;

import com.creative.model.Certificates;
import com.creative.model.Father;
import com.creative.model.Message;
import com.creative.model.Son;
import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.concurrent.TimeUnit;

public class KryoCodecTest extends CodecTest {

    @Test
    public void kryoCodecSizeTest() throws Throwable {
        KryoCodec.register(Message.class);
        byte[] obj1 = KryoCodec.encode(msg1);
        System.out.println(obj1.length);
    }

    @Benchmark
    public void kryoCodecMultiTest() throws Exception {
        kryoEncodeAndDecode();
    }

    @Test
    public void testThrpt() throws InterruptedException {
        final int[] count = {0};
        Thread t = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    kryoEncodeAndDecode();
                    count[0]++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(10);
        t.interrupt();
        System.out.println(count[0] / 10);
    }

    private void kryoEncodeAndDecode() throws Exception {
        KryoCodec.register(Message.class);
        KryoCodec.register(Father.class);
        KryoCodec.register(Son.class);
        KryoCodec.register(Certificates.class);

        byte[] obj1 = KryoCodec.encode(msg1);
        msg1 = (Message) KryoCodec.decode(obj1);

        byte[] obj2 = KryoCodec.encode(msg2);
        msg2 = (Message) KryoCodec.decode(obj2);

        byte[] obj3 = KryoCodec.encode(msg3);
        msg3 = (Message) KryoCodec.decode(obj3);
    }

    @Test
    public void kyroCodecTest() throws Exception {
        KryoCodec.register(Father.class);
        KryoCodec.register(Son.class);
        KryoCodec.register(Certificates.class);

        Father father = new Father();
        byte[] obj1 = KryoCodec.encode(father);
        Father fatherCopy = (Father) KryoCodec.decode(obj1);

        Assert.assertEquals(father, fatherCopy);

        //Timestamp type
        //System.out.println(fatherCopy.getLocation_time());
        //System.out.println(father.getLocation_time());

        //Locale
        //System.out.println(fatherCopy.getLocale());
        //System.out.println(father.getLocale());

        //EnumSet
        //bug
        //System.out.println(fatherCopy.getCertificates());
        //System.out.println(father.getCertificates());

        //BitSet
        //bug
        //System.out.println(fatherCopy.getqRCode());
        //System.out.println(father.getqRCode());

        Son son = new Son();
        byte[] obj2 = KryoCodec.encode(son);
        Son sonCopy = (Son) KryoCodec.decode(obj2);

        //Enum
        System.out.println(sonCopy.getCf());
        System.out.println(son.getCf());

        //same name filed,parent first
        System.out.println(son.getAge());
        System.out.println(sonCopy.getAge());
        System.out.println(son.isCCP());
        System.out.println(sonCopy.isCCP());
        System.out.println(son.getSalary());
        System.out.println(sonCopy.getSalary());
    }
}