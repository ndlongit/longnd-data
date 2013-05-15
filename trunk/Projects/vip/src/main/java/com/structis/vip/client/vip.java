package com.structis.vip.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.util.Theme;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.ActionHelper;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.service.ClientAuthenticationServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class vip implements EntryPoint, ValueChangeHandler<String> {

	private NavigationService navigation = NavigationFactory.getNavigation();

	private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Choose the theme
		GXT.setDefaultTheme(Theme.BLUE, true);

		// TODO do we add handle for history change?
		// History.addValueChangeHandler(this);
		if (SessionServiceImpl.getInstance().getUserContext() == null) {
			ClientAuthenticationServiceAsync.Util.getInstance().ssoLogin(new AsyncCallback<UserModel>() {
				@Override
				public void onSuccess(UserModel userContext) {
					if (userContext == null) {
						navigation.goToEcran(Action.ACTION_LOGIN);
					} else {
						SessionServiceImpl.getInstance().setUserContext(userContext);
						if (userContext.isAdministrateur() || (userContext.getPerimetre() == null)) {
							navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
						} else {
							clientPerimetreService.findFirstLevelPerimetreByUserRoles(userContext.getEntite().getEntId(), false, SessionServiceImpl.getInstance().getUserContext().getUserRoles(),
									new AsyncCallback<List<PerimetreModel>>() {
										@Override
										public void onSuccess(List<PerimetreModel> arg0) {
											boolean isAuto = false;
											for (PerimetreModel perMdl : arg0) {
												if (perMdl.getPerId().equals(SessionServiceImpl.getInstance().getUserContext().getPerimetre().getPerId())) {
													SessionServiceImpl.getInstance().setEntiteContext(SessionServiceImpl.getInstance().getUserContext().getEntite());
													SessionServiceImpl.getInstance().setPerimetreContext(SessionServiceImpl.getInstance().getUserContext().getPerimetre());
													NavigationEvent e = new NavigationEvent(new DelegationListProjectEvent(SessionServiceImpl.getInstance().getUserContext().getEntite(), 
																								SessionServiceImpl.getInstance().getUserContext().getPerimetre()));
													isAuto = true;
													navigation.goToEcran(Action.ACTION_DELEGATION, e);
													break;
												}
											}
											if (!isAuto) {
												navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
											}
										}
	
										@Override
										public void onFailure(Throwable arg0) {
											navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
										}
									});
						}
					}
				}
	
				@Override
				public void onFailure(Throwable caught) {
					navigation.goToEcran(Action.ACTION_LOGIN);
				}
			});
		} else {			
			UserModel userContext = SessionServiceImpl.getInstance().getUserContext();
			if (userContext.isAdministrateur() || (userContext.getPerimetre() == null)) {
				navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
			} else {
				clientPerimetreService.findFirstLevelPerimetreByUserRoles(userContext.getEntite().getEntId(), false, SessionServiceImpl.getInstance().getUserContext().getUserRoles(),
						new AsyncCallback<List<PerimetreModel>>() {
							@Override
							public void onSuccess(List<PerimetreModel> arg0) {
								boolean isAuto = false;
								for (PerimetreModel perMdl : arg0) {
									if (perMdl.getPerId().equals(SessionServiceImpl.getInstance().getUserContext().getPerimetre().getPerId())) {
										SessionServiceImpl.getInstance().setEntiteContext(SessionServiceImpl.getInstance().getUserContext().getEntite());
										SessionServiceImpl.getInstance().setPerimetreContext(SessionServiceImpl.getInstance().getUserContext().getPerimetre());
										NavigationEvent e = new NavigationEvent(new DelegationListProjectEvent(SessionServiceImpl.getInstance().getUserContext().getEntite(), 
																					SessionServiceImpl.getInstance().getUserContext().getPerimetre()));
										isAuto = true;
										navigation.goToEcran(Action.ACTION_DELEGATION, e);
										break;
									}
								}
								if (!isAuto) {
									navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
								}
							}

							@Override
							public void onFailure(Throwable arg0) {
								navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
							}
						});
			}		
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
//		navigation.goToEcran(Action.ACTION_LOGIN);
		String rawEvent = event.getValue();
		String realEvent = rawEvent;
		Map<String, String> params = new HashMap<String, String>();

		if (realEvent.contains("&")) {
			// parse parameters
			realEvent = rawEvent.substring(0, rawEvent.indexOf("&"));
			String paramsString = rawEvent.substring(rawEvent.indexOf("&"));
			for (String string : paramsString.split("&")) {
				String[] text = string.split("=", 2);
				if (text.length == 2) {
					params.put(text[0], text[1]);
				}
			}
		}

		Action dest = ActionHelper.getActionFromLabel(realEvent);

		if (null == dest) {
			navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
		} else {
			if (navigation.getActionActuelle() != dest) {
				navigation.goToEcran(dest, new NavigationEvent(params));
			} else if (!params.isEmpty()) {
				navigation.goToEcran(dest, new NavigationEvent(params, false));
			}
		}
	}
}
