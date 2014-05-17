package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MhostshisConstants;

/**
 * Mhostshis エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mhostshis")
public class Mhostshis extends MhostshisBase implements MhostshisConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
