package jp.co.inte.crm.common.entity.names;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mclttransmng;
import jp.co.inte.crm.common.entity.names.McltNames._McltNames;
import jp.co.inte.crm.common.entity.names.McltempNames._McltempNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mclttransmng}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class MclttransmngBaseNames {

    /**
     * transidのプロパティ名を返します。
     * 
     * @return transidのプロパティ名
     */
    public static PropertyName<String> transid() {
        return new PropertyName<String>("transid");
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
     * transplandtのプロパティ名を返します。
     * 
     * @return transplandtのプロパティ名
     */
    public static PropertyName<Date> transplandt() {
        return new PropertyName<Date>("transplandt");
    }

    /**
     * starttimeのプロパティ名を返します。
     * 
     * @return starttimeのプロパティ名
     */
    public static PropertyName<Time> starttime() {
        return new PropertyName<Time>("starttime");
    }

    /**
     * endtimeのプロパティ名を返します。
     * 
     * @return endtimeのプロパティ名
     */
    public static PropertyName<Time> endtime() {
        return new PropertyName<Time>("endtime");
    }

    /**
     * actpurposekbnのプロパティ名を返します。
     * 
     * @return actpurposekbnのプロパティ名
     */
    public static PropertyName<Short> actpurposekbn() {
        return new PropertyName<Short>("actpurposekbn");
    }

    /**
     * actmethodkbnのプロパティ名を返します。
     * 
     * @return actmethodkbnのプロパティ名
     */
    public static PropertyName<Short> actmethodkbn() {
        return new PropertyName<Short>("actmethodkbn");
    }

    /**
     * transmethodkbnのプロパティ名を返します。
     * 
     * @return transmethodkbnのプロパティ名
     */
    public static PropertyName<Short> transmethodkbn() {
        return new PropertyName<Short>("transmethodkbn");
    }

    /**
     * transstskbnのプロパティ名を返します。
     * 
     * @return transstskbnのプロパティ名
     */
    public static PropertyName<Short> transstskbn() {
        return new PropertyName<Short>("transstskbn");
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
     * transfromempidのプロパティ名を返します。
     * 
     * @return transfromempidのプロパティ名
     */
    public static PropertyName<String> transfromempid() {
        return new PropertyName<String>("transfromempid");
    }

    /**
     * transtoempidのプロパティ名を返します。
     * 
     * @return transtoempidのプロパティ名
     */
    public static PropertyName<String> transtoempid() {
        return new PropertyName<String>("transtoempid");
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
     * mcltempのプロパティ名を返します。
     * 
     * @return mcltempのプロパティ名
     */
    public static _McltempNames mcltemp() {
        return new _McltempNames("mcltemp");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MclttransmngBaseNames extends PropertyName<Mclttransmng> {

        /**
         * インスタンスを構築します。
         */
        public _MclttransmngBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MclttransmngBaseNames(final String name) {
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
        public _MclttransmngBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * transidのプロパティ名を返します。
         *
         * @return transidのプロパティ名
         */
        public PropertyName<String> transid() {
            return new PropertyName<String>(this, "transid");
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
         * transplandtのプロパティ名を返します。
         *
         * @return transplandtのプロパティ名
         */
        public PropertyName<Date> transplandt() {
            return new PropertyName<Date>(this, "transplandt");
        }

        /**
         * starttimeのプロパティ名を返します。
         *
         * @return starttimeのプロパティ名
         */
        public PropertyName<Time> starttime() {
            return new PropertyName<Time>(this, "starttime");
        }

        /**
         * endtimeのプロパティ名を返します。
         *
         * @return endtimeのプロパティ名
         */
        public PropertyName<Time> endtime() {
            return new PropertyName<Time>(this, "endtime");
        }

        /**
         * actpurposekbnのプロパティ名を返します。
         *
         * @return actpurposekbnのプロパティ名
         */
        public PropertyName<Short> actpurposekbn() {
            return new PropertyName<Short>(this, "actpurposekbn");
        }

        /**
         * actmethodkbnのプロパティ名を返します。
         *
         * @return actmethodkbnのプロパティ名
         */
        public PropertyName<Short> actmethodkbn() {
            return new PropertyName<Short>(this, "actmethodkbn");
        }

        /**
         * transmethodkbnのプロパティ名を返します。
         *
         * @return transmethodkbnのプロパティ名
         */
        public PropertyName<Short> transmethodkbn() {
            return new PropertyName<Short>(this, "transmethodkbn");
        }

        /**
         * transstskbnのプロパティ名を返します。
         *
         * @return transstskbnのプロパティ名
         */
        public PropertyName<Short> transstskbn() {
            return new PropertyName<Short>(this, "transstskbn");
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
         * transfromempidのプロパティ名を返します。
         *
         * @return transfromempidのプロパティ名
         */
        public PropertyName<String> transfromempid() {
            return new PropertyName<String>(this, "transfromempid");
        }

        /**
         * transtoempidのプロパティ名を返します。
         *
         * @return transtoempidのプロパティ名
         */
        public PropertyName<String> transtoempid() {
            return new PropertyName<String>(this, "transtoempid");
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
         * mcltempのプロパティ名を返します。
         * 
         * @return mcltempのプロパティ名
         */
        public _McltempNames mcltemp() {
            return new _McltempNames(this, "mcltemp");
        }
    }
}
