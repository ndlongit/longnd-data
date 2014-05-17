package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MintrausrsecConstants;

/**
 * Mintrausrsec エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mintrausrsec")
public class Mintrausrsec extends MintrausrsecBase implements MintrausrsecConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
