package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TgrpmtrlqttlDto;

/**
 * <pre>
 * tgrpmtrlqttl(ネタQグループ集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TgrpmtrlqttlService {

    /**
     * ネタQグループ集計
     * グループのネタQ集計情報を返却する
     * 
     * @param grpno グループNo
     * @param year 年度
     * @param quarter 四半期
     * @param ttlunit 集計単位
     * @return List<TgrpmtrlqttlDto> ネタQグループ集計一覧
     */
    List<TgrpmtrlqttlDto> getGrpMaterialTotalList(String grpno, String year, int quarter, int ttlunit);

}
