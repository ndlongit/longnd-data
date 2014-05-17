package jp.co.inte.crm.web.service;

import java.math.BigDecimal;
import java.util.List;

import jp.co.inte.crm.web.dto.McltstshisDto;

/**
 * <pre>
 * mcltstshis(顧客ステータス)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface McltstshisService {

    /**
     * 顧客ステータス一覧
     * 顧客ステータス履歴を返す
     * 
     * @param cltcd 顧客CD
     * @return McltstshisDto 顧客ステータス一覧
     */
    McltstshisDto findById(String cltcd, BigDecimal historyid);

    /**
     * 顧客ステータス一覧
     * 顧客ステータス履歴を返す
     * 
     * @param cltstsidDto 顧客ステータスID
     * @return List<McltstshisDto> 顧客ステータス一覧
     */
    List<McltstshisDto> getCltStatusList(int cltstsidDto);

    /**
     * 顧客ステータス登録
     * 顧客のステータスを更新する。
     * 
     * @param mcltstshisDto 顧客ステータス
     * @return 更新した行数
     */
    int regist(McltstshisDto mcltstshisDto);

}
