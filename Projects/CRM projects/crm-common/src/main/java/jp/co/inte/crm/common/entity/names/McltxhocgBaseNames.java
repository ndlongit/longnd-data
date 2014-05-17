package jp.co.inte.crm.common.entity.names;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltxhocg;
import jp.co.inte.crm.common.entity.names.McltNames._McltNames;
import jp.co.inte.crm.common.entity.names.MhocgNames._MhocgNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mcltxhocg}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class McltxhocgBaseNames {

    /**
     * cghocltidのプロパティ名を返します。
     * 
     * @return cghocltidのプロパティ名
     */
    public static PropertyName<String> cghocltid() {
        return new PropertyName<String>("cghocltid");
    }

    /**
     * cltidのプロパティ名を返します。
     * 
     * @return cltidのプロパティ名
     */
    public static PropertyName<String> cltid() {
        return new PropertyName<String>("cltid");
    }

    /**
     * hocgidのプロパティ名を返します。
     * 
     * @return hocgidのプロパティ名
     */
    public static PropertyName<String> hocgid() {
        return new PropertyName<String>("hocgid");
    }

    /**
     * dicisionのプロパティ名を返します。
     * 
     * @return dicisionのプロパティ名
     */
    public static PropertyName<Boolean> dicision() {
        return new PropertyName<Boolean>("dicision");
    }

    /**
     * ordercgのプロパティ名を返します。
     * 
     * @return ordercgのプロパティ名
     */
    public static PropertyName<Boolean> ordercg() {
        return new PropertyName<Boolean>("ordercg");
    }

    /**
     * etcのプロパティ名を返します。
     * 
     * @return etcのプロパティ名
     */
    public static PropertyName<Boolean> etc() {
        return new PropertyName<Boolean>("etc");
    }

    /**
     * attendeddatetimeのプロパティ名を返します。
     * 
     * @return attendeddatetimeのプロパティ名
     */
    public static PropertyName<Timestamp> attendeddatetime() {
        return new PropertyName<Timestamp>("attendeddatetime");
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
     * mhocgのプロパティ名を返します。
     * 
     * @return mhocgのプロパティ名
     */
    public static _MhocgNames mhocg() {
        return new _MhocgNames("mhocg");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _McltxhocgBaseNames extends PropertyName<Mcltxhocg> {

        /**
         * インスタンスを構築します。
         */
        public _McltxhocgBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _McltxhocgBaseNames(final String name) {
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
        public _McltxhocgBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * cghocltidのプロパティ名を返します。
         *
         * @return cghocltidのプロパティ名
         */
        public PropertyName<String> cghocltid() {
            return new PropertyName<String>(this, "cghocltid");
        }

        /**
         * cltidのプロパティ名を返します。
         *
         * @return cltidのプロパティ名
         */
        public PropertyName<String> cltid() {
            return new PropertyName<String>(this, "cltid");
        }

        /**
         * hocgidのプロパティ名を返します。
         *
         * @return hocgidのプロパティ名
         */
        public PropertyName<String> hocgid() {
            return new PropertyName<String>(this, "hocgid");
        }

        /**
         * dicisionのプロパティ名を返します。
         *
         * @return dicisionのプロパティ名
         */
        public PropertyName<Boolean> dicision() {
            return new PropertyName<Boolean>(this, "dicision");
        }

        /**
         * ordercgのプロパティ名を返します。
         *
         * @return ordercgのプロパティ名
         */
        public PropertyName<Boolean> ordercg() {
            return new PropertyName<Boolean>(this, "ordercg");
        }

        /**
         * etcのプロパティ名を返します。
         *
         * @return etcのプロパティ名
         */
        public PropertyName<Boolean> etc() {
            return new PropertyName<Boolean>(this, "etc");
        }

        /**
         * attendeddatetimeのプロパティ名を返します。
         *
         * @return attendeddatetimeのプロパティ名
         */
        public PropertyName<Timestamp> attendeddatetime() {
            return new PropertyName<Timestamp>(this, "attendeddatetime");
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

        /**
         * mhocgのプロパティ名を返します。
         * 
         * @return mhocgのプロパティ名
         */
        public _MhocgNames mhocg() {
            return new _MhocgNames(this, "mhocg");
        }
    }
}
