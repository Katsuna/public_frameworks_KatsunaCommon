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
package com.katsuna.commons.domain;

import android.content.ContentUris;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.katsuna.commons.utils.LetterNormalizer;

import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.util.List;

public class Contact implements Comparable<Contact>, Serializable {

    private static final long serialVersionUID = 4439301448809751895L;

    private long id;
    private String displayName;
    private Name name;
    private List<Phone> phones;
    private Email email;
    private Address address;
    private Bitmap photo;
    private int timesContacted;
    private long lastTimeContacted;
    private boolean starred;
    private Uri photoUri;
    private String messageAddress;
    private String firstLetterNormalized;

    public Contact() {
    }

    public void initialize() {
        name = new Name();
    }

    public Contact(Contact contact) {
        id = contact.getId();
        setDisplayName(contact.getDisplayName());
        timesContacted = contact.getTimesContacted();
        lastTimeContacted = contact.getLastTimeContacted();
        starred = contact.isStarred();
        photoUri = contact.getPhotoUri();
        description = new Description(contact.getDescription());
    }

    @Override
    public String toString() {
        return "Contact: " + id + " " + displayName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        String output = "";
        if (displayName != null) {
            output = displayName.replace(",", "");
        }
        return output;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        this.firstLetterNormalized = LetterNormalizer.normalize(displayName);
    }

    @Override
    public int compareTo(@NonNull Contact another) {
        int result = ObjectUtils.compare(firstLetterNormalized, another.getFirstLetterNormalized());
        if (result == 0) {
            result = displayName.compareTo(another.displayName);
        }
        return result;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public int getTimesContacted() {
        return timesContacted;
    }

    public void setTimesContacted(int timesContacted) {
        this.timesContacted = timesContacted;
    }

    public long getLastTimeContacted() {
        return lastTimeContacted;
    }

    public void setLastTimeContacted(long lastTimeContacted) {
        this.lastTimeContacted = lastTimeContacted;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Phone getPhone(int index) {
        Phone output = null;
        if (phones != null && phones.size() > index) {
            output = phones.get(index);
        }
        return output;
    }

    public Uri getPhotoUri() {
        if (photoUri == null) {
            photoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        }
        return photoUri;
    }

    public String getMessageAddress() {
        return messageAddress;
    }

    public void setMessageAddress(String messageAddress) {
        this.messageAddress = messageAddress;
    }

    public String getFirstLetterNormalized() {
        return firstLetterNormalized;
    }

    private Description description;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public boolean isEmpty() {
        // we assume that a contact is empty if there is no name assigned to it
        return name == null;

    }
}
