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

/**
 * 営業売上明細
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class TslempBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 受注番号 */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer ordcd;

    /** 案件番号 */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer itemcd;

    /** 顧客CD */
    @Column(length = 10, nullable = false, unique = false)
    public String cltcd;

    /** セット商品名 */
    @Column(length = 2147483647, nullable = true, unique = false)
    public String setprodnm;

    /** 商品区分 0:an,1:DUDA */
    @Column(precision = 5, nullable = true, unique = false)
    public Short prodkbn;

    /** 金額 */
    @Column(precision = 12, nullable = true, unique = false)
    public BigDecimal amount;

    /** 受注日 */
    @Column(nullable = true, unique = false)
    public Date orddt;

    /** 計上日 */
    @Column(nullable = true, unique = false)
    public Date apprd;

    /** 計上月 */
    @Column(length = 6, nullable = true, unique = false)
    public String apprm;

    /** 計上週 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short apprw;

    /** 掲載開始日 */
    @Column(nullable = true, unique = false)
    public Date pubstartdt;

    /** 営業担当者ID */
    @Column(length = 1, nullable = true, unique = false)
    public String cltempid;

    /** 代理店CD */
    @Column(length = 1, nullable = true, unique = false)
    public String agtcd;

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
    @JoinColumn(name = "cltcd", referencedColumnName = "cltcd")
    public Mclt mclt;

}
