package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McltempConstants;

/**
 * Mcltemp エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mcltemp")
public class Mcltemp extends McltempBase implements McltempConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
