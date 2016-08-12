package com.creative.commons.utils;

import com.creative.model.Message;
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


    private void msgPackEncodeAndDecode() throws IOException {
        byte[] obj1 = MsgPackCodec.encode(msg1);
        msg1 = MsgPackCodec.decode(obj1, Message.class);

        byte[] obj2 = MsgPackCodec.encode(msg2);
        msg2 = MsgPackCodec.decode(obj2, Message.class);

        byte[] obj3 = MsgPackCodec.encode(msg3);
        msg3 = MsgPackCodec.decode(obj3, Message.class);
    }
}

@org.msgpack.annotation.Message
class NewMessage {
    String a;

    public NewMessage() {
    }

    public NewMessage(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "NewMessage{" +
                "a='" + a + '\'' +
                '}';
    }
}
