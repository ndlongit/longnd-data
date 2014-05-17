package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MmtrlflgxdeptConstants;

/**
 * Mmtrlflgxdept エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mmtrlflgxdept")
public class Mmtrlflgxdept extends MmtrlflgxdeptBase implements MmtrlflgxdeptConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
