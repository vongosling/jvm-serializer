package com.creative.commons.utils;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Von Gosling
 */
public class Message implements Serializable {
    private static final long     serialVersionUID = 1892011038502772782L;
    /**
     * message properties
     */
    protected Map<String, Object> properties       = Maps.newHashMap();
    /**
     * message headers
     */
    protected Map<String, Object> headers          = Maps.newHashMap();
    /**
     * message body
     */
    protected Serializable        body;

    public Message(Serializable body) {
        this.body = body;
    }

    /**
     * @return the properties
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * @return the headers
     */
    public Map<String, Object> getHeaders() {
        return headers;
    }

    /**
     * @param headers the headers to set
     */
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    /**
     * @return the body
     */
    public Serializable getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Serializable body) {
        this.body = body;
    }

}
