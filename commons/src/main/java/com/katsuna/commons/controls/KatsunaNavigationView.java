package com.katsuna.commons.controls;

import android.content.Context;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.katsuna.commons.R;

public class KatsunaNavigationView extends FrameLayout {
    private final NavigationView mNavigationView;

    public KatsunaNavigationView(Context context) {
        this(context, null);
    }

    public KatsunaNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KatsunaNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.common_navigation_view, this);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        removeOverScroll();
    }

    public void setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener listener) {
        mNavigationView.setNavigationItemSelectedListener(listener);
    }

    private void removeOverScroll() {
        NavigationMenuView navigationMenuView = findNavigationMenuView();

        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
    }

    private NavigationMenuView findNavigationMenuView() {
        for (int i = 0; i < mNavigationView.getChildCount(); i++) {
            if (mNavigationView.getChildAt(i) instanceof NavigationMenuView) {
                return (NavigationMenuView) mNavigationView.getChildAt(i);
            }
        }

        return null;
    }
}
