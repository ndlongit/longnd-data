package com.structis.fichesst.client.panel;

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
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.structis.fichesst.client.event.ExportAllFicheStRefracEvent;
import com.structis.fichesst.client.event.ExportAllFichesstEvent;
import com.structis.fichesst.client.event.ExportSyntheseEvent;
import com.structis.fichesst.client.event.SyntheseEvent;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class SyntheseButtonsPanel extends AbstractPanel {

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
		buttonPanel1.setHeight(30);
		buttonPanel1.setButtonAlign(HorizontalAlignment.CENTER);
		buttonPanel1.addButton(new Button(messages.printSynthese(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				bus.fireEvent(new ExportSyntheseEvent());
			}
		}));
		buttonPanel1.addButton(new Button(messages.printSubcontractor(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				bus.fireEvent(new ExportAllFichesstEvent());

			}
		}));

		buttonPanel1.addButton(new Button(messages.printRefacturations(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				bus.fireEvent(new ExportAllFicheStRefracEvent());
				// showFicheStReport(bus, listFicheStDto, false, "Impression de plusieurs refacturations");
			}
		}));

		add(buttonPanel1);

		ContentPanel buttonPanel2 = new ContentPanel();
		buttonPanel2.setLayout(new CardLayout());
		buttonPanel2.setHeaderVisible(false);
		buttonPanel2.setBorders(false);
		buttonPanel2.setBodyBorder(false);
		buttonPanel2.setHeight(30);

		buttonPanel2.setButtonAlign(HorizontalAlignment.CENTER);

		if (isAdminOrContributor(role, user)) {
			Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
				@Override
				public void handleEvent(MessageBoxEvent be) {
					Button btt = be.getButtonClicked();
					if (Dialog.OK.equals(btt.getItemId())) {
						// gotoAcceuilEcran();
						Window.Location.reload();
						History.newItem("acceuil");
					}
				}
			};
			buttonPanel2.addButton(createCancelButton(callback));

			buttonPanel2.addButton(new Button(messages.saveChanges(), new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					bus.fireEvent(new SyntheseEvent());
					// gotoAcceuilEcran();
					Window.Location.reload();
					History.newItem("acceuil");
				}
			}));
		} else {
			buttonPanel2.addButton(new Button("Retour", new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					Window.Location.reload();
					History.newItem("acceuil");
				}
			}));
		}

		add(buttonPanel2);
	}

}
