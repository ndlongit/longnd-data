package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TactmonthttlDto;
import jp.co.inte.crm.web.dto.TactmonthttlParamDto;

/**
 * <pre>
 * tactmonthttl(アクション状況月個人集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TactmonthttlService {

    /**
     * アクション状況月個人集計情報を返却する
     * 
     * @param tactmonthttlParamDto 検索条件
     * @return List<TactmonthttlDto> アクション状況月個人集計
     */
    List<TactmonthttlDto> getActTotalList(TactmonthttlParamDto tactmonthttlParamDto);

}
