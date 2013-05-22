package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.DocumentTypeModel;

public class ModifyDocTypeEvent extends GwtEvent<ModifyDocTypeHandler> {

    private static Type<ModifyDocTypeHandler> TYPE = new Type<ModifyDocTypeHandler>();

    private DocumentTypeModel model;
    private int mode;

    public static Type<ModifyDocTypeHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ModifyDocTypeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ModifyDocTypeHandler handler) {
        handler.onLoadAction(this);
    }

    public DocumentTypeModel getModel() {
        return this.model;
    }

    public void setModel(DocumentTypeModel model) {
        this.model = model;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
