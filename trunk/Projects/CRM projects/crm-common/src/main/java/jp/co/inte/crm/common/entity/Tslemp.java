package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TslempConstants;

/**
 * Tslemp エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tslemp")
public class Tslemp extends TslempBase implements TslempConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
