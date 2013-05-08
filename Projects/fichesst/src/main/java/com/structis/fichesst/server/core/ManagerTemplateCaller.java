package com.structis.fichesst.server.core;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.mapper.ExceptionMapper;
import com.structis.fichesst.server.mapper.ModelBeanMapperIfc;

@Transactional
public class ManagerTemplateCaller implements ManagerTemplateIfc {

	protected static Logger logger = Logger.getLogger(ManagerTemplateCaller.class);

	private ModelBeanMapperIfc modelBeanMapper;

	public void setModelBeanMapper(ModelBeanMapperIfc modelBeanMapper) {
		this.modelBeanMapper = modelBeanMapper;
	}

	/**
	 * M�thode qui fait : mapping input, appel au manager, mapping output
	 * 
	 * @param callBack l'appel de manager
	 * @param param les parametres des appel de manager avant le mapping
	 * @return r�sultat de l'appel de manager mapp�
	 * @throws Throwable
	 */
	public Object callManager(ManagerCallBack callBack, Object... param) {
		Object result = null;

		try {
			// Start
			Object resultTemp = null;
			if( logger.isDebugEnabled() ) {
				logger.debug("Start call manager");
				logger.debug("Params         : " + param);
			}

			// Mapping input
			Object[] mappedParam = new Object[param.length];
			for( int i = 0 ; i < param.length ; i++ ) {
				mappedParam[i] = modelBeanMapper.map(param[i]);
			}
			if( logger.isDebugEnabled() ) {
				logger.debug("Params mapped : " + mappedParam);
			}

			// Appel le manager
			resultTemp = callBack.execute(mappedParam);

			// Mapping output
			result = modelBeanMapper.map(resultTemp);
			if( logger.isDebugEnabled() ) {
				logger.debug("Result mapped : " + result);
			}
		}
		catch( Throwable ex ) {
			ex.printStackTrace();
			throw ExceptionMapper.map(ex);
		}

		return result;
	}
}
