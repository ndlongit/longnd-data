package com.structis.fichesst.shared.dto;

import java.util.Date;

public class PenaltyDto extends AbstractDto {

	public static final String DATE = "date";

	public static final String AMOUNT = "amount";

	public static final String COMMENT = "comment";

	public Date getDate() {
		return get(DATE);
	}

	public void setDate(Date date) {
		set(DATE, date);
	}

	public Double getAmount() {
		return get(AMOUNT, 0.0);
	}

	public void setAmount(Double amount) {
		set(AMOUNT, amount);
	}

	public String getComment() {
		return get(COMMENT, "");
	}

	public void setComment(String comment) {
		set(COMMENT, comment);
	}
	
	@Override
	public void initTestData() {
		setDate(new Date());
		setAmount(2.1);
		setComment("comment");
	}
}
