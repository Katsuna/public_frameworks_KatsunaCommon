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

public class SpinnerItem {
    private String value;
    private int descriptionResId;

    public SpinnerItem(String value) {
        this.value = value;
    }

    public SpinnerItem(String value, int descriptionResId) {
        this.value = value;
        this.descriptionResId = descriptionResId;
    }

    public String getValue() {
        return value;
    }

    public int getDescriptionResId() {
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
