package jp.co.inte.crm.web.service;

import jp.co.inte.crm.web.dto.McltxclaimDto;

/**
 * <pre>
 * mcltxclaim(顧客クレーム紐付)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface McltxclaimService {

    /**
     * 顧客クレーム紐付登録
     * 
     * @param cltclaimrltDto 顧客クレーム紐付
     * @return 更新した行数
     */
    int regist(McltxclaimDto cltclaimrltDto);

}
