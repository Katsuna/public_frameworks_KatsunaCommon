package com.katsuna.commons.ui.adapters.holders;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.data.ContactDescriptionResolver;
import com.katsuna.commons.data.ContactPhoneResolver;
import com.katsuna.commons.domain.Contact;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.OpticalParams;
import com.katsuna.commons.entities.SizeProfile;
import com.katsuna.commons.entities.SizeProfileKeyV2;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.ui.adapters.models.ContactsGroupState;
import com.katsuna.commons.ui.listeners.IContactListener;
import com.katsuna.commons.utils.ColorAdjusterV2;
import com.katsuna.commons.utils.ColorCalcV2;
import com.katsuna.commons.utils.SizeAdjuster;
import com.katsuna.commons.utils.SizeCalcV2;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private final TextView mContactName;
    private final TextView mContactDesc;
    private final TextView mContactPhone;
    private final View mActionButtonsContainer;
    private final IContactListener mListener;
    private Button mCallButton;
    private Button mMessageButton;
    private TextView mMoreText;
    private final ViewGroup mMoreActionsContainer;
    private final ViewGroup mButtonsWrapper;
    private UserProfile mUserProfile;
    private TextView mEditContactText;
    private TextView mDeleteContactText;

    public ContactViewHolder(View itemView, IContactListener contactListener) {
        super(itemView);
        mContactName = itemView.findViewById(R.id.contact_name);
        mContactDesc = itemView.findViewById(R.id.contact_desc);
        mContactPhone = itemView.findViewById(R.id.contact_phone);
        mActionButtonsContainer = itemView.findViewById(R.id.action_buttons_container);
        mListener = contactListener;

        mButtonsWrapper = itemView.findViewById(R.id.action_buttons_wrapper);
        mMoreActionsContainer = itemView.findViewById(R.id.more_actions_container);
    }

    public void bind(final Contact contact, final int position,
                     final ContactsGroupState contactsGroupState) {
        Context ctx = itemView.getContext();
        mUserProfile = mListener.getUserProfileContainer().getActiveUserProfile();

        mContactName.setText(contact.getDisplayName());

        String contactDesc = ContactDescriptionResolver.getDescription(ctx, contact);
        if (TextUtils.isEmpty(contactDesc)) {
            mContactDesc.setVisibility(View.GONE);
        } else {
            mContactDesc.setText(contactDesc);
            mContactDesc.setVisibility(View.VISIBLE);
        }

        String contactPhone = ContactPhoneResolver.getPrimaryPhone(ctx, contact);
        mContactPhone.setText(contactPhone);

        if (contact.getId() == contactsGroupState.getContactId() &&
                contactsGroupState.isFocused()) {
            mButtonsWrapper.removeAllViews();
            addActionButtonControls(contact);
            mActionButtonsContainer.setVisibility(View.VISIBLE);
        } else {
            mActionButtonsContainer.setVisibility(View.GONE);
        }

        ColorProfile colorProfile = mUserProfile.colorProfile;
        int secondaryColor1 = ColorCalcV2.getColor(ctx, ColorProfileKeyV2.SECONDARY_COLOR_1,
                colorProfile);
        int secondaryColor2 = ColorCalcV2.getColor(ctx, ColorProfileKeyV2.SECONDARY_COLOR_2,
                colorProfile);
        int greyColor2 = ColorCalcV2.getColor(ctx, ColorProfileKeyV2.SECONDARY_GREY_2,
                colorProfile);

        int colorForTextFields;
        int colorForBackground;
        int elevation = ctx.getResources().getDimensionPixelSize(R.dimen.common_selection_elevation);
        itemView.setElevation(0);
        if (contactsGroupState.isFocused()) {
            if (contact.getId() == contactsGroupState.getContactId() ||
                    contactsGroupState.getContactId() == 0) {
                // we have a contact selected in this contacts group and this is our contact
                colorForTextFields = R.color.common_black87;
                if (contactsGroupState.isPremium()) {
                    colorForBackground = secondaryColor2;
                } else {
                    colorForBackground = secondaryColor1;
                }
                itemView.setElevation(elevation);
            } else {
                // we have a contact selected in this contacts group but not this contact
                colorForTextFields = R.color.common_black34;
                colorForBackground = ContextCompat.getColor(ctx, R.color.common_transparent);
            }
        } else {
            if (contactsGroupState.getContactId() > 0) {
                // we have a contact selected but not in this contacts group
                colorForTextFields = R.color.common_black34;
                colorForBackground = ContextCompat.getColor(ctx, R.color.common_transparent);
            } else {
                // we have no contact selected
                colorForTextFields = R.color.common_black87;
                if (contactsGroupState.isPremium()) {
                    colorForBackground = secondaryColor2;
                } else {
                    colorForBackground = greyColor2;
                }
            }
        }
        mContactName.setTextColor(ContextCompat.getColor(ctx, colorForTextFields));
        itemView.setBackgroundColor(colorForBackground);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.selectContact(contactsGroupState.getContactGroupPosition(),
                        contact.getFirstLetterNormalized(), contact, position);
            }
        });

        adjustSizeProfile();
        adjustColorProfile();
    }

    private void bindActionButtonsControls(final Contact contact) {
        mCallButton = itemView.findViewById(R.id.button_call);
        mMessageButton = itemView.findViewById(R.id.button_message);
        mMoreText = itemView.findViewById(R.id.txt_more);

        mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.callContact(contact);
            }
        });

        mMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSMS(contact);
            }
        });
        mMoreText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMoreActionsContainer.getVisibility() == View.VISIBLE) {
                    expandMoreActions(false, contact);
                } else {
                    expandMoreActions(true, contact);
                }
            }
        });
    }

    private void bindEditControls(final Contact contact) {
        mEditContactText = itemView.findViewById(R.id.edit_contact_text);
        mDeleteContactText = itemView.findViewById(R.id.delete_contact_text);

        mEditContactText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.editContact(contact.getId());
            }
        });

        mDeleteContactText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.deleteContact(contact);
            }
        });
    }

    private void adjustSizeProfile() {
        SizeProfile sizeProfile = mUserProfile.opticalSizeProfile;

        // display name
        OpticalParams opticalParams = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.TITLE, sizeProfile);
        SizeAdjuster.adjustText(itemView.getContext(), mContactName, opticalParams);

        // contact description
        opticalParams = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.SUBHEADING_1, sizeProfile);
        SizeAdjuster.adjustText(itemView.getContext(), mContactDesc, opticalParams);

        // contact phone
        SizeAdjuster.adjustText(itemView.getContext(), mContactPhone, opticalParams);

        // adjust buttons
        opticalParams = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.BUTTON, sizeProfile);
        if (mCallButton != null) {
            SizeAdjuster.adjustText(itemView.getContext(), mCallButton, opticalParams);
        }
        if (mMessageButton != null) {
            SizeAdjuster.adjustText(itemView.getContext(), mMessageButton, opticalParams);
        }

        // more text
        if (mMoreText != null) {
            SizeAdjuster.adjustText(itemView.getContext(), mMoreText, opticalParams);
        }

        // contact edit actions
        opticalParams = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.SUBHEADING_2, sizeProfile);
        if (mEditContactText != null) {
            SizeAdjuster.adjustText(itemView.getContext(), mEditContactText, opticalParams);
        }
        if (mDeleteContactText != null) {
            SizeAdjuster.adjustText(itemView.getContext(), mDeleteContactText, opticalParams);
        }
    }

    private void adjustColorProfile() {
        ColorAdjusterV2.adjustButtons(itemView.getContext(), mUserProfile,
                mCallButton, mMessageButton, mMoreText);
    }

    private void expandMoreActions(boolean flag, Contact contact) {
        if (flag) {
            mMoreActionsContainer.removeAllViews();
            addEditControls(contact);
            mMoreActionsContainer.setVisibility(View.VISIBLE);
            mMoreText.setText(R.string.common_less);
        } else {
            mMoreActionsContainer.setVisibility(View.GONE);
            mMoreText.setText(R.string.common_more);
        }
    }

    private void addActionButtonControls(Contact contact) {
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        View buttonsView = mUserProfile.isRightHanded ?
                inflater.inflate(R.layout.common_contact_buttons_rh, mButtonsWrapper, false) :
                inflater.inflate(R.layout.common_contact_buttons_lh, mButtonsWrapper, false);

        mButtonsWrapper.addView(buttonsView);

        // init controls and bind actions
        bindActionButtonsControls(contact);

        // adjust profiles
        adjustSizeProfile();
        adjustColorProfile();
    }

    private void addEditControls(Contact contact) {
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        View editControlsView = inflater.inflate(R.layout.common_edit_contact_actions,
                mMoreActionsContainer, false);

        mMoreActionsContainer.addView(editControlsView);

        // init controls and bind actions
        bindEditControls(contact);

        // adjust profiles
        adjustSizeProfile();
        adjustColorProfile();
    }

}
