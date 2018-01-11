package com.katsuna.commons.ui.adapters.models;

import com.katsuna.commons.domain.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsGroup {

    public List<Contact> contactList;
    public boolean premium;
    public String firstLetter;

    public ContactsGroup() {
        contactList = new ArrayList<>();
    }

}
