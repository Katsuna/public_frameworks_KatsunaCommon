package com.katsuna.commons.entities;

public class GenderInfo {

    public Gender gender;
    public String descr;

    @Override
    public String toString() {
        return String.format("GenderInfo[gender=%s, descr=%s]", gender.name(), descr);
    }
}