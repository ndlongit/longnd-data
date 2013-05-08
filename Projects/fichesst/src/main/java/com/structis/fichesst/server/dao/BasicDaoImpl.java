package com.structis.fichesst.server.dao;

import java.io.Serializable;

import com.structis.fichesst.server.bean.domain.core.BasicEntity;
import com.structis.fichesst.server.dao.jpa.GenericJpaDao;

public class BasicDaoImpl<T extends BasicEntity<?>, ID extends Serializable> extends GenericJpaDao<T, ID> implements
		BasicDao<T, ID> {
}
