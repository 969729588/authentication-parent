package com.milepost.authenticationExample.conditionBean.properties;

/**
 * Created by @author yihui in 10:17 18/10/18.
 */
public class PropertyValueNotExistBean {
    public String name;

    public PropertyValueNotExistBean(String name) {
        this.name = name;
    }

    public String getName() {
        return "property value not exist: " + name;
    }
}
