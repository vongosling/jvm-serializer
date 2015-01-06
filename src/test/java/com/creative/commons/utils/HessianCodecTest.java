package com.creative.commons.utils;

import com.caucho.hessian.io.ExtSerializerFactory;
import com.caucho.hessian.io.LocaleSerializer;
import com.caucho.hessian.io.SerializerFactory;
import com.creative.model.Father;
import com.creative.model.Message;
import com.creative.model.Son;
import com.google.common.base.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HessianCodecTest extends CodecTest{
    @Test
    public void hessianCodecTest() throws Throwable {
        Father father = new Father();
        byte[] obj1 = HessianCodec.encode(father);
        Father fatherCopy = (Father) HessianCodec.decode(obj1);

        //Timestamp type is null
        //System.out.println(fatherCopy.getLocation_time());
        //System.out.println(father.getLocation_time());

        //Float type field is null
        //System.out.println(fatherCopy.getCet4Score());

        //Locale,if not set ext ExtSerializerFactory
        //bugs,http://bugs.caucho.com/view.php?id=5046
        //System.out.println(fatherCopy.getLocale());
        //System.out.println(father.getLocale());

        //EnumSet
        //Warning:WARNING: Hessian/Burlap: 'java.lang.Enum' is an unknown class in sun.misc.Launcher$AppClassLoader@9cc3baa:
        //java.lang.RuntimeException: Class java.lang.Enum is not an enum
        //System.out.println(fatherCopy.getCertificates());
        //System.out.println(father.getCertificates());

        //BitSet
        //bug
        //System.out.println(fatherCopy.getqRCode());
        //System.out.println(father.getqRCode());

        SerializerFactory serializerFactory = SerializerFactory.createDefault();
        ExtSerializerFactory extFactory = new ExtSerializerFactory();
        extFactory.addSerializer(Locale.class, LocaleSerializer.create());
        //extFactory.addSerializer(Enum.class,new EnumSerializer(Enum.class));
        //extFactory.addDeserializer(Enum.class,new EnumDeserializer(Enum.class));
        serializerFactory.addFactory(extFactory);
        System.out.println(serializerFactory.getSerializer(Locale.class));
        System.out.println(serializerFactory.getSerializer(BigDecimal.class));
        System.out.println(serializerFactory.getSerializer(EnumSet.class));
        System.out.println(serializerFactory.getSerializer(BitSet.class));
        //System.out.println(serializerFactory.getDeserializer(Enum.class));

        Assert.assertEquals(father.getSalary(), fatherCopy.getSalary());

        Son son = new Son();
        byte[] obj2 = HessianCodec.encode(son);
        Son sonCopy = (Son) HessianCodec.decode(obj2);

        //Enum
        System.out.println(sonCopy.getCf());
        System.out.println(son.getCf());

        //same name filed
        System.out.println(son.getAge());
        System.out.println(sonCopy.getAge());
        System.out.println(son.isCCP());
        System.out.println(sonCopy.isCCP());
    }

    @Test
    public void hessianCodecMultiTest() throws Throwable {
        //Warmup
        for (int i = 1; i < warmupIter; i++) {
            hessianEncodeAndDecode();
        }
        //do multi-test
        Stopwatch watch = Stopwatch.createStarted();
        for (int i = 1; i < testIter; i++) {
            hessianEncodeAndDecode();
        }
        watch.stop();
        System.out.println(String.format("Hessian serializer-deserializer costs: %d ms",
                watch.elapsed(TimeUnit.MILLISECONDS)));
    }

    private void hessianEncodeAndDecode() throws IOException {
        byte[] obj1 = HessianCodec.encode(msg1);
        msg1 = (Message) HessianCodec.decode(obj1);

        byte[] obj2 = HessianCodec.encode(msg2);
        msg2 = (Message) HessianCodec.decode(obj2);

        byte[] obj3 = HessianCodec.encode(msg3);
        msg3 = (Message) HessianCodec.decode(obj3);
    }
}