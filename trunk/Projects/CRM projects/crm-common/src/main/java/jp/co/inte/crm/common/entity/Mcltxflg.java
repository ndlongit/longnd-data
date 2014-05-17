package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McltxflgConstants;

/**
 * Mcltxflg エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mcltxflg")
public class Mcltxflg extends McltxflgBase implements McltxflgConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
