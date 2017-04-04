package com.katsuna.commons.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.katsuna.commons.entities.OpticalParams;

public class SizeAdjuster {

    public static void adjustText(Context context, TextView textView, OpticalParams opticalParams) {
        // set text size
        if (opticalParams.getTextSize() != 0) {
            float textSize = context.getResources().getDimension(opticalParams.getTextSize());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        // set text color
        if (opticalParams.getTextColor() != 0) {
            int textColor = ContextCompat.getColor(context, opticalParams.getTextColor());
            textView.setTextColor(textColor);
        }

        // set font family
        if (opticalParams.getTextFontFamily() != null) {
            textView.setTypeface(Typeface.create(opticalParams.getTextFontFamily(), Typeface.NORMAL));
        }
    }

    public static void adjustButton(Context context, Button button, OpticalParams opticalParams) {
        if (opticalParams.getHeight() != 0) {
            int height = context.getResources().getDimensionPixelSize(opticalParams.getHeight());

            ViewGroup.LayoutParams buttonLayoutParams = button.getLayoutParams();
            buttonLayoutParams.height = height;
            button.setLayoutParams(buttonLayoutParams);
        }
    }

}
