package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 法人ステータス履歴
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class MhostshisBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 法人ID */
    @Id
    @Column(length = 10, nullable = false, unique = false)
    public String hoid;

    /** 履歴ID */
    @Id
    @Column(precision = 2, nullable = false, unique = false)
    public BigDecimal historyid;

    /** 法人ステータス履歴区分 0:クローズ解除,1:クローズ */
    @Column(precision = 5, nullable = true, unique = false)
    public Short mhostshiskbn;

    /** 理由 */
    @Column(length = 100, nullable = true, unique = false)
    public String reason;

    /** 理由備考 */
    @Column(length = 300, nullable = true, unique = false)
    public String reasonrmks;

    /** まとめ先法人 */
    @Column(length = 9, nullable = true, unique = false)
    public String ajthocd;

    /** 申請者社員CD */
    @Column(length = 20, nullable = true, unique = false)
    public String reqempcd;

    /** 申請者名 */
    @Column(length = 192, nullable = true, unique = false)
    public String reqempnm;

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
    @JoinColumn(name = "hoid", referencedColumnName = "hocd")
    public Mho mho;

}
