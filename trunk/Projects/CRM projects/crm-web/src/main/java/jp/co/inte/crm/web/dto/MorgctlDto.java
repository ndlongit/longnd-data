package jp.co.inte.crm.web.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.OneToMany;

import jp.co.inte.crm.common.dto.CrmBaseDto;
import jp.co.inte.crm.common.entity.Mcltflgxdept;
import jp.co.inte.crm.common.entity.Memgmmgr;
import jp.co.inte.crm.common.entity.Mintrausrsec;
import jp.co.inte.crm.common.entity.Mmtrlflgxdept;

public class MorgctlDto extends CrmBaseDto {

    /** 組織番号 */
    public BigDecimal deptsecno;

    /** 有効開始日 */
    public Date expstrdt;

    /** 有効終了日 */
    public Date expenddt;

    /** 上位組織番号 */
    public BigDecimal uprdeptsecno;

    /** 組織名 */
    public String deptsecnm;

    /** 組織区分 */
    public String deptseckbn;

    /** 表示順 */
    public BigDecimal dspseq;

    /** インテリジェンス組織番号 */
    public String intedeptsecno;

    /** 非表示フラグ */
    public String inviflg;

    /** mcltflgxdeptList関連プロパティ */
    public List<Mcltflgxdept> mcltflgxdeptList;

    /** mintrausrsecList関連プロパティ */
    public List<Mintrausrsec> mintrausrsecList;

    /** mmtrlflgxdeptList関連プロパティ */
    @OneToMany(mappedBy = "morgctl")
    public List<Mmtrlflgxdept> mmtrlflgxdeptList;

    /** memgmmgr関連プロパティ */
    public Memgmmgr memgmmgr;

}