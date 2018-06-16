package com.katsuna.commons.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.katsuna.commons.controls.KatsunaButton;
import com.katsuna.commons.controls.KatsunaEditText;
import com.katsuna.commons.controls.KatsunaImageView;
import com.katsuna.commons.controls.KatsunaTextView;
import com.katsuna.commons.controls.KatsunaToggleButton;
import com.katsuna.commons.entities.OpticalParams;
import com.katsuna.commons.entities.SizeProfile;
import com.katsuna.commons.entities.SizeProfileKey;
import com.katsuna.commons.entities.UserProfile;

import java.util.List;

public class SizeAdjuster extends KatsunaAdjuster {

    public static void adjustText(Context context, TextView textView, OpticalParams opticalParams) {
        // set text size
        if (opticalParams.getTextSize() != 0) {
            float textSize = context.getResources().getDimension(opticalParams.getTextSize());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        // set font family
        if (opticalParams.getTextFontFamily() != null) {
            textView.setTypeface(Typeface.create(opticalParams.getTextFontFamily(), Typeface.NORMAL));
        }

        if (opticalParams.getHeight() != 0) {
            int height = context.getResources().getDimensionPixelSize(opticalParams.getHeight());
            textView.setMinHeight(height);
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

    public static void adjustButtonContainer(Context context, View container, OpticalParams opticalParams) {
        if (opticalParams.getHeight() != 0) {
            int height = context.getResources().getDimensionPixelSize(opticalParams.getHeight());

            ViewGroup.LayoutParams buttonLayoutParams = container.getLayoutParams();
            buttonLayoutParams.height = height;
            container.setLayoutParams(buttonLayoutParams);
        }
    }

    public static void adjustIcon(Context context, View icon, OpticalParams opticalParams) {
        if (opticalParams.getHeight() != 0) {
            int height = context.getResources().getDimensionPixelSize(opticalParams.getHeight());

            ViewGroup.LayoutParams lp = icon.getLayoutParams();
            lp.width = height;
            lp.height = height;
            icon.setLayoutParams(lp);
        }
    }
    public static void applySizeProfile(Context context, ViewGroup viewGroup, UserProfile profile) {
        applySizeProfile(context, viewGroup, profile.opticalSizeProfile);
    }

    public static void applySizeProfile(Context context, ViewGroup viewGroup, SizeProfile profile) {
        // adjust all katsuna icons
        OpticalParams iconParams = SizeCalc.getOpticalParams(SizeProfileKey.ICON,
                profile);

        List<KatsunaImageView> katsunaImageViews = getKatsunaImageViews(viewGroup);
        for (View v : katsunaImageViews) {
            SizeAdjuster.adjustIcon(context, v, iconParams);
        }

        // adjust all text views
        List<KatsunaTextView> katsunaTextViews = getKatsunaTextViews(viewGroup);
        for (KatsunaTextView tv : katsunaTextViews) {
            SizeProfileKey key = tv.getSizeProfileKey();
            if (key == null) continue;
            OpticalParams tvParams = SizeCalc.getOpticalParams(key, profile);
            SizeAdjuster.adjustText(context, tv, tvParams);
        }

        // adjust all buttons
        List<KatsunaButton> buttons = getKatsunaButtons(viewGroup);
        for (KatsunaButton button : buttons) {
            SizeProfileKey key = button.getSizeProfileKey();
            if (key == null) continue;
            OpticalParams buttonParams = SizeCalc.getOpticalParams(key, profile);
            SizeAdjuster.adjustText(context, button, buttonParams);
        }

        // adjust all toggle buttons
        List<KatsunaToggleButton> toggleButtons = getKatsunaToggleButtons(viewGroup);
        for (KatsunaToggleButton button : toggleButtons) {
            SizeProfileKey key = button.getSizeProfileKey();
            if (key == null) continue;
            OpticalParams tbParams = SizeCalc.getOpticalParams(key, profile);
            SizeAdjuster.adjustText(context, button, tbParams);
        }

        // adjust all editTexts
        List<KatsunaEditText> editTexts = getKatsunaEditTexts(viewGroup);
        for (KatsunaEditText editText : editTexts) {
            SizeProfileKey key = editText.getSizeProfileKey();
            if (key == null) continue;
            OpticalParams editTextParams = SizeCalc.getOpticalParams(key, profile);
            SizeAdjuster.adjustText(context, editText, editTextParams);
        }
    }
}
