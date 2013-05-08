package com.structis.fichesst.server.dao;

import java.util.List;

import com.structis.fichesst.server.bean.domain.CautionFournie;

public interface CautionFournieDao extends BasicDao<CautionFournie, Integer> {

	List<CautionFournie> findByFicheStId(Integer ficheStId);

}
