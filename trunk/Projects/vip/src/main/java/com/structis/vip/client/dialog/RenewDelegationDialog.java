package com.structis.vip.client.dialog;

import java.util.Date;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.RenewDelegationEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.shared.model.DelegationModel;

public class RenewDelegationDialog extends Window {

    private final int WIDTH = 400;
    private final int HEIGHT = 150;

    private final Messages messages = GWT.create(Messages.class);
    private DelegationModel delegationModel;

    private Button btnChooseDate;
    private Button btnNo;
    private SimpleEventBus bus;
    DateField df;
    private int typeRenew = 0; // 0: renew , 1: replace delegant

    public RenewDelegationDialog(DelegationModel delegationModel, SimpleEventBus bus) {
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

        this.df = new DateField();
        this.df.setLabelStyle("width: 100px;");
        this.df.setId("dfFin");
        this.df.setStyleAttribute("width", "200px");
        this.df.setFieldLabel(this.messages.delegationformdatefin());
        this.df.setAllowBlank(false);
        this.df.setEditable(false);
        this.df.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
        this.df.setValue(new Date());
        main.add(this.df);
        this.btnChooseDate = new Button(this.messages.commonDialogOuiButton());
        this.btnNo = new Button(this.messages.commonNon());
        this.addButton(this.btnChooseDate);
        this.addButton(this.btnNo);
        this.btnNo.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                RenewDelegationDialog.this.hide();
            }
        });
        this.btnChooseDate.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                RenewDelegationDialog.this.delegationModel.setEndDate(RenewDelegationDialog.this.df.getValue());
                RenewDelegationEvent event = new RenewDelegationEvent();
                event.setTypeRenew(RenewDelegationDialog.this.typeRenew);
                event.setModel(RenewDelegationDialog.this.delegationModel);
                RenewDelegationDialog.this.bus.fireEvent(event);
                RenewDelegationDialog.this.hide();
            }
        });

        this.add(main);
        this.setHeading(this.messages.delegationrenewheading());
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setModal(true);
        this.setButtonAlign(HorizontalAlignment.RIGHT);
    }

    public DelegationModel getDelegationModel() {
        return this.delegationModel;
    }

    public void setDelegationModel(DelegationModel delegationModel) {
        this.delegationModel = delegationModel;
    }

    public void setTypeRenew(int typeRenew) {
        this.typeRenew = typeRenew;
    }

    public int getTypeRenew() {
        return this.typeRenew;
    }
}
