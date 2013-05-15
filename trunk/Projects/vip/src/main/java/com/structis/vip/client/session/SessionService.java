package com.structis.vip.client.session;

import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.view.ViewState;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public interface SessionService {
	
	UserModel getUserContext();
	
	void setUserContext(final UserModel userContext);
	
	EntiteModel getEntiteContext();

	void setEntiteContext(EntiteModel entiteContext);

	PerimetreModel getPerimetreContext();

	void setPerimetreContext(PerimetreModel perimetreContext);

	Action getActionContext();

	void setActionContext(Action actionContext);
	
	ViewState getSessionState(ViewState viewState);
	
	boolean setSessionState(final Action action, final ViewState viewState);
	
	boolean isSessionClose();
	
	void killSession();
	
	LanguageModel getLanguageContext();
	
	void setLanguageContext(LanguageModel languageModel);
}
