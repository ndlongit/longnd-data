package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 計画月個人
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class TplanmBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** インテリジェンス社員番号 */
    @Column(length = 20, nullable = false, unique = false)
    public String inteempno;

    /** 年月 */
    @Column(nullable = true, unique = false)
    public Date date;

    /** 集計単位 月,1w,2w,3w,4w */
    @Column(precision = 5, nullable = true, unique = false)
    public Short ttlunit;

    /** 商品区分 an,DODA */
    @Column(precision = 5, nullable = true, unique = false)
    public Short productkbn;

    /** 集計顧客ステータス区分 合計金額、既存、新規・ブランク */
    @Column(precision = 5, nullable = true, unique = false)
    public Short ttlmcltstshiskbn;

    /** 売上金額計画 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal slamountplan;

    /** 売上社数計画 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short slcountplan;

    /** 提案金額計画 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal offeramountplan;

    /** 提案社数計画 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short offercountplan;

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

    /** mintrausr関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "inteempno", referencedColumnName = "inteempno")
    public Mintrausr mintrausr;

}
