package jp.co.inte.crm.web.service;

import java.math.BigDecimal;

import jp.co.inte.crm.web.dto.MmtrlflgDto;

/**
 * <pre>
 * mmtrlflg(ネタフラグマスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface MmtrlflgService {

    /**
     * ネタフラグマスタ情報
     * ネタフラグのマスタを返す
     * 
     * @param flgno フラグNo
     * @return Mmtrlflg ネタフラグマスタ
     */
    MmtrlflgDto findById(BigDecimal flgno);

    /**
     * ネタフラグマスタ登録
     * 
     * @param mmtrlflgDto ネタフラグマスタ
     * @return 更新した行数
     */
    int regist(MmtrlflgDto mmtrlflgDto);

}
