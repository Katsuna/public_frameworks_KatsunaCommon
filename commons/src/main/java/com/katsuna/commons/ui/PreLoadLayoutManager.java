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
package com.katsuna.commons.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class PreLoadLayoutManager extends LinearLayoutManager {

    private int mDisplayHeight;

    public PreLoadLayoutManager(Context context) {
        super(context);
        setHeight(context);
    }

    public PreLoadLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        setHeight(context);
    }

    public PreLoadLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setHeight(context);
    }

    private void setHeight(Context context) {
        mDisplayHeight = context.getResources().getDisplayMetrics().heightPixels * 2;
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        return mDisplayHeight;
    }
}
