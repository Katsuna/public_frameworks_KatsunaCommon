package com.katsuna.commons.ui;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.katsuna.commons.ui.adapters.TabsPagerAdapter;
import com.katsuna.commons.ui.adapters.models.ContactListItemModel;
import com.katsuna.commons.ui.fragments.support.SearchBarFragment;
import com.katsuna.commons.utils.Constants;
import com.katsuna.commons.utils.ListChopper;
import com.katsuna.commons.utils.Separator;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchBarActivity extends KatsunaActivity
        implements SearchBarFragment.OnFragmentInteractionListener {

    protected Handler mDeselectionActionHandler;
    protected boolean mItemSelected;
    protected long mLastSelectionTimestamp = System.currentTimeMillis();

    @Override
    protected void onResume() {
        super.onResume();

        if (mUserProfileChanged) {
            // color profile adjustments
            mAdjuster.adjustSearchBar(mViewPagerContainer, mPrevButton, mNextButton);

            // right hand adjustments
            mAdjuster.adjustSearchBarForRightHand(mFabToolbarContainer, mFabToolbar);
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

    protected void initDeselectionActionHandler() {
        mDeselectionActionHandler = new Handler();
        mDeselectionActionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                if (now - Constants.SELECTION_THRESHOLD > mLastSelectionTimestamp && mItemSelected) {
                    deselectItem();
                }
                mDeselectionActionHandler.postDelayed(this, Constants.HANDLER_DELAY);
            }
        }, Constants.HANDLER_DELAY);
    }

    protected void refreshLastSelectionTimestamp() {
        mLastSelectionTimestamp = System.currentTimeMillis();
    }

    // Subclasses must implement this.
    protected abstract void deselectItem();

}
