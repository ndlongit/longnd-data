package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TapproachdtlDto;
import jp.co.inte.crm.web.dto.TapproachdtlParamDto;

/**
 * <pre>
 * tapproachdtlDto(接触詳細)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TapproachdtlService {

    /**
     * 接触詳細情報
     * 接触詳細と紐づく接触予定履歴と次回予定を返却する。
     * 
     * @param approachid 接触ID
     * @param cltid 顧客ID
     * @return TapproachdtlDto 接触詳細情報
     */
    TapproachdtlDto getApproachdtlList(String approachid, String cltid);

    /**
     * 接触予定一覧
     * 接触予定・履歴一覧を返却する
     * ※検索条件で検索した接触詳細に紐づく履歴一覧を返却する。
     * 
     * @param tapproachdtlParamDto 検索条件
     * @return List<TapproachdtlDto> 接触予定一覧
     */
    List<TapproachdtlDto> getApproachplanhisList(TapproachdtlParamDto tapproachdtlParamDto);

    /**
     * 接触詳細、接触予定・履歴を登録する。
     * 
     * @param tapproachdtlDto 接触詳細
     * @return 更新した行数
     */
    int regist(TapproachdtlDto tapproachdtlDto);

    /**
     * 接触詳細、接触予定・履歴を削除する。
     * 
     * @param tapproachdtlDto 接触詳細
     * @return 更新した行数
     */
    int delete(TapproachdtlDto tapproachdtlDto);

}
