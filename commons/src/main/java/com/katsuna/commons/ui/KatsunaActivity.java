package com.katsuna.commons.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.entities.UserProfileContainer;
import com.katsuna.commons.utils.ColorCalc;
import com.katsuna.commons.utils.ProfileReader;

/**
 * Provides common functionality to subclasses.
 * Use it to automatically access {@link UserProfileContainer} info from a subclass.
 */

public abstract class KatsunaActivity extends AppCompatActivity {

    protected UserProfileContainer mUserProfileContainer;
    protected boolean mUserProfileChanged;
    protected Toolbar mToolbar;
    protected FloatingActionButton mFab1;
    protected FloatingActionButton mFab2;
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
            adjustFabColors(colorProfile);
        }
    }

    protected void adjustFabColors(ColorProfile profile) {
        if(mFab1 != null) {
            int color1 = ColorCalc.getColor(this, ColorProfileKey.ACCENT1_COLOR, profile);
            setFabBackgroundColor(mFab1, color1);
        }
        if(mFab2 != null) {
            int color2 = ColorCalc.getColor(this, ColorProfileKey.ACCENT2_COLOR, profile);
            setFabBackgroundColor(mFab2, color2);
        }
    }

    private void setFabBackgroundColor(FloatingActionButton fab, int color) {
        fab.setBackgroundTintList(ColorStateList.valueOf(color));
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

}
