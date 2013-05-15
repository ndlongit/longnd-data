package com.structis.vip.server.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DocumentType;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("DocTypeDao")
public class DocTypeDaoImpl extends HibernateGenericDao<DocumentType, Integer> implements DocTypeDao {

	public DocTypeDaoImpl() {
		super(DocumentType.class);
	}

	@Override
	@Transactional
	public DocumentType insert(DocumentType doc) {
		this.save(doc);
		return doc;
	}

	@Override
	@Transactional
	public DocumentType update(DocumentType doc) {
		EntityManager em = getEntityManager();
		try {					
			DocumentType jpa = get(doc);		
			if (jpa != null && jpa.getId() != null) {
				DataCopier.copyNotIdFields(doc, jpa);
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

	

	
}
