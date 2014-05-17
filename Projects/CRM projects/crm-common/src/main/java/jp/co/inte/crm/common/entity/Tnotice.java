package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TnoticeConstants;

/**
 * Tnotice エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tnotice")
public class Tnotice extends TnoticeBase implements TnoticeConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
