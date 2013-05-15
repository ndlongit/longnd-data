package com.structis.vip.client.event.control;

import java.util.Date;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class RefreshTreeEvent extends GwtEvent<RefreshTreeHandler> {
	
	private static Type<RefreshTreeHandler> TYPE = new Type<RefreshTreeHandler>();
	
	private EntiteModel entiteModel;
	private PerimetreModel perimetreModel;
	
	private ControlModel controlModel;
			
	public RefreshTreeEvent(){}
	public RefreshTreeEvent(EntiteModel entiteModel, PerimetreModel perimetreModel){
		this.entiteModel = entiteModel;
		this.perimetreModel = perimetreModel;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RefreshTreeHandler> getAssociatedType() {
		return TYPE;
	}
	
	public static Type<RefreshTreeHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RefreshTreeHandler handler) {
		handler.onLoadAction(this);
	}

	public EntiteModel getEntiteModel() {
		return entiteModel;
	}

	public void setEntiteModel(EntiteModel entiteModel) {
		this.entiteModel = entiteModel;
	}

	public PerimetreModel getPerimetreModel() {
		return perimetreModel;
	}

	
	public void setPerimetreModel(PerimetreModel perimetreModel) {
		this.perimetreModel = perimetreModel;
	}
	public void setPerimetreTreeModel(PerimetreModel perimetreModel) {
		this.perimetreModel = perimetreModel;
	}

	public void setControlModel(ControlModel cm) {
		this.controlModel = cm;		
	}

	public ControlModel getControlModel() {
		return controlModel;
	}	
	

}