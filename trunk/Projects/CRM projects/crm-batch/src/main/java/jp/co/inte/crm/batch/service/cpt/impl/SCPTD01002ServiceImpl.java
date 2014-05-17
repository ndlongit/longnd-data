package jp.co.inte.crm.batch.service.cpt.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.batch.service.cpt.SCPTD01002Service;
import jp.co.inte.crm.common.dao.MadrcdDao;
import jp.co.inte.crm.common.dao.MhoDao;
import jp.co.inte.crm.common.dao.MhocgDao;
import jp.co.inte.crm.common.dao.WkhocgDao;
import jp.co.inte.crm.common.dto.batch.ImportErrorDto;
import jp.co.inte.crm.common.dto.batch.cpt.SCPTD01002Dto;
import jp.co.inte.crm.common.entity.Mhocg;
import jp.co.inte.crm.common.entity.WkhocgEntity;
import jp.co.inte.crm.common.service.CrmBaseService;
import jp.co.inte.crm.common.service.CrmSequenceService;
import jp.co.inte.cspfw.batch.constants.BatchConstants;
import jp.co.inte.cspfw.batch.log.FwBatchLogManager;
import jp.co.inte.cspfw.batch.util.FwBatchMessageUtils;
import jp.co.inte.cspfw.util.FwListUtils;
import jp.co.inte.cspfw.util.FwStringUtils;

import org.seasar.framework.beans.util.Beans;

/**
 * 法人担当者取込ロジックの実装クラス
 * 
 */
public class SCPTD01002ServiceImpl extends CrmBaseService implements SCPTD01002Service {

    /** ロガー */
    private static final FwBatchLogManager LOG = new FwBatchLogManager(SCPTD01002Service.class);

    /** 法人マスタDAO */
    @Resource
    private MhoDao mhoDao;

    /** 住所コードマスタDAO */
    @Resource
    private MadrcdDao madrcdDao;

    /** 法人担当者マスタDAO */
    @Resource
    private MhocgDao mhocgDao;

    /** WK法人担当者取込DAO */
    @Resource
    private WkhocgDao wkhocgDao;

    /** 採番サービス */
    @Resource
    private CrmSequenceService crmSequenceService;

    /**
     * 取込処理
     * 
     * @inheritDoc
     * @param scptd01002Dto 法人担当者取込DTO
     * @return int 処理結果
     */
    @Override
    public int importMain(SCPTD01002Dto scptd01002Dto) {

        try {

            // ワークテーブルデータ取得
            List<WkhocgEntity> wkhocgEntityList = getWkhocgEntityList();

            // 取込対象が0件の場合、処理終了
            if (FwListUtils.isEmpty(wkhocgEntityList)) {
                LOG.infoLog("importMain",
                        FwBatchMessageUtils.getMessage("errors.WCRM0003", "WK法人担当者取込テーブル"));
                return BatchConstants.WARN;
            }

            // 取込処理
            importData(scptd01002Dto, wkhocgEntityList);

            // WK法人担当者取込データ削除
            deleteWkhocg(wkhocgEntityList);

            return BatchConstants.SUCCESS;

        } catch (Exception e) {
            LOG.infoLog("importMain",
                    FwBatchMessageUtils.getMessage("errors.SCRM0001", "法人担当者の取込処理"));
            throw e;
        }
    }

    /**
     * WK法人担当者取込データ取得
     * 
     * @return List<WkhocgEntity> WK法人担当者取込エンティティリスト
     */
    private List<WkhocgEntity> getWkhocgEntityList() {

        try {

            LOG.infoLog("getWkhocgEntityList",
                    FwBatchMessageUtils.getMessage("errors.ICRM0016", "WK法人担当者取込"));

            return wkhocgDao.findAll();

        } finally {
            LOG.infoLog("getWkhocgEntityList",
                    FwBatchMessageUtils.getMessage("errors.ICRM0017", "WK法人担当者取込"));
        }
    }

