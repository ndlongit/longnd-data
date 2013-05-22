package com.structis.vip.client.fieldset;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.CollaborateurTypeModel;
import com.structis.vip.shared.model.DelegationModel;

public class DelegantFieldSet extends DynamicFieldSet {

    private LabelField lblDelegantNom;

    private LabelField lblDelegantPreNom;

    public LabelField getLblDelegantNom() {
        return this.lblDelegantNom;
    }

    public void setLblDelegantNom(LabelField lblDelegantNom) {
        this.lblDelegantNom = lblDelegantNom;
    }

    public LabelField getLblDelegantPreNom() {
        return this.lblDelegantPreNom;
    }

    public void setLblDelegantPreNom(LabelField lblDelegantPreNom) {
        this.lblDelegantPreNom = lblDelegantPreNom;
    }

    private TextField<String> lblDelegantTitre;
    private LabelField lblDelegantQualite;
    private LabelField lblDelegantStatut;

    // fields for type other DP/DE
    private LabelField lblDelegantDelegationDate;
    private LabelField lblDelegantManagerMandataireNom;
    private LabelField lblDelegantManagerMandatairePreNom;
    private LabelField lblDelegantManagerMandataireQualite;

    // fields for type Mandataire social
    private LabelField lblDelegantMandataireDateConseil;
    private LabelField lblDelegantMandataireStatutConseil;
    private LabelField lblDelegantMandataireDateEffet;

    public DelegantFieldSet() {
        this(null);
    }

