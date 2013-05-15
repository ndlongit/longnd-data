package com.structis.vip.shared.model;

public class FieldRuleModel extends BaseModelDataActivable {
	
	private static final long serialVersionUID = 6807380804987897136L;
	public static final String FIR_ID = "id";	
	public static final String FIR_DEM_GROUP = "group";
	public static final String FIR_IS_DISPLAYED = "isDisplayed";
	public static final String FIE_IS_REQUIRED = "isRequired";
	public static final String FIE_FIELD = "field";
	    
	@SuppressWarnings("unused")
	private FieFieldModel fieldModel;
	
	public Integer getId() {
		return get(FIR_ID);
	}
	public void setId(Integer id) {
		set(FIR_ID, id);
	}
	public Integer getGroup() {
		return get(FIR_DEM_GROUP);
	}
	public void setGroup(Integer group) {
		set(FIR_DEM_GROUP, group);
	}
	public Integer getIsDisplayed() {
		return get(FIR_IS_DISPLAYED);
	}
	public void setIsDisplayed(Integer isDisplayed) {
		set(FIR_IS_DISPLAYED, isDisplayed);
	}
	public Integer getIsRequired() {
		return get(FIE_IS_REQUIRED);
	}
	public void setIsRequired(Integer isRequired) {
		set(FIE_IS_REQUIRED, isRequired);
	}
	public FieFieldModel getField() {
		return get(FIE_FIELD);
	}
	public void setField(FieFieldModel field) {
		set(FIE_FIELD, field);
	}	
}