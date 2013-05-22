package com.structis.vip.client.widget;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.structis.vip.client.message.Messages;

/**
 * Dialog de validation pour le cas ou il y a des desactivation
 * 
 * @author b.brotosumpeno
 * 
 */
public class DialogValider extends Dialog {

    protected final Messages messages = GWT.create(Messages.class);
    private VerticalPanel panelDialog = new VerticalPanel();
    private Button valideButton = new Button(this.messages.commonDialogOuiButton());
    private Button annuleButton = new Button(this.messages.commonDialogNonButton());

    public DialogValider() {
        // ############### Pr�parer le DIALOG BOX ################
        final VerticalPanel outerPanelDialog = new VerticalPanel();
        outerPanelDialog.add(new HTML(this.messages.commonDialogHeader()));
        outerPanelDialog.setSpacing(3);

        this.panelDialog.setSpacing(3);
        outerPanelDialog.add(this.panelDialog);
        outerPanelDialog.add(new HTML(this.messages.commonDialogFooter()));
        this.add(outerPanelDialog);
        this.setSize(300, 200);
        this.setButtonAlign(HorizontalAlignment.CENTER);
        this.setButtons("");

        this.addButton(this.valideButton);
        this.addButton(this.annuleButton);

        // Comportement par d�faut
        this.valideButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DialogValider.this.hide();
            }
        });

        this.annuleButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DialogValider.this.hide();
            }
        });

    }

    /**
     * Supprimer les messages de desactivations
     */
    public void clearDesactiveModel() {
        this.panelDialog.clear();
    }

    /**
     * 
     * @return true s'il y a une desactivation
     */
    public boolean isDesactiveModelClear() {
        return (this.panelDialog.getWidgetCount() == 0);
    }

    /**
     * Ajoute message desactivation
     * 
     * @param libelle
     *            d'une donn�e desactiv�e
     */
    public void addDesactive(String libelle) {
        this.panelDialog.add(new HTML("- " + libelle));
    }

    /**
     * @param listener
     *            validation
     */
    public void addValiderListener(SelectionListener<ButtonEvent> listener) {
        this.valideButton.addSelectionListener(listener);
    }

    /**
     * 
     * @param listener
     *            annulation
     */
    public void addAnnulerListener(SelectionListener<ButtonEvent> listener) {
        this.annuleButton.addSelectionListener(listener);
    }
}
