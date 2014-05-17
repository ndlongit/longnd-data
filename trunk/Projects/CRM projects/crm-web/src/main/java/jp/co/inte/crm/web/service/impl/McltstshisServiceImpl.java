/**
 * 
 */
package jp.co.inte.crm.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.dao.McltstshisDao;
import jp.co.inte.crm.common.entity.Mcltstshis;
import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.web.dto.McltstshisDto;
import jp.co.inte.crm.web.service.McltstshisService;

import org.seasar.framework.beans.util.Beans;

/**
 * @since
 * @author dinh.le.giang
 */
public class McltstshisServiceImpl extends CrmBaseService implements McltstshisService {

    @Resource
    protected McltstshisDao mcltstshisDao;

    /**
     * 顧客ステータス一覧
     * 顧客ステータス履歴を返す
     * 
     * @param cltcd 顧客CD
     * @return McltstshisDto 顧客ステータス一覧
     */
    @Override
    public McltstshisDto findById(String cltcd, BigDecimal historyid) {
        McltstshisDto mcltstshisDto = new McltstshisDto();
        Mcltstshis mcltstshis = mcltstshisDao.findById(cltcd, historyid);
        if (mcltstshis != null) {
            Beans.copy(mcltstshis, mcltstshisDto).dateConverter("yyyy-MM-dd").execute();
        }
        return mcltstshisDto;

    }

    /**
     * 顧客ステータス一覧
     * 顧客ステータス履歴を返す
     * 
     * @param cltstsidDto 顧客ステータスID
     * @return List<McltstshisDto> 顧客ステータス一覧
     */
    @Override
    public List<McltstshisDto> getCltStatusList(int cltstsidDto) {

        List<McltstshisDto> mcltstshisDtoList = new ArrayList<McltstshisDto>();

        return mcltstshisDtoList;

    }

    /**
     * 顧客ステータス登録
     * 顧客のステータスを更新する。
     * 
     * @param mcltstshisDto 顧客ステータス
     * @return 更新した行数
     */
    @Override
    public int regist(McltstshisDto mcltstshisDto) {

        return 0;
    }
}
