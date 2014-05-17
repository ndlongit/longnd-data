package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TdeprltqttllDto;

/**
 * <pre>
 * tdeprltqttll(実績Q部門集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TdeprltqttllService {

    /**
     * 実績Q部門集計
     * 部門の実績Q集計情報を返却する
     * 
     * @param depno 部門No
     * @param year 年度
     * @param quarter 四半期
     * @param ttlunit 集計単位
     * @return List<TdeprltqttllDto> 実績Q部門集計一覧
     */
    List<TdeprltqttllDto> getDepResultTotalList(String depno, String year, int quarter, int ttlunit);

}
