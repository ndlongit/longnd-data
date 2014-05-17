package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TplanmConstants;

/**
 * Tplanm エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tplanm")
public class Tplanm extends TplanmBase implements TplanmConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
