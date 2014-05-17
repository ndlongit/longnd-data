package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * 自社競合売上集計
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class TcomcompeslttlBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 顧客ID */
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String cltid;

    /** 顧客CD */
    @Column(length = 9, nullable = true, unique = false)
    public String cltcd;

    /** APOLLOID */
    @Column(length = 7, nullable = true, unique = false)
    public String apolloid;

    /** 商品区分 an、DODA */
    @Column(precision = 5, nullable = true, unique = false)
    public Short prdkbn;

    /** 年度 2014 */
    @Column(length = 4, nullable = true, unique = false)
    public String year;

    /** 取込区分 0:自社、1:求人DB、2:GreenDB */
    @Column(precision = 5, nullable = true, unique = false)
    public Short importkbn;

    /** 競合媒体名 */
    @Column(length = 255, nullable = true, unique = false)
    public String compelastpubproduct;

    /** 競合最終掲載日 */
    @Column(nullable = true, unique = false)
    public Date compelastpubdt;

    /** 競合最終掲載金額 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal compelastpubamount;

    /** 競合最終掲載枠 */
    @Column(length = 255, nullable = true, unique = false)
    public String compelastpubframe;

    /** 職種 */
    @Column(length = 255, nullable = true, unique = false)
    public String jobcategory;

    /** 雇用形態 */
    @Column(length = 255, nullable = true, unique = false)
    public String emptyform;

    /** 競合年間合計 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal compeyearttl;

    /** 競合4月実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt4;

    /** 競合5月実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt5;

    /** 競合6月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt6;

    /** 競合7月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt7;

    /** 競合8月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt8;

    /** 競合9月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt9;

    /** 競合10月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt10;

    /** 競合11月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt11;

    /** 競合12月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt12;

    /** 競合1月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt1;

    /** 競合2月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt2;

    /** 競合3月前実績 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal monthrlt3;

    /** 最終売上チーム */
    @Column(length = 50, nullable = true, unique = false)
    public String lastslteam;

    /** 最終売上営業員 */
    @Column(length = 50, nullable = true, unique = false)
    public String lastslemp;

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
    @OneToOne
    @JoinColumn(name = "cltid", referencedColumnName = "cltid")
    public Mclt mclt;

}
