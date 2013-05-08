package com.structis.fichesst.server.mapper;

import org.apache.log4j.Logger;
import org.dozer.DozerEventListener;
import org.dozer.event.DozerEvent;

import com.structis.fichesst.server.bean.core.Activable;
import com.structis.fichesst.shared.dto.ModelActivable;

public class MappingActivableListener implements DozerEventListener {

	private static Logger logger = Logger.getLogger(MappingActivableListener.class);

	private DateMapperIfc dateMapper;

	public void setDateMapper(DateMapperIfc dateHelper) {
		this.dateMapper = dateHelper;
	}

	public void preWritingDestinationValue(DozerEvent event) {
	}

	public void postWritingDestinationValue(DozerEvent event) {
		Object x = event.getSourceObject();
		Object y = event.getDestinationObject();
		if( logger.isDebugEnabled() )
			logger.debug("Before Mapping Activable " + x + " => " + y);

		if( null != x && null != y ) {

			// Mapping Server Bean => Client Model
			if( x instanceof Activable && y instanceof ModelActivable ) {
				Activable activable = (Activable) x;
				ModelActivable modelActivable = (ModelActivable) y;
				if( null == activable.getDateSuppr() ) {
					// S'il n'a pas de dateSuppr => active
					modelActivable.setActive(true);
					modelActivable.setDateSuppr(null);
				}
				else {
					// Si non �tat inactive
					modelActivable.setActive(false);
					modelActivable.setDateSuppr(activable.getDateSuppr());
				}

			}

			// Mapping Client Model => Server Bean
			if( y instanceof Activable && x instanceof ModelActivable ) {
				Activable activable = (Activable) y;
				ModelActivable modelActivable = (ModelActivable) x;
				if( modelActivable.getActive() ) {
					// S'il est active => DateSuppr est null
					activable.setDateSuppr(null);
				}
				else {
					// Sinon
					if( null == modelActivable.getDateSuppr() ) {
						// Nouvelle activation + New Date
						activable.setDateSuppr(dateMapper.getActualDate());
					}
					else {
						// Old Date
						activable.setDateSuppr(modelActivable.getDateSuppr());
					}
				}
			}

			if( logger.isDebugEnabled() )
				logger.debug("After Mapping Activable " + x + " => " + y);
		}
	}

	public void mappingStarted(DozerEvent event) {
	}

	public void mappingFinished(DozerEvent event) {

	}

}
