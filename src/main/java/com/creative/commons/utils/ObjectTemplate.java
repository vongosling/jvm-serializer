package com.creative.commons.utils;

import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.template.Templates;
import org.msgpack.type.*;
import org.msgpack.unpacker.Converter;
import org.msgpack.unpacker.Unpacker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xinyuzhou.zxy
 */
public class ObjectTemplate  extends AbstractTemplate<Object>{
    static final ObjectTemplate instance = new ObjectTemplate();

    private ObjectTemplate() {
    }

    static public ObjectTemplate getInstance() {
        return instance;
    }


    @Override
    public void write(Packer pk, Object v, boolean required) throws IOException {
        if (v == null) {
            if (required) {
                throw new MessageTypeException("Attempted to write null");
            }
            pk.writeNil();
            return;
        }
        pk.write(v);
    }

    @Override
    public Object read(Unpacker u, Object to, boolean required) throws IOException {
        if (!required && u.trySkipNil()) {
            return null;
        }

        return toObject(u.readValue());
    }

    private static Object toObject(Value value) throws IOException {
        Converter conv = new Converter(value);
        if (value.isNilValue()) { // null
            return null;
        } else if (value.isRawValue()) { // byte[] or String or maybe Date?
            // deserialize value to String object
            RawValue v = value.asRawValue();
            return conv.read(Templates.TString);
        } else if (value.isBooleanValue()) { // boolean
            return conv.read(Templates.TBoolean);
        } else if (value.isIntegerValue()) { // int or long or BigInteger
            // deserialize value to int
            IntegerValue v = value.asIntegerValue();
            return conv.read(Templates.TLong);
        } else if (value.isFloatValue()) { // float or double
            // deserialize value to double
            FloatValue v = value.asFloatValue();
            return conv.read(Templates.TDouble);
        } else if (value.isArrayValue()) { // List or Set
            // deserialize value to List object
            ArrayValue v = value.asArrayValue();
            List<Object> ret = new ArrayList<>(v.size());
            for (Value elementValue : v) {
                ret.add(toObject(elementValue));
            }
            return ret;
        } else if (value.isMapValue()) { // Map
            MapValue v = value.asMapValue();


            Map map = new HashMap<>(v.size());
            for (Map.Entry<Value, Value> entry : v.entrySet()) {
                Value key = entry.getKey();
                Value val = entry.getValue();

                map.put(toObject(key), toObject(val));
            }

            return map;
        } else {
            throw new RuntimeException("fatal error");
        }
    }
}
