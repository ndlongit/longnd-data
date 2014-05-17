package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * アクション集計マスタ アクション集計設定画面で使用する
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MactsumBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 統括部No */
    @Id
    @Column(length = 11, nullable = false, unique = false)
    public String ctrldeptno;

    /** 年度 2014 */
    @Id
    @Column(length = 4, nullable = false, unique = false)
    public String year;

    /** 四半期 第2Q等 */
    @Id
    @Column(precision = 1, nullable = false, unique = false)
    public BigDecimal quarter;

    /** フラグ1 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg1;

    /** フラグ2 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg2;

    /** フラグ3 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg3;

    /** フラグ4 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg4;

    /** フラグ5 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg5;

    /** フラグ6 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg6;

    /** フラグ7 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg7;

    /** フラグ8 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg8;

    /** フラグ9 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg9;

    /** フラグ10 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean flg10;

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
