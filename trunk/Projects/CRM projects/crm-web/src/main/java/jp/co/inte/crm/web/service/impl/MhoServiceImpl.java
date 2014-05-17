package jp.co.inte.crm.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.dao.MhoDao;
import jp.co.inte.crm.common.entity.Mho;
import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.web.dto.MhoDto;
import jp.co.inte.crm.web.dto.MhoParamDto;
import jp.co.inte.crm.web.service.MhoService;

import org.seasar.framework.beans.util.Beans;

public class MhoServiceImpl extends CrmBaseService implements MhoService {

    @Resource
    protected MhoDao mhoDao;

    @Override
    public MhoDto findById(String hoid) {
        MhoDto mhoDto = new MhoDto();
        Mho entity = mhoDao.findById(hoid);
        Beans.copy(entity, mhoDto).dateConverter("yyyy-MM-dd").execute();
        return mhoDto;
    }

    @Override
    public List<MhoDto> getCorpList(MhoParamDto mhodtoParam) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public int regist(Mho mho) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }
}