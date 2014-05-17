package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McltstshisConstants;

/**
 * Mcltstshis エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mcltstshis")
public class Mcltstshis extends McltstshisBase implements McltstshisConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
