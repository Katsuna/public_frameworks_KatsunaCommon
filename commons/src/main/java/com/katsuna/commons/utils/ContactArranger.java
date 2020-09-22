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
package com.katsuna.commons.utils;

import com.katsuna.commons.domain.Contact;
import com.katsuna.commons.ui.adapters.models.ContactListItemModel;
import com.katsuna.commons.ui.adapters.models.ContactsGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContactArranger {

    public static List<ContactListItemModel> sortContactsBySurname(List<Contact> contacts) {
        Collections.sort(contacts);

        List<ContactListItemModel> output = new ArrayList<>();

        String s = "-";

        for (Contact c : contacts) {
            String firstLetterNormalized = c.getFirstLetterNormalized();

            //check if contact is separator
            Separator separator = Separator.NONE;
            if (firstLetterNormalized != null) {
                if (!firstLetterNormalized.startsWith(s)) {
                    s = firstLetterNormalized;
                    separator = Separator.FIRST_LETTER;
                }
            }

            ContactListItemModel model = new ContactListItemModel();
            model.setContact(c);
            model.setSeparator(separator);

            output.add(model);
        }

        return output;
    }

    public static List<ContactsGroup> getSortedContactsGroups(List<Contact> contacts) {
        Collections.sort(contacts);

        LinkedHashMap<String, ContactsGroup> map = new LinkedHashMap<>();

        for (Contact c : contacts) {
            String firstLetterNormalized = c.getFirstLetterNormalized();

            if (map.containsKey(firstLetterNormalized)) {
                map.get(firstLetterNormalized).contactList.add(c);
            } else {
                ContactsGroup contactsGroup = new ContactsGroup();
                contactsGroup.firstLetter = firstLetterNormalized;
                contactsGroup.contactList.add(c);
                map.put(firstLetterNormalized, contactsGroup);
            }
        }

        List<ContactsGroup> output = new ArrayList<>(map.values());

        return output;
    }

    public static List<ContactListItemModel> getContactsProcessed(List<Contact> contacts) {
        List<ContactListItemModel> topContacts = getTopContacts(contacts);

        //sort contacts
        List<ContactListItemModel> contactsSorted = sortContactsBySurname(contacts);

        List<ContactListItemModel> output = new ArrayList<>();
        output.addAll(topContacts);
        output.addAll(contactsSorted);

        return output;
    }

    public static List<ContactsGroup> getContactsGroups(List<Contact> contacts) {
        ContactsGroup topContactsGroup = getTopContactsGroup(contacts);

        //sort contacts
        List<ContactsGroup> contactsSorted = getSortedContactsGroups(contacts);

        List<ContactsGroup> output = new ArrayList<>();
        output.add(topContactsGroup);
        output.addAll(contactsSorted);

        return output;
    }

    private static List<ContactListItemModel> getTopContacts(List<Contact> contacts) {
        Map<Long, Contact> map = getTopContactsMap(contacts);

        //get topContacts
        List<ContactListItemModel> topContacts = new ArrayList<>();
        boolean firstItemSet = false;
        for (Map.Entry<Long, Contact> entry : map.entrySet()) {
            ContactListItemModel model = new ContactListItemModel();
            model.setContact(entry.getValue());
            model.setPremium(true);
            if (!firstItemSet) {
                model.setSeparator(Separator.STARRED);
                firstItemSet = true;
            }
            topContacts.add(model);
        }

        return topContacts;
    }

    private static ContactsGroup getTopContactsGroup(List<Contact> contacts) {
        // populate contacts
        Map<Long, Contact> map = getTopContactsMap(contacts);

        // populate output
        ContactsGroup contactsGroup = new ContactsGroup();
        contactsGroup.premium = true;
        contactsGroup.contactList = new ArrayList<>(map.values());

        return contactsGroup;
    }

    private static Map<Long, Contact> getTopContactsMap(List<Contact> contacts) {
        //select top contacts
        Contact[] frequentContacted = getFrequentContacted(contacts);
        Contact[] latestContacted = getLatestContacted(contacts);
        List<Contact> starredContacts = getStarredContacts(contacts);

        //add 3 most frequent
        LinkedHashMap<Long, Contact> map = new LinkedHashMap<>();
        int i = 0;
        for (Contact c : frequentContacted) {
            map.put(c.getId(), c);
            i++;
            if (i == 3) {
                break;
            }
        }

        //add 2 latest
        i = 0;
        for (Contact c : latestContacted) {
            if (!map.containsKey(c.getId())) {
                map.put(c.getId(), c);
                i++;
                if (i == 2) {
                    break;
                }
            }
        }

        //add starred
        for (Contact c : starredContacts) {
            if (!map.containsKey(c.getId())) {
                map.put(c.getId(), c);
            }
        }

        return map;
    }

    private static Contact[] getLatestContacted(List<Contact> contacts) {
        Contact[] output = getDeepCopy(contacts);
        bubbleSort4LatestContacted(output);
        return output;
    }

    private static Contact[] getFrequentContacted(List<Contact> contacts) {
        Contact[] output = getDeepCopy(contacts);
        bubbleSort4FrequentContacted(output);
        return output;
    }

    private static List<Contact> getStarredContacts(List<Contact> contacts) {
        List<Contact> output = new ArrayList<>();

        for (Contact c : contacts) {
            if (c.isStarred()) {
                output.add(new Contact(c));
            }
        }

        return output;
    }

    private static Contact[] getDeepCopy(List<Contact> contacts) {
        Contact[] output = new Contact[contacts.size()];
        for (int i = 0; i < contacts.size(); i++) {
            output[i] = new Contact(contacts.get(i));
        }
        return output;
    }

    private static void bubbleSort4LatestContacted(Contact[] contacts) {

        /*
         * In bubble sort, we basically traverse the array from first
         * to array_length - 1 position and compare the element with the next one.
         * Element is swapped with the next element if the next element is smaller.
         *
         * Bubble sort steps are as follows.
         *
         * 1. Compare array[0] & array[1]
         * 2. If array[0] < array [1] swap it.
         * 3. Compare array[1] & array[2]
         * 4. If array[1] < array[2] swap it.
         * ...
         * 5. Compare array[n-1] & array[n]
         * 6. if [n-1] < array[n] then swap it.
         *
         * After this step we will have smallest element at the last index.
         *
         * Repeat the same steps for array[1] to array[n-1]
         *
         */

        int n = contacts.length;
        Contact temp;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (contacts[j - 1].getLastTimeContacted() < contacts[j].getLastTimeContacted()) {
                    //swap the elements!
                    temp = contacts[j - 1];
                    contacts[j - 1] = contacts[j];
                    contacts[j] = temp;
                }

            }
        }
    }

    private static void bubbleSort4FrequentContacted(Contact[] contacts) {

        int n = contacts.length;
        Contact temp;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (contacts[j - 1].getTimesContacted() < contacts[j].getTimesContacted()) {
                    //swap the elements!
                    temp = contacts[j - 1];
                    contacts[j - 1] = contacts[j];
                    contacts[j] = temp;
                }

            }
        }
    }

}
