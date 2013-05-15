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
	private Button valideButton = new Button(messages.commonDialogOuiButton());
	private Button annuleButton = new Button(messages.commonDialogNonButton());

    public DialogValider() {
		// ############### Pr�parer le DIALOG BOX ################
		final VerticalPanel outerPanelDialog = new VerticalPanel();
		outerPanelDialog.add(new HTML(messages.commonDialogHeader()));
		outerPanelDialog.setSpacing(3);
		
		panelDialog.setSpacing(3);
		outerPanelDialog.add(panelDialog);
		outerPanelDialog.add(new HTML(messages.commonDialogFooter()));
		add(outerPanelDialog);
		setSize(300, 200);
		setButtonAlign(HorizontalAlignment.CENTER);
		setButtons("");
		
		addButton(valideButton);
		addButton(annuleButton);
		
		// Comportement par d�faut
		valideButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				hide();
			}
		});
		
		annuleButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				hide();
			}
		});
		
    }
    
    /**
     * Supprimer les messages de desactivations
     */
    public void clearDesactiveModel(){
    	panelDialog.clear();
    }
    
    /**
     * 
     * @return true s'il y a une desactivation
     */
    public boolean isDesactiveModelClear(){
    	return (panelDialog.getWidgetCount() == 0);
    }
    
    /**
     * Ajoute message desactivation
     * @param libelle d'une donn�e desactiv�e
     */
    public void addDesactive(String libelle) {
    	panelDialog.add(new HTML("- " + libelle));
    }
    
    /**
     * @param listener validation
     */
    public void addValiderListener(SelectionListener<ButtonEvent> listener) {
    	valideButton.addSelectionListener(listener);
    }
    
    /**
     * 
     * @param listener annulation
     */
    public void addAnnulerListener(SelectionListener<ButtonEvent> listener) {
    	annuleButton.addSelectionListener(listener);
    }
}
