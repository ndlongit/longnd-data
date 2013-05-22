package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.fieldset.ChantierFieldSet;
import com.structis.vip.client.fieldset.DelegantFieldSet;
import com.structis.vip.client.fieldset.DelegataireFieldSet;
import com.structis.vip.client.fieldset.DynamicFieldSet;
import com.structis.vip.client.fieldset.SocieteFieldSet;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationServiceAsync;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DomDelModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.FieldRuleModel;

public abstract class CommonDelegationPanel extends FormPanel {

    protected final static int WIDTH = 700;
    protected final static int HEIGHT = -1;

    protected final Messages messages = GWT.create(Messages.class);
    protected final FormData formData = new FormData("95%");
    protected SimpleEventBus bus;

    protected DelegantFieldSet delegantFieldSet;
    protected DelegataireFieldSet delegataireFieldSet;
    protected SocieteFieldSet societeFieldSet;
    protected ChantierFieldSet chantierFieldSet;
    protected DelegationModel delegationModel;
    protected List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    protected ClientDocumentMdlServiceAsync clientDocumentMdlService = ClientDocumentMdlServiceAsync.Util.getInstance();
    protected EntiteModel entiteModel = new EntiteModel();
    protected ClientDelegationServiceAsync clientDelegationService = ClientDelegationServiceAsync.Util.getInstance();

    protected CommonDelegationPanel(SimpleEventBus b) {
        this.bus = b;
        this.setFrame(true);
        this.setCollapsible(false);
        this.setLayout(new FlowLayout());
        this.setScrollMode(Scroll.AUTO);
    }

    /**
     * init field sets
     */
    protected void initFieldSets() {
        this.delegantFieldSet = new DelegantFieldSet();
        this.addFieldSet(this.delegantFieldSet);

        this.delegataireFieldSet = new DelegataireFieldSet(this.bus);
        this.addFieldSet(this.delegataireFieldSet);

        this.societeFieldSet = new SocieteFieldSet(this.bus);
        this.addFieldSet(this.societeFieldSet);

        this.chantierFieldSet = new ChantierFieldSet(this.bus);
        this.addFieldSet(this.chantierFieldSet);
    }

    private void addFieldSet(DynamicFieldSet fieldSet) {
        if (fieldSet != null) {
            fieldSet.collapse();
            this.add(fieldSet, this.formData);
        }
    }

    protected static void resetFieldSets(DynamicFieldSet... fieldSets) {
        for (DynamicFieldSet fieldSet : fieldSets) {
            if (fieldSet != null) {
                fieldSet.setVisible(false);
                fieldSet.invisibleAllFields();
            }
        }
    }

    protected void processFieldRules(final DelegationModel delegationModel, List<FieldRuleModel> fieldRules) {
        CollaborateurModel collaborateurModel = delegationModel.getDelegant();

        this.delegantFieldSet.applyInformation(delegationModel, collaborateurModel);

        for (Field<?> field : this.getFields()) {
            Object groupName = field.getData(DynamicFieldSet.PROP_GROUP_NAME);
            for (FieldRuleModel fieldRuleModel : fieldRules) {
                if (field.getId() == null) {
                    continue;
                }

                // Because field IDs in different groups can be duplicated, so if a group name is specified, compare by both ID and group name
                if (field.getId().equals(fieldRuleModel.getField().getFormFieldId())) {
                    if (groupName != null && groupName.toString().trim() != "") {
                        String dbGroupName = fieldRuleModel.getField().getGroup();
                        if (groupName.toString().equalsIgnoreCase(dbGroupName)) {
                            this.setProperties(field, fieldRuleModel);
                            break;
                        }
                    } else {

                        // compare by id only
                        this.setProperties(field, fieldRuleModel);
                        break;
                    }
                }
            }
        }
        this.processSpecificFields(delegationModel);
    }

    protected void setProperties(Field<?> field, FieldRuleModel fieldRuleModel) {
        int displayed = fieldRuleModel.getIsDisplayed().intValue();
        field.setVisible(1 == displayed);
        field.setData("visible", "" + displayed);
        field.setFieldLabel(fieldRuleModel.getField().getLabel());

        if (field instanceof TextField<?>) {
            TextField<?> txtField = (TextField<?>) field;
            txtField.setAllowBlank(1 != fieldRuleModel.getIsRequired().intValue());
        }
    }

    protected boolean isCirmad(String selectedItem) {
        return (selectedItem != null && selectedItem.toUpperCase().startsWith("CIRMAD"));
    }

