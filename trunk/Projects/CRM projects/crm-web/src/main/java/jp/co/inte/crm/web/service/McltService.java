package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.McltDto;
import jp.co.inte.crm.web.dto.McltParamDto;
import jp.co.inte.crm.web.dto.McltPlanDto;
import jp.co.inte.crm.web.dto.McltSalesDto;

/**
 * <pre>
 * mclt(顧客マスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface McltService {

    /**
     * 顧客基本情報一覧
     * 顧客の基本情報一覧を返す
     * 
     * @param mcltParamDto 検索条件
     * @return List<McltDto> 顧客マスタ一覧
     */
    List<McltDto> getCltList(McltParamDto mcltParamDto);

    /**
     * 顧客基本情報一覧
     * 顧客の基本情報一覧を返す
     * 
     * @param hoid 法人ID
     * @return List<McltDto> 顧客マスタ一覧
     */
    List<McltDto> getCltHolList(String hoid);

    /**
     * 顧客基本情報一覧
     * 顧客の基本情報を返す
     * 
     * @param cltid 顧客ID
     * @return McltDto 顧客マスタ
     */
    McltDto getClt(String cltid);

    /**
     * 顧客売上実績一覧
     * 顧客月別自社、競合の売上一覧
     * 
     * @param mcltParamDto 検索条件
     * @return McltDto 顧客マスタ一覧
     */
    List<McltSalesDto> getSalesList(McltParamDto mcltParamDto);

    /**
     * 顧客詳細情報
     * 顧客詳細情報を返す
     * 
     * @param cltcd 顧客CD
     * @return McltDto 顧客マスタ情報
     */
    McltDto findById(String cltcd);

    /**
     * 顧客一覧（月個社計画）
     * 月個社計画一覧を返す
     * 
     * @param mcltParamDto 検索条件
     * @return List<McltDto> 月個社計画一覧
     */
    List<McltDto> getMonthPlanList(McltParamDto mcltParamDto);

    /**
     * 顧客一覧（Q個社計画）
     * Q個社計画一覧を返す
     * 
     * @param mcltParamDto 検索条件
     * @return List<McltPlanDto> Q個社計画一覧
     */
    List<McltPlanDto> getQutPlanList(McltParamDto mcltParamDto);

    /**
     * 顧客基本情報登録
     * 顧客の基本情報を更新する。
     * 
     * @param mcltDto 顧客基本情報
     */
    int regist(McltDto mcltDto);

}
