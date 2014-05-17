package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Madrcd プロパティ名の集合 ＜利用者拡張用＞
 */
public class MadrcdNames extends MadrcdBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _MadrcdNames extends _MadrcdBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _MadrcdNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _MadrcdNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _MadrcdNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
