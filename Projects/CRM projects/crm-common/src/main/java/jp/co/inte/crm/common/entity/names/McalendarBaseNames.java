package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcalendar;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mcalendar}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class McalendarBaseNames {

    /**
     * dateのプロパティ名を返します。
     * 
     * @return dateのプロパティ名
     */
    public static PropertyName<Date> date() {
        return new PropertyName<Date>("date");
    }

    /**
     * caldateのプロパティ名を返します。
     * 
     * @return caldateのプロパティ名
     */
    public static PropertyName<String> caldate() {
        return new PropertyName<String>("caldate");
    }

    /**
     * divcaldateのプロパティ名を返します。
     * 
     * @return divcaldateのプロパティ名
     */
    public static PropertyName<String> divcaldate() {
        return new PropertyName<String>("divcaldate");
    }

    /**
     * divdatewviewのプロパティ名を返します。
     * 
     * @return divdatewviewのプロパティ名
     */
    public static PropertyName<BigDecimal> divdatewview() {
        return new PropertyName<BigDecimal>("divdatewview");
    }

    /**
     * divdatewcalのプロパティ名を返します。
     * 
     * @return divdatewcalのプロパティ名
     */
    public static PropertyName<BigDecimal> divdatewcal() {
        return new PropertyName<BigDecimal>("divdatewcal");
    }

    /**
     * yearのプロパティ名を返します。
     * 
     * @return yearのプロパティ名
     */
    public static PropertyName<String> year() {
        return new PropertyName<String>("year");
    }

    /**
     * quarterのプロパティ名を返します。
     * 
     * @return quarterのプロパティ名
     */
    public static PropertyName<String> quarter() {
        return new PropertyName<String>("quarter");
    }

    /**
     * halfのプロパティ名を返します。
     * 
     * @return halfのプロパティ名
     */
    public static PropertyName<String> half() {
        return new PropertyName<String>("half");
    }

    /**
     * dayofweekのプロパティ名を返します。
     * 
     * @return dayofweekのプロパティ名
     */
    public static PropertyName<BigDecimal> dayofweek() {
        return new PropertyName<BigDecimal>("dayofweek");
    }

    /**
     * sldaysのプロパティ名を返します。
     * 
     * @return sldaysのプロパティ名
     */
    public static PropertyName<BigDecimal> sldays() {
        return new PropertyName<BigDecimal>("sldays");
    }

    /**
     * sldtのプロパティ名を返します。
     * 
     * @return sldtのプロパティ名
     */
    public static PropertyName<BigDecimal> sldt() {
        return new PropertyName<BigDecimal>("sldt");
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
    public static class _McalendarBaseNames extends PropertyName<Mcalendar> {

        /**
         * インスタンスを構築します。
         */
        public _McalendarBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _McalendarBaseNames(final String name) {
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
        public _McalendarBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * caldateのプロパティ名を返します。
         *
         * @return caldateのプロパティ名
         */
        public PropertyName<String> caldate() {
            return new PropertyName<String>(this, "caldate");
        }

        /**
         * divcaldateのプロパティ名を返します。
         *
         * @return divcaldateのプロパティ名
         */
        public PropertyName<String> divcaldate() {
            return new PropertyName<String>(this, "divcaldate");
        }

        /**
         * divdatewviewのプロパティ名を返します。
         *
         * @return divdatewviewのプロパティ名
         */
        public PropertyName<BigDecimal> divdatewview() {
            return new PropertyName<BigDecimal>(this, "divdatewview");
        }

        /**
         * divdatewcalのプロパティ名を返します。
         *
         * @return divdatewcalのプロパティ名
         */
        public PropertyName<BigDecimal> divdatewcal() {
            return new PropertyName<BigDecimal>(this, "divdatewcal");
        }

        /**
         * yearのプロパティ名を返します。
         *
         * @return yearのプロパティ名
         */
        public PropertyName<String> year() {
            return new PropertyName<String>(this, "year");
        }

        /**
         * quarterのプロパティ名を返します。
         *
         * @return quarterのプロパティ名
         */
        public PropertyName<String> quarter() {
            return new PropertyName<String>(this, "quarter");
        }

        /**
         * halfのプロパティ名を返します。
         *
         * @return halfのプロパティ名
         */
        public PropertyName<String> half() {
            return new PropertyName<String>(this, "half");
        }

        /**
         * dayofweekのプロパティ名を返します。
         *
         * @return dayofweekのプロパティ名
         */
        public PropertyName<BigDecimal> dayofweek() {
            return new PropertyName<BigDecimal>(this, "dayofweek");
        }

        /**
         * sldaysのプロパティ名を返します。
         *
         * @return sldaysのプロパティ名
         */
        public PropertyName<BigDecimal> sldays() {
            return new PropertyName<BigDecimal>(this, "sldays");
        }

        /**
         * sldtのプロパティ名を返します。
         *
         * @return sldtのプロパティ名
         */
        public PropertyName<BigDecimal> sldt() {
            return new PropertyName<BigDecimal>(this, "sldt");
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
