package com.milepost.authenticationService.classes.entity;

import java.io.Serializable;

public class Classes implements Serializable {
    /**
     * classes.ID
     * 
     *
     * @mbggenerated
     */
    private String id;

    /**
     * classes.NAME
     * 
     *
     * @mbggenerated
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}