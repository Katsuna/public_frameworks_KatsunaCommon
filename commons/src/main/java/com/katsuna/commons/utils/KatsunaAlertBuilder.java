package com.katsuna.commons.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.entities.UserProfileContainer;

import java.util.List;

public class KatsunaAlertBuilder {
    private final Context mContext;
    private View mView;
    private int mTitleResId;
    private int mMessageResId;
    private UserProfileContainer mUserProfileContainer;
    private View.OnClickListener mCancelListener;
    private View.OnClickListener mOkListener;
    private Button mCancelButton;
    private Button mOkButton;
    private List<String> mScrollViewItems;

    public KatsunaAlertBuilder(Context context) {
        mContext = context;
    }

    public void setView(int layoutId) {
        mView = LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    public void setTitle(int titleResId) {
        mTitleResId = titleResId;
    }

    public void setMessage(int messageResId) {
        mMessageResId = messageResId;
    }

    public void setOkListener(View.OnClickListener okListener) {
        mOkListener = okListener;
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        mCancelListener = cancelListener;
    }

    public void setUserProfileContainer(UserProfileContainer userProfileContainer) {
        mUserProfileContainer = userProfileContainer;
    }

    public AlertDialog create() {
        final AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle(mTitleResId)
                .setMessage(mMessageResId)
                .setView(mView).create();

        int cancelButtonResId = ResourcesUtils.getId(mContext, "alert_cancel_button");
        mCancelButton = (Button) mView.findViewById(cancelButtonResId);
        mCancelButton.setText(android.R.string.cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancelListener != null) {
                    mCancelListener.onClick(v);
                }
                dialog.dismiss();
            }
        });

        int okButtonResId = ResourcesUtils.getId(mContext, "alert_ok_button");
        mOkButton = (Button) mView.findViewById(okButtonResId);
        mOkButton.setText(android.R.string.ok);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOkListener.onClick(v);
                dialog.dismiss();
            }
        });

        if (mScrollViewItems != null) {
            // populate scroll view
            int scrollViewItemsContainerId = ResourcesUtils.getId(mContext,
                    "scroll_view_items_container");
            LinearLayout scrollViewItemsContainer =
                    (LinearLayout) mView.findViewById(scrollViewItemsContainerId);

            int scrollItemsPaddingId = ResourcesUtils.getDimen(mContext,
                    "common_alert_scroll_items_padding");
            int scrollItemsPaddingInPixel = mContext.getResources()
                    .getDimensionPixelSize(scrollItemsPaddingId);

            for (String item : mScrollViewItems) {
                TextView tv = new TextView(mContext);
                tv.setText(item);
                tv.setPadding(scrollItemsPaddingInPixel * 2, scrollItemsPaddingInPixel,
                        scrollItemsPaddingInPixel * 2, scrollItemsPaddingInPixel);

                scrollViewItemsContainer.addView(tv);
            }

            // show scroll view
            int scrollViewContainerId = ResourcesUtils.getId(mContext, "scroll_view_container");
            View scrollViewContainer = mView.findViewById(scrollViewContainerId);
            scrollViewContainer.setVisibility(View.VISIBLE);
        }

        adjustProfile();

        return dialog;
    }

    private void adjustProfile() {
        ColorProfile colorProfile = mUserProfileContainer.getColorProfile();
        // set action buttons background color
        int color1 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT1_COLOR, colorProfile);
        Shape.setRoundedBackground(mOkButton, color1);

        int color2 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT2_COLOR, colorProfile);
        Shape.setRoundedBackground(mCancelButton, color2);
    }

    public void setScrollViewItems(List<String> scrollViewItems) {
        mScrollViewItems = scrollViewItems;
    }
}
