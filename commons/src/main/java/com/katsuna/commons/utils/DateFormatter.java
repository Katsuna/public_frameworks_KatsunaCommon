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

import android.text.format.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;

public class DateFormatter {

    public final static String KATSUNA_DATE_FORMAT = "dd/MM/YYYY HH:mm";

    /**
     * Returns relative localized description of the time provided.
     * Time must be no more than 7 days ago to return a value like Today, Yesterday, 2 days ago etc.
     */
    public static String getRelativeDay(long time) {
        return DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS).toString();
    }

    public static String format(long date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(KATSUNA_DATE_FORMAT);
    }

    public static String relativeFormat(long date) {
        DateTime dateTime = new DateTime(date);

        Interval todayInterval = new Interval(DateTime.now().withTimeAtStartOfDay(), Days.ONE);
        Interval yesterdayInterval = new Interval(DateTime.now().withTimeAtStartOfDay().minusDays(1), Days.ONE);

        String output;
        if (todayInterval.contains(dateTime) || yesterdayInterval.contains(dateTime)) {
            output = dateTime.toString("HH:mm ") + getRelativeDay(dateTime.getMillis());
        } else {
            output = dateTime.toString("HH:mm EEEE d-M-yy");
        }

        return output;
    }

}