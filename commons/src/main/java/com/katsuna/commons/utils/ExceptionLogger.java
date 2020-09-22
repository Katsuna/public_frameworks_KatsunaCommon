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

import android.content.ContentValues;
import android.content.Context;

import org.joda.time.DateTime;

import com.katsuna.commons.entities.KatsounaError;
import com.katsuna.commons.providers.ErrorProvider;

public class ExceptionLogger {

    public static void save(Context context, String application, String message) {
        ContentValues values = new ContentValues();
        values.put(KatsounaError.COL_TIME, new DateTime().toString());
        values.put(KatsounaError.COL_APPLICATION, application);
        values.put(KatsounaError.COL_MESSAGE, message);

        try {
            context.getContentResolver().insert(ErrorProvider.URI_ERRORS, values);
        } catch (Exception ex) {
            Log.w(ExceptionLogger.class, ex.getMessage());
        }
    }
}
