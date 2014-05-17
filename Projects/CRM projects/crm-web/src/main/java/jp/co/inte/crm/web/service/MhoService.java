package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.common.entity.Mho;
import jp.co.inte.crm.web.dto.MhoDto;
import jp.co.inte.crm.web.dto.MhoParamDto;

/**
 * <pre>
 * mho(法人マスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface MhoService {

    /**
     * 法人の基本情報一覧を返す
     * 
     * @param mhodtoParam 検索条件
     * @return List<MhoDto> 法人基本情報一覧
     */
    List<MhoDto> getCorpList(MhoParamDto mhodtoParam);

    /**
     * 法人詳細情報を返す
     * 
     * @param hoid CRM用法人ID
     * @return MhoDto 法人基本情報
     */
    MhoDto findById(String hoid);

    /**
     * 法人の基本情報を登録する。
     * 
     * @param mho 法人マスタ
     * @return int
     */
    int regist(Mho mho);
}
