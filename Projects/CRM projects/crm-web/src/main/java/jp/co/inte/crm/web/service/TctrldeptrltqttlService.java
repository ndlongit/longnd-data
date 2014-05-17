package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TctrldeptrltqttlDto;

/**
 * <pre>
 * tctrldeptrltqttl(実績Q統括部集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TctrldeptrltqttlService {

    /**
     * 実績Q統括部集計
     * 統括部の実績Q集計情報を返却する
     * 
     * @param ctrldeptno 統括部No
     * @param year 年度
     * @param quarter 四半期
     * @param ttlunit 集計単位
     * @return List<TctrldeptrltqttlDto> 実績Q統括部集計一覧
     */
    List<TctrldeptrltqttlDto> getCtrldeptResultTotalList(String ctrldeptno, String year, int quarter, int ttlunit);

}
