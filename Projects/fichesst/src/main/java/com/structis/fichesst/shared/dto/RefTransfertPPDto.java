package com.structis.fichesst.shared.dto;

public class RefTransfertPPDto extends SimpleDto {

	private static final long serialVersionUID = 1L;

	public static final String ORDER = "order";

	public RefTransfertPPDto() {
	}

	public RefTransfertPPDto(Integer id, String label) {
		this.set(ID, id);
		this.set(LABEL, label);
	}

	public Integer getOrder() {
		return get(ORDER, Integer.MAX_VALUE);
	}

	public void setOrder(Integer order) {
		set(ORDER, order);
	}
}
