package com.structis.vip.server.mapper;

import org.apache.log4j.Logger;
import org.dozer.DozerEventListener;
import org.dozer.event.DozerEvent;

import com.structis.vip.server.bean.core.Activable;
import com.structis.vip.shared.model.ModelActivable;

public class MappingActivableListener implements DozerEventListener {

    private static Logger logger = Logger.getLogger(MappingActivableListener.class);
    private DateMapperIfc dateMapper;

    public void setDateMapper(DateMapperIfc dateHelper) {
        this.dateMapper = dateHelper;
    }

    @Override
    public void preWritingDestinationValue(DozerEvent event) {
    }

    @Override
    public void postWritingDestinationValue(DozerEvent event) {
        Object x = event.getSourceObject();
        Object y = event.getDestinationObject();
        if (logger.isDebugEnabled())
            logger.debug("Before Mapping Activable " + x + " => " + y);

        if (null != x && null != y) {

            // Mapping Server Bean => Client Model
            if (x instanceof Activable && y instanceof ModelActivable) {
                Activable activable = (Activable) x;
                ModelActivable modelActivable = (ModelActivable) y;
                if (null == activable.getDateSuppr()) {
                    // S'il n'a pas de dateSuppr => active
                    modelActivable.setActive(true);
                    modelActivable.setDateSuppr(null);
                } else {
                    // Si non �tat inactive
                    modelActivable.setActive(false);
                    modelActivable.setDateSuppr(activable.getDateSuppr());
                }

            }

            // Mapping Client Model => Server Bean
            if (y instanceof Activable && x instanceof ModelActivable) {
                Activable activable = (Activable) y;
                ModelActivable modelActivable = (ModelActivable) x;
                if (modelActivable.getActive()) {
                    // S'il est active => DateSuppr est null
                    activable.setDateSuppr(null);
                } else {
                    // Sinon
                    if (null == modelActivable.getDateSuppr()) {
                        // Nouvelle activation + New Date
                        activable.setDateSuppr(this.dateMapper.getActualDate());
                    } else {
                        // Old Date
                        activable.setDateSuppr(modelActivable.getDateSuppr());
                    }
                }
            }

            if (logger.isDebugEnabled())
                logger.debug("After Mapping Activable " + x + " => " + y);
        }
    }

    @Override
    public void mappingStarted(DozerEvent event) {
    }

    @Override
    public void mappingFinished(DozerEvent event) {

    }

}
