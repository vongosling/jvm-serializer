package com.creative.model;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Von Gosling
 */
@org.msgpack.annotation.Message
public class Message implements Serializable {
    private static final long serialVersionUID = 1892011038502772782L;
    /**
     * message properties
     */
    protected Map<String, Object> properties = Maps.newHashMap();
    /**
     * message headers
     */
    protected Map<String, Object> headers = Maps.newHashMap();
    /**
     * message body
     */
    protected transient Serializable body;

    public Message(Serializable body) {
        this.body = body;
    }

    public Message() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (body != null ? !body.equals(message.body) : message.body != null) return false;
        if (headers != null ? !headers.equals(message.headers) : message.headers != null) return false;
        if (properties != null ? !properties.equals(message.properties) : message.properties != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = properties != null ? properties.hashCode() : 0;
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
