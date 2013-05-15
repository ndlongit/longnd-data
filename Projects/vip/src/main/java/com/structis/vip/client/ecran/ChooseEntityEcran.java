package com.structis.vip.client.ecran;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.LoadActionEvent;
import com.structis.vip.client.event.LoadActionHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.service.ClientEntiteServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class ChooseEntityEcran extends AbstractTabEcran implements EcranLoadable {
	private SimpleEventBus bus = new SimpleEventBus();
	private final Messages messages = GWT.create(Messages.class);

	private ComboBox<EntiteModel> cbEntity;
	private LabelField txtEntity;
	private ListStore<EntiteModel> entites = new ListStore<EntiteModel>();

	private ComboBox<PerimetreModel> cbPerimetre;
	private ListStore<PerimetreModel> perimetres = new ListStore<PerimetreModel>();
	private FormPanel frPanel;

	private Button btnValidate;
	private UserModel currentUser;
	private EntiteModel currentEntite;
	boolean isSuperUser = false;

	private ClientEntiteServiceAsync clientEntiteService = ClientEntiteServiceAsync.Util.getInstance();
	private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();
	private ClientUserServiceAsync clientUserService = ClientUserServiceAsync.Util.getInstance();

	public ChooseEntityEcran() {
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		LayoutContainer container = new LayoutContainer();
		container.setLayout(new CenterLayout());

		frPanel = new FormPanel();
		frPanel.setFrame(true);
		frPanel.setWidth(340);
		frPanel.setButtonAlign(HorizontalAlignment.CENTER);
		frPanel.setHeading(messages.chooseentityheader());

		// init commbobox entite
		cbEntity = new ComboBox<EntiteModel>();
		cbEntity.setFieldLabel(messages.delegationentite());
		cbEntity.setLabelSeparator("");
		cbEntity.setStore(entites);
		cbEntity.setDisplayField(EntiteModel.ENTITE_NAME);
		cbEntity.setTriggerAction(TriggerAction.ALL);
		cbEntity.setVisible(false);
		cbEntity.setAllowBlank(false);

		// init text field entite
		txtEntity = new LabelField();
		txtEntity.setFieldLabel(messages.delegationentite());
		txtEntity.setReadOnly(true);
		txtEntity.setVisible(false);

		frPanel.add(cbEntity);
		frPanel.add(txtEntity);

		// init commbobox perimetre
		cbPerimetre = new ComboBox<PerimetreModel>();
		cbPerimetre.setFieldLabel(messages.chooseentityperimetre());
		cbPerimetre.setLabelSeparator("");
		cbPerimetre.setStore(perimetres);
		cbPerimetre.setDisplayField(PerimetreModel.PERIMETRE_NAME);
		cbPerimetre.setTriggerAction(TriggerAction.ALL);
		cbPerimetre.setAllowBlank(false);
		cbPerimetre.setSimpleTemplate("<span title=\"{" + cbPerimetre.getDisplayField() + "}\">{"
				+ cbPerimetre.getDisplayField() + "}</span>");
		cbPerimetre.setValidator(new Validator() {
			@Override
			public String validate(Field<?> field, String value) {
				if (field == cbPerimetre && cbPerimetre.getValue() == null) {
					btnValidate.setEnabled(false);
					return messages.chooseentiteformperimetreerror();
				} else {
					btnValidate.setEnabled(true);
				}
				return null;
			}

		});

		btnValidate = new Button(messages.commonValiderButton());
		btnValidate.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				
				if (frPanel.isValid()) {
						EntiteModel entiteModel = null;
						if (isSuperUser) {
							entiteModel = cbEntity.getValue();
						} else {
							entiteModel = currentEntite;
						}						
						PerimetreModel perimetreModel = cbPerimetre.getValue();
						SessionServiceImpl.getInstance().setEntiteContext(entiteModel);
						SessionServiceImpl.getInstance().setPerimetreContext(perimetreModel);
						SessionServiceImpl.getInstance().getUserContext().setPerimetre(perimetreModel);
						SessionServiceImpl.getInstance().getUserContext().setEntite(entiteModel); //tdo 12 Dec
						
						clientUserService.updateNoRoles(SessionServiceImpl.getInstance().getUserContext(), new AsyncCallback<UserModel>() {
							
							@Override
							public void onSuccess(UserModel result) {
							}
							
							@Override
							public void onFailure(Throwable caught) {
							}
						});
						
						NavigationEvent e = new NavigationEvent(new DelegationListProjectEvent(entiteModel, perimetreModel));
						
						if (SessionServiceImpl.getInstance().getActionContext() != null) {						
							navigation.goToEcran(SessionServiceImpl.getInstance().getActionContext(), e);
						} else {
							navigation.goToEcran(Action.ACTION_DELEGATION, e);
						}					
				} else if (perimetres.getCount() == 0) {
					if (!isSuperUser) {
						EntiteModel entiteModel = null;
						if (isSuperUser) {
							entiteModel = cbEntity.getValue();
						} else {
							entiteModel = currentEntite;
						}
						MessageBox box = new MessageBox();
						box.setButtons(MessageBox.OK);
						box.setIcon(MessageBox.ERROR);
						box.setTitle(messages.commonErreurHeader());					
						box.setMessage(messages.commonnopermissionentite(entiteModel.getName()));
						((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonClosebutton());					
						box.show();
					}
					
				}
			}
		});

		frPanel.add(cbPerimetre);
		frPanel.addButton(btnValidate);

		container.add(frPanel);

		Viewport viewport = new Viewport();
		final BorderLayout layout = new BorderLayout();
		viewport.setLayout(layout);
		viewport.setStyleAttribute("padding", "0px");
		viewport.setBorders(true);
		viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH, 150));
		viewport.setStyleAttribute("background", "white");
		viewport.add(container, new BorderLayoutData(LayoutRegion.CENTER));
		add(viewport);

		bus.addHandler(LoadActionEvent.getType(), new LoadActionHandler() {
			public void onLoadAction(LoadActionEvent event) {
				displayLabelOrCombo();
			}
		});
		
		currentUser = getUserContext();
		if (currentUser != null) {
			// check super user
			isSuperUser = currentUser.isSuperUser();
			bus.fireEvent(new LoadActionEvent(null));
		}
	}

	private void displayLabelOrCombo() {
		if (isSuperUser) {
			frPanel.setHeading(messages.chooseentitysuperheader());
			getStoresForCombos();
			cbEntity.setVisible(true);
			txtEntity.setVisible(false);
			cbEntity.setValidator(new Validator() {
				@Override
				public String validate(Field<?> field, String value) {
					if (field == cbEntity && cbEntity.getValue() == null) {
						btnValidate.setEnabled(false);
						return messages.choosentiteformentiteerror();
					} else {
						btnValidate.setEnabled(true);
					}
					return null;
				}

			});
			cbEntity.addSelectionChangedListener(new SelectionChangedListener<EntiteModel>() {
				@Override
				public void selectionChanged(SelectionChangedEvent<EntiteModel> se) {
					EntiteModel selected = se.getSelectedItem();
					perimetres.removeAll();
					cbPerimetre.clear();
					if (null != selected) {
						refreshDataForPerimetre(selected.getEntId());
					}
				}
			});
		} else {

			//txtEntity.el().firstChild().setStyleName("textLikeLabel");// x-fieldset-noborder");
			txtEntity.setVisible(true);
			cbEntity.setAllowBlank(true);
			cbEntity.setVisible(false);
			getEntiteForUser(currentUser);
		}
		frPanel.repaint();

	}

	private void getEntiteForUser(UserModel currentUser) {
		currentEntite = new EntiteModel();
		clientEntiteService.getEntiteByUser(currentUser, new AsyncCallback<EntiteModel>() {
			@Override
			public void onSuccess(EntiteModel arg0) {
				currentEntite = arg0;
				txtEntity.setValue(currentEntite.getName());
				getStoreForPerimetreCombo(currentEntite.getEntId());
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}

	@Override
	public void onLoadApplication(NavigationEvent event) {
		if (event.getObject() instanceof LoadActionEvent) {
			bus.fireEvent((LoadActionEvent) event.getObject());
		}
	}

	private void getStoresForCombos() {
//		entites = new ListStore<EntiteModel>();
		clientEntiteService.getAllEntites(new AsyncCallback<List<EntiteModel>>() {
			@Override
			public void onSuccess(List<EntiteModel> arg0) {
				entites.removeAll();
				entites.add(arg0);
				cbEntity.setStore(entites);
				// set first entity
				EntiteModel entiteModel = null;
				if (SessionServiceImpl.getInstance().getEntiteContext() != null) {
					for (EntiteModel entiteMdl : entites.getModels()) {
						if (entiteMdl.getEntId().equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
							entiteModel = entiteMdl;
						}
					}
				}
				if (entiteModel == null) {
					if (arg0 != null && arg0.size() > 0) {
						EntiteModel em = arg0.get(0);
						cbEntity.select(0);
						cbEntity.setValue(em);
					}
				} else {
					cbEntity.setValue(entiteModel);
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}

	private void getStoreForPerimetreCombo(String emId) {
		perimetres.removeAll();
		cbPerimetre.clear();
		boolean isAdmin = false;
		if (Action.ACTION_ADMIN.equals(SessionServiceImpl.getInstance().getActionContext())) {
			isAdmin = true;
		}
		clientPerimetreService.findFirstLevelPerimetreByUserRoles(emId, isAdmin, SessionServiceImpl.getInstance().getUserContext().getUserRoles(), new AsyncCallback<List<PerimetreModel>>() {
			@Override
			public void onSuccess(List<PerimetreModel> arg0) {
				perimetres.removeAll();
				perimetres.add(arg0);
				cbPerimetre.setStore(perimetres);
				PerimetreModel perimetreModel = null;
				if ((SessionServiceImpl.getInstance().getUserContext() != null) && (SessionServiceImpl.getInstance().getUserContext().getPerimetre() != null)) {
					for (PerimetreModel perMdl : perimetres.getModels()) {
						if (perMdl.getPerId().equals(SessionServiceImpl.getInstance().getUserContext().getPerimetre().getPerId())) {
							perimetreModel = perMdl;
						}
					}
				}
					
				if (perimetreModel != null) {
					cbPerimetre.setValue(perimetreModel);
				} else { 
					if (arg0 != null && arg0.size() > 0) {
						PerimetreModel pm = arg0.get(0);
						cbPerimetre.select(0);
						cbPerimetre.setValue(pm);
					}
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}

	protected void refreshDataForPerimetre(final String emId) {
		getStoreForPerimetreCombo(emId);
	}
}