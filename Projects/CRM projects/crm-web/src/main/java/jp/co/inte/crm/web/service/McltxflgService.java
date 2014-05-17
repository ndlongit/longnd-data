package jp.co.inte.crm.web.service;

import jp.co.inte.crm.web.dto.McltxflgDto;

/**
 * <pre>
 * mcltxflg(顧客フラグ紐付)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface McltxflgService {

    /**
     * 顧客フラグ紐付を登録
     * 
     * @param mcltxflgDto 顧客フラグ紐付
     * @return 更新した行数
     */
    int regist(McltxflgDto mcltxflgDto);

}
