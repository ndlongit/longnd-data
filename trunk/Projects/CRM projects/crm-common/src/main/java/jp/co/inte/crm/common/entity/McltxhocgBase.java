package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 法人顧客担当者紐付
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class McltxhocgBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 担当者紐付ID "CH"+連番10桁 */
    @Id
    @Column(length = 12, nullable = false, unique = true)
    public String cghocltid;

    /** 顧客ID */
    @Column(length = 10, nullable = false, unique = false)
    public String cltid;

    /** 法人担当者ID */
    @Column(length = 12, nullable = false, unique = false)
    public String hocgid;

    /** 決裁権 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean dicision;

    /** 発注担当 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean ordercg;

    /** その他 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean etc;

    /** 在籍日時 */
    @Column(nullable = true, unique = false)
    public Timestamp attendeddatetime;

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

    /** mhocg関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "hocgid", referencedColumnName = "hocgid")
    public Mhocg mhocg;

}
