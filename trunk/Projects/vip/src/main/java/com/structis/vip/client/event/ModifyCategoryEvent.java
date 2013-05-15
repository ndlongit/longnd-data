package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.CategoryModel;

public class ModifyCategoryEvent extends GwtEvent<ModifyCategoryHandler> {
	private static Type<ModifyCategoryHandler> TYPE = new Type<ModifyCategoryHandler>();
	
	private CategoryModel model;

	public static Type<ModifyCategoryHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ModifyCategoryHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ModifyCategoryHandler handler) {
		handler.onLoadAction(this);
	}

	public CategoryModel getModel() {
		return model;
	}

	public void setModel(CategoryModel model) {
		this.model = model;
	}
}
