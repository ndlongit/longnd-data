package com.structis.fichesst.shared.dto;

import java.util.Date;

public class CautionFournieDto extends AbstractDto {

	private static final long serialVersionUID = 1L;

	public static final String DATE = "date";

	public static final String AMOUNT = "amount";

	public Date getDate() {
		return get(DATE);
	}

	public void setDate(Date date) {
		set(DATE, date);
	}

	public Double getAmount() {
		Object obj = get(AMOUNT);
		if( obj == null ) {
			return 0.0;
		}
		return get(AMOUNT);
	}

	public void setAmount(Double amount) {
		set(AMOUNT, amount);
	}

	@Override
	public String toString() {
		return "[Id: " + getId() + "; Date: " + getDate() + "; Amount: " + getAmount() + "]";
	}
}
