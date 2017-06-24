package com.katsuna.commons.profile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.entities.OpticalParams;
import com.katsuna.commons.entities.SizeProfileKey;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.ui.fragments.SearchBarFragment;
import com.katsuna.commons.utils.ColorCalc;
import com.katsuna.commons.utils.DrawUtils;
import com.katsuna.commons.utils.Shape;
import com.katsuna.commons.utils.SizeCalc;

import java.util.List;

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
        int color1 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT1_COLOR,
                mUserProfile.colorProfile);
        int color2 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT2_COLOR,
                mUserProfile.colorProfile);
        int whiteResId = ContextCompat.getColor(mContext, R.color.common_white);
        int blackResId = ContextCompat.getColor(mContext, R.color.common_black);

        if (fab1 != null) {
            if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                DrawUtils.setColor(fab1.getDrawable(), color2);
            } else {
                DrawUtils.setColor(fab1.getDrawable(), blackResId);
            }
            setFabBackgroundColor(fab1, color1);
        }
        if (fab2 != null) {
            if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                DrawUtils.setColor(fab2.getDrawable(), color1);
            } else {
                DrawUtils.setColor(fab2.getDrawable(), whiteResId);
            }
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
        int color1 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT1_COLOR,
                mUserProfile.colorProfile);
        int color2 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT2_COLOR,
                mUserProfile.colorProfile);
        int whiteResId = ContextCompat.getColor(mContext, R.color.common_white);
        int blackResId = ContextCompat.getColor(mContext, R.color.common_black);

        if (popupButton1 != null) {
            Shape.setRoundedBackground(popupButton1, color1);

            if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                popupButton1.setTextColor(color2);
            } else {
                popupButton1.setTextColor(blackResId);
            }
        }

        if (popupButton2 != null) {
            Shape.setRoundedBackground(popupButton2, color2);
            if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                popupButton2.setTextColor(color1);
            } else {
                popupButton2.setTextColor(whiteResId);
            }
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
            int accentColor1 = ColorCalc.getColor(mContext, ColorProfileKey.ACCENT1_COLOR,
                    mUserProfile.colorProfile);
            container.setBackgroundColor(accentColor1);

            int whiteResId = ContextCompat.getColor(mContext, R.color.common_white);
            int black87ResId = ContextCompat.getColor(mContext, R.color.common_black87);

            if (mUserProfile.colorProfile == ColorProfile.CONTRAST) {
                if (prevButton != null) {
                    DrawUtils.setColor(prevButton.getDrawable(), whiteResId);
                }
                if (nextButton != null) {
                    DrawUtils.setColor(nextButton.getDrawable(), whiteResId);
                }
            } else {
                if (prevButton != null) {
                    DrawUtils.setColor(prevButton.getDrawable(), black87ResId);
                }
                if (nextButton != null) {
                    DrawUtils.setColor(nextButton.getDrawable(), black87ResId);
                }
            }
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

        OpticalParams params = SizeCalc.getOpticalParams(SizeProfileKey.FLOATING_BUTTON,
                mUserProfile.opticalSizeProfile);
        int height = mContext.getResources().getDimensionPixelSize(params.getHeight());

        ViewGroup.LayoutParams layoutParams = fab.getLayoutParams();
        layoutParams.width = height;
        layoutParams.height = height;
    }

}
