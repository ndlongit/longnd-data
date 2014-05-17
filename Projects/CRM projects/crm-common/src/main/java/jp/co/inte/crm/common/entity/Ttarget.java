package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TtargetConstants;

/**
 * Ttarget エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "ttarget")
public class Ttarget extends TtargetBase implements TtargetConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
