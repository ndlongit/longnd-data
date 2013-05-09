package com.structis.fichesst.server.bean.core;

public interface Orderable {
	
	public static final String PROP_ORDER = "order";

	Integer getOrder();

	void setOrder(Integer order);
}
