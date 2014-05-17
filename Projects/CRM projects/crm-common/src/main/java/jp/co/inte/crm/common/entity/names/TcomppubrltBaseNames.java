package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tcomppubrlt;
import jp.co.inte.crm.common.entity.names.McltNames._McltNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Tcomppubrlt}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class TcomppubrltBaseNames {

    /**
     * cltcdのプロパティ名を返します。
     * 
     * @return cltcdのプロパティ名
     */
    public static PropertyName<String> cltcd() {
        return new PropertyName<String>("cltcd");
    }

    /**
     * complastpubdtのプロパティ名を返します。
     * 
     * @return complastpubdtのプロパティ名
     */
    public static PropertyName<Date> complastpubdt() {
        return new PropertyName<Date>("complastpubdt");
    }

    /**
     * lastpubprodのプロパティ名を返します。
     * 
     * @return lastpubprodのプロパティ名
     */
    public static PropertyName<String> lastpubprod() {
        return new PropertyName<String>("lastpubprod");
    }

    /**
     * complastpubamountのプロパティ名を返します。
     * 
     * @return complastpubamountのプロパティ名
     */
    public static PropertyName<BigDecimal> complastpubamount() {
        return new PropertyName<BigDecimal>("complastpubamount");
    }

    /**
     * lastslpostのプロパティ名を返します。
     * 
     * @return lastslpostのプロパティ名
     */
    public static PropertyName<String> lastslpost() {
        return new PropertyName<String>("lastslpost");
    }

    /**
     * lastslempnmのプロパティ名を返します。
     * 
     * @return lastslempnmのプロパティ名
     */
    public static PropertyName<String> lastslempnm() {
        return new PropertyName<String>("lastslempnm");
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
    public static class _TcomppubrltBaseNames extends PropertyName<Tcomppubrlt> {

        /**
         * インスタンスを構築します。
         */
        public _TcomppubrltBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TcomppubrltBaseNames(final String name) {
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
        public _TcomppubrltBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * complastpubdtのプロパティ名を返します。
         *
         * @return complastpubdtのプロパティ名
         */
        public PropertyName<Date> complastpubdt() {
            return new PropertyName<Date>(this, "complastpubdt");
        }

        /**
         * lastpubprodのプロパティ名を返します。
         *
         * @return lastpubprodのプロパティ名
         */
        public PropertyName<String> lastpubprod() {
            return new PropertyName<String>(this, "lastpubprod");
        }

        /**
         * complastpubamountのプロパティ名を返します。
         *
         * @return complastpubamountのプロパティ名
         */
        public PropertyName<BigDecimal> complastpubamount() {
            return new PropertyName<BigDecimal>(this, "complastpubamount");
        }

        /**
         * lastslpostのプロパティ名を返します。
         *
         * @return lastslpostのプロパティ名
         */
        public PropertyName<String> lastslpost() {
            return new PropertyName<String>(this, "lastslpost");
        }

        /**
         * lastslempnmのプロパティ名を返します。
         *
         * @return lastslempnmのプロパティ名
         */
        public PropertyName<String> lastslempnm() {
            return new PropertyName<String>(this, "lastslempnm");
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
