package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mhostshis;
import jp.co.inte.crm.common.entity.names.MhoNames._MhoNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mhostshis}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class MhostshisBaseNames {

    /**
     * hoidのプロパティ名を返します。
     * 
     * @return hoidのプロパティ名
     */
    public static PropertyName<String> hoid() {
        return new PropertyName<String>("hoid");
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
     * mhostshiskbnのプロパティ名を返します。
     * 
     * @return mhostshiskbnのプロパティ名
     */
    public static PropertyName<Short> mhostshiskbn() {
        return new PropertyName<Short>("mhostshiskbn");
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
     * ajthocdのプロパティ名を返します。
     * 
     * @return ajthocdのプロパティ名
     */
    public static PropertyName<String> ajthocd() {
        return new PropertyName<String>("ajthocd");
    }

    /**
     * reqempcdのプロパティ名を返します。
     * 
     * @return reqempcdのプロパティ名
     */
    public static PropertyName<String> reqempcd() {
        return new PropertyName<String>("reqempcd");
    }

    /**
     * reqempnmのプロパティ名を返します。
     * 
     * @return reqempnmのプロパティ名
     */
    public static PropertyName<String> reqempnm() {
        return new PropertyName<String>("reqempnm");
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
     * mhoのプロパティ名を返します。
     * 
     * @return mhoのプロパティ名
     */
    public static _MhoNames mho() {
        return new _MhoNames("mho");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MhostshisBaseNames extends PropertyName<Mhostshis> {

        /**
         * インスタンスを構築します。
         */
        public _MhostshisBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MhostshisBaseNames(final String name) {
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
        public _MhostshisBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * hoidのプロパティ名を返します。
         *
         * @return hoidのプロパティ名
         */
        public PropertyName<String> hoid() {
            return new PropertyName<String>(this, "hoid");
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
         * mhostshiskbnのプロパティ名を返します。
         *
         * @return mhostshiskbnのプロパティ名
         */
        public PropertyName<Short> mhostshiskbn() {
            return new PropertyName<Short>(this, "mhostshiskbn");
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
         * ajthocdのプロパティ名を返します。
         *
         * @return ajthocdのプロパティ名
         */
        public PropertyName<String> ajthocd() {
            return new PropertyName<String>(this, "ajthocd");
        }

        /**
         * reqempcdのプロパティ名を返します。
         *
         * @return reqempcdのプロパティ名
         */
        public PropertyName<String> reqempcd() {
            return new PropertyName<String>(this, "reqempcd");
        }

        /**
         * reqempnmのプロパティ名を返します。
         *
         * @return reqempnmのプロパティ名
         */
        public PropertyName<String> reqempnm() {
            return new PropertyName<String>(this, "reqempnm");
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
         * mhoのプロパティ名を返します。
         * 
         * @return mhoのプロパティ名
         */
        public _MhoNames mho() {
            return new _MhoNames(this, "mho");
        }
    }
}
