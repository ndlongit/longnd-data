package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.LanguageModel;

public class ModifyLanguageEvent extends GwtEvent<ModifyLanguageHandler> {

    private static Type<ModifyLanguageHandler> TYPE = new Type<ModifyLanguageHandler>();

    private LanguageModel model;

    public static Type<ModifyLanguageHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ModifyLanguageHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ModifyLanguageHandler handler) {
        handler.onLoadAction(this);
    }

    public LanguageModel getModel() {
        return this.model;
    }

    public void setModel(LanguageModel model) {
        this.model = model;
    }
}
