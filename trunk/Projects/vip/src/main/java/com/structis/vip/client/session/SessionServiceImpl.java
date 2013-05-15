package com.structis.vip.client.session;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.service.ClientUserContextServiceAsync;
import com.structis.vip.client.view.ViewState;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class SessionServiceImpl implements SessionService {

	private UserModel userContext;

	private EntiteModel entiteContext;

	private PerimetreModel perimetreContext;

	private Action actionContext;
	
	private LanguageModel languageContext;

	private static final SessionService instance = new SessionServiceImpl();

	private SessionServiceImpl() {
	}

	public static SessionService getInstance() {
		return instance;
	}

	@Override
	public UserModel getUserContext() {
		return userContext;
	}

	@Override
	public void setUserContext(UserModel userContext) {
		this.userContext = userContext;
	}
	
	

	@Override
	public EntiteModel getEntiteContext() {
		return entiteContext;
	}

	@Override
	public void setEntiteContext(EntiteModel entiteContext) {
		this.entiteContext = entiteContext;
	}

	@Override
	public PerimetreModel getPerimetreContext() {
		return perimetreContext;
	}

	@Override
	public void setPerimetreContext(PerimetreModel perimetreContext) {
		this.perimetreContext = perimetreContext;
	}

	@Override
	public Action getActionContext() {
		return actionContext;
	}

	@Override
	public void setActionContext(Action actionContext) {
		this.actionContext = actionContext;
	}

	@Override
	public ViewState getSessionState(ViewState viewState) {
		// TODO: implements saving session
		return null;
	}

	@Override
	public boolean setSessionState(Action action, ViewState viewState) {
		// TODO: imeplement saving session
		return true;
	}

	@Override
	public boolean isSessionClose() {
		if (userContext != null) {
			if (userContext.isSessionExpired(new Date())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void killSession() {
		if (userContext != null) {
			ClientUserContextServiceAsync.Util.getInstance().disconnectUser(userContext, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void arg0) {
				}

				@Override
				public void onFailure(Throwable t) {
					GWT.log("error in SessionServiceImpl.killSession", t);
				}
			});
		}

	}

	@Override
	public LanguageModel getLanguageContext() {
		return this.languageContext;
	}

	@Override
	public void setLanguageContext(LanguageModel languageModel) {
		this.languageContext = languageModel;
	}
}
