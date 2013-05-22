package com.structis.vip.client.fieldset;

import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.service.ClientPayCodeServiceAsync;
import com.structis.vip.client.service.ClientSyncServiceAsync;
import com.structis.vip.shared.model.AddressModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.PayCodeModel;

public class DelegataireFieldSet extends DynamicFieldSet {

    private static final String GROUP_NAME = "Délégataire";

    private LabelField lblDelegataireNom;
    private LabelField lblDelegatairePreNom;
    private LabelField lblDelegataireTitre;
    private LabelField lblDelegataireStatut;
    private LabelField lblDelegataireQualite;
    private LabelField lblDelegataireDateNaissance;
    private LabelField lblDelegataireLieuNaissance;
    private LabelField lblDelegataireNationalite;
    private LabelField lblDelegataireAdresse;
    private LabelField lblDelegataireDateFormation;
    private LabelField lblDelegataireZone;
    private LabelField lblDelegataireOperations;
    private LabelField lblDelegataireIntituleFormation;

    private ClientSyncServiceAsync clientSyncService = ClientSyncServiceAsync.Util.getInstance();
    private ClientPayCodeServiceAsync clientPayCodeService = ClientPayCodeServiceAsync.Util.getInstance();

    public DelegataireFieldSet(SimpleEventBus bus) {
        super(bus, messages.delegataireheading());

        // init delegataire first name
        this.lblDelegatairePreNom = this.addLabelField("lblDelegatairePreNom");

        // init delegataire last name
        this.lblDelegataireNom = this.addLabelField("lblDelegataireNom");
        this.lblDelegataireTitre = this.addLabelField("lblDelegataireTitre");
        this.lblDelegataireStatut = this.addLabelField("lblDelegataireStatut");

        // ini delegataire qualite
        this.lblDelegataireQualite = this.addLabelField("lblDelegataireQualite");
        this.lblDelegataireDateNaissance = this.addLabelField("lblDelegataireDateNaissance");
        this.lblDelegataireLieuNaissance = this.addLabelField("lblDelegataireLieuNaissance");
        this.lblDelegataireNationalite = this.addLabelField("lblDelegataireNationalite");

        // init delegataire address
        this.lblDelegataireAdresse = this.addLabelField("lblDelegataireAdresse");
        this.lblDelegataireDateFormation = this.addLabelField("lblDelegataireDateFormation");
        this.lblDelegataireZone = this.addLabelField("lblDelegataireZone");
        this.lblDelegataireOperations = this.addLabelField("lblDelegataireOperations");
        this.lblDelegataireIntituleFormation = this.addLabelField("lblDelegataireIntituleFormation");
    }

    private LabelField addLabelField(String id) {
        return super.addLabelField(id, GROUP_NAME);
    }

    public void applyInformation(final CollaborateurModel collaborateurModel) {
        this.lblDelegatairePreNom.setText(collaborateurModel.getFirstname());
        this.lblDelegataireNom.setText(collaborateurModel.getLastname());
        this.lblDelegataireQualite.setText(collaborateurModel.getNiveauHierarchique());
        this.lblDelegataireStatut.setText(collaborateurModel.getNiveauHierarchique());
        this.lblDelegataireDateNaissance.setText((collaborateurModel.getDateNaissance() != null) ? DateTimeFormat.getFormat(
                ConstantClient.DATE_FORMAT).format(collaborateurModel.getDateNaissance()) : "");
        this.lblDelegataireNationalite.setText(collaborateurModel.getNationality());
        this.lblDelegataireLieuNaissance.setText(collaborateurModel.getLieuNaissance());
        this.lblDelegataireZone.setText(collaborateurModel.getZone());
        this.lblDelegataireOperations.setText(collaborateurModel.getOperations());

        if (collaborateurModel != null) {
            this.clientSyncService.getAddress(collaborateurModel.getIdBycn(), new AsyncCallback<AddressModel>() {

                @Override
                public void onSuccess(final AddressModel arg0) {
                    if (arg0 != null) {
                        final StringBuffer buffer = new StringBuffer();
                        if (arg0.getNumeroRue() != null) {
                            buffer.append(arg0.getNumeroRue() + "</br>");
                        }
                        if (arg0.getComplementAdresse() != null) {
                            buffer.append(arg0.getComplementAdresse() + "</br>");
                        }
                        if (arg0.getBureauDistributeur() != null) {
                            buffer.append(arg0.getBureauDistributeur() + "</br>");
                        }
                        if (arg0.getPays() != null) {
                            DelegataireFieldSet.this.clientPayCodeService.findByCode(arg0.getPays(), new AsyncCallback<PayCodeModel>() {

                                @Override
                                public void onSuccess(PayCodeModel payCode) {
                                    if (payCode != null) {
                                        buffer.append(payCode.getName());
                                    } else {
                                        buffer.append(arg0.getPays());
                                    }
                                    // tdo
                                    if (buffer.toString().isEmpty()) {
                                        DelegataireFieldSet.this.lblDelegataireAdresse.setText(collaborateurModel.getAddress());
                                    } else {
                                        DelegataireFieldSet.this.lblDelegataireAdresse.setText(buffer.toString());
                                    }
                                }

                                @Override
                                public void onFailure(Throwable arg0) {
                                }
                            });
                        } else {
                            if (buffer.toString().isEmpty()) {
                                DelegataireFieldSet.this.lblDelegataireAdresse.setText(collaborateurModel.getAddress());
                            } else {
                                DelegataireFieldSet.this.lblDelegataireAdresse.setText(buffer.toString());
                            }
                        }
                    } else { // tdo
                        DelegataireFieldSet.this.lblDelegataireAdresse.setText(collaborateurModel.getAddress());
                    }
                }

                @Override
                public void onFailure(Throwable arg0) {
                }
            });
        }
    }

