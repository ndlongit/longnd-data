package jp.co.inte.crm.web.service;

import java.util.Date;
import java.util.List;

import jp.co.inte.crm.web.dto.TtargetDto;

/**
 * <pre>
 * ttarget(目標)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TtargetService {

    /**
     * 統括目標一覧
     * 統括部の目標金額を返す
     * 
     * @param ctrldeptcd 統括部No
     * @param date 年月
     * @param totalunit 集計単位
     * @param productkbn 商品区分
     * @return List<TtargetDto> 統括目標一覧を返す
     */
    List<TtargetDto> getCtrTargetamt(String ctrldeptcd, Date date, int totalunit, int productkbn);

    /**
     * 部門目標一覧
     * 部門の目標金額を返す
     * 
     * @param depno 部門No
     * @param date 年月
     * @param totalunit 集計単位
     * @param productkbn 商品区分
     * @return List<TtargetDto> 部門目標一覧を返す
     */
    List<TtargetDto> getDepTargetamt(String depno, Date date, int totalunit, int productkbn);

    /**
     * グループ目標一覧
     * グループの目標金額を返す
     * 
     * @param grpno グループNo
     * @param grpcd グループCD
     * @param date 年月
     * @param num 商品区分
     * @return List<TtargetDto> グループ目標一覧を返す
     */
    List<TtargetDto> getGrpTargetamt(String grpno, int grpcd, Date date, int num);

    /**
     * 営業員目標一覧
     * 社員の目標金額を返す
     * 
     * @param empcd 社員CD
     * @param grpcd グループCD
     * @param date 年月
     * @param num 商品区分
     * @return List<TtargetDto> 営業員目標一覧を返す
     */
    List<TtargetDto> getEmpTargetamt(String empcd, int grpcd, Date date, int num);

    /**
     * 売上ネタ売上実績集計一覧
     * 計画画面の売上実績欄の売上金額、売上社数(合計、既存、ブランク)を返却する。
     * 
     * @param empcd （社員CD or グループNo or 部門No or 統括部No)
     * @param totalunit 集計単位
     * @param productkbn 商品区分
     * @return List<TtargetDto> 売上ネタ売上実績集計一覧を返す
     */
    List<TtargetDto> getSlTotalAmtList(String empcd, int totalunit, int productkbn);

}
