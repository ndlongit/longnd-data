package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TmtrlmttlDto;
import jp.co.inte.crm.web.dto.TmtrlmttlParamDto;

/**
 * <pre>
 * tmtrlmttl(ネタ月個人集計)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TmtrlmttlService {

    /**
     * ネタ月個人集計
     * 個人のネタ集計情報を返却する
     * 
     * @param tmtrlmttlParamDto 検索条件
     * @return List<TmtrlmttlDto> ネタ月個人集計一覧
     */
    List<TmtrlmttlDto> getMaterialTotalList(TmtrlmttlParamDto tmtrlmttlParamDto);

}
