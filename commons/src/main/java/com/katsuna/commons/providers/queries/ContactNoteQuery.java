package com.katsuna.commons.providers.queries;

import android.provider.ContactsContract;

public class ContactNoteQuery {
    public static final String[] _PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Note._ID,
            ContactsContract.CommonDataKinds.Note.NOTE,
            ContactsContract.Data.CONTACT_ID
    };

    public static final int _ID = 0;
    public static final int NOTE = 1;
    public static final int CONTACT_ID = 2;
}
