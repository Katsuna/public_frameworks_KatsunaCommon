package com.katsuna.commons.profile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.utils.ColorCalc;
import com.katsuna.commons.utils.ResourcesUtils;
import com.katsuna.commons.utils.Shape;

public class Adjuster {

    private Context mContext;
    private UserProfile mUserProfile;

    public Adjuster(Context context) {
        mContext = context;
    }

    public Adjuster(Context context, UserProfile userProfile) {
        this(context);
        mUserProfile = userProfile;
    }

    public void adjustFabColors(FloatingActionButton fab1) {
        adjustFabColors(fab1, null);
    }

    public void adjustFabColors(FloatingActionButton fab1, FloatingActionButton fab2) {
        if (fab1 != null) {
            int color1 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT1_COLOR,
                    mUserProfile.colorProfile);
            setFabBackgroundColor(fab1, color1);
        }
        if (fab2 != null) {
            int color2 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT2_COLOR,
                    mUserProfile.colorProfile);
            setFabBackgroundColor(fab2, color2);
        }
    }

    public void setFabBackgroundColor(FloatingActionButton fab, int color) {
        fab.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    public void tintFabs(FloatingActionButton fab1, boolean flag) {
        tintFabs(fab1, null, flag);
    }

    public void tintFabs(FloatingActionButton fab1, FloatingActionButton fab2, boolean flag) {
        int color1;
        int color2;
        if (flag) {
            int color1ResId = ResourcesUtils.getColor(mContext, "common_pink_tinted");
            color1 = ContextCompat.getColor(mContext, color1ResId);
            int color2ResId = ResourcesUtils.getColor(mContext, "common_blue_tinted");
            color2 = ContextCompat.getColor(mContext, color2ResId);
        } else {
            color1 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT1_COLOR,
                    mUserProfile.colorProfile);
            color2 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT2_COLOR,
                    mUserProfile.colorProfile);
        }

        if (fab1 != null) {
            fab1.setBackgroundTintList(ColorStateList.valueOf(color1));
        }
        if (fab2 != null) {
            fab2.setBackgroundTintList(ColorStateList.valueOf(color2));
        }
    }

    public void adjustPopupButtons(Button popupButton1) {
        adjustPopupButtons(popupButton1, null);
    }

    public void adjustPopupButtons(Button popupButton1, Button popupButton2) {
        if (popupButton1 != null) {
            int color1 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT1_COLOR,
                    mUserProfile.colorProfile);
            Shape.setRoundedBackground(popupButton1, color1);
        }

        if (popupButton2 != null) {
            int color2 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT2_COLOR,
                    mUserProfile.colorProfile);
            Shape.setRoundedBackground(popupButton2, color2);
        }
    }

    public void adjustFabPosition(LinearLayout fabContainer, boolean verticalCenter) {
        int verticalCenterGravity = verticalCenter ? Gravity.CENTER : Gravity.BOTTOM;
        if (mUserProfile.isRightHanded) {
            fabContainer.setGravity(verticalCenterGravity | Gravity.END);
        } else {
            fabContainer.setGravity(verticalCenterGravity | Gravity.START);
        }
    }

}
