package com.katsuna.commons.ui.adapters.holders;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.ui.adapters.interfaces.LetterListener;

public class LetterViewHolder extends RecyclerView.ViewHolder {

    private TextView mLetter;
    private LetterListener mLetterListener;

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

        if (position % 2 == 0 ) {
            mLetter.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),
                    R.color.priority_three_tone_one));
        } else {
            mLetter.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),
                    R.color.priority_three));
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
