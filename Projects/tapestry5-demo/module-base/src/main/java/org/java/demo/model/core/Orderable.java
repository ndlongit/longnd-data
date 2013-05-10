package org.java.demo.model.core;

public interface Orderable {

    public static final String PROP_ORDER = "order";

    Integer getOrder();

    void setOrder(Integer order);
}
