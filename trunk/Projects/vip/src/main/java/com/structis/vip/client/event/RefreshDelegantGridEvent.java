package com.structis.vip.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.CollaborateurTypeModel;

public class RefreshDelegantGridEvent extends GwtEvent<RefreshDelegantGridHandler> {

    private static Type<RefreshDelegantGridHandler> TYPE = new Type<RefreshDelegantGridHandler>();

    private List<CollaborateurTypeModel> delegantTypes;

    public RefreshDelegantGridEvent() {
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<RefreshDelegantGridHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<RefreshDelegantGridHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RefreshDelegantGridHandler handler) {
        handler.onLoadAction(this);
    }

    public List<CollaborateurTypeModel> getDelegantTypes() {
        return this.delegantTypes;
    }

    public void setDelegantTypes(List<CollaborateurTypeModel> delegantTypes) {
        this.delegantTypes = delegantTypes;
    }

}
