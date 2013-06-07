package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyDocumentEvent;
import com.structis.vip.client.event.ModifyDocumentHandler;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDocTypeServiceAsync;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.exception.DocumentMdlException;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DocumentTypeModel;
import com.structis.vip.shared.model.LanguageModel;

public class DocumentFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 680;

    private ListStore<DocumentTypeModel> documentTypeStore = new ListStore<DocumentTypeModel>();

    private ListStore<LanguageModel> languageStore = new ListStore<LanguageModel>();
    private ClientLanguageServiceAsync clientLanguageService = ClientLanguageServiceAsync.Util.getInstance();
    private ClientDocumentMdlServiceAsync clientDocumentMdlService = ClientDocumentMdlServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private TextField<String> tfName;
    private TextField<String> tfVersion;
    private FileUploadField ffFile;
    private FileUploadField ffTemp;
    private Button btnReset;
    private ComboBox<DocumentTypeModel> cbType;
    private ComboBox<LanguageModel> cbLanguage;
    private Button btnAmnuler;
    private Button btnSave;
    private DocumentMdlModel model;
    private boolean isEdit = true;

    private Label lblErrorMessage;
    private LayoutContainer errorLayout;

    public DocumentFormPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);
        this.setWidth(this.WIDTH);

        this.addHandler();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.initData();
        this.initBackLink();
        this.addErrorLabel();

        this.initUI();
        this.initEvent();
    }

    private void addErrorLabel() {
        this.errorLayout = new LayoutContainer();
        this.errorLayout.setHeight(30);

        this.lblErrorMessage = new Label();
        this.lblErrorMessage.setStyleName("errorMessage");
        this.errorLayout.add(this.lblErrorMessage);
        this.add(this.errorLayout);
        this.errorLayout.setVisible(false);
    }

    private void showErrorLabel(Boolean isShow, String message) {
        this.lblErrorMessage.setText(message);
        this.errorLayout.setVisible(isShow);
    }

    private void addHandler() {
        this.bus.addHandler(ModifyDocumentEvent.getType(), new ModifyDocumentHandler() {

            @Override
            public void onLoadAction(ModifyDocumentEvent event) {
                if (ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM == event.getMode()) {
                    AppUtil.putInAdminEditMode();
                    if (event.getModel() != null) {
                        DocumentFormPanel.this.isEdit = true;
                        DocumentFormPanel.this.model = event.getModel();
                        DocumentFormPanel.this.tfName.setValue(DocumentFormPanel.this.model.getName());
                        DocumentFormPanel.this.tfName.setToolTip(DocumentFormPanel.this.model.getName());
                        DocumentFormPanel.this.cbLanguage.setValue(DocumentFormPanel.this.model.getLanguage());
                        DocumentFormPanel.this.tfVersion.setValue(DocumentFormPanel.this.model.getVersion());
                        DocumentFormPanel.this.cbType.setValue(DocumentFormPanel.this.getDocType(DocumentFormPanel.this.model.getType()));

                        DocumentFormPanel.this.ffFile.setValue(DocumentFormPanel.this.model.getFilename());
                        DocumentFormPanel.this.ffFile.setToolTip(DocumentFormPanel.this.model.getFilename());
                        DocumentFormPanel.this.ffTemp.setValue(DocumentFormPanel.this.model.getTempFilename());
                        if (DocumentFormPanel.this.model.getTempFilename() != null && DocumentFormPanel.this.model.getTempFilename().length() > 0) {
                            DocumentFormPanel.this.ffTemp.setToolTip(DocumentFormPanel.this.model.getTempFilename());
                        }

                    } else {
                        DocumentFormPanel.this.model = null;
                        DocumentFormPanel.this.isEdit = false;
                        DocumentFormPanel.this.panel.reset();
                        DocumentFormPanel.this.panel.clear();
                    }
                }
            }

        });
    }

    private DocumentTypeModel getDocType(String type) {
        if (type == null) {
            return null;
        } else {
            for (DocumentTypeModel dt : this.documentTypeStore.getModels()) {
                if (dt.getName().trim().equals(type.trim())) {
                    return dt;
                }
            }
            DocumentTypeModel newDoc = new DocumentTypeModel();
            newDoc.setName(type);
            return newDoc;
        }

    }

    private void initData() {
        this.languageStore.removeAll();
        this.clientLanguageService.getLanguages(new AsyncCallback<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                DocumentFormPanel.this.languageStore.add(arg0);
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });

        ClientDocTypeServiceAsync.Util.getInstance().getDocTypes(new AsyncCallback<List<DocumentTypeModel>>() {

            @Override
            public void onSuccess(List<DocumentTypeModel> arg0) {
                synchronized (this) {
                    DocumentFormPanel.this.documentTypeStore.removeAll();
                    DocumentFormPanel.this.documentTypeStore.add(arg0);
                    DocumentFormPanel.this.cbType.setValue(DocumentFormPanel.this.getDocType(DocumentFormPanel.this.model.getType()));
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.documentform());
        this.panel.setAction(GWT.getHostPageBaseURL() + ".uploadDocumentServiceServlet");
        this.panel.setFrame(true);
        this.panel.setEncoding(Encoding.MULTIPART);
        this.panel.setMethod(Method.POST);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setLabelWidth(110);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.documentname());
        this.tfName.setName("name");
        this.tfName.setMaxLength(150);
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

        HorizontalPanel p0 = new HorizontalPanel();
        p0.setBorders(false);
        p0.setTableWidth("100%");

        this.ffFile = new FileUploadField();
        this.ffFile.setAllowBlank(false);
        this.ffFile.setWidth("460");
        this.ffFile.setName("uploadedfile");
        // ffFile.setFieldLabel(messages.documentfile());
        this.ffFile.setLabelStyle("x-form-item-label");
        this.ffFile.getMessages().setBrowseText(this.messages.documentBrowseText());
        Button btnReset0 = new Button(this.messages.documentclearuploadfile());
        btnReset0.setWidth(60);
        btnReset0.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DocumentFormPanel.this.ffFile.reset();
            }
        });
        Label lblTmpFile0 = new Label(this.messages.documentfile() + ":");
        lblTmpFile0.setStyleName("x-form-item-label");
        lblTmpFile0.setWidth("115");
        p0.add(lblTmpFile0);
        p0.add(this.ffFile);
        p0.add(btnReset0);
        this.panel.add(p0, this.formData);

        // panel.add(ffFile, formData);

        HorizontalPanel p = new HorizontalPanel();
        p.setBorders(false);
        p.setTableWidth("100%");

        this.ffTemp = new FileUploadField();
        this.ffTemp.setName("temporaryfile");
        this.ffTemp.setAllowBlank(true);
        // ffTemp.setFieldLabel(messages.documentfiletemp());
        this.ffTemp.setLabelStyle("x-form-item-label");
        this.ffTemp.setWidth("460");

        this.ffTemp.getMessages().setBrowseText(this.messages.documentBrowseText());
        this.btnReset = new Button(this.messages.documentclearuploadfile());
        this.btnReset.setWidth(60);
        this.btnReset.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DocumentFormPanel.this.ffTemp.reset();
            }
        });
        Label lblTmpFile = new Label(this.messages.documentfiletemp() + ":");
        lblTmpFile.setStyleName("x-form-item-label");
        lblTmpFile.setWidth("115");
        p.add(lblTmpFile);
        p.add(this.ffTemp);
        p.add(this.btnReset);
        this.panel.add(p, this.formData);

        this.cbType = new ComboBox<DocumentTypeModel>();
        this.cbType.setStore(this.documentTypeStore);
        this.cbType.setFieldLabel(this.messages.documenttype());
        this.cbType.setDisplayField(DocumentTypeModel.DOC_TYPE_NAME);
        this.cbType.setName("type");
        this.cbType.setEditable(false);
        this.cbType.setAllowBlank(false);
        this.cbType.setTriggerAction(TriggerAction.ALL);
        this.panel.add(this.cbType, this.formData);

        this.cbLanguage = new ComboBox<LanguageModel>();
        this.cbLanguage.setAllowBlank(false);
        this.cbLanguage.setEditable(false);
        this.cbLanguage.setFieldLabel(this.messages.documentlanguage());
        this.cbLanguage.setName("language");
        this.cbLanguage.setDisplayField(LanguageModel.LAG_NAME);
        this.cbLanguage.setTriggerAction(TriggerAction.ALL);
        this.cbLanguage.setStore(this.languageStore);
        this.panel.add(this.cbLanguage, this.formData);

        this.tfVersion = new TextField<String>();
        this.tfVersion.setFieldLabel(this.messages.documentversion());
        this.tfVersion.setName("version");
        this.tfVersion.setMaxLength(50);
        this.tfVersion.setAllowBlank(true);
        this.panel.add(this.tfVersion, this.formData);

        this.btnAmnuler = new Button(this.messages.commonAnnulerButton());
        this.btnSave = new Button(this.messages.commonValiderButton());

        this.panel.addButton(this.btnAmnuler);
        this.panel.addButton(this.btnSave);

        this.panel.getButtonBar().setStyleAttribute("padding-right", "16px");

        this.add(this.panel);
    }

    private void initBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(this.WIDTH, -1);
        Label lblBack = new Label(this.messages.documentback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
                    DocumentFormPanel.this.bus.fireEvent(contentEvent);
                }
            }
        });

        this.add(backLink);
    }

    private void initEvent() {
        this.btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
                contentEvent.setEvent(new ModifyDocumentEvent());
                DocumentFormPanel.this.bus.fireEvent(contentEvent);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (DocumentFormPanel.this.panel.isValid()) {
                    DocumentFormPanel.this.panel.submit();
                }
            }
        });

        this.panel.addListener(Events.BeforeSubmit, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                DocumentFormPanel.this.showErrorLabel(false, "");

                if (DocumentFormPanel.this.ffFile.getValue() != null) {
                    String fileName = DocumentFormPanel.this.ffFile.getValue();

                    if (fileName != null && !"".equals(fileName)) {
                        int lastDot = fileName.lastIndexOf(".");
                        String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
                        if (!ClientConstant.DOC_EXTENSION_FILE.equals(extFile)) {
                            DocumentFormPanel.this.showErrorLabel(true, "Document doit être un fichier doc");
                            be.setCancelled(true);
                        }
                    }
                }

                if (DocumentFormPanel.this.ffTemp.getValue() != null) {
                    String fileName = DocumentFormPanel.this.ffTemp.getValue();

                    if (fileName != null && !"".equals(fileName)) {
                        int lastDot = fileName.lastIndexOf(".");
                        String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
                        if (!ClientConstant.DOC_EXTENSION_FILE.equals(extFile)) {
                            DocumentFormPanel.this.showErrorLabel(true, "Document doit être un fichier doc");
                            be.setCancelled(true);
                        }
                    }
                }
            }
        });

        this.panel.addListener(Events.Submit, new Listener<FormEvent>() {

            @Override
            public void handleEvent(FormEvent be) {
                String fileName = null;
                String tempFileName = null;
                if ((DocumentFormPanel.this.ffFile.getValue() != null) && (!"".equals(DocumentFormPanel.this.ffFile.getValue()))) {
                    fileName = DocumentFormPanel.this.ffFile.getValue();
                }
                if ((DocumentFormPanel.this.ffTemp.getValue() != null) && (!"".equals(DocumentFormPanel.this.ffTemp.getValue()))) {
                    tempFileName = DocumentFormPanel.this.ffTemp.getValue();
                }
                DocumentFormPanel.this.save(fileName, tempFileName);
            }
        });
    }

    private void save(String fileName, String tempFileName) {
        if (this.model == null) {
            this.model = new DocumentMdlModel();
        }
        this.model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
        this.model.setName(this.tfName.getValue());
        this.model.setType(this.cbType.getValue().getName());
        this.model.setLanguage(this.cbLanguage.getValue());
        this.model.setVersion(this.tfVersion.getValue());

        // if (ConstantClient.ENTITE_ID_IS_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
        // model.setVariables("etjStatut, delegataireAddress, etjRegistrationAddress, etjCapital, delegataireFirstname, delegataireTitle, endDate, amount1, etjRegistrationId, place1, amount3, startDate, etjAddress, etjName, amount2, delegataireLastname, amount4, delegantFirstname, comment1, delegantLastname, delegantTitle, delegantStatut, delegataireStatut");
        // } else {
        // model.setVariables("amount1,amount2,amount3,amount4,amount5,date1,date2,date3,dateDelegation,delegantDateConseil,delegantDateEffet,delegantFirstname,delegantLastname,delegantNom1,delegantPrenom1,delegantQualite,delegantQualite1,delegantStatutConseil,delegataireAddress,delegataireDateFormation,delegataireDateNaissance,delegataireFirstname,delegataireIntituleFormation,delegataireLastname,delegataireLieuNaissance,delegataireNationalite,delegataireQualite,demandeurFirstname,demandeurLastname,endDate,etjAddress,etjCapital,etjName,etjRegistrationAddress,etjRegistrationId,etjStatut,operations,perChantierCity,perChantierEndDate,perChantierID,perChantierName,perChantierPlannedEndDate,perChantierStartDate,place1,place2,place3,startDate,zone");
        // }

        if (null != fileName) {
            this.model.setFilename(fileName);
        }

        if (null != tempFileName) {
            this.model.setTempFilename(tempFileName);
        }

        if (this.isEdit == false) {
            this.clientDocumentMdlService.insert(this.model, new AsyncCallback<DocumentMdlModel>() {

                @Override
                public void onSuccess(DocumentMdlModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);

                    ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
                    subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);

                    contentEvent.setEvent(subEvent);
                    DocumentFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    String details = caught.getMessage();
                    if (caught instanceof DocumentMdlException) {
                        details = ExceptionMessageHandler.getErrorMessage(((DocumentMdlException) caught).getCode());
                    }
                    Info.display(DocumentFormPanel.this.messages.commonerror(), details);
                }
            });
        } else {
            this.clientDocumentMdlService.update(this.model, new AsyncCallback<DocumentMdlModel>() {

                @Override
                public void onSuccess(DocumentMdlModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);

                    ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
                    subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);

                    contentEvent.setEvent(subEvent);
                    DocumentFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    String details = caught.getMessage();
                    if (caught instanceof DocumentMdlException) {
                        details = ExceptionMessageHandler.getErrorMessage(((DocumentMdlException) caught).getCode());
                    }
                    Info.display(DocumentFormPanel.this.messages.commonerror(), details);
                }
            });
        }
    }
}
