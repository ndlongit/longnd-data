package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TplanqttlDto;
import jp.co.inte.crm.web.dto.TplanqttlParamDto;

/**
 * <pre>
 * tplanqttl(計画Q個人集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TplanqttllService {

    /**
     * 計画Q個人集計
     * 個人の計画Q集計を返却する。
     * 
     * @param tplanqttlParamDto 検索条件
     * @return List<TplanqttlDto> 計画Q個人集計一覧
     */
    List<TplanqttlDto> getPlanTotalList(TplanqttlParamDto tplanqttlParamDto);

    /**
     * 計画Q個人計画登録
     * 個人の計画Q集計欄の計画を登録する。
     * 
     * @param tplanqttlDto 計画Q個人集計
     * @return 更新した行数
     */
    int regist(TplanqttlDto tplanqttlDto);

}
