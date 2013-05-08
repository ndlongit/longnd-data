package com.structis.fichesst.server.service.domain;

import java.util.List;

import com.structis.fichesst.server.bean.domain.UtilisateurGrp;

public interface DomUtilisateurService extends BasicService<UtilisateurGrp, Integer> {
	List<UtilisateurGrp> findUserByRole(Boolean role);

	List<UtilisateurGrp> findUserAdmin();
	UtilisateurGrp saveUser(UtilisateurGrp user);
	List<UtilisateurGrp> saveListUser(List<UtilisateurGrp> users);
	UtilisateurGrp findUserByIdentifiant(String identifiant);
	void updateUser(UtilisateurGrp user,Boolean isAdmin);
	List<UtilisateurGrp> findUserByChantier(Integer idChantier);
	public void deleteUserByChantier(Integer idUser, Integer idChantier);
	public void deleteUser();
}
