package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.CollaborateurModel;

public class ModifyCollaboratureEvent extends GwtEvent<ModifyCollaboratureHandler> {

    public static final int MODE_VIEW = 1;

    private static Type<ModifyCollaboratureHandler> TYPE = new Type<ModifyCollaboratureHandler>();

    private CollaborateurModel model;
    private int mode;

    public static Type<ModifyCollaboratureHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ModifyCollaboratureHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ModifyCollaboratureHandler handler) {
        handler.onLoadAction(this);
    }

    public CollaborateurModel getModel() {
        return this.model;
    }

    public void setModel(CollaborateurModel model) {
        this.model = model;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
