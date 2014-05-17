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
 * 社内ユーザ所属マスタ ALより取込
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MintrausrsecBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** インテリジェンス社員番号 */
    @Id
    @Column(length = 20, nullable = false, unique = false)
    public String inteempno;

    /** 組織番号 統括部No、部門No、グループNo */
    @Id
    @Column(precision = 11, nullable = false, unique = false)
    public BigDecimal deptsecno;

    /** 兼務区分 S6:EM,S5:GM,S4:MGR,S3:MEMBER */
    @Column(length = 1, nullable = true, unique = false)
    public String adpstkbn;

    /** 有効開始日 */
    @Column(nullable = true, unique = false)
    public Date expstrdt;

    /** 有効終了日 */
    @Column(nullable = true, unique = false)
    public Date expenddt;

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

    /** morgctl関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "deptsecno", referencedColumnName = "deptsecno")
    public Morgctl morgctl;

}
