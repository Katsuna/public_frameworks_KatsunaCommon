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
package com.katsuna.commons.utils;

import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class KatsunaSnackbarBuilder {

    private final View mParentLayout;
    private final int mLayoutId;
    private final int mDuration;
    private Integer mDescriptionId;
    private Integer mView1Id;
    private View.OnClickListener mView1Pressed;

    public KatsunaSnackbarBuilder(View parentLayout, int layoutId, int duration) {
        mParentLayout = parentLayout;
        mLayoutId = layoutId;
        mDuration = duration;
    }

    public void setDescriptionId(Integer descriptionId) {
        mDescriptionId = descriptionId;
    }

    public void setView1Id(Integer view1Id) {
        mView1Id = view1Id;
    }

    public void setView1Pressed(View.OnClickListener view1Pressed) {
        mView1Pressed = view1Pressed;
    }

    public Snackbar create() {

        // credits:   https://stackoverflow.com/a/33441214
        Snackbar snackbar = Snackbar.make(mParentLayout, "", mDuration);

        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Hide the text
        TextView textView = layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        LayoutInflater li = LayoutInflater.from(mParentLayout.getContext());
        View snackView = li.inflate(mLayoutId, null);

        TextView description = null;
        if (mDescriptionId != null) {
            description = snackView.findViewById(mDescriptionId);
        }

        View view1 = null;
        if (mView1Id != null) {
            view1 = snackView.findViewById(mView1Id);
        }

        //If the view is not covering the whole snackbar layout, add this line
        layout.setPadding(0, 0, 0, 0);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);

        // layout must be added first
        if (view1 != null) {
            view1.setOnClickListener(mView1Pressed);
        }

        return snackbar;
    }
}
