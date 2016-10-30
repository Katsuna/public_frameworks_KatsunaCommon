package com.katsuna.commons.utils;

import android.text.format.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;

/**
 * Created by alkis on 30/10/2016.
 */

public class DateFormatter {

    /**
     * Returns relative localized description of the time provided.
     * Time must be no more than 7 days ago to return a value like Today, Yesterday, 2 days ago etc.
     */
    public static String getRelativeDay(long time) {
        return DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS).toString();
    }

    public static String format(long date) {
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