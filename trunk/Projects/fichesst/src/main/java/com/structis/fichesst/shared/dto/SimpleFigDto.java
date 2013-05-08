package com.structis.fichesst.shared.dto;


public class SimpleFigDto extends AbstractDto{

	private static final long serialVersionUID = 1L;

	public static final String LABEL = "LABEL";

	public static final String VALUE = "VALUE";

	public String getLabel() {
		return get(LABEL);
	}

	public void setLabel(String label) {
		set(LABEL, label);
	}

	public Boolean getValue() {
		return get(VALUE);
	}

	public void setValue(Boolean value) {
		set(VALUE, value);
	}

	public SimpleFigDto (Integer id,String label){
		this.set(ID, id);
		this.set(LABEL,label);
	}
	public SimpleFigDto(){}
}
