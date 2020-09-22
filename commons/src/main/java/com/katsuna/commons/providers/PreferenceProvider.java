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
package com.katsuna.commons.providers;

import android.net.Uri;

public class PreferenceProvider {
    public static final String AUTHORITY = "com.katsuna.services.datastore.providers.PreferenceProvider";
    public static final String SCHEME = "content://";

    public static final String PREFERENCE = SCHEME + AUTHORITY + "/preference";
    public static final Uri URI_PREFERENCE = Uri.parse(PREFERENCE);
}
