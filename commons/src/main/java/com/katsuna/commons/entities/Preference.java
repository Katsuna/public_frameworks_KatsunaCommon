package com.katsuna.commons.entities;

/**
 * Holds the key-value pair of a katsuna preference.
 */

public class Preference extends BaseObject {

    public static final String TABLE_NAME = "preference";
    public static final String COL_KEY = "key";
    public static final int COL_KEY_INDEX = 1;
    public static final String COL_VALUE = "value";
    public static final int COL_VALUE_INDEX = 2;
    public static final String COL_DESCR = "descr";
    public static final int COL_DESCR_INDEX = 3;

    private String key;
    private String value;
    private String descr;

    public Preference() {
    }

    public Preference(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Preference(String key, String value, String descr) {
        this(key, value);
        this.descr = descr;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return String.format("Preference[key=%s, value=%s, descr=%s]", key, value, descr);
    }
}
