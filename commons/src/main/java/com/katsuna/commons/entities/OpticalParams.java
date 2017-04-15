package com.katsuna.commons.entities;

public class OpticalParams {

    private int mTextSize;
    private int mTextColor;
    private String mTextFontFamily;
    private int mHeight;
    private int mStyle;

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    public String getTextFontFamily() {
        return mTextFontFamily;
    }

    public void setTextFontFamily(String mTextFontFamily) {
        this.mTextFontFamily = mTextFontFamily;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public int getStyle() {
        return mStyle;
    }

    public void setStyle(int style) {
        this.mStyle = style;
    }
}
