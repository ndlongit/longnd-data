package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.WkhocgConstants;

/**
 * Wkhocg エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "wkhocg")
public class WkhocgEntity extends WkhocgBaseEntity implements WkhocgConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
