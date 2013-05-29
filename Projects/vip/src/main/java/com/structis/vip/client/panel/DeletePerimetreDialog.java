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
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.shared.SharedConstant;
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
        this.errorType = errorType;
        this.perId = perId;
        this.initUI();
        this.initData();
    }

    public void initData() {
        int numOfGrid = 0;
        if ((this.errorType & SharedConstant.REFER_DELEGATION) > 0) { //
            this.delegationPanel = this.createDelegationPanel();
            this.add(this.delegationPanel);
            numOfGrid++;
            ClientDelegationServiceAsync.Util.getInstance().findByPerimetre(this.perId, new AsyncCallback<List<DelegationModel>>() {

                @Override
                public void onSuccess(List<DelegationModel> arg0) {
                    DeletePerimetreDialog.this.delegations.removeAll();
                    DeletePerimetreDialog.this.delegations.add(arg0);
                }

                @Override
                public void onFailure(Throwable arg0) {
                }
            });
        }
        if ((this.errorType & SharedConstant.REFER_PERIMETRE) > 0) { //
            this.perimetrePanel = this.createPerimetrePanel();
            this.add(this.perimetrePanel);
            numOfGrid++;
            ClientPerimetreServiceAsync.Util.getInstance().findByPerimetreParent(this.perId, new AsyncCallback<List<PerimetreModel>>() {

                @Override
                public void onSuccess(List<PerimetreModel> arg0) {
                    DeletePerimetreDialog.this.perimetres.removeAll();
                    DeletePerimetreDialog.this.perimetres.add(arg0);
                }

                @Override
                public void onFailure(Throwable arg0) {
                }
            });
        }
        if ((this.errorType & SharedConstant.REFER_COLLABORATEUR) > 0) { //
            this.collaborateurPanel = this.createCollaborateurPanel();
            this.add(this.collaborateurPanel);
            numOfGrid++;
            ClientCollaborateurServiceAsync.Util.getInstance().findByPerimetre(this.perId, new AsyncCallback<List<CollaborateurModel>>() {

                @Override
                public void onSuccess(List<CollaborateurModel> arg0) {
                    DeletePerimetreDialog.this.collaborateurs.removeAll();
                    DeletePerimetreDialog.this.collaborateurs.add(arg0);
                }

                @Override
                public void onFailure(Throwable arg0) {
                }
            });
        }
        if ((this.errorType & SharedConstant.REFER_USER) > 0) { //
            this.userPanel = this.createUserPanel();
            this.add(this.userPanel);
            numOfGrid++;
            ClientUserServiceAsync.Util.getInstance().findByPerimetre(this.perId, new AsyncCallback<List<UserModel>>() {

                @Override
                public void onSuccess(List<UserModel> arg0) {
                    DeletePerimetreDialog.this.users.removeAll();
                    DeletePerimetreDialog.this.users.add(arg0);
                }

                @Override
                public void onFailure(Throwable arg0) {
                }
            });
        }
        if ((this.errorType & SharedConstant.REFER_CONTROL) > 0) { //
            this.controlPanel = this.createControlPanel();
            this.add(this.controlPanel);
            numOfGrid++;
            ClientControlServiceAsync.Util.getInstance().findByPerimetre(this.perId, new AsyncCallback<List<ControlModel>>() {

                @Override
                public void onSuccess(List<ControlModel> arg0) {
                    DeletePerimetreDialog.this.controls.removeAll();
                    DeletePerimetreDialog.this.controls.add(arg0);
                }

                @Override
                public void onFailure(Throwable arg0) {
                }
            });
        }
        this.setHeight(this.HEIGHT + numOfGrid * this.GRID_HEIGHT);
    }

    private void initUI() {
        this.setHeading(this.messages.perimetretypechooseheading());
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setResizable(false);
        this.setClosable(false);
        this.setModal(true);
        this.setButtonAlign(HorizontalAlignment.RIGHT);
        this.btnNo = new Button(this.messages.commonDialogNonButton());
        this.btnNo.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DeletePerimetreDialog.this.hide();
            }
        });

        this.addButton(this.btnNo);
        this.btnYes = new Button(this.messages.commonDialogOuiButton());
        this.btnYes.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DeletePerimetreDialog.this.hide();
            }
        });
        this.addButton(this.btnYes);

        this.btnDetail = new Button("Detail...");
        this.btnDetail.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {

            }
        });
        this.addButton(this.btnDetail);

    }

    private LayoutContainer createPerimetrePanel() {
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(this.GRID_HEIGHT);
        // setup grid for document view
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        // Column documents
        ColumnConfig column = new ColumnConfig();
        column.setId(PerimetreModel.PERIMETRE_NAME);
        column.setHeader(this.messages.perimetretypechooseperimetre());
        column.setRowHeader(true);
        column.setSortable(false);
        configs.add(column);

        // setup column model
        ColumnModel cm = new ColumnModel(configs);

        this.perimetreGrid = new Grid<PerimetreModel>(this.perimetres, cm);
        this.perimetreGrid.setStyleAttribute("borderTop", "none");
        this.perimetreGrid.setAutoExpandColumn(PerimetreModel.PERIMETRE_NAME);
        this.perimetreGrid.setBorders(false);
        this.perimetreGrid.setStripeRows(true);
        this.perimetreGrid.setColumnLines(true);
        this.perimetreGrid.setColumnReordering(true);
        main.add(this.perimetreGrid);
        return main;
    }

    private LayoutContainer createControlPanel() {
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(this.GRID_HEIGHT);
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

        this.controlGrid = new Grid<ControlModel>(this.controls, cm);
        this.controlGrid.setStyleAttribute("borderTop", "none");
        this.controlGrid.setAutoExpandColumn(ControlModel.CTL_CODEPROJECT);
        this.controlGrid.setBorders(false);
        this.controlGrid.setStripeRows(true);
        this.controlGrid.setColumnLines(true);
        this.controlGrid.setColumnReordering(true);

        main.add(this.controlGrid);
        return main;
    }

    private LayoutContainer createUserPanel() {
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(this.GRID_HEIGHT);
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

        this.userGrid = new Grid<UserModel>(this.users, cm);
        this.userGrid.setStyleAttribute("borderTop", "none");
        this.userGrid.setAutoExpandColumn(UserModel.USER_NAME);
        this.userGrid.setBorders(false);
        this.userGrid.setStripeRows(true);
        this.userGrid.setColumnLines(true);
        this.userGrid.setColumnReordering(true);

        main.add(this.userGrid);
        return main;
    }

    private LayoutContainer createCollaborateurPanel() {
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(this.GRID_HEIGHT);
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

        this.collaborateurGrid = new Grid<CollaborateurModel>(this.collaborateurs, cm);
        this.collaborateurGrid.setStyleAttribute("borderTop", "none");
        this.collaborateurGrid.setAutoExpandColumn(CollaborateurModel.COLLA_FULL_NAME);
        this.collaborateurGrid.setBorders(false);
        this.collaborateurGrid.setStripeRows(true);
        this.collaborateurGrid.setColumnLines(true);
        this.collaborateurGrid.setColumnReordering(true);

        main.add(this.collaborateurGrid);
        return main;
    }

    private LayoutContainer createDelegationPanel() {
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new FitLayout());
        main.setAutoWidth(true);
        main.setHeight(this.GRID_HEIGHT);
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

        this.delegationGrid = new Grid<DelegationModel>(this.delegations, cm);
        this.delegationGrid.setStyleAttribute("borderTop", "none");
        this.delegationGrid.setAutoExpandColumn("perimeter.name");
        this.delegationGrid.setBorders(false);
        this.delegationGrid.setStripeRows(true);
        this.delegationGrid.setColumnLines(true);
        this.delegationGrid.setColumnReordering(true);

        main.add(this.delegationGrid);
        return main;
    }
}
