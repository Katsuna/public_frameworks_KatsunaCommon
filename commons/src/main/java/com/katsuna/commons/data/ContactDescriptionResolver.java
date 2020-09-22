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
import com.katsuna.commons.domain.Description;
import com.katsuna.commons.providers.ContactProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactDescriptionResolver {


    public static String getDescription(Context context, Contact contact) {
        String output = ContactsInfoCache.DescriptionsMap.get(contact.getId());
        if (output == null) {
            // get Description from db
            ContactProvider provider = new ContactProvider(context);

            // Read all description and use the first one.
            List<Description> descriptions = provider.getDescriptions(contact.getId());
            if (descriptions.size() > 0) {
                output = descriptions.get(0).getDescription();
            } else {
                // this will mark that we have no description set
                output = "";
            }
            ContactsInfoCache.DescriptionsMap.put(contact.getId(), output);
        }

        return output;
    }

    public static void getDescriptions(Context context, List<Contact> contacts) {
        ContactProvider provider = new ContactProvider(context);
        HashMap<Long, Description> map = provider.getContactsDescriptions(contacts);

        for (Map.Entry<Long, Description> entry : map.entrySet()) {
            Long key = entry.getKey();
            Description value = entry.getValue();
            if (value == null) {
                ContactsInfoCache.DescriptionsMap.remove(key);
            } else {
                ContactsInfoCache.DescriptionsMap.put(key, value.getDescription());
            }
        }
    }

}
