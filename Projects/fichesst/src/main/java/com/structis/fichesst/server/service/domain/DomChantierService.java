package com.structis.fichesst.server.service.domain;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Chantier;
import com.structis.fichesst.shared.dto.ChantierModel;

public interface DomChantierService extends BasicService<Chantier, Integer> {
	public void deleteChantier(Integer idChantier);
	List<Chantier> findChantierByUser(Integer idUser);
}
