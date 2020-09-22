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
import com.katsuna.commons.entities.ColorProfileKeyV2;

/**
 * Calculates color based on ColorProfile and ColorProfileKeyV2.
 * Introduced to handle redesign v2
 */
public class ColorCalcV2 {

    private final static String PROFILE_NOT_SET = "profile not set";
    private final static String PROFILE_KEY_NOT_SET = "profile key not set";

    public static int getColor(Context context, ColorProfileKeyV2 key, ColorProfile profile) {
        int resId = ColorCalcV2.getColorResId(key, profile);
        return ContextCompat.getColor(context, resId);
    }

    public static int getColorResId(ColorProfileKeyV2 key, ColorProfile profile) {

        switch (key) {
            case PRIMARY_COLOR_1:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                        return R.color.common_amberA400;
                    case MAIN:
                        return R.color.common_deep_orange_A200;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_amberA700;
                    case CONTRAST:
                        return R.color.common_grey900;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case PRIMARY_COLOR_2:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                    case MAIN:
                        return R.color.common_blueA700;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_indigoA700;
                    case CONTRAST:
                        return R.color.common_dim_gray;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case SECONDARY_COLOR_1:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                        return R.color.common_milk_punch;
                    case MAIN:
                        return R.color.common_pippin;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_papaya_whip;
                    case CONTRAST:
                        return R.color.common_grey300;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case SECONDARY_COLOR_2:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                    case MAIN:
                        return R.color.common_solitude;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_ghost_white;
                    case CONTRAST:
                        return R.color.common_white_smoke;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case SECONDARY_COLOR_3:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                    case MAIN:
                        return R.color.common_solitude_2;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_lavender;
                    case CONTRAST:
                        return R.color.common_whisper;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case PRIMARY_GREY_1:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                    case MAIN:
                        return R.color.common_grey300;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_grey500;
                    case CONTRAST:
                        return R.color.common_grey500;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            case SECONDARY_GREY_2:
                switch (profile) {
                    case COLOR_IMPAIREMENT:
                    case MAIN:
                        return R.color.common_grey50;
                    case COLOR_IMPAIRMENT_AND_CONTRAST:
                        return R.color.common_white_smoke;
                    case CONTRAST:
                        return R.color.common_white_smoke;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
            default:
                throw new RuntimeException(PROFILE_KEY_NOT_SET);
        }
    }
}
