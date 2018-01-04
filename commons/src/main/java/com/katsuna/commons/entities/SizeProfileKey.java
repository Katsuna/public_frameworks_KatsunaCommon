package com.katsuna.commons.entities;

public enum SizeProfileKey {
    TITLE,
    SUBHEADER,
    BODY_1,
    BODY_2,
    ACTION_BUTTON,
    FLOATING_BUTTON,
    ICON,
    ITEM_TYPE_ICON,
    LEVEL_1_TEXT,
    LEVEL_2_TEXT,
    LEVEL_3_TEXT,
    ACTION_BUTTON_V2,
    MORE_TEXT,
    LEVEL_2_ACTION_ICON,
    LEVEL_2_ACTION_TEXT;

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
            case 7:
                return ITEM_TYPE_ICON;
            case 8:
                return LEVEL_1_TEXT;
            case 9:
                return LEVEL_2_TEXT;
            case 10:
                return LEVEL_3_TEXT;
            case 11:
                return ACTION_BUTTON_V2;
            case 12:
                return MORE_TEXT;
            case 13:
                return LEVEL_2_ACTION_ICON;
            case 14:
                return LEVEL_2_ACTION_TEXT;
        }
        return null;
    }
}
