package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MmtrlflgConstants;

/**
 * Mmtrlflg エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mmtrlflg")
public class Mmtrlflg extends MmtrlflgBase implements MmtrlflgConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}