package com.structis.fichesst.server.dao;

import java.util.List;

import com.structis.fichesst.server.bean.domain.UtilisateurGrp;

public interface UtilisateurGrpDao extends BasicDao<UtilisateurGrp, Integer>{
	List<UtilisateurGrp> findUserAdmin();
	List<UtilisateurGrp> findUserByChantier(Integer idChantier);
	UtilisateurGrp	findUserByIdentifiant(String identifiant);
	void deleteUserByChantier(Integer idUser,Integer idChantier);
	void updateUser(UtilisateurGrp user,Boolean isAdmin);
}
