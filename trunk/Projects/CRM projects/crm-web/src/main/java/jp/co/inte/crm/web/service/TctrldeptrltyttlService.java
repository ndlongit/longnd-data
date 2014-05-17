package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TctrldeptrltyttlDto;

/**
 * <pre>
 * tctrldeptrltyttl(年間実績統括部集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TctrldeptrltyttlService {

    /**
     * 年間実績統括部集計
     * 統括部の年間集計一覧を返す
     * 
     * @param ctrldeptno 統括部
     * @param empcd 社員CD
     * @param year 年度
     * @param ttlunit 集計単位
     * @return List<Tctrldeptrltyttl> 年間実績統括部集計一覧
     */
    List<TctrldeptrltyttlDto> getCtrldeptYearTotalList(String ctrldeptno, String empcd, String year, int ttlunit);

}
