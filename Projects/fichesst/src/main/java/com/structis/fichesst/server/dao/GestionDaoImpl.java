package com.structis.fichesst.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.structis.fichesst.server.bean.domain.Gestion;

@Repository
public class GestionDaoImpl extends BasicDaoImpl<Gestion, Integer> implements GestionDao {
	
	private static String GET_GESTION_BY_LIST_ID = "SELECT g FROM Gestion g WHERE g.id IN (:ids)";
	public List<Gestion> getGestionByListId(List<Integer> ids){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fichesst");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Gestion> query = em.createQuery(GET_GESTION_BY_LIST_ID,Gestion.class);
		query.setParameter("ids", ids);
		List<Gestion> listGestion = query.getResultList();
		return listGestion;
	}
}
