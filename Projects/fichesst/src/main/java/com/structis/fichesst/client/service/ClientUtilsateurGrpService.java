package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

@RemoteServiceRelativePath("springGwtServices/clientUtilisateurGrpService")
public interface ClientUtilsateurGrpService extends RemoteService {
	List<UtilisateurGrpModel> findAll();
	List<UtilisateurGrpModel> findUserAdmin();
	UtilisateurGrpModel saveUser(UtilisateurGrpModel user);
	List<UtilisateurGrpModel> saveListUser();
	List<UtilisateurGrpModel> findAllUserByChantier(Integer idChantier);
	UtilisateurGrpModel checkUserByKerberosSSO() ;
	boolean checkIdentifiant(String identifiant);
	void updateListUser(List<UtilisateurGrpModel> user,List<UtilisateurGrpModel> userDelete,List<UtilisateurGrpModel> userModel,
			List<UtilisateurGrpModel> usersDelete, Integer idChantier);
}
