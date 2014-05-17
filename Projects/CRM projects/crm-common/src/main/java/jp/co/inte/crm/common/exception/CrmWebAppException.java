package jp.co.inte.crm.common.exception;

import jp.co.inte.cspfw.exception.AbstractFwSiteAppException;
import jp.co.inte.cspfw.web.common.FwForwardable;

/**
 * 個別サイト専用のアプリ例外クラス
 * 
 * @since 1.0
 */
public class CrmWebAppException extends AbstractFwSiteAppException implements FwForwardable {

    /** シリアルバージョンID. */
    private static final long serialVersionUID = 1L;

    /** エラー画面表示後の遷移先(存在しない場合null) */
    private String forwardUrl;

    /** 遷移名(リンク・ボタン名に利用する) */
    private String forwardTitle;

    /**
     * メッセージ付きコンストラクタ.
     * 
     * @param errorCode エラーコード
     * @param msg メッセージ
     * @param params メッセージプレースホルダー置換パラメーター
     */
    public CrmWebAppException(String errorCode, String msg, String... params) {
        super(errorCode, msg, params);
    }

    /**
     * 例外付きコンストラクタ.
     * 
     * @param errorCode エラーコード
     * @param cause 例外
     */
    public CrmWebAppException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * 例外・メッセージ付きコンストラクタ.
     * 
     * @param errorCode エラーコード
     * @param cause 例外
     * @param message メッセージ
     * @param params メッセージプレースホルダー置換パラメーター
     */
    public CrmWebAppException(String errorCode, Throwable cause, String message, String... params) {
        super(errorCode, cause, message, params);
    }

    /**
     * エラー画面表示後の遷移先を取得する.
     * 
     * @return エラー画面からの遷移先
     */
    @Override
    public String getForwardUrl() {
        return forwardUrl;
    }

    /**
     * エラー画面表示後の遷移先を設定する.
     * 
     * @param url エラー画面表示後の遷移先
     */
    public void setForwardUrl(String url) {
        forwardUrl = url;
    }

    /**
     * 遷移名を設定する.
     * 
     * @param caption 遷移名
     */
    public void setForwardTitle(String caption) {
        forwardTitle = caption;
    }

    /**
     * 遷移名を取得する.
     * 
     * @return 遷移名
     */
    @Override
    public String getForwardTitle() {
        return forwardTitle;
    }
}
