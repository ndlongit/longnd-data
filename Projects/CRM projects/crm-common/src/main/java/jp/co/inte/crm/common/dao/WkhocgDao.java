package jp.co.inte.crm.common.dao;

import java.util.List;

import jp.co.inte.crm.common.entity.WkhocgEntity;
import jp.co.inte.cspfw.util.FwListUtils;

/**
 * Wkhocg Daoクラス ＜利用者拡張用＞
 */
public class WkhocgDao extends WkhocgBaseDao {

    /**
     * 削除処理
     * 
     * @param entities エンティティリスト
     * @return int 削除件数
     */
    public int delete(List<WkhocgEntity> entities) {

        if (FwListUtils.isEmpty(entities)) {
            return 0;
        }

        int result = 0;
        for (WkhocgEntity entity : entities) {
            result += delete(entity);
        }

        return result;
    }
}
