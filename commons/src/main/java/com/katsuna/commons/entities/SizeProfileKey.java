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
