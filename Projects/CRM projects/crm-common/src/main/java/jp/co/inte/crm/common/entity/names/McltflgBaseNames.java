package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltflg;
import jp.co.inte.crm.common.entity.names.McltflgxdeptNames._McltflgxdeptNames;
import jp.co.inte.crm.common.entity.names.McltxflgNames._McltxflgNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mcltflg}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class McltflgBaseNames {

    /**
     * flgnoのプロパティ名を返します。
     * 
     * @return flgnoのプロパティ名
     */
    public static PropertyName<BigDecimal> flgno() {
        return new PropertyName<BigDecimal>("flgno");
    }

    /**
     * flgnmのプロパティ名を返します。
     * 
     * @return flgnmのプロパティ名
     */
    public static PropertyName<String> flgnm() {
        return new PropertyName<String>("flgnm");
    }

    /**
     * ctrlassignframeのプロパティ名を返します。
     * 
     * @return ctrlassignframeのプロパティ名
     */
    public static PropertyName<String> ctrlassignframe() {
        return new PropertyName<String>("ctrlassignframe");
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
     * updatedtのプロパティ名を返します。
     * 
     * @return updatedtのプロパティ名
     */
    public static PropertyName<Date> updatedt() {
        return new PropertyName<Date>("updatedt");
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
     * mcltflgxdeptのプロパティ名を返します。
     * 
     * @return mcltflgxdeptのプロパティ名
     */
    public static _McltflgxdeptNames mcltflgxdept() {
        return new _McltflgxdeptNames("mcltflgxdept");
    }

    /**
     * mcltxflgListのプロパティ名を返します。
     * 
     * @return mcltxflgListのプロパティ名
     */
    public static _McltxflgNames mcltxflgList() {
        return new _McltxflgNames("mcltxflgList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _McltflgBaseNames extends PropertyName<Mcltflg> {

        /**
         * インスタンスを構築します。
         */
        public _McltflgBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _McltflgBaseNames(final String name) {
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
        public _McltflgBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * flgnoのプロパティ名を返します。
         *
         * @return flgnoのプロパティ名
         */
        public PropertyName<BigDecimal> flgno() {
            return new PropertyName<BigDecimal>(this, "flgno");
        }

        /**
         * flgnmのプロパティ名を返します。
         *
         * @return flgnmのプロパティ名
         */
        public PropertyName<String> flgnm() {
            return new PropertyName<String>(this, "flgnm");
        }

        /**
         * ctrlassignframeのプロパティ名を返します。
         *
         * @return ctrlassignframeのプロパティ名
         */
        public PropertyName<String> ctrlassignframe() {
            return new PropertyName<String>(this, "ctrlassignframe");
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
         * updatedtのプロパティ名を返します。
         *
         * @return updatedtのプロパティ名
         */
        public PropertyName<Date> updatedt() {
            return new PropertyName<Date>(this, "updatedt");
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
         * mcltflgxdeptのプロパティ名を返します。
         * 
         * @return mcltflgxdeptのプロパティ名
         */
        public _McltflgxdeptNames mcltflgxdept() {
            return new _McltflgxdeptNames(this, "mcltflgxdept");
        }

        /**
         * mcltxflgListのプロパティ名を返します。
         * 
         * @return mcltxflgListのプロパティ名
         */
        public _McltxflgNames mcltxflgList() {
            return new _McltxflgNames(this, "mcltxflgList");
        }
    }
}
