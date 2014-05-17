package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TapproachdtlConstants;

/**
 * Tapproachdtl エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tapproachdtl")
public class Tapproachdtl extends TapproachdtlBase implements TapproachdtlConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
