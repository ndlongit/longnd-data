package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.McltrankDto;

/**
 * <pre>
 * mcltrank(顧客ランクマスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface McltrankService {

    /**
     * 顧客ランクマスタ一覧を返す。
     * 
     * @param ctrldepno 統括部No
     * @return List<McltrankDto> 顧客ランクマスタ一覧
     */
    List<McltrankDto> getMclrankList(String ctrldepno);

    /**
     * 顧客ランクマスタを登録する。
     * 
     * @param mcltrank 顧客ランクマスタ
     * @return 更新した行数
     */
    int regist(McltrankDto mcltrank);

}
