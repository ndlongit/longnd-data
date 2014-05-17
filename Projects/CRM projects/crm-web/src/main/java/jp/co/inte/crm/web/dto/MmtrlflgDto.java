package jp.co.inte.crm.web.dto;

import java.math.BigDecimal;

import jp.co.inte.crm.common.dto.CrmBaseDto;

public class MmtrlflgDto extends CrmBaseDto {

    /** フラグNo */
    public BigDecimal flgno;

    /** フラグ名 */
    public String flgnm;

    /** 統括別割り振り枠 */
    public Short ctrlassignframe;

    /** 開始日 */
    public String startdt;

    /** クローズ予定日 */
    public String closeplandate;

    /** レコード更新日時 */
    public String updtimestamp;

    /** mmtrlflgxdept関連プロパティ */
    public MmtrlflgxdeptDto mmtrlflgxdeptDto;
}
