package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.SizeProfileKey;
import com.katsuna.commons.entities.SizeProfileKeyV2;

public class KatsunaButton extends AppCompatButton implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;
    private final SizeProfileKey mSizeProfileKey;
    private final SizeProfileKeyV2 mSizeProfileKeyV2;

    public KatsunaButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaButton);
        mSizeProfileEnabled = array.getBoolean(R.styleable.KatsunaButton_sizeProfileEnabled, true);
        int sizeProfileKeyIndex = array.getInt(R.styleable.KatsunaButton_sizeProfileKey, -1);
        mSizeProfileKey = SizeProfileKey.fromInteger(sizeProfileKeyIndex);
        int sizeProfileKeyV2Index = array.getInt(R.styleable.KatsunaTextView_sizeProfileKeyV2, -1);
        mSizeProfileKeyV2 = SizeProfileKeyV2.fromInteger(sizeProfileKeyV2Index);

        array.recycle();
    }

    @Override
    public boolean isSizeProfileEnabled() {
        return mSizeProfileEnabled;
    }

    public SizeProfileKey getSizeProfileKey() {
        return mSizeProfileKey;
    }

    public SizeProfileKeyV2 getSizeProfileKeyV2() {
        return mSizeProfileKeyV2;
    }
}
