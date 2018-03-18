package com.katsuna.commons.controls;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.ColorProfileKeyV2;
import com.katsuna.commons.entities.UserProfile;
import com.katsuna.commons.utils.ColorAdjusterV2;
import com.katsuna.commons.utils.ColorCalcV2;
import com.katsuna.commons.utils.DrawableGenerator;

public class DemoProfileV2 extends FrameLayout {

    private boolean mSelected;
    private Button mCallButton;
    private Button mMessageButton;
    private TextView mDisplayName;
    private CardView mContainerCardDemo;
    private View mContainerCardDemoInner;
    private View mBorderCard;
    private ImageView mCallTypeImage;
    private boolean checked;
    private int mTransparentColor;
    private int mPrimary2color;

    public DemoProfileV2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        mContainerCardDemo = (CardView) findViewById(R.id.container_card_demo);
        mBorderCard = findViewById(R.id.border_card);
        mContainerCardDemoInner = findViewById(R.id.container_card_demo_inner);
        mCallTypeImage = (ImageView) findViewById(R.id.call_type_image);

        mCallButton = (Button) findViewById(R.id.call_button);
        mMessageButton = (Button) findViewById(R.id.message_button);
    }

    public void select(boolean enabled) {
        if (mSelected == enabled) return;

        mSelected = enabled;
        if (enabled) {
            mBorderCard.setBackground(getDrawable(mPrimary2color));
        } else {
            mBorderCard.setBackground(getDrawable(mTransparentColor));
        }
    }

    private Drawable getDrawable(int backgroundColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        int  px = getContext().getResources().getDimensionPixelSize(R.dimen.common_8dp);
        shape.setCornerRadii(new float[] { px, px, px, px, px, px, px, px });
        shape.setColor(backgroundColor);
        return shape;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void adjustProfile(UserProfile profile) {
        // calc colors
        mPrimary2color = ColorCalcV2.getColor(getContext(), ColorProfileKeyV2.PRIMARY_COLOR_2,
                profile.colorProfile);
        mTransparentColor = ContextCompat.getColor(getContext(), R.color.common_transparent);

        int cardColor = ColorCalcV2.getColor(getContext(), ColorProfileKeyV2.PRIMARY_COLOR_1,
                profile.colorProfile);
        int cardColorAlpha = ColorCalcV2.getColor(getContext(), ColorProfileKeyV2.SECONDARY_COLOR_1,
                profile.colorProfile);

        mContainerCardDemo.setCardBackgroundColor(ColorStateList.valueOf(cardColor));
        mContainerCardDemoInner.setBackgroundColor(cardColorAlpha);

        // style callTypeDrawable based on call type
        Drawable callTypeDrawable = DrawableGenerator.getCallTypeDrawable(getContext(),
                CallLog.Calls.MISSED_TYPE, profile);
        mCallTypeImage.setImageDrawable(callTypeDrawable);

        ColorAdjusterV2.adjustButtons(getContext(), profile, mCallButton, mMessageButton);
    }

}
