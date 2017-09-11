package com.katsuna.commons.entities;

public class KatsunaApp {

    public String title;
    public String packageName;
    public int drawableId;
    public int drawableOpa54Id;

    public KatsunaApp(String title, String packageName, int drawableId, int drawableOpa54Id) {
        this.title = title;
        this.packageName = packageName;
        this.drawableId = drawableId;
        this.drawableOpa54Id = drawableOpa54Id;
    }

    @Override
    public String toString() {
        return " Katsuna App: " + title + " " + packageName;
    }
}
