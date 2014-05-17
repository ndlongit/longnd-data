package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Magt プロパティ名の集合 ＜利用者拡張用＞
 */
public class MagtNames extends MagtBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _MagtNames extends _MagtBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _MagtNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _MagtNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _MagtNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
