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
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.katsuna.commons.R;
import com.katsuna.commons.entities.UserProfile;

import java.util.ArrayList;
import java.text.DateFormatSymbols;
import java.util.List;

public class AlertUtils {

    public static void createListAlert(final Context context, final EditText editText,
                                       final String suggestedValue,
                                       final String title, final UserProfile profile,
                                       final List<String> items,
                                       final List<String> itemsLabels,
                                       final Callback callback) {
        //disable manual editing
        editText.setFocusable(false);
        editText.setLongClickable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KatsunaAlertBuilder builder = new KatsunaAlertBuilder(context);
                builder.setTitle(title);
                builder.setView(R.layout.common_katsuna_alert_list);
                builder.setUserProfile(profile);
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
                builder.setScrollViewItemsLabels(itemsLabels);

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

    public static List<String> getMonthsLabels() {
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(new DateFormatSymbols().getMonths()[i-1]);
        }
        return months;
    }

    public static List<String> getYears() {
        int thisYear = 2017;

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
