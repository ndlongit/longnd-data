package com.structis.fichesst.shared.dto;

public class RefTypeMchAvDto extends SimpleDto {

	public static final String ORDER = "order";

	public Integer getOrder() {
		return get(ORDER, Integer.MAX_VALUE);
	}

	public void setOrder(Integer order) {
		set(ORDER, order);
	}
}
