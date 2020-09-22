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
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextClock;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.SizeProfileKeyV2;

public class KatsunaTextClock extends TextClock implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;
    private final SizeProfileKeyV2 mSizeProfileKeyV2;

    public KatsunaTextClock(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaTextView);
        mSizeProfileEnabled = array.getBoolean(R.styleable.KatsunaTextView_sizeProfileEnabled, true);
        int sizeProfileKeyV2Index = array.getInt(R.styleable.KatsunaTextView_sizeProfileKeyV2, -1);
        mSizeProfileKeyV2 = SizeProfileKeyV2.fromInteger(sizeProfileKeyV2Index);

        array.recycle();
    }

    @Override
    public boolean isSizeProfileEnabled() {
        return mSizeProfileEnabled;
    }

    public SizeProfileKeyV2 getSizeProfileKeyV2() {
        return mSizeProfileKeyV2;
    }
}
