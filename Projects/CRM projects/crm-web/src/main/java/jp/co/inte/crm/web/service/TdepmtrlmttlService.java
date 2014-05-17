package jp.co.inte.crm.web.service;

import java.util.Date;
import java.util.List;

import jp.co.inte.crm.web.dto.TdepmtrlmttlDto;

/**
 * <pre>
 * tdepmtrlmttl(ネタ月部門集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TdepmtrlmttlService {

    /**
     * ネタ月部門集計
     * 部門のネタQ集計情報を返却する
     * 
     * @param depno 部門No
     * @param date 年月
     * @param ttlunit 集計単位
     * @return List<Tdepmtrlmttl> ネタ月部門集計一覧
     */
    List<TdepmtrlmttlDto> getDepMaterialTotalList(String depno, Date date, int ttlunit);

}
