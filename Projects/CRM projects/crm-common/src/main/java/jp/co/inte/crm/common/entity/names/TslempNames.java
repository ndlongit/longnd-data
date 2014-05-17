package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Tslemp プロパティ名の集合 ＜利用者拡張用＞
 */
public class TslempNames extends TslempBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _TslempNames extends _TslempBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _TslempNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _TslempNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _TslempNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
