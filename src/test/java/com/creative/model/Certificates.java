package com.creative.model;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Von Gosling
 */
public enum Certificates {
    CET4("cet4"),
    CET6("cet6"),
    MCSE("mcse"),
    CCNP("ccnp"),
    OCP("ocp"),
    SCJP("scjp");

    private String value;

    Certificates() {
    }

    Certificates(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    private static Map<String, Certificates> stringToEnum = Maps.newHashMap();

    static {
        for (Certificates fileFormat : values()) {
            stringToEnum.put(fileFormat.getValue(), fileFormat);
        }
    }

    public static Certificates fromString(String value) {
        return stringToEnum.get(value.toLowerCase());
    }
}
