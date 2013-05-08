package com.structis.fichesst.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.structis.fichesst.client.ecran.ErrorEcran;
import com.structis.fichesst.client.ecran.SyntheseEcran;
import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.client.service.ClientChantierServiceAsync;
import com.structis.fichesst.client.service.ClientRoleService;
import com.structis.fichesst.client.service.ClientRoleServiceAsync;
import com.structis.fichesst.client.service.ClientUtilsateurGrpService;
import com.structis.fichesst.client.service.ClientUtilsateurGrpServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.client.util.NameValuePair;
import com.structis.fichesst.client.util.ReportUtil;
import com.structis.fichesst.client.widget.CustomEditorGrid;
import com.structis.fichesst.client.widget.CustomFieldSet;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class AcceuilPanel extends AbstractPanel {
    Button btnCreer;
    ColumnConfig column;
    private ColumnConfig column_1;
    FieldSet fsNouveauxChantier;
    Grid<ChantierModel> gridChantier;
    Integer idUser;
    Widget img;
    LayoutContainer layoutcell;
    private final ClientChantierServiceAsync service = GWT.create(ClientChantierService.class);
    private final ClientRoleServiceAsync serviceRole = GWT.create(ClientRoleService.class);
    private final ClientUtilsateurGrpServiceAsync serviceUser = GWT.create(ClientUtilsateurGrpService.class);
    ListStore<ChantierModel> storeChantier;
    TextField<String> txtChantier;
    UtilisateurGrpModel user = new UtilisateurGrpModel();
    UtilisateurGrpModel userModel;

    public AcceuilPanel() {
	loadChantier();
    }

    public SelectionListener<ButtonEvent> AddButtonEvent() {
	SelectionListener<ButtonEvent> add = new SelectionListener<ButtonEvent>() {
	    @Override
	    public void componentSelected(ButtonEvent ce) {
		final ChantierModel chantier = new ChantierModel();
		chantier.set("nom", txtChantier.getValue());
		if (isExistIdentifiant(txtChantier.getValue())) {
		    service.createChantier(chantier, user.getId(), new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable arg0) {
			}

			@Override
			public void onSuccess(Boolean arg0) {
			    storeChantier.removeAll();
			    loadChantier();
			}
		    });
		} else {
		    MessageBox.alert("Alert", messages.existChantier(), null);
		    txtChantier.clear();
		    txtChantier.focus();
		    btnCreer.setEnabled(false);
		}
	    }
	};
	return add;
    }

    private boolean isExistIdentifiant(String nom) {
	boolean added = true;
	for (ChantierModel model : gridChantier.getStore().getModels()) {
	    if (model.getNom().equalsIgnoreCase(nom)) {
		added = false;
	    }
	}
	return added;
    }

    public void loadChantier() {
	serviceUser.checkUserByKerberosSSO(new AsyncCallback<UtilisateurGrpModel>() {
	    @Override
	    public void onFailure(Throwable arg0) {
	    }

	    @Override
	    public void onSuccess(UtilisateurGrpModel arg0) {
		user = arg0;
		if (arg0.getBadmin() != null && arg0.getBadmin() == true) {
		    fsNouveauxChantier.setVisible(true);
		    service.findAll(new AsyncCallback<List<ChantierModel>>() {
			@Override
			public void onFailure(Throwable arg0) {
			}

			@Override
			public void onSuccess(List<ChantierModel> arg0) {
			    storeChantier.add(arg0);
			}
		    });
		} else {
		    service.findChantierByUser(arg0.getId(), new AsyncCallback<List<ChantierModel>>() {
			@Override
			public void onFailure(Throwable arg0) {
			}

			@Override
			public void onSuccess(List<ChantierModel> arg0) {
			    storeChantier.add(arg0);
			}
		    });
		}
	    }
	});
    }

    @Override
    protected void onRender(Element parent, int index) {
	super.onRender(parent, index);
	LayoutContainer mainContent = new LayoutContainer();
	setLayout(new RowLayout(Orientation.VERTICAL));
	setMonitorWindowResize(true);
	List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
	ContentPanel contentChantier = new ContentPanel();
	GridCellRenderer<ChantierModel> deleteBtn = new GridCellRenderer<ChantierModel>() {

	    @Override
	    public Object render(final ChantierModel model, String property, ColumnData config, int rowIndex, int colIndex, final ListStore<ChantierModel> store, Grid<ChantierModel> grid) {
		layoutcell = new LayoutContainer();
		layoutcell = new LayoutContainer();
		layoutcell.setLayout(new ColumnLayout());
		Listener<MessageBoxEvent> callback = new Listener<MessageBoxEvent>() {
		    @Override
		    public void handleEvent(MessageBoxEvent be) {
			Button btt = be.getButtonClicked();
			if (Dialog.OK.equals(btt.getItemId())) {
			    if (storeChantier == null) {
				return;
			    } else {
				storeChantier.remove(model);
				storeChantier.commitChanges();
				service.deleteChantier(model.getId(), new AsyncCallback<Void>() {

				    @Override
				    public void onFailure(Throwable arg0) {
				    }

				    @Override
				    public void onSuccess(Void arg0) {
				    }
				});

			    }
			}
		    }
		};
		img = createDeleteButton(callback, messages.confirmDelete());
		Image img1 = new Image("./images/imprimer.png");
		img1.setTitle("Imprimer");
		img1.addClickHandler(new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			// syn
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String exportPdfUrl = GWT.getHostPageBaseURL() + "acceuil.pdf";
			params.add(new NameValuePair("chantierId", model.getId() + ""));
			ReportUtil.showReport(exportPdfUrl, params.toArray(new NameValuePair[params.size()]));

		    }
		});
		Image img2 = new Image("./images/voir.png");
		img2.setTitle("Voir");
		if (user.getBadmin() != null && user.getBadmin() == true) {
		    layoutcell.add(img);
		}
		layoutcell.add(new Html("&nbsp;&nbsp;&nbsp;"));
		layoutcell.add(img1);
		layoutcell.add(new Html("&nbsp;&nbsp;&nbsp;"));
		layoutcell.add(img2);
		layoutcell.add(new Html("&nbsp;&nbsp;&nbsp;"));
		img2.addClickHandler(new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {
			serviceRole.findRoleById(model.getId(), user.getId(), new AsyncCallback<RoleModel>() {
			    @Override
			    public void onFailure(Throwable arg0) {
			    }

			    @Override
			    public void onSuccess(RoleModel role) {
				if (role != null && role.getBcontributeur() == false && role.getBlecteur() == false && user.getBadmin() == false) {
				    GuiUtil.gotoEcran(new ErrorEcran());
				} else {
				    GuiUtil.gotoEcran(new SyntheseEcran(model, role, user));
				}
			    }
			});
		    }
		});
		return layoutcell;
	    }
	};

	column = new ColumnConfig("supprimer", "", 150);
	column.setSortable(false);
	column.setMenuDisabled(true);
	column.setGroupable(false);
	column.setFixed(true);
	column.setResizable(false);

	column.setRenderer(deleteBtn);
	column.setAlignment(HorizontalAlignment.LEFT);

	configs.add(column);
	column_1 = new ColumnConfig();
	column_1.setId("nom");
	column_1.setHeader("Nom du chantier");
	column_1.setSortable(false);
	column_1.setMenuDisabled(true);
	column_1.setFixed(true);
	column_1.setAlignment(HorizontalAlignment.LEFT);
	configs.add(column_1);
	storeChantier = new ListStore<ChantierModel>();

	ColumnModel cm = new ColumnModel(configs);
	gridChantier = new CustomEditorGrid<ChantierModel>(storeChantier, cm);
	gridChantier.setAutoExpandColumn("nom");
	gridChantier.setHeight(370);
	gridChantier.setAutoExpandMax(1500);
	gridChantier.setAutoExpandMin(250);
	gridChantier.setWidth(1500);
	FieldSet fsChantier = new CustomFieldSet();
	add(fsChantier);
	fsChantier.setHeading("Chantiers existants");
	fsChantier.setAutoHeight(true);
	// fsChantier.setHeight(400);
	contentChantier.setLayout(new FitLayout());
	contentChantier.setHeaderVisible(false);
	contentChantier.setHeight(200);
	// contentChantier.setAutoHeight(true);
	contentChantier.add(gridChantier);
	fsChantier.add(contentChantier);
	fsNouveauxChantier = new CustomFieldSet();
	fsNouveauxChantier.setCollapsible(false);
	fsNouveauxChantier.setLayout(new ColumnLayout());
	LabelField lblfldNomDuChantier = new LabelField(messages.nameChantier() + ":");
	fsNouveauxChantier.add(lblfldNomDuChantier);
	txtChantier = new TextField<String>();
	txtChantier.addKeyListener(new KeyListener() {
	    @Override
	    public void componentKeyUp(ComponentEvent event) {
		if (txtChantier.getValue() != null && !txtChantier.getValue().trim().isEmpty()) {
		    btnCreer.setEnabled(true);
		} else {
		    btnCreer.setEnabled(false);
		}

	    }
	});
	fsNouveauxChantier.add(txtChantier, new com.extjs.gxt.ui.client.widget.layout.ColumnData(230.0));
	txtChantier.setFieldLabel("New TextField");
	SelectionListener<ButtonEvent> add = AddButtonEvent();
	btnCreer = new Button(messages.creer());
	btnCreer.addSelectionListener(add);
	btnCreer.setEnabled(false);
	fsNouveauxChantier.add(btnCreer, new com.extjs.gxt.ui.client.widget.layout.ColumnData(85.0));
	mainContent.add(fsChantier);
	mainContent.add(fsNouveauxChantier);
	setPadding(mainContent);
	fsNouveauxChantier.setVisible(false);
	fsNouveauxChantier.setHeading(messages.newChantier());
	fsNouveauxChantier.setStyleAttribute("backgroundColor", "#EDF5EA");
	add(mainContent);

    }

}
