package jp.co.inte.crm.common.entity.names;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tclaim;
import jp.co.inte.crm.common.entity.names.McltxclaimNames._McltxclaimNames;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Tclaim}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class TclaimBaseNames {

    /**
     * claimidのプロパティ名を返します。
     * 
     * @return claimidのプロパティ名
     */
    public static PropertyName<String> claimid() {
        return new PropertyName<String>("claimid");
    }

    /**
     * occurdtのプロパティ名を返します。
     * 
     * @return occurdtのプロパティ名
     */
    public static PropertyName<Date> occurdt() {
        return new PropertyName<Date>("occurdt");
    }

    /**
     * claimstskbnのプロパティ名を返します。
     * 
     * @return claimstskbnのプロパティ名
     */
    public static PropertyName<Short> claimstskbn() {
        return new PropertyName<Short>("claimstskbn");
    }

    /**
     * contactformkbnのプロパティ名を返します。
     * 
     * @return contactformkbnのプロパティ名
     */
    public static PropertyName<Short> contactformkbn() {
        return new PropertyName<Short>("contactformkbn");
    }

    /**
     * hocgnmのプロパティ名を返します。
     * 
     * @return hocgnmのプロパティ名
     */
    public static PropertyName<String> hocgnm() {
        return new PropertyName<String>("hocgnm");
    }

    /**
     * answercgnmのプロパティ名を返します。
     * 
     * @return answercgnmのプロパティ名
     */
    public static PropertyName<String> answercgnm() {
        return new PropertyName<String>("answercgnm");
    }

    /**
     * claimcontentsのプロパティ名を返します。
     * 
     * @return claimcontentsのプロパティ名
     */
    public static PropertyName<String> claimcontents() {
        return new PropertyName<String>("claimcontents");
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
     * mcltxclaimListのプロパティ名を返します。
     * 
     * @return mcltxclaimListのプロパティ名
     */
    public static _McltxclaimNames mcltxclaimList() {
        return new _McltxclaimNames("mcltxclaimList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TclaimBaseNames extends PropertyName<Tclaim> {

        /**
         * インスタンスを構築します。
         */
        public _TclaimBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TclaimBaseNames(final String name) {
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
        public _TclaimBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * claimidのプロパティ名を返します。
         *
         * @return claimidのプロパティ名
         */
        public PropertyName<String> claimid() {
            return new PropertyName<String>(this, "claimid");
        }

        /**
         * occurdtのプロパティ名を返します。
         *
         * @return occurdtのプロパティ名
         */
        public PropertyName<Date> occurdt() {
            return new PropertyName<Date>(this, "occurdt");
        }

        /**
         * claimstskbnのプロパティ名を返します。
         *
         * @return claimstskbnのプロパティ名
         */
        public PropertyName<Short> claimstskbn() {
            return new PropertyName<Short>(this, "claimstskbn");
        }

        /**
         * contactformkbnのプロパティ名を返します。
         *
         * @return contactformkbnのプロパティ名
         */
        public PropertyName<Short> contactformkbn() {
            return new PropertyName<Short>(this, "contactformkbn");
        }

        /**
         * hocgnmのプロパティ名を返します。
         *
         * @return hocgnmのプロパティ名
         */
        public PropertyName<String> hocgnm() {
            return new PropertyName<String>(this, "hocgnm");
        }

        /**
         * answercgnmのプロパティ名を返します。
         *
         * @return answercgnmのプロパティ名
         */
        public PropertyName<String> answercgnm() {
            return new PropertyName<String>(this, "answercgnm");
        }

        /**
         * claimcontentsのプロパティ名を返します。
         *
         * @return claimcontentsのプロパティ名
         */
        public PropertyName<String> claimcontents() {
            return new PropertyName<String>(this, "claimcontents");
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
         * mcltxclaimListのプロパティ名を返します。
         * 
         * @return mcltxclaimListのプロパティ名
         */
        public _McltxclaimNames mcltxclaimList() {
            return new _McltxclaimNames(this, "mcltxclaimList");
        }
    }
}
