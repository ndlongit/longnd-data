package com.structis.vip.client.navigation;

import java.util.HashMap;
import java.util.Map;

public class NavigationEvent {

    private Object object;

    private Map<String, String> source;

    private boolean load = true;

    public NavigationEvent(Object object) {
        this.object = object;
    }

    public NavigationEvent() {
        this.source = new HashMap<String, String>();
    }

    public NavigationEvent(String key, String value) {
        super();
        this.source = new HashMap<String, String>();
        this.source.put(key, value);
    }

    public NavigationEvent(String key, Integer value) {
        super();
        this.source = new HashMap<String, String>();
        this.source.put(key, value + "");
    }

    public NavigationEvent(Map<String, String> source) {
        super();
        this.source = source;
    }

    public NavigationEvent(Map<String, String> source, boolean load) {
        super();
        this.source = source;
        this.load = load;
    }

    public Map<String, String> getParameters() {
        return this.source;
    }

    public String getParameter(String key) {
        if (null == this.source) {
            return null;
        }
        return this.source.get(key);
    }

    public boolean getLoad() {
        return this.load;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
