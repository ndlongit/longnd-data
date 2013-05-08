package com.structis.fichesst.server.service.domain;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;

public interface DomRoleService extends BasicService<Role, Rolepk> {
	List<Role> findRoleById(Integer idChantier, Integer idUser);

	public void createRole(Integer idChantier, Integer idUser,
			Boolean bcontributeur, Boolean blecteur);
	List<Role> findRolesByIdUser(Integer idUser);
}
