package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.Language;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomLanguageService extends GenericEntityService<Language, Integer> {

    List<Language> getLanguages();

    Language findById(Integer languageId);

    Language insert(Language doc);

    Language update(Language doc);

    Language getDefaultLanguage();

}
