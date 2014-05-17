package jp.co.inte.crm.common.entity.names;

import org.seasar.extension.jdbc.name.PropertyName;

/**
 * Ttarget プロパティ名の集合 ＜利用者拡張用＞
 */
public class TtargetNames extends TtargetBaseNames {

    /**
     * @author S2JDBC-Gen
     */
    public static class _TtargetNames extends _TtargetBaseNames {

        /**
         * インスタンスを構築します。
         */
        public _TtargetNames() {
            super();
        }

        /**
         * インスタンスを構築します。
         * @param name 名前
         */
        public _TtargetNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * @param parent 親
         * @param name 名前
         */
        public _TtargetNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

    }

}
