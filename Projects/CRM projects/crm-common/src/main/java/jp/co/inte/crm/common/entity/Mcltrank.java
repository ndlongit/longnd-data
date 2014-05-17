package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McltrankConstants;

/**
 * Mcltrank エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mcltrank")
public class Mcltrank extends McltrankBase implements McltrankConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
