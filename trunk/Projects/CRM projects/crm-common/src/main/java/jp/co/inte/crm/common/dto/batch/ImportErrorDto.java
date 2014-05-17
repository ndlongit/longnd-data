package jp.co.inte.crm.common.dto.batch;

import jp.co.inte.crm.common.dto.CrmBaseDto;
import jp.co.inte.crm.common.entity.CrmBaseEntity;

/**
 * 取込除外となった情報を保持するDTO
 * *
 */
public class ImportErrorDto extends CrmBaseDto {

    /** シリアルバージョンID. */
    private static final long serialVersionUID = 1L;

    /** メッセージ */
    public String message;

    /** 取込除外となったエラーのレコード */
    public CrmBaseEntity record;
}
