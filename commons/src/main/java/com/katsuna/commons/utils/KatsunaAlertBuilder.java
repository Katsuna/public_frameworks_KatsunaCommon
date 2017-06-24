package com.katsuna.commons.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.UserProfileContainer;

import java.util.List;

public class KatsunaAlertBuilder {
    private final Context mContext;
    private View mView;
    private int mTitleResId;
    private int mMessageResId;
    private ColorProfile mColorProfile;
    private boolean mCancelHidden;
    private View.OnClickListener mCancelListener;
    private View.OnClickListener mOkListener;
    private KatsunaAlertText mTextSelected;
    private Button mCancelButton;
    private Button mOkButton;
    private List<String> mScrollViewItems;
    private EditText mText;

    public KatsunaAlertBuilder(Context context) {
        mContext = context;
    }

    public void setView(int layoutId) {
        mView = LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    public void setTitle(int titleResId) {
        mTitleResId = titleResId;
    }

    public void setMessage(int messageResId) {
        mMessageResId = messageResId;
    }

    public void setOkListener(View.OnClickListener okListener) {
        mOkListener = okListener;
    }

    public void setTextSelected(KatsunaAlertText textSelected) {
        mTextSelected = textSelected;
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        mCancelListener = cancelListener;
    }

    public void setUserProfileContainer(UserProfileContainer userProfileContainer) {
        setColorProfile(userProfileContainer.getColorProfile());
    }

    public AlertDialog create() {
        final AlertDialog dialog;
        if (mMessageResId != 0) {
            dialog = new AlertDialog.Builder(mContext)
                    .setTitle(mTitleResId)
                    .setMessage(mMessageResId)
                    .setView(mView).create();
        } else {
            dialog = new AlertDialog.Builder(mContext)
                    .setTitle(mTitleResId)
                    .setView(mView).create();
        }

        mCancelButton = (Button) mView.findViewById(R.id.alert_cancel_button);
        mCancelButton.setText(android.R.string.cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancelListener != null) {
                    mCancelListener.onClick(v);
                }
                dialog.dismiss();
            }
        });

        if (mCancelHidden) {
            mCancelButton.setVisibility(View.INVISIBLE);
        }

        mText = (EditText) mView.findViewById(R.id.text_input);

        if (mText != null) {
            mText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        Window window = dialog.getWindow();
                        if (window != null)
                            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                }
            });
        }

        mOkButton = (Button) mView.findViewById(R.id.alert_ok_button);
        mOkButton.setText(android.R.string.ok);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOkListener != null) {
                    mOkListener.onClick(v);
                }
                if (mTextSelected != null) {
                    mTextSelected.textSelected(mText.getText().toString());
                }
                dialog.dismiss();
            }
        });

        if (mScrollViewItems != null) {
            // populate scroll view
            LinearLayout scrollViewItemsContainer =
                    (LinearLayout) mView.findViewById(R.id.scroll_view_items_container);

            int scrollItemsPaddingInPixel = mContext.getResources()
                    .getDimensionPixelSize(R.dimen.common_alert_scroll_items_padding);

            for (String item : mScrollViewItems) {
                TextView tv = new TextView(mContext);
                tv.setText(item);
                tv.setPadding(scrollItemsPaddingInPixel * 2, scrollItemsPaddingInPixel,
                        scrollItemsPaddingInPixel * 2, scrollItemsPaddingInPixel);

                scrollViewItemsContainer.addView(tv);
            }

            // show scroll view
            View scrollViewContainer = mView.findViewById(R.id.scroll_view_container);
            scrollViewContainer.setVisibility(View.VISIBLE);
        }

        adjustProfile();

        return dialog;
    }

    private void adjustProfile() {
        if (mColorProfile == null) {
            return;
        }
        ColorAdjuster.adjustButtons(mContext, mColorProfile, mOkButton, mCancelButton);
    }

    public void setScrollViewItems(List<String> scrollViewItems) {
        mScrollViewItems = scrollViewItems;
    }

    public void setColorProfile(ColorProfile colorProfile) {
        mColorProfile = colorProfile;
    }

    public void setCancelHidden(boolean cancelHidden) {
        mCancelHidden = cancelHidden;
    }

    public interface KatsunaAlertText {
        void textSelected(String input);
    }
}
