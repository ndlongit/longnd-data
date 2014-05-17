package jp.co.inte.crm.web.form;

import java.math.BigDecimal;

/**
 * N104 form
 * 
 * @since
 * 
 */
public class N104Form {

    /** No */
    public BigDecimal flgNo;

    /** 統括別 割振り枠 */
    public Short ctrlAssignFrame;

    public boolean ctrlAssignFrameDisabled;

    /** ネタフラグ名称 */
    public String flgNm = "";

    /** 開始日 */
    public String startdt;

    /** クローズ予定日 */
    public String closePlandate;

    /** 更新日 */
    public String updtimestamp;
}