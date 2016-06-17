package gr.crystalogic.commons.utils;

import android.content.ContentValues;
import android.content.Context;

import org.joda.time.DateTime;

import gr.crystalogic.commons.entities.KatsounaError;
import gr.crystalogic.commons.providers.ErrorProvider;

public class ExceptionLogger {

    public static void save(Context context, String application, String message) {
        ContentValues values = new ContentValues();
        values.put(KatsounaError.COL_TIME, new DateTime().toString());
        values.put(KatsounaError.COL_APPLICATION, application);
        values.put(KatsounaError.COL_MESSAGE, message);

        context.getContentResolver().insert(ErrorProvider.URI_ERRORS, values);
    }
}
