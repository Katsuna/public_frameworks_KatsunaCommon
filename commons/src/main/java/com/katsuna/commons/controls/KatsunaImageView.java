package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfileKeyV2;

public class KatsunaImageView extends AppCompatImageView implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;
    private ColorProfileKeyV2 mColorProfileKeyV2;

    public KatsunaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaImageView);
        mSizeProfileEnabled = array.getBoolean(R.styleable.KatsunaImageView_sizeProfileEnabled, true);
        int i = array.getInt(R.styleable.KatsunaImageView_colorProfileKey, -1);
        mColorProfileKeyV2 = ColorProfileKeyV2.fromId(i);

        array.recycle();
    }

    @Override
    public boolean isSizeProfileEnabled() {
        return mSizeProfileEnabled;
    }

    public ColorProfileKeyV2 getColorProfileKeyV2() {
        return mColorProfileKeyV2;
    }
}
