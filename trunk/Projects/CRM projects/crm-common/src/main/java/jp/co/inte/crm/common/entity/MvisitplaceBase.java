package jp.co.inte.crm.common.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 訪問先マスタ
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MvisitplaceBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 訪問先ID "VP"+連番10桁 */
    @Id
    @Column(length = 12, nullable = false, unique = true)
    public String visitplaceid;

    /** 顧客ID */
    @Column(length = 10, nullable = false, unique = false)
    public String cltid;

    /** 拠点名 */
    @Column(length = 10, nullable = true, unique = false)
    public String location;

    /** 郵便番号 */
    @Column(length = 8, nullable = true, unique = false)
    public String zip;

    /** 都道府県 */
    @Column(length = 60, nullable = true, unique = false)
    public String pref;

    /** 市区町村 */
    @Column(length = 150, nullable = true, unique = false)
    public String cty;

    /** 町名 */
    @Column(length = 192, nullable = true, unique = false)
    public String ad01;

    /** 番地ビル */
    @Column(length = 192, nullable = true, unique = false)
    public String ad02;

    /** TEL */
    @Column(length = 20, nullable = true, unique = false)
    public String tel;

    /** FAX */
    @Column(length = 20, nullable = true, unique = false)
    public String fax;

    /** コメント */
    @Column(length = 300, nullable = true, unique = false)
    public String coment;

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

}
