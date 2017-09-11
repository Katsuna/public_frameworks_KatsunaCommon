package com.katsuna.commons.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfile;
import com.katsuna.commons.entities.KatsunaApp;
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
    private KatsunaAlertList mListItemSelected;
    private Button mCancelButton;
    private Button mOkButton;
    private List<String> mScrollViewItems;
    private List<String> mScrollViewItemsLabels;

    private EditText mText;
    private boolean mCustomTitleOn;
    private String mSelectedItem;
    private KatsunaApp mKatsunaApp;
    private ImageView mAppIcon;
    private TextView mAppTitle;
    private TextView mAppDesc;

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

    public void setListItemSelected(KatsunaAlertList listItemSelected) {
        mListItemSelected = listItemSelected;
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        mCancelListener = cancelListener;
    }

    public void setSelectedItem(String value) {
        mSelectedItem = value;
    }

    public void setUserProfileContainer(UserProfileContainer userProfileContainer) {
        setColorProfile(userProfileContainer.getColorProfile());
    }

    public AlertDialog create() {
        final AlertDialog dialog;
        if (mMessageResId != 0) {
            dialog = new AlertDialog.Builder(mContext)
                    .setMessage(mMessageResId)
                    .setView(mView).create();
        } else {
            dialog = new AlertDialog.Builder(mContext)
                    .setView(mView).create();
        }

        setTitle(dialog);

        mCancelButton = (Button) mView.findViewById(R.id.alert_cancel_button);
        if (mCancelButton != null) {
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
            int scrollItemsHeightInPixel = mContext.getResources()
                    .getDimensionPixelSize(R.dimen.common_alert_scroll_items_height);

            int i = 1;

            int bgColor = ContextCompat.getColor(mContext, R.color.common_grey100);
            int textColor = ContextCompat.getColor(mContext, R.color.common_black54);
            int textSize = mContext.getResources()
                    .getDimensionPixelSize(R.dimen.common_subheader_text_size_intermediate);

            TextView selectedItem = null;

            int itemIndex = 0;
            for (String item : mScrollViewItems) {

                final TextView tv = new TextView(mContext);

                if (mScrollViewItemsLabels != null) {
                    tv.setText(mScrollViewItemsLabels.get(itemIndex));
                    tv.setTag(item);
                } else {
                    tv.setText(item);
                    tv.setTag(item);
                }
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                tv.setTextColor(textColor);
                tv.setHeight(scrollItemsHeightInPixel);
                tv.setPadding(scrollItemsPaddingInPixel * 3, scrollItemsPaddingInPixel,
                        scrollItemsPaddingInPixel * 3, scrollItemsPaddingInPixel);

                // paint background
                i++;
                if (i % 2 == 0) {
                    tv.setBackgroundColor(bgColor);
                }


                if (mListItemSelected != null) {
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListItemSelected.listItemSelected(tv.getTag().toString());
                            dialog.dismiss();
                        }
                    });
                }

                if (item.equals(mSelectedItem)) {
                    tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
                    selectedItem = tv;
                }

                scrollViewItemsContainer.addView(tv);
                itemIndex++;
            }

            // show scroll view
            final ScrollView scrollViewContainer = (ScrollView) mView.findViewById(R.id.scroll_view_container);
            scrollViewContainer.setVisibility(View.VISIBLE);

            if (selectedItem != null) {
                if (scrollViewContainer != null) {
                    final TextView finalSelectedItem = selectedItem;
                    scrollViewContainer.post(new Runnable() {
                        @Override
                        public void run() {
                            //ViewUtils.verticalScrollToView(mContext, scrollViewContainer, finalSelectedItem);
                            int alertHeight = mContext.getResources()
                                    .getDimensionPixelSize(R.dimen.common_alert_height);
                            scrollViewContainer.scrollTo(0, finalSelectedItem.getBottom()
                                    - alertHeight / 2);
                        }
                    });
                }


                selectedItem.getParent().requestChildFocus(selectedItem, selectedItem);
            }
        }

        adjustProfile();

        return dialog;
    }

    public AlertDialog createKatsunaAppSuggestion() {
        final AlertDialog dialog;
        if (mMessageResId != 0) {
            dialog = new AlertDialog.Builder(mContext)
                    .setMessage(mMessageResId)
                    .setView(mView).create();
        } else {
            dialog = new AlertDialog.Builder(mContext)
                    .setView(mView).create();
        }

        mAppIcon = (ImageView) mView.findViewById(R.id.app_icon);
        mAppIcon.setImageResource(mKatsunaApp.drawableId);

        mAppTitle = (TextView) mView.findViewById(R.id.app_title);

        String title = mView.getResources().getString(R.string.common_download_app,
                mKatsunaApp.title);
        mAppTitle.setText(title);

        mAppDesc = (TextView) mView.findViewById(R.id.app_desc);
        String desc = mView.getResources().getString(R.string.common_katsuna_app_desc,
                mKatsunaApp.title);
        mAppDesc.setText(desc);

        mCancelButton = (Button) mView.findViewById(R.id.alert_cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        mOkButton = (Button) mView.findViewById(R.id.alert_ok_button);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KatsunaUtils.goToGooglePlay(mView.getContext(), mKatsunaApp.packageName);
                dialog.dismiss();
            }
        });

        if (mColorProfile != null) {
            ColorAdjuster.adjustButtons(mContext, mColorProfile, mOkButton, mCancelButton);
        }

        return  dialog;
    }

    public void setCustomTitle(boolean enabled) {
        mCustomTitleOn = enabled;
    }

    private void setTitle(AlertDialog dialog) {
        if (mCustomTitleOn) {
            TextView title = new TextView(mContext);

            int textColor = ContextCompat.getColor(mContext, R.color.common_black87);
            int textSize = mContext.getResources()
                    .getDimensionPixelSize(R.dimen.common_title_text_size_intermediate);
            int padding = mContext.getResources()
                    .getDimensionPixelSize(R.dimen.common_alert_scroll_items_padding);

            title.setText(mTitleResId);
            title.setTextColor(textColor);
            title.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            title.setPadding(padding * 3, padding * 2, padding * 3, padding * 2);
            title.setTypeface(Typeface.create(Constants.SANS_SERIF_MEDIUM, Typeface.NORMAL));

            dialog.setCustomTitle(title);
        } else {
            dialog.setTitle(mTitleResId);
        }
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

    public void setScrollViewItemsLabels(List<String> scrollViewItemsLabels) {
        mScrollViewItemsLabels = scrollViewItemsLabels;
    }

    public void setColorProfile(ColorProfile colorProfile) {
        mColorProfile = colorProfile;
    }

    public void setCancelHidden(boolean cancelHidden) {
        mCancelHidden = cancelHidden;
    }

    public void setKatsunaApp(KatsunaApp katsunaApp) {
        mKatsunaApp = katsunaApp;
    }

    public interface KatsunaAlertText {
        void textSelected(String input);
    }

    public interface KatsunaAlertList {
        void listItemSelected(String input);
    }
}
