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
package com.katsuna.commons.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.katsuna.commons.R;
import com.katsuna.commons.domain.Contact;
import com.katsuna.commons.entities.UserProfile;
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
        UserProfile userProfile = mContactListener.getUserProfileContainer().getActiveUserProfile();
        int layout = userProfile.isRightHanded ? R.layout.common_contact_v2 :
                R.layout.common_contact_v2_lh;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout, parent, false);
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
