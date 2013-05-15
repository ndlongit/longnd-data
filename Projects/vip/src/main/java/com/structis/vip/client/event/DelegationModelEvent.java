package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;

public class DelegationModelEvent extends GwtEvent<DelegationModelHandler> {
	public static final int MODE_IS_NEW = 1;
	public static final int MODE_IS_EDIT = 2;
	public static final int MODE_IS_UPDATE_DOCUMENT = 3;

	private static Type<DelegationModelHandler> TYPE = new Type<DelegationModelHandler>();

	private int mode;

	private Integer group;

	private EntiteModel entiteModel;

	private DelegationNatureModel natureModel;

	private LanguageModel languageModel;
	private Integer hasMultipleDelegation;
	private Integer hasMultipleDelegataire;
	private Integer subDelegation;
	

	@Override
	protected void dispatch(DelegationModelHandler handler) {
		handler.onLoadAction(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DelegationModelHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<DelegationModelHandler> getType() {
		return TYPE;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public EntiteModel getEntiteModel() {
		return entiteModel;
	}

	public void setEntiteModel(EntiteModel entiteModel) {
		this.entiteModel = entiteModel;
	}

	public DelegationNatureModel getNatureModel() {
		return natureModel;
	}

	public void setNatureModel(DelegationNatureModel natureModel) {
		this.natureModel = natureModel;
	}

	public LanguageModel getLanguageModel() {
		return languageModel;
	}

	public void setLanguageModel(LanguageModel languageModel) {
		this.languageModel = languageModel;
	}

	public Integer getHasMultipleDelegation() {
		return hasMultipleDelegation;
	}

	public void setHasMultipleDelegation(Integer hasMultipleDelegation) {
		this.hasMultipleDelegation = hasMultipleDelegation;
	}

	public void setHasMultipleDelegataire(Integer hasMultipleDelegataire) {
		this.hasMultipleDelegataire = hasMultipleDelegataire;
	}

	public Integer getHasMultipleDelegataire() {
		return hasMultipleDelegataire;
	}

	public Integer getSubDelegation() {
		return subDelegation;
	}

	public void setSubDelegation(Integer subDelegation) {
		this.subDelegation = subDelegation;
	}
	
	
	
}
