package jp.co.inte.crm.web.service;

import java.util.Date;
import java.util.List;

import jp.co.inte.crm.web.dto.TmtrlqttlDto;

/**
 * <pre>
 * tgrpmtrlmttl(ネタ月グループ集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TgrpmtrlmttlService {

    /**
     * ネタ月グループ集計
     * グループのネタ月集計を返却する
     * 
     * @param grpno グループNo
     * @param date 年月
     * @param ttlunit 集計単位
     * @return List<TmtrlqttlDto> ネタ月グループ集計一覧
     */
    List<TmtrlqttlDto> getGrpMaterialTotalList(String grpno, Date date, int ttlunit);

}
