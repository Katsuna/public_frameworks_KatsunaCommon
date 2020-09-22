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
package com.katsuna.commons.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
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
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.KatsunaApp;
import com.katsuna.commons.entities.OpticalParams;
import com.katsuna.commons.entities.SizeProfileKeyV2;
import com.katsuna.commons.entities.UserProfile;

import java.util.List;

public class KatsunaAlertBuilder {
    private final Context mContext;
    private View mView;
    private String mMessage;
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
    private String mSelectedItem;
    private KatsunaApp mKatsunaApp;
    private ImageView mAppIcon;
    private TextView mAppTitle;
    private TextView mAppDesc;
    private Integer mTextVisibility;
    private Integer mTextInputType;
    private String mTitle;
    private UserProfile mUserProfile;
    private TextView mKatsunaMessage;
    private TextView mKatsunaTitle;
    private String mTextInput;

    private String mCancelButtonText;
    private String mOkButtonText;

    public KatsunaAlertBuilder(Context context) {
        mContext = context;
    }

    public void setView(int layoutId) {
        mView = LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    public void setMessage(String message) {
        mMessage = message;
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

    public AlertDialog create() {
        final AlertDialog dialog;
        dialog = new AlertDialog.Builder(mContext).setView(mView).create();

        mCancelButton = (Button) mView.findViewById(R.id.alert_cancel_button);
        if (mCancelButton != null) {
            if (mCancelButtonText != null) {
                mCancelButton.setText(mCancelButtonText);
            } else {
                mCancelButton.setText(android.R.string.cancel);
            }

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
        mKatsunaTitle = (TextView) mView.findViewById(R.id.katsuna_title);
        if (mKatsunaTitle != null) {
            mKatsunaTitle.setText(mTitle);
        }
        mKatsunaMessage = (TextView) mView.findViewById(R.id.katsuna_message);
        if (mKatsunaMessage != null) {
            if (TextUtils.isEmpty(mMessage)) {
                mKatsunaMessage.setVisibility(View.GONE);
            } else {
                mKatsunaMessage.setText(mMessage);
                mKatsunaMessage.setVisibility(View.VISIBLE);
            }
        }

        if (mText != null) {
            if (mTextVisibility != null) {
                mText.setVisibility(mTextVisibility);
            }
            if (mTextInputType != null) {
                mText.setInputType(mTextInputType);
            }
            if (mTextInput != null) {
                mText.setText(mTextInput);
            }
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

                boolean selectTv = false;
                if (item.equals(mSelectedItem)) {
                    selectTv = true;
                } else {
                      if (mScrollViewItemsLabels != null
                              && mScrollViewItemsLabels.size() > itemIndex)  {
                          if (mScrollViewItemsLabels.get(itemIndex).equals(mSelectedItem)) {
                              selectTv = true;
                          }
                      }
                }

                if (selectTv) {
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
        dialog = new AlertDialog.Builder(mContext).setView(mView).create();

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

        if (mUserProfile != null) {
            ColorAdjusterV2.adjustButtons(mContext, mUserProfile, mOkButton, mCancelButton);
        }

        return dialog;
    }

    private void adjustProfile() {
        if (mUserProfile == null) {
            return;
        }
        ColorAdjusterV2.adjustButtons(mContext, mUserProfile, mOkButton, mCancelButton);
        int bgColor = ColorCalcV2.getColor(mContext, ColorProfileKeyV2.SECONDARY_COLOR_1,
                mUserProfile.colorProfile);
        mView.setBackgroundColor(bgColor);

        if (mKatsunaTitle != null) {
            OpticalParams opticalParams = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.SUBHEADING_2,
                    mUserProfile.opticalSizeProfile);
            SizeAdjuster.adjustText(mContext, mKatsunaTitle, opticalParams);
        }

        if (mKatsunaMessage != null) {
            OpticalParams opticalParams = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.SUBHEADING_1,
                    mUserProfile.opticalSizeProfile);
            SizeAdjuster.adjustText(mContext, mKatsunaMessage, opticalParams);
        }

        if (mOkButton != null) {
            OpticalParams opticalParams = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.BUTTON,
                    mUserProfile.opticalSizeProfile);
            SizeAdjuster.adjustText(mContext, mOkButton, opticalParams);
        }

        if (mCancelButton != null) {
            OpticalParams opticalParams = SizeCalcV2.getOpticalParams(SizeProfileKeyV2.BUTTON,
                    mUserProfile.opticalSizeProfile);
            SizeAdjuster.adjustText(mContext, mCancelButton, opticalParams);
        }
    }

    public void setScrollViewItems(List<String> scrollViewItems) {
        mScrollViewItems = scrollViewItems;
    }

    public void setScrollViewItemsLabels(List<String> scrollViewItemsLabels) {
        mScrollViewItemsLabels = scrollViewItemsLabels;
    }

    public void setUserProfile(UserProfile userProfile) {
        mUserProfile = userProfile;
    }

    public void setCancelHidden(boolean cancelHidden) {
        mCancelHidden = cancelHidden;
    }

    public void setKatsunaApp(KatsunaApp katsunaApp) {
        mKatsunaApp = katsunaApp;
    }

    public void setTextVisibility(int visibility) {
        mTextVisibility = visibility;
    }

    public void setTextInputType(Integer inputType) {
        mTextInputType = inputType;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setText(String text) {
        mTextInput = text;
    }

    public void setCancelButtonText(String cancelButtonText) {
        mCancelButtonText = cancelButtonText;
    }

    public interface KatsunaAlertText {
        void textSelected(String input);
    }

    public interface KatsunaAlertList {
        void listItemSelected(String input);
    }
}
