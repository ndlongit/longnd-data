package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Tplanq プロパティ名の集合 ＜利用者拡張用＞
 */
public class TplanqNames extends TplanqBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _TplanqNames extends _TplanqBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _TplanqNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _TplanqNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _TplanqNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
