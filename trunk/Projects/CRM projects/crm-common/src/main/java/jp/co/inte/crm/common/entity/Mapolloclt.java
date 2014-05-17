package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MapollocltConstants;

/**
 * Mapolloclt エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mapolloclt")
public class Mapolloclt extends MapollocltBase implements MapollocltConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
