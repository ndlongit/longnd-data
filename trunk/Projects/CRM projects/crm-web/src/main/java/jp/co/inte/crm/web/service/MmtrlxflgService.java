package jp.co.inte.crm.web.service;

import jp.co.inte.crm.web.dto.MmtrlxflgDto;

/**
 * <pre>
 * materialflgrlt(売上ネタフラグ紐付)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface MmtrlxflgService {

    /**
     * ネタフラグの一覧を返す
     * 
     * @param ctrlassignframe 統括別 割り振り枠
     * @param cltid 顧客
     * @return Mmtrlxflg 売上ネタフラグ紐付
     */
    MmtrlxflgDto getMaterialFlgList(int ctrlassignframe, int cltid);

    /**
     * ネタフラグを登録する
     * 
     * @param mmtrlxflgDto 売上ネタフラグ紐付
     * @return 更新した行数
     */
    int regist(MmtrlxflgDto mmtrlxflgDto);

}
