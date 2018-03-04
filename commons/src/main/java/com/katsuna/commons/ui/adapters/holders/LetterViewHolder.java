package com.katsuna.commons.ui.adapters.holders;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.ui.adapters.interfaces.LetterListener;
import com.katsuna.commons.utils.ColorCalcV2;

public class LetterViewHolder extends RecyclerView.ViewHolder {

    private final TextView mLetter;
    private final LetterListener mLetterListener;

    public LetterViewHolder(View itemView, LetterListener letterListener) {
        super(itemView);
        mLetter = (TextView) itemView.findViewById(R.id.search_letter);
        mLetterListener = letterListener;
    }

    public void bind(final String letter, int position) {
        mLetter.setText(letter);
        mLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLetterListener.selectItemByStartingLetter(letter);
            }
        });

        applyColorProfile();

        UserProfile userProfile = mLetterListener.getUserProfile();
        int primaryColor1 = ColorCalcV2.getColor(itemView.getContext(),
                ColorProfileKeyV2.PRIMARY_COLOR_1, userProfile.colorProfile);

        int secondaryColor1 = ColorCalcV2.getColor(itemView.getContext(),
                ColorProfileKeyV2.SECONDARY_COLOR_1, userProfile.colorProfile);

        int white87 = ContextCompat.getColor(itemView.getContext(), R.color.common_white87);
        int black87 = ContextCompat.getColor(itemView.getContext(), R.color.common_black87);

        if (position % 2 == 0 ) {
            mLetter.setBackgroundColor(secondaryColor1);
            mLetter.setTextColor(black87);
        } else {
            mLetter.setBackgroundColor(primaryColor1);
            if (userProfile.colorProfile == ColorProfile.CONTRAST) {
                mLetter.setTextColor(white87);
            } else {
                mLetter.setTextColor(black87);
            }
        }
    }

    private void applyColorProfile() {
        if (mLetterListener != null) {
            UserProfile profile = mLetterListener.getUserProfile();
            if (profile.colorProfile == ColorProfile.CONTRAST) {
                int whiteResId = ContextCompat.getColor(itemView.getContext(),
                        R.color.common_white);
                mLetter.setTextColor(whiteResId);
            }
        }
    }

}
