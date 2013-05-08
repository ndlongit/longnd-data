package com.structis.fichesst.server.service.domain;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Gestion;
import com.structis.fichesst.shared.dto.GestionDto;

public interface GestionService extends BasicService<Gestion, Integer> {

	Object save(List<GestionDto> list, Integer integer);
}
