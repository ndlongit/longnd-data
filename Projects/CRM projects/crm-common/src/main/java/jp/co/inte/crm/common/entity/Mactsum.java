package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MactsumConstants;

/**
 * Mactsum エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mactsum")
public class Mactsum extends MactsumBase implements MactsumConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
