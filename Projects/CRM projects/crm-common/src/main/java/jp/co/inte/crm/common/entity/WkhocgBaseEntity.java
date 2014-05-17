package jp.co.inte.crm.common.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * WK法人担当者取込
 * 
 */
@MappedSuperclass
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl" },
        date = "2014/05/15 21:28:24")
public class WkhocgBaseEntity extends CrmBaseEntity implements Serializable {

    /** シリアル・バージョンID */
    private static final long serialVersionUID = 1L;

    /** 取込No 意味なし連番 */
    @Id
    @Column(precision = 10, nullable = false, unique = true)
    public Integer importno;

    /** 法人ID */
    @Column(length = 10, nullable = false, unique = false)
    public String hoid;

    /** 法人担当者姓 */
    @Column(length = 192, nullable = false, unique = false)
    public String hocgnmlast;

    /** 法人担当者名 */
    @Column(length = 192, nullable = false, unique = false)
    public String hocgnmfirst;

    /** 法人担当者カナ姓 */
    @Column(length = 192, nullable = false, unique = false)
    public String hocgkanalast;

    /** 法人担当者カナ名 */
    @Column(length = 192, nullable = false, unique = false)
    public String hocgkanafirst;

    /** 所属部署 */
    @Column(length = 192, nullable = true, unique = false)
    public String post;

    /** 役職 */
    @Column(length = 192, nullable = true, unique = false)
    public String position;

    /** 電話番号 */
    @Column(length = 20, nullable = true, unique = false)
    public String tel;

    /** TEL携帯 */
    @Column(length = 20, nullable = true, unique = false)
    public String cellphone;

    /** メール */
    @Column(length = 360, nullable = true, unique = false)
    public String mail;

    /** FAX */
    @Column(length = 20, nullable = true, unique = false)
    public String fax;

    /** 番地ビル */
    @Column(length = 192, nullable = true, unique = false)
    public String adr02;

    /** 住所CD */
    @Column(length = 8, nullable = true, unique = false)
    public String adcd;
}
