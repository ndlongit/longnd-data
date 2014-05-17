package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MsysusrConstants;

/**
 * Msysusr エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "msysusr")
public class Msysusr extends MsysusrBase implements MsysusrConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
