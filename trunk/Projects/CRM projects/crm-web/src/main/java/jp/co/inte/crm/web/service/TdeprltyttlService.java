package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TdeprltyttlDto;

/**
 * <pre>
 * tdeprltyttl(年間実績部門集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TdeprltyttlService {

    /**
     * 年間実績部門集計
     * 部門の年間集計一覧を返す
     * 
     * @param depno 部門
     * @param empcd 社員CD
     * @param year 年度
     * @param ttlunit 集計単位
     * @return List<Tdeprltyttl> 年間実績部門集計一覧
     */
    List<TdeprltyttlDto> getDepYearTotalList(String depno, String empcd, String year, int ttlunit);

}
