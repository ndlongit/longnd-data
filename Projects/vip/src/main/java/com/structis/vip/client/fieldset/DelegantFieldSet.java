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
		return lblDelegantNom;
	}

	public void setLblDelegantNom(LabelField lblDelegantNom) {
		this.lblDelegantNom = lblDelegantNom;
	}

	public LabelField getLblDelegantPreNom() {
		return lblDelegantPreNom;
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
		lblDelegantPreNom = addLabelField("lblDelegantPreNom");
		lblDelegantNom = addLabelField("lblDelegantNom");
		lblDelegantTitre = addTextField("lblDelegantTitre");

		// init delegant first name
		lblDelegantStatut = addLabelField("lblDelegantStatut");
		lblDelegantQualite = addLabelField("lblDelegantQualite");

		// 21 Feb 13
		lblDelegantMandataireDateConseil = addLabelField("lblDelegantMandataireDateConseil");
		lblDelegantMandataireStatutConseil = addLabelField("lblDelegantMandataireStatutConseil");
		lblDelegantMandataireDateEffet = addLabelField("lblDelegantMandataireDateEffet");
		lblDelegantDelegationDate = addLabelField("lblDelegantDelegationDate");
		lblDelegantManagerMandataireNom = addLabelField("lblDelegantManagerMandataireNom");
		lblDelegantManagerMandatairePreNom = addLabelField("lblDelegantManagerMandatairePreNom");
		lblDelegantManagerMandataireQualite = addLabelField("lblDelegantManagerMandataireQualite");
	}
	
	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		GWT.log(this.getClass().getName() + ":onRender");
	}

	public LabelField getLblDelegantManagerMandataireQualite() {
		return lblDelegantManagerMandataireQualite;
	}

	public void setLblDelegantManagerMandataireQualite(LabelField lblDelegantManagerMandataireQualite) {
		this.lblDelegantManagerMandataireQualite = lblDelegantManagerMandataireQualite;
	}
	
	protected void setGroupName(String groupName) {
		Component[] allFields = { lblDelegantPreNom, lblDelegantNom, lblDelegantTitre, lblDelegantStatut, lblDelegantQualite,
				lblDelegantMandataireDateConseil, lblDelegantMandataireStatutConseil, lblDelegantMandataireDateEffet, lblDelegantDelegationDate,
				lblDelegantManagerMandataireNom, lblDelegantManagerMandatairePreNom, lblDelegantManagerMandataireQualite };
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
		if( model == null || collaborateurModel == null ) {
			return;
		}

		if( model.getEntite() != null && CommonUtils.belongsBYEFEGroup(model.getEntite().getEntId()) ) {
			int groupId;
			String colGroup = collaborateurModel.getType() != null ? collaborateurModel.getType().getGroup().getName() : null;
			if( CollaborateurTypeModel.belongsMandataireSocial(colGroup) ) {
				setGroupName("Délégant type PDG/DG/DGD (Mandataire Social)");
				lblDelegantMandataireDateConseil.setValue(collaborateurModel.getDateConseil() != null ? DateTimeFormat.getFormat(
						ConstantClient.DATE_FORMAT).format(collaborateurModel.getDateConseil()) : null);
				lblDelegantMandataireDateEffet.setValue(collaborateurModel.getDateEffet() != null ? DateTimeFormat.getFormat(
						ConstantClient.DATE_FORMAT).format(collaborateurModel.getDateEffet()) : null);
				lblDelegantMandataireStatutConseil.setValue(collaborateurModel.getStatutConseil());
				groupId = 1;
			}
			else { // group of type DE/DP ...
				setGroupName("Délégant type DGA/DP/DR/DE (Manager à Large Périmètre)");
				CollaborateurModel mandataire = collaborateurModel.getDelegant();
				// title and qualite are the same
				if( mandataire != null ) {
					lblDelegantDelegationDate.setValue(collaborateurModel.getDateDelegation() != null ? DateTimeFormat.getFormat(
							ConstantClient.DATE_FORMAT).format(collaborateurModel.getDateDelegation()) : null);
					lblDelegantManagerMandataireNom.setValue(mandataire.getLastname());
					lblDelegantManagerMandatairePreNom.setValue(mandataire.getFirstname());
				}
				else {
					lblDelegantDelegationDate.setValue(null);
					lblDelegantManagerMandataireNom.setValue(null);
					lblDelegantManagerMandatairePreNom.setValue(null);
				}
				lblDelegantManagerMandataireQualite.setValue(collaborateurModel.getQualiteColDelegant());
				groupId = 2;
			}
			setFieldsVisible(groupId);
			
			lblDelegantPreNom.setText(collaborateurModel.getFirstname());
			lblDelegantNom.setText(collaborateurModel.getLastname());
			lblDelegantStatut.setValue(collaborateurModel.getNiveauHierarchique());
			lblDelegantQualite.setText(collaborateurModel.getQualiteDelegant());
		}
		else { // etde
			setGroupName(null);
				// title and qualite are the same
			if( (model.getDelegant() != null) && (collaborateurModel != null) && (model.getDelegant().getId().intValue() == collaborateurModel.getId().intValue()) ) {
				if( model.getId() != null ) {
					lblDelegantTitre.setValue(model.getDelegantTitle());
					lblDelegantQualite.setValue(model.getDelegantQualite());
					lblDelegantPreNom.setText(model.getDelegantFirstname());
					lblDelegantNom.setText(model.getDelegantLastname());
					lblDelegantStatut.setValue(model.getDelegantNiveauHierarchique());
				}
				else {
					lblDelegantPreNom.setText(collaborateurModel.getFirstname());
					lblDelegantNom.setText(collaborateurModel.getLastname());
					lblDelegantTitre.setValue(collaborateurModel.getTitle());
					lblDelegantQualite.setValue(collaborateurModel.getQualiteDelegant());
					lblDelegantStatut.setValue(collaborateurModel.getNiveauHierarchique());
				}
			}
			else if( collaborateurModel != null ) {
				lblDelegantPreNom.setText(collaborateurModel.getFirstname());
				lblDelegantNom.setText(collaborateurModel.getLastname());
				lblDelegantTitre.setValue(collaborateurModel.getTitle());
				lblDelegantQualite.setValue(collaborateurModel.getQualiteDelegant());
				lblDelegantStatut.setValue(collaborateurModel.getNiveauHierarchique());
			}
			else {
				lblDelegantTitre.setValue(null);
				lblDelegantQualite.setValue(null);
				lblDelegantPreNom.setText(null);
				lblDelegantNom.setText(null);
				lblDelegantStatut.setValue(null);
			}
		}
		
		lblDelegantPreNom.setText(collaborateurModel.getFirstname());
		lblDelegantNom.setText(collaborateurModel.getLastname());
	}

	private void setFieldsVisible(int group) {
		lblDelegantDelegationDate.setVisible(false);
		lblDelegantManagerMandataireNom.setVisible(false);
		lblDelegantManagerMandatairePreNom.setVisible(false);
		lblDelegantManagerMandataireQualite.setVisible(false);
		lblDelegantMandataireDateConseil.setVisible(false);
		lblDelegantMandataireStatutConseil.setVisible(false);
		lblDelegantMandataireDateEffet.setVisible(false);
		lblDelegantQualite.setVisible(false);

		//common
		lblDelegantPreNom.setVisible("1".equals(lblDelegantPreNom.getData("visible")) ? true : false);
		lblDelegantNom.setVisible("1".equals(lblDelegantNom.getData("visible")));

		switch( group ) {
			case 1 :
				lblDelegantQualite.setVisible("1".equals(lblDelegantQualite.getData("visible")));
				lblDelegantMandataireDateConseil.setVisible("1".equals(lblDelegantMandataireDateConseil.getData("visible")));
				lblDelegantMandataireDateEffet.setVisible("1".equals(lblDelegantMandataireDateEffet.getData("visible")));
				lblDelegantMandataireStatutConseil.setVisible("1".equals(lblDelegantMandataireStatutConseil.getData("visible")));
				break;
			case 2 :
				lblDelegantQualite.setVisible("1".equals(lblDelegantQualite.getData("visible")));
				lblDelegantDelegationDate.setVisible("1".equals(lblDelegantDelegationDate.getData("visible")));
				lblDelegantManagerMandataireNom.setVisible("1".equals(lblDelegantManagerMandataireNom.getData("visible")));
				lblDelegantManagerMandatairePreNom.setVisible("1".equals(lblDelegantManagerMandatairePreNom.getData("visible")));
				lblDelegantManagerMandataireQualite.setVisible("1".equals(lblDelegantManagerMandataireQualite.getData("visible")));
				break;
			//		case 3:
			//			lblDelegantGerantPreNom.setVisible("1".equals(lblDelegantGerantPreNom.getData("visible")) ? true : false);
			//			lblDelegantGerantNom.setVisible("1".equals(lblDelegantGerantNom.getData("visible")) ? true : false);
			//			lblDelegantGerantQualite.setVisible("1".equals(lblDelegantGerantQualite.getData("visible")) ? true : false);
			//			break;
			//		case 4:
			//			lblDelegantDirecteurGeneralPreNom.setVisible("1".equals(lblDelegantDirecteurGeneralPreNom.getData("visible")) ? true : false);
			//			lblDelegantDirecteurGeneralNom.setVisible("1".equals(lblDelegantDirecteurGeneralNom.getData("visible")) ? true : false);
			//			lblDelegantDirecteurGeneralQualite.setVisible("1".equals(lblDelegantDirecteurGeneralQualite.getData("visible")) ? true : false);
			//			break;
			default :

		}
	}
	
	public String getTitleOrQualite() {
		if (lblDelegantTitre.isVisible()) {
			return lblDelegantTitre.getValue();			
		} 
		if (lblDelegantQualite.isVisible()) {
			return lblDelegantQualite.getText();
		} 

		return "";
	}

	public TextField<String> getTitre() {
		return this.lblDelegantTitre;
	}

	public LabelField getLblDelegantStatut() {
		return lblDelegantStatut;
	}
	
	public void setLblDelegantStatut(LabelField lblDelegantStatut) {
		this.lblDelegantStatut = lblDelegantStatut;
	}
	
	public LabelField getLblDelegantQualite() {
		return lblDelegantQualite;
	}

	public void setLblDelegantQualite(LabelField lblDelegantQualite) {
		this.lblDelegantQualite = lblDelegantQualite;
	}

	public boolean isDelegantQualiteVisible() {
		return lblDelegantQualite.isVisible();
//		 || lblDelegantManagerQualite.isVisible() 
//		|| lblDelegantGerantQualite.isVisible() || lblDelegantDirecteurGeneralQualite.isVisible();		
	}

	public boolean isTitreVisible() { 
		return lblDelegantTitre.isVisible();
	}
	
	public void setShow() {		
//		if (lblDelegantNom != null && lblDelegantNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantManagerNom != null && lblDelegantManagerNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantGerantNom != null && lblDelegantGerantNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantDirecteurGeneralNom != null && lblDelegantDirecteurGeneralNom.isVisible()) {
//			this.setVisible(true);
//		}else if (lblDelegantPreNom != null && lblDelegantPreNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantManagerPreNom != null && lblDelegantManagerPreNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantGerantPreNom != null && lblDelegantGerantPreNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantDirecteurGeneralPreNom != null && lblDelegantDirecteurGeneralPreNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantTitre != null && lblDelegantTitre.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantQualite != null && lblDelegantQualite.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantManagerQualite != null && lblDelegantManagerQualite.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantGerantQualite != null && lblDelegantGerantQualite.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantDirecteurGeneralQualite != null && lblDelegantDirecteurGeneralQualite.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantStatut != null && lblDelegantStatut.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantNomme != null && lblDelegantNomme.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantDateCa != null && lblDelegantDateCa.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantDateDecision != null && lblDelegantDateDecision.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantManagerDateSocial != null && lblDelegantManagerDateSocial.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantManagerMandataireNom != null && lblDelegantManagerMandataireNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantManagerMandatairePreNom != null && lblDelegantManagerMandatairePreNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblDelegantManagerMandataireQualite != null && lblDelegantManagerMandataireQualite.isVisible()) {
//			this.setVisible(true);
//		} else {
//			this.setVisible(true);
//		}
		this.setVisible(true);
	}
}