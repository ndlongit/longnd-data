package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tslemp;
import jp.co.inte.crm.common.entity.names.McltNames._McltNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Tslemp}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class TslempBaseNames {

    /**
     * ordcdのプロパティ名を返します。
     * 
     * @return ordcdのプロパティ名
     */
    public static PropertyName<Integer> ordcd() {
        return new PropertyName<Integer>("ordcd");
    }

    /**
     * itemcdのプロパティ名を返します。
     * 
     * @return itemcdのプロパティ名
     */
    public static PropertyName<Integer> itemcd() {
        return new PropertyName<Integer>("itemcd");
    }

    /**
     * cltcdのプロパティ名を返します。
     * 
     * @return cltcdのプロパティ名
     */
    public static PropertyName<String> cltcd() {
        return new PropertyName<String>("cltcd");
    }

    /**
     * setprodnmのプロパティ名を返します。
     * 
     * @return setprodnmのプロパティ名
     */
    public static PropertyName<String> setprodnm() {
        return new PropertyName<String>("setprodnm");
    }

    /**
     * prodkbnのプロパティ名を返します。
     * 
     * @return prodkbnのプロパティ名
     */
    public static PropertyName<Short> prodkbn() {
        return new PropertyName<Short>("prodkbn");
    }

    /**
     * amountのプロパティ名を返します。
     * 
     * @return amountのプロパティ名
     */
    public static PropertyName<BigDecimal> amount() {
        return new PropertyName<BigDecimal>("amount");
    }

    /**
     * orddtのプロパティ名を返します。
     * 
     * @return orddtのプロパティ名
     */
    public static PropertyName<Date> orddt() {
        return new PropertyName<Date>("orddt");
    }

    /**
     * apprdのプロパティ名を返します。
     * 
     * @return apprdのプロパティ名
     */
    public static PropertyName<Date> apprd() {
        return new PropertyName<Date>("apprd");
    }

    /**
     * apprmのプロパティ名を返します。
     * 
     * @return apprmのプロパティ名
     */
    public static PropertyName<String> apprm() {
        return new PropertyName<String>("apprm");
    }

    /**
     * apprwのプロパティ名を返します。
     * 
     * @return apprwのプロパティ名
     */
    public static PropertyName<Short> apprw() {
        return new PropertyName<Short>("apprw");
    }

    /**
     * pubstartdtのプロパティ名を返します。
     * 
     * @return pubstartdtのプロパティ名
     */
    public static PropertyName<Date> pubstartdt() {
        return new PropertyName<Date>("pubstartdt");
    }

    /**
     * cltempidのプロパティ名を返します。
     * 
     * @return cltempidのプロパティ名
     */
    public static PropertyName<String> cltempid() {
        return new PropertyName<String>("cltempid");
    }

    /**
     * agtcdのプロパティ名を返します。
     * 
     * @return agtcdのプロパティ名
     */
    public static PropertyName<String> agtcd() {
        return new PropertyName<String>("agtcd");
    }

    /**
     * crttimestampのプロパティ名を返します。
     * 
     * @return crttimestampのプロパティ名
     */
    public static PropertyName<Timestamp> crttimestamp() {
        return new PropertyName<Timestamp>("crttimestamp");
    }

    /**
     * crtusrcdのプロパティ名を返します。
     * 
     * @return crtusrcdのプロパティ名
     */
    public static PropertyName<String> crtusrcd() {
        return new PropertyName<String>("crtusrcd");
    }

    /**
     * crtusridのプロパティ名を返します。
     * 
     * @return crtusridのプロパティ名
     */
    public static PropertyName<String> crtusrid() {
        return new PropertyName<String>("crtusrid");
    }

    /**
     * crtpgidのプロパティ名を返します。
     * 
     * @return crtpgidのプロパティ名
     */
    public static PropertyName<String> crtpgid() {
        return new PropertyName<String>("crtpgid");
    }

    /**
     * updtimestampのプロパティ名を返します。
     * 
     * @return updtimestampのプロパティ名
     */
    public static PropertyName<Timestamp> updtimestamp() {
        return new PropertyName<Timestamp>("updtimestamp");
    }

    /**
     * updusrcdのプロパティ名を返します。
     * 
     * @return updusrcdのプロパティ名
     */
    public static PropertyName<String> updusrcd() {
        return new PropertyName<String>("updusrcd");
    }

    /**
     * updusridのプロパティ名を返します。
     * 
     * @return updusridのプロパティ名
     */
    public static PropertyName<String> updusrid() {
        return new PropertyName<String>("updusrid");
    }

    /**
     * updpgidのプロパティ名を返します。
     * 
     * @return updpgidのプロパティ名
     */
    public static PropertyName<String> updpgid() {
        return new PropertyName<String>("updpgid");
    }

    /**
     * mcltのプロパティ名を返します。
     * 
     * @return mcltのプロパティ名
     */
    public static _McltNames mclt() {
        return new _McltNames("mclt");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TslempBaseNames extends PropertyName<Tslemp> {

        /**
         * インスタンスを構築します。
         */
        public _TslempBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TslempBaseNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _TslempBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * ordcdのプロパティ名を返します。
         *
         * @return ordcdのプロパティ名
         */
        public PropertyName<Integer> ordcd() {
            return new PropertyName<Integer>(this, "ordcd");
        }

        /**
         * itemcdのプロパティ名を返します。
         *
         * @return itemcdのプロパティ名
         */
        public PropertyName<Integer> itemcd() {
            return new PropertyName<Integer>(this, "itemcd");
        }

        /**
         * cltcdのプロパティ名を返します。
         *
         * @return cltcdのプロパティ名
         */
        public PropertyName<String> cltcd() {
            return new PropertyName<String>(this, "cltcd");
        }

        /**
         * setprodnmのプロパティ名を返します。
         *
         * @return setprodnmのプロパティ名
         */
        public PropertyName<String> setprodnm() {
            return new PropertyName<String>(this, "setprodnm");
        }

        /**
         * prodkbnのプロパティ名を返します。
         *
         * @return prodkbnのプロパティ名
         */
        public PropertyName<Short> prodkbn() {
            return new PropertyName<Short>(this, "prodkbn");
        }

        /**
         * amountのプロパティ名を返します。
         *
         * @return amountのプロパティ名
         */
        public PropertyName<BigDecimal> amount() {
            return new PropertyName<BigDecimal>(this, "amount");
        }

        /**
         * orddtのプロパティ名を返します。
         *
         * @return orddtのプロパティ名
         */
        public PropertyName<Date> orddt() {
            return new PropertyName<Date>(this, "orddt");
        }

        /**
         * apprdのプロパティ名を返します。
         *
         * @return apprdのプロパティ名
         */
        public PropertyName<Date> apprd() {
            return new PropertyName<Date>(this, "apprd");
        }

        /**
         * apprmのプロパティ名を返します。
         *
         * @return apprmのプロパティ名
         */
        public PropertyName<String> apprm() {
            return new PropertyName<String>(this, "apprm");
        }

        /**
         * apprwのプロパティ名を返します。
         *
         * @return apprwのプロパティ名
         */
        public PropertyName<Short> apprw() {
            return new PropertyName<Short>(this, "apprw");
        }

        /**
         * pubstartdtのプロパティ名を返します。
         *
         * @return pubstartdtのプロパティ名
         */
        public PropertyName<Date> pubstartdt() {
            return new PropertyName<Date>(this, "pubstartdt");
        }

        /**
         * cltempidのプロパティ名を返します。
         *
         * @return cltempidのプロパティ名
         */
        public PropertyName<String> cltempid() {
            return new PropertyName<String>(this, "cltempid");
        }

        /**
         * agtcdのプロパティ名を返します。
         *
         * @return agtcdのプロパティ名
         */
        public PropertyName<String> agtcd() {
            return new PropertyName<String>(this, "agtcd");
        }

        /**
         * crttimestampのプロパティ名を返します。
         *
         * @return crttimestampのプロパティ名
         */
        public PropertyName<Timestamp> crttimestamp() {
            return new PropertyName<Timestamp>(this, "crttimestamp");
        }

        /**
         * crtusrcdのプロパティ名を返します。
         *
         * @return crtusrcdのプロパティ名
         */
        public PropertyName<String> crtusrcd() {
            return new PropertyName<String>(this, "crtusrcd");
        }

        /**
         * crtusridのプロパティ名を返します。
         *
         * @return crtusridのプロパティ名
         */
        public PropertyName<String> crtusrid() {
            return new PropertyName<String>(this, "crtusrid");
        }

        /**
         * crtpgidのプロパティ名を返します。
         *
         * @return crtpgidのプロパティ名
         */
        public PropertyName<String> crtpgid() {
            return new PropertyName<String>(this, "crtpgid");
        }

        /**
         * updtimestampのプロパティ名を返します。
         *
         * @return updtimestampのプロパティ名
         */
        public PropertyName<Timestamp> updtimestamp() {
            return new PropertyName<Timestamp>(this, "updtimestamp");
        }

        /**
         * updusrcdのプロパティ名を返します。
         *
         * @return updusrcdのプロパティ名
         */
        public PropertyName<String> updusrcd() {
            return new PropertyName<String>(this, "updusrcd");
        }

        /**
         * updusridのプロパティ名を返します。
         *
         * @return updusridのプロパティ名
         */
        public PropertyName<String> updusrid() {
            return new PropertyName<String>(this, "updusrid");
        }

        /**
         * updpgidのプロパティ名を返します。
         *
         * @return updpgidのプロパティ名
         */
        public PropertyName<String> updpgid() {
            return new PropertyName<String>(this, "updpgid");
        }

        /**
         * mcltのプロパティ名を返します。
         * 
         * @return mcltのプロパティ名
         */
        public _McltNames mclt() {
            return new _McltNames(this, "mclt");
        }
    }
}
