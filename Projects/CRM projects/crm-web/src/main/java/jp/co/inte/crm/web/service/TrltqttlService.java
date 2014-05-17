package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TrltqttlDto;
import jp.co.inte.crm.web.dto.TrltqttlParamDto;

/**
 * <pre>
 * trltqttl(実績Q個人集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TrltqttlService {

    /**
     * 実績Q個人集計
     * 個人の実績Q集計を返却する
     * 
     * @param trltqttlParamDto 検索条件
     * @return List<Trltqttl> 実績Q個人集計一覧
     */
    List<TrltqttlDto> getResultTotalList(TrltqttlParamDto trltqttlParamDto);

    /**
     * 実績Q個人集計を登録する
     * 
     * @param trltqttl 実績Q個人集計
     * @return 更新した行数
     */
    int regist(TrltqttlDto trltqttl);

}
