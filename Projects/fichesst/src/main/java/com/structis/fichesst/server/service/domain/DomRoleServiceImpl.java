package com.structis.fichesst.server.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;
import com.structis.fichesst.server.dao.RoleDao;

@Service("domRoleService")
public class DomRoleServiceImpl extends BasicServiceImpl<Role, Rolepk, RoleDao> implements DomRoleService{
	@Autowired
	RoleDao roleDao;
	@Override
	public List<Role> findRoleById(Integer idChantier, Integer idUser) {
		return roleDao.findRoleById(idChantier, idUser);
	}
	@Override
	public void createRole(Integer idChantier, Integer idUser,
			Boolean bcontributeur, Boolean blecteur) {
		roleDao.createRoleRelation(idChantier, idUser, bcontributeur, blecteur);
	}
	@Override
	public List<Role> findRolesByIdUser(Integer idUser) {
		return roleDao.findRolesByIdUser(idUser);
	}

}
