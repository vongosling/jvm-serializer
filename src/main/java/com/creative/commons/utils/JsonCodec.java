package com.creative.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * @author von gosling
 */
public abstract class JsonCodec {
    private static SerializeConfig config = new SerializeConfig();
    private static SerializerFeature[] sfeatures = { SerializerFeature.UseISO8601DateFormat };
    private static Feature[] features = { Feature.AllowISO8601DateFormat };
    static {
        config.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        config.put(java.sql.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        config.put(java.sql.Timestamp.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    public static byte[] encode(Object content) {
        return JSONObject.toJSONString(content, config, sfeatures).getBytes();
    }

    public static Object decode(byte[] content) {
        return JSON.parse(content, features);
    }
}