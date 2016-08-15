package com.creative.commons.utils;

import com.creative.model.Father;
import com.creative.model.Message;
import com.creative.model.Son;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.After;
import org.junit.Before;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Von Gosling
 */
@State(Scope.Benchmark)
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
        //props.put("timesZone", tz);
        props.put("timestamp", tt);

        msg1.setProperties(props);
    }


    @After
    public void destroy() {
        props.clear();
    }
}
