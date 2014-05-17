/**
 * 
 */
package jp.co.inte.crm.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.dao.TslmtrlDao;
import jp.co.inte.crm.common.entity.Tslmtrl;
import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.web.dto.TslmtrlDto;
import jp.co.inte.crm.web.dto.TslmtrlParamDto;
import jp.co.inte.crm.web.service.TslmtrlService;

import org.seasar.framework.beans.util.Beans;

/**
 * @since
 * 
 * @author dinh.le.giang
 */
public class TslmtrlServiceImpl extends CrmBaseService implements TslmtrlService {
    @Resource
    protected TslmtrlDao tslmtrlDao;

    @Override
    public TslmtrlDto findById(String hoid) {
        TslmtrlDto tslmtrlDto = new TslmtrlDto();
        Tslmtrl entity = tslmtrlDao.findById(hoid);
        if (entity != null) {
            Beans.copy(entity, tslmtrlDto).dateConverter("yyyy-MM-dd").execute();
        }

        return tslmtrlDto;
    }

    @Override
    public List<TslmtrlDto> getTodayTslmtrlList(String loginid, Date date) {
        List<TslmtrlDto> tslmtrlDtoList = new ArrayList<TslmtrlDto>();

        return tslmtrlDtoList;
    }

    @Override
    public List<TslmtrlDto> getTodayTslmtrlList(int ctrno, Date date) {
        List<TslmtrlDto> tslmtrlDtoList = new ArrayList<TslmtrlDto>();

        return tslmtrlDtoList;

    }

    @Override
    public List<TslmtrlDto> getTslmtrlList(TslmtrlParamDto tslmtrlParamDto) {
        List<TslmtrlDto> tslmtrlDtoList = new ArrayList<TslmtrlDto>();
        List<Tslmtrl> tslmtrlList = tslmtrlDao.findAll();
        for (Tslmtrl tslmtrl : tslmtrlList) {
            TslmtrlDto tslmtrlDto = new TslmtrlDto();
            Beans.copy(tslmtrl, tslmtrlDto).dateConverter("yyyy-MM-dd").execute();
            tslmtrlDtoList.add(tslmtrlDto);
        }
        return tslmtrlDtoList;

    }

    @Override
    public int regist(TslmtrlDto tslmaterialDto) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

    @Override
    public int delete(Tslmtrl tslmtrl) {
        return tslmtrlDao.deleteSaleMaterial(tslmtrl);
    }
}
