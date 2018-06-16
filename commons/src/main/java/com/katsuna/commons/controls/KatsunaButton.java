package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.SizeProfileKey;

public class KatsunaButton extends AppCompatButton implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;
    private final SizeProfileKey mSizeProfileKey;

    public KatsunaButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaButton);
        mSizeProfileEnabled = array.getBoolean(R.styleable.KatsunaButton_sizeProfileEnabled, true);
        int sizeProfileKeyIndex = array.getInt(R.styleable.KatsunaButton_sizeProfileKey, -1);
        mSizeProfileKey = SizeProfileKey.fromInteger(sizeProfileKeyIndex);

        array.recycle();
    }

    @Override
    public boolean isSizeProfileEnabled() {
        return mSizeProfileEnabled;
    }

    public SizeProfileKey getSizeProfileKey() {
        return mSizeProfileKey;
    }
}
