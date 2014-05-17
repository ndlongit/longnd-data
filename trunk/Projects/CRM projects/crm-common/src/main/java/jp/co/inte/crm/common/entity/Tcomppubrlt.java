package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.TcomppubrltConstants;

/**
 * Tcomppubrlt エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "tcomppubrlt")
public class Tcomppubrlt extends TcomppubrltBase implements TcomppubrltConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
