package jp.co.inte.crm.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.dao.TapproachdtlDao;
import jp.co.inte.crm.web.dto.TapproachdtlDto;
import jp.co.inte.crm.web.dto.TapproachdtlParamDto;
import jp.co.inte.crm.web.service.TapproachdtlService;

import org.apache.log4j.Logger;

public class TapproachdtlServiceImpl implements TapproachdtlService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Resource
    protected TapproachdtlDao tapproachdtlDao;

    @Override
    public TapproachdtlDto getApproachdtlList(String approachid, String cltid) {
        return null;
    }

    @Override
    public List<TapproachdtlDto> getApproachplanhisList(TapproachdtlParamDto tapproachdtlParamDto) {
        // FIXME retrieve data from DB

        List<TapproachdtlDto> results = new ArrayList<TapproachdtlDto>();
        TapproachdtlDto dto = null;
        for (int i = 0; i < 101; i++) {
            dto = new TapproachdtlDto();
            dto.id = "id " + i;
            dto.date = new java.util.Date();
            dto.starttime = new java.util.Date();
            dto.endtime = new java.util.Date();
            dto.cltnm = "ヒマワリ酒造";
            dto.bonm = "○○支店";
            dto.actdtlmemo = "あああああああああああああああああああああああああああああああああああ";
            results.add(dto);
        }
        return results;
    }

    @Override
    public int regist(TapproachdtlDto tapproachdtlDto) {
        return 0;
    }

    @Override
    public int delete(TapproachdtlDto tapproachdtlDto) {
        return 0;
    }
}
