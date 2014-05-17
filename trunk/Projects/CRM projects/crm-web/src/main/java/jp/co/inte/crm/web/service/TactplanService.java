package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TactplanDto;

/**
 * <pre>
 * tactplan(活動予定)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TactplanService {

    /**
     * 活動予定・振り返り一覧
     * 活動予定・振り返り情報一覧を返す
     * 
     * @param empcd 社員CD
     * @return List<TactplanDto> 活動予定一覧
     */
    List<TactplanDto> getActPlanList(String empcd);

    /**
     * 活動予定・振り返り情報
     * 活動予定・振り返りの情報を返す
     * 
     * @param empcd 社員CD
     * @param actplanno 活動予定No
     * @return TactplanDto 活動予定
     */
    TactplanDto findById(String empcd, int actplanno);

    /**
     * 活動予定・振り返り 登録
     * 活動予定・振り返りの情報を登録する。
     * 
     * @param actplan 活動予定・振り返り
     * @return 更新した行数
     */
    int regist(TactplanDto actplan);
}
