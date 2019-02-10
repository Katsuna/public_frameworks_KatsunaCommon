package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextClock;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.SizeProfileKeyV2;

public class KatsunaTextClock extends TextClock implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;
    private final SizeProfileKeyV2 mSizeProfileKeyV2;

    public KatsunaTextClock(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaTextView);
        mSizeProfileEnabled = array.getBoolean(R.styleable.KatsunaTextView_sizeProfileEnabled, true);
        int sizeProfileKeyV2Index = array.getInt(R.styleable.KatsunaTextView_sizeProfileKeyV2, -1);
        mSizeProfileKeyV2 = SizeProfileKeyV2.fromInteger(sizeProfileKeyV2Index);

        array.recycle();
    }

    @Override
    public boolean isSizeProfileEnabled() {
        return mSizeProfileEnabled;
    }

    public SizeProfileKeyV2 getSizeProfileKeyV2() {
        return mSizeProfileKeyV2;
    }
}
