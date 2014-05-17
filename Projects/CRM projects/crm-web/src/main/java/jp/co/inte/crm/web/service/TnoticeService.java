package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.TnoticeDto;

/**
 * <pre>
 * notice(お知らせ欄)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface TnoticeService {

    /**
     * お知らせの一覧を返す
     * 
     * @return List<TnoticeDto> お知らせ一覧リスト
     */
    List<TnoticeDto> getNoticeList();

    /**
     * お知らせ情報を返す
     * 
     * @param tnoticeid お知らせIDs
     * @return TnoticeDto お知らせ情報
     */
    TnoticeDto findById(int tnoticeid);

    /**
     * お知らせ登録
     * 
     * @param tnotice お知らせ
     * @return 更新した行数
     */
    int regist(TnoticeDto tnotice);

    /**
     * お知らせ削除
     * 
     * @param tnotice お知らせ
     * @return 更新した行数
     */
    int delete(TnoticeDto tnotice);

}
