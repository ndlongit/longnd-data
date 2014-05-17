package jp.co.inte.crm.web.service;

import java.util.Date;
import java.util.List;

import jp.co.inte.crm.common.entity.Tslmtrl;
import jp.co.inte.crm.web.dto.TslmtrlDto;
import jp.co.inte.crm.web.dto.TslmtrlParamDto;

/**
 * <pre>
 * tslmtrl(売上ネタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TslmtrlService {

    /**
     * 売上ネタ詳細
     * 売上・ネタの詳細情報を返却する。
     * 顧客に紐づく情報(顧客名、支店名、顧客CD、CRM用顧客ID、顧客ステータス、業種、顧客ランク、フラグ1-12)も
     * 合わせて取得する。
     * 
     * @param slmtrlid 売上ネタID
     * @return TslmtrlDto 売上ネタ情報
     */
    TslmtrlDto findById(String slmtrlid);

    /**
     * 本日売上ネタ一覧
     * ホーム画面の本日の提案・クロージング　売上・ネタ一覧を返却する
     * 顧客に紐づく情報(顧客名、支店名、顧客CD、CRM用顧客ID、顧客ステータス、業種、顧客ランク、フラグ1-12)も
     * 合わせて取得する。
     * 
     * @param loginid ログインID
     * @param date 日付
     * @return List<TslmtrlDto> 売上ネタ情報一覧
     */
    List<TslmtrlDto> getTodayTslmtrlList(String loginid, Date date);

    /**
     * 本日組織売上ネタ一覧
     * ホーム画面(GL以上)の本日の提案・クロージング　売上・ネタ一覧を返却する
     * 顧客に紐づく情報(顧客名、支店名、顧客CD、CRM用顧客ID、顧客ステータス、業種、顧客ランク、フラグ1-12)も
     * 合わせて取得する。
     * 
     * @param ctrno ログインIDの組織CD(グループNo or 部門No or 統括部No)
     * @param date 日付
     * @return List<TslmtrlDto> 売上ネタ情報
     */
    List<TslmtrlDto> getTodayTslmtrlList(int ctrno, Date date);

    /**
     * 売上ネタ一覧を返却する
     * 
     * @param tslmtrlParamDto 検索条件パラメータ
     * @return List<TslmtrlDto> 売上ネタ情報
     */
    List<TslmtrlDto> getTslmtrlList(TslmtrlParamDto tslmtrlParamDto);

    /**
     * 売上・ネタ情報を登録する
     * 
     * @param tslmaterialDto 売上ネタ
     * @return 更新した行数を返す
     */
    int regist(TslmtrlDto tslmaterialDto);

    /**
     * 売上・ネタ情報を削除する
     * 
     * @param tslmaterialDto 売上ネタ
     * @return 更新した行数を返す
     */
    int delete(Tslmtrl tslmtrl);

}
