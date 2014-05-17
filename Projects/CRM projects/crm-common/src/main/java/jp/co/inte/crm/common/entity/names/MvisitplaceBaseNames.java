package jp.co.inte.crm.common.entity.names;

import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mvisitplace;
import jp.co.inte.crm.common.entity.names.McltNames._McltNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mvisitplace}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class MvisitplaceBaseNames {

    /**
     * visitplaceidのプロパティ名を返します。
     * 
     * @return visitplaceidのプロパティ名
     */
    public static PropertyName<String> visitplaceid() {
        return new PropertyName<String>("visitplaceid");
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
     * locationのプロパティ名を返します。
     * 
     * @return locationのプロパティ名
     */
    public static PropertyName<String> location() {
        return new PropertyName<String>("location");
    }

    /**
     * zipのプロパティ名を返します。
     * 
     * @return zipのプロパティ名
     */
    public static PropertyName<String> zip() {
        return new PropertyName<String>("zip");
    }

    /**
     * prefのプロパティ名を返します。
     * 
     * @return prefのプロパティ名
     */
    public static PropertyName<String> pref() {
        return new PropertyName<String>("pref");
    }

    /**
     * ctyのプロパティ名を返します。
     * 
     * @return ctyのプロパティ名
     */
    public static PropertyName<String> cty() {
        return new PropertyName<String>("cty");
    }

    /**
     * ad01のプロパティ名を返します。
     * 
     * @return ad01のプロパティ名
     */
    public static PropertyName<String> ad01() {
        return new PropertyName<String>("ad01");
    }

    /**
     * ad02のプロパティ名を返します。
     * 
     * @return ad02のプロパティ名
     */
    public static PropertyName<String> ad02() {
        return new PropertyName<String>("ad02");
    }

    /**
     * telのプロパティ名を返します。
     * 
     * @return telのプロパティ名
     */
    public static PropertyName<String> tel() {
        return new PropertyName<String>("tel");
    }

    /**
     * faxのプロパティ名を返します。
     * 
     * @return faxのプロパティ名
     */
    public static PropertyName<String> fax() {
        return new PropertyName<String>("fax");
    }

    /**
     * comentのプロパティ名を返します。
     * 
     * @return comentのプロパティ名
     */
    public static PropertyName<String> coment() {
        return new PropertyName<String>("coment");
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
    public static class _MvisitplaceBaseNames extends PropertyName<Mvisitplace> {

        /**
         * インスタンスを構築します。
         */
        public _MvisitplaceBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MvisitplaceBaseNames(final String name) {
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
        public _MvisitplaceBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * visitplaceidのプロパティ名を返します。
         *
         * @return visitplaceidのプロパティ名
         */
        public PropertyName<String> visitplaceid() {
            return new PropertyName<String>(this, "visitplaceid");
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
         * locationのプロパティ名を返します。
         *
         * @return locationのプロパティ名
         */
        public PropertyName<String> location() {
            return new PropertyName<String>(this, "location");
        }

        /**
         * zipのプロパティ名を返します。
         *
         * @return zipのプロパティ名
         */
        public PropertyName<String> zip() {
            return new PropertyName<String>(this, "zip");
        }

        /**
         * prefのプロパティ名を返します。
         *
         * @return prefのプロパティ名
         */
        public PropertyName<String> pref() {
            return new PropertyName<String>(this, "pref");
        }

        /**
         * ctyのプロパティ名を返します。
         *
         * @return ctyのプロパティ名
         */
        public PropertyName<String> cty() {
            return new PropertyName<String>(this, "cty");
        }

        /**
         * ad01のプロパティ名を返します。
         *
         * @return ad01のプロパティ名
         */
        public PropertyName<String> ad01() {
            return new PropertyName<String>(this, "ad01");
        }

        /**
         * ad02のプロパティ名を返します。
         *
         * @return ad02のプロパティ名
         */
        public PropertyName<String> ad02() {
            return new PropertyName<String>(this, "ad02");
        }

        /**
         * telのプロパティ名を返します。
         *
         * @return telのプロパティ名
         */
        public PropertyName<String> tel() {
            return new PropertyName<String>(this, "tel");
        }

        /**
         * faxのプロパティ名を返します。
         *
         * @return faxのプロパティ名
         */
        public PropertyName<String> fax() {
            return new PropertyName<String>(this, "fax");
        }

        /**
         * comentのプロパティ名を返します。
         *
         * @return comentのプロパティ名
         */
        public PropertyName<String> coment() {
            return new PropertyName<String>(this, "coment");
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
