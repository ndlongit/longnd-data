package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * システム利用者マスタ ALより取込
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MuserauthBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** システム利用者コード */
    @Column(length = 9, nullable = true, unique = false)
    public String sysusrno;

    /** 代理店コード */
    @Column(length = 9, nullable = false, unique = false)
    public String agtcd;

    /** 顧客コード */
    @Column(length = 9, nullable = true, unique = false)
    public String cltcd;

    /** GW契約コード */
    @Column(length = 9, nullable = true, unique = false)
    public String gwcontrcd;

    /** システム利用者ID */
    @Column(length = 20, nullable = true, unique = false)
    public String sysusrid;

    /** システム利用者パスワード */
    @Column(length = 50, nullable = true, unique = false)
    public String syspwd;

    /** パスワード有効期限 */
    @Column(nullable = true, unique = false)
    public Date pwdlmtdt;

    /** インテリジェンス社員番号 */
    @Column(length = 20, nullable = true, unique = true)
    public String inteempno;

    /** GWユーザー名 */
    @Column(length = 300, nullable = true, unique = false)
    public String gwusrnm;

    /** GWユーザーカナ名 */
    @Column(length = 300, nullable = true, unique = false)
    public String gwusrkn;

    /** GWユーザー役職名 */
    @Column(length = 192, nullable = true, unique = false)
    public String gwusrtitl;

    /** GWユーザーメールアドレス */
    @Column(length = 360, nullable = true, unique = false)
    public String gwusrmail;

    /** GWユーザー電話番号 */
    @Column(length = 20, nullable = true, unique = false)
    public String gwusrtel;

    /** GWユーザーFAX番号 */
    @Column(length = 20, nullable = true, unique = false)
    public String gwusrfax;

    /** GWユーザー部署名 */
    @Column(length = 300, nullable = true, unique = false)
    public String gwusrdeptnm;

    /** GWユーザー権限レベル */
    @Column(length = 1, nullable = true, unique = false)
    public String gwusrauthlvl;

    /** 無効フラグ */
    @Column(length = 1, nullable = true, unique = false)
    public String invaflg;

    /** 登録済フラグ */
    @Column(length = 1, nullable = true, unique = false)
    public String regfinflg;

    /** 初期ユーザーフラグ */
    @Column(length = 1, nullable = true, unique = false)
    public String iniusrflg;

    /** INTEユーザーフラグ */
    @Column(length = 1, nullable = true, unique = false)
    public String inteusrflg;

    /** 非表示フラグ */
    @Column(length = 1, nullable = true, unique = false)
    public String inviflg;

    // /** レコード作成日時 */
    // @Column(nullable = true, unique = false)
    // public Timestamp crttimestamp;
    //
    // /** レコード作成利用者コード */
    // @Column(length = 9, nullable = true, unique = false)
    // public String crtusrcd;
    //
    // /** レコード作成利用者ID */
    // @Column(length = 20, nullable = true, unique = false)
    // public String crtusrid;
    //
    // /** 登録プログラムID */
    // @Column(length = 128, nullable = true, unique = false)
    // public String crtpgid;
    //
    // /** レコード更新日時 */
    // @Column(nullable = true, unique = false)
    // public Timestamp updtimestamp;
    //
    // /** レコード更新利用者コード */
    // @Column(length = 9, nullable = true, unique = false)
    // public String updusrcd;
    //
    // /** レコード更新利用者ID */
    // @Column(length = 20, nullable = true, unique = false)
    // public String updusrid;
    //
    // /** 更新プログラムID */
    // @Column(length = 128, nullable = true, unique = false)
    // public String updpgid;

    /** mintrausr関連プロパティ */
    @OneToOne(mappedBy = "msysusr")
    public Mintrausr mintrausr;

}
