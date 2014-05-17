package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McltxhocgConstants;

/**
 * Mcltxhocg エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mcltxhocg")
public class Mcltxhocg extends McltxhocgBase implements McltxhocgConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
