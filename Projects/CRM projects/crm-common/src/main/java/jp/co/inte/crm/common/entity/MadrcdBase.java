package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 住所コードマスタ 求人DBの住所コードマスタよりImport
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MadrcdBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 住所ＣＤ */
    @Column(length = 8, nullable = true, unique = false)
    public String adcd;

    /** 区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short kbn;

    /** 郵便番号 XXX-XXXX */
    @Column(length = 8, nullable = true, unique = false)
    public String zip;

    /** 漢字郵便番号 */
    @Column(length = 100, nullable = true, unique = false)
    public String kjzip;

    /** 住所名 */
    @Column(length = 600, nullable = true, unique = false)
    public String adr;

    /** 都道府県CD */
    @Column(precision = 2, nullable = true, unique = false)
    public BigDecimal prefcd;

    /** 市区町村CD */
    @Column(precision = 2, nullable = true, unique = false)
    public BigDecimal ctycd;

    /** 町丁目CD */
    @Column(precision = 2, nullable = true, unique = false)
    public BigDecimal towncd;

    /** 都道府県 */
    @Column(length = 30, nullable = true, unique = false)
    public String prefnm;

    /** 市区郡 */
    @Column(length = 100, nullable = true, unique = false)
    public String ctynm;

    /** 町名 */
    @Column(length = 200, nullable = true, unique = false)
    public String townnm;

    /** 変更住所CD */
    @Column(length = 8, nullable = true, unique = false)
    public String chadcd;

    /** 変更後都道府県 */
    @Column(length = 30, nullable = true, unique = false)
    public String chprefnm;

    /** 変更後市区郡 */
    @Column(length = 100, nullable = true, unique = false)
    public String chcitynm;

    /** 変更後町名 */
    @Column(length = 200, nullable = true, unique = false)
    public String chtownnm;

    /** 更新日 */
    @Column(nullable = true, unique = false)
    public Date updday;

    /** inte社員CD */
    @Column(length = 20, nullable = true, unique = false)
    public String inteusrid;

    /** inte名前 */
    @Column(length = 200, nullable = true, unique = false)
    public String inteusrnm;

    /** inte統括部 */
    @Column(length = 150, nullable = true, unique = false)
    public String inteunifinm;

    /** inte部 */
    @Column(length = 150, nullable = true, unique = false)
    public String intedeptnm;

    /** inte課 */
    @Column(length = 150, nullable = true, unique = false)
    public String intesecnm;

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

}
