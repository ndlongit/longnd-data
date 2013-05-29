package com.structis.vip.client.event.control;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class ViewControleEvent extends GwtEvent<ViewControleHandler> {

    private static Type<ViewControleHandler> TYPE = new Type<ViewControleHandler>();

    private EntiteModel entiteModel;
    private PerimetreTreeModel perimetreTreeModel;

    private ControlModel controlModel;

    public ViewControleEvent() {
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ViewControleHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<ViewControleHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ViewControleHandler handler) {
        handler.onLoadAction(this);
    }

    public EntiteModel getEntiteModel() {
        return this.entiteModel;
    }

    public void setEntiteModel(EntiteModel entiteModel) {
        this.entiteModel = entiteModel;
    }

    public PerimetreTreeModel getPerimetreTreeModel() {
        return this.perimetreTreeModel;
    }

    public void setPerimetreTreeModel(PerimetreTreeModel perimetreModel) {
        this.perimetreTreeModel = perimetreModel;
    }

    public void setControlModel(ControlModel cm) {
        this.controlModel = cm;
    }

    public ControlModel getControlModel() {
        return this.controlModel;
    }

}
