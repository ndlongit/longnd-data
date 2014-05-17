package jp.co.inte.crm.web.dto;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;

import jp.co.inte.crm.common.entity.Mclt;
import jp.co.inte.crm.common.entity.Mslmtrlxflg;

public class TslmtrlDto {

    /** 売上ネタID "N"+yy+連番8桁 */
    public String slmtrlid;

    /** 顧客ID */
    public String cltid;

    /** 提案ステータス区分 失注、提案、受注 */
    public Short offerstskbn;

    /** ヨミ区分 未提案、Dヨミ、Cヨミ、Bヨミ、Aヨミ */
    public Short expectkbn;

    /** 売上金額 */
    public BigDecimal slamount;

    /** 商品CD プルダウン表示 */
    public String prodcd;

    /** 提案日 */
    public Date offerdate;

    /** 提案活動方法区分 飛込み、訪問、電話、FAX、メール　DM・資料送付、来社、会食、その他 */
    public Short offeractmethodkbn;

    /** クロージング日 */
    public Date closingdt;

    /** クロージング活動方法区分 飛込み、訪問、電話、FAX、メール　DM・資料送付、来社、会食、その他 */
    public Short closingactmethodkbn;

    /** 掲載開始日 */
    public Date pubstartdt;

    /** 計上開始週 */
    public BigDecimal apprstartweek;

    /** 掲載期間 */
    public Short pubterm;

    /** 掲載終了日 */
    public Date pubenddt;

    /** 分割区分 */
    public Short divisionkbn;

    /** 計上区分 */
    public Short apprdatekbn;

    /** 評価区分 an、DODA */
    public Short valuekbn;

    /** コメント */
    public String coment;

    /** 担当営業CD */
    public String cgempcd;

    /** 担当営業区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short cgkbn;

    /** 担当営業名 */
    public String cgempnm;

    /** mclt関連プロパティ */
    public Mclt mclt;

    /** mslmtrlxflg関連プロパティ */
    public Mslmtrlxflg mslmtrlxflg;

    public BigDecimal countTotal;

    public BigDecimal countExisted;

    public BigDecimal countOrginal;
}