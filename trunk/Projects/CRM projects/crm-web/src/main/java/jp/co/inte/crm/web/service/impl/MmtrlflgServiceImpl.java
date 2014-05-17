package jp.co.inte.crm.web.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import jp.co.inte.crm.common.dao.MmtrlflgDao;
import jp.co.inte.crm.common.entity.Mmtrlflg;
import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.web.dto.MmtrlflgDto;
import jp.co.inte.crm.web.service.MmtrlflgService;

import org.seasar.framework.beans.util.Beans;

public class MmtrlflgServiceImpl extends CrmBaseService implements MmtrlflgService {

    @Resource
    protected MmtrlflgDao mmtrlflgDao;

    /** date format */
    private final String DATE_FORMAT = "yyyy/MM/dd";

    @Override
    public MmtrlflgDto findById(BigDecimal flgno) {
        MmtrlflgDto mmtrlflgDto = new MmtrlflgDto();
        Mmtrlflg entity = mmtrlflgDao.findById(flgno);
        Beans.copy(entity, mmtrlflgDto).dateConverter(DATE_FORMAT).execute();
        return mmtrlflgDto;
    }

    @Override
    public int regist(MmtrlflgDto mmtrlflgDto) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }
}