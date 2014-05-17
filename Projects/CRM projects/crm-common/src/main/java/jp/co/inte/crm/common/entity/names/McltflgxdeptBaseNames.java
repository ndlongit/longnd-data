package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltflgxdept;
import jp.co.inte.crm.common.entity.names.McltflgNames._McltflgNames;
import jp.co.inte.crm.common.entity.names.MorgctlNames._MorgctlNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mcltflgxdept}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class McltflgxdeptBaseNames {

    /**
     * deptsecnoのプロパティ名を返します。
     * 
     * @return deptsecnoのプロパティ名
     */
    public static PropertyName<BigDecimal> deptsecno() {
        return new PropertyName<BigDecimal>("deptsecno");
    }

    /**
     * flgnoのプロパティ名を返します。
     * 
     * @return flgnoのプロパティ名
     */
    public static PropertyName<BigDecimal> flgno() {
        return new PropertyName<BigDecimal>("flgno");
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
     * mcltflgのプロパティ名を返します。
     * 
     * @return mcltflgのプロパティ名
     */
    public static _McltflgNames mcltflg() {
        return new _McltflgNames("mcltflg");
    }

    /**
     * morgctlのプロパティ名を返します。
     * 
     * @return morgctlのプロパティ名
     */
    public static _MorgctlNames morgctl() {
        return new _MorgctlNames("morgctl");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _McltflgxdeptBaseNames extends PropertyName<Mcltflgxdept> {

        /**
         * インスタンスを構築します。
         */
        public _McltflgxdeptBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _McltflgxdeptBaseNames(final String name) {
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
        public _McltflgxdeptBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * deptsecnoのプロパティ名を返します。
         *
         * @return deptsecnoのプロパティ名
         */
        public PropertyName<BigDecimal> deptsecno() {
            return new PropertyName<BigDecimal>(this, "deptsecno");
        }

        /**
         * flgnoのプロパティ名を返します。
         *
         * @return flgnoのプロパティ名
         */
        public PropertyName<BigDecimal> flgno() {
            return new PropertyName<BigDecimal>(this, "flgno");
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
         * mcltflgのプロパティ名を返します。
         * 
         * @return mcltflgのプロパティ名
         */
        public _McltflgNames mcltflg() {
            return new _McltflgNames(this, "mcltflg");
        }

        /**
         * morgctlのプロパティ名を返します。
         * 
         * @return morgctlのプロパティ名
         */
        public _MorgctlNames morgctl() {
            return new _MorgctlNames(this, "morgctl");
        }
    }
}
