package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.vip.client.widget.XComboBox;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class ReportFilterPanel extends FieldSet {

    private ListStore<PerimetreTypeModel> types = new ListStore<PerimetreTypeModel>();
    private ListStore<PerimetreModel> perimetres = new ListStore<PerimetreModel>();

    public ReportFilterPanel(SimpleEventBus bus) {

        initUI();

        addHandler();
    }

    private void addHandler() {

    }

    private void initUI() {
        setHeading("Choix du périmètre");
        FormData formData = new FormData("100%");
        FormLayout layout = new FormLayout();

        XComboBox<PerimetreModel> cbPerimetre = new XComboBox<PerimetreModel>();
        cbPerimetre.setStore(perimetres);
        cbPerimetre.setFieldLabel("Périmètre");

        FormPanel main = new FormPanel();
        main.setBorders(true);
        main.setWidth("1000px");
        main.setLayout(new ColumnLayout());

        LayoutContainer left = new LayoutContainer();
        left.setId("aaaaa");
        left.setBorders(true);
        left.setLayout(layout);
        XComboBox<PerimetreTypeModel> cbPerimetreType = new XComboBox<PerimetreTypeModel>();
        cbPerimetreType.setStore(types);
        cbPerimetreType.setFieldLabel("Type de périmètre sélectionné");

        LayoutContainer right = new LayoutContainer();
        right.setId("bbbbbb");
        right.setBorders(true);
        right.setLayout(layout);
        right.add(cbPerimetre, formData);
        main.setStyleAttribute("padding-left", "0px");
        main.setStyleAttribute("padding-bottom", "0px");
        main.add(left, new ColumnData(.5));
        main.add(right, new ColumnData(.5));
        setWidth("1000px");
        add(main);

    }
}
