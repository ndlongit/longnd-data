package jp.co.inte.crm.web.service;

import java.util.List;

import jp.co.inte.crm.web.dto.MadrcdDto;

/**
 * <pre>
 * MadrcdDto(住所コードマスタ)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface MadrcdService {

    /**
     * 住所情報を返す
     * 
     * @param adcd 住所ＣＤ
     * @return MadrcdDto 住所マスタ情報
     */
    MadrcdDto findById(String adcd);

    /**
     * 住所一覧を返す
     * 
     * @param prefcd 都道府県CD
     * @param ctycd 市町村CD
     * @param townnm 町名
     * @return List<MadrcdDto> 住所マスタリスト
     */
    List<MadrcdDto> getAdcdList(String prefcd, String ctycd, String townnm);

}
