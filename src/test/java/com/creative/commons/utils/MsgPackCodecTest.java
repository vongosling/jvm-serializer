package com.creative.commons.utils;

import com.creative.model.Message;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;

/**
 * @author xinyuzhou.zxy
 */
public class MsgPackCodecTest extends CodecTest{

    @Benchmark
    public void measureName() throws IOException {
        msgPackEncodeAndDecode();
    }

    @Test
    public void msgPackCodecSizeTest() {
        byte[] obj1 = MsgPackCodec.encode(msg1);
        System.out.println(obj1.length);
    }


    private void msgPackEncodeAndDecode() throws IOException {
        byte[] obj1 = MsgPackCodec.encode(msg1);
        msg1 = MsgPackCodec.decode(obj1, Message.class);

        byte[] obj2 = MsgPackCodec.encode(msg2);
        msg2 = MsgPackCodec.decode(obj2, Message.class);

        byte[] obj3 = MsgPackCodec.encode(msg3);
        msg3 = MsgPackCodec.decode(obj3, Message.class);
    }
}

