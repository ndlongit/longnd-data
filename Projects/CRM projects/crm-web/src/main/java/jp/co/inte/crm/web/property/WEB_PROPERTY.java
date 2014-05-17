package jp.co.inte.crm.web.property;

import java.util.Date;

import javax.annotation.Generated;
import javax.annotation.Resource;

import jp.co.inte.cspfw.util.property.FwPropertiesManager;
import jp.co.inte.cspfw.util.property.FwPropertiesManagerFactory;
import jp.co.inte.cspfw.util.property.FwPropertyKeys;

/**
 * <pre>
 * 個別サイト用のプロパティに対応するクラスです.<br/>
 * propertiesファイルに定義を追加する場合は、このクラスにもenum定義を追加してください.
 * </pre>
 * 
 * @since 1.0
 */
@Generated(value = {"CspProperty-Enum-Gen",
        "jp.co.inte.cspfw.tools.GenerateCspPropertyKeys" },
        date = "Tue Aug 06 12:45:08 JST 2013")
public enum WEB_PROPERTY implements FwPropertyKeys {

    /** enum定義をこの位置に追加していってください. */

    /** ENUM定義 区切り(使用不可) */
    ZZZ_END_ZZZ(null);

    /**
     * コンストラクタ 引数のプロパティキーとenumの値を紐付ける
     * 
     * @param propkey プロパティーキー
     */
    private WEB_PROPERTY(String propkey) {
        this.propkey = propkey;
    }

    /**
     * 利用者がenumクラスでプロパティにアクセスできるように、FwPropertiesManagerをインジェクトし、enumにアクセサを定義します.
     */
    @Resource
    protected transient FwPropertiesManager<WEB_PROPERTY> fwPropertiesManager =
            FwPropertiesManagerFactory.getFactory().getManager(WEB_PROPERTY.class);

    /**
     * プロパティキー
     */
    private String propkey;

    /**
     * {@inheritDoc}
     */
    @Override
    public String key() {
        return this.propkey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String val() {
        return fwPropertiesManager.get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String val(String suffix) {
        return fwPropertiesManager.get(this.key() + suffix);
    }

    /**
     * プロパティの値を取得するメソッドです.<br/>
     * valメソッドを呼び出すのみです.
     * 
     * @return プロパティの値
     * @see WEB_PROPERTY#val()
     */
    @Override
    public String toString() {
        return this.val();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer toInteger() {
        return fwPropertiesManager.getInteger(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long toLong() {
        return fwPropertiesManager.getLong(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean toBoolean() {
        return fwPropertiesManager.getBoolean(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date toDate() {
        return fwPropertiesManager.getDate(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date toDatetime() {
        return fwPropertiesManager.getDatetime(this);
    }
}
