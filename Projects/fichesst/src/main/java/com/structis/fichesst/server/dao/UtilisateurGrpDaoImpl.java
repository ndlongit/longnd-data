package com.structis.fichesst.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;
import com.structis.fichesst.server.bean.domain.UtilisateurGrp;

@Repository("utilisateurGrpDao")
public class UtilisateurGrpDaoImpl extends
		BasicDaoImpl<UtilisateurGrp, Integer> implements UtilisateurGrpDao {

	@Override
	public List<UtilisateurGrp> findUserAdmin() {
		TypedQuery<UtilisateurGrp> query = entityManager.createQuery(
				"Select u from UtilisateurGrp u where u.badmin=:badmin",
				UtilisateurGrp.class);
		query.setParameter("badmin", true);
		List<UtilisateurGrp> results = query.getResultList();
		return results;

	}
	@Override
	public List<UtilisateurGrp> findUserByChantier(Integer idChantier) {

		TypedQuery<UtilisateurGrp> query = entityManager
				.createQuery(
						"SELECT u FROM UtilisateurGrp u left join u.roles r  where (r.id.idUtilisateurGrp=u.id) and (r.id.idChantier=:idchantier)  ",
						UtilisateurGrp.class);
		query.setParameter("idchantier", idChantier);
		List<UtilisateurGrp> results = query.getResultList();
		return results;
	}
	@Override
	public void deleteUserByChantier(Integer idUser, Integer idChantier) {
		try {
	
			UtilisateurGrp user;
			try {
				user = entityManager.getReference(UtilisateurGrp.class, idUser);
				List<Role> roleList = user.getRoles();
				for (Role role : roleList) {
					entityManager.remove(entityManager.merge(role));
				}

			} catch (Exception e) {
			}

		} catch (Exception e) {
		}
	}

	@Override
	public UtilisateurGrp findUserByIdentifiant(String identifiant) {
		TypedQuery<UtilisateurGrp> query = entityManager
				.createQuery(
						"Select u from UtilisateurGrp u where u.identifiant=:identifiant",
						UtilisateurGrp.class);
		query.setParameter("identifiant", identifiant);
		List<UtilisateurGrp> result = query.getResultList();
		return result.get(0);
	}

	@Override
	public void updateUser(UtilisateurGrp user,Boolean isAdmin) {
		UtilisateurGrp persitentUser=entityManager.find(UtilisateurGrp.class,user.getId());
		user.setBadmin(isAdmin);
		user.setIdentifiant(persitentUser.getIdentifiant());
		try {
			entityManager.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
