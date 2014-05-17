package jp.co.inte.crm.web.dto;

import java.math.BigDecimal;

import jp.co.inte.crm.common.dto.CrmBaseDto;

public class McltstshisDto extends CrmBaseDto {

    /** 顧客ID */
    public String cltid;

    /** 履歴ID */
    public BigDecimal historyid;

    /** 顧客ステータス履歴区分 1:新規,2:見込客,3:既存,4:ブランク */
    public String cltstshiscd;

    /** 理由 */
    public String reason;

    /** 理由備考 */
    public String reasonrmks;

    /** まとめ先顧客 */
    public String ajtcltcd;

}
