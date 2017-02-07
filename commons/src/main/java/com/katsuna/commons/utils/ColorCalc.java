package com.katsuna.commons.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;

/**
 * Calculates color based on ColorProfile and ColorProfileKey.
 */

public class ColorCalc {

    private final static String PROFILE_NOT_SET = "profile not set";
    private final static String PROFILE_KEY_NOT_SET = "profile key not set";

    public static int getColor(Context context, ColorProfileKey key, ColorProfile profile) {
        String color = ColorCalc.getColorResId(key, profile);
        int resId = ResourcesUtils.getColor(context, color);
        return ContextCompat.getColor(context, resId);
    }

    public static String getColorResId(ColorProfileKey key, ColorProfile profile) {

        switch (key) {
            case MAIN_COLOR_VERY_LIGHT:
                return "common_grey50";
            case MAIN_COLOR_LIGHT:
                return "common_grey100";
            case MAIN_COLOR_MEDIUM:
                return "common_grey300";
            case MAIN_COLOR_DARK:
                return "common_grey600";
            case POP_UP_COLOR:
                return "common_white";
            case ACCENT1_COLOR:
                switch (profile) {
                    case MAIN:
                        return "common_sundown";
                    case CONTRAST:
                        return "common_redA100";
                    case COLOR_IMPAIREMENT:
                        return "common_yellow400";
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return "common_yellowA400";
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case ACCENT2_COLOR:
                switch (profile) {
                    case MAIN:
                    case COLOR_IMPAIREMENT:
                        return "common_blue900";
                    case CONTRAST:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return "common_indigo900";
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case DENIAL_COLOR:
                switch (profile) {
                    case MAIN:
                    case CONTRAST:
                        return "common_red500";
                    case COLOR_IMPAIREMENT:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return "common_yellow700";
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case PRIMARY_TEXT_OPACITY:
                switch (profile) {
                    case MAIN:
                    case COLOR_IMPAIREMENT:
                        return "common_black87";
                    case CONTRAST:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return "common_black";
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case SECONDARY_TEXT_OPACITY:
                switch (profile) {
                    case MAIN:
                    case COLOR_IMPAIREMENT:
                        return "common_black54";
                    case CONTRAST:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return "common_black70";
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case DISABLED_TEXT_OPACITY:
                return "common_black38";
            case DIVIDERS_OPACITY:
                return "common_black12";
            case ACTIVE_ICONS_OPACITY:
                switch (profile) {
                    case MAIN:
                    case COLOR_IMPAIREMENT:
                        return "common_black54";
                    case CONTRAST:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return "common_black87";
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case INACTIVE_ICONS_OPACITY:
                switch (profile) {
                    case MAIN:
                    case COLOR_IMPAIREMENT:
                        return "common_black38";
                    case CONTRAST:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return "common_black50";
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            default:
                throw new RuntimeException(PROFILE_KEY_NOT_SET);
        }
    }
}
