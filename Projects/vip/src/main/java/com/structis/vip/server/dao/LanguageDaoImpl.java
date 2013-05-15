package com.structis.vip.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Language;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("languageDao")
public class LanguageDaoImpl extends HibernateGenericDao<Language, Integer> implements LanguageDao {

	public LanguageDaoImpl() {
		super(Language.class);
	}

	@Transactional
	public Language insert(Language doc) {
		this.save(doc);
		return doc;
	}

	@Transactional
	public Language update(Language dl) {
		EntityManager em = getEntityManager();
		try {					
			Language jpa = get(dl);		
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
	public Language getDefaultLanguage() {
		String sql = " from Language l where l.isDefault = 1 ";

		Query query = getEntityManager().createQuery(sql);
		List<Language> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
}
