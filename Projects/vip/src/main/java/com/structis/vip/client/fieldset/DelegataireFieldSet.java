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
		lblDelegatairePreNom = addLabelField("lblDelegatairePreNom");

		// init delegataire last name
		lblDelegataireNom = addLabelField("lblDelegataireNom");
		lblDelegataireTitre = addLabelField("lblDelegataireTitre");		
		lblDelegataireStatut = addLabelField("lblDelegataireStatut");

		// ini delegataire qualite
		lblDelegataireQualite = addLabelField("lblDelegataireQualite");
		lblDelegataireDateNaissance = addLabelField("lblDelegataireDateNaissance");
		lblDelegataireLieuNaissance = addLabelField("lblDelegataireLieuNaissance");
		lblDelegataireNationalite = addLabelField("lblDelegataireNationalite");
		
		// init delegataire address
		lblDelegataireAdresse = addLabelField("lblDelegataireAdresse");
		lblDelegataireDateFormation = addLabelField("lblDelegataireDateFormation");
		lblDelegataireZone = addLabelField("lblDelegataireZone");
		lblDelegataireOperations = addLabelField("lblDelegataireOperations");
		lblDelegataireIntituleFormation = addLabelField("lblDelegataireIntituleFormation");
	}

	private LabelField addLabelField(String id) {
		return super.addLabelField(id, GROUP_NAME);
	}

	public void applyInformation(final CollaborateurModel collaborateurModel) {
		lblDelegatairePreNom.setText(collaborateurModel.getFirstname());
		lblDelegataireNom.setText(collaborateurModel.getLastname());
		lblDelegataireQualite.setText(collaborateurModel.getNiveauHierarchique());
		lblDelegataireStatut.setText(collaborateurModel.getNiveauHierarchique());
		lblDelegataireDateNaissance.setText((collaborateurModel.getDateNaissance() != null) ? 
				DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(collaborateurModel.getDateNaissance()) :"");
		lblDelegataireNationalite.setText(collaborateurModel.getNationality());
		lblDelegataireLieuNaissance.setText(collaborateurModel.getLieuNaissance());
		lblDelegataireZone.setText(collaborateurModel.getZone());
		lblDelegataireOperations.setText(collaborateurModel.getOperations());
		
		if (collaborateurModel != null) {
			clientSyncService.getAddress(collaborateurModel.getIdBycn(), new AsyncCallback<AddressModel>() {
				
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
							clientPayCodeService.findByCode(arg0.getPays(), new AsyncCallback<PayCodeModel>() {
								@Override
								public void onSuccess(PayCodeModel payCode) {
									if (payCode != null) {
										buffer.append(payCode.getName());										
									} else {
										buffer.append(arg0.getPays());										
									}
									//tdo
									if (buffer.toString().isEmpty()) {
										lblDelegataireAdresse.setText(collaborateurModel.getAddress());
									} else {
										lblDelegataireAdresse.setText(buffer.toString());
									}
								}
								
								@Override
								public void onFailure(Throwable arg0) {
								}
							});
						} else {
							if (buffer.toString().isEmpty()) {
								lblDelegataireAdresse.setText(collaborateurModel.getAddress());
							} else {
								lblDelegataireAdresse.setText(buffer.toString());
							}
						}
					}else { //tdo
						lblDelegataireAdresse.setText(collaborateurModel.getAddress());
					}
				}
				
				@Override
				public void onFailure(Throwable arg0) {
				}
			});
		}
	}

	public LabelField getLblDelegataireNom() {
		return lblDelegataireNom;
	}

	public void setLblDelegataireNom(LabelField lblDelegataireNom) {
		this.lblDelegataireNom = lblDelegataireNom;
	}

	public LabelField getLblDelegatairePreNom() {
		return lblDelegatairePreNom;
	}

	public void setLblDelegatairePreNom(LabelField lblDelegatairePreNom) {
		this.lblDelegatairePreNom = lblDelegatairePreNom;
	}

	public LabelField getLblDelegataireTitre() {
		return lblDelegataireTitre;
	}

	public void setLblDelegataireTitre(LabelField lblDelegataireTitre) {
		this.lblDelegataireTitre = lblDelegataireTitre;
	}

	public LabelField getLblDelegataireStatut() {
		return lblDelegataireStatut;
	}

	public void setLblDelegataireStatut(LabelField lblDelegataireStatut) {
		this.lblDelegataireStatut = lblDelegataireStatut;
	}

	public LabelField getLblDelegataireQualite() {
		return lblDelegataireQualite;
	}

	public void setLblDelegataireQualite(LabelField lblDelegataireQualite) {
		this.lblDelegataireQualite = lblDelegataireQualite;
	}

	public LabelField getLblDelegataireDateNaissance() {
		return lblDelegataireDateNaissance;
	}

	public void setLblDelegataireDateNaissance(
			LabelField lblDelegataireDateNaissance) {
		this.lblDelegataireDateNaissance = lblDelegataireDateNaissance;
	}

	public LabelField getLblDelegataireLieuNaissance() {
		return lblDelegataireLieuNaissance;
	}

	public void setLblDelegataireLieuNaissance(
			LabelField lblDelegataireLieuNaissance) {
		this.lblDelegataireLieuNaissance = lblDelegataireLieuNaissance;
	}

	public LabelField getLblDelegataireNationalite() {
		return lblDelegataireNationalite;
	}

	public void setLblDelegataireNationalite(LabelField lblDelegataireNationalite) {
		this.lblDelegataireNationalite = lblDelegataireNationalite;
	}

	public LabelField getLblDelegataireAdresse() {
		return lblDelegataireAdresse;
	}

	public void setLblDelegataireAdresse(LabelField lblDelegataireAdresse) {
		this.lblDelegataireAdresse = lblDelegataireAdresse;
	}

	public LabelField getLblDelegataireDateFormation() {
		return lblDelegataireDateFormation;
	}

	public void setLblDelegataireDateFormation(
			LabelField lblDelegataireDateFormation) {
		this.lblDelegataireDateFormation = lblDelegataireDateFormation;
	}

	public LabelField getLblDelegataireIntituleFormation() {
		return lblDelegataireIntituleFormation;
	}

	public void setLblDelegataireIntituleFormation(
			LabelField lblDelegataireIntituleFormation) {
		this.lblDelegataireIntituleFormation = lblDelegataireIntituleFormation;
	}
	public void setShow() {		
//		if (lblDelegataireNom != null && lblDelegataireNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegatairePreNom != null && lblDelegatairePreNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireTitre != null && lblDelegataireTitre.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireStatut != null && lblDelegataireStatut.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireQualite != null && lblDelegataireQualite.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireDateNaissance != null && lblDelegataireDateNaissance.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireLieuNaissance != null && lblDelegataireLieuNaissance.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireNationalite != null && lblDelegataireNationalite.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireAdresse != null && lblDelegataireAdresse.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireDateFormation != null && lblDelegataireDateFormation.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireZone != null && lblDelegataireZone.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireOperations != null && lblDelegataireOperations.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegataireIntituleFormation != null && lblDelegataireIntituleFormation.isVisible()) {
//			this.setVisible(true);
//		} else {
//			this.setVisible(false);
//		}
		this.setVisible(true);
	}
	
}