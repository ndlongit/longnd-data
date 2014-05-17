package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 引継管理
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MclttransmngBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 引継ID yyMM+連番8桁 */
    @Id
    @Column(length = 12, nullable = false, unique = true)
    public String transid;

    /** 顧客ID */
    @Column(length = 10, nullable = false, unique = false)
    public String cltid;

    /** 引継予定日 */
    @Column(nullable = true, unique = false)
    public Date transplandt;

    /** 開始時間 */
    @Column(nullable = true, unique = false)
    public Time starttime;

    /** 終了時間 */
    @Column(nullable = true, unique = false)
    public Time endtime;

    /** 活動目的区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short actpurposekbn;

    /** 活動方法区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short actmethodkbn;

    /** 引継方法区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short transmethodkbn;

    /** 引継ステータス区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short transstskbn;

    /** コメント */
    @Column(length = 300, nullable = true, unique = false)
    public String coment;

    /** 引継元営業員ID */
    @Column(length = 20, nullable = false, unique = false)
    public String transfromempid;

    /** 引継先営業員ID */
    @Column(length = 20, nullable = false, unique = false)
    public String transtoempid;

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

    /** mclt関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "cltid", referencedColumnName = "cltid")
    public Mclt mclt;

    /** mcltemp関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "cltid", referencedColumnName = "cltid")
    public Mcltemp mcltemp;

}
