package com.structis.vip.client.dialog;

import java.util.Date;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.DelegationEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationDocumentServiceAsync;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.DelegationDocumentModel;
import com.structis.vip.shared.model.DelegationModel;

public class UploadDocumentDialog extends Window {

    private final Messages messages = GWT.create(Messages.class);
    private final FormData formData = new FormData("95%");

    private final int WIDTH = 400;

    private final ClientDelegationDocumentServiceAsync clientDelegationDocumentServiceAsync = ClientDelegationDocumentServiceAsync.Util.getInstance();

    private FormPanel panel;
    private TextField<String> tfDescription;
    private FileUploadField ffFile;
    private Button btnAmnuler;
    private Button btnSave;

    private DelegationModel delegationModel;
    private SimpleEventBus bus;
    private String generateFileName = "";

    private Label lblErrorMessage;

    public UploadDocumentDialog(SimpleEventBus bus, DelegationModel delegationModel) {
        Date now = new Date();
        this.generateFileName = now.getTime() + CommonUtils.randomString(12) + ".pdf";
        this.delegationModel = delegationModel;
        this.bus = bus;
        this.initUI();
        this.addErrorLabel();
    }

    private void addErrorLabel() {
        this.lblErrorMessage = new Label("");
        this.lblErrorMessage.setStyleName("errorMessage");
        this.add(this.lblErrorMessage, new FlowData(new Margins(0, 0, 10, 10)));
    }

    public void initUI() {
        this.setHeading(this.messages.delegationdocumentdialogheading());
        this.setSize(this.WIDTH, -1);
        this.setResizable(false);
        this.setModal(true);
        this.setButtonAlign(HorizontalAlignment.RIGHT);

        this.panel = new FormPanel();
        this.panel.setHeaderVisible(false);
        this.panel.setAction(GWT.getHostPageBaseURL() + ".uploadDelegationDocumentServiceServlet");
        this.panel.setFrame(false);
        this.panel.setBorders(false);
        this.panel.setBodyBorder(false);
        this.panel.setEncoding(Encoding.MULTIPART);
        this.panel.setMethod(Method.POST);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.tfDescription = new TextField<String>();
        this.tfDescription.setFieldLabel(this.messages.delegationdocumentdialogdescription());
        this.tfDescription.setName("Description");
        this.tfDescription.setMaxLength(150);
        this.tfDescription.setAllowBlank(false);
        this.panel.add(this.tfDescription, this.formData);

        this.ffFile = new FileUploadField();
        this.ffFile.setAllowBlank(false);
        this.ffFile.setName(this.generateFileName);
        this.ffFile.setFieldLabel(this.messages.delegationdocumentdialogfile());
        this.panel.add(this.ffFile, this.formData);

        this.btnAmnuler = new Button(this.messages.commonAnnulerButton());
        this.btnSave = new Button(this.messages.commonValiderButton());

        this.add(this.panel);
        this.addButton(this.btnAmnuler);
        this.addButton(this.btnSave);

        this.btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                UploadDocumentDialog.this.hide();
            }
        });

        this.panel.addListener(Events.BeforeSubmit, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                String fileName = UploadDocumentDialog.this.ffFile.getFileInput().getValue();
                if (fileName != null && !"".equals(fileName)) {
                    int lastDot = fileName.lastIndexOf(".");
                    String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
                    if (!ConstantClient.PDF_EXTENSION_FILE.equals(extFile)) {
                        UploadDocumentDialog.this.lblErrorMessage.setText(UploadDocumentDialog.this.messages.delegationdocumentdialogmsgacceptpdf());
                        be.setCancelled(true);
                    }
                }
            }
        });

        this.panel.addListener(Events.Submit, new Listener<FormEvent>() {

            @Override
            public void handleEvent(FormEvent be) {
                UploadDocumentDialog.this.save(UploadDocumentDialog.this.generateFileName);
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (UploadDocumentDialog.this.panel.isValid()) {
                    UploadDocumentDialog.this.panel.submit();
                }
            }
        });
    }

    private void save(String fileName) {
        DelegationDocumentModel model = new DelegationDocumentModel();
        model.setDelegation(this.delegationModel);
        model.setDescription(this.tfDescription.getValue());
        model.setFileName(fileName);
        this.clientDelegationDocumentServiceAsync.insert(model, new AsyncCallback<DelegationDocumentModel>() {

            @Override
            public void onSuccess(DelegationDocumentModel result) {
                if (result != null) {
                    Info.display(UploadDocumentDialog.this.messages.commoninfo(),
                            UploadDocumentDialog.this.messages.delegationdocumentdialogcreatesuccess());
                    DelegationEvent event = new DelegationEvent();
                    event.setMode(DelegationEvent.MODE_IS_UPDATED_DOCUMENT);
                    UploadDocumentDialog.this.bus.fireEvent(event);
                    UploadDocumentDialog.this.hide();
                } else {
                    UploadDocumentDialog.this.lblErrorMessage.setText(UploadDocumentDialog.this.messages.delegationdocumentdialogcreatefailed());
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                UploadDocumentDialog.this.lblErrorMessage.setText(UploadDocumentDialog.this.messages.delegationdocumentdialogcreatefailed());
            }
        });

    }
}
