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

public enum SizeProfileKeyV2 {
    TITLE,
    SUBHEADING_1,
    SUBHEADING_2,
    BODY_1,
    BUTTON,
    FLOATING_BUTTON,
    ICON_1;

    public static SizeProfileKeyV2 fromInteger(int x) {
        switch(x) {
            case 0:
                return TITLE;
            case 1:
                return SUBHEADING_1;
            case 2:
                return SUBHEADING_2;
            case 3:
                return BODY_1;
            case 4:
                return BUTTON;
            case 5:
                return ICON_1;
            case 6:
                return FLOATING_BUTTON;
        }
        return null;
    }
}
