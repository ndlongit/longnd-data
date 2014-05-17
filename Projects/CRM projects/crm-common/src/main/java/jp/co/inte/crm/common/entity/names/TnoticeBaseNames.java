package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tnotice;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Tnotice}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class TnoticeBaseNames {

    /**
     * tnoticeidのプロパティ名を返します。
     * 
     * @return tnoticeidのプロパティ名
     */
    public static PropertyName<BigDecimal> tnoticeid() {
        return new PropertyName<BigDecimal>("tnoticeid");
    }

    /**
     * tnoticecontentsのプロパティ名を返します。
     * 
     * @return tnoticecontentsのプロパティ名
     */
    public static PropertyName<String> tnoticecontents() {
        return new PropertyName<String>("tnoticecontents");
    }

    /**
     * linkurlのプロパティ名を返します。
     * 
     * @return linkurlのプロパティ名
     */
    public static PropertyName<String> linkurl() {
        return new PropertyName<String>("linkurl");
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
     * @author S2JDBC-Gen
     */
    public static class _TnoticeBaseNames extends PropertyName<Tnotice> {

        /**
         * インスタンスを構築します。
         */
        public _TnoticeBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TnoticeBaseNames(final String name) {
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
        public _TnoticeBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * tnoticeidのプロパティ名を返します。
         *
         * @return tnoticeidのプロパティ名
         */
        public PropertyName<BigDecimal> tnoticeid() {
            return new PropertyName<BigDecimal>(this, "tnoticeid");
        }

        /**
         * tnoticecontentsのプロパティ名を返します。
         *
         * @return tnoticecontentsのプロパティ名
         */
        public PropertyName<String> tnoticecontents() {
            return new PropertyName<String>(this, "tnoticecontents");
        }

        /**
         * linkurlのプロパティ名を返します。
         *
         * @return linkurlのプロパティ名
         */
        public PropertyName<String> linkurl() {
            return new PropertyName<String>(this, "linkurl");
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
    }
}
