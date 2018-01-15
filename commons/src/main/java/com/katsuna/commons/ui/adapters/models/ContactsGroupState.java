package com.katsuna.commons.ui.adapters.models;


public class ContactsGroupState {

    private boolean premium;
    private boolean focused;
    private boolean unfocused;

    private int contactGroupPosition;
    private String startLetter;
    private long contactId;


    public ContactsGroupState(boolean premium, boolean focused, boolean unfocused,
                              int contactGroupPosition) {
        this.premium = premium;
        this.focused = focused;
        this.unfocused = unfocused;
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

    public boolean isUnfocused() {
        return unfocused;
    }

    public void setUnfocused(boolean unfocused) {
        this.unfocused = unfocused;
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
}
