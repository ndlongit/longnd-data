package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientControlServiceAsync;
import com.structis.vip.client.service.ClientDelegationServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.shared.Constants;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class DeletePerimetreDialog extends Window {
	private final Messages messages = GWT.create(Messages.class);
	private final Integer GRID_HEIGHT = 20;
	private final int WIDTH = 550;
	private final int HEIGHT = 320;

	private SimpleEventBus bus;

	private Button btnYes;		
	private Button btnNo;
	private Button btnDetail;
	private LayoutContainer perimetrePanel = new LayoutContainer();
	private ListStore<PerimetreModel> perimetres = new ListStore<PerimetreModel>();
	private Grid<PerimetreModel> perimetreGrid;
	
	private LayoutContainer delegationPanel = new LayoutContainer();
	private ListStore<DelegationModel> delegations = new ListStore<DelegationModel>();
	private Grid<DelegationModel> delegationGrid;
	
	private LayoutContainer userPanel = new LayoutContainer();
	private ListStore<UserModel> users = new ListStore<UserModel>();
	private Grid<UserModel> userGrid;
	
	private LayoutContainer controlPanel = new LayoutContainer();
	private ListStore<ControlModel> controls = new ListStore<ControlModel>();
	private Grid<ControlModel> controlGrid;
	
	private LayoutContainer collaborateurPanel = new LayoutContainer();
	private ListStore<CollaborateurModel> collaborateurs = new ListStore<CollaborateurModel>();
	private Grid<CollaborateurModel> collaborateurGrid;
	private Integer errorType;
	private String perId;	

	public DeletePerimetreDialog(SimpleEventBus bus, String perId, Integer errorType) {
		this.bus = bus;
		this.errorType = errorType;
		this.perId = perId;
		initUI();
		initData();
	}

	public void initData() {
		int numOfGrid = 0;
		if ((errorType & Constants.REFER_DELEGATION) > 0) { //
			delegationPanel = createDelegationPanel();
			add(delegationPanel);
			numOfGrid++;
			ClientDelegationServiceAsync.Util.getInstance().findByPerimetre(perId,
					new AsyncCallback<List<DelegationModel>>() {

				@Override
				public void onSuccess(List<DelegationModel> arg0) {
					delegations.removeAll();
					delegations.add(arg0);
				}

				@Override
				public void onFailure(Throwable arg0) {
				}
			});
		}
		if ((errorType & Constants.REFER_PERIMETRE) > 0) { //
			perimetrePanel = createPerimetrePanel();
			add(perimetrePanel);
			numOfGrid++;
			ClientPerimetreServiceAsync.Util.getInstance().findByPerimetreParent(perId,
					new AsyncCallback<List<PerimetreModel>>() {

				@Override
				public void onSuccess(List<PerimetreModel> arg0) {
					perimetres.removeAll();
					perimetres.add(arg0);
				}

				@Override
				public void onFailure(Throwable arg0) {
				}
			});
		}
		if ((errorType & Constants.REFER_COLLABORATEUR) > 0) { //
			collaborateurPanel = createCollaborateurPanel();
			add(collaborateurPanel);
			numOfGrid++;
			ClientCollaborateurServiceAsync.Util.getInstance().findByPerimetre(perId,
					new AsyncCallback<List<CollaborateurModel>>() {

				@Override
				public void onSuccess(List<CollaborateurModel> arg0) {
					collaborateurs.removeAll();
					collaborateurs.add(arg0);
				}

				@Override
				public void onFailure(Throwable arg0) {
				}
			});
		}
		if ((errorType & Constants.REFER_USER) > 0) { //
			userPanel = createUserPanel();
			add(userPanel);
			numOfGrid++;
			ClientUserServiceAsync.Util.getInstance().findByPerimetre(perId,
					new AsyncCallback<List<UserModel>>() {

				@Override
				public void onSuccess(List<UserModel> arg0) {
					users.removeAll();
					users.add(arg0);
				}

				@Override
				public void onFailure(Throwable arg0) {
				}
			});
		}
		if ((errorType & Constants.REFER_CONTROL) > 0) { //
			controlPanel = createControlPanel();
			add(controlPanel);
			numOfGrid++;
			ClientControlServiceAsync.Util.getInstance().findByPerimetre(perId,
					new AsyncCallback<List<ControlModel>>() {

				@Override
				public void onSuccess(List<ControlModel> arg0) {
					controls.removeAll();
					controls.add(arg0);
				}

				@Override
				public void onFailure(Throwable arg0) {
				}
			});
		}
		this.setHeight(HEIGHT + numOfGrid*GRID_HEIGHT);
	}
	

	private void initUI() {
		setHeading(messages.perimetretypechooseheading());
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setClosable(false);
		setModal(true);
		setButtonAlign(HorizontalAlignment.RIGHT);
		btnNo = new Button(messages.commonDialogNonButton());
		btnNo.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				hide();
			}
		});

		addButton(btnNo);
		btnYes = new Button(messages.commonDialogOuiButton());
		btnYes.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				hide();
			}
		});
		addButton(btnYes);
		
		btnDetail = new Button("Detail...");
		btnDetail.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				
			}
		});
		addButton(btnDetail);
		
	}
	private LayoutContainer createPerimetrePanel() {
		LayoutContainer main = new LayoutContainer();
		main.setLayout(new FitLayout());
		main.setAutoWidth(true);
		main.setHeight(GRID_HEIGHT);
		// setup grid for document view
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Column documents
		ColumnConfig column = new ColumnConfig();
		column.setId(PerimetreModel.PERIMETRE_NAME);
		column.setHeader(messages.perimetretypechooseperimetre());
		column.setRowHeader(true);
		column.setSortable(false);
		configs.add(column);					

		// setup column model
		ColumnModel cm = new ColumnModel(configs);

		perimetreGrid = new Grid<PerimetreModel>(perimetres, cm);
		perimetreGrid.setStyleAttribute("borderTop", "none");
		perimetreGrid.setAutoExpandColumn(PerimetreModel.PERIMETRE_NAME);
		perimetreGrid.setBorders(false);
		perimetreGrid.setStripeRows(true);
		perimetreGrid.setColumnLines(true);
		perimetreGrid.setColumnReordering(true);
		main.add(perimetreGrid);
		return main;
	}
	
	private LayoutContainer createControlPanel() {
		LayoutContainer main = new LayoutContainer();
		main.setLayout(new FitLayout());
		main.setAutoWidth(true);
		main.setHeight(GRID_HEIGHT);
		// setup grid for document view
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Column documents
		ColumnConfig column = new ColumnConfig();
		column.setId(ControlModel.CTL_CODEPROJECT);
		column.setHeader("Code Projet");
		column.setRowHeader(true);
		column.setSortable(false);
		configs.add(column);					

		// setup column model
		ColumnModel cm = new ColumnModel(configs);

		controlGrid = new Grid<ControlModel>(controls, cm);
		controlGrid.setStyleAttribute("borderTop", "none");
		controlGrid.setAutoExpandColumn(ControlModel.CTL_CODEPROJECT);
		controlGrid.setBorders(false);
		controlGrid.setStripeRows(true);
		controlGrid.setColumnLines(true);
		controlGrid.setColumnReordering(true);

		main.add(controlGrid);
		return main;
	}
	
	private LayoutContainer createUserPanel() {
		LayoutContainer main = new LayoutContainer();
		main.setLayout(new FitLayout());
		main.setAutoWidth(true);
		main.setHeight(GRID_HEIGHT);
		// setup grid for document view
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Column documents
		ColumnConfig column = new ColumnConfig();
		column.setId(UserModel.USER_NAME);
		column.setHeader("Nom");
		column.setRowHeader(true);
		column.setSortable(false);
		configs.add(column);					

		// setup column model
		ColumnModel cm = new ColumnModel(configs);

		userGrid = new Grid<UserModel>(users, cm);
		userGrid.setStyleAttribute("borderTop", "none");
		userGrid.setAutoExpandColumn(UserModel.USER_NAME);
		userGrid.setBorders(false);
		userGrid.setStripeRows(true);
		userGrid.setColumnLines(true);
		userGrid.setColumnReordering(true);

		main.add(userGrid);
		return main;
	}
	
	private LayoutContainer createCollaborateurPanel() {
		LayoutContainer main = new LayoutContainer();
		main.setLayout(new FitLayout());
		main.setAutoWidth(true);
		main.setHeight(GRID_HEIGHT);
		// setup grid for document view
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Column documents
		ColumnConfig column = new ColumnConfig();
		column.setId(CollaborateurModel.COLLA_FULL_NAME);
		column.setHeader("Nom");
		column.setRowHeader(true);
		column.setSortable(false);
		configs.add(column);					

		// setup column model
		ColumnModel cm = new ColumnModel(configs);

		collaborateurGrid = new Grid<CollaborateurModel>(collaborateurs, cm);
		collaborateurGrid.setStyleAttribute("borderTop", "none");
		collaborateurGrid.setAutoExpandColumn(CollaborateurModel.COLLA_FULL_NAME);
		collaborateurGrid.setBorders(false);
		collaborateurGrid.setStripeRows(true);
		collaborateurGrid.setColumnLines(true);
		collaborateurGrid.setColumnReordering(true);

		main.add(collaborateurGrid);
		return main;
	}
	
	private LayoutContainer createDelegationPanel() {
		LayoutContainer main = new LayoutContainer();
		main.setLayout(new FitLayout());
		main.setAutoWidth(true);
		main.setHeight(GRID_HEIGHT);
		// setup grid for document view
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Column documents
		ColumnConfig column = new ColumnConfig();
		column.setId("perimeter.name");
		column.setHeader("Perimetre");
		column.setRowHeader(true);
		column.setSortable(false);
		configs.add(column);					

		// setup column model
		ColumnModel cm = new ColumnModel(configs);

		delegationGrid = new Grid<DelegationModel>(delegations, cm);
		delegationGrid.setStyleAttribute("borderTop", "none");
		delegationGrid.setAutoExpandColumn("perimeter.name");
		delegationGrid.setBorders(false);
		delegationGrid.setStripeRows(true);
		delegationGrid.setColumnLines(true);
		delegationGrid.setColumnReordering(true);

		main.add(delegationGrid);
		return main;
	}
}