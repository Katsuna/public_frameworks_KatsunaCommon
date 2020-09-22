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
package com.katsuna.commons.entities;

public class KatsunaApp {

    public String title;
    public String packageName;
    public int drawableId;

    public KatsunaApp(String title, String packageName, int drawableId) {
        this.title = title;
        this.packageName = packageName;
        this.drawableId = drawableId;
    }

    @Override
    public String toString() {
        return " Katsuna App: " + title + " " + packageName;
    }
}
