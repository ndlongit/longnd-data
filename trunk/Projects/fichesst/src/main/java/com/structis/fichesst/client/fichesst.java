package com.structis.fichesst.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.structis.fichesst.client.ecran.AcceuilEcran;
import com.structis.fichesst.client.ecran.AdminEcran;
import com.structis.fichesst.client.ecran.ErrorEcran;
import com.structis.fichesst.client.ecran.FicheSTEcran;
import com.structis.fichesst.client.ecran.SyntheseEcran;
import com.structis.fichesst.client.ecran.TransfertppEcran;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.client.service.ClientChantierServiceAsync;
import com.structis.fichesst.client.service.ClientFicheStService;
import com.structis.fichesst.client.service.ClientFicheStServiceAsync;
import com.structis.fichesst.client.service.ClientRoleService;
import com.structis.fichesst.client.service.ClientRoleServiceAsync;
import com.structis.fichesst.client.service.ClientUtilsateurGrpService;
import com.structis.fichesst.client.service.ClientUtilsateurGrpServiceAsync;
import com.structis.fichesst.client.util.CookieUtil;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class fichesst implements EntryPoint, ValueChangeHandler<String> {
	String action = "";

	NavigationService navigation = NavigationFactory.getNavigation();

	private final ClientUtilsateurGrpServiceAsync service = GWT.create(ClientUtilsateurGrpService.class);

	private final ClientChantierServiceAsync serviceChantier = GWT.create(ClientChantierService.class);

	private final ClientFicheStServiceAsync serviceFichest = GWT.create(ClientFicheStService.class);

	private final ClientRoleServiceAsync serviceRole = GWT.create(ClientRoleService.class);

	UtilisateurGrpModel user = new UtilisateurGrpModel();

	public void loadApplicationContext() {
		final Map<String, String> params = new HashMap<String, String>();
		String token = History.getToken();
		History.fireCurrentHistoryState();

		action = token;
		if (token.contains("&")) {
			String paramsString = token.substring(token.indexOf("&"));
			action = token.substring(0, token.indexOf("&"));
			for (String string : paramsString.split("&")) {
				String[] text = string.split("=");
				if (text.length == 2) {
					params.put(text[0], text[1]);
				}
			}
		} else {
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
				if (History.getToken().equalsIgnoreCase("admin") && user.getBadmin() != null && user.getBadmin() == true) {
					GuiUtil.gotoEcran(new AdminEcran());
				} else {
					String currentChantierId = CookieUtil.getCurrentChantier();
					if (History.getToken().equalsIgnoreCase("newfichest") && user.getBadmin() != null && user.getBadmin() == true) {

						serviceChantier.findChantierById(Integer.parseInt(currentChantierId), new AsyncCallbackWithErrorResolution<ChantierModel>() {
							@Override
							public void onSuccess(ChantierModel arg0) {
								navigation.getContext().setCurrentChantier(arg0);
								GuiUtil.gotoEcran(new FicheSTEcran());
							}
						});

					} else if (currentChantierId != null && History.getToken().equalsIgnoreCase(currentChantierId)) {
						serviceRole.findRoleById(Integer.parseInt(currentChantierId), user.getId(),
								new AsyncCallbackWithErrorResolution<RoleModel>() {

									@Override
									public void onSuccess(RoleModel arg0) {
										navigation.getContext().setRoleModel(arg0);
									}
								});
						serviceChantier.findChantierById(Integer.parseInt(currentChantierId), new AsyncCallbackWithErrorResolution<ChantierModel>() {
							@Override
							public void onSuccess(ChantierModel arg0) {
								navigation.getContext().setCurrentChantier(arg0);

								GuiUtil.gotoEcran(new SyntheseEcran());
							}
						});

					} else if (CookieUtil.getCurrentTransfertpp() != null
							&& History.getToken().equalsIgnoreCase(CookieUtil.getCurrentTransfertpp() + "&transfertpp")) {
						navigation.getContext().setTransfertppId(Integer.parseInt(CookieUtil.getCurrentTransfertpp()));
						serviceChantier.findChantierById(Integer.parseInt(currentChantierId), new AsyncCallbackWithErrorResolution<ChantierModel>() {
							@Override
							public void onSuccess(ChantierModel arg0) {
								navigation.getContext().setCurrentChantier(arg0);
								GuiUtil.gotoEcran(new TransfertppEcran());
							}
						});

					} else {
						final String currentFichestId = CookieUtil.getCurrentFichest();
						if (currentFichestId != null && History.getToken().equalsIgnoreCase(currentFichestId + "&fichest")) {
							navigation.getContext().setFichestId(Integer.parseInt(currentFichestId));
							serviceRole.findRoleById(Integer.parseInt(currentChantierId), user.getId(),
									new AsyncCallbackWithErrorResolution<RoleModel>() {
										@Override
										public void onSuccess(RoleModel arg0) {
											navigation.getContext().setRoleModel(arg0);
										}
									});
							serviceChantier.findChantierById(Integer.parseInt(currentChantierId),
									new AsyncCallbackWithErrorResolution<ChantierModel>() {
										@Override
										public void onSuccess(ChantierModel arg0) {
											navigation.getContext().setCurrentChantier(arg0);
											serviceFichest.find(Integer.parseInt(currentFichestId),
													new AsyncCallbackWithErrorResolution<FicheStDto>() {
														@Override
														public void onSuccess(FicheStDto arg0) {
															navigation.getContext().setSocieteName(arg0.getSociete());
															GuiUtil.gotoEcran(new FicheSTEcran());
														}
													});

										}
									});
						} else if (History.getToken().equalsIgnoreCase("acceuil") && user != null) {
							GuiUtil.gotoEcran(new AcceuilEcran());
						} else {
							GuiUtil.gotoEcran(new AcceuilEcran());
						}
					}
				}

			}
		});
	}

	@Override
	public void onModuleLoad() {
		History.addValueChangeHandler(this);
		History.fireCurrentHistoryState();

		loadApplicationContext();
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String rawEvent = event.getValue();
		String realEvent = rawEvent;
		Map<String, String> params = new HashMap<String, String>();
		if (realEvent.contains("&")) {
			realEvent = rawEvent.substring(0, rawEvent.indexOf("&"));
			String paramsString = rawEvent.substring(rawEvent.indexOf("&"));
			for (String string : paramsString.split("&")) {
				String[] text = string.split("=", 2);
				if (text.length == 2) {
					params.put(text[0], text[1]);
				}
			}
		}
		if ("acceuil".equalsIgnoreCase(event.getValue())) {
			// GuiUtil.gotoEcran(new AcceuilEcran());
		}
		if ("admin".equalsIgnoreCase(event.getValue())) {
			// GuiUtil.gotoEcran(new AdminEcran());
		}
		if ("newfichest".equalsIgnoreCase(event.getValue())) {
			// GuiUtil.gotoEcran(new FicheSTEcran());
		}
		if (navigation.getContext().getCurrentChantier() != null
				&& navigation.getContext().getCurrentChantier().getId().toString().equalsIgnoreCase(event.getValue())) {
			// GuiUtil.gotoEcran(new SyntheseEcran());
		}
		if (navigation.getContext().getTransfertppId() != null
				&& navigation.getContext().getTransfertppId().toString().equalsIgnoreCase(event.getValue())) {
			// GuiUtil.gotoEcran(new TransfertppEcran());
		}
		if (navigation.getContext().getFichestId() != null && navigation.getContext().getFichestId().toString().equalsIgnoreCase(event.getValue())) {
			GuiUtil.gotoEcran(new FicheSTEcran());
		}
	}

}
