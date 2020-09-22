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
package com.katsuna.commons.color;

//import android.util.Log;

public class ColorTransparentUtils {

    public static final String TAG = "ColorTransparentUtils";

    public static int convertIntoColorInt(int colorCode, int transLevel) {
        /*Log.d(TAG, "input colorCode: " + colorCode + " colorCodeHex: " +
            Integer.toHexString(colorCode));*/

        int transLevelHex = Math.round(255 * transLevel / 100);

/*        Log.d(TAG, "input transLevel: " + transLevel + " transLevelHex: " +
            Integer.toHexString(transLevelHex));*/

        int transLevelHex2 = transLevelHex << 24;

/*        Log.d(TAG, "input transLevel2: " + transLevelHex2 + " transLevelHex2: "
            + Integer.toHexString(transLevelHex2));*/

        int colorWithOpacity = (colorCode & 0x00FFFFFF) | transLevelHex2;

/*        Log.d(TAG, "input colorWithOpacity: " + colorWithOpacity + " colorCodeHex: "
            + Integer.toHexString(colorWithOpacity));*/

        return colorWithOpacity;
    }

}