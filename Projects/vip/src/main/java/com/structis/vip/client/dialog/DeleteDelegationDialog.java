package com.structis.vip.client.dialog;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.DeleteDelegationEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationServiceAsync;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.DelegationModel;

public class DeleteDelegationDialog extends Window {

    private final int WIDTH = 550;
    private final int HEIGHT = 300;

    private final Messages messages = GWT.create(Messages.class);
    private ClientDelegationServiceAsync clientDelegantService = ClientDelegationServiceAsync.Util.getInstance();
    private DelegationModel delegationModel;

    private Button btnDelete;
    private Button btnNo;
    private SimpleEventBus bus;

    public DeleteDelegationDialog(DelegationModel delegationModel, SimpleEventBus bus) {
        this.delegationModel = delegationModel;
        this.bus = bus;

        this.initUI();
    }

    public void initUI() {
        LayoutContainer main = new LayoutContainer();
        main.setStyleAttribute("margin", "20px");
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.LEFT);
        main.setLayout(flLeft);

        main.add(this.createLabelField(this.messages.commonPerimetre(), this.delegationModel.getPerimeter().getName()));
        main.add(this.createLabelField(this.messages.nature(), this.delegationModel.getDelegationNature().getName()));
        main.add(this.createLabelField(this.messages.delegationformprincipale(), this.delegationModel.getDelegationType().getName()));
        main.add(this.createLabelField(this.messages.delegationformdelegant(), this.delegationModel.getDelegant().getFullnameNoSeparater()));
        main.add(this.createLabelField(this.messages.delegationformdelegataire(), this.delegationModel.getDelegataire().getFullnameNoSeparater()));
        main.add(this.createLabelField(this.messages.delegationformdatedebut(), (this.delegationModel.getStartDate() != null) ? DateTimeFormat
                .getFormat("dd/MM/yyyy").format(this.delegationModel.getStartDate()) : ""));

        main.add(this.createLabelField(this.messages.delegationformdatefin(),
                (this.delegationModel.getEndDate() != null) ? DateTimeFormat.getFormat("dd/MM/yyyy").format(this.delegationModel.getEndDate()) : ""));

        this.btnDelete = new Button(this.messages.commonSupprimer());
        this.btnNo = new Button(this.messages.commonNon());

        this.addButton(this.btnDelete);
        this.addButton(this.btnNo);

        this.btnNo.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DeleteDelegationDialog.this.hide();
            }
        });

        this.btnDelete.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DeleteDelegationDialog.this.clientDelegantService.delete(DeleteDelegationDialog.this.delegationModel, new AsyncCallback<Boolean>() {

                    @Override
                    public void onSuccess(Boolean arg0) {
                        Info.display(DeleteDelegationDialog.this.messages.commonInfoHeader(),
                                DeleteDelegationDialog.this.messages.delegationmodeldeletesuccessfully());
                        DeleteDelegationEvent event = new DeleteDelegationEvent();
                        event.setModel(DeleteDelegationDialog.this.delegationModel);
                        DeleteDelegationDialog.this.bus.fireEvent(event);

                        DeleteDelegationDialog.this.hide();
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        String message = caught.getMessage();
                        if (caught instanceof DelegationException) {
                            message = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());
                        }
                        Info.display(DeleteDelegationDialog.this.messages.commonerror(), message);
                        DeleteDelegationDialog.this.hide();
                    }
                });
            }
        });

        this.add(main);
        this.setHeading(this.messages.commonSupprimer());
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setModal(true);
        this.setButtonAlign(HorizontalAlignment.RIGHT);
    }

    private LabelField createLabelField(String label, String value) {
        LabelField lb = new LabelField();
        lb.setLabelStyle("width: 130px;");
        lb.setStyleAttribute("width", "300px");
        lb.setFieldLabel(label);
        lb.setText(value);
        return lb;
    }

    public DelegationModel getDelegationModel() {
        return this.delegationModel;
    }

    public void setDelegationModel(DelegationModel delegationModel) {
        this.delegationModel = delegationModel;
    }
}
