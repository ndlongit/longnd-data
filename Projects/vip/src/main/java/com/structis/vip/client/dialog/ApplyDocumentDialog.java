package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.DelegationModelEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.model.DemDomModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.EntiteModel;

public class ApplyDocumentDialog extends Window {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 550;
    private final int HEIGHT = 370;

    private SimpleEventBus bus;
    private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();
    private ClientDocumentMdlServiceAsync clientDocumentMdlServiceAsync = ClientDocumentMdlServiceAsync.Util.getInstance();
    private ListStore<DocumentMdlModel> store = new ListStore<DocumentMdlModel>();
    private CheckBoxListView<DocumentMdlModel> view;
    private Integer group;

    private Button btnSave;
    private Button btnCancel;

    public ApplyDocumentDialog(SimpleEventBus bus) {
        this.bus = bus;
        this.initUI();
    }

    public void initUI() {
        this.setHeading(this.messages.delegationdocumentheading());
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setResizable(false);
        this.setModal(true);
        this.setButtonAlign(HorizontalAlignment.RIGHT);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(300);

        this.view = new CheckBoxListView<DocumentMdlModel>();
        this.view.setStore(this.store);
        this.view.setDisplayProperty(DocumentMdlModel.DOM_NAME);
        this.view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        if (SessionServiceImpl.getInstance().getEntiteContext() != null
                && SharedConstant.ENTITE_ID_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
            this.view.addListener(Events.Select, new Listener<ListViewEvent<DocumentMdlModel>>() {

                @Override
                public void handleEvent(ListViewEvent<DocumentMdlModel> le) {
                    List<DocumentMdlModel> checkedList = ApplyDocumentDialog.this.view.getChecked();
                    InputElement ie = null;
                    NodeList<Element> nodes = ApplyDocumentDialog.this.el().select(ApplyDocumentDialog.this.view.getCheckBoxSelector());
                    int index = ApplyDocumentDialog.this.store.indexOf(le.getModel());
                    if (index != -1) {
                        Element e = nodes.getItem(index);
                        if (com.google.gwt.dom.client.Element.is(e)) {
                            ie = InputElement.as(e);
                            if (ie.isChecked() == false) {
                                for (int i = 0; i < checkedList.size(); i++) {
                                    if (!checkedList.get(i).equals(le.getModel())) {
                                        ApplyDocumentDialog.this.view.setChecked(checkedList.get(i), false);
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }

        this.btnSave = new Button(this.messages.commonApplybutton());
        this.btnCancel = new Button(this.messages.commonAnnulerButton());

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if ((ApplyDocumentDialog.this.group != null) && (ApplyDocumentDialog.this.group != 0)) {
                    ApplyDocumentDialog.this.clientDemDomServiceAsync.deleteByGroup(ApplyDocumentDialog.this.group, new AsyncCallback<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            List<DemDomModel> demDoms = new ArrayList<DemDomModel>();
                            for (DocumentMdlModel mdl : ApplyDocumentDialog.this.view.getChecked()) {
                                DemDomModel demDom = new DemDomModel();
                                demDom.setGroup(ApplyDocumentDialog.this.group);
                                demDom.setDocumentMdl(mdl);
                                demDoms.add(demDom);
                            }

                            if (demDoms.size() != 0) {
                                ApplyDocumentDialog.this.clientDemDomServiceAsync.insert(demDoms, ApplyDocumentDialog.this.group,
                                        new AsyncCallback<Integer>() {

                                            @Override
                                            public void onSuccess(Integer arg0) {
                                                ApplyDocumentDialog.this.disableEvents(true);
                                                try {
                                                    DelegationModelEvent event = new DelegationModelEvent();
                                                    event.setMode(DelegationModelEvent.MODE_IS_UPDATE_DOCUMENT);
                                                    event.setGroup(0);
                                                    ApplyDocumentDialog.this.bus.fireEvent(event);
                                                } catch (Exception e) {
                                                }
                                                ApplyDocumentDialog.this.disableEvents(false);
                                            }

                                            @Override
                                            public void onFailure(Throwable arg0) {
                                            }
                                        });
                            } else {
                                DelegationModelEvent event = new DelegationModelEvent();
                                event.setMode(DelegationModelEvent.MODE_IS_UPDATE_DOCUMENT);
                                event.setGroup(0);
                                ApplyDocumentDialog.this.bus.fireEvent(event);
                            }
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });
                }
                ApplyDocumentDialog.this.hide();
            }
        });

        this.btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ApplyDocumentDialog.this.hide();
            }
        });

        this.addButton(this.btnSave);
        this.addButton(this.btnCancel);

        main.add(this.view);
        this.add(main);
    }

    public void setData(EntiteModel entiteModel, final Integer group) {
        this.group = group;

        this.clientDocumentMdlServiceAsync.getAllDocumentModelsByEntite(entiteModel, new AsyncCallback<List<DocumentMdlModel>>() {

            @Override
            public void onSuccess(List<DocumentMdlModel> arg0) {
                ApplyDocumentDialog.this.store.removeAll();
                ApplyDocumentDialog.this.store.add(arg0);
                ApplyDocumentDialog.this.clientDemDomServiceAsync.getAllDemDomsByDemGroup(group, new AsyncCallback<List<DemDomModel>>() {

                    @Override
                    public void onSuccess(List<DemDomModel> arg0) {
                        for (DemDomModel demDom : arg0) {
                            for (int i = 0; i < ApplyDocumentDialog.this.store.getCount(); i++) {
                                if (ApplyDocumentDialog.this.store.getAt(i).getId().equals(demDom.getDocumentMdl().getId())) {
                                    ApplyDocumentDialog.this.view.setChecked(ApplyDocumentDialog.this.store.getAt(i), true);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                    }
                });
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }
}
