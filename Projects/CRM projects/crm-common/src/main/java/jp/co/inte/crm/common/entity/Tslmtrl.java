package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TslmtrlConstants;

/**
 * Tslmtrl エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tslmtrl")
public class Tslmtrl extends TslmtrlBase implements TslmtrlConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
