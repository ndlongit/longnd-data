package com.structis.fichesst.server.mapper;

import org.dozer.DozerBeanMapper;

public interface ModelBeanMapperIfc {

	public Object map(Object object);

	public Object map(Object object, String mapId);

	public DozerBeanMapper getMapper();
}
