package jp.co.inte.crm.web.service;

import jp.co.inte.crm.web.dto.McltxhocgDto;

/**
 * <pre>
 * coverhocltrlt(法人顧客担当者紐付)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface McltxhocgService {

    /**
     * 法人顧客担当者紐付を返す
     * 
     * @param cltid 顧客
     * @return Tslmaterial 売上ネタフラグ紐付
     */
    McltxhocgDto getMcltxhocgList(String cltid);

    /**
     * 法人顧客担当者紐付登録
     * 法人顧客担当者紐付を更新する。
     * 
     * @param mcltxhocgDto 法人顧客担当者紐付
     * @return 更新した行数
     */
    int regist(McltxhocgDto mcltxhocgDto);

}
