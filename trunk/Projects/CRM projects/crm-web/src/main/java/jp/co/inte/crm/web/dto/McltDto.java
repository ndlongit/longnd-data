package jp.co.inte.crm.web.dto;

import java.math.BigDecimal;
import java.util.List;

import jp.co.inte.crm.common.dto.CrmBaseDto;
import jp.co.inte.crm.common.entity.Magt;
import jp.co.inte.crm.common.entity.Mcltemp;
import jp.co.inte.crm.common.entity.Mcltrank;
import jp.co.inte.crm.common.entity.Mcltstshis;
import jp.co.inte.crm.common.entity.Mclttransmng;
import jp.co.inte.crm.common.entity.Mcltxclaim;
import jp.co.inte.crm.common.entity.Mcltxflg;
import jp.co.inte.crm.common.entity.Mcltxhocg;
import jp.co.inte.crm.common.entity.Mho;
import jp.co.inte.crm.common.entity.Mvisitplace;
import jp.co.inte.crm.common.entity.Tapproachdtl;
import jp.co.inte.crm.common.entity.Tcomcompeslttl;
import jp.co.inte.crm.common.entity.Tcomppubrlt;
import jp.co.inte.crm.common.entity.Tslemp;
import jp.co.inte.crm.common.entity.Tslmtrl;

public class McltDto extends CrmBaseDto {

    /** 顧客ID "CC"+連番8桁 */
    public String cltid;

    /** 顧客CD */
    public String cltcd;

    /** 顧客ステータス区分 物理名はALと同一 */
    public String cltstscd;

    /** 法人ID */
    public String hoid;

    /** APOLLOID */
    public String apolloid;

    /** 商号表示区分 0:前株,1:後株,2:前有,3:後有,9:その他 */
    public Short biznmkbn;

    /** 顧客名 */
    public String cltnm;

    /** 顧客名カナ */
    public String cltkn;

    /** 支店名 */
    public String bonm;

    /** 住所CD */
    public String adcd;

    /** 郵便番号注意書き */
    public String zipnotes;

    /** 町名 */
    public String adr01;

    /** 番地ビル */
    public String adr02;

    /** TEL */
    public String telnum;

    /** FAX */
    public String fax;

    /** 業種中分類CD */
    public Short midbizcd;

    /** 業種中分類NAME */
    public String midbiznm;

    /** URL */
    public String url;

    /** 備考 */
    public String remarks;

    /** 顧客ランク ヒアリング情報 */
    public Short cltrank;

    /** 年間予算 ヒアリング情報 */
    public BigDecimal yearestimate;

    /** 発注単価 ヒアリング情報 */
    public BigDecimal ordunitprice;

    /** 採用単価 ヒアリング情報 */
    public BigDecimal emptylyunitprice;

    /** 応募単価 ヒアリング情報 */
    public BigDecimal applyunitprice;

    /** 掲載頻度 ヒアリング情報 */
    public String pubfrequency;

    /** 利用媒体 ヒアリング情報 */
    public String useprod;

    /** 採用課題 ヒアリング情報 */
    public String emptyloyproblem;

    /** その他定性情報 ヒアリング情報 */
    public String etcqualitativeinfo;

    /** 担当者姓 */
    public String cltcgnmlast;

    /** 担当者名 */
    public String cltcgnmfirst;

    /** 担当者カナ姓 */
    public String cltcgkanalast;

    /** 担当者カナ名 */
    public String cltcgkanafirst;

    /** 担当者所属部署 */
    public String cltcgdep;

    /** 担当者役職 */
    public String cltcgpos;

    /** 担当者メール */
    public String cltcgmail;

    /** 代理店CD */
    public String agtcd;

    /** 非表示フラグ true:非表示、false:表示 */
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
    public Magt magt;

    /** mcltxclaim関連プロパティ */
    public Mcltxclaim mcltxclaim;

    /** mcltxflg関連プロパティ */
    public Mcltxflg mcltxflg;

    /** mho関連プロパティ */
    public Mho mho;

    /** mcltemp関連プロパティ */
    public Mcltemp mcltemp;

    /** mcltrank関連プロパティ */
    public Mcltrank mcltrank;

    /** mcltstshisList関連プロパティ */
    public List<Mcltstshis> mcltstshisList;

    /** mclttransmngList関連プロパティ */
    public List<Mclttransmng> mclttransmngList;

    /** mcltxhocgList関連プロパティ */
    public List<Mcltxhocg> mcltxhocgList;

    /** mvisitplaceList関連プロパティ */
    public List<Mvisitplace> mvisitplaceList;

    /** tapproachdtlList関連プロパティ */
    public List<Tapproachdtl> tapproachdtlList;

    /** tcomcompeslttl関連プロパティ */
    public Tcomcompeslttl tcomcompeslttl;

    /** tcomppubrltList関連プロパティ */
    public List<Tcomppubrlt> tcomppubrltList;

    /** tslempList関連プロパティ */
    public List<Tslemp> tslempList;

    /** tslmtrlList関連プロパティ */
    public List<Tslmtrl> tslmtrlList;
}