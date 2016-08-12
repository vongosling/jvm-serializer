package com.creative.commons.utils;

import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * @author xinyuzhou.zxy
 */
public class MsgPackCodec {
    private static MessagePack messagePack = new MessagePack();

    static {
        messagePack.register(Object.class, ObjectTemplate.getInstance());
    }
    public static <T> byte[] encode(T content) {

        try {
            return messagePack.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T decode(byte[] content, Class<T> clazz) {

        try {
            return messagePack.read(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
