package com.katsuna.commons.data;

import android.content.Context;
import android.os.AsyncTask;

import com.katsuna.commons.domain.Contact;

import java.lang.ref.WeakReference;

public class FetchContactsInfoAsyncTask extends AsyncTask<Contact, Void, Void> {

    private final WeakReference<Context> mContext;

    public FetchContactsInfoAsyncTask(WeakReference<Context> context) {
        mContext = context;
    }

    @Override
    protected Void doInBackground(Contact... contacts) {

        ContactDescriptionResolver.getDescriptions(mContext.get(), contacts);

        return null;
    }
}
