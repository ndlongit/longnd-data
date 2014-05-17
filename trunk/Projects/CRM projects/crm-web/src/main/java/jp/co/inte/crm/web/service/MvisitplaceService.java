package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.MvisitplaceDto;

/**
 * <pre>
 * mvisitplace(訪問先マスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface MvisitplaceService {

    /**
     * 訪問先住所一覧を返す
     * 
     * @param cltid 顧客ID
     * @return List<MvisitplaceDto> 訪問先住所一覧
     */
    List<MvisitplaceDto> getVisitPlaceList(String cltid);

    /**
     * 訪問先詳細情報を返す。
     * 
     * @param cltcd 顧客CD
     * @return MvisitplaceDto 訪問先詳細情報
     */
    MvisitplaceDto findById(String cltcd);

    /**
     * 訪問先の情報を登録する。
     * 
     * @param mvisitplaceDto 訪問先の情報
     * @return 更新した行数
     */
    int regist(MvisitplaceDto mvisitplaceDto);

    /**
     * 訪問先の情報を削除する。
     * 
     * @param visitplaceno 訪問先No
     * @return 更新した行数
     */
    int delete(String visitplaceno);

}
