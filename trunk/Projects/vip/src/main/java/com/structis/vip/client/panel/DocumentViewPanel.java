package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyDocumentEvent;
import com.structis.vip.client.event.ModifyDocumentHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDocTypeServiceAsync;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DocumentTypeModel;
import com.structis.vip.shared.model.LanguageModel;

public class DocumentViewPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ListStore<DocumentTypeModel> documentTypeStore = new ListStore<DocumentTypeModel>();

    private ListStore<LanguageModel> languageStore = new ListStore<LanguageModel>();
    private ClientLanguageServiceAsync clientLanguageService = ClientLanguageServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private LabelField tfName;
    private LabelField tfVersion;
    private LabelField ffFile;
    private LabelField ffTemp;
    private LabelField cbType;
    private LabelField cbLanguage;
    private Button btnAmnuler;
    private Button btnModify;
    private DocumentMdlModel model;

    public DocumentViewPanel(SimpleEventBus bus) {
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
        this.initUI();
        this.initEvent();
    }

    private void addHandler() {
        this.bus.addHandler(ModifyDocumentEvent.getType(), new ModifyDocumentHandler() {

            @Override
            public void onLoadAction(ModifyDocumentEvent event) {
                if (ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_VIEW_DOCUMENT == event.getMode()) {
                    if (event.getModel() != null) {
                        DocumentViewPanel.this.model = event.getModel();
                        DocumentViewPanel.this.tfName.setValue(DocumentViewPanel.this.model.getName());
                        DocumentViewPanel.this.cbLanguage.setValue(DocumentViewPanel.this.model.getLanguage().getName());
                        DocumentViewPanel.this.tfVersion.setValue(DocumentViewPanel.this.model.getVersion());
                        DocumentViewPanel.this.cbType.setValue(DocumentViewPanel.this.getDocType(DocumentViewPanel.this.model.getType()));
                        // if (DOC_TYPE_DP.equals(model.getType().trim())) {
                        // cbType.setValue(documentTypeStore.getAt(0).getName());
                        // } else {
                        // cbType.setValue(documentTypeStore.getAt(1).getName());
                        // }

                        DocumentViewPanel.this.ffFile.setValue(DocumentViewPanel.this.model.getFilename());
                        DocumentViewPanel.this.ffTemp.setValue(DocumentViewPanel.this.model.getTempFilename());
                    }
                }
            }
        });
    }

    private String getDocType(String type) {
        if (type == null) {
            return null;
        } else {
            for (DocumentTypeModel dt : this.documentTypeStore.getModels()) {
                if (dt.getName().trim().equals(type.trim())) {
                    return dt.getName();
                }
            }

            return type;
        }

    }

    private void initData() {
        this.languageStore.removeAll();
        this.clientLanguageService.getLanguages(new AsyncCallback<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                DocumentViewPanel.this.languageStore.add(arg0);
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
        ClientDocTypeServiceAsync.Util.getInstance().getDocTypes(new AsyncCallback<List<DocumentTypeModel>>() {

            @Override
            public void onSuccess(List<DocumentTypeModel> arg0) {
                synchronized (this) {
                    DocumentViewPanel.this.documentTypeStore.removeAll();
                    DocumentViewPanel.this.documentTypeStore.add(arg0);
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
        // documentTypeStore.removeAll();
        // documentTypeStore.add(new DocumentTypeModel(DOC_TYPE_DP));
        // documentTypeStore.add(new DocumentTypeModel(DOC_TYPE_LR));
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

        this.tfName = new LabelField();
        this.tfName.setFieldLabel(this.messages.documentname());
        this.tfName.setName("name");
        this.panel.add(this.tfName, this.formData);

        this.ffFile = new LabelField();
        this.ffFile.addStyleName("x-link-item");
        this.ffFile.setFieldLabel(this.messages.documentfile());
        this.panel.add(this.ffFile, this.formData);

        this.ffTemp = new LabelField();
        this.ffTemp.addStyleName("x-link-item");
        this.ffTemp.setFieldLabel(this.messages.documentfiletemp());
        this.panel.add(this.ffTemp, this.formData);

        this.cbType = new LabelField();
        this.cbType.setFieldLabel(this.messages.documenttype());
        this.panel.add(this.cbType, this.formData);

        this.cbLanguage = new LabelField();
        this.cbLanguage.setFieldLabel(this.messages.documentlanguage());
        this.panel.add(this.cbLanguage, this.formData);

        this.tfVersion = new LabelField();
        this.tfVersion.setFieldLabel(this.messages.documentversion());
        this.panel.add(this.tfVersion, this.formData);

        this.btnAmnuler = new Button(this.messages.commonAnnulerButton());
        this.btnModify = new Button(this.messages.commonModifierButton());

        this.panel.addButton(this.btnAmnuler);
        this.panel.addButton(this.btnModify);

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
                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
                DocumentViewPanel.this.bus.fireEvent(contentEvent);
            }
        });

        this.add(backLink);
    }

    private void initEvent() {
        this.btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
                DocumentViewPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModify.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);

                ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
                subEvent.setModel(DocumentViewPanel.this.model);
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);

                event.setEvent(subEvent);
                DocumentViewPanel.this.bus.fireEvent(event);
            }
        });

        this.ffFile.addListener(Events.OnClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                String reportUrl = GWT.getHostPageBaseURL() + ".printTemplateDocumentServiceServlet";
                List<NameValuePair> values = new ArrayList<NameValuePair>();
                String fileName = DocumentViewPanel.this.ffFile.getText();

                fileName = URL.encode(DocumentViewPanel.this.ffFile.getText());
                values.add(new NameValuePair("fileName", fileName));
                ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
            }
        });

        this.ffTemp.addListener(Events.OnClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                String reportUrl = GWT.getHostPageBaseURL() + ".printTemplateDocumentServiceServlet";
                List<NameValuePair> values = new ArrayList<NameValuePair>();
                values.add(new NameValuePair("fileName", DocumentViewPanel.this.ffTemp.getText()));
                ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
            }
        });
    }
}
