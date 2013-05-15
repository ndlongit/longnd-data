package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.bean.domain.DomDel;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("documentModelDao")
public class DocumentMdlDaoImpl extends HibernateGenericDao<DocumentMdl, Integer> implements DocumentMdlDao {

	public DocumentMdlDaoImpl() {
		super(DocumentMdl.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentMdl> getDocumentModelsByLanguage(Integer lgId) {
		String sql = " from DocumentMdl d where d.language.id = :languageId " +
		"order by d.filename";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("languageId", lgId);
		List<DocumentMdl> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentMdl> getDocumentModelsByLanguageAndType(Integer lgId,
			String type) {
		String sql = " from DocumentMdl d where d.language.id = :languageId " +
		" and d.type = :type order by d.filename";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("type", type);
		query.setParameter("languageId", lgId);
		List<DocumentMdl> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<DocumentMdl> getAllDocumentModels() {
		String sql = " from DocumentMdl d order by d.filename";
		Query query = getEntityManager().createQuery(sql);		
		List<DocumentMdl> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<DomDel> getDocumentsByDelegation(Integer delegationId) {		
		String sql = " from DomDel dd where dd.delegation.id = :delegationId " +
		" order by dd.documentMdl.filename";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("delegationId", delegationId);
		List<DomDel> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<DomDel> getDocumentsByDelegationAndLanguage(
			Integer delegationId, Integer languageId) {
		String sql = " from DomDel dd where dd.delegation.id = :delegationId " +
		" and dd.documentMdl.language.id = :languageId " +
		" order by dd.documentMdl.filename";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("delegationId", delegationId);
		query.setParameter("languageId", languageId);
		List<DomDel> resultList = query.getResultList();
		return resultList;

	}

	@Override
	public List<DomDel> getDocumentsByDelegationLanguageAndType(
			Integer delegationId, Integer languageId, String type) {
		String sql = "select dd.documentMdl from DomDel dd where dd.delegation.id = :delegationId " +
		" and dd.documentMdl.type = :type " +		
		" and dd.documentMdl.language.id = :languageId " +
		" order by dd.documentMdl.filename";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("delegationId", delegationId);
		query.setParameter("type", type);
		query.setParameter("languageId", languageId);
		List<DomDel> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<DocumentMdl> getAllDocumentModelsByEntiteId(String entId) {
		String sql = " from DocumentMdl d where d.entite.id = :entId order by d.filename";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("entId", entId);
		List<DocumentMdl> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public DocumentMdl insert(DocumentMdl model) {
		this.save(model);		
		return model; 
	}

	@Transactional
	public DocumentMdl update(DocumentMdl dl) {
		EntityManager em = getEntityManager();
		try {					
			DocumentMdl jpa = get(dl);		
			if (jpa != null && jpa.getId() != null) {
				DataCopier.copyNotIdFields(dl, jpa);
				em.merge(jpa);
				return jpa;
			}
		} catch (Exception ex) {			
			return null;			
		} finally {
			em.close();
		}
		return null;				
	}

	@Override
	public List<DocumentMdl> findByName(String name, String entId) {
		String sql = " from DocumentMdl d where d.name like :pname and d.entite.id = :entId order by d.filename";

		Query query = getEntityManager().createQuery(sql);
		query.setParameter("pname", name);
		query.setParameter("entId", entId);
		List<DocumentMdl> resultList = query.getResultList();
		return resultList;
	}
}
