package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TgrprltqttlDto;

/**
 * <pre>
 * tgrprltqttl(実績Qグループ集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TgrprltqttlService {

    /**
     * 実績Qグループ集計
     * 実績Qグループ集計情報を返却する
     * 
     * @param grpno グループNo
     * @param year 年度
     * @param quarter 四半期
     * @param ttlunit 集計単位
     * @return List<TgrprltqttlDto> 実績Qグループ集計一覧
     */
    List<TgrprltqttlDto> getGrpResultTotalList(String grpno, String year, int quarter, int ttlunit);

}
