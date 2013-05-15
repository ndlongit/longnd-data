package com.structis.vip.server.dao;

import com.structis.vip.server.bean.domain.Language;
import com.structis.vip.server.dao.support.GenericDao;

public interface LanguageDao extends GenericDao<Language, Integer> {

	Language insert(Language doc);

	Language update(Language doc);

	Language getDefaultLanguage();

}
