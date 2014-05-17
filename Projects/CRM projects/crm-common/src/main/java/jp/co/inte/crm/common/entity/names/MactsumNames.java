package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Mactsum プロパティ名の集合 ＜利用者拡張用＞
 */
public class MactsumNames extends MactsumBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _MactsumNames extends _MactsumBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _MactsumNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _MactsumNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _MactsumNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
