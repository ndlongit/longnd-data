package com.structis.fichesst.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.server.bean.domain.Role;
import com.structis.fichesst.server.bean.domain.Rolepk;
import com.structis.fichesst.server.bean.domain.UtilisateurGrp;

@Repository("chantierDao")
public class ChantierDaoImpl extends BasicDaoImpl<Chantier, Integer> implements
		ChantierDao {
	public List<UtilisateurGrp> findUserByChantier() {
		TypedQuery<UtilisateurGrp> query = entityManager
				.createQuery(
						"select u.id from UtilisateurGrp u left join  u.roles ro left join ro.chantier ch where ch.id=:id",
						UtilisateurGrp.class);
		query.setParameter("id", 7);
		List<UtilisateurGrp> results = query.getResultList();
		return results;
	}
	@Override
	public void deleteChantier(Integer idchantier) {
		Chantier chantier;
		chantier = entityManager.getReference(Chantier.class, idchantier);
		chantier.getId();
		List<Role> roleList = chantier.getRoles();
		if ( roleList.size() == 0) {
			entityManager.remove(chantier);
		} else {
			for (Role role : roleList) {
				Integer idUser = role.getId().getIdUtilisateurGrp();
				Rolepk rolePK = new Rolepk(idUser, chantier.getId());
				role.setId(rolePK);
				entityManager.remove(role);
			}
		}
		entityManager.remove(chantier);
	}
	@Override
	public List<Chantier> findChantierByUser(Integer idUser) {
		TypedQuery<Chantier> query = entityManager
				.createQuery(
						"Select c from Chantier c,Role r "
								+ "where(r.id.idChantier=c.id)AND(r.id.idUtilisateurGrp=:idUser)",
						Chantier.class);
		query.setParameter("idUser", idUser);
		List<Chantier> results = query.getResultList();
		return results;
	}
	@Override
	public Chantier findChantierbyId(Integer idChantier) {
		TypedQuery<Chantier> query = entityManager.createQuery(
				"Select c from Chantier c where c.id=:idChantier",
				Chantier.class);
		query.setParameter("idChantier", idChantier);
		List<Chantier> result = query.getResultList();
		return result.get(0);

	}

}
