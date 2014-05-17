package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Mclt プロパティ名の集合 ＜利用者拡張用＞
 */
public class McltNames extends McltBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _McltNames extends _McltBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _McltNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _McltNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _McltNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
