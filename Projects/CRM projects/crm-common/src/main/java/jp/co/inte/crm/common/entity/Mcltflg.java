package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McltflgConstants;

/**
 * Mcltflg エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mcltflg")
public class Mcltflg extends McltflgBase implements McltflgConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}