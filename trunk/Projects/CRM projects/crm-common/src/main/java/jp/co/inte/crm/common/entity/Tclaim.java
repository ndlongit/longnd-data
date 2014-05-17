package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TclaimConstants;

/**
 * Tclaim エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tclaim")
public class Tclaim extends TclaimBase implements TclaimConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
