package com.katsuna.commons.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.ColorProfileKey;
import com.katsuna.commons.ui.adapters.TabsPagerAdapter;
import com.katsuna.commons.ui.adapters.models.ContactListItemModel;
import com.katsuna.commons.ui.fragments.SearchBarFragment;
import com.katsuna.commons.utils.ColorCalc;
import com.katsuna.commons.utils.ListChopper;
import com.katsuna.commons.utils.ResourcesUtils;
import com.katsuna.commons.utils.Separator;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchBarActivity extends KatsunaActivity
        implements SearchBarFragment.OnFragmentInteractionListener {

    @Override
    protected void onResume() {
        super.onResume();

        if (mUserProfileChanged) {
            ColorProfile colorProfile = mUserProfileContainer.getColorProfile();
            // color profile adjustments
            adjustSearchBar(colorProfile);

            // right hand adjustments
            adjustSearchToolbarRightHand();
        }
    }

    private void adjustSearchToolbarRightHand() {
        if (mFabToolbarContainer == null || mFabToolbar == null) {
            return;
        }

        int shadowResId = ResourcesUtils.getDimen(this, "common_search_shadow");
        int shadowPixels = getResources().getDimensionPixelSize(shadowResId);
        if (mUserProfileContainer.isRightHanded()) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabToolbarContainer.getLayoutParams();
            lp.gravity = Gravity.END;

            //set shadow properly
            int bgResId = ResourcesUtils.getDrawable(this, "common_search_bar_bg");
            mFabToolbar.setBackground(getDrawable(bgResId));
            mFabToolbar.setPadding(shadowPixels, 0, 0, 0);
        } else {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabToolbarContainer.getLayoutParams();
            lp.gravity = Gravity.START;

            //set shadow properly
            int bgResId = ResourcesUtils.getDrawable(this, "common_search_bar_bg_left_handed");
            mFabToolbar.setBackground(getDrawable(bgResId));
            mFabToolbar.setPadding(0, 0, shadowPixels, 0);
        }
    }

    private void adjustSearchBar(ColorProfile profile) {
        if (mViewPagerContainer != null) {
            int accentColor1 = ColorCalc.getColor(this, ColorProfileKey.ACCENT1_COLOR, profile);
            mViewPagerContainer.setBackgroundColor(accentColor1);
        }
    }

    protected void initializeFabToolbar(List<ContactListItemModel> models) {
        List<String> letters = new ArrayList<>();

        for (ContactListItemModel contactListItemModel : models) {
            if (!contactListItemModel.isPremium()) {
                if (contactListItemModel.getSeparator() == Separator.FIRST_LETTER) {
                    String letter = contactListItemModel.getContact().getFirstLetterNormalized();
                    if (!letters.contains(letter)) {
                        letters.add(letter);
                    }
                }
            }
        }

        List<ArrayList<String>> lettersLists = ListChopper.chopped(letters, 20);

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        for (ArrayList<String> lettersList : lettersLists) {
            fragmentArrayList.add(SearchBarFragment.newInstance(lettersList));
        }

        TabsPagerAdapter mLetterAdapter = new TabsPagerAdapter(getSupportFragmentManager(),
                fragmentArrayList);
        mViewPager.setAdapter(mLetterAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                adjustFabToolbarNavButtonsVisibility();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        adjustFabToolbarNavButtonsVisibility();
    }

    protected void adjustFabToolbarNavButtonsVisibility() {
        int pages = mViewPager.getChildCount();
        int currentPage = mViewPager.getCurrentItem();

        if (pages == currentPage + 1) {
            mNextButton.setVisibility(View.INVISIBLE);
        } else {
            mNextButton.setVisibility(View.VISIBLE);
        }

        if (currentPage == 0) {
            mPrevButton.setVisibility(View.INVISIBLE);
        } else {
            mPrevButton.setVisibility(View.VISIBLE);
        }
    }

}
