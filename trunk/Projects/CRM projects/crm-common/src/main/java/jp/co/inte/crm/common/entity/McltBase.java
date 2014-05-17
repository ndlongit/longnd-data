package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 顧客マスタ
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class McltBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 顧客ID "CC"+連番8桁 */
    @Id
    @Column(length = 10, nullable = false, unique = true)
    public String cltid;

    /** 顧客CD */
    @Column(length = 9, nullable = true, unique = true)
    public String cltcd;

    /** 顧客ステータス区分 物理名はALと同一 */
    @Column(length = 3, nullable = true, unique = false)
    public String cltstscd;

    /** 法人ID */
    @Column(length = 10, nullable = false, unique = false)
    public String hoid;

    /** APOLLOID */
    @Column(length = 7, nullable = true, unique = false)
    public String apolloid;

    /** 商号表示区分 0:前株,1:後株,2:前有,3:後有,9:その他 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short biznmkbn;

    /** 顧客名 */
    @Column(length = 384, nullable = false, unique = false)
    public String cltnm;

    /** 顧客名カナ */
    @Column(length = 384, nullable = true, unique = false)
    public String cltkn;

    /** 支店名 */
    @Column(length = 384, nullable = true, unique = false)
    public String bonm;

    /** 住所CD */
    @Column(length = 8, nullable = true, unique = false)
    public String adcd;

    /** 郵便番号注意書き */
    @Column(length = 3000, nullable = true, unique = false)
    public String zipnotes;

    /** 町名 */
    @Column(length = 192, nullable = true, unique = false)
    public String adr01;

    /** 番地ビル */
    @Column(length = 192, nullable = true, unique = false)
    public String adr02;

    /** TEL */
    @Column(length = 20, nullable = true, unique = false)
    public String telnum;

    /** FAX */
    @Column(length = 20, nullable = true, unique = false)
    public String fax;

    /** 業種中分類CD */
    @Column(precision = 5, nullable = true, unique = false)
    public Short midbizcd;

    /** URL */
    @Column(length = 384, nullable = true, unique = false)
    public String url;

    /** 備考 */
    @Column(length = 1000, nullable = true, unique = false)
    public String remarks;

    /** 顧客ランク ヒアリング情報 */
    @Column(precision = 5, nullable = true, unique = true)
    public Short cltrank;

    /** 年間予算 ヒアリング情報 */
    @Column(precision = 131089, nullable = true, unique = false)
    public BigDecimal yearestimate;

    /** 発注単価 ヒアリング情報 */
    @Column(precision = 131089, nullable = true, unique = false)
    public BigDecimal ordunitprice;

    /** 採用単価 ヒアリング情報 */
    @Column(precision = 131089, nullable = true, unique = false)
    public BigDecimal emptylyunitprice;

    /** 応募単価 ヒアリング情報 */
    @Column(precision = 131089, nullable = true, unique = false)
    public BigDecimal applyunitprice;

    /** 掲載頻度 ヒアリング情報 */
    @Column(length = 20, nullable = true, unique = false)
    public String pubfrequency;

    /** 利用媒体 ヒアリング情報 */
    @Column(length = 20, nullable = true, unique = false)
    public String useprod;

    /** 採用課題 ヒアリング情報 */
    @Column(length = 3000, nullable = true, unique = false)
    public String emptyloyproblem;

    /** その他定性情報 ヒアリング情報 */
    @Column(length = 3000, nullable = true, unique = false)
    public String etcqualitativeinfo;

    /** 担当者姓 */
    @Column(length = 192, nullable = true, unique = false)
    public String cltcgnmlast;

    /** 担当者名 */
    @Column(length = 192, nullable = true, unique = false)
    public String cltcgnmfirst;

    /** 担当者カナ姓 */
    @Column(length = 192, nullable = true, unique = false)
    public String cltcgkanalast;

    /** 担当者カナ名 */
    @Column(length = 192, nullable = true, unique = false)
    public String cltcgkanafirst;

    /** 担当者所属部署 */
    @Column(length = 192, nullable = true, unique = false)
    public String cltcgdep;

    /** 担当者役職 */
    @Column(length = 192, nullable = true, unique = false)
    public String cltcgpos;

    /** 担当者メール */
    @Column(length = 360, nullable = true, unique = false)
    public String cltcgmail;

    /** 代理店CD */
    @Column(length = 9, nullable = true, unique = true)
    public String agtcd;

    /** 非表示フラグ true:非表示、false:表示 */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean hideflg;

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

    /** magt関連プロパティ */
    @OneToOne(mappedBy = "mclt")
    public Magt magt;

    /** mcltxclaim関連プロパティ */
    @OneToOne
    @JoinColumn(name = "cltid", referencedColumnName = "cltid")
    public Mcltxclaim mcltxclaim;

    /** mcltxflg関連プロパティ */
    @OneToOne
    @JoinColumn(name = "cltid", referencedColumnName = "cltid")
    public Mcltxflg mcltxflg;

    /** mho関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "hoid", referencedColumnName = "hoid")
    public Mho mho;

    /** mcltemp関連プロパティ */
    @OneToOne(mappedBy = "mclt")
    public Mcltemp mcltemp;

    /** mcltrank関連プロパティ */
    @OneToOne(mappedBy = "mclt")
    public Mcltrank mcltrank;

    /** mcltstshisList関連プロパティ */
    @OneToMany(mappedBy = "mclt")
    public List<Mcltstshis> mcltstshisList;

    /** mclttransmngList関連プロパティ */
    @OneToMany(mappedBy = "mclt")
    public List<Mclttransmng> mclttransmngList;

    /** mcltxhocgList関連プロパティ */
    @OneToMany(mappedBy = "mclt")
    public List<Mcltxhocg> mcltxhocgList;

    /** mvisitplaceList関連プロパティ */
    @OneToMany(mappedBy = "mclt")
    public List<Mvisitplace> mvisitplaceList;

    /** tapproachdtlList関連プロパティ */
    @OneToMany(mappedBy = "mclt")
    public List<Tapproachdtl> tapproachdtlList;

    /** tcomcompeslttl関連プロパティ */
    @OneToOne(mappedBy = "mclt")
    public Tcomcompeslttl tcomcompeslttl;

    /** tcomppubrltList関連プロパティ */
    @OneToMany(mappedBy = "mclt")
    public List<Tcomppubrlt> tcomppubrltList;

    /** tslempList関連プロパティ */
    @OneToMany(mappedBy = "mclt")
    public List<Tslemp> tslempList;

    /** tslmtrlList関連プロパティ */
    @OneToMany(mappedBy = "mclt")
    public List<Tslmtrl> tslmtrlList;

}
