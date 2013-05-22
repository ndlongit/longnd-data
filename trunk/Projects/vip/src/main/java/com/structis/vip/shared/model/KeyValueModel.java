package com.structis.vip.shared.model;

public class KeyValueModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;
    public final static String KEY = "key";
    public final static String VALUE = "value";
    public final static String TYPE = "type";

    public String getKey() {
        return this.get(KEY);
    }

    public String getValue() {
        return this.get(VALUE);
    }

    public void setKey(String key) {
        this.set(KEY, key);
    }

    public void setValue(String value) {
        this.set(VALUE, value);
    }

    public String getType() {
        return this.get(TYPE);
    }

    public void setType(String type) {
        this.set(TYPE, type);
    }
}
