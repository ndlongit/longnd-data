package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 営業担当者マスタ
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class McltempBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** インテリジェンス社員番号 */
    @Column(length = 20, nullable = false, unique = false)
    public String inteempno;

    /** 顧客ID */
    @Column(length = 10, nullable = false, unique = true)
    public String cltid;

    /** 開始日 */
    @Column(nullable = true, unique = false)
    public Date strdt;

    /** 終了日 */
    @Column(nullable = true, unique = false)
    public Date enddt;

    /** 担当区分 '010:主担当　020:副担当 */
    @Column(length = 3, nullable = true, unique = false)
    public String cgkbn;

    /** 申請日 */
    @Column(nullable = true, unique = false)
    public Date applydt;

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

    /** mclt関連プロパティ */
    @OneToOne
    @JoinColumn(name = "cltid", referencedColumnName = "cltid")
    public Mclt mclt;

    /** mintrausr関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "inteempno", referencedColumnName = "inteempno")
    public Mintrausr mintrausr;

    /** mclttransmngList関連プロパティ */
    @OneToMany(mappedBy = "mcltemp")
    public List<Mclttransmng> mclttransmngList;

}
