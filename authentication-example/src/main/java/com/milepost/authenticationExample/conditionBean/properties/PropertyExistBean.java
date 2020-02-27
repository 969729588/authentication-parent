package com.milepost.authenticationExample.conditionBean.properties;

/**
 * Created by @author yihui in 09:51 18/10/18.
 */
public class PropertyExistBean {
    private String name;

    public PropertyExistBean(String name) {
        this.name = name;
    }

    public String getName() {
        return "property : " + name;
    }
}
