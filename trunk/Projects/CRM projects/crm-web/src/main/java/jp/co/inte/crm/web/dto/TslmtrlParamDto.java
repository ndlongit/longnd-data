package jp.co.inte.crm.web.dto;

import jp.co.inte.crm.common.dto.CrmBaseDto;

public class TslmtrlParamDto extends CrmBaseDto {

    public String cltid;

    private String slmtrlid;

    public String getSlmtrlid() {
        return slmtrlid;
    }

    public void setSlmtrlid(String slmtrlid) {
        this.slmtrlid = slmtrlid;
    }
}