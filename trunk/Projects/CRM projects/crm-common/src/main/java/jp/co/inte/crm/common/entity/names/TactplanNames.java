package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Tactplan プロパティ名の集合 ＜利用者拡張用＞
 */
public class TactplanNames extends TactplanBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _TactplanNames extends _TactplanBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _TactplanNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _TactplanNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _TactplanNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
