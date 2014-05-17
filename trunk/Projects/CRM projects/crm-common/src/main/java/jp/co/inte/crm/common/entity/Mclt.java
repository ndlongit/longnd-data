package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McltConstants;

/**
 * Mclt エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mclt")
public class Mclt extends McltBase implements McltConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
