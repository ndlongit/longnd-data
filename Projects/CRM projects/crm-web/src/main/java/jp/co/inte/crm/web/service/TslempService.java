package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TslempDto;
import jp.co.inte.crm.web.dto.TslempParamDto;

/**
 * <pre>
 * tslemp(営業売上明細)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TslempService {

    /**
     * 営業売上明細
     * 確定した売上実績一覧を返却する
     * 
     * @param tslempParamDto 検索条件
     * @return List<TslempDto> 営業売上明細
     */
    List<TslempDto> getStaffSalesList(TslempParamDto tslempParamDto);

}
