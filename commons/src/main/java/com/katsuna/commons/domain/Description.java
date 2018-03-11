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
