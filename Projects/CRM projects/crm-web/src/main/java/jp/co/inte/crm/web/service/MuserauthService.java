package jp.co.inte.crm.web.service;

import jp.co.inte.crm.web.dto.MuserauthDto;

/**
 * <pre>
 * muserauth(ユーザー権限)
 * </pre>
 * 
 * @author
 * @version 1.0
 * @since 1.0
 */
public interface MuserauthService {

    /**
     * 権限情報
     * ログインユーザーの権限(表示画面、閲覧権限)情報を返す
     * 
     * @param screenid 画面ID
     * @return Muserauth ユーザー権限
     */
    MuserauthDto findById(String screenid);

}
