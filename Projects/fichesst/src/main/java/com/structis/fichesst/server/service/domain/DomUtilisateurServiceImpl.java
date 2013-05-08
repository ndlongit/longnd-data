package com.structis.fichesst.server.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;
import com.structis.fichesst.server.bean.domain.UtilisateurGrp;
import com.structis.fichesst.server.dao.RoleDao;
import com.structis.fichesst.server.dao.UtilisateurGrpDao;
import com.structis.fichesst.shared.dto.SimpleDto;
import com.structis.fichesst.shared.exception.DataConstraintException;

@Service("domUtilisateurService")
public class DomUtilisateurServiceImpl extends BasicServiceImpl<UtilisateurGrp, Integer, UtilisateurGrpDao> implements
		DomUtilisateurService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public void deleteUser() {

		boolean flag = false;
		List<UtilisateurGrp> users = dao.findAll();
		for( UtilisateurGrp utilisateurGrp : users ) {

			List<Role> rolesUser = roleDao.findRolesByIdUser(utilisateurGrp.getId());
			if( rolesUser.size() > 0 ) {
				for( Role role2 : rolesUser ) {
					if( (role2.getBcontributeur() == null || (role2.getBcontributeur() != null && role2.getBcontributeur() == false)) && (role2.getBlecteur() == null || (role2.getBlecteur() != null && role2.getBlecteur() == false)) && utilisateurGrp.getBadmin() == null ) {
						flag = true;
					}
					else {
						flag = false;
						break;
					}
				}
				if( flag == true ) {
					List<Role> rolesUser2 = utilisateurGrp.getRoles();
					if( rolesUser2.size() > 0 ) {
						for( Role role3 : rolesUser2 ) {
							role3.setId(new Rolepk(utilisateurGrp.getId(), role3.getChantier().getId()));
							role3.setChantier(null);
							role3.setUtilisateurGrp(null);

							roleDao.delete(role3);
						}
					}
					dao.delete(utilisateurGrp);
				}
			}
			else {
				if( utilisateurGrp.getBadmin() == null ) {
					dao.delete(utilisateurGrp);
				}
			}
		}

	}

	@Override
	public void deleteUserByChantier(Integer idUser, Integer idChantier) {
		dao.deleteUserByChantier(idUser, idChantier);
	}

	@Override
	public List<UtilisateurGrp> findAll() {
		return super.findAll();
	}

	List<SimpleDto> findAllModel() {
		return null;
	}

	@Override
	public List<UtilisateurGrp> findUserAdmin() {
		List<UtilisateurGrp> listUser = dao.findUserAdmin();
		return listUser;
	}

	@Override
	public List<UtilisateurGrp> findUserByChantier(Integer idChantier) {

		return dao.findUserByChantier(idChantier);
	}

	@Override
	public UtilisateurGrp findUserByIdentifiant(String identifiant) {
		return super.findUniqueByProperty(UtilisateurGrp.PROP_IDENTIFIANT, identifiant);
	}

	@Override
	public List<UtilisateurGrp> findUserByRole(Boolean role) {
		return null;
	}

	@Override
	public List<UtilisateurGrp> saveListUser(List<UtilisateurGrp> users) {

		for( UtilisateurGrp utilisateurGrp : users ) {
			try {
				save(utilisateurGrp);
			}
			catch( DataConstraintException e ) {
				e.printStackTrace();
			}
			catch( Exception e ) {
				e.printStackTrace();
			}
		}
		return users;

	}

	@Override
	public UtilisateurGrp saveUser(UtilisateurGrp user) {
		try {
			this.save(user);
		}
		catch( DataConstraintException e ) {
			e.printStackTrace();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(UtilisateurGrp user, Boolean isAdmin) {
		dao.updateUser(user, isAdmin);
	}

}
