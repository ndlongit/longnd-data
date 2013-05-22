package com.structis.vip.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class DelegationTreeEvent extends GwtEvent<DelegationTreeHandler> {

    private static Type<DelegationTreeHandler> TYPE = new Type<DelegationTreeHandler>();

    private PerimetreTreeModel treeModel;

    public DelegationTreeEvent(PerimetreTreeModel treeModel) {
        this.treeModel = treeModel;
    }

    public PerimetreTreeModel getTreeModel() {
        return this.treeModel;
    }

    public void setTreeModel(PerimetreTreeModel treeModel) {
        this.treeModel = treeModel;
    }

    public static Type<DelegationTreeHandler> getType() {
        return TYPE;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<DelegationTreeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DelegationTreeHandler handler) {
        handler.onLoadAction(this);
    }

}
