package com.structis.fichesst.server.service.domain;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Gestion;

public interface DomGestionService extends BasicService<Gestion, Integer> {
	List<Gestion> getGestionByListId(List<Integer> ids);
}
