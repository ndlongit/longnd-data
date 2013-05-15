package com.structis.vip.server.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

public class ModelBeanMapperDozerImpl implements ModelBeanMapperIfc {
	
	private static Logger logger = Logger.getLogger(ModelBeanMapperDozerImpl.class);
	
	private DozerBeanMapper mapper;
	private ModelBeanMap map;

	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	public void setModelBeanMap(ModelBeanMap map) {
		this.map = map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object mapList(List xs, String mapId) {
		List ys = null;
		if (null != xs) {
			ys = new ArrayList();
			for (Object x : xs) {
				Object y = map(x, mapId);
				ys.add(y);
			}
		}
		return ys;
	}
	
	public Object map(Object object) {
		Object result = null;
		if (object instanceof MapperId) {
			MapperId mapId = (MapperId) object;
			result = map(mapId.getObject(), mapId.getMapId());
		}
		else {
			result = map(object, null);
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * Mapping object
	 */
	public Object map(Object x, String mapId) {
		Object y = null;
		
		// Si l'objet est null, retourner null
		if (null == x) {
			if (logger.isDebugEnabled()) logger.debug("L'objet entre est null");
			return null;
		}
		
		// Si l'objet est de type liste
		if (x instanceof List) {
			if (logger.isDebugEnabled()) logger.debug("L'objet est une liste");
			return mapList((List) x, mapId);
		}
		
		// S'il n'est pas dans la liste, on retourne objet
		if(null ==  map.get(x)) {
			if (logger.isDebugEnabled()) logger.debug("L''objet n'est pas dans la liste de map");
			return x;
		}
		
		// Si non, fait la mapping
		if (null == mapId) {
			y = mapper.map(x, map.get(x));
		}
		else {
			y = mapper.map(x, map.get(x), mapId);
		}
		if (logger.isDebugEnabled()) logger.debug("Mapping " + x + " => " + y);
		
		return y;
	}


}
