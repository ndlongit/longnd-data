package com.structis.fichesst.server.service.domain;

import java.util.List;

import com.structis.fichesst.server.bean.domain.CautionFournie;

public interface CautionFournieService extends BasicService<CautionFournie, Integer> {
	List<CautionFournie> findByFicheStId(Integer ficheStId);
}
