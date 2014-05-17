package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MagtConstants;

/**
 * Magt エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "magt")
public class Magt extends MagtBase implements MagtConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
