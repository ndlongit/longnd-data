package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.MadrcdConstants;

/**
 * Madrcd エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "madrcd")
public class Madrcd extends MadrcdBase implements MadrcdConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
