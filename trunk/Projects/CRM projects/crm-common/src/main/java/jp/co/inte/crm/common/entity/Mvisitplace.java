package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MvisitplaceConstants;

/**
 * Mvisitplace エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mvisitplace")
public class Mvisitplace extends MvisitplaceBase implements MvisitplaceConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
