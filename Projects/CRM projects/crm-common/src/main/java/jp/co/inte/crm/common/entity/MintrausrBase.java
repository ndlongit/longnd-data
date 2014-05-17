package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 社内ユーザマスタ ALより取込
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MintrausrBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** インテリジェンス社員番号 社員CD */
    @Id
    @Column(length = 20, nullable = false, unique = true)
    public String inteempno;

    /** GE営業員コード */
    @Column(length = 8, nullable = true, unique = false)
    public String geslempcd;

    /** 所属課番号 */
    @Column(precision = 11, nullable = true, unique = false)
    public BigDecimal secno;

    /** 旧所属課番号 */
    @Column(precision = 11, nullable = true, unique = false)
    public BigDecimal oldsecno;

    /** 名前 */
    @Column(length = 192, nullable = true, unique = false)
    public String name;

    /** 名前カナ */
    @Column(length = 192, nullable = true, unique = false)
    public String kana;

    /** OPPO社員ID */
    @Column(length = 20, nullable = true, unique = false)
    public String intrusrid;

    /** メールアドレス */
    @Column(length = 384, nullable = true, unique = false)
    public String mail;

    /** インテリジェンスユーザーID */
    @Column(length = 64, nullable = true, unique = false)
    public String inteusrid;

    /** 商奉行ID */
    @Column(precision = 6, nullable = true, unique = false)
    public BigDecimal abid;

    /** 無効フラグ */
    @Column(length = 1, nullable = true, unique = false)
    public String invaflg;

    /** 入社日 */
    @Column(nullable = true, unique = false)
    public Date entdt;

    /** 退社日 */
    @Column(nullable = true, unique = false)
    public Date retdt;

    /** 性別コード */
    @Column(length = 1, nullable = true, unique = false)
    public String sexcd;

    /** 備考 */
    @Column(length = 750, nullable = true, unique = false)
    public String rmks;

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

    /** mcltempList関連プロパティ */
    @OneToMany(mappedBy = "mintrausr")
    public List<Mcltemp> mcltempList;

    /** memporg関連プロパティ */
    @OneToOne
    @JoinColumn(name = "inteempno", referencedColumnName = "inteempno")
    public Memporg memporg;

    /** msysusr関連プロパティ */
    @OneToOne
    @JoinColumn(name = "inteempno", referencedColumnName = "inteempno")
    public Msysusr msysusr;

    /** mintrausrsecList関連プロパティ */
    @OneToMany(mappedBy = "mintrausr")
    public List<Mintrausrsec> mintrausrsecList;

    /** tactplanList関連プロパティ */
    @OneToMany(mappedBy = "mintrausr")
    public List<Tactplan> tactplanList;

    /** tplanmList関連プロパティ */
    @OneToMany(mappedBy = "mintrausr")
    public List<Tplanm> tplanmList;

    /** tplanqList関連プロパティ */
    @OneToMany(mappedBy = "mintrausr")
    public List<Tplanq> tplanqList;

    /** ttargetList関連プロパティ */
    @OneToMany(mappedBy = "mintrausr")
    public List<Ttarget> ttargetList;

}
