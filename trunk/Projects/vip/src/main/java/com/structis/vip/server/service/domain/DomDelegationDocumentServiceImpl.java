package com.structis.vip.server.service.domain;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.DelegationDocument;
import com.structis.vip.server.core.Constants;
import com.structis.vip.server.dao.DelegationDocumentDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDelegationDocumentService")
public class DomDelegationDocumentServiceImpl extends GenericEntityServiceImpl<DelegationDocument, Integer> implements
		DomDelegationDocumentService {

	private static final Logger LOGGER = Logger.getLogger(DomDelegationDocumentServiceImpl.class);

	@Autowired
	@Qualifier("delegationDocumentDao")
	private DelegationDocumentDao delegationDocumentDao;

	@Override
	public GenericDao<DelegationDocument, Integer> getDao() {
		return delegationDocumentDao;
	}

	@Override
	public DelegationDocument getNew() {
		return new DelegationDocument();
	}

	@Override
	public DelegationDocument getNewWithDefaults() {
		return new DelegationDocument();
	}

	@Override
	public List<DelegationDocument> getDelegationDocuments(Integer delegationId) {
		return delegationDocumentDao.getDelegationDocuments(delegationId);
	}

	@Override
	public DelegationDocument insert(DelegationDocument delegationDocument, String path) {
		File file = new File(path + Constants.DELEGATION_DOCUMENT_FILE_PATH + File.separator +  delegationDocument.getFileName());
		if (file.exists()) {
			LOGGER.info(file.getAbsoluteFile() + " EXIST");
			return delegationDocumentDao.insert(delegationDocument);
		} else {
			LOGGER.info(file.getAbsoluteFile() + " DOES NOT EXIST");
			return null;
		}
	}

	@Override
	public Boolean deleteByDelId(Integer delId, String path) {
		return delegationDocumentDao.deleteByDelId(delId, path);
	}
}