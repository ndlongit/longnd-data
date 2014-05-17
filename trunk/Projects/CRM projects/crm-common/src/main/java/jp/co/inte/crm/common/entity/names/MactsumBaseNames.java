package jp.co.inte.crm.common.entity.names;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mactsum;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Mactsum}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
           date = "2014/05/13 17:07:21")
public class MactsumBaseNames {

    /**
     * ctrldeptnoのプロパティ名を返します。
     * 
     * @return ctrldeptnoのプロパティ名
     */
    public static PropertyName<String> ctrldeptno() {
        return new PropertyName<String>("ctrldeptno");
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
    public static PropertyName<BigDecimal> quarter() {
        return new PropertyName<BigDecimal>("quarter");
    }

    /**
     * flg1のプロパティ名を返します。
     * 
     * @return flg1のプロパティ名
     */
    public static PropertyName<Boolean> flg1() {
        return new PropertyName<Boolean>("flg1");
    }

    /**
     * flg2のプロパティ名を返します。
     * 
     * @return flg2のプロパティ名
     */
    public static PropertyName<Boolean> flg2() {
        return new PropertyName<Boolean>("flg2");
    }

    /**
     * flg3のプロパティ名を返します。
     * 
     * @return flg3のプロパティ名
     */
    public static PropertyName<Boolean> flg3() {
        return new PropertyName<Boolean>("flg3");
    }

    /**
     * flg4のプロパティ名を返します。
     * 
     * @return flg4のプロパティ名
     */
    public static PropertyName<Boolean> flg4() {
        return new PropertyName<Boolean>("flg4");
    }

    /**
     * flg5のプロパティ名を返します。
     * 
     * @return flg5のプロパティ名
     */
    public static PropertyName<Boolean> flg5() {
        return new PropertyName<Boolean>("flg5");
    }

    /**
     * flg6のプロパティ名を返します。
     * 
     * @return flg6のプロパティ名
     */
    public static PropertyName<Boolean> flg6() {
        return new PropertyName<Boolean>("flg6");
    }

    /**
     * flg7のプロパティ名を返します。
     * 
     * @return flg7のプロパティ名
     */
    public static PropertyName<Boolean> flg7() {
        return new PropertyName<Boolean>("flg7");
    }

    /**
     * flg8のプロパティ名を返します。
     * 
     * @return flg8のプロパティ名
     */
    public static PropertyName<Boolean> flg8() {
        return new PropertyName<Boolean>("flg8");
    }

    /**
     * flg9のプロパティ名を返します。
     * 
     * @return flg9のプロパティ名
     */
    public static PropertyName<Boolean> flg9() {
        return new PropertyName<Boolean>("flg9");
    }

    /**
     * flg10のプロパティ名を返します。
     * 
     * @return flg10のプロパティ名
     */
    public static PropertyName<Boolean> flg10() {
        return new PropertyName<Boolean>("flg10");
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
    public static class _MactsumBaseNames extends PropertyName<Mactsum> {

        /**
         * インスタンスを構築します。
         */
        public _MactsumBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MactsumBaseNames(final String name) {
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
        public _MactsumBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * ctrldeptnoのプロパティ名を返します。
         *
         * @return ctrldeptnoのプロパティ名
         */
        public PropertyName<String> ctrldeptno() {
            return new PropertyName<String>(this, "ctrldeptno");
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
        public PropertyName<BigDecimal> quarter() {
            return new PropertyName<BigDecimal>(this, "quarter");
        }

        /**
         * flg1のプロパティ名を返します。
         *
         * @return flg1のプロパティ名
         */
        public PropertyName<Boolean> flg1() {
            return new PropertyName<Boolean>(this, "flg1");
        }

        /**
         * flg2のプロパティ名を返します。
         *
         * @return flg2のプロパティ名
         */
        public PropertyName<Boolean> flg2() {
            return new PropertyName<Boolean>(this, "flg2");
        }

        /**
         * flg3のプロパティ名を返します。
         *
         * @return flg3のプロパティ名
         */
        public PropertyName<Boolean> flg3() {
            return new PropertyName<Boolean>(this, "flg3");
        }

        /**
         * flg4のプロパティ名を返します。
         *
         * @return flg4のプロパティ名
         */
        public PropertyName<Boolean> flg4() {
            return new PropertyName<Boolean>(this, "flg4");
        }

        /**
         * flg5のプロパティ名を返します。
         *
         * @return flg5のプロパティ名
         */
        public PropertyName<Boolean> flg5() {
            return new PropertyName<Boolean>(this, "flg5");
        }

        /**
         * flg6のプロパティ名を返します。
         *
         * @return flg6のプロパティ名
         */
        public PropertyName<Boolean> flg6() {
            return new PropertyName<Boolean>(this, "flg6");
        }

        /**
         * flg7のプロパティ名を返します。
         *
         * @return flg7のプロパティ名
         */
        public PropertyName<Boolean> flg7() {
            return new PropertyName<Boolean>(this, "flg7");
        }

        /**
         * flg8のプロパティ名を返します。
         *
         * @return flg8のプロパティ名
         */
        public PropertyName<Boolean> flg8() {
            return new PropertyName<Boolean>(this, "flg8");
        }

        /**
         * flg9のプロパティ名を返します。
         *
         * @return flg9のプロパティ名
         */
        public PropertyName<Boolean> flg9() {
            return new PropertyName<Boolean>(this, "flg9");
        }

        /**
         * flg10のプロパティ名を返します。
         *
         * @return flg10のプロパティ名
         */
        public PropertyName<Boolean> flg10() {
            return new PropertyName<Boolean>(this, "flg10");
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
