/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.katsuna.commons.utils;

import android.view.View;
import android.view.ViewGroup;

import com.katsuna.commons.controls.KatsunaButton;
import com.katsuna.commons.controls.KatsunaEditText;
import com.katsuna.commons.controls.KatsunaImageView;
import com.katsuna.commons.controls.KatsunaTextClock;
import com.katsuna.commons.controls.KatsunaTextView;
import com.katsuna.commons.controls.KatsunaToggleButton;

import java.util.ArrayList;
import java.util.List;

public abstract class KatsunaAdjuster {

    protected static List<KatsunaImageView> getKatsunaImageViews(ViewGroup viewGroup) {
        ArrayList<KatsunaImageView> views = new ArrayList<>();

        final int childcount = viewGroup.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                views.addAll(getKatsunaImageViews((ViewGroup) v));
            } else if (v instanceof KatsunaImageView) {
                if (((KatsunaImageView) v).isSizeProfileEnabled()) {
                    views.add((KatsunaImageView) v);
                }
            }
        }
        return views;
    }

    protected static List<KatsunaTextView> getKatsunaTextViews(ViewGroup viewGroup) {
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

    protected static List<KatsunaTextClock> getKatsunaTextClocks(ViewGroup viewGroup) {
        ArrayList<KatsunaTextClock> views = new ArrayList<>();

        final int childcount = viewGroup.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                views.addAll(getKatsunaTextClocks((ViewGroup) v));
            } else if (v instanceof KatsunaTextClock) {
                KatsunaTextClock katsunaTextClock = (KatsunaTextClock) v;
                if (katsunaTextClock.isSizeProfileEnabled()) {
                    views.add(katsunaTextClock);
                }
            }
        }
        return views;
    }

    protected static List<KatsunaButton> getKatsunaButtons(ViewGroup viewGroup) {
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

    protected static List<KatsunaToggleButton> getKatsunaToggleButtons(ViewGroup viewGroup) {
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

    protected static List<KatsunaEditText> getKatsunaEditTexts(ViewGroup viewGroup) {
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
