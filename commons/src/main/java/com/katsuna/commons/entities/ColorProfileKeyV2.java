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

public enum ColorProfileKeyV2 {
    PRIMARY_COLOR_1(1),
    PRIMARY_COLOR_2(2),
    SECONDARY_COLOR_1(3),
    SECONDARY_COLOR_2(4),
    SECONDARY_COLOR_3(5),
    PRIMARY_GREY_1(6),
    SECONDARY_GREY_2(7);

    private final int id;

    ColorProfileKeyV2(int v) {
        id = v;
    }

    public int getId() {
        return id;
    }

    public static ColorProfileKeyV2 fromId(int id) {
        for (ColorProfileKeyV2 type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}
