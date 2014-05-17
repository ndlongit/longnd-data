package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TcomcompeslttlConstants;

/**
 * Tcomcompeslttl エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tcomcompeslttl")
public class Tcomcompeslttl extends TcomcompeslttlBase implements TcomcompeslttlConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
