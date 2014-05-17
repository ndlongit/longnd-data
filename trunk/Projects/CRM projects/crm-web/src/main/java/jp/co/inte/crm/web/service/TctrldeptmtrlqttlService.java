package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TctrldeptmtrlqttlDto;

/**
 * <pre>
 * tctrldeptmtrlqttl(ネタQ統括部集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TctrldeptmtrlqttlService {

    /**
     * ネタQ統括部集計
     * 統括部のネタQ集計情報を返却する
     * 
     * @param ctrldeptno 統括部
     * @param year 年度
     * @param quarter 四半期
     * @param ttlunit 集計単位
     * @return List<Tctrldeptmtrlqttl> ネタQ統括部集計一覧
     */
    List<TctrldeptmtrlqttlDto> getCtrldeptMaterialTotalList(String ctrldeptno, String year, int quarter, int ttlunit);

}
