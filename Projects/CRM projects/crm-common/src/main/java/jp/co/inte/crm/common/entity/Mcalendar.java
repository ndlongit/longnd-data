package jp.co.inte.crm.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import jp.co.inte.crm.common.entity.constants.McalendarConstants;

/**
 * Mcalendar エンティティ ＜利用者拡張用＞
 */
@Entity
@Table(schema = "crm", name = "mcalendar")
public class Mcalendar extends McalendarBase implements McalendarConstants {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

}
