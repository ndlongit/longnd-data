/**
 * 
 */
package jp.co.inte.crm.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.dao.McltDao;
import jp.co.inte.crm.common.entity.Mclt;
import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.web.dto.McltDto;
import jp.co.inte.crm.web.dto.McltParamDto;
import jp.co.inte.crm.web.dto.McltPlanDto;
import jp.co.inte.crm.web.dto.McltSalesDto;
import jp.co.inte.crm.web.service.McltService;

import org.seasar.framework.beans.util.Beans;

/**
 * @since
 * 
 * @author dinh.le.giang
 * 
 */
public class McltServiceImpl extends CrmBaseService implements McltService {

    @Resource
    protected McltDao mcltDao;

    /**
     * 顧客詳細情報
     * 顧客詳細情報を返す
     * 
     * @param cltcd 顧客CD
     * @return McltDto 顧客マスタ情報
     */
    @Override
    public McltDto findById(String cltid) {
        McltDto mcltDto = new McltDto();
        Mclt entity = mcltDao.findById(cltid);
        if (entity != null) {
            Beans.copy(entity, mcltDto).dateConverter("yyyy-MM-dd").execute();
        }

        return mcltDto;
    }

    /**
     * 顧客基本情報一覧
     * 顧客の基本情報一覧を返す
     * 
     * @param mcltParamDto 検索条件
     * @return List<McltDto> 顧客マスタ一覧
     */
    @Override
    public List<McltDto> getCltList(McltParamDto mcltParamDto) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /**
     * 顧客基本情報一覧
     * 顧客の基本情報一覧を返す
     * 
     * @param hoid 法人ID
     * @return List<McltDto> 顧客マスタ一覧
     */
    @Override
    public List<McltDto> getCltHolList(String hoid) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /**
     * 顧客基本情報一覧
     * 顧客の基本情報を返す
     * 
     * @param cltid 顧客ID
     * @return McltDto 顧客マスタ
     */
    @Override
    public McltDto getClt(String cltid) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /**
     * 顧客売上実績一覧
     * 顧客月別自社、競合の売上一覧
     * 
     * @param mcltParamDto 検索条件
     * @return McltDto 顧客マスタ一覧
     */
    @Override
    public List<McltSalesDto> getSalesList(McltParamDto mcltParamDto) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /**
     * 顧客一覧（月個社計画）
     * 月個社計画一覧を返す
     * 
     * @param mcltParamDto 検索条件
     * @return List<McltDto> 月個社計画一覧
     */
    @Override
    public List<McltDto> getMonthPlanList(McltParamDto mcltParamDto) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /**
     * 顧客一覧（Q個社計画）
     * Q個社計画一覧を返す
     * 
     * @param mcltParamDto 検索条件
     * @return List<McltPlanDto> Q個社計画一覧
     */
    @Override
    public List<McltPlanDto> getQutPlanList(McltParamDto mcltParamDto) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /**
     * 顧客基本情報登録
     * 顧客の基本情報を更新する。
     * 
     * @param mcltDto 顧客基本情報
     */
    @Override
    public int regist(McltDto mcltDto) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

}
