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
package com.katsuna.commons.controls;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.utils.ColorAdjuster;

public class DemoProfile extends LinearLayout {

    private boolean mSelected;
    private View mCallButtonContainer;
    private View mMessageButtonContainer;
    private Button mCallButton;
    private Button mMessageButton;
    private TextView mDisplayName;

    public DemoProfile(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        mCallButtonContainer = findViewById(R.id.call_button_container);
        mCallButton = (Button) findViewById(R.id.call_button);
        mMessageButtonContainer = findViewById(R.id.message_button_container);
        mMessageButton = (Button) findViewById(R.id.message_button);
        mDisplayName = (TextView) findViewById(R.id.displayName);
    }

    public void select(boolean enabled) {
        mSelected = enabled;
        if (enabled) {
            this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.common_border_blue));
        } else {
            this.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.common_border_grey));
        }
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void adjustColorProfile(ColorProfile colorProfile) {
        ColorAdjuster.adjustButtons(getContext(), colorProfile, mCallButtonContainer, mCallButton,
                mMessageButtonContainer, mMessageButton);
    }

}
