package com.katsuna.commons.entities;

public enum ColorProfile {
    AUTO("common_profile_auto"),
    MAIN("common_profile_color_main"),
    CONTRAST("common_profile_color_contrast"),
    COLOR_IMPAIREMENT("common_profile_color_impairement"),
    COLOR_IMPAIRMENT_AND_CONTRAST("common_profile_color_impairement_contrast");

    private final String resId;

    ColorProfile(String resId) {
        this.resId = resId;
    }

    public String getResId() {
        return resId;
    }
}