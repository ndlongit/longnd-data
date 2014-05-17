package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 組織マスタ ALより取込
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MorgctlBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 組織番号 */
    @Column(precision = 11, nullable = false, unique = true)
    public BigDecimal deptsecno;

    /** 有効開始日 */
    @Column(nullable = true, unique = false)
    public Date expstrdt;

    /** 有効終了日 */
    @Column(nullable = true, unique = false)
    public Date expenddt;

    /** 上位組織番号 */
    @Column(precision = 11, nullable = true, unique = false)
    public BigDecimal uprdeptsecno;

    /** 組織名 */
    @Column(length = 150, nullable = true, unique = false)
    public String deptsecnm;

    /** 組織区分 */
    @Column(length = 2, nullable = true, unique = false)
    public String deptseckbn;

    /** 表示順 */
    @Column(precision = 9, nullable = true, unique = false)
    public BigDecimal dspseq;

    /** インテリジェンス組織番号 */
    @Column(length = 20, nullable = true, unique = false)
    public String intedeptsecno;

    /** 非表示フラグ */
    @Column(length = 1, nullable = true, unique = false)
    public String inviflg;

    // /** レコード作成日時 */
    // @Column(nullable = true, unique = false)
    // public Timestamp crttimestamp;
    //
    // /** レコード作成利用者コード */
    // @Column(length = 9, nullable = true, unique = false)
    // public String crtusrcd;
    //
    // /** レコード作成利用者ID */
    // @Column(length = 20, nullable = true, unique = false)
    // public String crtusrid;
    //
    // /** 登録プログラムID */
    // @Column(length = 128, nullable = true, unique = false)
    // public String crtpgid;
    //
    // /** レコード更新日時 */
    // @Column(nullable = true, unique = false)
    // public Timestamp updtimestamp;
    //
    // /** レコード更新利用者コード */
    // @Column(length = 9, nullable = true, unique = false)
    // public String updusrcd;
    //
    // /** レコード更新利用者ID */
    // @Column(length = 20, nullable = true, unique = false)
    // public String updusrid;
    //
    // /** 更新プログラムID */
    // @Column(length = 128, nullable = true, unique = false)
    // public String updpgid;

    /** mcltflgxdeptList関連プロパティ */
    @OneToMany(mappedBy = "morgctl")
    public List<Mcltflgxdept> mcltflgxdeptList;

    /** mintrausrsecList関連プロパティ */
    @OneToMany(mappedBy = "morgctl")
    public List<Mintrausrsec> mintrausrsecList;

    /** mmtrlflgxdeptList関連プロパティ */
    @OneToMany(mappedBy = "morgctl")
    public List<Mmtrlflgxdept> mmtrlflgxdeptList;

    /** memgmmgr関連プロパティ */
    @OneToOne
    @JoinColumn(name = "deptsecno", referencedColumnName = "deptsecno")
    public Memgmmgr memgmmgr;

}
