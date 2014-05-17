package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MintrausrConstants;

/**
 * Mintrausr エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mintrausr")
public class Mintrausr extends MintrausrBase implements MintrausrConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
