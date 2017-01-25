package com.katsuna.commons.entities;

public class SpinnerItem {
    private String value;
    private String descriptionResId;

    public SpinnerItem(String value) {
        this.value = value;
    }

    public SpinnerItem(String value, String descriptionResId) {
        this.value = value;
        this.descriptionResId = descriptionResId;
    }

    public String getValue() {
        return value;
    }

    public String getDescriptionResId() {
        return descriptionResId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SpinnerItem) {
            SpinnerItem c = (SpinnerItem) obj;
            return c.getValue().equals(value);
        }

        return false;
    }
}
