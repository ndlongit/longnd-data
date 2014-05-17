package jp.co.inte.crm.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 顧客フラグマスタ
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/13 17:07:19")
public class McltflgBase extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** フラグNo */
    @Id
    @Column(precision = 2, nullable = false, unique = true)
    public BigDecimal flgno;

    /** フラグ名 */
    @Column(length = 5, nullable = true, unique = false)
    public String flgnm;

    /** 統括別割振り枠 文字数？ */
    @Column(length = 15, nullable = false, unique = false)
    public String ctrlassignframe;

    /** 開始日 */
    @Column(nullable = false, unique = false)
    public Date strdt;

    /** 終了予定日 */
    @Column(nullable = false, unique = false)
    public Date enddt;

    /** 更新日 */
    @Column(nullable = true, unique = false)
    public Date updatedt;

    /** レコード作成日時 */
    @Column(nullable = true, unique = false)
    public Timestamp crttimestamp;

    /** レコード作成利用者コード */
    @Column(length = 9, nullable = true, unique = false)
    public String crtusrcd;

    /** レコード作成利用者ID */
    @Column(length = 20, nullable = true, unique = false)
    public String crtusrid;

    /** 登録プログラムID */
    @Column(length = 128, nullable = true, unique = false)
    public String crtpgid;

    /** レコード更新日時 */
    @Column(nullable = true, unique = false)
    public Timestamp updtimestamp;

    /** レコード更新利用者コード */
    @Column(length = 9, nullable = true, unique = false)
    public String updusrcd;

    /** レコード更新利用者ID */
    @Column(length = 20, nullable = true, unique = false)
    public String updusrid;

    /** 更新プログラムID */
    @Column(length = 128, nullable = true, unique = false)
    public String updpgid;

    /** mcltflgxdept関連プロパティ */
    @OneToOne
    @JoinColumn(name = "flgno", referencedColumnName = "flgno")
    public Mcltflgxdept mcltflgxdept;

    /** mcltxflgList関連プロパティ */
    @OneToMany(mappedBy = "mcltflg")
    public List<Mcltxflg> mcltxflgList;

}
