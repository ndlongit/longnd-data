package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TapproachplanhisConstants;

/**
 * Tapproachplanhis エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tapproachplanhis")
public class Tapproachplanhis extends TapproachplanhisBase implements TapproachplanhisConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
