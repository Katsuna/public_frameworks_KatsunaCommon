package com.katsuna.commons.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.katsuna.commons.R;


public class KatsunaInfoActivity extends KatsunaActivity {

    protected final String TAG = "KatsunaInfoActivity";

    protected TextView mAppName;
    protected TextView mAppVersion;
    protected ImageView mAppIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_info_activity);

        mAppName = (TextView) findViewById(R.id.app_name);
        mAppVersion = (TextView) findViewById(R.id.app_version);
        mAppIcon = (ImageView) findViewById(R.id.app_icon);
    }

    @Override
    protected void showPopup(boolean flag) {

    }
}
