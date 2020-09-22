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
package com.katsuna.commons.domain;

import com.katsuna.commons.utils.DataAction;

import java.io.Serializable;

public class Description implements Serializable {

    private static final long serialVersionUID = -2664278634090496331L;

    private String id;
    private String description;
    private DataAction dataAction;

    public Description() { }

    public Description(String description) {
        this.description = description;
    }

    public Description(Description description) {
        if (description != null) {
            this.id = description.getId();
            this.description = description.getDescription();
        }
    }

    @Override
    public String toString() {
        return " Description: " + getDescription();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataAction getDataAction() {
        return dataAction;
    }

    public void setDataAction(DataAction dataAction) {
        this.dataAction = dataAction;
    }
}
