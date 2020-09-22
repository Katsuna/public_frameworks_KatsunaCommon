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
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfileKeyV2;

public class KatsunaImageView extends AppCompatImageView implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;
    private ColorProfileKeyV2 mColorProfileKeyV2;

    public KatsunaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaImageView);
        mSizeProfileEnabled = array.getBoolean(R.styleable.KatsunaImageView_sizeProfileEnabled, true);
        int i = array.getInt(R.styleable.KatsunaImageView_colorProfileKey, -1);
        mColorProfileKeyV2 = ColorProfileKeyV2.fromId(i);

        array.recycle();
    }

    @Override
    public boolean isSizeProfileEnabled() {
        return mSizeProfileEnabled;
    }

    public ColorProfileKeyV2 getColorProfileKeyV2() {
        return mColorProfileKeyV2;
    }
}
