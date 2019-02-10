package com.katsuna.commons.utils;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class DateUtils {

    public static boolean sameDate(Calendar cal, Calendar selectedDate) {
        return cal.get(MONTH) == selectedDate.get(MONTH)
            && cal.get(YEAR) == selectedDate.get(YEAR)
            && cal.get(DAY_OF_MONTH) == selectedDate.get(DAY_OF_MONTH);
    }

    public static boolean containsDate(List<Calendar> selectedCals, Calendar cal) {
        if (selectedCals == null) {
            return false;
        }

        for (Calendar selectedCal : selectedCals) {
            if (sameDate(cal, selectedCal)) {
                return true;
            }
        }
        return false;
    }

    public static boolean betweenDates(Date date, Calendar minCal, Calendar maxCal) {
        final Date min = minCal.getTime();
        return (date.equals(min) || date.after(min)) // >= minCal
            && date.before(maxCal.getTime()); // && < maxCal
    }

    public static Calendar minDate(List<Calendar> selectedCals) {
        if (selectedCals == null || selectedCals.size() == 0) {
            return null;
        }
        Collections.sort(selectedCals);
        return selectedCals.get(0);
    }

    public static Calendar maxDate(List<Calendar> selectedCals) {
        if (selectedCals == null || selectedCals.size() == 0) {
            return null;
        }
        Collections.sort(selectedCals);
        return selectedCals.get(selectedCals.size() - 1);
    }

    public static boolean betweenDates(Calendar cal, Calendar minCal, Calendar maxCal) {
        if (minCal == null || maxCal == null) {
            return false;
        }

        final Date date = cal.getTime();
        return betweenDates(date, minCal, maxCal);
    }

}
