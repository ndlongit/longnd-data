package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Tclaim プロパティ名の集合 ＜利用者拡張用＞
 */
public class TclaimNames extends TclaimBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _TclaimNames extends _TclaimBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _TclaimNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _TclaimNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _TclaimNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
