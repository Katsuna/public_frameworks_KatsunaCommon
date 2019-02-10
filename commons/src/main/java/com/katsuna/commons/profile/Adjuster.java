package com.katsuna.commons.profile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.OpticalParams;
import com.katsuna.commons.entities.SizeProfileKeyV2;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.utils.ColorCalc;
import com.katsuna.commons.utils.ColorCalcV2;
import com.katsuna.commons.utils.DrawUtils;
import com.katsuna.commons.utils.Shape;
import com.katsuna.commons.utils.SizeCalcV2;

public class Adjuster {

    private final Context mContext;
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

    public void adjustFabSample(View fab, TextView textView) {
        int color1 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT1_COLOR,
                mUserProfile.colorProfile);
        int color2 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT2_COLOR,
                mUserProfile.colorProfile);
        int whiteResId = ContextCompat.getColor(mContext, R.color.common_white);
        int blackResId = ContextCompat.getColor(mContext, R.color.common_black);

        if (fab != null) {
            GradientDrawable fabBg =  (GradientDrawable) fab.getBackground();
            fabBg.setColor(color1);
        }

        if (textView != null) {
            if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                textView.setTextColor(whiteResId);
            } else {
                textView.setTextColor(blackResId);
            }
        }
    }

    public void adjustFabColors(FloatingActionButton fab1, FloatingActionButton fab2) {
        int color1 = ColorCalcV2.getColor(mContext, ColorProfileKeyV2.PRIMARY_COLOR_1,
                mUserProfile.colorProfile);
        int color2 = ColorCalcV2.getColor(mContext, ColorProfileKeyV2.PRIMARY_COLOR_2,
                mUserProfile.colorProfile);
        int whiteResId = ContextCompat.getColor(mContext, R.color.common_white);
        int blackResId = ContextCompat.getColor(mContext, R.color.common_black);

        if (fab1 != null) {
            if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                DrawUtils.setColor(fab1.getDrawable(), whiteResId);
            } else {
                DrawUtils.setColor(fab1.getDrawable(), blackResId);
            }
            setFabBackgroundColor(fab1, color1);
        }
        if (fab2 != null) {
            DrawUtils.setColor(fab2.getDrawable(), whiteResId);
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
        if (flag) {
            int color1 = ContextCompat.getColor(mContext, R.color.common_pink_tinted);
            int color2 = ContextCompat.getColor(mContext, R.color.common_blue_tinted);
            int whiteResId = ContextCompat.getColor(mContext, R.color.common_white);
            int blackResId = ContextCompat.getColor(mContext, R.color.common_black54);

            if (fab1 != null) {
                if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                    fab1.setBackgroundTintList(ColorStateList.valueOf(color2));
                    DrawUtils.setColor(fab1.getDrawable(), whiteResId);
                } else {
                    fab1.setBackgroundTintList(ColorStateList.valueOf(color1));
                    DrawUtils.setColor(fab1.getDrawable(), blackResId);
                }
            }
            if (fab2 != null) {
                if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                    fab2.setBackgroundTintList(ColorStateList.valueOf(color1));
                    DrawUtils.setColor(fab2.getDrawable(), blackResId);
                } else {
                    fab2.setBackgroundTintList(ColorStateList.valueOf(color2));
                    DrawUtils.setColor(fab2.getDrawable(), whiteResId);
                }
            }
        } else {
            adjustFabColors(fab1, fab2);
        }
    }

    public void adjustPopupButtons(Button popupButton1) {
        adjustPopupButtons(popupButton1, null);
    }

    public void adjustPopupButtons(Button popupButton1, Button popupButton2) {
        int color1 = ColorCalcV2.getColor(mContext, ColorProfileKeyV2.PRIMARY_COLOR_1,
                mUserProfile.colorProfile);
        int color2 = ColorCalcV2.getColor(mContext, ColorProfileKeyV2.PRIMARY_COLOR_2,
                mUserProfile.colorProfile);
        int whiteResId = ContextCompat.getColor(mContext, R.color.common_white);
        int blackResId = ContextCompat.getColor(mContext, R.color.common_black);

        if (popupButton1 != null) {
            Shape.setRoundedBackground(popupButton1, color1);

            if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                popupButton1.setTextColor(whiteResId);
            } else {
                popupButton1.setTextColor(blackResId);
            }
        }

        if (popupButton2 != null) {
            Shape.setRoundedBackground(popupButton2, color2);
            popupButton2.setTextColor(whiteResId);
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

    public void adjustSearchBar(View container, ImageButton prevButton, ImageButton nextButton) {
        if (container != null) {
            int primaryColor1 = ColorCalcV2.getColor(mContext, ColorProfileKeyV2.PRIMARY_COLOR_1,
                    mUserProfile.colorProfile);

            container.setBackgroundColor(primaryColor1);
        }
    }

    public void adjustRightHand(LinearLayout fabContainer, FloatingActionButton fab1,
                                Button popupButton1) {
        int horizontalGravity = mUserProfile.isRightHanded ? Gravity.END : Gravity.START;
        if (fabContainer != null) {
            fabContainer.setGravity(horizontalGravity | Gravity.CENTER);
            fabContainer.removeAllViews();
            fabContainer.addView(mUserProfile.isRightHanded ? popupButton1 : fab1);
            fabContainer.addView(mUserProfile.isRightHanded ? fab1 : popupButton1);
        }
    }

    public void adjustSearchBarForRightHand(View mFabToolbarContainer, View mFabToolbar) {
        if (mFabToolbarContainer == null || mFabToolbar == null) {
            return;
        }

        int shadowPixels = mContext.getResources()
                .getDimensionPixelSize(com.katsuna.commons.R.dimen.common_search_shadow);
        if (mUserProfile.isRightHanded) {
            FrameLayout.LayoutParams lp =
                    (FrameLayout.LayoutParams) mFabToolbarContainer.getLayoutParams();
            lp.gravity = Gravity.END;

            //set shadow properly
            Drawable bg = mContext.getDrawable(com.katsuna.commons.R.drawable.common_search_bar_bg);
            mFabToolbar.setBackground(bg);
            mFabToolbar.setPadding(shadowPixels, 0, 0, 0);
        } else {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabToolbarContainer.getLayoutParams();
            lp.gravity = Gravity.START;

            //set shadow properly
            Drawable bg = mContext.getDrawable(com.katsuna.commons.R.drawable.common_search_bar_bg_left_handed);
            mFabToolbar.setBackground(bg);
            mFabToolbar.setPadding(0, 0, shadowPixels, 0);
        }
    }

    public void adjustFabSize(FloatingActionButton fab1, FloatingActionButton fab2) {
        adjustFabSize(fab1);
        adjustFabSize(fab2);
    }

    public void adjustFabSize(FloatingActionButton fab) {
        if (fab == null) return;

        OpticalParams params = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.FLOATING_BUTTON,
                mUserProfile.opticalSizeProfile);
        int h = mContext.getResources().getDimensionPixelSize(params.getHeight());

        ViewGroup.LayoutParams layoutParams = fab.getLayoutParams();
        layoutParams.width = h;
        layoutParams.height = h;
    }


    public void adjustFabSampleSize(View fab, TextView textView) {
        OpticalParams params = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.FLOATING_BUTTON,
                mUserProfile.opticalSizeProfile);
        int h = mContext.getResources().getDimensionPixelSize(params.getHeight());

        ViewGroup.LayoutParams layoutParams = fab.getLayoutParams();
        layoutParams.width = h;
        layoutParams.height = h;

        float textSize = mContext.getResources()
                .getDimension(R.dimen.common_fab_text_size_intermediate);
        switch (mUserProfile.opticalSizeProfile) {
            case SIMPLE:
                textSize = mContext.getResources().
                        getDimension(R.dimen.common_fab_text_size_simple);
                break;
            case INTERMEDIATE:
                textSize = mContext.getResources()
                        .getDimension(R.dimen.common_fab_text_size_intermediate);
                break;
            case ADVANCED:
                textSize = mContext.getResources()
                        .getDimension(R.dimen.common_fab_text_size_advanced);
                break;
        }
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }
}
