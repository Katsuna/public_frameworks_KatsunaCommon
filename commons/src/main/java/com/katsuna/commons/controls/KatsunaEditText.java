package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.SizeProfileKey;

public class KatsunaEditText extends EditText implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;
    private final SizeProfileKey mSizeProfileKey;

    public KatsunaEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaImageView);
        mSizeProfileEnabled = array.getBoolean(R.styleable.KatsunaEditText_sizeProfileEnabled, true);
        int sizeProfileKeyIndex = array.getInt(R.styleable.KatsunaEditText_sizeProfileKey, 0);
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
