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

public final class Constants {

    private Constants() {
        // restrict instantiation
    }

    public static final String DISPLAY_SORT_KEY = "display_sort";
    public static final String DISPLAY_SORT_SURNAME = "1";
    public static final String DISPLAY_SORT_NAME = "2";

    public static final String CREATE_CONTACT_ACTION = "com.katsuna.contacts.create";
    public static final String ADD_TO_CONTACT_ACTION = "com.katsuna.contacts.add";
    public static final String ADD_TO_CONTACT_ACTION_NUMBER = "number";
    public static final String EDIT_CONTACT_ACTION = "com.katsuna.contacts.edit";
    public static final String SELECT_CONTACT_NUMBER_ACTION = "com.katsuna.commons.select_contact_number";

    public static final int FAB_TRANSFORMATION_DURATION = 200;

    public static final int POPUP_INACTIVITY_THRESHOLD = 10000;
    public static final int SELECTION_THRESHOLD = 20000;
    public static final int HANDLER_DELAY = 1000;

    public static final String SANS_SERIF = "sans-serif";
    public static final String SANS_SERIF_MEDIUM = "sans-serif-medium";

    public static final String KATSUNA_PRIVACY_URL = "http://katsuna.com/privacy-policy/";
    public static final String KATSUNA_TERMS_OF_USE = "http://katsuna.com/eula/";
}
