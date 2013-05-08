package com.structis.fichesst.shared.dto;

import java.util.ArrayList;
import java.util.List;

public class SimpleDto extends AbstractDto implements Comparable<SimpleDto> {

	private static final long serialVersionUID = 1L;

	public static final String LABEL = "LABEL";

	public static final String VALUE = "VALUE";

	public String getLabel() {
		return get(LABEL);
	}

	public void setLabel(String label) {
		set(LABEL, label);
	}

	public String getValue() {
		return get(VALUE);
	}

	public void setValue(String value) {
		set(VALUE, value);
	}

	@Override
	public int compareTo(SimpleDto other) {
		if( other == null ) {
			return -1;
		}

		String label1 = getLabel();
		String label2 = other.getLabel();
		if( label1 != null && label2 != null ) {
			return label1.compareToIgnoreCase(label2);
		}
		else {
			if( label1 == null ) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}

		@Override
		public boolean equals(Object obj) {
			if( obj == null || !(obj instanceof SimpleDto) ) {
				return false;
			}
	
			SimpleDto other = (SimpleDto) obj;
			Integer id1 = getId();
			Integer id2 = other.getId();
			if( id1 == null && id2 == null ) {
				return false;
			}
			else {
				return(id1 != null && id1.equals(id2));
			}
		}

	@Override
	public String toString() {
		return "[Id: " + getId() + "; Label: " + getLabel() + "]";
	}
	public static List<SimpleDto> initDevers(){
		List<SimpleDto> list=new ArrayList<SimpleDto>();
		list.add(new SimpleDto(9,"de"));
		list.add(new SimpleDto(10,"vers"));
		return list;
			
	}
	public SimpleDto (Integer id,String label){
		this.set(ID, id);
		this.set(LABEL,label);
	}
	public SimpleDto(){}
}
