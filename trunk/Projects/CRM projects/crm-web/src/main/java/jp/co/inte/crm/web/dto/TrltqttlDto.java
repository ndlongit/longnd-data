package jp.co.inte.crm.web.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jp.co.inte.crm.common.dto.CrmBaseDto;

public class TrltqttlDto extends CrmBaseDto {

    public String inteempno;

    public String date;

    public Integer ttlunit;

    public Integer productkbn;

    public BigDecimal targetamount;

    public Timestamp crttimestamp;

    public String crtusrcd;

    public String crtusrid;

    public String crtpgid;

    public Timestamp updtimestamp;

    public String updusrcd;

    public String updusrid;

    public String updpgid;

    public BigDecimal slamount;

}
