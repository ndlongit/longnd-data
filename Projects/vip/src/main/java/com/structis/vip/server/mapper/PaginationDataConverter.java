package com.structis.vip.server.mapper;

import org.apache.log4j.Logger;
import org.dozer.CustomConverter;

public class PaginationDataConverter implements CustomConverter {
	
	private static Logger logger = Logger.getLogger(PaginationDataConverter.class);
	private ModelBeanMapperIfc mapper;

	public void setMapper(ModelBeanMapperIfc mapper) {
		this.mapper = mapper;
	}

	/**
	 * Mapping pour la pagination, la liste ne peut pas etre typ� (il est en ModelData)
	 */
	public Object convert(Object arg0, Object arg1, Class<?> arg2, Class<?> arg3) {
		if (logger.isDebugEnabled()) logger.debug("Custom converter a : " + arg2  + ", b: " + arg3);
		if (null != arg0) {
			return mapper.map(arg0);
		}
		else {
			return mapper.map(arg1);
		}
	}

}
