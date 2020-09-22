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

import com.katsuna.commons.R;
import com.katsuna.commons.entities.OpticalParams;
import com.katsuna.commons.entities.SizeProfile;
import com.katsuna.commons.entities.SizeProfileKeyV2;

/**
 * Calculates optical params based on SizeProfile and SizeProfileKey.
 * Introduced to handle redesign v2
 */

public class SizeCalcV2 {

    private final static String PROFILE_NOT_SET = "profile not set";
    private final static String PROFILE_KEY_NOT_SET = "profile key not set";

    public static OpticalParams getOpticalParams(SizeProfileKeyV2 key, SizeProfile profile) {

        OpticalParams params = new OpticalParams();

        switch (key) {
            case TITLE:
                switch (profile) {
                    case SIMPLE:
                        params.setTextSize(R.dimen.common_26sp);
                        params.setTextFontFamily(Constants.SANS_SERIF_MEDIUM);
                        break;
                    case INTERMEDIATE:
                    case AUTO:
                        params.setTextSize(R.dimen.common_23sp);
                        params.setTextFontFamily(Constants.SANS_SERIF_MEDIUM);
                        break;
                    case ADVANCED:
                        params.setTextSize(R.dimen.common_20sp);
                        params.setTextFontFamily(Constants.SANS_SERIF_MEDIUM);
                        break;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
                break;
            case SUBHEADING_1:
                switch (profile) {
                    case SIMPLE:
                        params.setTextSize(R.dimen.common_24sp);
                        params.setTextFontFamily(Constants.SANS_SERIF);
                        break;
                    case INTERMEDIATE:
                    case AUTO:
                        params.setTextSize(R.dimen.common_21sp);
                        params.setTextFontFamily(Constants.SANS_SERIF);
                        break;
                    case ADVANCED:
                        params.setTextSize(R.dimen.common_16sp);
                        params.setTextFontFamily(Constants.SANS_SERIF);
                        break;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
                break;
            case SUBHEADING_2:
                switch (profile) {
                    case SIMPLE:
                        params.setTextSize(R.dimen.common_24sp);
                        params.setTextFontFamily(Constants.SANS_SERIF_MEDIUM);
                        break;
                    case INTERMEDIATE:
                    case AUTO:
                        params.setTextSize(R.dimen.common_21sp);
                        params.setTextFontFamily(Constants.SANS_SERIF_MEDIUM);
                        break;
                    case ADVANCED:
                        params.setTextSize(R.dimen.common_16sp);
                        params.setTextFontFamily(Constants.SANS_SERIF_MEDIUM);
                        break;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
                break;
            case BODY_1:
                switch (profile) {
                    case SIMPLE:
                        params.setTextSize(R.dimen.common_21sp);
                        params.setTextFontFamily(Constants.SANS_SERIF);
                        break;
                    case INTERMEDIATE:
                    case AUTO:
                        params.setTextSize(R.dimen.common_19sp);
                        params.setTextFontFamily(Constants.SANS_SERIF);
                        break;
                    case ADVANCED:
                        params.setTextSize(R.dimen.common_14sp);
                        params.setTextFontFamily(Constants.SANS_SERIF);
                        break;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
                break;
            case BUTTON:
                switch (profile) {
                    case SIMPLE:
                        params.setTextSize(R.dimen.common_21sp);
                        params.setTextFontFamily(Constants.SANS_SERIF_MEDIUM);
                        params.setHeight(R.dimen.common_42dp);
                        break;
                    case INTERMEDIATE:
                    case AUTO:
                        params.setTextSize(R.dimen.common_16sp);
                        params.setTextFontFamily(Constants.SANS_SERIF_MEDIUM);
                        params.setHeight(R.dimen.common_36dp);
                        break;
                    case ADVANCED:
                        params.setTextSize(R.dimen.common_action_button_text_size_advanced);
                        params.setTextFontFamily(Constants.SANS_SERIF_MEDIUM);
                        params.setHeight(R.dimen.common_36dp);
                        break;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
                break;
            case ICON_1:
                switch (profile) {
                    case SIMPLE:
                        params.setHeight(R.dimen.common_32dp);
                        break;
                    case INTERMEDIATE:
                    case ADVANCED:
                    case AUTO:
                        params.setHeight(R.dimen.common_28dp);
                        break;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
                break;
            case FLOATING_BUTTON:
                switch (profile) {
                    case SIMPLE:
                        params.setHeight(R.dimen.common_63dp);
                        break;
                    case INTERMEDIATE:
                    case AUTO:
                        params.setHeight(R.dimen.common_60dp);
                        break;
                    case ADVANCED:
                        params.setHeight(R.dimen.common_56dp);
                        break;
                    default:
                        throw new RuntimeException(PROFILE_NOT_SET);
                }
                break;
            default:
                throw new RuntimeException(PROFILE_KEY_NOT_SET);
        }

        return params;
    }
}