    public LabelField getLblDelegataireNom() {
        return this.lblDelegataireNom;
    }

    public void setLblDelegataireNom(LabelField lblDelegataireNom) {
        this.lblDelegataireNom = lblDelegataireNom;
    }

    public LabelField getLblDelegatairePreNom() {
        return this.lblDelegatairePreNom;
    }

    public void setLblDelegatairePreNom(LabelField lblDelegatairePreNom) {
        this.lblDelegatairePreNom = lblDelegatairePreNom;
    }

    public LabelField getLblDelegataireTitre() {
        return this.lblDelegataireTitre;
    }

    public void setLblDelegataireTitre(LabelField lblDelegataireTitre) {
        this.lblDelegataireTitre = lblDelegataireTitre;
    }

    public LabelField getLblDelegataireStatut() {
        return this.lblDelegataireStatut;
    }

    public void setLblDelegataireStatut(LabelField lblDelegataireStatut) {
        this.lblDelegataireStatut = lblDelegataireStatut;
    }

    public LabelField getLblDelegataireQualite() {
        return this.lblDelegataireQualite;
    }

    public void setLblDelegataireQualite(LabelField lblDelegataireQualite) {
        this.lblDelegataireQualite = lblDelegataireQualite;
    }

    public LabelField getLblDelegataireDateNaissance() {
        return this.lblDelegataireDateNaissance;
    }

    public void setLblDelegataireDateNaissance(LabelField lblDelegataireDateNaissance) {
        this.lblDelegataireDateNaissance = lblDelegataireDateNaissance;
    }

    public LabelField getLblDelegataireLieuNaissance() {
        return this.lblDelegataireLieuNaissance;
    }

    public void setLblDelegataireLieuNaissance(LabelField lblDelegataireLieuNaissance) {
        this.lblDelegataireLieuNaissance = lblDelegataireLieuNaissance;
    }

    public LabelField getLblDelegataireNationalite() {
        return this.lblDelegataireNationalite;
    }

    public void setLblDelegataireNationalite(LabelField lblDelegataireNationalite) {
        this.lblDelegataireNationalite = lblDelegataireNationalite;
    }

    public LabelField getLblDelegataireAdresse() {
        return this.lblDelegataireAdresse;
    }

    public void setLblDelegataireAdresse(LabelField lblDelegataireAdresse) {
        this.lblDelegataireAdresse = lblDelegataireAdresse;
    }

    public LabelField getLblDelegataireDateFormation() {
        return this.lblDelegataireDateFormation;
    }

    public void setLblDelegataireDateFormation(LabelField lblDelegataireDateFormation) {
        this.lblDelegataireDateFormation = lblDelegataireDateFormation;
    }

    public LabelField getLblDelegataireIntituleFormation() {
        return this.lblDelegataireIntituleFormation;
    }

    public void setLblDelegataireIntituleFormation(LabelField lblDelegataireIntituleFormation) {
        this.lblDelegataireIntituleFormation = lblDelegataireIntituleFormation;
    }

    public void setShow() {
        // if (lblDelegataireNom != null && lblDelegataireNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegatairePreNom != null && lblDelegatairePreNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireTitre != null && lblDelegataireTitre.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireStatut != null && lblDelegataireStatut.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireQualite != null && lblDelegataireQualite.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireDateNaissance != null && lblDelegataireDateNaissance.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireLieuNaissance != null && lblDelegataireLieuNaissance.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireNationalite != null && lblDelegataireNationalite.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireAdresse != null && lblDelegataireAdresse.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireDateFormation != null && lblDelegataireDateFormation.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireZone != null && lblDelegataireZone.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireOperations != null && lblDelegataireOperations.isVisible()) {
        // this.setVisible(true);
        // } else if (lblDelegataireIntituleFormation != null && lblDelegataireIntituleFormation.isVisible()) {
        // this.setVisible(true);
        // } else {
        // this.setVisible(false);
        // }
        this.setVisible(true);
    }

}
