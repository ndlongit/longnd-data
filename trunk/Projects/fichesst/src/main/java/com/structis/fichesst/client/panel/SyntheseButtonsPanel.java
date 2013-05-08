package com.structis.fichesst.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.structis.fichesst.client.ecran.AcceuilEcran;
import com.structis.fichesst.client.event.ExportSyntheseEcranEvent;
import com.structis.fichesst.client.event.SyntheseEvent;
import com.structis.fichesst.client.popup.CreateFicheSTPopUpReportWindow;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class SyntheseButtonsPanel extends AbstractPanel {
	
	private List<FicheStDto> listFicheSt;
	
	public SyntheseButtonsPanel(SimpleEventBus b, RoleModel role, UtilisateurGrpModel user) {
		this.bus = b;
		
		TableLayout layout = new TableLayout(1);
		layout.setWidth("100%");
		setLayout(layout);
		ContentPanel buttonPanel1 = new ContentPanel();
		buttonPanel1.setLayout(new CardLayout());
		buttonPanel1.setHeaderVisible(false);
		buttonPanel1.setBorders(false);
		buttonPanel1.setBodyBorder(false);
		buttonPanel1.setHeight(40);
		buttonPanel1.setButtonAlign(HorizontalAlignment.CENTER);
		buttonPanel1.addButton(new Button(messages.printSynthese(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				// bus.fireEvent(new ExportFicheStEvent());
				bus.fireEvent(new ExportSyntheseEcranEvent());
			}
		}));
		buttonPanel1.addButton(new Button(messages.printSubcontractor(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				// bus.fireEvent(new ExportGestionPanelEvent());
				GWT.log("allModels::::" + listFicheSt.size());
				CreateFicheSTPopUpReportWindow popUpReport = new CreateFicheSTPopUpReportWindow(bus, listFicheSt, true);
				popUpReport.show();
			}
		}));
		
		buttonPanel1.addButton(new Button(messages.printRefacturations(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				// bus.fireEvent(new ExportAvancementPanelEvent());
				CreateFicheSTPopUpReportWindow popUpReport = new CreateFicheSTPopUpReportWindow(bus, listFicheSt, false);
				popUpReport.show();
			}
		}));
		
		add(buttonPanel1);
		
		ContentPanel buttonPanel2 = new ContentPanel();
		buttonPanel2.setLayout(new CardLayout());
		buttonPanel2.setHeaderVisible(false);
		buttonPanel2.setBorders(false);
		buttonPanel2.setBodyBorder(false);
		buttonPanel2.setHeight(40);
		buttonPanel2.setButtonAlign(HorizontalAlignment.CENTER);
		
		if ((user.getBadmin() != null && user.getBadmin()) || (role.getBcontributeur() != null && role.getBcontributeur())) {
			Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
				@Override
				public void handleEvent(MessageBoxEvent be) {
					Button btt = be.getButtonClicked();
					if (Dialog.OK.equals(btt.getItemId())) {
						gotoAcceuilEcran();
					}
				}
			};
			buttonPanel2.addButton(createCancelButton(callback));
			
			buttonPanel2.addButton(new Button(messages.saveChanges(), new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					bus.fireEvent(new SyntheseEvent());
					gotoAcceuilEcran();
				}
			}));
		} else {
			buttonPanel2.addButton(new Button("Retour", new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					gotoAcceuilEcran();
				}
			}));
		}
		
		add(buttonPanel2);
	}
	
	public List<FicheStDto> getListFicheSt() {
		return listFicheSt;
	}
	
	private void gotoAcceuilEcran() {
		GuiUtil.gotoEcran(new AcceuilEcran());
	}
	
	public void setListFicheSt(List<FicheStDto> listFicheSt) {
		this.listFicheSt = listFicheSt;
	}
}
