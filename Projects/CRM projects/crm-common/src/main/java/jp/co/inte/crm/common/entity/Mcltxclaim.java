package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McltxclaimConstants;

/**
 * Mcltxclaim エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mcltxclaim")
public class Mcltxclaim extends McltxclaimBase implements McltxclaimConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
