package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.dialog.ChoosePerimetreTypeDialog;
import com.structis.vip.client.event.AdministrationTreeEvent;
import com.structis.vip.client.event.AdministrationTreeHandler;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.ModifyUserEvent;
import com.structis.vip.client.event.PerimetreEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientEntiteServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.exception.PerimetreException;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public class AdministrationTreePanel extends ContentPanel {

    private final Messages messages = GWT.create(Messages.class);

    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();
    private ClientEntiteServiceAsync clientEntiteService = ClientEntiteServiceAsync.Util.getInstance();

    private SimpleEventBus bus;

    private TreeLoader<PerimetreTreeModel> loader;
    private TreeStore<PerimetreTreeModel> store = new TreeStore<PerimetreTreeModel>();
    private TreePanel<PerimetreTreeModel> tree = new TreePanel<PerimetreTreeModel>(store);

    private Button btnAjouter;
    private Button btnModifier;
    private Button btnSupprimer;
    private Button btnSync;

    PerimetreTreeModel parentNode;
    String newNode;

    public AdministrationTreePanel(SimpleEventBus bus) {
        this.bus = bus;

        setHeading(messages.admintreeheading());
        setBodyBorder(true);
        setBorders(false);
        setScrollMode(Scroll.AUTO);
        setLayout(new RowLayout(Orientation.VERTICAL));
        setHeight(-1);

        initTreePanel();
        initButtonPanel();

        initEvent();
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);
    }

    private void initEvent() {
        bus.addHandler(AdministrationTreeEvent.getType(), new AdministrationTreeHandler() {

            @Override
            public void onLoadAction(AdministrationTreeEvent event) {
                if (event.getParentId() == null) {
                    loader.load();
                    ModifyUserEvent userEvent = new ModifyUserEvent();
                    userEvent.setPerId(null);
                    userEvent.setMode(ModifyUserEvent.MODE_IS_UPDATE_PERIMETRE);
                    bus.fireEvent(userEvent);
                } else {
                    boolean isFound = false;
                    for (PerimetreTreeModel model : store.getAllItems()) {
                        if (event.getParentId().equals(model.getPerId())) {
                            isFound = true;
                            loader.loadChildren(model);
                            break;
                        }
                    }
                    if (!isFound) {
                        loader.load();
                    }
                }
            }
        });

        bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                store.removeAll();
                loader.load();
                btnAjouter.setEnabled(true);
                if (SharedConstant.ENTITE_ID_ETDE.equalsIgnoreCase(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                    // btnAjouter.setEnabled(false);
                    btnSync.setEnabled(true);
                } else {
                    // btnAjouter.setEnabled(true);
                    btnSync.setEnabled(false);
                }
            }
        });
    }

    private void initTreePanel() {
        RpcProxy<List<PerimetreTreeModel>> proxy = new RpcProxy<List<PerimetreTreeModel>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<List<PerimetreTreeModel>> callback) {
                PerimetreTreeModel model = (PerimetreTreeModel) loadConfig;
                if (model == null) {
                    boolean isAdminForAllPerimetre = false;
                    for (UserRoleModel userRole : SessionServiceImpl.getInstance().getUserContext().getUserRoles()) {
                        if (userRole.getRole().isApplicationAdmin()) {
                            isAdminForAllPerimetre = true;
                            break;
                        }
                    }
                    if (isAdminForAllPerimetre) {
                        clientEntiteService.getTreeModelById(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), SessionServiceImpl
                                .getInstance().getUserContext().getUserRoles(), callback);
                    } else {
                        clientPerimetreService.getTreeModelById(SessionServiceImpl.getInstance().getPerimetreContext().getPerId(), SessionServiceImpl
                                .getInstance().getUserContext().getUserRoles(), callback);
                    }
                } else {
                    model.setName(SafeHtmlUtils.htmlEscape(model.getName()));
                    clientPerimetreService.getTreeModelByParent(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), SessionServiceImpl
                            .getInstance().getUserContext().getUserRoles(), model, callback);
                }
            }
        };

        loader = new BaseTreeLoader<PerimetreTreeModel>(proxy) {

            @Override
            public boolean hasChildren(PerimetreTreeModel parent) {
                return parent.getIsParent();
            }
        };

        store = new TreeStore<PerimetreTreeModel>(loader);

        tree = new TreePanel<PerimetreTreeModel>(store);
        tree.setId(ClientConstant.ADMIN_TREE_ID);
        tree.setDisplayProperty(PerimetreTreeModel.PERIMETRE_TREE_NAME);
        tree.setStateful(true);
        tree.setHeight("100%");
        tree.getStyle().setJointCollapsedIcon(IconHelper.createPath("html/icon/plus.jpg"));
        tree.getStyle().setJointExpandedIcon(IconHelper.createPath("html/icon/minus.jpg"));
        tree.getStyle().setNodeCloseIcon(null);
        tree.getStyle().setNodeOpenIcon(null);

        tree.setIconProvider(new ModelIconProvider<PerimetreTreeModel>() {

            @Override
            public AbstractImagePrototype getIcon(PerimetreTreeModel model) {
                if ((model.getIsUoAdmin() != null) && (model.getIsUoAdmin())) {
                    return IconHelper.createPath("html/icon/check.png");
                } else {
                    return null;
                }
            }

        });
        tree.getSelectionModel().addListener(Events.BeforeSelect, new Listener<SelectionEvent<PerimetreTreeModel>>() {

            @Override
            public void handleEvent(SelectionEvent<PerimetreTreeModel> be) {
                if (AppUtil.checkToShowWarningInAdminEditMode(true)) {
                    be.setCancelled(true);
                }
            }

        });

        tree.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<PerimetreTreeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<PerimetreTreeModel> se) {
                final PerimetreTreeModel node = tree.getSelectionModel().getSelectedItem();
                if ((node != null) && (!node.getIsEntite())) {
                    PerimetreEvent event = new PerimetreEvent();
                    event.setPerimetreId(node.getPerId());
                    event.setPerimetreParentId(node.getParent());
                    event.setMode(PerimetreEvent.MODE_IS_VIEW);
                    event.setIsUoAdmin(node.getIsUoAdmin());
                    String path = node.getPath() == null ? "" : node.getPath();
                    event.setPath(path);
                    bus.fireEvent(event);
                }

            }
        });

        add(tree, new RowData(1, 1));
    }

    private void initButtonPanel() {
        btnAjouter = new Button(messages.admintreebuttonajouter());
        btnAjouter.setWidth(70);
        btnAjouter.setEnabled(false);

        btnModifier = new Button(messages.admintreebuttonmodifier());
        btnModifier.setWidth(70);

        btnSupprimer = new Button(messages.admintreebuttonsupprimer());
        btnSupprimer.setWidth(70);

        btnSync = new Button(messages.admintreebuttonsync());
        btnSync.setWidth(70);
        btnSync.setEnabled(false);

        btnSync.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    final PerimetreTreeModel node = tree.getSelectionModel().getSelectedItem();
                    if (node != null) {
                        if (node.getIsUoAdmin()) {
                            if (SharedConstant.ENTITE_ID_ETDE.equals(node.getEntiteId())) {
                                // sync only for ETDE
                                if (node.getIsEntite()) {
                                    ChoosePerimetreTypeDialog dialog = new ChoosePerimetreTypeDialog(bus);
                                    dialog.show();
                                    dialog.initData(ClientConstant.PERIMETRE_ID_IS_TOP);
                                } else {
                                    ChoosePerimetreTypeDialog dialog = new ChoosePerimetreTypeDialog(bus);
                                    dialog.show();
                                    dialog.initData(node.getPerId());
                                }
                            } else {
                                Info.display(messages.commoninfo(), messages.admintreesyncetde());
                            }
                        } else {
                            Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
                        }
                    } else {
                        Info.display(messages.commoninfo(), messages.admintreeselect());
                    }
                }
            }
        });

        btnAjouter.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(true)) {
                    final PerimetreTreeModel node = tree.getSelectionModel().getSelectedItem();
                    if (node != null) {
                        if (node.getIsUoAdmin()) {
                            if (SharedConstant.ENTITE_ID_ETDE.equals(node.getEntiteId())) {
                                AppUtil.showConfirmMessageBox(messages.perimetreaddconfirm(node.getName()), messages.commonContinueButton(),
                                        messages.commonAnnulerButton(), new Listener<MessageBoxEvent>() {

                                            @Override
                                            public void handleEvent(MessageBoxEvent be) {

                                                if (be.getButtonClicked().getText().equalsIgnoreCase(messages.commonContinueButton())) {
                                                    // vip v1.1 - enable
                                                    // create new
                                                    // perimetre for
                                                    // etde
                                                    // if
                                                    // (!ConstantClient.ENTITE_ID_IS_ETDE.equals(node.getEntiteId()))
                                                    // {
                                                    // add for all
                                                    // entite, except
                                                    // ETDE
                                                    addNewParam(node);

                                                    // vip v1.1 - enable
                                                    // create new
                                                    // perimetre for
                                                    // etde
                                                    // } else {
                                                    // Info.display(messages.commoninfo(),
                                                    // messages.admintreecreenoetde());
                                                    // }
                                                }
                                            }

                                        });
                            } else {
                                addNewParam(node);
                            }

                        } else {
                            Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
                        }
                    } else {
                        Info.display(messages.commoninfo(), messages.admintreeselect());
                    }
                }
            }
        });

        btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(true)) {
                    final PerimetreTreeModel node = tree.getSelectionModel().getSelectedItem();
                    if ((node != null) && (!node.getIsEntite())) {
                        if (node.getIsUoAdmin()) {
                            // redirect to perimetre modified form for
                            // only perimetre
                            PerimetreEvent event = new PerimetreEvent();
                            event.setPerimetreId(node.getPerId());
                            event.setPerimetreParentId(node.getParent());
                            event.setMode(PerimetreEvent.MODE_IS_EDIT);
                            event.setIsUoAdmin(node.getIsUoAdmin());
                            String path = node.getPath() == null ? "" : node.getPath();
                            event.setPath(path);
                            ContentEvent content = new ContentEvent();
                            content.setMode(ContentEvent.CHANGE_MODE_TO_PERIMETRE_FORM);
                            content.setEvent(event);
                            bus.fireEvent(content);
                        } else {
                            Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
                        }
                    } else {
                        Info.display(messages.commoninfo(), messages.admintreeselect());
                    }
                }
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final PerimetreTreeModel node = tree.getSelectionModel().getSelectedItem();
                    // onMonday: continue checking when deleting
                    clientPerimetreService.hasReferenceInDelegationOrControlOrPerimetre(node.getPerId(), new AsyncCallback<Integer>() {

                        @Override
                        public void onSuccess(Integer arg0) {
                            if (arg0 > 0) { // if has at least a
                                            // delegation or
                                            // controle : cannot
                                            // delete the
                                            // perimetre
                                AppUtil.showError(messages.perimetredeletemanuallyhasdelegation());
                            } else { // if no delegation or
                                     // controle : check if
                                     // there is a user/
                                     // collaborateur use the
                                     // perimetre
                                clientPerimetreService.hasReferenceInUserCollaborateur(node.getPerId(), new AsyncCallback<Integer>() {

                                    @Override
                                    public void onSuccess(Integer arg0) {
                                        if (arg0 > 0) { // if
                                                        // has
                                                        // at
                                                        // least
                                                        // a
                                                        // user
                                                        // or
                                                        // collaborateur
                                                        // :
                                                        // show
                                                        // warning
                                                        // to
                                                        // update
                                                        // referenece
                                                        // to
                                                        // null
                                            // DeletePerimetreDialog
                                            // dlg
                                            // =
                                            // new
                                            // DeletePerimetreDialog(bus,node.getPerId(),arg0);
                                            // dlg.show();
                                            AppUtil.showConfirmMessageBox(messages.perimetredeletemanuallynodelegation(),
                                                    new Listener<MessageBoxEvent>() {

                                                        @Override
                                                        public void handleEvent(MessageBoxEvent be) {
                                                            if (be.getButtonClicked().getText().equalsIgnoreCase(messages.commonDialogOuiButton())) {
                                                                // no
                                                                // action

                                                            } else { // set
                                                                     // null
                                                                     // to
                                                                     // every
                                                                     // reference,
                                                                     // then
                                                                     // delete
                                                                     // the
                                                                     // perimetre
                                                                clientPerimetreService.clearReferenceToPerimetreInUserCollaborateur(node.getPerId(),
                                                                        new AsyncCallback<Void>() {

                                                                            @Override
                                                                            public void onSuccess(Void arg0) {
                                                                                deletePerimetre(node);
                                                                            }

                                                                            @Override
                                                                            public void onFailure(Throwable arg0) {
                                                                            }
                                                                        });
                                                            }
                                                        }

                                                    });
                                        } else { // if
                                                 // no
                                                 // reference
                                                 // ->
                                                 // allow
                                                 // delete
                                                 // the
                                                 // perimetre
                                            deletePerimetre(node);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable arg0) {
                                        String details = messages.perimetredeletefailed();
                                        if (arg0 instanceof PerimetreException) {
                                            details = ExceptionMessageHandler.getErrorMessage(((PerimetreException) arg0).getCode());
                                        }
                                        Info.display(messages.commonerror(), details);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                            String details = messages.perimetredeletefailed();
                            if (arg0 instanceof PerimetreException) {
                                details = ExceptionMessageHandler.getErrorMessage(((PerimetreException) arg0).getCode());
                            }
                            Info.display(messages.commonerror(), details);
                        }
                    });
                    // clientPerimetreService.deleteById(node.getPerId(), new
                    // AsyncCallback<Boolean>() {
                    //
                    // @Override
                    // public void onSuccess(Boolean arg0) {
                    // if (arg0) {
                    // // update tree
                    // AdministrationTreeEvent treeEvent = new
                    // AdministrationTreeEvent();
                    // treeEvent.setParentId(node.getParent());
                    // // update perimetre form
                    // PerimetreEvent event = new PerimetreEvent();
                    // event.setMode(PerimetreEvent.MODE_IS_NOT_SELECTED);
                    // bus.fireEvent(event);
                    // bus.fireEvent(treeEvent);
                    // Info.display(messages.commoninfo(),
                    // messages.perimetredeletesuccess());
                    // }
                    // }
                    //
                    // @Override
                    // public void onFailure(Throwable arg0) {
                    // String details = messages.perimetredeletefailed();
                    // if (arg0 instanceof PerimetreException) {
                    // details =
                    // ExceptionMessageHandler.getErrorMessage(((PerimetreException)
                    // arg0).getCode());
                    // }
                    // Info.display(messages.commonerror(), details);
                    // }
                    // });
                }
            }
        };

        btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(true)) {
                    final PerimetreTreeModel node = tree.getSelectionModel().getSelectedItem();
                    if ((node != null) && (!node.getIsEntite())) {
                        if (node.getIsUoAdmin()) {
                            MessageBox box = new MessageBox();
                            box.setButtons(MessageBox.YESNO);
                            box.setIcon(MessageBox.INFO);
                            box.setTitle(messages.commonConfirmation());
                            box.addCallback(l);
                            box.setMessage(messages.perimetredeleteconfirm());
                            ((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonOui());
                            ((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonNon());
                            box.show();
                        } else {
                            Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
                        }
                    } else {
                        Info.display(messages.commoninfo(), messages.admintreeselect());
                    }
                }
            }
        });

        LayoutContainer action = new LayoutContainer();
        action.setLayout(new TableLayout(2));
        action.setStyleAttribute("marginLeft", "10px");

        action.add(btnAjouter, new TableData("100", "30"));
        action.add(btnModifier, new TableData("100", "30"));
        action.add(btnSupprimer, new TableData("100", "30"));
        action.add(btnSync, new TableData("100", "30"));

        setBottomComponent(action);
    }

    private void deletePerimetre(final PerimetreTreeModel node) {
        clientPerimetreService.deleteById(node.getPerId(), new AsyncCallback<Boolean>() {

            @Override
            public void onSuccess(Boolean arg0) {
                if (arg0) {
                    // update tree
                    AdministrationTreeEvent treeEvent = new AdministrationTreeEvent();
                    treeEvent.setParentId(node.getParent());
                    // update perimetre form
                    PerimetreEvent event = new PerimetreEvent();
                    event.setMode(PerimetreEvent.MODE_IS_NOT_SELECTED);
                    bus.fireEvent(event);
                    bus.fireEvent(treeEvent);
                    Info.display(messages.commoninfo(), messages.perimetredeletesuccess());
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
                String details = messages.perimetredeletefailed();
                if (arg0 instanceof PerimetreException) {
                    details = ExceptionMessageHandler.getErrorMessage(((PerimetreException) arg0).getCode());
                }
                Info.display(messages.commonerror(), details);
            }
        });
    }

    private void addNewParam(PerimetreTreeModel node) {
        PerimetreEvent event = new PerimetreEvent();
        if (node.getIsEntite()) {
            event.setPerimetreId(null);
            event.setPerimetreParentId(null);
        } else {
            event.setPerimetreId(null);
            event.setPerimetreParentId(node.getPerId());
        }
        event.setMode(PerimetreEvent.MODE_IS_CREATE);
        String path = node.getPath() == null ? "" : node.getPath();
        event.setPath(path);
        event.setIsUoAdmin(node.getIsUoAdmin());
        ContentEvent content = new ContentEvent();
        content.setMode(ContentEvent.CHANGE_MODE_TO_PERIMETRE_FORM);
        content.setEvent(event);
        bus.fireEvent(content);
    }
}
