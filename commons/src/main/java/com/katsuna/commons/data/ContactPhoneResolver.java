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
package com.katsuna.commons.data;

import android.content.Context;

import com.katsuna.commons.domain.Contact;
import com.katsuna.commons.domain.Phone;
import com.katsuna.commons.providers.ContactProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactPhoneResolver {


    public static String getPrimaryPhone(Context context, Contact contact) {
        String output = ContactsInfoCache.PrimaryPhoneMap.get(contact.getId());
        if (output == null) {
            // get Description from db
            ContactProvider cp = new ContactProvider(context);

            List<Phone> phoneList = cp.getPhones(contact.getId());
            if (phoneList.size() > 0) {
                Phone primaryPhone = phoneList.get(0);
                output = primaryPhone.getNumber();
            }

            ContactsInfoCache.PrimaryPhoneMap.put(contact.getId(), output);
        }

        return output;
    }

    public static void getPrimaryPhones(Context context, List<Contact> contacts) {
        ContactProvider provider = new ContactProvider(context);
        HashMap<Long, String> map = provider.getContactsPrimaryPhones(contacts);

        for (Map.Entry<Long, String> entry : map.entrySet()) {
            Long key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                ContactsInfoCache.PrimaryPhoneMap.remove(key);
            } else {
                ContactsInfoCache.PrimaryPhoneMap.put(key, value);
            }
        }
    }

}
