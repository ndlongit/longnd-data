package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.McltflgDto;

/**
 * <pre>
 * mcltflg(顧客フラグマスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface McltflgService {

    /**
     * 顧客フラグ詳細
     * 顧客フラグマスタ情報を返却する。
     * 
     * @param flgno フラグNo
     * @return McltflgDto 顧客フラグ詳細
     */
    McltflgDto findById(int flgno);

    /**
     * 顧客フラグ詳細
     * 顧客フラグマスタ情報を返却する。
     * 
     * @return List<McltflgDto> 顧客フラグ詳細
     */
    List<McltflgDto> getCustFlgList();

    /**
     * 顧客フラグ登録
     * 顧客フラグマスタを登録する。
     * 
     * @param mcltflgDto 顧客フラグマスタ
     * @return 更新した行数
     */
    int regist(McltflgDto mcltflgDto);

}
