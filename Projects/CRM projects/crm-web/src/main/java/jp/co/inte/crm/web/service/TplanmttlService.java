package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TplanmttlDto;
import jp.co.inte.crm.web.dto.TplanmttlParamDto;

/**
 * <pre>
 * tplanmttl(計画月個人集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TplanmttlService {

    /**
     * 計画月個人集計
     * 個人の計画月集計を返却する。
     * 
     * @param tplanmttlParamDto 検索条件
     * @return List<TplanmttlDto> 計画月個人集計一覧
     */
    List<TplanmttlDto> getPlanTotalList(TplanmttlParamDto tplanmttlParamDto);

    /**
     * 計画月個人計画登録
     * 個人の計画月集計欄の計画を登録する。
     * 
     * @param tplanmttlDto 売上ネタ個人計画
     * @return 更新した行数
     */
    int regist(TplanmttlDto tplanmttlDto);

}
