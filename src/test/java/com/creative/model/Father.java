package com.creative.model;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Von Gosling
 */
public class Father implements Serializable {
    private static final long serialVersionUID = 893890389701153928L;
    protected boolean isCCP = Boolean.TRUE;
    protected byte multilingualer = Byte.MAX_VALUE;
    protected short age = Short.MAX_VALUE;
    protected char education = Character.MAX_VALUE;
    protected int phoneNumber = Integer.MAX_VALUE;
    protected long anniversary = Long.MAX_VALUE;
    protected float cet4Score = Float.MAX_VALUE;
    protected double cet6Score = Double.MAX_VALUE;

    protected Date birthday = Calendar.getInstance().getTime();
    protected BigDecimal salary = BigDecimal.valueOf(11000.13);

    protected TimeZone location = Calendar.getInstance().getTimeZone();
    protected Timestamp location_time = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());

    protected Locale locale = Locale.getDefault();
    //protected EnumSet certificates = EnumSet.allOf(Certificates.class);
    protected BitSet qRCode = BitSet.valueOf(new long[]{123, 345});

    public BitSet getqRCode() {
        return qRCode;
    }

    public void setqRCode(BitSet qRCode) {
        this.qRCode = qRCode;
    }

//    public EnumSet getCertificates() {
//        return certificates;
//    }

//    public void setCertificates(EnumSet certificates) {
//        this.certificates = certificates;
//    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isCCP() {
        return isCCP;
    }

    public void setCCP(boolean isCCP) {
        this.isCCP = isCCP;
    }

    public byte getMultilingualer() {
        return multilingualer;
    }

    public void setMultilingualer(byte multilingualer) {
        this.multilingualer = multilingualer;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public char getEducation() {
        return education;
    }

    public void setEducation(char education) {
        this.education = education;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(long anniversary) {
        this.anniversary = anniversary;
    }

    public float getCet4Score() {
        return cet4Score;
    }

    public void setCet4Score(float cet4Score) {
        this.cet4Score = cet4Score;
    }

    public double getCet6Score() {
        return cet6Score;
    }

    public void setCet6Score(double cet6Score) {
        this.cet6Score = cet6Score;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public TimeZone getLocation() {
        return location;
    }

    public void setLocation(TimeZone location) {
        this.location = location;
    }

    public Timestamp getLocation_time() {
        return location_time;
    }

    public void setLocation_time(Timestamp location_time) {
        this.location_time = location_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Father father = (Father) o;

        if (age != father.age) return false;
        if (anniversary != father.anniversary) return false;
        if (Float.compare(father.cet4Score, cet4Score) != 0) return false;
        if (Double.compare(father.cet6Score, cet6Score) != 0) return false;
        if (education != father.education) return false;
        if (isCCP != father.isCCP) return false;
        if (multilingualer != father.multilingualer) return false;
        if (phoneNumber != father.phoneNumber) return false;
        if (birthday != null ? !birthday.equals(father.birthday) : father.birthday != null) return false;
        if (location != null ? !location.equals(father.location) : father.location != null) return false;
        if (location_time != null ? !location_time.equals(father.location_time) : father.location_time != null)
            return false;
        if (salary != null ? !salary.equals(father.salary) : father.salary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (isCCP ? 1 : 0);
        result = 31 * result + (int) multilingualer;
        result = 31 * result + (int) age;
        result = 31 * result + (int) education;
        result = 31 * result + phoneNumber;
        result = 31 * result + (int) (anniversary ^ (anniversary >>> 32));
        result = 31 * result + (cet4Score != +0.0f ? Float.floatToIntBits(cet4Score) : 0);
        temp = Double.doubleToLongBits(cet6Score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (location_time != null ? location_time.hashCode() : 0);
        return result;
    }
}
