package jp.co.inte.crm.common.entity.names;

import java.sql.Timestamp;

import javax.annotation.Generated;

import jp.co.inte.crm.common.entity.WkhocgEntity;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link WkhocgEntity}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl" },
        date = "2014/05/16 11:28:38")
public class WkhocgBaseNames {

    /**
     * importnoのプロパティ名を返します。
     * 
     * @return importnoのプロパティ名
     */
    public static PropertyName<Integer> importno() {
        return new PropertyName<Integer>("importno");
    }

    /**
     * hoidのプロパティ名を返します。
     * 
     * @return hoidのプロパティ名
     */
    public static PropertyName<String> hoid() {
        return new PropertyName<String>("hoid");
    }

    /**
     * hocgnmlastのプロパティ名を返します。
     * 
     * @return hocgnmlastのプロパティ名
     */
    public static PropertyName<String> hocgnmlast() {
        return new PropertyName<String>("hocgnmlast");
    }

    /**
     * hocgnmfirstのプロパティ名を返します。
     * 
     * @return hocgnmfirstのプロパティ名
     */
    public static PropertyName<String> hocgnmfirst() {
        return new PropertyName<String>("hocgnmfirst");
    }

    /**
     * hocgkanalastのプロパティ名を返します。
     * 
     * @return hocgkanalastのプロパティ名
     */
    public static PropertyName<String> hocgkanalast() {
        return new PropertyName<String>("hocgkanalast");
    }

    /**
     * hocgkanafirstのプロパティ名を返します。
     * 
     * @return hocgkanafirstのプロパティ名
     */
    public static PropertyName<String> hocgkanafirst() {
        return new PropertyName<String>("hocgkanafirst");
    }

    /**
     * postのプロパティ名を返します。
     * 
     * @return postのプロパティ名
     */
    public static PropertyName<String> post() {
        return new PropertyName<String>("post");
    }

    /**
     * positionのプロパティ名を返します。
     * 
     * @return positionのプロパティ名
     */
    public static PropertyName<String> position() {
        return new PropertyName<String>("position");
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
     * cellphoneのプロパティ名を返します。
     * 
     * @return cellphoneのプロパティ名
     */
    public static PropertyName<String> cellphone() {
        return new PropertyName<String>("cellphone");
    }

    /**
     * mailのプロパティ名を返します。
     * 
     * @return mailのプロパティ名
     */
    public static PropertyName<String> mail() {
        return new PropertyName<String>("mail");
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
     * adr02のプロパティ名を返します。
     * 
     * @return adr02のプロパティ名
     */
    public static PropertyName<String> adr02() {
        return new PropertyName<String>("adr02");
    }

    /**
     * adcdのプロパティ名を返します。
     * 
     * @return adcdのプロパティ名
     */
    public static PropertyName<String> adcd() {
        return new PropertyName<String>("adcd");
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
    public static class _WkhocgBaseNames extends PropertyName<WkhocgEntity> {

        /**
         * インスタンスを構築します。
         */
        public _WkhocgBaseNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _WkhocgBaseNames(final String name) {
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
        public _WkhocgBaseNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * importnoのプロパティ名を返します。
         * 
         * @return importnoのプロパティ名
         */
        public PropertyName<Integer> importno() {
            return new PropertyName<Integer>(this, "importno");
        }

        /**
         * hoidのプロパティ名を返します。
         * 
         * @return hoidのプロパティ名
         */
        public PropertyName<String> hoid() {
            return new PropertyName<String>(this, "hoid");
        }

        /**
         * hocgnmlastのプロパティ名を返します。
         * 
         * @return hocgnmlastのプロパティ名
         */
        public PropertyName<String> hocgnmlast() {
            return new PropertyName<String>(this, "hocgnmlast");
        }

        /**
         * hocgnmfirstのプロパティ名を返します。
         * 
         * @return hocgnmfirstのプロパティ名
         */
        public PropertyName<String> hocgnmfirst() {
            return new PropertyName<String>(this, "hocgnmfirst");
        }

        /**
         * hocgkanalastのプロパティ名を返します。
         * 
         * @return hocgkanalastのプロパティ名
         */
        public PropertyName<String> hocgkanalast() {
            return new PropertyName<String>(this, "hocgkanalast");
        }

        /**
         * hocgkanafirstのプロパティ名を返します。
         * 
         * @return hocgkanafirstのプロパティ名
         */
        public PropertyName<String> hocgkanafirst() {
            return new PropertyName<String>(this, "hocgkanafirst");
        }

        /**
         * postのプロパティ名を返します。
         * 
         * @return postのプロパティ名
         */
        public PropertyName<String> post() {
            return new PropertyName<String>(this, "post");
        }

        /**
         * positionのプロパティ名を返します。
         * 
         * @return positionのプロパティ名
         */
        public PropertyName<String> position() {
            return new PropertyName<String>(this, "position");
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
         * cellphoneのプロパティ名を返します。
         * 
         * @return cellphoneのプロパティ名
         */
        public PropertyName<String> cellphone() {
            return new PropertyName<String>(this, "cellphone");
        }

        /**
         * mailのプロパティ名を返します。
         * 
         * @return mailのプロパティ名
         */
        public PropertyName<String> mail() {
            return new PropertyName<String>(this, "mail");
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
         * adr02のプロパティ名を返します。
         * 
         * @return adr02のプロパティ名
         */
        public PropertyName<String> adr02() {
            return new PropertyName<String>(this, "adr02");
        }

        /**
         * adcdのプロパティ名を返します。
         * 
         * @return adcdのプロパティ名
         */
        public PropertyName<String> adcd() {
            return new PropertyName<String>(this, "adcd");
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
