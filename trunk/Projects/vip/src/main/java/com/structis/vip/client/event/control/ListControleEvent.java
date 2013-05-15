package com.structis.vip.client.event.control;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class ListControleEvent extends GwtEvent<ListControleHandler> {
	
	private static com.google.gwt.event.shared.GwtEvent.Type<ListControleHandler> TYPE = new Type<ListControleHandler>();
	
	private EntiteModel entiteModel;
	private PerimetreTreeModel perimetreTreeModel;
	private Boolean refresh = false;
	private ControlModel controlModel;
	
	public ListControleEvent(){}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ListControleHandler> getAssociatedType() {
		return TYPE;
	}
	
	public static Type<ListControleHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ListControleHandler handler) {
		handler.onLoadAction(this);
	}

	public EntiteModel getEntiteModel() {
		return entiteModel;
	}

	public void setEntiteModel(EntiteModel entiteModel) {
		this.entiteModel = entiteModel;
	}

	public PerimetreTreeModel getPerimetreTreeModel() {
		return perimetreTreeModel;
	}

	public void setPerimetreTreeModel(PerimetreTreeModel perimetreTreeModel) {
		this.perimetreTreeModel = perimetreTreeModel;
	}

	public Boolean getRefresh() {
		return refresh;
	}

	public void setRefresh(Boolean refresh) {
		this.refresh = refresh;
	}

	public ControlModel getControlModel() {
		return controlModel;
	}

	public void setControlModel(ControlModel controlModel) {
		this.controlModel = controlModel;
	}	
	
}