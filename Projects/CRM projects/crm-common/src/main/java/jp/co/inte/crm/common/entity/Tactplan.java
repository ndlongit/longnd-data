package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TactplanConstants;

/**
 * Tactplan エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tactplan")
public class Tactplan extends TactplanBase implements TactplanConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
