package com.structis.vip.client.ecran;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HideMode;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.message.ActionMessages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.panel.HeaderPanel;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.client.view.ViewState;
import com.structis.vip.shared.model.UserModel;
import com.structis.vip.shared.model.UserRoleModel;

public class AbstractTabEcran extends LayoutContainer {
	NavigationService navigation = NavigationFactory.getNavigation();
	private ViewState viewState = new ViewState();	
	TabPanel tabSet = new TabPanel();
	HeaderPanel headerPanel;
	private LayoutContainer currentContent;
	private Action currentAction;
	
	ArrayList<Action> tabActionBYFE= new ArrayList<Action>();
	ArrayList<Action> tabActionETDE= new ArrayList<Action>();
	ArrayList<Action> tabActionList= new ArrayList<Action>();
	ActionMessages actionMessages = GWT.create(ActionMessages.class);
	TabItem tabControle;
	TabItem tabDocument;
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		headerPanel = new HeaderPanel();
		
		RootPanel.get("appHeaderRight").clear();
		RootPanel.get("appHeaderRight").add(headerPanel);
	}
//	public void initTab(LayoutContainer content, Action action){
//		
//		tabActionList.add(Action.ACTION_DELEGATION);
//		
//		if (SessionServiceImpl.getInstance().getEntiteContext() != null && 
//				(ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())
//						|| SessionServiceImpl.getInstance().getUserContext().isSuperUser())) {
//			//tabActionList.add(Action.ACTION_ACCEUIL);
//			tabActionList.add(Action.ACTION_CONTROL);
//		}
//		
//		if (SessionServiceImpl.getInstance().getEntiteContext() != null && 
//				(ConstantClient.ENTITE_ID_IS_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())
//						|| SessionServiceImpl.getInstance().getUserContext().isSuperUser())) {
//			tabActionList.add(Action.ACTION_DOCUMENT);
//		}
//		
//		
//		tabActionList.add(Action.ACTION_PILOTAGE);
//
//		
//		tabSet = new TabPanel();
//	    tabSet.setResizeTabs(true);
//
//	    tabSet.setMinTabWidth(115);
//	    tabSet.setResizeTabs(true);
//	    tabSet.setAnimScroll(true);
//	    tabSet.setTabScroll(true);
//	    
//	  
//	    for(final Action keyAction:tabActionList){
//	    	TabItem item = new TabItem();
//	    	item.setText(actionMessages.getString(keyAction.getLabel()));
//	    	item.setClosable(false);
//		    item.setLayout(new FitLayout());
//		    tabSet.add(item);
//	    	if(action==keyAction){
//	    		item.add(content, new FitData(0));
//	    		tabSet.setSelection(item);
//	    	}else{
//	    		item.addListener(Events.BeforeSelect, new Listener<ComponentEvent>() {
//	    			public void handleEvent(ComponentEvent be) {
//	    				if (!AppUtil.checkToShowWarningInEditMode()) {
//	    				disableEvents(true);
//	    				
//	    				navigation.goToEcran(keyAction);
//	    				be.setCancelled(true);
//	    				
//	    				disableEvents(false);
//	    				} else {
//	    					be.setCancelled(true);
//	    				}
//	    			}
//	    		});
//	    	}
////	    	if (keyAction == Action.ACTION_CONTROL) {
////	    		tabControl = item;
////	    	} else if (keyAction == Action.ACTION_DOCUMENT) {
////	    		tabDocument = item;
////	    	}
//	    		
//	    	
//	    }
//	   
//	    Viewport viewport = new Viewport();
//		final BorderLayout layout = new BorderLayout();  
//		viewport.setLayout(layout);  
//		viewport.setStyleAttribute("padding", "0px 0px 12px 2px");  
//		viewport.setBorders(true);
//		viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH,45));
//	    viewport.setStyleAttribute("background", "white");
//	    viewport.add(tabSet, new BorderLayoutData(LayoutRegion.CENTER));
//	    add(viewport);
//	}
	public void initTab(LayoutContainer content, Action action){		
		currentContent = content;
		currentAction = action;
		tabActionList.add(Action.ACTION_DELEGATION);		
		tabActionList.add(Action.ACTION_CONTROL);
		tabActionList.add(Action.ACTION_DOCUMENT);
		tabActionList.add(Action.ACTION_PILOTAGE);
		addTabItemsToList(content, tabActionList, action);
						
	    tabSet.setResizeTabs(true);
	    tabSet.setMinTabWidth(115);
	    tabSet.setResizeTabs(true);
	    tabSet.setAnimScroll(true);
	    tabSet.setTabScroll(true);	    	   	    		   
	   
	    Viewport viewport = new Viewport();
		final BorderLayout layout = new BorderLayout();  
		viewport.setLayout(layout);  
		viewport.setStyleAttribute("padding", "0px 0px 12px 2px");  
		viewport.setBorders(true);
		viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH,45));
	    viewport.setStyleAttribute("background", "white");
	    viewport.add(tabSet, new BorderLayoutData(LayoutRegion.CENTER));
	    add(viewport);
	}
	
	private void addTabItemsToList(LayoutContainer content, ArrayList<Action> tabActionList, Action action) {
		 for(final Action keyAction:tabActionList){
		    	TabItem item = new TabItem();
		    	item.setId("tab_"+actionMessages.getString(keyAction.getLabel()));
		    	item.setText(actionMessages.getString(keyAction.getLabel()));
		    	item.setClosable(false);
			    item.setLayout(new FitLayout());
			    tabSet.add(item);
		    	if(action==keyAction){
		    		item.add(content, new FitData(0));
		    		tabSet.setSelection(item);
		    	}else{
		    		item.addListener(Events.BeforeSelect, new Listener<ComponentEvent>() {
		    			public void handleEvent(ComponentEvent be) {
		    				if (!AppUtil.checkToShowWarningInEditMode()) {
		    				disableEvents(true);
		    				
		    				navigation.goToEcran(keyAction);
		    				be.setCancelled(true);
		    				
		    				disableEvents(false);
		    				} else {
		    					be.setCancelled(true);
		    				}
		    			}
		    		});
		    	}	
		    	if (keyAction == Action.ACTION_CONTROL) {
		    		tabControle = item;
		    	}
		    	if (keyAction == Action.ACTION_DOCUMENT) {
		    		tabDocument = item;
		    	} 
		    }
	}
	
	protected UserModel getUserContext() {
		return SessionServiceImpl.getInstance().getUserContext();
	}
	
	protected void setUserContext(UserModel usercontext) {
		SessionServiceImpl.getInstance().setUserContext(usercontext);
	}
	
	public ViewState getViewState() {
		viewState.clear();
		return viewState;
	}
	
	public void setViewState(final ViewState viewState) {
		this.viewState.clear();
	}
	
	public void resetTab() {
		disableEvents(true);
		UserModel user = SessionServiceImpl.getInstance().getUserContext();
		
		// add BYTP
//		if (SessionServiceImpl.getInstance().getEntiteContext() != null && 
//				(ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId()))) {
		if (SessionServiceImpl.getInstance().getEntiteContext() != null && 
				CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
			TabItem item = tabSet.getItemByItemId("tab_" + actionMessages.getString(Action.ACTION_DOCUMENT.getLabel()) );
			TabItem tabDelegation = tabSet.getItemByItemId("tab_" + actionMessages.getString(Action.ACTION_DELEGATION.getLabel()) );
			boolean tabSelected = true;
			if (item != null) {
				hideTab(tabDocument);				
			}			
			if (!hasDelegationRole(user)) {
//				showTab(tabDelegation);		
//			} else {
				hideTab(tabDelegation);
				tabSelected = false;
			}
			
			if (hasControlRole(user)) {
//				if (true) {
				showTab(tabControle);	
				if (!tabSelected) {
					tabSet.setSelection(tabControle);
				}
			} else {
				hideTab(tabControle);
			}
		} else {
//			TabItem item = tabSet.getItemByItemId("tab_" + actionMessages.getString(Action.ACTION_CONTROL.getLabel()) );
			if (tabControle != null) {
				hideTab(tabControle);				
			}			
			showTab(tabDocument);		
			
		}		
		tabSet.repaint();		
		disableEvents(false);		
	}
	
	private boolean hasDelegationRole(UserModel user) {
		List<UserRoleModel> roles =  user.getUserRoles();
		for (UserRoleModel r: roles) {
			if (r.getRole().getId() < 7) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasControlRole(UserModel user) {
		List<UserRoleModel> roles =  user.getUserRoles();
		for (UserRoleModel r: roles) {
			if (r.getRole().getId() == 1 || r.getRole().getId() == 2 || r.getRole().getId() == 3 || 
					r.getRole().getId() == 7 || r.getRole().getId() == 8) {
				return true;
			}
		}
		return false;
	}
	private void hideTab(TabItem tab) {
		tab.getHeader().hide();
		tab.hide();
	}
	private void showTab(TabItem tab) {
		tab.getHeader().show();
		tab.show();
	}
}
