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
