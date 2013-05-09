package com.structis.fichesst.shared.dto;

public class LotTypeDto extends AbstractDto implements Comparable<LotTypeDto> {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "name";

	public static final String ORDER = "order";

	public static final String INCLUDEDINTOTAL = "includedInTotal";

	public String getName() {
		return get(NAME);
	}

	public void setName(String name) {
		set(NAME, name);
	}

	public Integer getOrder() {
		return get(ORDER, Integer.MAX_VALUE);
	}

	public void setOrder(Integer order) {
		set(ORDER, order);
	}

	public String getIncludedInTotal() {
		return get(INCLUDEDINTOTAL);
	}

	public void setIncludedInTotal(String includedInTotal) {
		set(INCLUDEDINTOTAL, includedInTotal);
	}

	@Override
	public int compareTo(LotTypeDto other) {
		Integer order1 = getOrder();
		Integer order2 = other.getOrder();
		return order1.compareTo(order2);
	}
}
