package jp.co.inte.crm.common.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * APOLLO顧客マスタ
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MapollocltBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** APOLLOID */
    @Column(length = 7, nullable = false, unique = false)
    public String apolloid;

    /** 媒体ステータス */
    @Column(length = 255, nullable = true, unique = false)
    public String mediasts;

    /** 紹介ステータス */
    @Column(length = 255, nullable = true, unique = false)
    public String itdsts;

    /** インダストリ */
    @Column(length = 255, nullable = true, unique = false)
    public String industry;

    /** APOLLO業種大 */
    @Column(length = 255, nullable = true, unique = false)
    public String biztypenm;

    /** APOLLO業種小 */
    @Column(length = 255, nullable = true, unique = false)
    public String biznm;

    /** 出稿ランク独自 */
    @Column(length = 255, nullable = true, unique = false)
    public String lank;

    /** 出稿ランク独自変動 */
    @Column(length = 255, nullable = true, unique = false)
    public String lankvar;

    /** DODA紹介担当 */
    @Column(length = 255, nullable = true, unique = false)
    public String itdstsra;

    /** DODA媒体担当 */
    @Column(length = 255, nullable = true, unique = false)
    public String mediastsrc;

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

    /** mho関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "apolloid", referencedColumnName = "apolloid")
    public Mho mho;

}
