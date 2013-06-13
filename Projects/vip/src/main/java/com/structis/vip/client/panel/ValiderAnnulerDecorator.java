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

    public ValiderAnnulerDecorator() {
        this.createButton();
    }

    public Button getValiderButton() {
        return this.validerButton;
    }

    public Button getAnnulerButton() {
        return this.annulerButton;
    }

    public ValiderAnnulerDecorator(ValiderAnnulerPanelIfc cp) {
        this.createButton();
        this.addReferenceContext(cp);
    }

    public void addPanel(ValiderAnnulerPanelIfc cp) {
        this.addReferenceContext(cp);
    }

    private void createButton() {
        this.setFrame(false);
        this.addButton(this.validerButton);
        this.addButton(this.annulerButton);
        this.setButtonAlign(HorizontalAlignment.CENTER);
    }

    private void addReferenceContext(ValiderAnnulerPanelIfc cp) {
        this.contentPanel = cp;
        this.contentPanel.buildPanel();
        this.validerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                contentPanel.onValider();
            }
        });

        this.annulerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                contentPanel.onAnnuler();
            }
        });
        this.add((Component) this.contentPanel);
    }

}
