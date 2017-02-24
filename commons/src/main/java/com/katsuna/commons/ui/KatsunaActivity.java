package com.katsuna.commons.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.entities.UserProfileContainer;
import com.katsuna.commons.utils.ColorCalc;
import com.katsuna.commons.utils.Constants;
import com.katsuna.commons.utils.ProfileReader;
import com.katsuna.commons.utils.ResourcesUtils;
import com.katsuna.commons.utils.Shape;

/**
 * Provides common functionality to subclasses.
 * Use it to automatically access {@link UserProfileContainer} info from a subclass.
 */

public abstract class KatsunaActivity extends AppCompatActivity {

    protected UserProfileContainer mUserProfileContainer;
    protected boolean mUserProfileChanged;
    protected Toolbar mToolbar;
    protected LinearLayout mButtonsContainer1;
    protected LinearLayout mButtonsContainer2;
    protected LinearLayout mFabContainer;
    protected FloatingActionButton mFab1;
    protected FloatingActionButton mFab2;
    protected Button mPopupButton1;
    protected Button mPopupButton2;
    protected long mLastTouchTimestamp;
    protected Handler mPopupActionHandler;
    protected boolean mPopupVisible;
    protected View mFabToolbar;
    protected boolean mFabToolbarOn;
    protected FrameLayout mFabToolbarContainer;
    protected LinearLayout mViewPagerContainer;
    protected ViewPager mViewPager;
    protected ImageButton mPrevButton;
    protected ImageButton mNextButton;
    private int mTheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setupTheme();
        super.onCreate(savedInstanceState);
    }

    private void setupTheme() {
        UserProfileContainer userProfileContainer = ProfileReader.getKatsunaUserProfile(this);
        ColorProfile colorProfile = userProfileContainer.getColorProfile();
        mTheme = getTheme(colorProfile);
        setTheme(mTheme);
    }

    private int getTheme(ColorProfile profile) {
        int theme = ResourcesUtils.getStyle(this, "CommonAppTheme");
        if (profile == ColorProfile.CONTRAST ||
                profile == ColorProfile.COLOR_IMPAIRMENT_AND_CONTRAST) {
            theme = ResourcesUtils.getStyle(this, "CommonAppThemeContrast");
        }
        return theme;
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserProfileContainer userProfileContainer = ProfileReader.getKatsunaUserProfile(this);
        setUserProfile(userProfileContainer);

        ColorProfile colorProfile = mUserProfileContainer.getColorProfile();
        int newTheme = getTheme(colorProfile);
        if (newTheme != mTheme) {
            // theme changed!
            // recreate activity to apply new theme.
            finish();
            startActivity(new Intent(this, this.getClass()));
            return;
        }

        if (mUserProfileChanged) {
            // color profile adjustments
            adjustFabColors(colorProfile);
            adjustPopupButtons(colorProfile);

            // right hand adjustments
            adjustRightHand();
        }

        refreshLastTouchTimestamp();
    }

    private void adjustFabColors(ColorProfile profile) {
        if (mFab1 != null) {
            int color1 = ColorCalc.getColor(this, ColorProfileKey.ACCENT1_COLOR, profile);
            setFabBackgroundColor(mFab1, color1);
        }
        if (mFab2 != null) {
            int color2 = ColorCalc.getColor(this, ColorProfileKey.ACCENT2_COLOR, profile);
            setFabBackgroundColor(mFab2, color2);
        }
    }

    protected void tintFabs(boolean flag) {
        int color1;
        int color2;
        if (flag) {
            int color1ResId = ResourcesUtils.getColor(this, "common_pink_tinted");
            color1 = ContextCompat.getColor(this, color1ResId);
            int color2ResId = ResourcesUtils.getColor(this, "common_blue_tinted");
            color2 = ContextCompat.getColor(this, color2ResId);
        } else {
            ColorProfile colorProfile = mUserProfileContainer.getColorProfile();
            color1 = ColorCalc.getColor(this, ColorProfileKey.ACCENT1_COLOR, colorProfile);
            color2 = ColorCalc.getColor(this, ColorProfileKey.ACCENT2_COLOR, colorProfile);
        }

        if (mFab1 != null) {
            mFab1.setBackgroundTintList(ColorStateList.valueOf(color1));
        }
        if (mFab2 != null) {
            mFab2.setBackgroundTintList(ColorStateList.valueOf(color2));
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        refreshLastTouchTimestamp();
        return super.dispatchTouchEvent(ev);
    }

    protected void refreshLastTouchTimestamp() {
        mLastTouchTimestamp = System.currentTimeMillis();
    }

    protected void initPopupActionHandler() {
        mPopupActionHandler = new Handler();
        mPopupActionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                if (now - Constants.POPUP_INACTIVITY_THRESHOLD > mLastTouchTimestamp && !mPopupVisible) {
                    showPopup(true);
                }
                mPopupActionHandler.postDelayed(this, Constants.HANDLER_DELAY);
            }
        }, Constants.HANDLER_DELAY);
    }

    protected abstract void showPopup(boolean flag);

    private void adjustPopupButtons(ColorProfile profile) {
        if (mPopupButton1 != null) {
            int color1 = ColorCalc.getColor(this, ColorProfileKey.ACCENT1_COLOR, profile);
            Shape.setRoundedBackground(mPopupButton1, color1);
        }

        if (mPopupButton2 != null) {
            int color2 = ColorCalc.getColor(this, ColorProfileKey.ACCENT2_COLOR, profile);
            Shape.setRoundedBackground(mPopupButton2, color2);
        }
    }

    private void adjustRightHand() {
        if (mUserProfileContainer.isRightHanded()) {
            positionFabsToLeft(false);
        } else {
            positionFabsToLeft(true);
        }
    }

    protected void positionFabsToLeft(boolean flag) {
        int horizontalGravity = flag ? Gravity.START : Gravity.END;
        if (mFabContainer != null) {
            // case with fab container with 1 or 2 fabs present with secondary buttons with text
            // description
            mFabContainer.setGravity(horizontalGravity | Gravity.CENTER);

            if (mButtonsContainer1 != null) {
                mButtonsContainer1.removeAllViews();
                mButtonsContainer1.addView(flag ? mFab1 : mPopupButton1);
                mButtonsContainer1.addView(flag ? mPopupButton1 : mFab1);
            }

            if (mButtonsContainer2 != null) {
                mButtonsContainer2.removeAllViews();
                mButtonsContainer2.addView(flag ? mFab2 : mPopupButton2);
                mButtonsContainer2.addView(flag ? mPopupButton2 : mFab2);
            }
        } else {
            if (mFab2 != null) {
                ViewGroup.LayoutParams lp = mFab2.getLayoutParams();
                if (lp instanceof CoordinatorLayout.LayoutParams) {
                    CoordinatorLayout.LayoutParams coordLp = (CoordinatorLayout.LayoutParams) lp;
                    //keep vertical gravity
                    coordLp.gravity &= Gravity.VERTICAL_GRAVITY_MASK;
                    //apply horizontal gravity
                    coordLp.gravity |= horizontalGravity;
                }
            }
        }
    }

    protected void adjustFabPosition(boolean verticalCenter) {
        int verticalCenterGravity = verticalCenter ? Gravity.CENTER : Gravity.BOTTOM;
        if (mUserProfileContainer.isRightHanded()) {
            mFabContainer.setGravity(verticalCenterGravity | Gravity.END);
        } else {
            mFabContainer.setGravity(verticalCenterGravity | Gravity.START);
        }
    }

    private void setFabBackgroundColor(FloatingActionButton fab, int color) {
        fab.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    protected void initToolbar() {
        initToolbar(null);
    }

    protected void initToolbar(Integer drawableResId) {
        int resId = ResourcesUtils.getId(this, "toolbar");
        mToolbar = (Toolbar) findViewById(resId);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (drawableResId != null) {
                actionBar.setHomeAsUpIndicator(drawableResId);
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUserProfile(UserProfileContainer userProfileContainer) {
        if (userProfileContainer.equals(mUserProfileContainer)) {
            mUserProfileChanged = false;
        } else {
            mUserProfileContainer = userProfileContainer;
            mUserProfileChanged = true;
        }
    }

}
