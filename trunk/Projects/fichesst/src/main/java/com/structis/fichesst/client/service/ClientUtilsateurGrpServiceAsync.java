package com.structis.fichesst.client.service;

import java.util.List;

import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientUtilsateurGrpServiceAsync {
	void findAll(AsyncCallback<List<UtilisateurGrpModel>> callback);
	void findUserAdmin(AsyncCallback<List<UtilisateurGrpModel>> callback);
	void saveUser(UtilisateurGrpModel user, AsyncCallback<UtilisateurGrpModel> callback);
	void saveListUser(AsyncCallback<List<UtilisateurGrpModel>> callback);
	void findAllUserByChantier(Integer idChantier, AsyncCallback<List<UtilisateurGrpModel>> callback);
	void checkUserByKerberosSSO(AsyncCallback<UtilisateurGrpModel> callback) ;
	void checkIdentifiant(String identifiant, AsyncCallback<Boolean> callback);
	void updateListUser(List<UtilisateurGrpModel> user,List<UtilisateurGrpModel> userDelete,List<UtilisateurGrpModel> userModel,
			List<UtilisateurGrpModel> usersDelete, Integer idChantier, AsyncCallback<Void> callback);
}