    protected abstract void processSpecificFields(DelegationModel delegationModel);

    protected void createCommonDocUIs() {
        GridCellRenderer<DocumentMdlModel> documentRender = new GridCellRenderer<DocumentMdlModel>() {
    
            @Override
            public Object render(final DocumentMdlModel model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex,
                    int colIndex, ListStore<DocumentMdlModel> store, Grid<DocumentMdlModel> grid) {
                final com.google.gwt.user.client.ui.Label label = new com.google.gwt.user.client.ui.Label();
                if (delegationModel != null && delegationModel.getId() != null) {
                    
                    //Do not display as a Link for BYEFE, but just a Text
                    if(!ConstantClient.ENTITE_BYEFE.equalsIgnoreCase(entiteModel.getName())) {
                        label.setStyleName("x-link-item");
                    }
                }
                int delegationType = delegationModel.getDelegationType().getId();
                String docModelName = model.getName();
                if (delegationType == ConstantClient.DELEGATION_TYPE_IS_TEMPORAIRE) {
                    docModelName += " " + messages.commontemporary();
                }
                label.setText(docModelName);
                label.setTitle(docModelName);
    
                if (delegationModel != null && delegationModel.getId() != null && !ConstantClient.ENTITE_BYEFE.equalsIgnoreCase(entiteModel.getName())) {
                    label.addClickHandler(new ClickHandler() {
    
                        @Override
                        public void onClick(ClickEvent arg0) {
                            String reportUrl = GWT.getHostPageBaseURL() + ".printDocumentServiceServlet";
                            List<NameValuePair> values = new ArrayList<NameValuePair>();
                            values.add(new NameValuePair("domId", model.getId().toString()));
                            if (delegationModel != null && delegationModel.getId() != null) {
                                values.add(new NameValuePair("delId", delegationModel.getId().toString()));
                            }
                            ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
                        }
                    });
                }
    
                return label;
            }
        };
    
        // Column documents
        ColumnConfig name = new ColumnConfig("documentMdl.name", this.messages.delegationformlesdocuments(), 300);
        name.setRowHeader(true);
        name.setRenderer(documentRender);
        name.setSortable(false);
        configs.add(name);
    
        ColumnConfig signed = new ColumnConfig("signed", "Signed", 200);
    
        GridCellRenderer<DocumentMdlModel> signedRender = new GridCellRenderer<DocumentMdlModel>() {
    
            @Override
            public Object render(final DocumentMdlModel model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex,
                    int colIndex, ListStore<DocumentMdlModel> store, Grid<DocumentMdlModel> grid) {
                final com.google.gwt.user.client.ui.Label lbl = new com.google.gwt.user.client.ui.Label();
                lbl.setStyleName("x-link-item");
    
                if (delegationModel != null && delegationModel.getId() != null) {
                    clientDocumentMdlService.getDocumentsByDelegation(delegationModel.getId(),
                            new AsyncCallbackWithErrorResolution<List<DomDelModel>>() {
    
                                @Override
                                public void onSuccess(List<DomDelModel> arg0) {
                                    if (arg0.size() == 0) {
                                        return;
                                    }

                                    for (DomDelModel domDelModel : arg0) {
                                        if (domDelModel.getDocumentMdl().getId() == model.getId()) {
                                            String signedFilename = domDelModel.getSignedFilename();
                                            String link = null;
                                            if (signedFilename == null) {
                                                link = domDelModel.getHemeraLien();
                                            } else {
                                                link = signedFilename;
                                            }
                                            
                                            lbl.setText(link);
                                            lbl.setTitle(link);
                                        }
                                    }
                                }
                            });
    
                    lbl.addClickHandler(new ClickHandler() {
    
                        @Override
                        public void onClick(ClickEvent arg0) {
                            String fileName = lbl.getText();
                            if (fileName != null && fileName.toLowerCase().startsWith("http")) {
                                String options = "menubar=no,location=no,resizable=no,scrollbars=yes,status=no";
                                Window.open(fileName, "signedDocument", options);
                            } else {
                                String reportUrl = GWT.getHostPageBaseURL() + ".printSignedDocumentServiceServlet";
                                List<NameValuePair> values = new ArrayList<NameValuePair>();
                                values.add(new NameValuePair("fileName", fileName));
                                values.add(new NameValuePair("delId", delegationModel.getId().toString()));
                                ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
                            }
                        }
                    });
                }
    
                return lbl;
            }
        };
        signed.setRenderer(signedRender);
    
        configs.add(signed);
    }
}
