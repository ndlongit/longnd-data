package jp.co.inte.crm.web.service;

import java.util.Date;
import java.util.List;

import jp.co.inte.crm.web.dto.TdeprltmttlDto;

/**
 * <pre>
 * tdeprltmttl(売上ネタ部門実績集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TdeprltmttlService {

    /**
     * 実績月部門集計
     * 部門の実績月集計を返却する
     * 
     * @param depno 部門No
     * @param date 年月
     * @param ttlunit 集計単位
     * @return List<Tdeprltmttl> 実績月部門集計一覧
     */
    List<TdeprltmttlDto> getDepResultTotalList(String depno, Date date, int ttlunit);

}
