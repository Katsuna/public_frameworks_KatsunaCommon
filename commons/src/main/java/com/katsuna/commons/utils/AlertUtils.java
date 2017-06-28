package com.katsuna.commons.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.UserProfile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlertUtils {

    public static void createListAlert(final Context context, final EditText editText,
                                       final String suggestedValue,
                                       final int titleResId, final UserProfile profile,
                                       final List<String> items, final Callback callback) {
        //disable manual editing
        editText.setFocusable(false);
        editText.setLongClickable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KatsunaAlertBuilder builder = new KatsunaAlertBuilder(context);
                builder.setTitle(titleResId);
                builder.setView(R.layout.common_katsuna_alert_list);
                builder.setColorProfile(profile.colorProfile);
                builder.setListItemSelected(new KatsunaAlertBuilder.KatsunaAlertList() {
                    @Override
                    public void listItemSelected(String input) {
                        editText.setText(input);
                        if (callback != null) {
                            callback.onListItemSelected();
                        }
                    }
                });

                builder.setScrollViewItems(items);
                builder.setCustomTitle(true);

                if (suggestedValue != null && TextUtils.isEmpty(editText.getText())) {
                    builder.setSelectedItem(suggestedValue);
                } else {
                    builder.setSelectedItem(editText.getText().toString());
                }

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public static List<String> getDays() {
        List<String> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        return days;
    }

    public static List<String> getMonths() {
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(String.valueOf(i));
        }
        return months;
    }

    public static List<String> getYears() {
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);

        List<String> years = new ArrayList<>();
        for (int i = 0; i <= 150; i++) {
            years.add(String.valueOf(thisYear - i));
        }
        return years;
    }

    public interface Callback {
        void onListItemSelected();
    }

}
