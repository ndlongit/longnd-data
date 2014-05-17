package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mmtrlflg;
import jp.co.inte.crm.common.entity.names.MmtrlflgxdeptNames._MmtrlflgxdeptNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mmtrlflg}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class MmtrlflgBaseNames {

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
    public static PropertyName<Short> ctrlassignframe() {
        return new PropertyName<Short>("ctrlassignframe");
    }

    /**
     * startdtのプロパティ名を返します。
     * 
     * @return startdtのプロパティ名
     */
    public static PropertyName<Date> startdt() {
        return new PropertyName<Date>("startdt");
    }

    /**
     * closeplandateのプロパティ名を返します。
     * 
     * @return closeplandateのプロパティ名
     */
    public static PropertyName<Date> closeplandate() {
        return new PropertyName<Date>("closeplandate");
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
     * mmtrlflgxdeptのプロパティ名を返します。
     * 
     * @return mmtrlflgxdeptのプロパティ名
     */
    public static _MmtrlflgxdeptNames mmtrlflgxdept() {
        return new _MmtrlflgxdeptNames("mmtrlflgxdept");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MmtrlflgBaseNames extends PropertyName<Mmtrlflg> {

        /**
         * インスタンスを構築します。
         */
        public _MmtrlflgBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MmtrlflgBaseNames(final String name) {
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
        public _MmtrlflgBaseNames(final PropertyName<?> parent, final String name) {
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
        public PropertyName<Short> ctrlassignframe() {
            return new PropertyName<Short>(this, "ctrlassignframe");
        }

        /**
         * startdtのプロパティ名を返します。
         *
         * @return startdtのプロパティ名
         */
        public PropertyName<Date> startdt() {
            return new PropertyName<Date>(this, "startdt");
        }

        /**
         * closeplandateのプロパティ名を返します。
         *
         * @return closeplandateのプロパティ名
         */
        public PropertyName<Date> closeplandate() {
            return new PropertyName<Date>(this, "closeplandate");
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
         * mmtrlflgxdeptのプロパティ名を返します。
         * 
         * @return mmtrlflgxdeptのプロパティ名
         */
        public _MmtrlflgxdeptNames mmtrlflgxdept() {
            return new _MmtrlflgxdeptNames(this, "mmtrlflgxdept");
        }
    }
}
