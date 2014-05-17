package jp.co.inte.crm.common.dao;

import jp.co.inte.crm.common.entity.CrmSequenceEntity;

/**
 * シーケンスDAO
 * 
 */
public class CrmSequenceDao extends CrmBaseDao<CrmSequenceEntity> {

    /**
     * シーケンスオブジェクトの値を取得します。
     * 
     * @param sequenceName シーケンス名
     * @return int シーケンスオブジェクトの値
     */
    public int createSeqNextVal(String sequenceName) {
        return super.createSeqNextVal(Integer.class, sequenceName);
    }
}
