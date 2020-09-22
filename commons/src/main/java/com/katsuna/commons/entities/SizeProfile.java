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