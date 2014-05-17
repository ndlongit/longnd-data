package jp.co.inte.crm.web.form;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.cli.Option;

public class N102Form {

    // Option controls
    public List<Option> searchvewPld;

    public List<Option> s_prefnmPld;

    public List<Option> s_grpnm;

    public List<Option> s_ctrldeptnm;

    public List<Option> s_depnm;

    public List<Option> midbizPld;

    public List<Option> cltrankPld;

    public BigDecimal flgno;

    public String flgnm;

    public String ctrlassignframe;

    public Date strdt;

    public Date enddt;

    public Date updatedt;

    public Timestamp crttimestamp;

    public String crtusrcd;

    public String crtusrid;

    public String crtpgid;

    public Timestamp updtimestamp;

    public String updusrcd;

    public String updusrid;

    public String updpgid;
}
