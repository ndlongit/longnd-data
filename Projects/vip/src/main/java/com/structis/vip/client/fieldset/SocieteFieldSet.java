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

        lblSocieteNom = addLabelField("lblSocieteNom");
        lblSocieteStatusJuridique = addLabelField("lblSocieteStatusJuridique");
        lblSocieteCapital = addLabelField("lblSocieteCapital");
        lblSocieteAdresse = addLabelField("lblSocieteAdresse");
        lblSocieteSiret = addLabelField("lblSocieteSiret");
        lblSocieteVille = addLabelField("lblSocieteVille");
    }

    private LabelField addLabelField(String id) {
        return super.addLabelField(id, null);
    }

    public void applyInformation(EntiteJuridiqueModel entiteJuridiqueModel) {
        lblSocieteNom.setText(entiteJuridiqueModel.getName());
        lblSocieteStatusJuridique.setText(entiteJuridiqueModel.getStatut());
        lblSocieteCapital.setText(entiteJuridiqueModel.getCapital());
        lblSocieteAdresse.setText(entiteJuridiqueModel.getAddress());
        lblSocieteSiret.setText(entiteJuridiqueModel.getRegistrationId());
        lblSocieteVille.setText(entiteJuridiqueModel.getRegistrationAddress());
    }

    public LabelField getLblSocieteNom() {
        return lblSocieteNom;
    }

    public void setLblSocieteNom(LabelField lblSocieteNom) {
        this.lblSocieteNom = lblSocieteNom;
    }

    public LabelField getLblSocieteStatusJuridique() {
        return lblSocieteStatusJuridique;
    }

    public void setLblSocieteStatusJuridique(LabelField lblSocieteStatusJuridique) {
        this.lblSocieteStatusJuridique = lblSocieteStatusJuridique;
    }

    public LabelField getLblSocieteCapital() {
        return lblSocieteCapital;
    }

    public void setLblSocieteCapital(LabelField lblSocieteCapital) {
        this.lblSocieteCapital = lblSocieteCapital;
    }

    public LabelField getLblSocieteAdresse() {
        return lblSocieteAdresse;
    }

    public void setLblSocieteAdresse(LabelField lblSocieteAdresse) {
        this.lblSocieteAdresse = lblSocieteAdresse;
    }

    public LabelField getLblSocieteSiret() {
        return lblSocieteSiret;
    }

    public void setLblSocieteSiret(LabelField lblSocieteSiret) {
        this.lblSocieteSiret = lblSocieteSiret;
    }

    public LabelField getLblSocieteVille() {
        return lblSocieteVille;
    }

    public void setLblSocieteVille(LabelField lblSocieteVille) {
        this.lblSocieteVille = lblSocieteVille;
    }

    public void setShow() {
        // if (lblSocieteNom != null && lblSocieteNom.isVisible()) {
        // setVisible(true);
        // } else if (lblSocieteStatusJuridique != null && lblSocieteStatusJuridique.isVisible()) {
        // setVisible(true);
        // } else if (lblSocieteCapital != null && lblSocieteCapital.isVisible()) {
        // setVisible(true);
        // } else if (lblSocieteAdresse != null && lblSocieteAdresse.isVisible()) {
        // setVisible(true);
        // } else if (lblSocieteSiret != null && lblSocieteSiret.isVisible()) {
        // setVisible(true);
        // } else if (lblSocieteVille != null && lblSocieteVille.isVisible()) {
        // setVisible(true);
        // } else {
        // setVisible(false);
        // }
        setVisible(true);
    }

}
