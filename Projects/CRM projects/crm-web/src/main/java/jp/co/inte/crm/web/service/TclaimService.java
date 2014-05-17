package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TclaimDto;

/**
 * <pre>
 * tclaim(クレーム)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TclaimService {

    /**
     * クレーム一覧
     * クレーム一覧を返す。
     * 
     * @param cltid 顧客ID
     * @return List<TclaimDto> クレーム一覧
     */
    List<TclaimDto> getClaimList(String cltid);

    /**
     * クレーム登録
     * クレーム登録を行う。
     * 
     * @param tclaimDto クレーム
     * @return 更新した行数
     */
    int regist(TclaimDto tclaimDto);

    /**
     * クレーム削除
     * クレーム削除を行う。
     * 
     * @param tclaimDto クレーム
     * @return 更新した行数
     */
    int delete(TclaimDto tclaimDto);

}
