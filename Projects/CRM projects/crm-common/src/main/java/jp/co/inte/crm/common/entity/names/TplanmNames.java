package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Tplanm プロパティ名の集合 ＜利用者拡張用＞
 */
public class TplanmNames extends TplanmBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _TplanmNames extends _TplanmBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _TplanmNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _TplanmNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _TplanmNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
