/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.katsuna.commons.ui;

import android.support.annotation.IdRes;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.controls.DemoProfileV2;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.OpticalParams;
import com.katsuna.commons.entities.PreferenceKey;
import com.katsuna.commons.entities.SizeProfile;
import com.katsuna.commons.entities.SizeProfileKey;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.profile.Adjuster;
import com.katsuna.commons.utils.ColorAdjusterV2;
import com.katsuna.commons.utils.ColorCalcV2;
import com.katsuna.commons.utils.SettingsManager;
import com.katsuna.commons.utils.SizeAdjuster;
import com.katsuna.commons.utils.SizeCalc;
import com.katsuna.commons.utils.ViewUtils;

public abstract class SettingsActivityBase extends KatsunaActivity {

    protected ScrollView mScrollViewContainer;
    private TextView mSizeInitialContainer;
    private View mSizeExpandedContainer;
    private boolean mHandControlsExpanded;
    private boolean mSizeControlsExpanded;
    private boolean mColorControlsExpanded;
    private View mColorExpandedContainer;
    private RadioGroup mHandExpandedContainer;
    private TextView mHandInitialContainer;
    private View mHandDivider;
    private TextView mColorInitialContainer;
    private RadioGroup mRadioGroupSize;
    private RadioButton mRadioSizeAuto;
    private RadioButton mRadioSizeAdvanced;
    private RadioButton mRadioSizeIntermediate;
    private RadioButton mRadioSizeSimple;
    private RadioButton mRadioColorAuto;
    private RadioButton mRadioRightHand;
    private RadioButton mRadioLeftHand;
    private boolean sizeRadioAutoChangeInProgress;
    private View mSizeSampleFab;
    private TextView mSizeSampleFabText;
    private CardView mUsabilitySettingsCard;
    private View mUsabilitySettingsCardInner;
    private boolean resumed = false;
    private DemoProfileV2 mDemoProfileImpairement;
    private DemoProfileV2 mDemoProfileMain;
    private DemoProfileV2 mDemoProfileContrastImpairement;
    private DemoProfileV2 mDemoProfileContrast;

    protected void initControls() {
        mUsabilitySettingsCard = (CardView) findViewById(R.id.usability_settings_card);
        mUsabilitySettingsCardInner = findViewById(R.id.usability_settings_card_inner);

        // hand settings
        mHandDivider = findViewById(R.id.hand_divider);
        mHandInitialContainer = (TextView) findViewById(R.id.hand_initial_container);
        mHandExpandedContainer = (RadioGroup) findViewById(R.id.radio_group_hand);
        mRadioRightHand = (RadioButton) findViewById(R.id.radio_right_hand);
        mRadioLeftHand = (RadioButton) findViewById(R.id.radio_left_hand);

        // size settings
        mSizeExpandedContainer = findViewById(R.id.size_expanded_container);
        mSizeInitialContainer = (TextView) findViewById(R.id.size_initial_container);
        mRadioSizeAuto = (RadioButton) findViewById(R.id.radio_size_auto);
        mRadioSizeAdvanced = (RadioButton) findViewById(R.id.radio_size_advanced);
        mRadioSizeIntermediate = (RadioButton) findViewById(R.id.radio_size_intermediate);
        mRadioSizeSimple = (RadioButton) findViewById(R.id.radio_size_simple);
        mRadioGroupSize = (RadioGroup) findViewById(R.id.radio_group_size);
        mSizeSampleFab = findViewById(R.id.commom_size_sample_fab);
        mSizeSampleFabText = (TextView) findViewById(R.id.commom_size_sample_fab_text);

        // color settings
        mColorExpandedContainer = findViewById(R.id.color_expanded_container);
        mColorInitialContainer = (TextView) findViewById(R.id.color_initial_container);

        setupDemoProfiles();
    }

    private void setupDemoProfiles() {
        mDemoProfileImpairement = (DemoProfileV2) findViewById(R.id.profile_impairement_v2);
        mDemoProfileMain = (DemoProfileV2) findViewById(R.id.profile_main_v2);
        mDemoProfileContrastImpairement = (DemoProfileV2) findViewById(R.id.profile_contrast_impairement_v2);
        mDemoProfileContrast = (DemoProfileV2) findViewById(R.id.profile_contrast_v2);

        UserProfile demoProfile = new UserProfile();
        demoProfile.opticalSizeProfile = SizeProfile.INTERMEDIATE;

        demoProfile.colorProfile = ColorProfile.COLOR_IMPAIREMENT;
        mDemoProfileImpairement.adjustProfile(demoProfile);

        demoProfile.colorProfile = ColorProfile.MAIN;
        mDemoProfileMain.adjustProfile(demoProfile);

        demoProfile.colorProfile = ColorProfile.COLOR_IMPAIRMENT_AND_CONTRAST;
        mDemoProfileContrastImpairement .adjustProfile(demoProfile);

        demoProfile.colorProfile = ColorProfile.CONTRAST;
        mDemoProfileContrast .adjustProfile(demoProfile);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mUserProfileChanged) {
            if (!mUserProfileContainer.hasKatsunaServices()) {
                //enable app right hand setting
                String isRightHanded = SettingsManager.readSetting(this, PreferenceKey.RIGHT_HAND,
                        "true");
                mRadioRightHand.setChecked(Boolean.parseBoolean(isRightHanded));
                mHandInitialContainer.setVisibility(View.VISIBLE);
                mHandDivider.setVisibility(View.VISIBLE);
            } else {
                mHandInitialContainer.setVisibility(View.GONE);
                mHandDivider.setVisibility(View.GONE);
            }
        }

