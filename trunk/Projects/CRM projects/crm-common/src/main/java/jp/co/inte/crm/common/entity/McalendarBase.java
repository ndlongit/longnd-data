package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * カレンダマスタ
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class McalendarBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 年月日 */
    @Id
    @Column(nullable = false, unique = true)
    public Date date;

    /** 暦年月 */
    @Column(length = 8, nullable = true, unique = false)
    public String caldate;

    /** Divカレンダー年月 */
    @Column(length = 6, nullable = true, unique = false)
    public String divcaldate;

    /** Div年月週（表示用） */
    @Column(precision = 1, nullable = true, unique = false)
    public BigDecimal divdatewview;

    /** Div年月週（計算用） */
    @Column(precision = 1, nullable = true, unique = false)
    public BigDecimal divdatewcal;

    /** 年度 */
    @Column(length = 4, nullable = true, unique = false)
    public String year;

    /** 四半期 2014年3Q */
    @Column(length = 7, nullable = true, unique = false)
    public String quarter;

    /** 半期 2014年後期 */
    @Column(length = 7, nullable = true, unique = false)
    public String half;

    /** 曜日 */
    @Column(precision = 1, nullable = true, unique = false)
    public BigDecimal dayofweek;

    /** 月内営業日数 */
    @Column(precision = 2, nullable = true, unique = false)
    public BigDecimal sldays;

    /** 営業日 */
    @Column(precision = 2, nullable = true, unique = false)
    public BigDecimal sldt;

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
