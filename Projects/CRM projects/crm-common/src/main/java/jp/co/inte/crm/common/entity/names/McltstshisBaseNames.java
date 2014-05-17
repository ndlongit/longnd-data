package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltstshis;
import jp.co.inte.crm.common.entity.names.McltNames._McltNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mcltstshis}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class McltstshisBaseNames {

    /**
     * cltidのプロパティ名を返します。
     * 
     * @return cltidのプロパティ名
     */
    public static PropertyName<String> cltid() {
        return new PropertyName<String>("cltid");
    }

    /**
     * historyidのプロパティ名を返します。
     * 
     * @return historyidのプロパティ名
     */
    public static PropertyName<BigDecimal> historyid() {
        return new PropertyName<BigDecimal>("historyid");
    }

    /**
     * cltstscdのプロパティ名を返します。
     * 
     * @return cltstscdのプロパティ名
     */
    public static PropertyName<String> cltstscd() {
        return new PropertyName<String>("cltstscd");
    }

    /**
     * reasonのプロパティ名を返します。
     * 
     * @return reasonのプロパティ名
     */
    public static PropertyName<String> reason() {
        return new PropertyName<String>("reason");
    }

    /**
     * reasonrmksのプロパティ名を返します。
     * 
     * @return reasonrmksのプロパティ名
     */
    public static PropertyName<String> reasonrmks() {
        return new PropertyName<String>("reasonrmks");
    }

    /**
     * ajtcltcdのプロパティ名を返します。
     * 
     * @return ajtcltcdのプロパティ名
     */
    public static PropertyName<String> ajtcltcd() {
        return new PropertyName<String>("ajtcltcd");
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
    public static class _McltstshisBaseNames extends PropertyName<Mcltstshis> {

        /**
         * インスタンスを構築します。
         */
        public _McltstshisBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _McltstshisBaseNames(final String name) {
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
        public _McltstshisBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * historyidのプロパティ名を返します。
         *
         * @return historyidのプロパティ名
         */
        public PropertyName<BigDecimal> historyid() {
            return new PropertyName<BigDecimal>(this, "historyid");
        }

        /**
         * cltstscdのプロパティ名を返します。
         *
         * @return cltstscdのプロパティ名
         */
        public PropertyName<String> cltstscd() {
            return new PropertyName<String>(this, "cltstscd");
        }

        /**
         * reasonのプロパティ名を返します。
         *
         * @return reasonのプロパティ名
         */
        public PropertyName<String> reason() {
            return new PropertyName<String>(this, "reason");
        }

        /**
         * reasonrmksのプロパティ名を返します。
         *
         * @return reasonrmksのプロパティ名
         */
        public PropertyName<String> reasonrmks() {
            return new PropertyName<String>(this, "reasonrmks");
        }

        /**
         * ajtcltcdのプロパティ名を返します。
         *
         * @return ajtcltcdのプロパティ名
         */
        public PropertyName<String> ajtcltcd() {
            return new PropertyName<String>(this, "ajtcltcd");
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
