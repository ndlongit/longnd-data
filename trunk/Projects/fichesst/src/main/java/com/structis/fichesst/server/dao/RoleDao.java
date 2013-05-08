package com.structis.fichesst.server.dao;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;
import com.structis.fichesst.server.bean.domain.UtilisateurGrp;

public interface RoleDao  extends BasicDao<Role, Rolepk>{
	List<Role> findRoleById(Integer idChantier,Integer idUser);
	public void createRoleRelation(Integer idChantier, Integer idUser,Boolean bcontributeur,Boolean blecteur);
	public void deleteRoleById(Integer idChantier,Integer idUser);
	List<Role> findRolesByIdUser(Integer idUser);
}
