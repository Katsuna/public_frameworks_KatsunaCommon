package com.katsuna.commons.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.RemoteViews;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.entities.UserProfile;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class NotificationRemoteViewBuilder {

    private final Context mContext;
    private final UserProfile mProfile;
    private final int mLayoutId;
    private int mLeftHandedLayoutId;
    private String mTitle;
    private String mDescription;
    private String mPrimaryButtonLabel;
    private String mSecondaryButtonLabel;

    private int mImageId;
    private Bitmap mBitmap;
    private PendingIntent mPrimaryPendingIntent;
    private PendingIntent mSecondaryPendingIntent;

    public NotificationRemoteViewBuilder(Context context, UserProfile profile, int layoutId) {
        mContext = context;
        mProfile = profile;
        mLayoutId = layoutId;
    }

    public NotificationRemoteViewBuilder(Context context, UserProfile profile, int layoutId,
                                         int leftHandedLayoutId) {
        this(context, profile, layoutId);
        mLeftHandedLayoutId = leftHandedLayoutId;
    }

    public RemoteViews build() {

        int layoutId = mLayoutId;
        if (!mProfile.isRightHanded && mLeftHandedLayoutId != 0) {
            layoutId = mLeftHandedLayoutId;
        }
        RemoteViews notificationView = new RemoteViews(mContext.getPackageName(), layoutId);

        if (mImageId != 0) {
            notificationView.setImageViewResource(R.id.image, mImageId);
        }

        if (mBitmap != null) {
            notificationView.setImageViewBitmap(R.id.image, mBitmap);
        }

        notificationView.setTextViewText(R.id.title, mTitle);
        notificationView.setTextViewText(R.id.description, mDescription);
        notificationView.setTextViewText(R.id.primary_button, mPrimaryButtonLabel);
        notificationView.setOnClickPendingIntent(R.id.primary_button, mPrimaryPendingIntent);

        //adjust profile

        //size adjustments
        notificationView.setTextViewTextSize(R.id.title, TypedValue.COMPLEX_UNIT_PX,
                getTextSize(mProfile));
        notificationView.setTextViewTextSize(R.id.description, TypedValue.COMPLEX_UNIT_PX,
                getTextSize(mProfile));

        if (TextUtils.isEmpty(mTitle)) {
            notificationView.setViewVisibility(R.id.title, View.GONE);
        }

        //color adjustments
        int color1 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT1_COLOR,
                mProfile.colorProfile);
        int color2 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT2_COLOR,
                mProfile.colorProfile);

        notificationView.setInt(R.id.primary_button, "setBackgroundColor", color1);

        if (mProfile.colorProfile == ColorProfile.CONTRAST) {
            int whiteResId = ContextCompat.getColor(mContext, R.color.common_white);
            notificationView.setTextColor(R.id.primary_button, whiteResId);
        }

        // hack to adjust height
        int padding = getButtonPadding(mProfile);
        notificationView.setViewPadding(R.id.primary_button, padding, padding, padding, padding);

        if (isSecondaryButtonEnabled()) {
            notificationView.setViewVisibility(R.id.secondary_button, View.VISIBLE);
            notificationView.setTextViewText(R.id.secondary_button, mSecondaryButtonLabel);
            notificationView.setOnClickPendingIntent(R.id.secondary_button, mSecondaryPendingIntent);

            notificationView.setViewPadding(R.id.secondary_button, padding, padding, padding,
                    padding);

            int bgDrawable = getSecondaryButtonBackground(mProfile);
            notificationView.setInt(R.id.secondary_button, "setBackgroundResource", bgDrawable);

            if (mProfile.colorProfile == ColorProfile.CONTRAST) {
                notificationView.setInt(R.id.secondary_button, "setTextColor", color1);
            } else {
                notificationView.setInt(R.id.secondary_button, "setTextColor", color2);
            }
        }

        return notificationView;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setPrimaryButtonLabel(String label) {
        mPrimaryButtonLabel = label;
    }

    public void setSecondaryButtonLabel(String label) {
        mSecondaryButtonLabel = label;
    }

    public void setImageId(int imageId) {
        this.mImageId = imageId;
    }

    public PendingIntent getPrimaryPendingIntent() {
        return mPrimaryPendingIntent;
    }

    public void setPrimaryPendingIntent(PendingIntent primaryPendingIntent) {
        this.mPrimaryPendingIntent = primaryPendingIntent;
    }

    public PendingIntent getSecondaryPendingIntent() {
        return mSecondaryPendingIntent;
    }

    public void setSecondaryPendingIntent(PendingIntent secondaryPendingIntent) {
        this.mSecondaryPendingIntent = secondaryPendingIntent;
    }

    private int getSecondaryButtonBackground(UserProfile profile) {
        switch (profile.colorProfile) {
            case COLOR_IMPAIREMENT:
            case MAIN:
                return R.drawable.common_button_border_blue_a700;
            case COLOR_IMPAIRMENT_AND_CONTRAST:
                return R.drawable.common_button_border_indigo_a700;
            case CONTRAST:
                return R.drawable.common_button_border_grey900;
            default:
                return R.drawable.common_button_border_blue_a700;
        }
    }

    private int getTextSize(UserProfile profile) {
        switch (profile.opticalSizeProfile) {
            case SIMPLE:
                return getDimen(R.dimen.common_notification_text_size_simple);
            case ADVANCED:
                return getDimen(R.dimen.common_notification_text_size_advanced);
            case INTERMEDIATE:
            case AUTO:
                return getDimen(R.dimen.common_notification_text_size_intemediate);
            default:
                return getDimen(R.dimen.common_notification_text_size_intemediate);
        }
    }

    private int getButtonPadding(UserProfile profile) {
        switch (profile.opticalSizeProfile) {
            case SIMPLE:
                return getDimen(R.dimen.common_notification_button_padding_simple);
            case ADVANCED:
                return getDimen(R.dimen.common_notification_button_padding_advanced);
            case INTERMEDIATE:
            case AUTO:
                return getDimen(R.dimen.common_notification_button_padding_intemediate);
            default:
                return getDimen(R.dimen.common_notification_button_padding_intemediate);
        }
    }

    private int getDimen(int resId) {
        Resources r = mContext.getResources();
        return r.getDimensionPixelSize(resId);
    }

    private boolean isSecondaryButtonEnabled() {
        return !TextUtils.isEmpty(mSecondaryButtonLabel);
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }
}
