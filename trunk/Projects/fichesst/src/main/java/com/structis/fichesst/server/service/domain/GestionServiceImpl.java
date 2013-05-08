package com.structis.fichesst.server.service.domain;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Gestion;
import com.structis.fichesst.server.dao.GestionDao;
import com.structis.fichesst.server.service.domain.GestionService;
import com.structis.fichesst.server.service.domain.BasicServiceImpl;
import com.structis.fichesst.shared.dto.GestionDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GestionServiceImpl extends BasicServiceImpl<Gestion, Integer, GestionDao> implements GestionService {

	@Override
	public Object save(List<GestionDto> list, Integer integer) {
		return null;
	}
	
//	@Override
	@Transactional(readOnly = true)
	public List<Gestion> getGestionByListId(List<Integer> ids) {
		List<Gestion> listGestion = dao.getGestionByListId(ids);
		return listGestion;

	}
}
