package jp.co.inte.crm.web.service;

import java.util.Date;
import java.util.List;

import jp.co.inte.crm.web.dto.TctrldeptrltmttlDto;

/**
 * <pre>
 * tctrldeptrltmttl(実績月統括部集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TctrldeptrltmttlService {

    /**
     * 実績月統括部集計
     * 統括部の実績月集計情報を返却する
     * 
     * @param ctrldeptno 統括部
     * @param date 年月
     * @param ttlunit 集計単位
     * @return List<TctrldeptrltmttlDto> 実績月統括部集計一覧
     */
    List<TctrldeptrltmttlDto> getCtrldeptResultTotalList(String ctrldeptno, Date date, int ttlunit);

}
