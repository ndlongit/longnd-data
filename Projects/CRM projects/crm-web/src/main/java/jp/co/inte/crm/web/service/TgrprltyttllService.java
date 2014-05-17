package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TgrprltyttllDto;

/**
 * <pre>
 * Tgrprltyttll(年間実績グループ集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TgrprltyttllService {

    /**
     * 年間実績グループ集計
     * 年間実績グループ集計の一覧を返す
     * 
     * @param grpno グループ
     * @param year 年度
     * @param ttlunit 集計単位
     * @return List<Tgrprltyttll> 年間実績グループ集計一覧
     */
    List<TgrprltyttllDto> getTapproachdtlList(String grpno, String year, int ttlunit);

}
