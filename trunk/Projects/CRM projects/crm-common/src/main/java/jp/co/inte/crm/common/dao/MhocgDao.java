package jp.co.inte.crm.common.dao;

import jp.co.inte.crm.common.entity.Mhocg;
import jp.co.inte.cspfw.util.FwStringUtils;

/**
 * Mhocg Daoクラス ＜利用者拡張用＞
 */
public class MhocgDao extends MhocgBaseDao {

    /**
     * 法人ID、法人担当者カナ姓、法人担当者カナ名で検索します。
     * 引数のいずれかがnullの場合、nullを返却します。
     * 
     * @param hoid 法人ID
     * @param hocgkanalast 法人担当者カナ姓
     * @param hocgkanafirst 法人担当者カナ名
     * @return Mhocg 法人担当者マスタ
     */
    public Mhocg findByKey(String hoid, String hocgkanalast, String hocgkanafirst) {

        // いずれかがnullの場合
        if (FwStringUtils.isEmpty(hoid)
                || FwStringUtils.isEmpty(hocgkanalast)
                || FwStringUtils.isEmpty(hocgkanafirst)) {
            return null;
        }

        return select().where(
                "hoid = ? and hocgkanalast = ? and hocgkanafirst = ?",
                hoid, hocgkanalast, hocgkanafirst).getSingleResult();
    }
}
