package jp.co.inte.crm.web.dto;

import java.math.BigDecimal;

import jp.co.inte.crm.common.dto.CrmBaseDto;
import jp.co.inte.crm.common.entity.Tslmtrl;

public class MslmtrlxflgDto extends CrmBaseDto {

    /** 売上ネタID */
    public String slmtrlid;

    /** フラグNo */
    public BigDecimal flgno;

    /** tslmtrl関連プロパティ */
    public Tslmtrl tslmtrl;

}
