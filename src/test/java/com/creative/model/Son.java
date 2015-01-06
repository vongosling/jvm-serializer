package com.creative.model;

/**
 * @author Von Gosling
 */
public class Son extends Father{
    private static final long serialVersionUID = 6952023538649252068L;
    private byte multilingualer = Byte.MIN_VALUE;
    private short age = Short.MIN_VALUE;
    private char education = Character.MIN_VALUE;
    private int phoneNumber = Integer.MIN_VALUE;
    private long anniversary= Long.MIN_VALUE;
    private float cet4Score=Float.MIN_VALUE;
    private double cet6Score=Double.MIN_VALUE;
    private boolean isCCP = Boolean.FALSE;

    private Certificates cf = Certificates.CET4;

    public Certificates getCf() {
        return cf;
    }

    public void setCf(Certificates cf) {
        this.cf = cf;
    }
}
