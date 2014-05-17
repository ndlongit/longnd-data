package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MslmtrlxflgConstants;

/**
 * Mslmtrlxflg エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mslmtrlxflg")
public class Mslmtrlxflg extends MslmtrlxflgBase implements MslmtrlxflgConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
