package jp.co.inte.crm.common.entity.names;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltemp;
import jp.co.inte.crm.common.entity.names.McltNames._McltNames;
import jp.co.inte.crm.common.entity.names.MclttransmngNames._MclttransmngNames;
import jp.co.inte.crm.common.entity.names.MintrausrNames._MintrausrNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mcltemp}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class McltempBaseNames {

    /**
     * inteempnoのプロパティ名を返します。
     * 
     * @return inteempnoのプロパティ名
     */
    public static PropertyName<String> inteempno() {
        return new PropertyName<String>("inteempno");
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
     * strdtのプロパティ名を返します。
     * 
     * @return strdtのプロパティ名
     */
    public static PropertyName<Date> strdt() {
        return new PropertyName<Date>("strdt");
    }

    /**
     * enddtのプロパティ名を返します。
     * 
     * @return enddtのプロパティ名
     */
    public static PropertyName<Date> enddt() {
        return new PropertyName<Date>("enddt");
    }

    /**
     * cgkbnのプロパティ名を返します。
     * 
     * @return cgkbnのプロパティ名
     */
    public static PropertyName<String> cgkbn() {
        return new PropertyName<String>("cgkbn");
    }

    /**
     * applydtのプロパティ名を返します。
     * 
     * @return applydtのプロパティ名
     */
    public static PropertyName<Date> applydt() {
        return new PropertyName<Date>("applydt");
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
     * mintrausrのプロパティ名を返します。
     * 
     * @return mintrausrのプロパティ名
     */
    public static _MintrausrNames mintrausr() {
        return new _MintrausrNames("mintrausr");
    }

    /**
     * mclttransmngListのプロパティ名を返します。
     * 
     * @return mclttransmngListのプロパティ名
     */
    public static _MclttransmngNames mclttransmngList() {
        return new _MclttransmngNames("mclttransmngList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _McltempBaseNames extends PropertyName<Mcltemp> {

        /**
         * インスタンスを構築します。
         */
        public _McltempBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _McltempBaseNames(final String name) {
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
        public _McltempBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * inteempnoのプロパティ名を返します。
         *
         * @return inteempnoのプロパティ名
         */
        public PropertyName<String> inteempno() {
            return new PropertyName<String>(this, "inteempno");
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
         * strdtのプロパティ名を返します。
         *
         * @return strdtのプロパティ名
         */
        public PropertyName<Date> strdt() {
            return new PropertyName<Date>(this, "strdt");
        }

        /**
         * enddtのプロパティ名を返します。
         *
         * @return enddtのプロパティ名
         */
        public PropertyName<Date> enddt() {
            return new PropertyName<Date>(this, "enddt");
        }

        /**
         * cgkbnのプロパティ名を返します。
         *
         * @return cgkbnのプロパティ名
         */
        public PropertyName<String> cgkbn() {
            return new PropertyName<String>(this, "cgkbn");
        }

        /**
         * applydtのプロパティ名を返します。
         *
         * @return applydtのプロパティ名
         */
        public PropertyName<Date> applydt() {
            return new PropertyName<Date>(this, "applydt");
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
         * mintrausrのプロパティ名を返します。
         * 
         * @return mintrausrのプロパティ名
         */
        public _MintrausrNames mintrausr() {
            return new _MintrausrNames(this, "mintrausr");
        }

        /**
         * mclttransmngListのプロパティ名を返します。
         * 
         * @return mclttransmngListのプロパティ名
         */
        public _MclttransmngNames mclttransmngList() {
            return new _MclttransmngNames(this, "mclttransmngList");
        }
    }
}
