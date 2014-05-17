package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * 法人マスタ
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MhoBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 法人ID "HC"+連番8桁 */
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String hoid;

    /** 法人CD */
    @Column(length = 9, nullable = true, unique = true)
    public String hocd;

    /** 法人ステータス区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short mhostskbn;

    /** APOLLOID */
    @Column(length = 7, nullable = true, unique = true)
    public String apolloid;

    /** 商号表示区分 0:前株,1:後株,2:前有,3:後有,9:その他 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short biznmkbn;

    /** 法人名 */
    @Column(length = 384, nullable = true, unique = false)
    public String honm;

    /** 法人名カナ */
    @Column(length = 384, nullable = true, unique = false)
    public String hokn;

    /** 法人区分 0:法人,1:個人事業主 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short hokbn;

    /** 代表者姓 */
    @Column(length = 192, nullable = true, unique = false)
    public String repnmlast;

    /** 代表者名 */
    @Column(length = 192, nullable = true, unique = false)
    public String repnmfirst;

    /** 代表者カナ姓 */
    @Column(length = 192, nullable = true, unique = false)
    public String repknlast;

    /** 代表者カナ名 */
    @Column(length = 192, nullable = true, unique = false)
    public String repknfirst;

    /** 代表TEL */
    @Column(length = 20, nullable = true, unique = false)
    public String reptel;

    /** 住所CD */
    @Column(length = 8, nullable = true, unique = false)
    public String adcd;

    /** 町名 住所1 */
    @Column(length = 192, nullable = true, unique = false)
    public String adr01;

    /** 番地ビル 住所2 */
    @Column(length = 192, nullable = true, unique = false)
    public String adr02;

    /** 事業内容 */
    @Column(length = 3000, nullable = true, unique = false)
    public String bizcontents;

    /** 業種CD */
    @Column(precision = 5, nullable = true, unique = false)
    public Short bizcd;

    /** 業種中分類CD */
    @Column(precision = 5, nullable = true, unique = false)
    public Short midbizcd;

    /** URL */
    @Column(length = 384, nullable = true, unique = false)
    public String url;

    /** 備考 */
    @Column(length = 1000, nullable = true, unique = false)
    public String remarks;

    /** 備考最新入力者CD */
    @Column(length = 20, nullable = true, unique = false)
    public String remarkslastinputempcd;

    /** 備考最新入力日 */
    @Column(nullable = true, unique = false)
    public Date remarkslastinputdt;

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

    /** mapollocltList関連プロパティ */
    @OneToMany(mappedBy = "mho")
    public List<Mapolloclt> mapollocltList;

    /** mcltList関連プロパティ */
    @OneToMany(mappedBy = "mho")
    public List<Mclt> mcltList;

    /** mhocgList関連プロパティ */
    @OneToMany(mappedBy = "mho")
    public List<Mhocg> mhocgList;

    /** mhostshisList関連プロパティ */
    @OneToMany(mappedBy = "mho")
    public List<Mhostshis> mhostshisList;

}
