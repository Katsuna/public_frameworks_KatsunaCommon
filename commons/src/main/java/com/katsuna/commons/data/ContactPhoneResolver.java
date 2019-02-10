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