    public DelegantFieldSet(String groupName) {
        super(null, messages.delegantheading());

        // init delegant first name
        this.lblDelegantPreNom = this.addLabelField("lblDelegantPreNom");
        this.lblDelegantNom = this.addLabelField("lblDelegantNom");
        this.lblDelegantTitre = this.addTextField("lblDelegantTitre");

        // init delegant first name
        this.lblDelegantStatut = this.addLabelField("lblDelegantStatut");
        this.lblDelegantQualite = this.addLabelField("lblDelegantQualite");

        // 21 Feb 13
        this.lblDelegantMandataireDateConseil = this.addLabelField("lblDelegantMandataireDateConseil");
        this.lblDelegantMandataireStatutConseil = this.addLabelField("lblDelegantMandataireStatutConseil");
        this.lblDelegantMandataireDateEffet = this.addLabelField("lblDelegantMandataireDateEffet");
        this.lblDelegantDelegationDate = this.addLabelField("lblDelegantDelegationDate");
        this.lblDelegantManagerMandataireNom = this.addLabelField("lblDelegantManagerMandataireNom");
        this.lblDelegantManagerMandatairePreNom = this.addLabelField("lblDelegantManagerMandatairePreNom");
        this.lblDelegantManagerMandataireQualite = this.addLabelField("lblDelegantManagerMandataireQualite");
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);
        GWT.log(this.getClass().getName() + ":onRender");
    }

    public LabelField getLblDelegantManagerMandataireQualite() {
        return this.lblDelegantManagerMandataireQualite;
    }

    public void setLblDelegantManagerMandataireQualite(LabelField lblDelegantManagerMandataireQualite) {
        this.lblDelegantManagerMandataireQualite = lblDelegantManagerMandataireQualite;
    }

    protected void setGroupName(String groupName) {
        Component[] allFields = { this.lblDelegantPreNom, this.lblDelegantNom, this.lblDelegantTitre, this.lblDelegantStatut,
                this.lblDelegantQualite, this.lblDelegantMandataireDateConseil, this.lblDelegantMandataireStatutConseil,
                this.lblDelegantMandataireDateEffet, this.lblDelegantDelegationDate, this.lblDelegantManagerMandataireNom,
                this.lblDelegantManagerMandatairePreNom, this.lblDelegantManagerMandataireQualite };
        for (Component field : allFields) {
            field.setData(PROP_GROUP_NAME, groupName);
        }
    }

    private TextField<String> addTextField(String id) {
        return super.addTextField(id, null);
    }

    private LabelField addLabelField(String id) {
        return super.addLabelField(id, null);
    }

    /**
     * apply data to field set
     */
    public void applyInformation(DelegationModel model, CollaborateurModel collaborateurModel) {
        if (model == null || collaborateurModel == null) {
            return;
        }

        if (model.getEntite() != null && CommonUtils.belongsBYEFEGroup(model.getEntite().getEntId())) {
            int groupId;
            String colGroup = collaborateurModel.getType() != null ? collaborateurModel.getType().getGroup().getName() : null;
            if (CollaborateurTypeModel.belongsMandataireSocial(colGroup)) {
                this.setGroupName("Délégant type PDG/DG/DGD (Mandataire Social)");
                this.lblDelegantMandataireDateConseil.setValue(collaborateurModel.getDateConseil() != null ? DateTimeFormat.getFormat(
                        ConstantClient.DATE_FORMAT).format(collaborateurModel.getDateConseil()) : null);
                this.lblDelegantMandataireDateEffet.setValue(collaborateurModel.getDateEffet() != null ? DateTimeFormat.getFormat(
                        ConstantClient.DATE_FORMAT).format(collaborateurModel.getDateEffet()) : null);
                this.lblDelegantMandataireStatutConseil.setValue(collaborateurModel.getStatutConseil());
                groupId = 1;
            } else { // group of type DE/DP ...
                this.setGroupName("Délégant type DGA/DP/DR/DE (Manager à Large Périmètre)");
                CollaborateurModel mandataire = collaborateurModel.getDelegant();
                // title and qualite are the same
                if (mandataire != null) {
                    this.lblDelegantDelegationDate.setValue(collaborateurModel.getDateDelegation() != null ? DateTimeFormat.getFormat(
                            ConstantClient.DATE_FORMAT).format(collaborateurModel.getDateDelegation()) : null);
                    this.lblDelegantManagerMandataireNom.setValue(mandataire.getLastname());
                    this.lblDelegantManagerMandatairePreNom.setValue(mandataire.getFirstname());
                } else {
                    this.lblDelegantDelegationDate.setValue(null);
                    this.lblDelegantManagerMandataireNom.setValue(null);
                    this.lblDelegantManagerMandatairePreNom.setValue(null);
                }
                this.lblDelegantManagerMandataireQualite.setValue(collaborateurModel.getQualiteColDelegant());
                groupId = 2;
            }
            this.setFieldsVisible(groupId);

            this.lblDelegantPreNom.setText(collaborateurModel.getFirstname());
            this.lblDelegantNom.setText(collaborateurModel.getLastname());
            this.lblDelegantStatut.setValue(collaborateurModel.getNiveauHierarchique());
            this.lblDelegantQualite.setText(collaborateurModel.getQualiteDelegant());
        } else { // etde
            this.setGroupName(null);
            // title and qualite are the same
            if ((model.getDelegant() != null) && (collaborateurModel != null)
                    && (model.getDelegant().getId().intValue() == collaborateurModel.getId().intValue())) {
                if (model.getId() != null) {
                    this.lblDelegantTitre.setValue(model.getDelegantTitle());
                    this.lblDelegantQualite.setValue(model.getDelegantQualite());
                    this.lblDelegantPreNom.setText(model.getDelegantFirstname());
                    this.lblDelegantNom.setText(model.getDelegantLastname());
                    this.lblDelegantStatut.setValue(model.getDelegantNiveauHierarchique());
                } else {
                    this.lblDelegantPreNom.setText(collaborateurModel.getFirstname());
                    this.lblDelegantNom.setText(collaborateurModel.getLastname());
                    this.lblDelegantTitre.setValue(collaborateurModel.getTitle());
                    this.lblDelegantQualite.setValue(collaborateurModel.getQualiteDelegant());
                    this.lblDelegantStatut.setValue(collaborateurModel.getNiveauHierarchique());
                }
            } else if (collaborateurModel != null) {
                this.lblDelegantPreNom.setText(collaborateurModel.getFirstname());
                this.lblDelegantNom.setText(collaborateurModel.getLastname());
                this.lblDelegantTitre.setValue(collaborateurModel.getTitle());
                this.lblDelegantQualite.setValue(collaborateurModel.getQualiteDelegant());
                this.lblDelegantStatut.setValue(collaborateurModel.getNiveauHierarchique());
            } else {
                this.lblDelegantTitre.setValue(null);
                this.lblDelegantQualite.setValue(null);
                this.lblDelegantPreNom.setText(null);
                this.lblDelegantNom.setText(null);
                this.lblDelegantStatut.setValue(null);
            }
        }

        this.lblDelegantPreNom.setText(collaborateurModel.getFirstname());
        this.lblDelegantNom.setText(collaborateurModel.getLastname());
    }

    private void setFieldsVisible(int group) {
        this.lblDelegantDelegationDate.setVisible(false);
        this.lblDelegantManagerMandataireNom.setVisible(false);
        this.lblDelegantManagerMandatairePreNom.setVisible(false);
        this.lblDelegantManagerMandataireQualite.setVisible(false);
        this.lblDelegantMandataireDateConseil.setVisible(false);
        this.lblDelegantMandataireStatutConseil.setVisible(false);
        this.lblDelegantMandataireDateEffet.setVisible(false);
        this.lblDelegantQualite.setVisible(false);

        // common
        this.lblDelegantPreNom.setVisible("1".equals(this.lblDelegantPreNom.getData("visible")) ? true : false);
        this.lblDelegantNom.setVisible("1".equals(this.lblDelegantNom.getData("visible")));

        switch (group) {
        case 1:
            this.lblDelegantQualite.setVisible("1".equals(this.lblDelegantQualite.getData("visible")));
            this.lblDelegantMandataireDateConseil.setVisible("1".equals(this.lblDelegantMandataireDateConseil.getData("visible")));
            this.lblDelegantMandataireDateEffet.setVisible("1".equals(this.lblDelegantMandataireDateEffet.getData("visible")));
            this.lblDelegantMandataireStatutConseil.setVisible("1".equals(this.lblDelegantMandataireStatutConseil.getData("visible")));
            break;
        case 2:
            this.lblDelegantQualite.setVisible("1".equals(this.lblDelegantQualite.getData("visible")));
            this.lblDelegantDelegationDate.setVisible("1".equals(this.lblDelegantDelegationDate.getData("visible")));
            this.lblDelegantManagerMandataireNom.setVisible("1".equals(this.lblDelegantManagerMandataireNom.getData("visible")));
            this.lblDelegantManagerMandatairePreNom.setVisible("1".equals(this.lblDelegantManagerMandatairePreNom.getData("visible")));
            this.lblDelegantManagerMandataireQualite.setVisible("1".equals(this.lblDelegantManagerMandataireQualite.getData("visible")));
            break;
        // case 3:
        // lblDelegantGerantPreNom.setVisible("1".equals(lblDelegantGerantPreNom.getData("visible")) ? true : false);
        // lblDelegantGerantNom.setVisible("1".equals(lblDelegantGerantNom.getData("visible")) ? true : false);
        // lblDelegantGerantQualite.setVisible("1".equals(lblDelegantGerantQualite.getData("visible")) ? true : false);
        // break;
        // case 4:
        // lblDelegantDirecteurGeneralPreNom.setVisible("1".equals(lblDelegantDirecteurGeneralPreNom.getData("visible")) ? true : false);
        // lblDelegantDirecteurGeneralNom.setVisible("1".equals(lblDelegantDirecteurGeneralNom.getData("visible")) ? true : false);
        // lblDelegantDirecteurGeneralQualite.setVisible("1".equals(lblDelegantDirecteurGeneralQualite.getData("visible")) ? true : false);
        // break;
        default:

        }
    }

    public String getTitleOrQualite() {
        if (this.lblDelegantTitre.isVisible()) {
            return this.lblDelegantTitre.getValue();
        }
        if (this.lblDelegantQualite.isVisible()) {
            return this.lblDelegantQualite.getText();
        }

        return "";
    }

    public TextField<String> getTitre() {
        return this.lblDelegantTitre;
    }

    public LabelField getLblDelegantStatut() {
        return this.lblDelegantStatut;
    }

    public void setLblDelegantStatut(LabelField lblDelegantStatut) {
        this.lblDelegantStatut = lblDelegantStatut;
    }

    public LabelField getLblDelegantQualite() {
        return this.lblDelegantQualite;
    }

    public void setLblDelegantQualite(LabelField lblDelegantQualite) {
        this.lblDelegantQualite = lblDelegantQualite;
    }

    public boolean isDelegantQualiteVisible() {
        return this.lblDelegantQualite.isVisible();
        // || lblDelegantManagerQualite.isVisible()
        // || lblDelegantGerantQualite.isVisible() || lblDelegantDirecteurGeneralQualite.isVisible();
    }

    public boolean isTitreVisible() {
        return this.lblDelegantTitre.isVisible();
    }

    public void setShow() {
        // if (lblDelegantNom != null && lblDelegantNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantManagerNom != null && lblDelegantManagerNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantGerantNom != null && lblDelegantGerantNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantDirecteurGeneralNom != null && lblDelegantDirecteurGeneralNom.isVisible()) {
        // this.setVisible(true);
        // }else if (lblDelegantPreNom != null && lblDelegantPreNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantManagerPreNom != null && lblDelegantManagerPreNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantGerantPreNom != null && lblDelegantGerantPreNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantDirecteurGeneralPreNom != null && lblDelegantDirecteurGeneralPreNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantTitre != null && lblDelegantTitre.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantQualite != null && lblDelegantQualite.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantManagerQualite != null && lblDelegantManagerQualite.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantGerantQualite != null && lblDelegantGerantQualite.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantDirecteurGeneralQualite != null && lblDelegantDirecteurGeneralQualite.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantStatut != null && lblDelegantStatut.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantNomme != null && lblDelegantNomme.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantDateCa != null && lblDelegantDateCa.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantDateDecision != null && lblDelegantDateDecision.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantManagerDateSocial != null && lblDelegantManagerDateSocial.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantManagerMandataireNom != null && lblDelegantManagerMandataireNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantManagerMandatairePreNom != null && lblDelegantManagerMandatairePreNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegantManagerMandataireQualite != null && lblDelegantManagerMandataireQualite.isVisible()) {
        // this.setVisible(true);
        // } else {
        // this.setVisible(true);
        // }
        this.setVisible(true);
    }
}
