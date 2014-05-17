package jp.co.inte.crm.common.service.impl;

import javax.annotation.Resource;

import jp.co.inte.crm.common.dao.CrmSequenceDao;
import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.common.service.CrmSequenceService;

/**
 * 採番サービスの実装クラス
 * 
 */
public class CrmSequenceServiceImpl extends CrmBaseService implements CrmSequenceService {

    /** シーケンスDAO */
    @Resource
    private CrmSequenceDao crmSequenceDao;

    /** 接頭語 法人担当者ID */
    private static final String PREFIX_HOCGID = "HO";

    /**
     * 法人担当者IDの採番処理
     * 
     * @inheritDoc
     * @return String 採番された法人担当者ID
     */
    @Override
    public String createSeqNextValHocgid() {
        // シーケンス取得
        int hocgid = crmSequenceDao.createSeqNextVal("hocgid");
        return PREFIX_HOCGID + String.format("%1$08d", hocgid);
    }

}
