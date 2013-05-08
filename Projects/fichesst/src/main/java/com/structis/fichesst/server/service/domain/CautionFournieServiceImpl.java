package com.structis.fichesst.server.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.fichesst.server.bean.domain.CautionFournie;
import com.structis.fichesst.server.dao.CautionFournieDao;

@Service("cautionFournieService")
public class CautionFournieServiceImpl extends BasicServiceImpl<CautionFournie, Integer, CautionFournieDao> implements
		CautionFournieService {
	
	@Autowired
	private CautionFournieDao cautionFournieDao;
	
	@Override
    public List<CautionFournie> findByFicheStId(Integer ficheStId) {
		List<CautionFournie> results = cautionFournieDao.findByFicheStId(ficheStId);		
		return results; 
    }
	
}
