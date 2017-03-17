package com.herongtech.console.service.callremote.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


public abstract class DynamicProperty {
    /**
     * 存放动态属性的值
     */
    private Map<String,Object> propertyMap = new HashMap<String,Object>();

    public DynamicProperty() {
        super();
    }

    public Map<String,Object> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String,Object> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public void addProperty(String key, Object value) {
        if(StringUtils.isEmpty(key)) {
            return;
        }
        this.propertyMap.put(key, value);
    }

    public Object getPropertyValue(String key) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return this.propertyMap.get(key);
    }
}
