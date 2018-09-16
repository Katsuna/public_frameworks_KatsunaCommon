package com.katsuna.commons.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.katsuna.commons.R;

public class BrowserUtils {

    public static void openUrl(Context context, String url) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.common_browser_not_found, Toast.LENGTH_LONG).show();

        }
    }

}
