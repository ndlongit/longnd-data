package jp.co.inte.crm.web.service;

import jp.co.inte.crm.web.dto.MhostshisDto;

/**
 * <pre>
 * mhostshis(法人ステータス)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface MhostshisService {

    /**
     * 法人ステータス履歴情報を返す
     * 
     * @param hoid 法人ID
     * @return MhostshisDto 法人ステータス
     */
    MhostshisDto findById(String hoid);

    /**
     * 法人ステータス登録
     * 
     * @param mhostshisDto 法人ステータス
     * @return 更新した行数
     */
    int regist(MhostshisDto mhostshisDto);

}
