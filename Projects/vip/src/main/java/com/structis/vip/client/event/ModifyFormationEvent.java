package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.FormationModel;

public class ModifyFormationEvent extends GwtEvent<ModifyFormationHandler> {
	private static Type<ModifyFormationHandler> TYPE = new Type<ModifyFormationHandler>();
	
	private FormationModel model;

	public static Type<ModifyFormationHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyFormationHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyFormationHandler handler) {
		handler.onLoadAction(this);
	}

	public FormationModel getModel() {
		return model;
	}

	public void setModel(FormationModel model) {
		this.model = model;
	}
}
