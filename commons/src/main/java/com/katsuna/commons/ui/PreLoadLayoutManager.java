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
