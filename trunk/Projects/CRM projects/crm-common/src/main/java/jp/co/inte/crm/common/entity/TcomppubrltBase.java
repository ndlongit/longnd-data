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
 * 自社最終掲載実績
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class TcomppubrltBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 顧客CD */
    @Column(length = 9, nullable = false, unique = false)
    public String cltcd;

    /** 自社最終掲載日 */
    @Column(nullable = true, unique = false)
    public Date complastpubdt;

    /** 最終掲載媒体 セット商品名 */
    @Column(length = 20, nullable = true, unique = false)
    public String lastpubprod;

    /** 自社最終掲載金額 */
    @Column(precision = 131089, nullable = true, unique = false)
    public BigDecimal complastpubamount;

    /** 最終売上部署 */
    @Column(length = 20, nullable = true, unique = false)
    public String lastslpost;

    /** 最終売上営業名 */
    @Column(length = 20, nullable = true, unique = false)
    public String lastslempnm;

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
