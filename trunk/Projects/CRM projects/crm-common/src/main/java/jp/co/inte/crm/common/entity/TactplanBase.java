package jp.co.inte.crm.common.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 活動予定
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class TactplanBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 活動予定ID "AC"+yy+連番7桁 */
    @Id
    @Column(length = 11, nullable = false, unique = true)
    public String tactplanid;

    /** インテリジェンス社員番号 */
    @Column(length = 20, nullable = false, unique = false)
    public String inteempno;

    /** 活動予定・振り返り */
    @Column(length = 300, nullable = true, unique = false)
    public String tactplanlookingback;

    /** 削除フラグ */
    @Column(length = 1, nullable = true, unique = false)
    public Boolean deleteflg;

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
    @ManyToOne
    @JoinColumn(name = "inteempno", referencedColumnName = "inteempno")
    public Mintrausr mintrausr;

}
