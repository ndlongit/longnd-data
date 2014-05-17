package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * 売上ネタ
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class TslmtrlBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 売上ネタID "N"+yy+連番8桁 */
    @Id
    @Column(length = 11, nullable = false, unique = true)
    public String slmtrlid;

    /** 顧客ID */
    @Column(length = 10, nullable = false, unique = false)
    public String cltid;

    /** 提案ステータス区分 失注、提案、受注 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short offerstskbn;

    /** ヨミ区分 未提案、Dヨミ、Cヨミ、Bヨミ、Aヨミ */
    @Column(precision = 5, nullable = true, unique = false)
    public Short expectkbn;

    /** 売上金額 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal slamount;

    /** 商品CD プルダウン表示 */
    @Column(length = 8, nullable = true, unique = false)
    public String prodcd;

    /** 提案日 */
    @Column(nullable = true, unique = false)
    public Date offerdate;

    /** 提案活動方法区分 飛込み、訪問、電話、FAX、メール　DM・資料送付、来社、会食、その他 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short offeractmethodkbn;

    /** クロージング日 */
    @Column(nullable = true, unique = false)
    public Date closingdt;

    /** クロージング活動方法区分 飛込み、訪問、電話、FAX、メール　DM・資料送付、来社、会食、その他 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short closingactmethodkbn;

    /** 掲載開始日 */
    @Column(nullable = true, unique = false)
    public Date pubstartdt;

    /** 計上開始週 */
    @Column(precision = 1, nullable = true, unique = false)
    public BigDecimal apprstartweek;

    /** 掲載期間 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short pubterm;

    /** 掲載終了日 */
    @Column(nullable = true, unique = false)
    public Date pubenddt;

    /** 分割区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short divisionkbn;

    /** 計上区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short apprdatekbn;

    /** 評価区分 an、DODA */
    @Column(precision = 5, nullable = true, unique = false)
    public Short valuekbn;

    /** コメント */
    @Column(length = 200, nullable = true, unique = false)
    public String coment;

    /** 担当営業CD */
    @Column(length = 20, nullable = true, unique = false)
    public String cgempcd;

    /** 担当営業区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short cgkbn;

    /** 担当営業名 */
    @Column(length = 200, nullable = true, unique = false)
    public String cgempnm;

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

    /** mslmtrlxflg関連プロパティ */
    @OneToOne
    @JoinColumn(name = "slmtrlid", referencedColumnName = "slmtrlid")
    public Mslmtrlxflg mslmtrlxflg;

}
