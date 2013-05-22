package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DomDelModel;

public class DocumentsDialog extends Window {

    private List<DocumentMdlModel> documents;
    private ListStore<DomDelModel> dataStore;

    private Button btnSelect;
    private Button btnCancel;

    List<DocumentMdlModel> selectedDocuments = null;

    public DocumentsDialog(List<DocumentMdlModel> documents, ListStore<DomDelModel> dataStore) {
        this.documents = documents;
        this.dataStore = dataStore;
        this.refineDocuments();
        this.initUI();
    }

    private void refineDocuments() {
        if (this.dataStore.getModels().size() == 0) {
            return;
        }
        HashMap<Integer, DocumentMdlModel> refinedDocuments = new HashMap<Integer, DocumentMdlModel>();
        for (int index = 0; index < this.documents.size(); index++) {
            refinedDocuments.put(this.documents.get(index).getId(), this.documents.get(index));
        }

        List<DomDelModel> data = this.dataStore.getModels();
        for (int i = 0; i < data.size(); i++) {
            DomDelModel document = data.get(i);
            if (refinedDocuments.get(document.getId()) != null) {
                refinedDocuments.remove(document.getId());
            }
        }
        if (refinedDocuments.values().size() > 0) {
            this.documents = new ArrayList<DocumentMdlModel>(refinedDocuments.values());
        } else {
            this.documents = new ArrayList<DocumentMdlModel>();
        }

    }

    private void initUI() {
        LayoutContainer main = new LayoutContainer();
        main.setStyleAttribute("margin", "20px");
        FitLayout flLeft = new FitLayout();
        main.setLayout(flLeft);

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        final CheckBoxSelectionModel<DocumentMdlModel> sm = new CheckBoxSelectionModel<DocumentMdlModel>();
        sm.setSelectionMode(SelectionMode.MULTI);
        configs.add(sm.getColumn());

        ColumnConfig column = new ColumnConfig();
        column.setId(DocumentMdlModel.DOM_NAME);
        column.setHeader("Name");
        column.setWidth(600);
        configs.add(column);

        column = new ColumnConfig();
        column.setId(DocumentMdlModel.DOM_TYPE);
        column.setHeader("Type");
        column.setWidth(100);
        configs.add(column);

        ListStore<DocumentMdlModel> store = new ListStore<DocumentMdlModel>();
        store.add(this.documents);

        ColumnModel cm = new ColumnModel(configs);

        ContentPanel cp = new ContentPanel();
        cp.setHeaderVisible(false);
        cp.setFrame(true);
        // cp.setIcon(Resources.ICONS.table());
        cp.setLayout(new FitLayout());
        cp.setButtonAlign(HorizontalAlignment.RIGHT);
        cp.setSize(700, 400);
        final Grid<DocumentMdlModel> grid = new Grid<DocumentMdlModel>(store, cm);
        grid.setSelectionModel(sm);
        grid.setBorders(true);
        grid.setStripeRows(true);
        grid.setColumnReordering(true);
        grid.getAriaSupport().setLabelledBy(cp.getHeader().getId() + "-label");
        grid.addPlugin(sm);

        cp.add(grid);

        this.btnSelect = new Button("Select");
        this.btnSelect.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DocumentsDialog.this.selectedDocuments = grid.getSelectionModel().getSelectedItems();
                if (DocumentsDialog.this.selectedDocuments.size() > 0) {
                    List<DomDelModel> domDels = new ArrayList<DomDelModel>();
                    for (int i = 0; i < DocumentsDialog.this.selectedDocuments.size(); i++) {
                        DocumentMdlModel template = DocumentsDialog.this.selectedDocuments.get(i);
                        DomDelModel domdel = new DomDelModel();
                        domdel.setDocumentMdl(template);
                        domDels.add(domdel);
                    }
                    DocumentsDialog.this.dataStore.add(domDels);
                }
                DocumentsDialog.this.hide();
            }
        });
        this.btnCancel = new Button("Cancel");
        this.btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DocumentsDialog.this.selectedDocuments = null;
                DocumentsDialog.this.hide();

            }
        });

        cp.addButton(this.btnSelect);
        cp.addButton(this.btnCancel);
        cp.setButtonAlign(HorizontalAlignment.CENTER);
        main.add(cp);
        this.add(main);
        this.setSize(800, 500);
        this.setHeading("Document Selection Dialog");
    }

}
