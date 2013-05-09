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
import com.structis.fichesst.client.event.ExportAvancementEvent;
import com.structis.fichesst.client.event.ExportFicheStEvent;
import com.structis.fichesst.client.event.ExportGestionEvent;
import com.structis.fichesst.client.event.ExportSuiviDesAccomptesEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class PrintFicheStButtonsPanel extends AbstractPanel {

	private final ContentPanel buttonPanel1;

	private final ContentPanel buttonPanel2;

	public PrintFicheStButtonsPanel(SimpleEventBus b, final ChantierModel chantier, final RoleModel role, final UtilisateurGrpModel user) {
		this.bus = b;

		TableLayout layout = new TableLayout(1);
		layout.setWidth("100%");
		setLayout(layout);

		buttonPanel1 = new ContentPanel();
		buttonPanel1.setLayout(new CardLayout());
		buttonPanel1.setHeaderVisible(false);
		buttonPanel1.setBorders(false);
		buttonPanel1.setBodyBorder(false);
		buttonPanel1.setHeight(40);
		buttonPanel1.setButtonAlign(HorizontalAlignment.CENTER);

		buttonPanel1.addButton(new Button(messages.printAll(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				bus.fireEvent(new ExportFicheStEvent());
			}
		}));

		buttonPanel1.addButton(new Button(messages.print(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				bus.fireEvent(new ExportGestionEvent());
			}
		}));

		buttonPanel1.addButton(new Button(messages.printPromotions(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				bus.fireEvent(new ExportAvancementEvent());
			}
		}));

		buttonPanel1.addButton(new Button(messages.printAcomptes(), new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				bus.fireEvent(new ExportSuiviDesAccomptesEvent());
			}
		}));

		add(buttonPanel1);

		buttonPanel2 = new ContentPanel();
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
						Window.Location.reload();
						History.newItem(chantier.getId().toString());
					}
				}
			};
			buttonPanel2.addButton(createCancelButton(callback));

			buttonPanel2.addButton(new Button(messages.saveChanges(), new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					SaveFicheStEvent event = new SaveFicheStEvent();
					// event.setNextPage(new SyntheseEcran(chantier, role, user));
					bus.fireEvent(event);
					Window.Location.reload();
					History.newItem(chantier.getId().toString());
				}
			}));
		} else {
			buttonPanel2.addButton(new Button("Retour", new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					// gotoSyntheseEcran(chantier, role, user);
					Window.Location.reload();
					History.newItem(chantier.getId().toString());
				}
			}));
		}

		add(buttonPanel2);
	}
}