    /**
     * データ取込処理
     * 
     * @param scptd01002Dto 法人担当者取込DTO
     * @param wkhocgEntityList WK法人担当者取込エンティティリスト
     */
    private void importData(SCPTD01002Dto scptd01002Dto,
            List<WkhocgEntity> wkhocgEntityList) {

        // 取込処理開始
        LOG.infoLog("importData",
                FwBatchMessageUtils.getMessage("errors.ICRM0018", "WK法人担当者取込"));

        // エラーリスト
        List<ImportErrorDto> errorList = new ArrayList<ImportErrorDto>();

        // 登録件数
        int registCount = 0;

        for (WkhocgEntity wkhocgEntity : wkhocgEntityList) {

            // 取込データチェック
            errorList.addAll(checkData(wkhocgEntity));

            // エラーが存在する場合、スキップ
            if (!FwListUtils.isEmpty(errorList)) {
                continue;
            }

            // 法人担当者マスタ登録処理
            registCount += mhocgDao.insert(createInsertMhocg(wkhocgEntity));
        }

        // 登録件数ログ出力
        LOG.infoLog("importData",
                FwBatchMessageUtils.getMessage("errors.ICRM0013", "法人担当者マスタ", String.valueOf(registCount)));

        // エラーリスト
        scptd01002Dto.importErrorList = errorList;

        // 取込処理終了
        LOG.infoLog("importData",
                FwBatchMessageUtils.getMessage("errors.ICRM0019", "WK法人担当者取込"));
    }

    /**
     * WK法人担当者取込データ削除
     * 
     * @param wkhocgEntityList WK法人担当者取込エンティティリスト
     */
    private void deleteWkhocg(List<WkhocgEntity> wkhocgEntityList) {

        try {

            LOG.infoLog("deleteWkhocg",
                    FwBatchMessageUtils.getMessage("errors.ICRM0001", "WK法人担当者取込テーブルの削除"));

            // 削除処理
            int result = wkhocgDao.delete(wkhocgEntityList);
            LOG.infoLog("deleteWkhocg",
                    FwBatchMessageUtils.getMessage("errors.ICRM0015", "WK法人担当者取込テーブル", String.valueOf(result)));

        } finally {
            LOG.infoLog("deleteWkhocg",
                    FwBatchMessageUtils.getMessage("errors.ICRM0002", "WK法人担当者取込テーブルの削除"));
        }
    }

    /**
     * 取込データチェック処理
     * 
     * @param wkhocgEntity WK法人担当者取込エンティティ
     * @return List<ImportErrorDto> 取込除外リスト
     */
    private List<ImportErrorDto> checkData(WkhocgEntity wkhocgEntity) {

        List<ImportErrorDto> errorList = new ArrayList<ImportErrorDto>();

        // 法人IDの存在チェック
        if (mhoDao.findById(wkhocgEntity.hoid) == null) {
            ImportErrorDto errorDto = new ImportErrorDto();
            errorDto.message = FwBatchMessageUtils.getMessage(
                    "errors.OCRM0005", "法人ID", wkhocgEntity.hoid);
            errorDto.record = wkhocgEntity;
            errorList.add(errorDto);
        }

        // 住所CDの存在チェック
        if (!FwStringUtils.isEmpty(wkhocgEntity.adcd)) {
            if (madrcdDao.findById(wkhocgEntity.adcd) == null) {
                ImportErrorDto errorDto = new ImportErrorDto();
                errorDto.message = FwBatchMessageUtils.getMessage(
                        "errors.OCRM0006", "住所CD", wkhocgEntity.adcd);
                errorDto.record = wkhocgEntity;
                errorList.add(errorDto);
            }
        }

        // 法人担当者カナの重複チェック
        if (mhocgDao.findByKey(wkhocgEntity.hoid,
                wkhocgEntity.hocgkanalast, wkhocgEntity.hocgkanafirst) != null) {
            ImportErrorDto errorDto = new ImportErrorDto();
            errorDto.message = FwBatchMessageUtils.getMessage(
                    "errors.OCRM0003", "法人担当者名カナ",
                    wkhocgEntity.hocgkanalast + wkhocgEntity.hocgkanafirst);
            errorDto.record = wkhocgEntity;
            errorList.add(errorDto);
        }

        return errorList;
    }

    /**
     * 登録用の法人担当者マスタエンティティ生成
     * 
     * @param wkhocgEntity WK法人担当者取込エンティティ
     * @return Mhocg 登録用の法人担当者マスタエンティティ
     */
    private Mhocg createInsertMhocg(WkhocgEntity wkhocgEntity) {

        Mhocg mhocg = Beans.createAndCopy(Mhocg.class, wkhocgEntity)
                .excludesNull().excludesWhitespace().execute();

        // 法人担当者ID採番
        mhocg.hocgid = crmSequenceService.createSeqNextValHocgid();

        return mhocg;
    }

}
