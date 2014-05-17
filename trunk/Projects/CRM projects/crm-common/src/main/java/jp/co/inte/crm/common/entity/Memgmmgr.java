package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MemgmmgrConstants;

/**
 * Memgmmgr エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "memgmmgr")
public class Memgmmgr extends MemgmmgrBase implements MemgmmgrConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
