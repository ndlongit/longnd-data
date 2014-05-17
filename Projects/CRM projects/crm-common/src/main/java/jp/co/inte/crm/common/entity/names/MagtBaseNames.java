package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Magt;
import jp.co.inte.crm.common.entity.names.McltNames._McltNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Magt}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class MagtBaseNames {

    /**
     * agtcdのプロパティ名を返します。
     * 
     * @return agtcdのプロパティ名
     */
    public static PropertyName<String> agtcd() {
        return new PropertyName<String>("agtcd");
    }

    /**
     * agtnmのプロパティ名を返します。
     * 
     * @return agtnmのプロパティ名
     */
    public static PropertyName<String> agtnm() {
        return new PropertyName<String>("agtnm");
    }

    /**
     * agtempcdのプロパティ名を返します。
     * 
     * @return agtempcdのプロパティ名
     */
    public static PropertyName<BigDecimal> agtempcd() {
        return new PropertyName<BigDecimal>("agtempcd");
    }

    /**
     * agtempnmのプロパティ名を返します。
     * 
     * @return agtempnmのプロパティ名
     */
    public static PropertyName<String> agtempnm() {
        return new PropertyName<String>("agtempnm");
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
    public static class _MagtBaseNames extends PropertyName<Magt> {

        /**
         * インスタンスを構築します。
         */
        public _MagtBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MagtBaseNames(final String name) {
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
        public _MagtBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * agtnmのプロパティ名を返します。
         *
         * @return agtnmのプロパティ名
         */
        public PropertyName<String> agtnm() {
            return new PropertyName<String>(this, "agtnm");
        }

        /**
         * agtempcdのプロパティ名を返します。
         *
         * @return agtempcdのプロパティ名
         */
        public PropertyName<BigDecimal> agtempcd() {
            return new PropertyName<BigDecimal>(this, "agtempcd");
        }

        /**
         * agtempnmのプロパティ名を返します。
         *
         * @return agtempnmのプロパティ名
         */
        public PropertyName<String> agtempnm() {
            return new PropertyName<String>(this, "agtempnm");
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
