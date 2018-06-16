package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ToggleButton;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.SizeProfileKey;

public class KatsunaToggleButton extends ToggleButton implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;
    private final SizeProfileKey mSizeProfileKey;

    public KatsunaToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaToggleButton);
        mSizeProfileEnabled =
                array.getBoolean(R.styleable.KatsunaToggleButton_sizeProfileEnabled, true);
        int sizeProfileKeyIndex =
                array.getInt(R.styleable.KatsunaToggleButton_sizeProfileKey, -1);
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
