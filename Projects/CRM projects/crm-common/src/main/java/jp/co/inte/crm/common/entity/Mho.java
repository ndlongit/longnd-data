package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MhoConstants;

/**
 * Mho エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mho")
public class Mho extends MhoBase implements MhoConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
