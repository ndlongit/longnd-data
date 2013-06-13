package com.structis.vip.client.panel.control;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.control.RefreshExternControllerGridEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientExternControllerControlServiceAsync;
import com.structis.vip.client.service.ClientExternControllerServiceAsync;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.ExternControllerModel;

public class ApplyExternControllerDialog extends Window {

    private final Messages messages = GWT.create(Messages.class);
    private final int WIDTH = 550;
    private final int HEIGHT = 370;

    private SimpleEventBus bus;
    private ClientExternControllerControlServiceAsync clientExternControllerControlServiceAsync = ClientExternControllerControlServiceAsync.Util
            .getInstance();
    private ClientExternControllerServiceAsync clientExternControllerServiceAsync = ClientExternControllerServiceAsync.Util.getInstance();
    private ListStore<ExternControllerModel> store = new ListStore<ExternControllerModel>();
    private CheckBoxListView<ExternControllerModel> view;
    private ControlModel control;

    private Button btnSave;
    private Button btnCancel;

    public ApplyExternControllerDialog(SimpleEventBus bus) {
        this.bus = bus;
        this.initUI();
    }

    public void initUI() {
        this.setHeading(messages.controlerdialogheading());
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setResizable(false);
        this.setModal(true);
        this.setButtonAlign(HorizontalAlignment.RIGHT);

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(300);

        this.view = new CheckBoxListView<ExternControllerModel>();
        this.view.setStore(this.store);
        // view.setDisplayProperty(ExternControllerModel.EXC_LASTNAME);
        this.view.setTemplate(this.createTemplate());

        this.view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // if (SessionServiceImpl.getInstance().getEntiteContext() != null &&
        // ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
        // view.addListener(Events.Select, new Listener<ListViewEvent<ExternControllerModel>>() {
        // @Override
        // public void handleEvent(ListViewEvent<ExternControllerModel> le) {
        // List<ExternControllerModel> checkedList = view.getChecked();
        // InputElement ie = null;
        // NodeList<Element> nodes = el().select(view.getCheckBoxSelector());
        // int index = store.indexOf(le.getModel());
        // if (index != -1) {
        // Element e = nodes.getItem(index);
        // if (InputElement.is(e)) {
        // ie = InputElement.as(e);
        // if (ie.isChecked() == false) {
        // for (int i=0; i<checkedList.size(); i++) {
        // if (!checkedList.get(i).equals(le.getModel())) {
        // view.setChecked(checkedList.get(i), false);
        // }
        // }
        // }
        // }
        // }
        // }
        // });
        // }

        this.btnSave = new Button(messages.commonApplybutton());
        this.btnCancel = new Button(messages.commonAnnulerButton());

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // if ((control != null) && (control.getId() != 0)) {
                clientExternControllerControlServiceAsync.deleteByControl(
                        control.getId(), new AsyncCallback<Boolean>() {

                            @Override
                            public void onSuccess(Boolean arg0) {
                                List<ExtControllerControlModel> eccs = new ArrayList<ExtControllerControlModel>();
                                for (ExternControllerModel mdl : view.getChecked()) {
                                    ExtControllerControlModel ecc = new ExtControllerControlModel();
                                    ecc.setControl(control);
                                    ecc.setExternalController(mdl);
                                    eccs.add(ecc);
                                }

                                // if (eccs.size() != 0) {
                                // clientExternControllerControlServiceAsync.insert(eccs, new AsyncCallback<Void>() {
                                // @Override
                                // public void onSuccess(Void arg0) {
                                // disableEvents(true);
                                // RefreshExternControllerGridEvent recge = new RefreshExternControllerGridEvent();
                                // bus.fireEvent(recge);
                                // disableEvents(false);
                                // }
                                //
                                // @Override
                                // public void onFailure(Throwable arg0) {
                                // }
                                // });
                                // } else {//
                                // }
                                // if (eccs.size() != 0) {
                                RefreshExternControllerGridEvent recge = new RefreshExternControllerGridEvent();
                                recge.setExternControllers(eccs);
                                bus.fireEvent(recge);
                                // }

                            }

                            @Override
                            public void onFailure(Throwable arg0) {
                            }
                        });
                // }
                hide();
            }
        });

        this.btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });

        this.addButton(this.btnSave);
        this.addButton(this.btnCancel);

        main.add(this.view);
        this.add(main);
    }

    private String createTemplate() {
        String spacing = GXT.isIE && !GXT.isStrict ? "0" : "3";
        String template = "<tpl for=\".\"><div class='x-view-item x-view-item-check'><table cellspacing='"
                + spacing
                + "' cellpadding=0><tr><td><input class=\"x-view-item-checkbox\" type=\"checkbox\" /></td><td><td>{name}</td></tr></table></div></tpl>";
        return template;
    }

    public void setData(EntiteModel entiteModel, final ControlModel control, final List<ExtControllerControlModel> extControllers) {
        this.control = control;

        this.clientExternControllerServiceAsync.findAll(new AsyncCallback<List<ExternControllerModel>>() {

            @Override
            public void onSuccess(List<ExternControllerModel> arg0) {
                store.removeAll();
                store.add(arg0);
                for (ExtControllerControlModel ecc : extControllers) {
                    for (int i = 0; i < store.getCount(); i++) {
                        if (store.getAt(i).getId().equals(ecc.getExternalController().getId())) {
                            view.setChecked(store.getAt(i), true);
                        }
                    }
                }
                // if (control.getId() != null) {
                // clientExternControllerControlServiceAsync.findByControl(control.getId(), new AsyncCallback<List<ExtControllerControlModel>>() {
                // @Override
                // public void onSuccess(List<ExtControllerControlModel> arg0) {
                // for (ExtControllerControlModel ecc : arg0) {
                // for (int i = 0; i < store.getCount(); i++) {
                // if (store.getAt(i).getId().equals(ecc.getExternalController().getId())) {
                // view.setChecked(store.getAt(i), true);
                // }
                // }
                // }
                // }
                //
                // @Override
                // public void onFailure(Throwable arg0) {
                // }
                // });
                // } else {
                // for (ExtControllerControlModel ecc : control.getExternControllers()) {
                // for (int i = 0; i < store.getCount(); i++) {
                // if (store.getAt(i).getId().equals(ecc.getExternalController().getId())) {
                // view.setChecked(store.getAt(i), true);
                // }
                // }
                // }
                // }
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }
}
