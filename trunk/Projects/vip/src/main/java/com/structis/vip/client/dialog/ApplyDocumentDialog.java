package com.structis.vip.client.dialog;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.DelegationModelEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.DemDomModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.EntiteModel;

public class ApplyDocumentDialog extends Window {
	private final Messages messages = GWT.create(Messages.class);
	private final int WIDTH = 550;
	private final int HEIGHT = 370;

	private SimpleEventBus bus;
	private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();
	private ClientDocumentMdlServiceAsync clientDocumentMdlServiceAsync = ClientDocumentMdlServiceAsync.Util
			.getInstance();
	private ListStore<DocumentMdlModel> store = new ListStore<DocumentMdlModel>();
	private CheckBoxListView<DocumentMdlModel> view;
	private Integer group;

	private Button btnSave;
	private Button btnCancel;

	public ApplyDocumentDialog(SimpleEventBus bus) {
		this.bus = bus;
		initUI();
	}

	public void initUI() {
		setHeading(messages.delegationdocumentheading());
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setModal(true);
		setButtonAlign(HorizontalAlignment.RIGHT);

		LayoutContainer main = new LayoutContainer();
		main.setLayout(new FitLayout());
		main.setAutoWidth(true);
		main.setHeight(300);

		view = new CheckBoxListView<DocumentMdlModel>();
		view.setStore(store);
		view.setDisplayProperty(DocumentMdlModel.DOM_NAME);
		view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		if (SessionServiceImpl.getInstance().getEntiteContext() != null &&
				ConstantClient.ENTITE_ID_IS_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
			view.addListener(Events.Select, new Listener<ListViewEvent<DocumentMdlModel>>() {
				@Override
				public void handleEvent(ListViewEvent<DocumentMdlModel> le) {
					List<DocumentMdlModel> checkedList = view.getChecked();
					InputElement ie = null;
					NodeList<Element> nodes = el().select(view.getCheckBoxSelector());
					int index = store.indexOf(le.getModel());
					if (index != -1) {
						Element e = nodes.getItem(index);
						if (InputElement.is(e)) {
							ie = InputElement.as(e);
							if (ie.isChecked() == false) {
								 for (int i=0; i<checkedList.size(); i++) {
					    			if (!checkedList.get(i).equals(le.getModel())) {
					    				view.setChecked(checkedList.get(i), false);
					    			}
					    		  }
							}
						}
					}
				}
			});
		}

		btnSave = new Button(messages.commonApplybutton());
		btnCancel = new Button(messages.commonAnnulerButton());

		btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if ((group != null) && (group != 0)) {
					clientDemDomServiceAsync.deleteByGroup(group, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							List<DemDomModel> demDoms = new ArrayList<DemDomModel>();
							for (DocumentMdlModel mdl : view.getChecked()) {
								DemDomModel demDom = new DemDomModel();
								demDom.setGroup(group);
								demDom.setDocumentMdl(mdl);
								demDoms.add(demDom);
							}

							if (demDoms.size() != 0) {
								clientDemDomServiceAsync.insert(demDoms, group, new AsyncCallback<Integer>() {
									@Override
									public void onSuccess(Integer arg0) {
										disableEvents(true);
										try {
											DelegationModelEvent event = new DelegationModelEvent();
											event.setMode(DelegationModelEvent.MODE_IS_UPDATE_DOCUMENT);
											event.setGroup(0);
											bus.fireEvent(event);
										} catch (Exception e){}
										disableEvents(false);
									}

									@Override
									public void onFailure(Throwable arg0) {
									}
								});
							} else {
								DelegationModelEvent event = new DelegationModelEvent();
								event.setMode(DelegationModelEvent.MODE_IS_UPDATE_DOCUMENT);
								event.setGroup(0);
								bus.fireEvent(event);
							}
						}

						@Override
						public void onFailure(Throwable arg0) {
						}
					});
				}
				hide();
			}
		});

		btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				hide();
			}
		});

		addButton(btnSave);
		addButton(btnCancel);

		main.add(view);
		add(main);
	}

	public void setData(EntiteModel entiteModel, final Integer group) {
		this.group = group;

		clientDocumentMdlServiceAsync.getAllDocumentModelsByEntite(entiteModel,
				new AsyncCallback<List<DocumentMdlModel>>() {
					@Override
					public void onSuccess(List<DocumentMdlModel> arg0) {
						store.removeAll();
						store.add(arg0);
						clientDemDomServiceAsync.getAllDemDomsByDemGroup(group, new AsyncCallback<List<DemDomModel>>() {
							@Override
							public void onSuccess(List<DemDomModel> arg0) {
								for (DemDomModel demDom : arg0) {
									for (int i = 0; i < store.getCount(); i++) {
										if (store.getAt(i).getId().equals(demDom.getDocumentMdl().getId())) {
											view.setChecked(store.getAt(i), true);
										}
									}
								}
							}

							@Override
							public void onFailure(Throwable arg0) {
							}
						});
					}

					@Override
					public void onFailure(Throwable arg0) {
					}
				});
	}
}
