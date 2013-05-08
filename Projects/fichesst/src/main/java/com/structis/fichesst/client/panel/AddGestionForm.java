package com.structis.fichesst.client.panel;

import java.util.Collections;
import java.util.List;

import com.extjs.gxt.ui.client.data.ChangeEvent;
import com.extjs.gxt.ui.client.data.ChangeListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.client.service.ClientRefTypeMchAvServiceAsync;
import com.structis.fichesst.client.service.ClientStatusServiceAsync;
import com.structis.fichesst.client.service.ClientTypeServiceAsync;
import com.structis.fichesst.client.widget.CustomComboBox;
import com.structis.fichesst.client.widget.CustomFormPanel;
import com.structis.fichesst.client.widget.CustomTextField;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.SimpleDto;

public class AddGestionForm extends AbstractDataForm<GestionDto> {

	private static final String ANCHOR_SPEC = "90%";

	private static Messages messages = GWT.create(Messages.class);

	private CustomComboBox<SimpleDto> status = null;

	private CustomComboBox<SimpleDto> marche = null;

	private CustomComboBox<SimpleDto> type = null;

	private FormPanel formPanel;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddGestionForm() {
		super();

		TableLayout layout = new TableLayout(1);
		layout.setWidth("100%");
		setLayout(layout);
		setDefaultBackgroundColor(this);

		formPanel = new CustomFormPanel();
		FormLayout fl_formPanel = new FormLayout();
		fl_formPanel.setLabelWidth(120);
		formPanel.setLayout(fl_formPanel);

		final TextField<String> devis = new CustomTextField<String>();
		devis.setName(GestionDto.DEVIS);
		devis.setFieldLabel(messages.devis());
		devis.setMaxLength(60);
		formPanel.add(devis, new FormData(ANCHOR_SPEC));

		status = new CustomComboBox<SimpleDto>();
		status.setStore(new ListStore());
		status.setName(GestionDto.STATUT);
		status.setFieldLabel(messages.status());
		loadStatusData();
		formPanel.add(status, new FormData(ANCHOR_SPEC));

		TextField<String> label1 = new CustomTextField<String>();
		label1.setMaxLength(MAX_LENGTH_1);
		label1.setName(GestionDto.LABEL);
		formPanel.add(label1, new FormData(ANCHOR_SPEC));
		label1.setFieldLabel(messages.label());

		TextArea comment = new TextArea();
		comment.setName(GestionDto.COMMENT);
		formPanel.add(comment, new FormData(ANCHOR_SPEC));
		comment.setFieldLabel(messages.comment());

		NumberField amount1 = createNumberField(null);
		amount1.setName(GestionDto.AMOUNT);
		formPanel.add(amount1, new FormData(ANCHOR_SPEC));
		amount1.setFieldLabel(messages.amount());
		add(formPanel);

		marche = new CustomComboBox<SimpleDto>();
		marche.setName(GestionDto.MARCHE);
		marche.setStore(new ListStore());
		marche.setFieldLabel(messages.marche());
		loadMarcheData();
		formPanel.add(marche, new FormData(ANCHOR_SPEC));

		NumberField avenants = createNumberField(null);
		avenants.setName(GestionDto.TRAITE);
		formPanel.add(avenants, new FormData(ANCHOR_SPEC));
		avenants.setFieldLabel(messages.avenants());

		NumberField arrete = createNumberField(null);
		arrete.setName(GestionDto.ARRETE);
		formPanel.add(arrete, new FormData(ANCHOR_SPEC));
		arrete.setFieldLabel(messages.arrete());

		NumberField nonArrete = createNumberField(null);
		nonArrete.setName(GestionDto.NON_ARRETE);
		formPanel.add(nonArrete, new FormData(ANCHOR_SPEC));
		nonArrete.setFieldLabel(messages.nonArrete());

		NumberField provision = createNumberField(null);
		provision.setName(GestionDto.PROVISION);
		formPanel.add(provision, new FormData(ANCHOR_SPEC));
		provision.setFieldLabel(messages.provision());

		NumberField devisRefuse = createNumberField(null);
		devisRefuse.setName(GestionDto.DEVIS_REFUSE);
		formPanel.add(devisRefuse, new FormData(ANCHOR_SPEC));
		devisRefuse.setFieldLabel(messages.devisRefuse());

		NumberField reelActivite = createNumberField(null);
		reelActivite.setName(GestionDto.REEL_ACTIVITIVE);
		formPanel.add(reelActivite, new FormData(ANCHOR_SPEC));
		reelActivite.setFieldLabel(messages.reelActivitive());
		reelActivite.setMinValue(0);
		reelActivite.setMaxValue(100);

		type = new CustomComboBox<SimpleDto>();
		type.setStore(new ListStore());
		type.setName(GestionDto.TYPE);
		type.setFieldLabel(messages.type());
		loadTypeData();
		formPanel.add(type, new FormData(ANCHOR_SPEC));

		TextField<String> label2 = new CustomTextField<String>();
		label2.setMaxLength(MAX_LENGTH_1);
		label2.setName(GestionDto.LABEL2);
		formPanel.add(label2, new FormData(ANCHOR_SPEC));
		label2.setFieldLabel(messages.label());

		NumberField amount2 = createNumberField(null);
		amount2.setName(GestionDto.AMOUNT2);
		formPanel.add(amount2, new FormData(ANCHOR_SPEC));
		amount2.setFieldLabel(messages.amount());

		final GestionDto dataModel = new GestionDto();
		dataModel.addChangeListener(new ChangeListener() {
			@Override
			public void modelChanged(ChangeEvent event) {
				SimpleDto m = dataModel.getMarche();
				if( m != null ) {
					dataModel.set(GestionDto.GROUPING, m.getLabel());
				}
			}
		});
		dataModel.initData();
		bindModel(formPanel, dataModel);
	}

	private void loadStatusData() {
		ClientStatusServiceAsync statusService = ClientStatusServiceAsync.Util.getInstance();
		statusService.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
			@Override
			public void onSuccess(final List<SimpleDto> results) {
				Collections.sort(results);
				ListStore<SimpleDto> list = new ListStore<SimpleDto>();
				list.add(results);
				status.setStore(list);
			}
		});
	}

	private void loadMarcheData() {
		ClientRefTypeMchAvServiceAsync service = ClientRefTypeMchAvServiceAsync.Util.getInstance();
		service.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
			@Override
			public void onSuccess(final List<SimpleDto> results) {
				Collections.sort(results);
				ListStore<SimpleDto> list = new ListStore<SimpleDto>();
				list.add(results);
				marche.setStore(list);
			}
		});
	}

	private void loadTypeData() {
		ClientTypeServiceAsync typeService = ClientTypeServiceAsync.Util.getInstance();
		typeService.findAll(new AsyncCallbackWithErrorResolution<List<SimpleDto>>() {
			@Override
			public void onSuccess(final List<SimpleDto> results) {
				Collections.sort(results);
				ListStore<SimpleDto> list = new ListStore<SimpleDto>();
				list.add(results);
				type.setStore(list);
			}
		});
	}
	
	public boolean isValid() {
		return formPanel != null && formPanel.isValid();
	}
}
