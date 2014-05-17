package jp.co.inte.crm.batch.service.cpt;

import jp.co.inte.crm.common.dto.batch.cpt.SCPTD01002Dto;

/**
 * 法人担当者取込ロジック
 * 
 */
public interface SCPTD01002Service {

    /**
     * 取込処理
     * 
     * @param scptd01002Dto 法人担当者取込DTO
     * @return int 処理結果
     */
    int importMain(SCPTD01002Dto scptd01002Dto);
}
