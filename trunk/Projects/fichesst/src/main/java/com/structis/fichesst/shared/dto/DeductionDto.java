package com.structis.fichesst.shared.dto;

import java.util.Date;

public class DeductionDto extends AbstractDto {

	public static final String DATE = "date";

	public static final String CANTO = "canto";

	public static final String BADGE = "badge";

	public static final String GRUE = "grue";

	public static final String LIFT = "lift";

	public static final String BENNE = "benne";

	public static final String NETTOYAGE = "nettoyage";

	public static final String AUTRES = "autres";

	public static final String PRORATA = "prorata";

	public static final String REFACTURATIONS = "refacturations";

	public Integer getCanto() {
		Object obj = get(CANTO);
		if( obj == null ) {
			return 0;
		}
		return get(CANTO);
	}

	public void setCanto(Integer canto) {
		set(CANTO, canto);
	}

	public Integer getBadge() {
		Object obj = get(BADGE);
		if( obj == null ) {
			return 0;
		}
		return get(BADGE);
	}

	public void setBadge(Integer badge) {
		set(BADGE, badge);
	}

	public Integer getGrue() {
		Object obj = get(GRUE);
		if( obj == null ) {
			return 0;
		}
		return get(GRUE);
	}

	public void setGrue(Integer grue) {
		set(GRUE, grue);
	}

	public Integer getLift() {
		Object obj = get(LIFT);
		if( obj == null ) {
			return 0;
		}
		return get(LIFT);
	}

	public void setLift(Integer lift) {
		set(LIFT, lift);
	}

	public Integer getBenne() {
		Object obj = get(BENNE);
		if( obj == null ) {
			return 0;
		}
		return get(BENNE);
	}

	public void setBenne(Integer benne) {
		set(BENNE, benne);
	}

	public Integer getNettoyage() {
		Object obj = get(NETTOYAGE);
		if( obj == null ) {
			return 0;
		}
		return get(NETTOYAGE);
	}

	public void setNettoyage(Integer nettoyage) {
		set(NETTOYAGE, nettoyage);
	}

	public Double getAutres() {
		Object obj = get(AUTRES);
		if( obj == null ) {
			return 0.0;
		}
		return get(AUTRES);
	}

	public void setAutres(Double autres) {
		set(AUTRES, autres);
	}

	public Date getDate() {
		return get(DATE);
	}

	public void setDate(Date date) {
		set(DATE, date);
	}

	public Double getProrata() {
		Object obj = get(PRORATA);
		if( obj == null ) {
			return 0.0;
		}
		return get(PRORATA);
	}

	public void setProrata(Double prorata) {
		set(PRORATA, prorata);
	}

	public Double getRefacturations() {
		Object obj = get(REFACTURATIONS);
		if( obj == null ) {
			return 0.0;
		}
		return get(REFACTURATIONS);
	}

	public void setRefacturations(Double refacturations) {
		set(REFACTURATIONS, refacturations);
	}
	
	@Override
	protected void initTestData() {
		setDate(new Date());
		setCanto(12);
		setBadge(11);
		setGrue(87);
		setLift(54);
		setBenne(5);
		setNettoyage(98);
		setAutres(9.0);
		setProrata(0.0);
		setRefacturations(0.0);
	}
}
