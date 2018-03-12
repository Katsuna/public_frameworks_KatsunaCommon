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
