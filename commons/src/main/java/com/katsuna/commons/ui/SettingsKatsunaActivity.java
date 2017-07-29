package com.katsuna.commons.ui;

import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.controls.DemoProfile;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.OpticalParams;
import com.katsuna.commons.entities.PreferenceKey;
import com.katsuna.commons.entities.SizeProfile;
import com.katsuna.commons.entities.SizeProfileKey;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.profile.Adjuster;
import com.katsuna.commons.utils.SettingsManager;
import com.katsuna.commons.utils.SizeAdjuster;
import com.katsuna.commons.utils.SizeCalc;
import com.katsuna.commons.utils.ViewUtils;

public abstract class SettingsKatsunaActivity extends KatsunaActivity {

    protected ScrollView mScrollViewContainer;
    private View mSizeInitialContainer;
    private View mSizeExpandedContainer;
    private boolean mHandControlsExpanded;
    private boolean mSizeControlsExpanded;
    private boolean mColorControlsExpanded;
    private View mColorExpandedContainer;
    private RadioGroup mHandExpandedContainer;
    private View mHandInitialContainer;
    private View mColorInitialContainer;
    private DemoProfile mColorMainDemo;
    private DemoProfile mColorContrastDemo;
    private DemoProfile mColorImpairementDemo;
    private DemoProfile mColorImpairementContrastDemo;
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
            } else {
                mHandInitialContainer.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void showPopup(boolean flag) {
        // no op for settings
    }

    protected void applyProfiles() {
        refreshUserProfileContainer();
        UserProfile profile = mUserProfileContainer.getActiveUserProfile();
        applyProfileLocal(profile);
    }

    private void applyProfileLocal(UserProfile profile) {
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
    }

    protected void loadProfiles() {
        // read size profile
        String sizeProfileStr = SettingsManager.readSetting(SettingsKatsunaActivity.this,
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
        String colorProfileStr = SettingsManager.readSetting(SettingsKatsunaActivity.this,
                PreferenceKey.COLOR_PROFILE, ColorProfile.AUTO.name());
        ColorProfile colorProfile = ColorProfile.valueOf(colorProfileStr);
        selectColorProfile(colorProfile);

        if(!mUserProfileContainer.hasKatsunaServices()) {
            boolean isRightHanded = Boolean.parseBoolean(
                    SettingsManager.readSetting(SettingsKatsunaActivity.this, PreferenceKey.RIGHT_HAND,
                            "true"));
            mRadioRightHand.setChecked(isRightHanded);
            mRadioLeftHand.setChecked(!isRightHanded);
        }
    }

    protected void initAppSettings() {
        initHandSetting();
        initSizeSetting();
        initColorSetting();
    }

    private void initHandSetting() {
        mHandInitialContainer = findViewById(R.id.hand_initial_container);
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
        mRadioRightHand = (RadioButton) findViewById(R.id.radio_right_hand);
        mRadioLeftHand = (RadioButton) findViewById(R.id.radio_left_hand);

        mHandExpandedContainer = (RadioGroup) findViewById(R.id.radio_group_hand);
        mHandExpandedContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                boolean isRightHand = true;
                if (checkedId == R.id.radio_left_hand) {
                    isRightHand = false;
                }
                SettingsManager.setSetting(SettingsKatsunaActivity.this, PreferenceKey.RIGHT_HAND,
                        String.valueOf(isRightHand));
            }
        });
    }

    private void initSizeSetting() {
        mSizeExpandedContainer = findViewById(R.id.size_expanded_container);
        mSizeInitialContainer = findViewById(R.id.size_initial_container);
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
        mRadioSizeAuto = (RadioButton) findViewById(R.id.radio_size_auto);
        mRadioSizeAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sizeRadioAutoChangeInProgress = true;
                    mRadioGroupSize.clearCheck();
                    SettingsManager.setSetting(SettingsKatsunaActivity.this,
                            PreferenceKey.OPTICAL_SIZE_PROFILE, SizeProfile.AUTO.name());
                    applyProfiles();
                    sizeRadioAutoChangeInProgress = false;
                }
            }
        });
        mRadioSizeAdvanced = (RadioButton) findViewById(R.id.radio_size_advanced);
        mRadioSizeIntermediate = (RadioButton) findViewById(R.id.radio_size_intermediate);
        mRadioSizeSimple = (RadioButton) findViewById(R.id.radio_size_simple);
        mRadioGroupSize = (RadioGroup) findViewById(R.id.radio_group_size);
        mRadioGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                // no reason to run listener
                if (sizeRadioAutoChangeInProgress) return;

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
                    SettingsManager.setSetting(SettingsKatsunaActivity.this,
                            PreferenceKey.OPTICAL_SIZE_PROFILE, sizeProfile.name());
                    applyProfiles();
                }
            }
        });


        mSizeSampleFab = findViewById(R.id.commom_size_sample_fab);
        mSizeSampleFabText = (TextView) findViewById(R.id.commom_size_sample_fab_text);
    }

    private void initColorSetting() {
        mColorExpandedContainer = findViewById(R.id.color_expanded_container);
        mColorInitialContainer = findViewById(R.id.color_initial_container);
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
                if (isChecked) {
                    updateColorProfile(ColorProfile.AUTO);
                    selectColorProfile(ColorProfile.AUTO);
                }
            }
        });

        mColorMainDemo = (DemoProfile) findViewById(R.id.demo_color_profile_main);
        mColorMainDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mColorMainDemo.isSelected()) {
                    updateColorProfile(ColorProfile.MAIN);
                    selectColorProfile(ColorProfile.MAIN);
                }
            }
        });
        mColorMainDemo.adjustColorProfile(ColorProfile.MAIN);

        mColorContrastDemo = (DemoProfile) findViewById(R.id.demo_color_profile_contrast);
        mColorContrastDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mColorContrastDemo.isSelected()) {
                    updateColorProfile(ColorProfile.CONTRAST);
                    selectColorProfile(ColorProfile.CONTRAST);
                }
            }
        });
        mColorContrastDemo.adjustColorProfile(ColorProfile.CONTRAST);

        mColorImpairementDemo = (DemoProfile) findViewById(R.id.demo_color_profile_impairement);
        mColorImpairementDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mColorImpairementDemo.isSelected()) {
                    updateColorProfile(ColorProfile.COLOR_IMPAIREMENT);
                    selectColorProfile(ColorProfile.COLOR_IMPAIREMENT);
                }
            }
        });
        mColorImpairementDemo.adjustColorProfile(ColorProfile.COLOR_IMPAIREMENT);

        mColorImpairementContrastDemo =
                (DemoProfile) findViewById(R.id.demo_color_profile_impairement_contrast);
        mColorImpairementContrastDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mColorImpairementContrastDemo.isSelected()) {
                    updateColorProfile(ColorProfile.COLOR_IMPAIRMENT_AND_CONTRAST);
                    selectColorProfile(ColorProfile.COLOR_IMPAIRMENT_AND_CONTRAST);
                }
            }
        });
        mColorImpairementContrastDemo.adjustColorProfile(ColorProfile.COLOR_IMPAIRMENT_AND_CONTRAST);
    }

    private void selectColorProfile(ColorProfile colorProfile) {
        mRadioColorAuto.setChecked(false);
        mColorMainDemo.select(false);
        mColorContrastDemo.select(false);
        mColorImpairementDemo.select(false);
        mColorImpairementContrastDemo.select(false);

        switch (colorProfile) {
            case AUTO:
                mRadioColorAuto.setChecked(true);
                break;
            case MAIN:
                mColorMainDemo.select(true);
                break;
            case CONTRAST:
                mColorContrastDemo.select(true);
                break;
            case COLOR_IMPAIREMENT:
                mColorImpairementDemo.select(true);
                break;
            case COLOR_IMPAIRMENT_AND_CONTRAST:
                mColorImpairementContrastDemo.select(true);
                break;
        }
    }

    private void updateColorProfile(ColorProfile colorProfile) {
        SettingsManager.setSetting(SettingsKatsunaActivity.this, PreferenceKey.COLOR_PROFILE,
                colorProfile.name());
        applyColorProfile(colorProfile);
        applyProfiles();
    }

    public void refreshControlsVisibility() {
        if (mHandControlsExpanded) {
            mHandExpandedContainer.setVisibility(View.VISIBLE);
        } else {
            mHandExpandedContainer.setVisibility(View.GONE);
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
                ViewUtils.verticalScrollToView(SettingsKatsunaActivity.this, mScrollViewContainer,
                        target);
            }
        });
    }
}
