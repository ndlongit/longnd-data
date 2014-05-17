package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TrltyttlDto;

/**
 * <pre>
 * trltyttl(年間実績個人集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TrltyttlService {

    /**
     * 年間実績個人集計
     * 年間実績個人集計の一覧を返す
     * 
     * @param empcd 社員CD
     * @param year 年度
     * @param ttlunit 集計単位
     * @return List<TrltyttlDto> 年間実績個人集計一覧
     */
    List<TrltyttlDto> getTapproachdtlList(String empcd, String year, int ttlunit);

}
