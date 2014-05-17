package jp.co.inte.crm.web.service;

import java.util.Date;
import java.util.List;

import jp.co.inte.crm.web.dto.TctrldeptmtrlmttlDto;

/**
 * <pre>
 * tctrldeptmtrlmttl(ネタ月統括部集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TctrldeptmtrlmttlService {

    /**
     * ネタ月統括部集計
     * 統括部のネタ月集計情報を返却する
     * 
     * @param ctrldeptno 統括部No
     * @param date 年月
     * @param ttlunit 集計単位
     * @return List<Tctrldeptmtrlmttl> ネタ月統括部集計一覧
     */
    List<TctrldeptmtrlmttlDto> getCtrldeptMaterialTotalList(String ctrldeptno, Date date, int ttlunit);

}
