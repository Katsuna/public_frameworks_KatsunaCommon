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