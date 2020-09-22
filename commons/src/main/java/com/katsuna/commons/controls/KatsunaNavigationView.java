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
package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.Menu;
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

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KatsunaNavigationView);
        int menuResId = array.getResourceId(R.styleable.KatsunaNavigationView_customMenu, -1);
        if (menuResId != -1) {
            // clear the default one
            Menu menu = mNavigationView.getMenu();
            if (menu != null) {
                menu.clear();
            }

            // set the custom one
            mNavigationView.inflateMenu(menuResId);
        }

        array.recycle();

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
