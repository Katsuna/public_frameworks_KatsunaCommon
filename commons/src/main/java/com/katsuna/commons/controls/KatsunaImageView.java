package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.katsuna.commons.R;

public class KatsunaImageView extends ImageView implements IKatsunaControl {

    private final boolean mSizeProfileEnabled;

    public KatsunaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaImageView);
        mSizeProfileEnabled = array.getBoolean(R.styleable.KatsunaImageView_sizeProfileEnabled, true);

        array.recycle();
    }

    @Override
    public boolean isSizeProfileEnabled() {
        return mSizeProfileEnabled;
    }
}
