package jp.co.inte.crm.web.service;

import java.util.Date;
import java.util.List;

import jp.co.inte.crm.web.dto.TgrprltmttlDto;

/**
 * <pre>
 * tgrprltmttl(実績月グループ集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TgrprltmttlService {

    /**
     * 実績月グループ集計
     * グループの実績月集計を返却する
     * 
     * @param grpno グループNo
     * @param date 年月
     * @param ttlunit 集計単位
     * @return List<TgrprltmttlDto> 実績月グループ集計一覧
     */
    List<TgrprltmttlDto> getGrpResultTotalList(String grpno, Date date, int ttlunit);

}
