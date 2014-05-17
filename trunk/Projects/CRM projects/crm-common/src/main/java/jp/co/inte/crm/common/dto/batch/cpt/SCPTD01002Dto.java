package jp.co.inte.crm.common.dto.batch.cpt;

import java.util.List;

import jp.co.inte.crm.common.dto.CrmBaseDto;
import jp.co.inte.crm.common.dto.batch.ImportErrorDto;

/**
 * 法人担当者取込DTO
 * 
 */
public class SCPTD01002Dto extends CrmBaseDto {

    /** シリアルバージョンID. */
    private static final long serialVersionUID = 1L;

    /** 取込除外リスト */
    public List<ImportErrorDto> importErrorList;
}
