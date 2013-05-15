package com.structis.vip.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DelegationModel;

public class DelegationGridProjectEvent extends GwtEvent<DelegationGridProjectHandler> {
	
	private static Type<DelegationGridProjectHandler> TYPE = new Type<DelegationGridProjectHandler>();
	
	private List<DelegationModel> listData;
	
	public DelegationGridProjectEvent(List<DelegationModel> listData){
		this.listData = listData;
	}
	
	public List<DelegationModel> getListData() {
		return listData;
	}

	public void setListData(List<DelegationModel> listData) {
		this.listData = listData;
	}

	public static Type<DelegationGridProjectHandler> getType(){
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DelegationGridProjectHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DelegationGridProjectHandler handler) {
		handler.onLoadAction(this);
	}

}