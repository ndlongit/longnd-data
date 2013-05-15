package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Language;
import com.structis.vip.server.dao.LanguageDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domLanguageService")
public class DomLanguageServiceImpl extends GenericEntityServiceImpl<Language, Integer>
		implements DomLanguageService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger
			.getLogger(DomLanguageServiceImpl.class);

	@Autowired
	@Qualifier("languageDao")
	private LanguageDao languageDao;
	@Override
	public List<Language> getLanguages() {
		return this.find();
	}

	@Override
	public Language findById(Integer languageId) {
		Language l = this.getByPrimaryKey(languageId);
		return l;
	}

	@Override
	public GenericDao<Language, Integer> getDao() {
		return languageDao;
	}

	@Override
	public Language getNew() {
		return new Language();
	}

	@Override
	public Language getNewWithDefaults() {
		return new Language();
	}

	@Override
	public Language insert(Language doc) {
		return languageDao.insert(doc);
	}

	@Override
	public Language update(Language doc) {
		return languageDao.update(doc);
	}

	@Override
	public Language getDefaultLanguage() {
		return languageDao.getDefaultLanguage();
	}

}