        initAppSettings();

        loadProfiles();

        resumed = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        resumed = false;
    }

    @Override
    protected void showPopup(boolean flag) {
        // no op for settings
    }

    protected void applyProfiles() {
        refreshUserProfileContainer();
        UserProfile profile = mUserProfileContainer.getActiveUserProfile();

        applySizeProfile(profile.opticalSizeProfile);

        OpticalParams opticalParams = SizeCalc.getOpticalParams(SizeProfileKey.SUBHEADER,
                profile.opticalSizeProfile);
        SizeAdjuster.adjustText(this, mRadioSizeAuto, opticalParams);
        SizeAdjuster.adjustText(this, mRadioColorAuto, opticalParams);
        SizeAdjuster.adjustText(this, mRadioRightHand, opticalParams);
        SizeAdjuster.adjustText(this, mRadioLeftHand, opticalParams);
        applyColorProfile(profile.colorProfile);

        Adjuster adjuster = new Adjuster(this, profile);
        adjuster.adjustFabSampleSize(mSizeSampleFab, mSizeSampleFabText);
        adjuster.adjustFabSample(mSizeSampleFab, mSizeSampleFabText);


        // color profiles
        int primary2 = ColorCalcV2.getColor(this, ColorProfileKeyV2.PRIMARY_COLOR_2, profile.colorProfile);
        ColorAdjusterV2.setTextViewDrawableColor(mHandInitialContainer, primary2);
        ColorAdjusterV2.setTextViewDrawableColor(mSizeInitialContainer, primary2);
        ColorAdjusterV2.setTextViewDrawableColor(mColorInitialContainer, primary2);

        int primaryGrey1 = ColorCalcV2.getColor(this, ColorProfileKeyV2.PRIMARY_GREY_1,
                profile.colorProfile);

        int secondaryGrey2 = ColorCalcV2.getColor(this, ColorProfileKeyV2.SECONDARY_GREY_2,
                profile.colorProfile);

        mUsabilitySettingsCard.setCardBackgroundColor(primaryGrey1);
        mUsabilitySettingsCardInner.setBackgroundColor(secondaryGrey2);
    }

    private void loadProfiles() {
        // read size profile
        String sizeProfileStr = SettingsManager.readSetting(SettingsActivityBase.this,
                PreferenceKey.OPTICAL_SIZE_PROFILE, SizeProfile.AUTO.name());
        SizeProfile sizeProfile = SizeProfile.valueOf(sizeProfileStr);

        switch (sizeProfile) {
            case AUTO:
                mRadioSizeAuto.setChecked(true);
                break;
            case ADVANCED:
                mRadioSizeAdvanced.setChecked(true);
                break;
            case INTERMEDIATE:
                mRadioSizeIntermediate.setChecked(true);
                break;
            case SIMPLE:
                mRadioSizeSimple.setChecked(true);
                break;
        }

        // read color profile
        String colorProfileStr = SettingsManager.readSetting(SettingsActivityBase.this,
                PreferenceKey.COLOR_PROFILE, ColorProfile.AUTO.name());
        ColorProfile colorProfile = ColorProfile.valueOf(colorProfileStr);
        selectColorProfile(colorProfile);

        if (!mUserProfileContainer.hasKatsunaServices()) {
            boolean isRightHanded = Boolean.parseBoolean(
                    SettingsManager.readSetting(SettingsActivityBase.this, PreferenceKey.RIGHT_HAND,
                            "true"));
            mRadioRightHand.setChecked(isRightHanded);
            mRadioLeftHand.setChecked(!isRightHanded);
        }
    }

    private void initAppSettings() {
        initHandSetting();
        initSizeSetting();
        initColorSetting();
    }

    private void initHandSetting() {

        mHandInitialContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandControlsExpanded = !mHandControlsExpanded;
                mSizeControlsExpanded = false;
                mColorControlsExpanded = false;
                refreshControlsVisibility();
                focusOnView(mHandInitialContainer);
            }
        });

        mHandExpandedContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                boolean isRightHand = true;
                if (checkedId == R.id.radio_left_hand) {
                    isRightHand = false;
                }
                SettingsManager.setSetting(SettingsActivityBase.this, PreferenceKey.RIGHT_HAND,
                        String.valueOf(isRightHand));
            }
        });
    }

    private void initSizeSetting() {
        mSizeInitialContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandControlsExpanded = false;
                mSizeControlsExpanded = !mSizeControlsExpanded;
                mColorControlsExpanded = false;
                refreshControlsVisibility();
                focusOnView(mSizeExpandedContainer);
            }
        });

        mRadioSizeAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!resumed) return;

                if (isChecked) {
                    sizeRadioAutoChangeInProgress = true;
                    mRadioGroupSize.clearCheck();
                    SettingsManager.setSetting(SettingsActivityBase.this,
                            PreferenceKey.OPTICAL_SIZE_PROFILE, SizeProfile.AUTO.name());
                    applyProfiles();
                    sizeRadioAutoChangeInProgress = false;
                }
            }
        });

        mRadioGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                // no reason to run listener
                if (sizeRadioAutoChangeInProgress) return;

                if (!resumed) return;

                SizeProfile sizeProfile = null;
                if (checkedId == R.id.radio_size_advanced) {
                    sizeProfile = SizeProfile.ADVANCED;
                } else if (checkedId == R.id.radio_size_intermediate) {
                    sizeProfile = SizeProfile.INTERMEDIATE;
                } else if (checkedId == R.id.radio_size_simple) {
                    sizeProfile = SizeProfile.SIMPLE;
                }
                if (sizeProfile != null) {
                    mRadioSizeAuto.setChecked(false);
                    SettingsManager.setSetting(SettingsActivityBase.this,
                            PreferenceKey.OPTICAL_SIZE_PROFILE, sizeProfile.name());
                    applyProfiles();
                }
            }
        });
    }

    private void initColorSetting() {
        mColorInitialContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandControlsExpanded = false;
                mSizeControlsExpanded = false;
                mColorControlsExpanded = !mColorControlsExpanded;
                refreshControlsVisibility();
                focusOnView(mColorExpandedContainer);
            }
        });

        initDemoColorProfiles();
    }

    private void initDemoColorProfiles() {
        mRadioColorAuto = (RadioButton) findViewById(R.id.radio_color_auto);
        mRadioColorAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!resumed) return;

                if (isChecked) {
                    selectColorProfile(ColorProfile.AUTO);
                    updateColorProfile(ColorProfile.AUTO);
                }
            }
        });

        mDemoProfileImpairement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectColorProfile(ColorProfile.COLOR_IMPAIREMENT);
                updateColorProfile(ColorProfile.COLOR_IMPAIREMENT);
            }
        });
        mDemoProfileMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectColorProfile(ColorProfile.MAIN);
                updateColorProfile(ColorProfile.MAIN);
            }
        });
        mDemoProfileContrastImpairement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectColorProfile(ColorProfile.COLOR_IMPAIRMENT_AND_CONTRAST);
                updateColorProfile(ColorProfile.COLOR_IMPAIRMENT_AND_CONTRAST);
            }
        });
        mDemoProfileContrast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectColorProfile(ColorProfile.CONTRAST);
                updateColorProfile(ColorProfile.CONTRAST);
            }
        });
    }

    private void selectColorProfile(ColorProfile colorProfile) {
        mRadioColorAuto.setChecked(false);
        mDemoProfileMain.select(false);
        mDemoProfileContrast.select(false);
        mDemoProfileImpairement.select(false);
        mDemoProfileContrastImpairement.select(false);

        switch (colorProfile) {
            case AUTO:
                mRadioColorAuto.setChecked(true);
                break;
            case MAIN:
                mDemoProfileMain.select(true);
                break;
            case CONTRAST:
                mDemoProfileContrast.select(true);
                break;
            case COLOR_IMPAIREMENT:
                mDemoProfileImpairement.select(true);
                break;
            case COLOR_IMPAIRMENT_AND_CONTRAST:
                mDemoProfileContrastImpairement.select(true);
                break;
        }
    }

    private void updateColorProfile(ColorProfile colorProfile) {
        SettingsManager.setSetting(SettingsActivityBase.this, PreferenceKey.COLOR_PROFILE,
                colorProfile.name());
        applyColorProfile(colorProfile);
        applyProfiles();
    }

    public void refreshControlsVisibility() {
        if (mHandControlsExpanded) {
            mHandExpandedContainer.setVisibility(View.VISIBLE);
            mHandDivider.setVisibility(View.GONE);
        } else {
            mHandExpandedContainer.setVisibility(View.GONE);
            mHandDivider.setVisibility(View.VISIBLE);
        }

        if (mSizeControlsExpanded) {
            mSizeExpandedContainer.setVisibility(View.VISIBLE);
        } else {
            mSizeExpandedContainer.setVisibility(View.GONE);
        }

        if (mColorControlsExpanded) {
            mColorExpandedContainer.setVisibility(View.VISIBLE);
        } else {
            mColorExpandedContainer.setVisibility(View.GONE);
        }

    }

    protected abstract void applyColorProfile(ColorProfile colorProfile);

    protected abstract void applySizeProfile(SizeProfile sizeProfile);

    private void focusOnView(final View target) {
        target.post(new Runnable() {
            @Override
            public void run() {
                ViewUtils.verticalScrollToView(SettingsActivityBase.this, mScrollViewContainer,
                        target);
            }
        });
    }
}
