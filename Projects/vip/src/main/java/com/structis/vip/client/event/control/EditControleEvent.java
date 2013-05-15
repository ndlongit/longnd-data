package com.structis.vip.client.event.control;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class EditControleEvent extends GwtEvent<EditControleHandler> {
	
	private static Type<EditControleHandler> TYPE = new Type<EditControleHandler>();
	
	private EntiteModel entiteModel;
	private PerimetreTreeModel perimetreTreeModel;
	
	private ControlModel controlModel;
		
	private Date startDate;
	private Date endDate;
	
	private int pageSize;
	
	public EditControleEvent(){}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditControleHandler> getAssociatedType() {
		return TYPE;
	}
	
	public static Type<EditControleHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditControleHandler handler) {
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

	public void setPerimetreTreeModel(PerimetreTreeModel perimetreModel) {
		this.perimetreTreeModel = perimetreModel;
	}

	public void setControlModel(ControlModel cm) {
		this.controlModel = cm;		
	}

	public ControlModel getControlModel() {
		return controlModel;
	}	
	

}