package jp.co.inte.crm.web.service;

import java.util.Date;
import java.util.List;

import jp.co.inte.crm.web.dto.TapproachplanhisDto;
import jp.co.inte.crm.web.dto.TapproachplanhisParamDto;

/**
 * <pre>
 * tapproachplanhis(接触予定履歴)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TapproachplanhisService {

    /**
     * 本日の接触予定履歴を返却する
     * 
     * @param loginid ログインID
     * @param date 日付
     * @return List<TapproachplanhisDto> 接触予定履歴
     */
    List<TapproachplanhisDto> getTapproachplanhisList(String loginid, Date date);

    /**
     * 接触予定履歴を返却する
     * 
     * @param tapproachplanhisParamDto 検索条件パラメータ
     * @return List<TapproachplanhisDto> 接触予定履歴
     */
    List<TapproachplanhisDto> getTapproList(TapproachplanhisParamDto tapproachplanhisParamDto);

    /**
     * 接触予定履歴登録
     * 
     * @param tapproachplanhisDto 接触予定履歴
     * @return 更新した行数
     */
    int regist(TapproachplanhisDto tapproachplanhisDto);

}
