package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.VipEvents;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.widget.DialogValider;
import com.structis.vip.client.widget.EcoPagingToolBar;
import com.structis.vip.shared.config.ReferencePagingLoadConfig;
import com.structis.vip.shared.model.BaseModelDataActivable;

/**
 * Classe abstraite qui contient le template de la cr�ation d'un panel administration
 * Il fournit les composant de base telque : la pagination, le bottom toolbar etc.
 * 
 * @author b.brotosumpeno
 *
 * @param <M>
 */
public abstract class AbstractReferenceContentPanel<M extends BaseModelDataActivable>
		extends ContentPanel implements ValiderAnnulerPanelIfc {
	
	/** Message */
	protected final Messages messages = GWT.create(Messages.class);
	
	/** Liste store de model data */
	protected ListStore<M> listeStoreModels = null;
	
	@SuppressWarnings("rawtypes")
	/** Le paging loader */
	protected PagingLoader loader = null;
	
	/** Dialog validation dans le cas des activations */
	protected DialogValider dialog = null;
	
	/** Utilisez la listeModels dans le cas de LocalPaging */
	protected List<M> listeModels = new ArrayList<M>();
	
	/** Contient les donn�es en de hors de paging, il sera ajout� lors de validation */
	protected List<M> listeNonCommitedModels = new ArrayList<M>();
	
	/** Liste temporer */
	protected List<M> listeUpdateModels = new ArrayList<M>();
	
	/** La liste � envoyer */
	protected List<M> listeToSend = new ArrayList<M>();
	
	/** La liste de changement */
	private List<MapTemp> listeMapChangeTemps = new ArrayList<MapTemp>();
	
	/**
	 * Class interne pour stocker les modifs temporer
	 * @author b.brotosumpeno
	 *
	 */
	@SuppressWarnings("rawtypes")
	private class MapTemp {
		
		@SuppressWarnings("unchecked")
		public MapTemp(M model, int offset, Map map) {
			this.model = model;
			this.offset = offset;
			this.map.putAll(map);
		}
				M model;
		int offset;
		
		Map map = new HashMap();
		
	}

	/**
	 * Action appel�e lors de chaque chargement de l'�cran
	 */
	protected abstract void onLoadApplication(Map<String, String> params);
	
	protected abstract void onLoadApplicationFromURL(Map<String, String> params);
	
	/**
	 * Cr�ation de contenu de panel (le grid, la colonne, les formulaires et le bouton ajouter. etc)
	 */
	protected abstract void createContent();
	
	/**
	 * Action appel�e lors de la mise � jour de model
	 */
	protected abstract void onCallUpdateListe();
	
	/**
	 * Cr�ation de proxy 
	 */
	@SuppressWarnings("rawtypes")
	protected abstract DataProxy getProxy();
	
	/**
	 * Cr�ation si remote ou pas 
	 */
	protected abstract boolean isRemote();
	
	/**
	 * Cr�ation si remote ou pas 
	 */
	protected abstract void updateListeModel(BaseEvent be);
	
	/**
	 * Les propri�tes initialisation
	 */
	protected int pagingSize = 0;
	protected int panelHeigh = 0;
	
	protected void initModels(){
		listeToSend.clear();
		listeModels.clear();
		listeNonCommitedModels.clear();
		listeUpdateModels.clear();
		listeMapChangeTemps.clear();
		listeStoreModels.rejectChanges();
	}
	
	/**
	 * Initialization des propri�t�s
	 */
	protected void initProperty(){
		pagingSize = 20;
		panelHeigh = 520;
	}
	
	/**
	 * La cr�ation des composants basiques du panel
	 */
	@SuppressWarnings("rawtypes")
	public void buildPanel() {

		// ############ Initializer le properties ##########
		initProperty();
		
	    // ############ Pr�parer le content panel ###########
		setFrame(false);
		setLayout(new FitLayout());
		setHeight(panelHeigh);
		setHeaderVisible(false);
		setBorders(false);
		setBodyBorder(false);
		
		// ############### Pr�parer le PAGING TOOLBAR ################
		DataProxy proxy = getProxy();
		
		loader = new BasePagingLoader(proxy) {

			@Override
			protected Object prepareLoadConfig(Object config) {
				BasePagingLoadConfig pagingConfig = (BasePagingLoadConfig) super.prepareLoadConfig(config);
				ReferencePagingLoadConfig refConfig = new ReferencePagingLoadConfig();
				refConfig.setProperties(pagingConfig.getProperties());
				refConfig.setSortField(pagingConfig.getSortField());
				refConfig.getProperties();
				refConfig.setPrepareLoadConfig(true);
				return refConfig;
			}
			
			
		};
		loader.setRemoteSort(isRemote());
		
		/*
		 * Ajoute la gestion de sauvegarde des donn�es modifier,
		 * Sauvegarder tous les donn�es modifi�es avant le chargement
		 */
		loader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>(){
			
			@SuppressWarnings("unchecked")
			public void handleEvent(LoadEvent be) {
				// Reinit modifs
				List<Record> records = listeStoreModels.getModifiedRecords();
				for (Record record : records) {
					// Ajouter
					listeMapChangeTemps.add(new MapTemp((M)record.getModel(), loader.getOffset(), record.getChanges()));
				}
			}
			
		});
		
		listeStoreModels = new ListStore<M>(loader);
		
		/*
		 * On recopie tous les donn�es dans le listeStore charg�
		 * Il y a deux types de nouvelles donn�es
		 * 1) Celui de modification
		 * 2) Celui d'ajoute
		 */
		listeStoreModels.getLoader().addLoadListener(new LoadListener(){

			@SuppressWarnings("unchecked")
			@Override
			public void loaderLoad(LoadEvent le) {
				// Ajoute ancien modifs
				List<MapTemp> listeMapChangeTempsToDelete = new ArrayList<MapTemp>();
				for (MapTemp m : listeMapChangeTemps) {
					if (null == m.model.getId()) {
						if (m.offset == loader.getOffset() 
								&& !listeStoreModels.contains(m.model)) {
							listeStoreModels.add(m.model);
							Record record = listeStoreModels.getRecord(m.model);
							for (String key : record.getPropertyNames()) {
								Object temp = m.model.get(key);
								m.model.set(key, null);
								record.set(key, temp);
							}
						}
					} 
					else {
						for (M mls : listeStoreModels.getModels()) {
							// le m.model et mls peuvent �tre diff�rent selon le type de pagination (local ou remote)
							if (m.model.getId() == mls.getId()) {
								Record record = listeStoreModels.getRecord(mls);
								Set<String> keys = m.map.keySet();
								for (String key : keys) {
									Object temp = m.model.get(key);
									mls.set(key, m.map.get(key));
									record.set(key, temp);
								}
								listeMapChangeTempsToDelete.add(m);
							}
						}
					}
				}
				listeMapChangeTemps.removeAll(listeMapChangeTempsToDelete);
			}
		});
		
		EcoPagingToolBar pagingBar = new EcoPagingToolBar(pagingSize);
		pagingBar.bind(loader);
		setTopComponent(pagingBar);
		
		// ############# Appel la cr�ation sp�cifique de l'�cran ###############
		createContent();
		
		// ################ Cr�er le dialog valider ######################
		dialog = new DialogValider();
		dialog.addValiderListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				onCallUpdateListe();
			}
		});
		
		// ################ Ajoute listener pour cas success ou failure ########
		addListener(VipEvents.Success, new Listener<BaseEvent>() {
			
			public void handleEvent(BaseEvent be) {
				listeModels.clear();
				updateListeModel(be);
				listeNonCommitedModels.clear();
				listeMapChangeTemps.clear();
				listeStoreModels.commitChanges();
				loader.load(0, pagingSize);
				Info.display( messages.commonInfoHeader(), messages.commonMajSucces());
			}
		});
		
	};

	public void onLoadPanel(Map<String, String> params, boolean init){
		if (init){
			initModels();
		}
		if (!params.containsKey(ConstantClient.PAGINATION)) {
			params.put(ConstantClient.CANCEL_HISTORY, "ok");
			onLoadApplication(params);
		}
		else {
			onLoadApplicationFromURL(params);
		}
		clearBottomToolBar();
		
		// Refresh

		if (isGridRefresh()){
			refreshGrid();
		}
	}
	
	/**
	 * @return true si le grid doit �tre rafra�chir lors d'un chargement
	 *         par d�faut il est true
	 */
	protected boolean isGridRefresh(){
		return true;
	}

	/**
	 * Refresh le grid pour �viter le probl�me des d�calage
	 */
	private void refreshGrid() {
		for (Iterator<Component> iterator = iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			if (component instanceof Grid<?>) {
				Grid<?> grid = (Grid<?>) component;
				grid.getView().refresh(true);
				break;
			}
			
		}
	}

	/**
	 * Action lors d'une annulation
	 */
	public void onAnnuler(){
		listeStoreModels.rejectChanges();
    	loader.load(loader.getOffset(), pagingSize);
    	listeMapChangeTemps.clear();
    	
    	// nettoyage de bottom toolbar
    	clearBottomToolBar();
    	fireEvent(VipEvents.Annulation);
	}
	
	/**
	 * Action lors d'une validation
	 */
	@SuppressWarnings("unchecked")
	public void onValider() {
		// Init des donn�es temporaires
		listeToSend.clear();
		listeUpdateModels.clear();
		
		// Recuperer tous les donn�es modifi�ss ou ajout�es
		List<Record> modifiedRecords = listeStoreModels.getModifiedRecords();
		dialog.clearDesactiveModel();
		boolean valide = true;
		M modelIncorect = null;
		for (Record record : modifiedRecords) {
			for(String property : record.getPropertyNames()) {
				// Tester si le record valide ou pas
				if (!record.isValid(property)) {
					valide = false;
					modelIncorect = (M)record.getModel();
					break;
				}
			}
			// Cr�er un model � partir d'un record
			M model = (M)record.getModel();
			addModel(model);
		}
		 
		// Ajoute celui de listeMapChangeTemps
		for(MapTemp mapTemps : listeMapChangeTemps) {
			// R�cuperer un model � partir d'un Map Temp
			M model = mapTemps.model;
			addModel(model);
			
		}
		
		// Preparer les donn�es � envoyer
		listeToSend.addAll(listeUpdateModels);
		listeToSend.addAll(listeNonCommitedModels);
		
		if(!valide) {
			// Donn�es invalid => message d'erreur
			MessageBox.alert(messages.commonErreurHeader(), messages.commonModelInvalide() + modelIncorect, null);
		}
		else if (dialog.isDesactiveModelClear()) {
			// S'il n'y a pas desactivation
			onCallUpdateListe();
		}
		else {
			// Ouvrir la dialog de desactivation
			dialog.show();
		}
		
		// nettoyage de bottom toolbar
    	clearBottomToolBar();
	}
	
	private void addModel(M model) {
		// Ajoute les donn�es dans la liste update models
		listeUpdateModels.add(model);
		
		// Une unite est desactiv� si et seulement si 'DataSuppr' 'est' null mais 'active' est 'false'
		if ((model.getDateSuppr() == null) && !model.getActive()) {
			// Ajoute le libelle pour la confirmation desactivation
			dialog.addDesactive(model.getLibelle());
		}
	}
	
	/**
	 * Nettoyage de bottom toolBar
	 */
	@SuppressWarnings("rawtypes")
	protected void clearBottomToolBar(){
    	// Nettoyage de field
    	// Uniquement pour les types field (pas form)
    	Component bottonComp = getBottomComponent();
    	if (bottonComp instanceof ToolBar) {
    		ToolBar toolBar = (ToolBar) bottonComp;
    		List<Component> items = toolBar.getItems();
    		for (Component compToolBar : items) {
				if(compToolBar instanceof Field) {
					((Field) compToolBar).clear();
				}
				else if (compToolBar instanceof FormPanel) {
					((FormPanel) compToolBar).clear();
				}
			}
    	}
	}


}
