package com.structis.vip.client.fieldset;

import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.PerimetreModel;

public class ChantierFieldSet extends DynamicFieldSet {
	
	private LabelField lblChantierNom;
	private LabelField lblChantierVille;
	private LabelField lblChantierNumeroProjet;
	private LabelField lblChantierDateTravaux;
	private LabelField lblChantierDatePrevisionnelle;
	private LabelField lblChantierDateDefinitive;

	public ChantierFieldSet(SimpleEventBus bus) {
		super(bus, messages.chantierheading());
		
		lblChantierNom = addLabelField("lblChantierNom");
		lblChantierVille = addLabelField("lblChantierVille");
		lblChantierNumeroProjet = addLabelField("lblChantierNumeroProjet");
		lblChantierDateTravaux = addLabelField("lblChantierDateTravaux");
		lblChantierDatePrevisionnelle = addLabelField("lblChantierDatePrevisionnelle");
		lblChantierDateDefinitive = addLabelField("lblChantierDateDefinitive");
	}
	
	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		GWT.log(this.getClass().getName() + ":onRender");
	}

	private LabelField addLabelField(String id) {
		return super.addLabelField(id, null);
	}

	/**
	 * apply data to field set
	 */
	public void applyInformation(PerimetreModel perimetreModel2) {
//		if(perimetreModel2.getType().getName().equals(ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER)) {
		if (CommonUtils.isChantierType(perimetreModel2.getType().getName())) {
//			lblChantierNom.setText(perimetreModel2.getName());
//			lblChantierVille.setText(perimetreModel2.getCity());
//			//lblChantierNumeroProjet.setText(perimetreModel.getChantierID());
//			lblChantierNumeroProjet.setText(perimetreModel2.getChantierNumeroDeProjet());
//			lblChantierDateTravaux.setText(perimetreModel2.getChantierStartDate() != null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(perimetreModel2.getChantierStartDate()):"");
//			lblChantierDatePrevisionnelle.setText(perimetreModel2.getChantierPlannedEndDate()!= null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(perimetreModel2.getChantierPlannedEndDate()):"");
//			lblChantierDateDefinitive.setText(perimetreModel2.getChantierEndDate() != null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(perimetreModel2.getChantierEndDate()):"");
			
			ClientPerimetreServiceAsync.Util.getInstance().findById( perimetreModel2.getPerId(), new AsyncCallback<PerimetreModel>() {

				@Override
				public void onSuccess(PerimetreModel perimetreModel) {
					if (perimetreModel != null) {						
						lblChantierNom.setText(perimetreModel.getName());
						lblChantierVille.setText(perimetreModel.getCity());
						//lblChantierNumeroProjet.setText(perimetreModel.getChantierID());
						lblChantierNumeroProjet.setText(perimetreModel.getChantierNumeroDeProjet());
						lblChantierDateTravaux.setText(perimetreModel.getChantierStartDate() != null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(perimetreModel.getChantierStartDate()):"");
						lblChantierDatePrevisionnelle.setText(perimetreModel.getChantierPlannedEndDate()!= null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(perimetreModel.getChantierPlannedEndDate()):"");
						lblChantierDateDefinitive.setText(perimetreModel.getChantierEndDate() != null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(perimetreModel.getChantierEndDate()):"");
					}
				}

				@Override
				public void onFailure(Throwable arg0) {
				}
			});
			
		}
	}

	public LabelField getLblChantierNom() {
		return lblChantierNom;
	}

	public void setLblChantierNom(LabelField lblChantierNom) {
		this.lblChantierNom = lblChantierNom;
	}

	public LabelField getLblChantierVille() {
		return lblChantierVille;
	}

	public void setLblChantierVille(LabelField lblChantierVille) {
		this.lblChantierVille = lblChantierVille;
	}

	public LabelField getLblChantierNumeroProjet() {
		return lblChantierNumeroProjet;
	}

	public void setLblChantierNumeroProjet(LabelField lblChantierNumeroProjet) {
		this.lblChantierNumeroProjet = lblChantierNumeroProjet;
	}

	public LabelField getLblChantierDateTravaux() {
		return lblChantierDateTravaux;
	}

	public void setLblChantierDateTravaux(LabelField lblChantierDateTravaux) {
		this.lblChantierDateTravaux = lblChantierDateTravaux;
	}

	public LabelField getLblChantierDatePrevisionnelle() {
		return lblChantierDatePrevisionnelle;
	}

	public void setLblChantierDatePrevisionnelle(
			LabelField lblChantierDatePrevisionnelle) {
		this.lblChantierDatePrevisionnelle = lblChantierDatePrevisionnelle;
	}

	public LabelField getLblChantierDateDefinitive() {
		return lblChantierDateDefinitive;
	}

	public void setLblChantierDateDefinitive(LabelField lblChantierDateDefinitive) {
		this.lblChantierDateDefinitive = lblChantierDateDefinitive;
	}
	
	public void setShow() {
//		if (lblChantierNom != null && lblChantierNom.isVisible()) {
//			this.setVisible(true);
//		} else if (lblChantierVille != null && lblChantierVille.isVisible()) {
//			this.setVisible(true);
//		} else if (lblChantierNumeroProjet != null && lblChantierNumeroProjet.isVisible()) {
//			this.setVisible(true);
//		} else if (lblChantierDateTravaux != null && lblChantierDateTravaux.isVisible()) {
//			this.setVisible(true);
//		} else if (lblChantierDatePrevisionnelle != null && lblChantierDatePrevisionnelle.isVisible()) {
//			this.setVisible(true);
//		} else if (lblChantierDateDefinitive != null && lblChantierDateDefinitive.isVisible()) {
//			this.setVisible(true);
//		} else {
//			this.setVisible(false);
//		}
		this.setVisible(true);
	}
}
