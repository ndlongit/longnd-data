package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tapproachplanhis;
import jp.co.inte.crm.common.entity.names.TapproachdtlNames._TapproachdtlNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Tapproachplanhis}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class TapproachplanhisBaseNames {

    /**
     * approachidのプロパティ名を返します。
     * 
     * @return approachidのプロパティ名
     */
    public static PropertyName<String> approachid() {
        return new PropertyName<String>("approachid");
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
     * subjectのプロパティ名を返します。
     * 
     * @return subjectのプロパティ名
     */
    public static PropertyName<String> subject() {
        return new PropertyName<String>("subject");
    }

    /**
     * actstskbnのプロパティ名を返します。
     * 
     * @return actstskbnのプロパティ名
     */
    public static PropertyName<Short> actstskbn() {
        return new PropertyName<Short>("actstskbn");
    }

    /**
     * dateのプロパティ名を返します。
     * 
     * @return dateのプロパティ名
     */
    public static PropertyName<Date> date() {
        return new PropertyName<Date>("date");
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
     * connectbeginkbnのプロパティ名を返します。
     * 
     * @return connectbeginkbnのプロパティ名
     */
    public static PropertyName<Short> connectbeginkbn() {
        return new PropertyName<Short>("connectbeginkbn");
    }

    /**
     * actcgidのプロパティ名を返します。
     * 
     * @return actcgidのプロパティ名
     */
    public static PropertyName<String> actcgid() {
        return new PropertyName<String>("actcgid");
    }

    /**
     * setidのプロパティ名を返します。
     * 
     * @return setidのプロパティ名
     */
    public static PropertyName<String> setid() {
        return new PropertyName<String>("setid");
    }

    /**
     * setdtのプロパティ名を返します。
     * 
     * @return setdtのプロパティ名
     */
    public static PropertyName<Date> setdt() {
        return new PropertyName<Date>("setdt");
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
     * tapproachdtlのプロパティ名を返します。
     * 
     * @return tapproachdtlのプロパティ名
     */
    public static _TapproachdtlNames tapproachdtl() {
        return new _TapproachdtlNames("tapproachdtl");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TapproachplanhisBaseNames extends PropertyName<Tapproachplanhis> {

        /**
         * インスタンスを構築します。
         */
        public _TapproachplanhisBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TapproachplanhisBaseNames(final String name) {
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
        public _TapproachplanhisBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * approachidのプロパティ名を返します。
         *
         * @return approachidのプロパティ名
         */
        public PropertyName<String> approachid() {
            return new PropertyName<String>(this, "approachid");
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
         * subjectのプロパティ名を返します。
         *
         * @return subjectのプロパティ名
         */
        public PropertyName<String> subject() {
            return new PropertyName<String>(this, "subject");
        }

        /**
         * actstskbnのプロパティ名を返します。
         *
         * @return actstskbnのプロパティ名
         */
        public PropertyName<Short> actstskbn() {
            return new PropertyName<Short>(this, "actstskbn");
        }

        /**
         * dateのプロパティ名を返します。
         *
         * @return dateのプロパティ名
         */
        public PropertyName<Date> date() {
            return new PropertyName<Date>(this, "date");
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
         * connectbeginkbnのプロパティ名を返します。
         *
         * @return connectbeginkbnのプロパティ名
         */
        public PropertyName<Short> connectbeginkbn() {
            return new PropertyName<Short>(this, "connectbeginkbn");
        }

        /**
         * actcgidのプロパティ名を返します。
         *
         * @return actcgidのプロパティ名
         */
        public PropertyName<String> actcgid() {
            return new PropertyName<String>(this, "actcgid");
        }

        /**
         * setidのプロパティ名を返します。
         *
         * @return setidのプロパティ名
         */
        public PropertyName<String> setid() {
            return new PropertyName<String>(this, "setid");
        }

        /**
         * setdtのプロパティ名を返します。
         *
         * @return setdtのプロパティ名
         */
        public PropertyName<Date> setdt() {
            return new PropertyName<Date>(this, "setdt");
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
         * tapproachdtlのプロパティ名を返します。
         * 
         * @return tapproachdtlのプロパティ名
         */
        public _TapproachdtlNames tapproachdtl() {
            return new _TapproachdtlNames(this, "tapproachdtl");
        }
    }
}
