package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * クレーム
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class TclaimBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** クレームID yy+連番16桁 */
    @Id
    @Column(length = 18, nullable = false, unique = true)
    public String claimid;

    /** 発生日 */
    @Column(nullable = true, unique = false)
    public Date occurdt;

    /** クレームステータス区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short claimstskbn;

    /** コンタクト形態区分 */
    @Column(precision = 5, nullable = true, unique = false)
    public Short contactformkbn;

    /** 先方担当者名 */
    @Column(length = 1000, nullable = true, unique = false)
    public String hocgnm;

    /** 対応担当者名 */
    @Column(length = 1000, nullable = true, unique = false)
    public String answercgnm;

    /** クレーム内容 */
    @Column(length = 4000, nullable = true, unique = false)
    public String claimcontents;

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

    /** mcltxclaimList関連プロパティ */
    @OneToMany(mappedBy = "tclaim")
    public List<Mcltxclaim> mcltxclaimList;

}
