package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McltflgxdeptConstants;

/**
 * Mcltflgxdept エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mcltflgxdept")
public class Mcltflgxdept extends McltflgxdeptBase implements McltflgxdeptConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
