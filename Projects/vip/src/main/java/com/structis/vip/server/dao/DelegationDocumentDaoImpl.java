package com.structis.vip.server.dao;

import java.io.File;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.DelegationDocument;
import com.structis.vip.server.core.Constants;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;

@Repository("delegationDocumentDao")
public class DelegationDocumentDaoImpl extends HibernateGenericDao<DelegationDocument, Integer> implements
		DelegationDocumentDao {

	private static final Logger LOGGER = Logger.getLogger(DelegationDocumentDaoImpl.class);
	
	public DelegationDocumentDaoImpl() {
		super(DelegationDocument.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DelegationDocument> getDelegationDocuments(Integer delegationId) {
		String sql = " from DelegationDocument d where d.delegation.id = :delegationId ";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("delegationId", delegationId);
		List<DelegationDocument> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public DelegationDocument insert(DelegationDocument delegationDocument) {
		this.save(delegationDocument);
		return delegationDocument;
	}

	@Transactional
	public Boolean deleteByDelId(Integer delId, String path) {
		List<DelegationDocument> list = getDelegationDocuments(delId);

		if (list.isEmpty() == false) {
			File file = null;
			for (DelegationDocument doc : list) {
				file = new File(path + Constants.DELEGATION_DOCUMENT_FILE_PATH + File.separator + doc.getFileName());
				LOGGER.info("DELETE OTHER DOCUMENT FILE PATH: " + file.getAbsolutePath());
				if (file.exists()) {
					LOGGER.info("OTHER DOCUMENT FILE EXISTS");
					file.delete();
				}
				delete(doc);
			}
		}
		return true;
	}
}