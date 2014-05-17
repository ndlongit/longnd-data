package jp.co.inte.crm.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import jp.co.inte.cspfw.entity.AbstractFwEntity;

/**
 * 
 * CRM-System BaseEntity Class.
 * 
 */
@MappedSuperclass
public class CrmBaseEntity extends AbstractFwEntity {

    /**
     * レコード作成日時.
     */
    @Column(nullable = false, unique = false)
    public Timestamp crttimestamp;

    /**
     * レコード作成利用者コード.
     */
    @Column(length = 9, nullable = false, unique = false)
    public String crtusrcd;

    /**
     * レコード作成利用者ID.
     */
    @Column(length = 20, nullable = false, unique = false)
    public String crtusrid;

    /**
     * 登録プログラムID.
     */
    @Column(length = 128, nullable = false, unique = false)
    public String crtpgid;

    /**
     * レコード更新日時.
     */
    @Column(nullable = false, unique = false)
    @Version
    public Timestamp updtimestamp;

    /**
     * レコード更新利用者コード.
     */
    @Column(length = 9, nullable = false, unique = false)
    public String updusrcd;

    /**
     * レコード更新利用者ID.
     */
    @Column(length = 20, nullable = false, unique = false)
    public String updusrid;

    /**
     * 更新プログラムID.
     */
    @Column(length = 128, nullable = false, unique = false)
    public String updpgid;

    /**
     * 登録者IDのフィールド名を取得.
     * 
     * <pre>
     * 　デフォルト値"insertUser"から変更する場合は、オーバーライドしてください。
     * </pre>
     * 
     * @return 登録者IDのフィールド名
     */
    @Override
    public String getInsertUserFieldName() {
        return "crtusrid";
    }

    /**
     * 登録システム／機能コードのフィールド名を取得.
     * 
     * <pre>
     * 　デフォルト値"insertPgm"から変更する場合は、オーバーライドしてください。
     * </pre>
     * 
     * @return 登録システム／機能コードのフィールド名
     */
    @Override
    public String getInsertPgmFieldName() {
        return "crtpgid";
    }

    /**
     * 登録日時のフィールド名を取得.
     * 
     * <pre>
     * 　デフォルト値"insertDate"から変更する場合は、オーバーライドしてください。
     * </pre>
     * 
     * @return 登録日時IDのフィールド名
     */
    @Override
    public String getInsertDateFieldName() {
        return "crttimestamp";
    }

    /**
     * 更新者IDのフィールド名を取得.
     * 
     * <pre>
     * 　デフォルト値"updateUser"から変更する場合は、オーバーライドしてください。
     * </pre>
     * 
     * @return 更新者IDのフィールド名
     */
    @Override
    public String getUpdateUserFieldName() {
        return "updusrid";
    }

    /**
     * 更新システム／機能コードのフィールド名を取得.
     * 
     * <pre>
     * 　デフォルト値"updatePgm"から変更する場合は、オーバーライドしてください。
     * </pre>
     * 
     * @return 更新システム／機能コードのフィールド名
     */
    @Override
    public String getUpdatePgmFieldName() {
        return "updpgid";
    }

    /**
     * 更新日時のフィールド名を取得.
     * 
     * <pre>
     * 　デフォルト値"updateDate"から変更する場合は、オーバーライドしてください。
     * </pre>
     * 
     * @return 更新日時IDのフィールド名
     */
    @Override
    public String getUpdateDateFieldName() {
        return "updtimestamp";
    }
}
