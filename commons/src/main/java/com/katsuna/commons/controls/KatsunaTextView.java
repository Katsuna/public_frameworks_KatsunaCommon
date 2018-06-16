package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.SizeProfileKey;

public class KatsunaTextView extends AppCompatTextView implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;
    private final SizeProfileKey mSizeProfileKey;

    public KatsunaTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaTextView);
        mSizeProfileEnabled = array.getBoolean(R.styleable.KatsunaTextView_sizeProfileEnabled, true);
        int sizeProfileKeyIndex = array.getInt(R.styleable.KatsunaTextView_sizeProfileKey, -1);
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
