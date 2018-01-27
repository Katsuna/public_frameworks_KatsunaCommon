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
