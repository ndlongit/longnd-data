// package com.structis.fichesst.client.panel;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import com.extjs.gxt.ui.client.binding.FieldBinding;
// import com.extjs.gxt.ui.client.binding.FormBinding;
// import com.extjs.gxt.ui.client.binding.SimpleComboBoxFieldBinding;
// import com.extjs.gxt.ui.client.store.ListStore;
// import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
// import com.extjs.gxt.ui.client.widget.form.FormPanel;
// import com.extjs.gxt.ui.client.widget.form.NumberField;
// import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
// import com.extjs.gxt.ui.client.widget.form.TextArea;
// import com.extjs.gxt.ui.client.widget.form.TextField;
// import com.extjs.gxt.ui.client.widget.layout.FormData;
// import com.extjs.gxt.ui.client.widget.layout.FormLayout;
// import com.extjs.gxt.ui.client.widget.layout.TableLayout;
// import com.google.gwt.core.client.GWT;
// import com.google.gwt.user.client.rpc.AsyncCallback;
// import com.structis.fichesst.client.message.Messages;
// import com.structis.fichesst.client.service.ClientRefTypeBudjService;
// import com.structis.fichesst.client.service.ClientRefTypeBudjServiceAsync;
// import com.structis.fichesst.shared.dto.LigTransfertppModel;
// import com.structis.fichesst.shared.dto.SimpleDto;
//
// public class AddTransfertPpForm extends AbstractDataForm<LigTransfertppModel> {
// private static final String ANCHOR_SPEC = "90%";
// private static Messages messages = GWT.create(Messages.class);
// private final ClientRefTypeBudjServiceAsync service = GWT
// .create(ClientRefTypeBudjService.class);
// ListStore<LigTransfertppModel> storeLigModel;
// private ListStore<SimpleDto> storeBudj = new ListStore<SimpleDto>();
// private SimpleComboBox<String> devers;
// private SimpleComboBox typeBudj;
// List<String> list = new ArrayList<String>();
// FormBinding formBinding;
//
// public AddTransfertPpForm() {
// TableLayout layout = new TableLayout(1);
// layout.setWidth("100%");
// setLayout(layout);
// setDefaultBackgroundColor(this);
// setMonitorWindowResize(true);
// loadTypeBudj();
// final FormPanel formPanel = new FormPanel();
// formPanel.setBorders(false);
// formPanel.setBodyBorder(false);
// formPanel.setHeaderVisible(false);
// FormLayout fl_formPanel = new FormLayout();
// fl_formPanel.setLabelWidth(120);
// formPanel.setLayout(fl_formPanel);
// typeBudj = new SimpleComboBox();
// typeBudj.setAllowBlank(false);
// typeBudj.setStore(storeBudj);
// typeBudj.setName(LigTransfertppModel.TYPE);
// typeBudj.setFieldLabel(messages.type());
// typeBudj.setValueField(SimpleDto.ID);
// typeBudj.setForceSelection(true);
// typeBudj.setTriggerAction(TriggerAction.ALL);
// typeBudj.setDisplayField(SimpleDto.LABEL);
// // typeBudj.setSimpleValue("Obj");
// formPanel.add(typeBudj, new FormData(ANCHOR_SPEC));
// final TextField<String> lot = new TextField<String>();
// lot.setName(LigTransfertppModel.LOT1);
// lot.setFieldLabel("Lot");
// lot.setAllowBlank(false);
// formPanel.add(lot, new FormData(ANCHOR_SPEC));
// final TextField<String> lot2 = new TextField<String>();
// lot2.setAllowBlank(false);
// lot2.setName(LigTransfertppModel.LOT2);
// lot2.setFieldLabel("Libelle");
// formPanel.add(lot2, new FormData(ANCHOR_SPEC));
// final TextField<String> fieldDevers = new TextField<String>();
// fieldDevers.setAllowBlank(false);
// fieldDevers.setName(LigTransfertppModel.DEVERS);
// fieldDevers.setFieldLabel("Libelle");
//
// formPanel.add(lot2, new FormData(ANCHOR_SPEC));
// devers = new SimpleComboBox<String>();
// devers.setName(LigTransfertppModel.DEVERS);
// devers.setForceSelection(true);
// devers.setTriggerAction(TriggerAction.ALL);
// devers.setAllowBlank(false);
// devers.setEditable(false);
// devers.add("de");
// devers.add("vers");
// devers.setFieldLabel("De/Vers");
//
// formPanel.add(devers, new FormData(ANCHOR_SPEC));
// TextArea comment = new TextArea();
// comment.setName(LigTransfertppModel.COMMENTAIRES);
// formPanel.add(comment, new FormData(ANCHOR_SPEC));
// comment.setFieldLabel(messages.comment());
// add(formPanel);
// NumberField quantity = createIntegerField(null, true);
// // NumberField quantity= new NumberField();
// quantity.setName(LigTransfertppModel.QUANTITY);
// formPanel.add(quantity, new FormData(ANCHOR_SPEC));
// quantity.setFieldLabel("Quantite");
//
// NumberField pu = createNumberField(null);
// pu.setName(LigTransfertppModel.PU);
// formPanel.add(pu, new FormData(ANCHOR_SPEC));
// pu.setFieldLabel("PU");
// LigTransfertppModel dataModel = new LigTransfertppModel(new SimpleDto(
// 1, "Obj"), "de");
// storeLigModel = new ListStore<LigTransfertppModel>();
// storeLigModel.add(dataModel);
// formPanel.add(typeBudj, new FormData(ANCHOR_SPEC));
// formBinding = new FormBinding(formPanel, true);
// formBinding.setStore(storeLigModel);
// formBinding.addFieldBinding(new FieldBinding(fieldDevers,
// LigTransfertppModel.DEVERS));
// formBinding.addFieldBinding(new SimpleComboBoxFieldBinding(devers,
// LigTransfertppModel.DEVERS));
// formBinding.autoBind();
// formBinding.bind(dataModel);
//
// bindModel(formPanel, dataModel);
// }
//
// private void loadTypeBudj() {
// service.findAll(new AsyncCallback<List<SimpleDto>>() {
//
// @Override
// public void onSuccess(List<SimpleDto> arg0) {
// // TODO Auto-generated method stub
// storeBudj.add(arg0);
// }
//
// @Override
// public void onFailure(Throwable arg0) {
// // TODO Auto-generated method stub
//
// }
// });
// }
// }

