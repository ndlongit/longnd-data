package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.McltempDto;

/**
 * <pre>
 * mcltemp(営業担当者マスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface McltempService {

    /**
     * 営業担当者一覧を返す。
     * 
     * @param cltid 顧客ID
     * @return List<McltempDto> 営業担当者一覧
     */
    List<McltempDto> getCustStaffList(String cltid);

    /**
     * 営業担当情報を返す
     * 
     * @param cltid 顧客ID
     * @return McltempDto 営業担当情報
     */
    McltempDto findById(String cltid);

    /**
     * 営業担当者登録
     * 営業担当情報を登録する。
     * 
     * @param mcltempDto 営業担当情報
     * @return 更新した行数
     */
    int regist(McltempDto mcltempDto);

}
