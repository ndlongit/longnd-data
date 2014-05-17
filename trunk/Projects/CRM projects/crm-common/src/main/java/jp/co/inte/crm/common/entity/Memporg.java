package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MemporgConstants;

/**
 * Memporg エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "memporg")
public class Memporg extends MemporgBase implements MemporgConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
