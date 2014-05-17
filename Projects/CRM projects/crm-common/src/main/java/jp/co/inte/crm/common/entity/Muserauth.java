package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MuserauthConstants;

/**
 * Msysusr エンティティ ＜利用者拡張用＞
 */
@Entity
// @Table(schema = "crm", name = "muserauth")
@Table(schema = "crm", name = "msysusr")
public class Muserauth extends MuserauthBase implements MuserauthConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
