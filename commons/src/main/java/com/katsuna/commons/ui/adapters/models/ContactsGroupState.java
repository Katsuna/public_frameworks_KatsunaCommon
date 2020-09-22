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
package com.katsuna.commons.ui.adapters.models;


public class ContactsGroupState {

    private boolean premium;
    private boolean focused;
    private boolean focusModeOn;
    private boolean highlighted;

    private int contactGroupPosition;
    private int selectedGroupPosition;
    private String startLetter;
    private long contactId;


    public ContactsGroupState(boolean premium, boolean focused, boolean focusModeOn,
                              boolean highlighted, int contactGroupPosition) {
        this.premium = premium;
        this.focused = focused;
        this.focusModeOn = focusModeOn;
        this.highlighted = highlighted;
        this.contactGroupPosition = contactGroupPosition;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean isFocusModeOn() {
        return focusModeOn;
    }

    public void setFocusModeOn(boolean unfocused) {
        this.focusModeOn = unfocused;
    }

    public String getStartLetter() {
        return startLetter;
    }

    public void setStartLetter(String startLetter) {
        this.startLetter = startLetter;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public int getContactGroupPosition() {
        return contactGroupPosition;
    }

    public void setContactGroupPosition(int contactGroupPosition) {
        this.contactGroupPosition = contactGroupPosition;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public int getSelectedGroupPosition() {
        return selectedGroupPosition;
    }

    public void setSelectedGroupPosition(int selectedGroupPosition) {
        this.selectedGroupPosition = selectedGroupPosition;
    }
}
