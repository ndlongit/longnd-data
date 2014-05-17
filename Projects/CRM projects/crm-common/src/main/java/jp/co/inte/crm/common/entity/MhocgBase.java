package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * 法人担当者マスタ
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MhocgBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 法人担当者ID "HO"+連番10桁 */
    @Column(length = 12, nullable = true, unique = true)
    public String hocgid;

    /** 法人ID "HC"+連番8桁 */
    @Column(length = 10, nullable = false, unique = false)
    public String hoid;

    /** 法人担当者姓 */
    @Column(length = 192, nullable = true, unique = false)
    public String hocgnmlast;

    /** 法人担当者名 */
    @Column(length = 192, nullable = true, unique = false)
    public String hocgnmfirst;

    /** 法人担当者カナ姓 */
    @Column(length = 192, nullable = true, unique = false)
    public String hocgkanalast;

    /** 法人担当者カナ名 */
    @Column(length = 192, nullable = true, unique = false)
    public String hocgkanafirst;

    /** 所属部署 */
    @Column(length = 192, nullable = true, unique = false)
    public String post;

    /** 役職 */
    @Column(length = 192, nullable = true, unique = false)
    public String position;

    /** 電話番号 連絡先1 */
    @Column(length = 20, nullable = true, unique = false)
    public String tel;

    /** TEL携帯 連絡先2 */
    @Column(length = 20, nullable = true, unique = false)
    public String cellphone;

    /** メール */
    @Column(length = 360, nullable = true, unique = false)
    public String mail;

    /** FAX */
    @Column(length = 20, nullable = true, unique = false)
    public String fax;

    /** クローズステータス区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short closestskbn;

    /** クローズ理由 */
    @Column(length = 300, nullable = true, unique = false)
    public String closereason;

    /** クローズ理由備考 */
    @Column(length = 300, nullable = true, unique = false)
    public String closereasonrmks;

    /** 住所CD */
    @Column(length = 8, nullable = true, unique = false)
    public String adcd;

    /** 町名 */
    @Column(length = 192, nullable = true, unique = false)
    public String adr01;

    /** 番地ビル */
    @Column(length = 192, nullable = true, unique = false)
    public String adr02;

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

    /** mcltxhocgList関連プロパティ */
    @OneToMany(mappedBy = "mhocg")
    public List<Mcltxhocg> mcltxhocgList;

    /** mho関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "hoid", referencedColumnName = "hoid")
    public Mho mho;

}
