package jp.co.inte.crm.batch.action.cpt;

import javax.annotation.Resource;

import jp.co.inte.crm.batch.service.cpt.SCPTD01002Service;
import jp.co.inte.crm.common.action.CrmBaseBatchAction;
import jp.co.inte.crm.common.dto.batch.cpt.SCPTD01002Dto;
import jp.co.inte.cspfw.batch.constants.BatchConstants;
import jp.co.inte.cspfw.batch.log.FwBatchLogManager;

/**
 * データ取込バッチ（法人担当者）Action
 * 
 */
public class SCPTD01002Action extends CrmBaseBatchAction {

    /** ロガー */
    private static final FwBatchLogManager LOG = new FwBatchLogManager(SCPTD01002Action.class);

    /** バッチ名 */
    private static final String BATCH_NAME = "データ取込バッチ（法人担当者）";

    /** 法人担当者取込ロジック */
    @Resource
    private SCPTD01002Service scptd01002Service;

    /** 法人担当者取込DTO */
    @Resource
    private SCPTD01002Dto scptd01002Dto;

    /**
     * 実行メソッド
     * 
     * @return int 処理結果
     */
    public int execute() {

        try {

            // 処理開始ログ
            LOG.infoLog("execute", super.getMessageBatchStartLog(BATCH_NAME));

            // 取込メイン処理
            int result = scptd01002Service.importMain(scptd01002Dto);

            // エラーログ出力
            super.writeImportErrorLog(LOG, scptd01002Dto.importErrorList);

            // 処理終了ログ
            LOG.infoLog("execute", super.getMessageBatchEndLog(BATCH_NAME));

            return result;

        } catch (Exception e) {
            LOG.errorLog(e);
            // 処理終了ログ
            LOG.infoLog("execute", super.getMessageBatchErrorEndLog(BATCH_NAME));
            return BatchConstants.ERROR;
        }

    }
}
