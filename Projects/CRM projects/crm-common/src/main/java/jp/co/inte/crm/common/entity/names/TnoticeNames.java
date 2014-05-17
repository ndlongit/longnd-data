package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Tnotice プロパティ名の集合 ＜利用者拡張用＞
 */
public class TnoticeNames extends TnoticeBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _TnoticeNames extends _TnoticeBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _TnoticeNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _TnoticeNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _TnoticeNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