package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.binding.SimpleComboBoxFieldBinding;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.client.service.ClientRefTypeBudjService;
import com.structis.fichesst.client.service.ClientRefTypeBudjServiceAsync;
import com.structis.fichesst.shared.dto.LigTransfertppModel;
import com.structis.fichesst.shared.dto.SimpleDto;

public class AddTransfertPpForm extends AbstractDataForm<LigTransfertppModel> {
	private static final String				 ANCHOR_SPEC = "90%";
	private static Messages					 messages	= GWT.create(Messages.class);
	private final SimpleComboBox<String>		devers;
	FormBinding								 formBinding;
	List<String>								list		= new ArrayList<String>();
	private final ClientRefTypeBudjServiceAsync service	 = GWT.create(ClientRefTypeBudjService.class);
	private final ListStore<SimpleDto>		  storeBudj   = new ListStore<SimpleDto>();
	ListStore<LigTransfertppModel>			  storeLigModel;
	private final SimpleComboBox				typeBudj;
	
	public AddTransfertPpForm() {
		TableLayout layout = new TableLayout(1);
		layout.setWidth("100%");
		setLayout(layout);
		setDefaultBackgroundColor(this);
		setMonitorWindowResize(true);
		loadTypeBudj();
		final FormPanel formPanel = new FormPanel();
		formPanel.setBorders(false);
		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		FormLayout fl_formPanel = new FormLayout();
		fl_formPanel.setLabelWidth(120);
		formPanel.setLayout(fl_formPanel);
		typeBudj = new SimpleComboBox();
		typeBudj.setAllowBlank(false);
		typeBudj.setStore(storeBudj);
		typeBudj.setName(LigTransfertppModel.TYPE);
		typeBudj.setFieldLabel(messages.type());
		typeBudj.setValueField(SimpleDto.ID);
		typeBudj.setForceSelection(true);
		typeBudj.setTriggerAction(TriggerAction.ALL);
		typeBudj.setDisplayField(SimpleDto.LABEL);
		typeBudj.setSimpleValue("Obj");
		formPanel.add(typeBudj, new FormData(ANCHOR_SPEC));
		final TextField<String> lot = createTextField(MAX_LENGTH_4);
		lot.setName(LigTransfertppModel.LOT1);
		lot.setFieldLabel("Lot1");
		formPanel.add(lot, new FormData(ANCHOR_SPEC));
		final TextField<String> lot2 = createTextField(MAX_LENGTH_4);
		lot2.setName(LigTransfertppModel.LOT2);
		lot2.setFieldLabel("Lot2");
		formPanel.add(lot2, new FormData(ANCHOR_SPEC));
		final TextField<String> fieldDevers = new TextField<String>();
		fieldDevers.setAllowBlank(false);
		fieldDevers.setName(LigTransfertppModel.DEVERS);
		fieldDevers.setFieldLabel("Libelle");
		
		formPanel.add(lot2, new FormData(ANCHOR_SPEC));
		devers = new SimpleComboBox<String>();
		devers.setForceSelection(true);
		devers.setTriggerAction(TriggerAction.ALL);
		devers.setAllowBlank(false);
		devers.setEditable(false);
		devers.add("de");
		devers.add("vers");
		devers.setFieldLabel("De/Vers");
		
		formPanel.add(devers, new FormData(ANCHOR_SPEC));
		TextArea comment = new TextArea();
		comment.setName(LigTransfertppModel.COMMENTAIRES);
		formPanel.add(comment, new FormData(ANCHOR_SPEC));
		comment.setFieldLabel(messages.comment());
		add(formPanel);
		NumberField quantity = createIntegerField(null, true);
		quantity.setName(LigTransfertppModel.QUANTITY);
		formPanel.add(quantity, new FormData(ANCHOR_SPEC));
		quantity.setFieldLabel("Quantite");
		
		NumberField pu = createNumberField(null);
		pu.setName(LigTransfertppModel.PU);
		formPanel.add(pu, new FormData(ANCHOR_SPEC));
		pu.setFieldLabel("PU");
		LigTransfertppModel dataModel = new LigTransfertppModel(new SimpleDto(1, "Obj"), "de");
		storeLigModel = new ListStore<LigTransfertppModel>();
		storeLigModel.add(dataModel);
		formPanel.add(typeBudj, new FormData(ANCHOR_SPEC));
		formBinding = new FormBinding(formPanel);
		
		formBinding.setStore(storeLigModel);
		formBinding.addFieldBinding(new FieldBinding(fieldDevers, LigTransfertppModel.DEVERS));
		formBinding.addFieldBinding(new SimpleComboBoxFieldBinding(devers, LigTransfertppModel.DEVERS));
		formBinding.autoBind();
		formBinding.bind(dataModel);
		
		bindModel(formPanel, dataModel);
	}
	
	private void loadTypeBudj() {
		service.findAll(new AsyncCallback<List<SimpleDto>>() {
			@Override
			public void onFailure(Throwable arg0) {
			}
			
			@Override
			public void onSuccess(List<SimpleDto> arg0) {
				storeBudj.add(arg0);
			}
		});
	}
}
