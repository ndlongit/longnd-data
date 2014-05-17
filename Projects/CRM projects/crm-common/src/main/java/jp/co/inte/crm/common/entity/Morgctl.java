package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MorgctlConstants;

/**
 * Morgctl エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "morgctl")
public class Morgctl extends MorgctlBase implements MorgctlConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
