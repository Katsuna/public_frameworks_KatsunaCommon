package com.katsuna.commons.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Size;
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

import java.util.ArrayList;
import java.util.List;

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

        List<View> katsunaImageViews = getKatsunaImageViews(viewGroup);
        for (View v : katsunaImageViews) {
            SizeAdjuster.adjustIcon(context, v, iconParams);
        }

        // adjust all text views
        List<KatsunaTextView> katsunaTextViews = getKatsunaTextViews(viewGroup);
        for (KatsunaTextView tv : katsunaTextViews) {
            OpticalParams tvParams = SizeCalc.getOpticalParams(
                    tv.getSizeProfileKey(), profile);
            SizeAdjuster.adjustText(context, tv, tvParams);
        }

        // adjust all buttons
        List<KatsunaButton> buttons = getKatsunaButtons(viewGroup);
        for (KatsunaButton button : buttons) {
            OpticalParams buttonParams = SizeCalc.getOpticalParams(
                    button.getSizeProfileKey(), profile);
            SizeAdjuster.adjustText(context, button, buttonParams);
        }

        // adjust all toggle buttons
        List<KatsunaToggleButton> toggleButtons = getKatsunaToggleButtons(viewGroup);
        for (KatsunaToggleButton button : toggleButtons) {
            OpticalParams tbParams = SizeCalc.getOpticalParams(
                    button.getSizeProfileKey(), profile);
            SizeAdjuster.adjustText(context, button, tbParams);
        }

        // adjust all editTexts
        List<KatsunaEditText> editTexts = getKatsunaEditTexts(viewGroup);
        for (KatsunaEditText editText : editTexts) {
            OpticalParams editTextParams = SizeCalc.getOpticalParams(
                    editText.getSizeProfileKey(), profile);
            SizeAdjuster.adjustText(context, editText, editTextParams);
        }
    }

    private static List<View> getKatsunaImageViews(ViewGroup viewGroup) {
        ArrayList<View> views = new ArrayList<>();

        final int childcount = viewGroup.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                views.addAll(getKatsunaImageViews((ViewGroup) v));
            } else if (v instanceof KatsunaImageView) {
                if (((KatsunaImageView) v).isSizeProfileEnabled()) {
                    views.add(v);
                }
            }
        }
        return views;
    }

    private static List<KatsunaTextView> getKatsunaTextViews(ViewGroup viewGroup) {
        ArrayList<KatsunaTextView> views = new ArrayList<>();

        final int childcount = viewGroup.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                views.addAll(getKatsunaTextViews((ViewGroup) v));
            } else if (v instanceof KatsunaTextView) {
                KatsunaTextView katsunaTextView = (KatsunaTextView) v;
                if (katsunaTextView.isSizeProfileEnabled()) {
                    views.add(katsunaTextView);
                }
            }
        }
        return views;
    }

    private static List<KatsunaButton> getKatsunaButtons(ViewGroup viewGroup) {
        ArrayList<KatsunaButton> views = new ArrayList<>();

        final int childcount = viewGroup.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                views.addAll(getKatsunaButtons((ViewGroup) v));
            } else if (v instanceof KatsunaButton) {
                KatsunaButton button = (KatsunaButton) v;
                if (button.isSizeProfileEnabled()) {
                    views.add(button);
                }
            }
        }
        return views;
    }

    private static List<KatsunaToggleButton> getKatsunaToggleButtons(ViewGroup viewGroup) {
        ArrayList<KatsunaToggleButton> views = new ArrayList<>();

        final int childcount = viewGroup.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                views.addAll(getKatsunaToggleButtons((ViewGroup) v));
            } else if (v instanceof KatsunaToggleButton) {
                KatsunaToggleButton toggleButton = (KatsunaToggleButton) v;
                if (toggleButton.isSizeProfileEnabled()) {
                    views.add(toggleButton);
                }
            }
        }
        return views;
    }

    private static List<KatsunaEditText> getKatsunaEditTexts(ViewGroup viewGroup) {
        ArrayList<KatsunaEditText> views = new ArrayList<>();

        final int childcount = viewGroup.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                views.addAll(getKatsunaEditTexts((ViewGroup) v));
            } else if (v instanceof KatsunaEditText) {
                KatsunaEditText editText = (KatsunaEditText) v;
                if (editText.isSizeProfileEnabled()) {
                    views.add(editText);
                }
            }
        }
        return views;
    }
}
