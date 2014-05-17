package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MhocgConstants;

/**
 * Mhocg エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mhocg")
public class Mhocg extends MhocgBase implements MhocgConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
