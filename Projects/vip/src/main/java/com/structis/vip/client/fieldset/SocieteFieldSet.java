package com.structis.vip.client.fieldset;

import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.vip.shared.model.EntiteJuridiqueModel;

public class SocieteFieldSet extends DynamicFieldSet {

    private LabelField lblSocieteNom;
    private LabelField lblSocieteStatusJuridique;
    private LabelField lblSocieteCapital;
    private LabelField lblSocieteAdresse;
    private LabelField lblSocieteSiret;
    private LabelField lblSocieteVille;

    public SocieteFieldSet(SimpleEventBus bus) {
        super(bus, messages.societeheading());

        this.lblSocieteNom = this.addLabelField("lblSocieteNom");
        this.lblSocieteStatusJuridique = this.addLabelField("lblSocieteStatusJuridique");
        this.lblSocieteCapital = this.addLabelField("lblSocieteCapital");
        this.lblSocieteAdresse = this.addLabelField("lblSocieteAdresse");
        this.lblSocieteSiret = this.addLabelField("lblSocieteSiret");
        this.lblSocieteVille = this.addLabelField("lblSocieteVille");
    }

    private LabelField addLabelField(String id) {
        return super.addLabelField(id, null);
    }

    public void applyInformation(EntiteJuridiqueModel entiteJuridiqueModel) {
        this.lblSocieteNom.setText(entiteJuridiqueModel.getName());
        this.lblSocieteStatusJuridique.setText(entiteJuridiqueModel.getStatut());
        this.lblSocieteCapital.setText(entiteJuridiqueModel.getCapital());
        this.lblSocieteAdresse.setText(entiteJuridiqueModel.getAddress());
        this.lblSocieteSiret.setText(entiteJuridiqueModel.getRegistrationId());
        this.lblSocieteVille.setText(entiteJuridiqueModel.getRegistrationAddress());
    }

    public LabelField getLblSocieteNom() {
        return this.lblSocieteNom;
    }

    public void setLblSocieteNom(LabelField lblSocieteNom) {
        this.lblSocieteNom = lblSocieteNom;
    }

    public LabelField getLblSocieteStatusJuridique() {
        return this.lblSocieteStatusJuridique;
    }

    public void setLblSocieteStatusJuridique(LabelField lblSocieteStatusJuridique) {
        this.lblSocieteStatusJuridique = lblSocieteStatusJuridique;
    }

    public LabelField getLblSocieteCapital() {
        return this.lblSocieteCapital;
    }

    public void setLblSocieteCapital(LabelField lblSocieteCapital) {
        this.lblSocieteCapital = lblSocieteCapital;
    }

    public LabelField getLblSocieteAdresse() {
        return this.lblSocieteAdresse;
    }

    public void setLblSocieteAdresse(LabelField lblSocieteAdresse) {
        this.lblSocieteAdresse = lblSocieteAdresse;
    }

    public LabelField getLblSocieteSiret() {
        return this.lblSocieteSiret;
    }

    public void setLblSocieteSiret(LabelField lblSocieteSiret) {
        this.lblSocieteSiret = lblSocieteSiret;
    }

    public LabelField getLblSocieteVille() {
        return this.lblSocieteVille;
    }

    public void setLblSocieteVille(LabelField lblSocieteVille) {
        this.lblSocieteVille = lblSocieteVille;
    }

    public void setShow() {
        // if (lblSocieteNom != null && lblSocieteNom.isVisible()) {
        // this.setVisible(true);
        // } else if (lblSocieteStatusJuridique != null && lblSocieteStatusJuridique.isVisible()) {
        // this.setVisible(true);
        // } else if (lblSocieteCapital != null && lblSocieteCapital.isVisible()) {
        // this.setVisible(true);
        // } else if (lblSocieteAdresse != null && lblSocieteAdresse.isVisible()) {
        // this.setVisible(true);
        // } else if (lblSocieteSiret != null && lblSocieteSiret.isVisible()) {
        // this.setVisible(true);
        // } else if (lblSocieteVille != null && lblSocieteVille.isVisible()) {
        // this.setVisible(true);
        // } else {
        // this.setVisible(false);
        // }
        this.setVisible(true);
    }

}
