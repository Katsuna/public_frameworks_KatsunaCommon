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
package com.katsuna.commons.ui.adapters.holders;

import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.UserProfileContainer;
import com.katsuna.commons.ui.adapters.ContactsAdapter;
import com.katsuna.commons.ui.adapters.models.ContactsGroup;
import com.katsuna.commons.ui.adapters.models.ContactsGroupState;
import com.katsuna.commons.ui.listeners.IContactListener;
import com.katsuna.commons.ui.listeners.IContactsGroupListener;
import com.katsuna.commons.utils.ColorCalcV2;

public class ContactsGroupViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = ContactsGroupViewHolder.class.getSimpleName();

    private final IContactsGroupListener mContactsGroupListener;
    private final IContactListener mContactListener;
    private final UserProfileContainer mUserProfileContainer;
    private final CardView mContactsGroupContainerCard;
    private final View mContactsGroupContainerCardInner;
    private final View mCardHandle;

    private final TextView mStartLetter;
    private final ImageView mStarIcon;
    private final TextView mStarDesc;
    private final RecyclerView mContactsList;
    private final View mOpacityLayer;

    public ContactsGroupViewHolder(View view,
                                   IContactsGroupListener contactsGroupListener,
                                   IContactListener contactListener) {
        super(view);
        mContactsGroupListener = contactsGroupListener;
        mContactListener = contactListener;
        mUserProfileContainer = contactsGroupListener.getUserProfileContainer();

        mStartLetter = (TextView) view.findViewById(R.id.start_letter);
        mStarIcon = (ImageView) view.findViewById(R.id.star_icon);
        mStarDesc = (TextView) view.findViewById(R.id.star_desc);
        mContactsList = (RecyclerView) view.findViewById(R.id.contacts_list);
        mContactsList.setItemViewCacheSize(100);
        mContactsList.setItemAnimator(null);

        LinearLayoutManager mgr = (LinearLayoutManager) mContactsList.getLayoutManager();
        mgr.setItemPrefetchEnabled(true);
        mgr.setInitialPrefetchItemCount(1000);

        mContactsGroupContainerCard = (CardView) view.findViewById(R.id.contacts_group_container_card);
        mOpacityLayer = view.findViewById(R.id.opacity_layer);
        mContactsGroupContainerCardInner = view.findViewById(R.id.contacts_group_container_card_inner);
        mCardHandle = view.findViewById(R.id.card_handle);
    }

    public void bind(final ContactsGroup model, final int position,
                     final ContactsGroupState contactsGroupState) {
        if (model.premium) {
            mStarIcon.setVisibility(View.VISIBLE);
            mStarDesc.setVisibility(View.VISIBLE);
            mStartLetter.setVisibility(View.GONE);
            //Log.e(TAG, "lag group model premium");
        } else {
            mStarIcon.setVisibility(View.GONE);
            mStarDesc.setVisibility(View.GONE);
            mStartLetter.setVisibility(View.VISIBLE);
            mStartLetter.setText(model.firstLetter);
            //Log.e(TAG, "lag group model: " + model.firstLetter);
        }

        ContactsAdapter adapter = new ContactsAdapter(model.contactList, mContactListener,
                contactsGroupState);
        mContactsList.setAdapter(adapter);

        adjustState(contactsGroupState);

        // direct focus on non selected contact if photo or name is clicked
        View.OnClickListener focusContact = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContactsGroupListener.selectContactsGroup(position);
            }
        };
        mContactsGroupContainerCard.setOnClickListener(focusContact);

    }

    private void adjustState(ContactsGroupState state) {
        // calc colors
        int cardColor;
        int cardColorAlpha;

        ColorProfile colorProfile = mUserProfileContainer.getActiveUserProfile().colorProfile;
        int primaryColor1 = ColorCalcV2.getColor(itemView.getContext(),
                ColorProfileKeyV2.PRIMARY_COLOR_1, colorProfile);
        int primaryColor2 = ColorCalcV2.getColor(itemView.getContext(),
                ColorProfileKeyV2.PRIMARY_COLOR_2, colorProfile);
        int secondaryColor1 = ColorCalcV2.getColor(itemView.getContext(),
                ColorProfileKeyV2.SECONDARY_COLOR_1, colorProfile);
        int secondaryColor2 = ColorCalcV2.getColor(itemView.getContext(),
                ColorProfileKeyV2.SECONDARY_COLOR_2, colorProfile);
        int greyColor2 = ColorCalcV2.getColor(itemView.getContext(),
                ColorProfileKeyV2.SECONDARY_GREY_2, colorProfile);

        int transparent = ContextCompat.getColor(itemView.getContext(), R.color.common_transparent);

        if (state.isPremium()) {
            cardColor = primaryColor2;
            cardColorAlpha = secondaryColor2;
        } else if (state.isFocused()) {
            cardColor = primaryColor1;
            cardColorAlpha = secondaryColor1;
        } else if (state.isHighlighted()) {
            cardColor = primaryColor2;
            cardColorAlpha = greyColor2;
        } else {
            cardColor = transparent;
            cardColorAlpha = greyColor2;
        }

        if (state.getContactId() > 0) {
            cardColorAlpha = ContextCompat.getColor(itemView.getContext(), R.color.common_unfocused);
        }

        if (state.isFocusModeOn()) {
            if (state.isFocused()) {
                mOpacityLayer.setVisibility(View.INVISIBLE);
            } else {
                mOpacityLayer.setVisibility(View.VISIBLE);
            }
        } else {
            mOpacityLayer.setVisibility(View.INVISIBLE);
        }

        // set colors
        mContactsGroupContainerCard.setCardBackgroundColor(ColorStateList.valueOf(cardColor));
        mContactsGroupContainerCardInner.setBackgroundColor(cardColorAlpha);
        mCardHandle.setBackgroundColor(cardColor);

        mStarDesc.setTextColor(primaryColor2);
        mStartLetter.setTextColor(primaryColor2);
        mStarIcon.setColorFilter(primaryColor2);
    }
}
