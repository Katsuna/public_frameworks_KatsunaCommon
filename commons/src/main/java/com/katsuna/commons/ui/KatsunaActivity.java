package com.katsuna.commons.ui;

import android.support.v4.content.ContextCompat;
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

    @Override
    protected void onResume() {
        super.onResume();
        UserProfileContainer userProfileContainer = ProfileReader.getKatsunaUserProfile(this);
        setUserProfile(userProfileContainer);

        if (mUserProfileChanged) {
            ColorProfile colorProfile = mUserProfileContainer.getColorProfile();
            adjustBackground(colorProfile);
            adjustStatusBar(colorProfile);
            adjustToolbar(colorProfile);
        }
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

    private void adjustBackground(ColorProfile profile) {
        int color = ColorCalc.getColor(ColorProfileKey.MAIN_COLOR_VERY_LIGHT, profile);
        getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(this, color));
    }

    private void adjustStatusBar(ColorProfile profile) {
        int color = ColorCalc.getColor(ColorProfileKey.MAIN_COLOR_DARK, profile);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, color));
    }

    private void adjustToolbar(ColorProfile profile) {
        if (mToolbar != null) {
            int color = ColorCalc.getColor(ColorProfileKey.MAIN_COLOR_MEDIUM, profile);
            mToolbar.setBackgroundColor(ContextCompat.getColor(this, color));
        }
    }
}
