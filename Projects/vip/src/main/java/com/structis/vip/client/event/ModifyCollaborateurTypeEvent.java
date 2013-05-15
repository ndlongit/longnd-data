package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.CollaborateurTypeModel;

public class ModifyCollaborateurTypeEvent extends GwtEvent<ModifyCollaborateurTypeHandler> {
	private static Type<ModifyCollaborateurTypeHandler> TYPE = new Type<ModifyCollaborateurTypeHandler>();
	
	private CollaborateurTypeModel model;

	public static Type<ModifyCollaborateurTypeHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyCollaborateurTypeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyCollaborateurTypeHandler handler) {
		handler.onLoadAction(this);
	}

	public CollaborateurTypeModel getModel() {
		return model;
	}

	public void setModel(CollaborateurTypeModel model) {
		this.model = model;
	}
}
