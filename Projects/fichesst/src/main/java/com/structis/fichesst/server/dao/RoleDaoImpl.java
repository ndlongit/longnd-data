package com.structis.fichesst.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.bean.domain.FicheTransfertpp;
import com.structis.fichesst.server.bean.domain.FicheTransfertppPk;
import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;

@Repository("roleDao")
public class RoleDaoImpl extends BasicDaoImpl<Role, Rolepk> implements RoleDao {

	@Override
	public List<Role> findRoleById(Integer idChantier, Integer idUser) {
		TypedQuery<Role> query = entityManager
				.createQuery(
						"Select r from Role r where r.id.idChantier=:idChantier and r.id.idUtilisateurGrp=:idUser",
						Role.class);
		query.setParameter("idChantier", idChantier);
		query.setParameter("idUser", idUser);
		List<Role> result = query.getResultList();
		return result;
	}

	@Override
	public void createRoleRelation(Integer idChantier, Integer idUser,Boolean bcontributeur,Boolean blecteur) {
		Role role=new Role();
		Rolepk pk=new Rolepk();
		pk.setIdChantier(idChantier);
		pk.setIdUtilisateurGrp(idUser);
		
		role.setId(pk);
		role.setBcontributeur(bcontributeur);
		role.setBlecteur(blecteur);
		entityManager.persist(role);
	}

	@Override
	public void deleteRoleById(Integer idChantier, Integer idUser) {
		Rolepk pk=new Rolepk();
		pk.setIdChantier(idChantier);
		pk.setIdUtilisateurGrp(idUser);
		Role r=new Role();
		r.setId(pk);
		entityManager.remove(entityManager.merge(r));
	}

	@Override
	public List<Role> findRolesByIdUser(Integer idUser) {
		TypedQuery<Role> query = entityManager
				.createQuery(
						"Select r from Role r where r.id.idUtilisateurGrp=:idUser",
						Role.class);
		query.setParameter("idUser", idUser);
		List<Role> result = query.getResultList();
		return result;
	}




}
