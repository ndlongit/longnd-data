package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * 接触詳細
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class TapproachdtlBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 接触ID "AP"+yy+連番8桁 */
    @Id
    @Column(length = 12, nullable = false, unique = true)
    public String approachid;

    /** 顧客ID */
    @Column(length = 10, nullable = false, unique = false)
    public String cltid;

    /** 活動詳細メモ 300文字 */
    @Column(length = 300, nullable = true, unique = false)
    public String actdtlmemo;

    /** 先方担当者 */
    @Column(length = 192, nullable = true, unique = false)
    public String hocgnm;

    /** 連絡先1 */
    @Column(length = 20, nullable = true, unique = false)
    public String tel1;

    /** 連絡先2 */
    @Column(length = 20, nullable = true, unique = false)
    public String tel2;

    /** 決裁権CD */
    @Column(precision = 5, nullable = true, unique = false)
    public Short dicisioncd;

    /** 媒体選定権CD */
    @Column(precision = 5, nullable = true, unique = false)
    public Short mediachoicecd;

    /** 在籍日時 */
    @Column(nullable = true, unique = false)
    public Timestamp attendeddatetime;

    /** 当社担当者 */
    @Column(length = 100, nullable = true, unique = false)
    public String cgnm;

    /** ニーズ区分 プルダウン */
    @Column(precision = 5, nullable = true, unique = false)
    public Short needskbn;

    /** 募集職種 */
    @Column(length = 50, nullable = true, unique = false)
    public String applyjobcategory;

    /** 募集人数 */
    @Column(length = 50, nullable = true, unique = false)
    public String applyjobcategorynum;

    /** 採用時期 */
    @Column(length = 50, nullable = true, unique = false)
    public String emptylytiming;

    /** 雇用形態 */
    @Column(length = 50, nullable = true, unique = false)
    public String emptyloymentform;

    /** 給与 */
    @Column(length = 50, nullable = true, unique = false)
    public String allowance;

    /** 勤務日時 */
    @Column(length = 50, nullable = true, unique = false)
    public String officehours;

    /** 応募資格採用条件 */
    @Column(length = 200, nullable = true, unique = false)
    public String emptylyqualification;

    /** 仕事内容 */
    @Column(length = 200, nullable = true, unique = false)
    public String jobcontents;

    /** 競合利用媒体 */
    @Column(length = 50, nullable = true, unique = false)
    public String compeuseproduct;

    /** 競合直近利用時期 */
    @Column(length = 30, nullable = true, unique = false)
    public String comperecentusetiming;

    /** 競合利用金額 */
    @Column(length = 30, nullable = true, unique = false)
    public String compeuseamount;

    /** 競合応募人数 */
    @Column(precision = 3, nullable = true, unique = false)
    public BigDecimal compeapplycount;

    /** 競合採用人数 */
    @Column(precision = 3, nullable = true, unique = false)
    public BigDecimal compeemptycount;

    /** 競合その他媒体利用について */
    @Column(length = 200, nullable = true, unique = false)
    public String compeetcproductuse;

    /** 制作依頼シート参照パス */
    @Column(length = 150, nullable = true, unique = false)
    public String pictreqsheetrefpath;

    /** クリエイティブ制作シート参照パス */
    @Column(length = 150, nullable = true, unique = false)
    public String crpicsheetrefpath;

    /** 写真参照パス */
    @Column(length = 150, nullable = true, unique = false)
    public String picrefpath;

    /** その他取材情報 */
    @Column(length = 500, nullable = true, unique = false)
    public String etcgatheredinfo;

    /** PV数 */
    @Column(precision = 3, nullable = true, unique = false)
    public BigDecimal pvcount;

    /** 応募数 */
    @Column(precision = 3, nullable = true, unique = false)
    public BigDecimal applycount;

    /** 面接数 */
    @Column(precision = 3, nullable = true, unique = false)
    public BigDecimal interviewcount;

    /** 採用人数 */
    @Column(precision = 3, nullable = true, unique = false)
    public BigDecimal emptynum;

    /** その他アフターフォロー情報 */
    @Column(length = 500, nullable = true, unique = false)
    public String etcafterfollow;

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

    /** tapproachplanhisList関連プロパティ */
    @OneToMany(mappedBy = "tapproachdtl")
    public List<Tapproachplanhis> tapproachplanhisList;

}
