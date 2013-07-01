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
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.exception.ExceptionMessageHandler;
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

    private FormPanel panel;
    private TextField<String> tfName;
    private TextField<String> tfVersion;
    private FileUploadField mainFile;
    private FileUploadField tempFile;
    private FileUploadField subDelelegationFile;
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

        setLayout(new FlowLayout(10));
        setScrollMode(Scroll.AUTO);
        setWidth(WIDTH);

        addHandler();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        initData();
        initBackLink();
        addErrorLabel();

        initUI();
        initEvent();
    }

    private void addErrorLabel() {
        errorLayout = new LayoutContainer();
        errorLayout.setHeight(30);

        lblErrorMessage = new Label();
        lblErrorMessage.setStyleName("errorMessage");
        errorLayout.add(lblErrorMessage);
        add(errorLayout);
        errorLayout.setVisible(false);
    }

    private void showErrorLabel(Boolean isShow, String message) {
        lblErrorMessage.setText(message);
        errorLayout.setVisible(isShow);
    }

    private void addHandler() {
        bus.addHandler(ModifyDocumentEvent.getType(), new ModifyDocumentHandler() {

            @Override
            public void onLoadAction(ModifyDocumentEvent event) {
                if (ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM == event.getMode()) {
                    AppUtil.putInAdminEditMode();
                    if (event.getModel() != null) {
                        isEdit = true;
                        model = event.getModel();
                        tfName.setValue(model.getName());
                        tfName.setToolTip(model.getName());
                        cbLanguage.setValue(model.getLanguage());
                        tfVersion.setValue(model.getVersion());
                        cbType.setValue(getDocType(model.getType()));

                        mainFile.setValue(model.getFilename());
                        mainFile.setToolTip(model.getFilename());
                        
                        String tempFilename = model.getTempFilename();
                        if (tempFilename != null && tempFilename.length() > 0) {
                            tempFile.setValue(tempFilename);
                            tempFile.setToolTip(tempFilename);
                        }
                        
                        String subDelegationFilename = model.getSubDelFilename();
                        if (subDelegationFilename != null && subDelegationFilename.length() > 0) {
                            subDelelegationFile.setValue(subDelegationFilename);
                            subDelelegationFile.setToolTip(subDelegationFilename);
                        }

                    } else {
                        model = null;
                        isEdit = false;
                        panel.reset();
                        panel.clear();
                    }
                }
            }
        });
    }

    private DocumentTypeModel getDocType(String type) {
        if (type == null) {
            return null;
        } else {
            for (DocumentTypeModel dt : documentTypeStore.getModels()) {
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
        languageStore.removeAll();
        clientLanguageService.getLanguages(new AsyncCallbackWithErrorResolution<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                languageStore.add(arg0);
            }
        });

        ClientDocTypeServiceAsync.Util.getInstance().getDocTypes(new AsyncCallbackWithErrorResolution<List<DocumentTypeModel>>() {

            @Override
            public void onSuccess(List<DocumentTypeModel> arg0) {
                synchronized (this) {
                    documentTypeStore.removeAll();
                    documentTypeStore.add(arg0);
                    cbType.setValue(getDocType(model.getType()));
                }
            }
        });
    }

    private void initUI() {
        panel = new FormPanel();
        panel.setHeading(messages.documentform());
        panel.setAction(GWT.getHostPageBaseURL() + ".uploadDocumentServiceServlet");
        panel.setFrame(true);
        panel.setEncoding(Encoding.MULTIPART);
        panel.setMethod(Method.POST);
        panel.setButtonAlign(HorizontalAlignment.RIGHT);
        panel.setLabelWidth(110);
        panel.setWidth(WIDTH);

        tfName = new TextField<String>();
        tfName.setFieldLabel(messages.documentname());
        tfName.setName("name");
        tfName.setMaxLength(150);
        tfName.setAllowBlank(false);
        panel.add(tfName, formData);
        
        mainFile = addFileUpload("uploadedFile", messages.documentfile(), false);
        tempFile = addFileUpload("temporaryfile", messages.documentfiletemp(), true);
        subDelelegationFile = addFileUpload("subDelelegationFile", messages.documentFileSubDelegation(), true);

        cbType = new ComboBox<DocumentTypeModel>();
        cbType.setStore(documentTypeStore);
        cbType.setFieldLabel(messages.documenttype());
        cbType.setDisplayField(DocumentTypeModel.NAME);
        cbType.setName("type");
        cbType.setEditable(false);
        cbType.setAllowBlank(false);
        cbType.setTriggerAction(TriggerAction.ALL);
        panel.add(cbType, formData);

        cbLanguage = new ComboBox<LanguageModel>();
        cbLanguage.setAllowBlank(false);
        cbLanguage.setEditable(false);
        cbLanguage.setFieldLabel(messages.documentlanguage());
        cbLanguage.setName("language");
        cbLanguage.setDisplayField(LanguageModel.LAG_NAME);
        cbLanguage.setTriggerAction(TriggerAction.ALL);
        cbLanguage.setStore(languageStore);
        panel.add(cbLanguage, formData);

        tfVersion = new TextField<String>();
        tfVersion.setFieldLabel(messages.documentversion());
        tfVersion.setName("version");
        tfVersion.setMaxLength(50);
        tfVersion.setAllowBlank(true);
        panel.add(tfVersion, formData);

        btnAmnuler = new Button(messages.commonAnnulerButton());
        btnSave = new Button(messages.commonValiderButton());

        panel.addButton(btnAmnuler);
        panel.addButton(btnSave);

        panel.getButtonBar().setStyleAttribute("padding-right", "16px");

        add(panel);
    }

    private FileUploadField addFileUpload(String name, String fieldLabel, boolean allowBlank) {
        HorizontalPanel hp = new HorizontalPanel();
        hp.setBorders(false);
        hp.setTableWidth("100%");

        final FileUploadField file = new FileUploadField();
        file.setName(name);
        file.setAllowBlank(allowBlank);
        file.setWidth("460");
        file.setLabelStyle("x-form-item-label");
        file.getMessages().setBrowseText(messages.documentBrowseText());
        Button resetButton = new Button(messages.documentclearuploadfile());
        resetButton.setWidth(60);
        resetButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                file.reset();
            }
        });
        Label lb = new Label(fieldLabel + ":");
        lb.setStyleName("x-form-item-label");
        lb.setWidth("115");
        hp.add(lb);
        hp.add(file);
        hp.add(resetButton);
        panel.add(hp, formData);
        
        return file;
    }

    private void initBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(WIDTH, -1);
        Label lblBack = new Label(messages.documentback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
                    bus.fireEvent(contentEvent);
                }
            }
        });

        add(backLink);
    }

    private void initEvent() {
        btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
                contentEvent.setEvent(new ModifyDocumentEvent());
                bus.fireEvent(contentEvent);
                AppUtil.removeAdminInEditMode();
            }
        });

        btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (panel.isValid()) {
                    panel.submit();
                }
            }
        });

        panel.addListener(Events.BeforeSubmit, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                showErrorLabel(false, "");

                if (mainFile.getValue() != null) {
                    String fileName = mainFile.getValue();

                    if (fileName != null && !"".equals(fileName)) {
                        int lastDot = fileName.lastIndexOf(".");
                        String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
                        if (!ClientConstant.DOC_EXTENSION_FILE.equals(extFile)) {
                            showErrorLabel(true, "Document doit être un fichier doc");
                            be.setCancelled(true);
                        }
                    }
                }

                if (tempFile.getValue() != null) {
                    String fileName = tempFile.getValue();

                    if (fileName != null && !"".equals(fileName)) {
                        int lastDot = fileName.lastIndexOf(".");
                        String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
                        if (!ClientConstant.DOC_EXTENSION_FILE.equals(extFile)) {
                            showErrorLabel(true, "Document doit être un fichier doc");
                            be.setCancelled(true);
                        }
                    }
                }
                showErrorLabel(false, "");

                if (subDelelegationFile.getValue() != null) {
                    String fileName = subDelelegationFile.getValue();

                    if (fileName != null && !"".equals(fileName)) {
                        int lastDot = fileName.lastIndexOf(".");
                        String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
                        if (!ClientConstant.DOC_EXTENSION_FILE.equals(extFile)) {
                            showErrorLabel(true, "Document doit être un fichier doc");
                            be.setCancelled(true);
                        }
                    }
                }
            }
        });

        panel.addListener(Events.Submit, new Listener<FormEvent>() {

            @Override
            public void handleEvent(FormEvent be) {
                String mainFileName = mainFile.getValue();                
                String tempFileName = tempFile.getValue();
                String subDelegationFileName = subDelelegationFile.getValue(); 

                save(mainFileName, tempFileName, subDelegationFileName);
            }
        });
    }

    private void save(String fileName, String tempFileName, String subDelegationFileName) {
        if (model == null) {
            model = new DocumentMdlModel();
        }
        model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
        model.setName(tfName.getValue());
        model.setType(cbType.getValue().getName());
        model.setLanguage(cbLanguage.getValue());
        model.setVersion(tfVersion.getValue());
        if (null != fileName) {
            model.setFilename(fileName);
        }

        if (null != tempFileName) {
            model.setTempFilename(tempFileName);
        }

        if (null != subDelegationFileName) {
            model.setSubDelFilename(subDelegationFileName);
        }

        if (isEdit == false) {
            clientDocumentMdlService.insert(model, new AsyncCallback<DocumentMdlModel>() {

                @Override
                public void onSuccess(DocumentMdlModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);

                    ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
                    subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);

                    contentEvent.setEvent(subEvent);
                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    String details = caught.getMessage();
                    if (caught instanceof DocumentMdlException) {
                        details = ExceptionMessageHandler.getErrorMessage(((DocumentMdlException) caught).getCode());
                    }
                    Info.display(messages.commonerror(), details);
                }
            });
        } else {
            clientDocumentMdlService.update(model, new AsyncCallbackWithErrorResolution<DocumentMdlModel>() {

                @Override
                public void onSuccess(DocumentMdlModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);

                    ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
                    subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);

                    contentEvent.setEvent(subEvent);
                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    String details = caught.getMessage();
                    if (caught instanceof DocumentMdlException) {
                        details = ExceptionMessageHandler.getErrorMessage(((DocumentMdlException) caught).getCode());
                    }
                    Info.display(messages.commonerror(), details);
                }
            });
        }
    }
}
