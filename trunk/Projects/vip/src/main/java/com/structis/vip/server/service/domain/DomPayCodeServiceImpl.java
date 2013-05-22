package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.PayCode;
import com.structis.vip.server.dao.PayCodeDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domPayCodeService")
public class DomPayCodeServiceImpl extends GenericEntityServiceImpl<PayCode, Integer> implements DomPayCodeService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomPayCodeServiceImpl.class);

    @Autowired
    @Qualifier("payCodeDao")
    private PayCodeDao payCodeDao;

    @Override
    public GenericDao<PayCode, Integer> getDao() {
        return this.payCodeDao;
    }

    @Override
    public PayCode getNew() {
        return new PayCode();
    }

    @Override
    public PayCode getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public PayCode insert(PayCode bean) {
        return this.payCodeDao.insert(bean);
    }

    @Override
    public PayCode update(PayCode bean) {
        return this.payCodeDao.update(bean);
    }

    @Override
    public List<PayCode> findAll() {
        return this.find();
    }

    @Override
    public PayCode findByCode(String code) {
        return this.payCodeDao.findByCode(code);
    }
}
