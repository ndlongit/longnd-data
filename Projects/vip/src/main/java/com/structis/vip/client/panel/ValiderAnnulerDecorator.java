package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.structis.vip.client.message.Messages;
import com.structis.vip.shared.model.BaseModelDataActivable;

/**
 * Le panel d�corator qui ajoute les boutons annuler et valider
 * 
 * @author b.brotosumpeno
 *
 * @param <M>
 */
public class ValiderAnnulerDecorator<M extends BaseModelDataActivable> extends ContentPanel {
	
	protected final Messages messages = GWT.create(Messages.class);
	private ValiderAnnulerPanelIfc contentPanel;
	private Button validerButton = new Button(messages.commonValiderButton());
	private Button annulerButton = new Button(messages.commonAnnulerButton());
	
	public ValiderAnnulerDecorator(){
		createButton();
	}
	
	public Button getValiderButton() {
		return validerButton;
	}

	public Button getAnnulerButton() {
		return annulerButton;
	}

	public ValiderAnnulerDecorator(ValiderAnnulerPanelIfc cp) {
		createButton();
		addReferenceContext(cp);
	}
	
	public void addPanel(ValiderAnnulerPanelIfc cp) {
		addReferenceContext(cp);
	}
	
	private void createButton(){
		setFrame(false);
		addButton(validerButton);
		addButton(annulerButton);
		setButtonAlign(HorizontalAlignment.CENTER);
	}
	
	private void addReferenceContext(ValiderAnnulerPanelIfc cp) {
		contentPanel = cp;
		contentPanel.buildPanel();
		validerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						contentPanel.onValider();
					}
				});
		
		annulerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				contentPanel.onAnnuler();
			}
		});
		add((Component)contentPanel);
	}

}
