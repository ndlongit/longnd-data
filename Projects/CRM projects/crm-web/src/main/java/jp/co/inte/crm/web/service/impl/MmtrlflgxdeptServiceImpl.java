package jp.co.inte.crm.web.service.impl;

import javax.annotation.Resource;

import jp.co.inte.crm.common.dao.MmtrlflgxdeptDao;
import jp.co.inte.crm.common.entity.Mmtrlflgxdept;
import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.web.service.MmtrlflgxdeptService;

public class MmtrlflgxdeptServiceImpl extends CrmBaseService implements MmtrlflgxdeptService {

    @Resource
    protected MmtrlflgxdeptDao mmtrlflgxdeptDao;

    @Override
    public Mmtrlflgxdept findAll() {
        // TODO Auto-generated method stub
        return mmtrlflgxdeptDao.findFisrt();
    }
}