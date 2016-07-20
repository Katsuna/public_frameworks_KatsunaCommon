package com.katsuna.commons.entities;

public class Profile extends BaseObject {

    public static final String TABLE_NAME = "profile";
    public static final String COL_TYPE = "type";

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Profile[type=%s]", getType());
    }
}

