package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TdepmtrlqttlDto;

/**
 * <pre>
 * tdepmtrlqttl(ネタQ部門集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TdepmtrlqttlService {

    /**
     * ネタQ部門集計
     * 部門のネタQ集計情報を返却する
     * 
     * @param depno 部門No
     * @param year 年度
     * @param quarter 四半期
     * @param ttlunit 集計単位
     * @return List<TdepmtrlqttlDto> ネタQ部門集計
     */
    List<TdepmtrlqttlDto> getDepMaterialTotalList(String depno, String year, int quarter, int ttlunit);

}
