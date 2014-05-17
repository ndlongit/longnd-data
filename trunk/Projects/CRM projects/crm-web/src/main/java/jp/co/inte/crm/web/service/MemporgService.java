package jp.co.inte.crm.web.service;

import jp.co.inte.crm.web.dto.MemporgDto;

/**
 * <pre>
 * memporg(人事マスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface MemporgService {

    /**
     * 社員情報を返す
     * 
     * @param empcd 社員CD
     * @return Memporg 人事マスタ情報
     */
    MemporgDto findById(String empcd);

}
