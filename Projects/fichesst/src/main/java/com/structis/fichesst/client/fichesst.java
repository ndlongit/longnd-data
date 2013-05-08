package com.structis.fichesst.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.client.ecran.AcceuilEcran;
import com.structis.fichesst.client.ecran.ErrorEcran;
import com.structis.fichesst.client.ecran.SyntheseEcran;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.client.service.ClientChantierServiceAsync;
import com.structis.fichesst.client.service.ClientRoleService;
import com.structis.fichesst.client.service.ClientRoleServiceAsync;
import com.structis.fichesst.client.service.ClientUtilsateurGrpService;
import com.structis.fichesst.client.service.ClientUtilsateurGrpServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class fichesst implements EntryPoint, ValueChangeHandler<String> {
	String action = "";

	NavigationService navigation = NavigationFactory.getNavigation();

	private final ClientUtilsateurGrpServiceAsync service = GWT.create(ClientUtilsateurGrpService.class);

	private final ClientRoleServiceAsync serviceRole = GWT.create(ClientRoleService.class);

	UtilisateurGrpModel user = new UtilisateurGrpModel();

	public void loadApplicationContext() {
		GWT.log("Init application context...");
		final Map<String, String> params = new HashMap<String, String>();
		String token = History.getToken();

		action = token;
		if( token.contains("&") ) {
			String paramsString = token.substring(token.indexOf("&"));
			action = token.substring(0, token.indexOf("&"));
			for( String string : paramsString.split("&") ) {
				String[] text = string.split("=");
				if( text.length == 2 ) {
					params.put(text[0], text[1]);
				}
			}
		}
		else {
			action = token;
		}

		service.checkUserByKerberosSSO(new AsyncCallbackWithErrorResolution<UtilisateurGrpModel>() {
			@Override
			public void onFailure(Throwable arg0) {
				GuiUtil.gotoEcran(new ErrorEcran());
			}

			@Override
			public void onSuccess(final UtilisateurGrpModel user) {
				navigation.getContext().setUserModel(user);
//				GuiUtil.gotoEcran(new AcceuilEcran());

				//For testing
				ClientChantierServiceAsync clientChantierService = GWT.create(ClientChantierService.class);
				clientChantierService.findChantierById(6, new AsyncCallback<ChantierModel>() {
					@Override
					public void onSuccess(ChantierModel chantier) {
						RoleModel roleModel = new RoleModel();
						roleModel.setIdentifiant("bycn\\long.nguyen");
						roleModel.setIdUtilisateurGrp(7);
						roleModel.setBcontributeur(true);
						roleModel.setBlecteur(true);
						GuiUtil.gotoEcran(new SyntheseEcran(chantier, roleModel, user));
					}

					@Override
					public void onFailure(Throwable caught) {
						GuiUtil.gotoEcran(new ErrorEcran());
					}
				});
			}
		});
	}

	@Override
	public void onModuleLoad() {
		loadApplicationContext();
		GWT.log("Start Loading ...");
		History.addValueChangeHandler(this);
		String currentToken = History.getToken();
		GWT.log("Go to token" + currentToken);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String rawEvent = event.getValue();
		String realEvent = rawEvent;
		GWT.log("URL Change ..." + realEvent);
		Map<String, String> params = new HashMap<String, String>();
		if( realEvent.contains("&") ) {
			realEvent = rawEvent.substring(0, rawEvent.indexOf("&"));
			String paramsString = rawEvent.substring(rawEvent.indexOf("&"));
			for( String string : paramsString.split("&") ) {
				String[] text = string.split("=", 2);
				if( text.length == 2 ) {
					params.put(text[0], text[1]);
				}
			}
		}
	}

}
