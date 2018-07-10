package com.katsuna.commons.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class KeyboardUtils {

    // Inspired from:  https://stackoverflow.com/a/17789187
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = activity.findViewById(android.R.id.content);
        }

        // npe checks
        if (view == null || imm == null) return;

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // Inspired from:  https://stackoverflow.com/a/17789187
    public static void hideKeyboardFrom(Context context, View view) {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        // npe checks
        if (view == null || imm == null) return;

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
