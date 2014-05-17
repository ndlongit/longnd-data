package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TrltmttlDto;
import jp.co.inte.crm.web.dto.TrltmttlParamDto;

/**
 * <pre>
 * trltmttl(実績月個人集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TrltmttlService {

    /**
     * 実績月個人集計
     * 個人の実績月集計を返却する
     * 
     * @param trltmttlParamDto 検索条件パラーメタ
     * @return List<TrltmttlDto> 実績月個人集計一覧
     */
    List<TrltmttlDto> getResultTotalList(TrltmttlParamDto trltmttlParamDto);

    /**
     * 実績月個人集計登録
     * 売実績月個人集計を登録する。
     * 
     * @param trltmttlDto 売実績月個人集計
     * @return 更新した行数
     */
    int regist(TrltmttlDto trltmttlDto);

}
