package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Mho プロパティ名の集合 ＜利用者拡張用＞
 */
public class MhoNames extends MhoBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _MhoNames extends _MhoBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _MhoNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _MhoNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _MhoNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
