package com.structis.vip.client.event.control;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.KeyValueModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class ControlFilterEvent extends GwtEvent<ControlFilterHandler> {

    private static Type<ControlFilterHandler> TYPE = new Type<ControlFilterHandler>();

    private EntiteModel entiteModel;
    private PerimetreTreeModel perimetreTreeModel;
    private List<ControlTypeModel> typeModel;

    private Date startDate;
    private Date endDate;
    private String codeProject;
    private List<KeyValueModel> caracteres;
    private String controllerName;

    private int pageSize;

    public ControlFilterEvent() {
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ControlFilterHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<ControlFilterHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ControlFilterHandler handler) {
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

    public void setPerimetreTreeModel(PerimetreTreeModel perimetreTreeModel) {
        this.perimetreTreeModel = perimetreTreeModel;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ControlTypeModel> getTypeModel() {
        return this.typeModel;
    }

    public void setTypeModel(List<ControlTypeModel> typeModel) {
        this.typeModel = typeModel;
    }

    public String getCodeProject() {
        return this.codeProject;
    }

    public void setCodeProject(String codeProject) {
        this.codeProject = codeProject;
    }

    public List<KeyValueModel> getCaracteres() {
        return this.caracteres;
    }

    public void setCaracteres(List<KeyValueModel> caracteres) {
        this.caracteres = caracteres;
    }

    public String getControllerName() {
        return this.controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
