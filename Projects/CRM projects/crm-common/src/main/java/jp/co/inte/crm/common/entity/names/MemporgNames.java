package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Memporg プロパティ名の集合 ＜利用者拡張用＞
 */
public class MemporgNames extends MemporgBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _MemporgNames extends _MemporgBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _MemporgNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _MemporgNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _MemporgNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
