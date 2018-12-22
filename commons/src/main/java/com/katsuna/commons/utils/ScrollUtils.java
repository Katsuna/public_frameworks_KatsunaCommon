package com.katsuna.commons.utils;

import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;

public class ScrollUtils {

    public static void focusOnView(final ScrollView scroll, final View v,
                                   final ScrollVertical vertical) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                int y = 0;

                switch (vertical) {
                    case TOP:
                        y = v.getTop();
                        break;
                    case CENTER:
                        y = ((v.getTop() + v.getBottom() - scroll.getHeight()) / 2);
                        break;
                    case BOTTOM:
                        y = v.getBottom();
                        break;
                }
                scroll.smoothScrollTo(0, y);
            }
        });
    }
}