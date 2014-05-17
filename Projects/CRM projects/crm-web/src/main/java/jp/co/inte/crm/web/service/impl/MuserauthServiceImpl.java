package jp.co.inte.crm.web.service.impl;

import javax.annotation.Resource;

import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.web.dto.MuserauthDto;
import jp.co.inte.crm.web.service.MuserauthService;

public class MuserauthServiceImpl extends CrmBaseService implements MuserauthService {

    @Resource
    // protected MuserauthDao muserauthDao;
    /** date format */
    private final String DATE_FORMAT = "yyyy/MM/dd";

    @Override
    public MuserauthDto findById(String screenid) {
        // MmtrlflgDto mmtrlflgDto = new MmtrlflgDto();
        // Mmtrlflg entity = mmtrlflgDao.findById(flgno);
        // Beans.copy(entity, mmtrlflgDto).dateConverter(DATE_FORMAT).execute();
        // return mmtrlflgDto;
        return null;
    }
}