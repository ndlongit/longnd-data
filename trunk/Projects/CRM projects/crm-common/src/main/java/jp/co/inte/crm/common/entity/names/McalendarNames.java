package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Mcalendar プロパティ名の集合 ＜利用者拡張用＞
 */
public class McalendarNames extends McalendarBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _McalendarNames extends _McalendarBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _McalendarNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _McalendarNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _McalendarNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
