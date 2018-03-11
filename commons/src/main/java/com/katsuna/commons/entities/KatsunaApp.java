package com.katsuna.commons.entities;

public class KatsunaApp {

    public String title;
    public String packageName;
    public int drawableId;

    public KatsunaApp(String title, String packageName, int drawableId) {
        this.title = title;
        this.packageName = packageName;
        this.drawableId = drawableId;
    }

    @Override
    public String toString() {
        return " Katsuna App: " + title + " " + packageName;
    }
}
