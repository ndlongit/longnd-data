package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 接触予定履歴
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class TapproachplanhisBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 接触ID */
    @Id
    @Column(length = 12, nullable = false, unique = false)
    public String approachid;

    /** 履歴ID */
    @Id
    @Column(precision = 3, nullable = false, unique = false)
    public BigDecimal historyid;

    /** 件名 */
    @Column(length = 40, nullable = true, unique = false)
    public String subject;

    /** 活動ステータス区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short actstskbn;

    /** 日付 */
    @Column(nullable = true, unique = false)
    public Date date;

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

    /** 問合せきっかけ区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short connectbeginkbn;

    /** 実行担当者ID */
    @Column(length = 20, nullable = true, unique = false)
    public String actcgid;

    /** 設定者ID */
    @Column(length = 20, nullable = true, unique = false)
    public String setid;

    /** 設定日 */
    @Column(nullable = true, unique = false)
    public Date setdt;

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

    /** tapproachdtl関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "approachid", referencedColumnName = "approachid")
    public Tapproachdtl tapproachdtl;

}
