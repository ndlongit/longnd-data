package com.structis.fichesst.shared.dto;

import java.util.Date;

public class ProgressDto extends SimpleDto {

	private static final long serialVersionUID = 1L;

	public static final String ORDER = "order";

	public static final String DATE = "date";

	public static final String CUMULE = "cumule";

	public static final String MOIS = "mois";

	public static final String CUMULE2 = "cumule2";

	public static final String MOIS2 = "mois2";

	public Integer getOrder() {
		return get(ORDER, 0);
	}

	public void setOrder(Integer number) {
		set(ORDER, number);
	}

	public Date getDate() {
		return get(DATE);
	}

	public void setDate(Date date) {
		set(DATE, date);
	}

	public Double getCumule() {
		Object obj = get(CUMULE);
		if( obj == null ) {
			return 0.0;
		}
		return get(CUMULE);
	}

	public void setCumule(Double cumule) {
		set(CUMULE, cumule);
	}

	public Double getMois() {
		Object obj = get(MOIS);
		if( obj == null ) {
			return 0.0;
		}
		return get(MOIS);
	}

	public void setMois(Double mois) {
		set(MOIS, mois);
	}

	public Double getCumule2() {
		Object obj = get(CUMULE2);
		if( obj == null ) {
			return 0.0;
		}
		return get(CUMULE2);
	}

	public void setCumule2(Double cumule2) {
		set(CUMULE2, cumule2);
	}

	public Double getMois2() {
		Object obj = get(MOIS2);
		if( obj == null ) {
			return 0.0;
		}
		return get(MOIS2);
	}

	public void setMois2(Double mois2) {
		set(MOIS2, mois2);
	}
	
	@Override
	protected void initTestData() {
		setLabel("Label 1");
		setDate(new Date());
		setCumule(10.0);
		setCumule2(20.0);
	}
}
