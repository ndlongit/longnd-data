package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;

public class DelegationListProjectEvent extends GwtEvent<DelegationListProjectHandler> {

	private static Type<DelegationListProjectHandler> TYPE = new Type<DelegationListProjectHandler>();
	
	public static final int DELEGATION_FILTER_FROM_SELECTION_SCREEN = 1;
	public static final int DELEGATION_FILTER_FROM_DELEGATION_FILTER = 2;

	private EntiteModel entiteModel;
	private PerimetreModel perimetreModel;
	
	private int mode = 1;

	public DelegationListProjectEvent(EntiteModel entiteModel, PerimetreModel uoModel) {
		this.entiteModel = entiteModel;
		this.perimetreModel = uoModel;
	}

	public EntiteModel getEntiteModel() {
		return entiteModel;
	}

	public PerimetreModel getPerimetreModel() {
		return perimetreModel;
	}

	public static Type<DelegationListProjectHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DelegationListProjectHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DelegationListProjectHandler handler) {
		handler.onLoadAction(this);
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
}