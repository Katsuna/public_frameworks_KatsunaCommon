package com.katsuna.commons.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
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

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.OpticalParams;
import com.katsuna.commons.entities.SizeProfileKey;
import com.katsuna.commons.entities.UserProfileContainer;
import com.katsuna.commons.profile.Adjuster;
import com.katsuna.commons.utils.Constants;
import com.katsuna.commons.utils.ProfileReader;
import com.katsuna.commons.utils.SizeCalc;

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
    protected Adjuster mAdjuster;
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
        int theme = R.style.CommonAppTheme;
        if (profile == ColorProfile.CONTRAST ||
                profile == ColorProfile.COLOR_IMPAIRMENT_AND_CONTRAST) {
            theme = R.style.CommonAppThemeContrast;
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
            mAdjuster = new Adjuster(this, mUserProfileContainer.getActiveUserProfile());
            // color profile adjustments
            mAdjuster.adjustFabColors(mFab1, mFab2);
            mAdjuster.adjustPopupButtons(mPopupButton1, mPopupButton2);

            // right hand adjustments
            adjustRightHand();

            // adjust fab size
            mAdjuster.adjustFabSize(mFab1, mFab2);
            adjustToolbar();
        }

        refreshLastTouchTimestamp();
        showPopup(false);
    }

    private void adjustToolbar() {
        if (mToolbar == null) return;

        OpticalParams params = SizeCalc.getOpticalParams(SizeProfileKey.TITLE,
                mUserProfileContainer.getOpticalSizeProfile());

        mToolbar.setTitleTextAppearance(this, params.getStyle());
    }

    protected void tintFabs(boolean flag) {
        if (mAdjuster != null) {
            mAdjuster.tintFabs(mFab1, mFab2, flag);
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
                if (now - Constants.POPUP_INACTIVITY_THRESHOLD > mLastTouchTimestamp) {
                    refreshLastTouchTimestamp();
                    showPopup(!mPopupVisible);
                }
                mPopupActionHandler.postDelayed(this, Constants.HANDLER_DELAY);
            }
        }, Constants.HANDLER_DELAY);
    }

    protected abstract void showPopup(boolean flag);

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
            if (mFab1 != null) {
                ViewGroup.LayoutParams lp = mFab1.getLayoutParams();
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
        mAdjuster.adjustFabPosition(mFabContainer, verticalCenter);
    }

    protected void initToolbar() {
        initToolbar(null);
    }

    protected void initToolbar(Integer drawableResId) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
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

    protected void refreshUserProfileContainer() {
        UserProfileContainer userProfileContainer = ProfileReader.getKatsunaUserProfile(this);
        setUserProfile(userProfileContainer);
    }

}
