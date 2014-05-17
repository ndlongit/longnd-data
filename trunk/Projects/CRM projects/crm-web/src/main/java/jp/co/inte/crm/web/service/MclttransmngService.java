package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.MclttransmngDto;
import jp.co.inte.crm.web.dto.MclttransmngParamDto;

/**
 * <pre>
 * mclttransmng(引継管理)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface MclttransmngService {

    /**
     * 顧客引継管理情報
     * 顧客の引継管理情報を返す
     * 
     * @param mclttransmngParamDto 検索条件
     * @return List<MclttransmngDto> 顧客の引継管理情報
     */
    List<MclttransmngDto> getStaffSalesList(MclttransmngParamDto mclttransmngParamDto);

    /**
     * 顧客引継管理情報
     * 顧客の引継管理情報を返す
     * 
     * @param mclttransmngDto 顧客の引継管理情報
     * @return 更新した行数
     */
    int regist(MclttransmngDto mclttransmngDto);

}
