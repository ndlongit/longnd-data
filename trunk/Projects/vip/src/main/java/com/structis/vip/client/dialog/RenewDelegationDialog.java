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
import com.structis.vip.client.constant.ClientConstant;
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

        initUI();
    }

    public void initUI() {
        LayoutContainer main = new LayoutContainer();
        main.setStyleAttribute("margin", "20px");
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.LEFT);
        main.setLayout(flLeft);

        df = new DateField();
        df.setLabelStyle("width: 100px;");
        df.setId("dfFin");
        df.setStyleAttribute("width", "200px");
        df.setFieldLabel(messages.delegationformdatefin());
        df.setAllowBlank(false);
        df.setEditable(false);
        df.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        df.setValue(new Date());
        main.add(df);
        btnChooseDate = new Button(messages.commonDialogOuiButton());
        btnNo = new Button(messages.commonNon());
        addButton(btnChooseDate);
        addButton(btnNo);
        btnNo.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });
        btnChooseDate.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                delegationModel.setEndDate(df.getValue());
                RenewDelegationEvent event = new RenewDelegationEvent();
                event.setTypeRenew(typeRenew);
                event.setModel(delegationModel);
                bus.fireEvent(event);
                hide();
            }
        });

        add(main);
        setHeading(messages.delegationrenewheading());
        setSize(WIDTH, HEIGHT);
        setModal(true);
        setButtonAlign(HorizontalAlignment.RIGHT);
    }

    public DelegationModel getDelegationModel() {
        return delegationModel;
    }

    public void setDelegationModel(DelegationModel delegationModel) {
        this.delegationModel = delegationModel;
    }

    public void setTypeRenew(int typeRenew) {
        this.typeRenew = typeRenew;
    }

    public int getTypeRenew() {
        return typeRenew;
    }
}
