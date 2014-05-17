package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MclttransmngConstants;

/**
 * Mclttransmng エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mclttransmng")
public class Mclttransmng extends MclttransmngBase implements MclttransmngConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
