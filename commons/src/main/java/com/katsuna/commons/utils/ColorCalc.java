/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.katsuna.commons.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;

/**
 * Calculates color based on ColorProfile and ColorProfileKey.
 */

public class ColorCalc {

    private final static String PROFILE_NOT_SET = "profile not set";
    private final static String PROFILE_KEY_NOT_SET = "profile key not set";

    public static int getColor(Context context, ColorProfileKey key, ColorProfile profile) {
        int resId = ColorCalc.getColorResId(key, profile);
        return ContextCompat.getColor(context, resId);
    }

    public static int getColorResId(ColorProfileKey key, ColorProfile profile) {

        switch (key) {
            case MAIN_COLOR_VERY_LIGHT:
                return R.color.common_grey50;
            case MAIN_COLOR_LIGHT:
                return R.color.common_grey100;
            case MAIN_COLOR_MEDIUM:
                return R.color.common_grey300;
            case MAIN_COLOR_DARK:
                return R.color.common_grey600;
            case POP_UP_COLOR:
                return R.color.common_white;
            case ACCENT1_COLOR:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                        return R.color.common_amberA400;
                    case MAIN:
                        return R.color.common_deep_orange_A100;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_amberA700;
                    case CONTRAST:
                        return R.color.common_grey900;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case ACCENT2_COLOR:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                    case MAIN:
                        return R.color.common_blueA700;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_indigoA700;
                    case CONTRAST:
                        return R.color.common_white;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case ACCENT3_COLOR:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                        return R.color.common_amber200;
                    case MAIN:
                        return R.color.common_deep_orange_200;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_amber300;
                    case CONTRAST:
                        return R.color.common_white;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case DENIAL_COLOR:
                switch (profile) {
                    case MAIN:
                    case CONTRAST:
                        return R.color.common_red500;
                    case COLOR_IMPAIREMENT:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_yellow700;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case PRIMARY_TEXT_OPACITY:
                switch (profile) {
                    case MAIN:
                    case COLOR_IMPAIREMENT:
                        return R.color.common_black87;
                    case CONTRAST:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_black;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case SECONDARY_TEXT_OPACITY:
                switch (profile) {
                    case MAIN:
                    case COLOR_IMPAIREMENT:
                        return R.color.common_black54;
                    case CONTRAST:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_black70;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case DISABLED_TEXT_OPACITY:
                return R.color.common_black38;
            case DIVIDERS_OPACITY:
                return R.color.common_black12;
            case ACTIVE_ICONS_OPACITY:
                switch (profile) {
                    case MAIN:
                    case COLOR_IMPAIREMENT:
                        return R.color.common_black54;
                    case CONTRAST:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_black87;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case INACTIVE_ICONS_OPACITY:
                switch (profile) {
                    case MAIN:
                    case COLOR_IMPAIREMENT:
                        return R.color.common_black38;
                    case CONTRAST:
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_black50;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            default:
                throw new RuntimeException(PROFILE_KEY_NOT_SET);
        }
    }
}
