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

public enum SizeProfileKey {
    TITLE,
    SUBHEADER,
    BODY_1,
    BODY_2,
    ACTION_BUTTON,
    FLOATING_BUTTON,
    ICON;

    public static SizeProfileKey fromInteger(int x) {
        switch(x) {
            case 0:
                return TITLE;
            case 1:
                return SUBHEADER;
            case 2:
                return BODY_1;
            case 3:
                return BODY_2;
            case 4:
                return ACTION_BUTTON;
            case 5:
                return FLOATING_BUTTON;
            case 6:
                return ICON;
        }
        return null;
    }
}
