package com.katsuna.commons.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katsuna.commons.R;
import com.katsuna.commons.domain.Contact;
import com.katsuna.commons.ui.adapters.holders.ContactViewHolder;
import com.katsuna.commons.ui.adapters.models.ContactsGroupState;
import com.katsuna.commons.ui.listeners.IContactListener;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Contact> mContacts;
    private final IContactListener mContactListener;
    private final ContactsGroupState mContactsGroupState;

    public ContactsAdapter(List<Contact> models, IContactListener contactListener,
                           ContactsGroupState contactsGroupState) {
        mContacts = models;
        mContactListener = contactListener;
        mContactsGroupState = contactsGroupState;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.common_contact_v2, parent, false);
        return new ContactViewHolder(view, mContactListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);
        ContactViewHolder contactViewHolder = (ContactViewHolder) holder;
        contactViewHolder.bind(contact, position, mContactsGroupState);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
