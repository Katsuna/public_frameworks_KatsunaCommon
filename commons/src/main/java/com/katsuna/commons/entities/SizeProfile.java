package com.katsuna.commons.entities;

import com.katsuna.commons.R;

public enum SizeProfile {
    AUTO(R.string.common_profile_auto),
    SIMPLE(R.string.common_profile_simple),
    INTERMEDIATE(R.string.common_profile_intermediate),
    ADVANCED(R.string.common_profile_advanced);

    private final int resId;

    SizeProfile(int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}