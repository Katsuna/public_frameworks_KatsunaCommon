package com.katsuna.commons.ui;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.katsuna.commons.ui.adapters.LettersAdapter;
import com.katsuna.commons.ui.adapters.interfaces.LetterListener;
import com.katsuna.commons.ui.adapters.models.ContactListItemModel;
import com.katsuna.commons.utils.Constants;
import com.katsuna.commons.utils.Separator;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchBarActivity extends KatsunaActivity implements LetterListener {

    protected Handler mDeselectionActionHandler;
    protected boolean mItemSelected;
    protected long mLastSelectionTimestamp = System.currentTimeMillis();
    protected RecyclerView mLettersList;

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

        LettersAdapter mLettersAdapter = new LettersAdapter(letters, this);
        mLettersList.setAdapter(mLettersAdapter);
        mLettersList.setLayoutManager(new LinearLayoutManager(this));
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
