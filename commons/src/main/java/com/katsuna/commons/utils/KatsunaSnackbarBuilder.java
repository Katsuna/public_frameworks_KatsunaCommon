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
