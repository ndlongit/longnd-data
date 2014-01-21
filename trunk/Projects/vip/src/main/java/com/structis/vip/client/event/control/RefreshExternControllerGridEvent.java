package com.structis.vip.client.event.control;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.ExtControllerControlModel;

public class RefreshExternControllerGridEvent extends GwtEvent<RefreshExternControllerGridHandler> {

    private static Type<RefreshExternControllerGridHandler> TYPE = new Type<RefreshExternControllerGridHandler>();

    private List<ExtControllerControlModel> externControllers;

    public RefreshExternControllerGridEvent() {
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<RefreshExternControllerGridHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<RefreshExternControllerGridHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RefreshExternControllerGridHandler handler) {
        handler.onLoadAction(this);
    }

    public List<ExtControllerControlModel> getExternControllers() {
        return this.externControllers;
    }

    public void setExternControllers(List<ExtControllerControlModel> externControllers) {
        this.externControllers = externControllers;
    }

}