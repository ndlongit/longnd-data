package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TplanqConstants;

/**
 * Tplanq エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tplanq")
public class Tplanq extends TplanqBase implements TplanqConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
