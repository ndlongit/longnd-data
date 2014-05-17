package jp.co.inte.crm.common.exception;

import jp.co.inte.cspfw.exception.AbstractFwSiteSystemException;

/**
 * 個別サイトバッチ用　システム例外の親クラス.
 * 
 * @since 1.0
 */
public class CrmBatchSystemException extends AbstractFwSiteSystemException {

    /** シリアルバージョンID. */
    private static final long serialVersionUID = 1L;

    /**
     * メッセージ付きコンストラクタ.
     * 
     * @param errorCode エラーコード
     * @param msg メッセージ
     * @param params メッセージプレースホルダー置換パラメーター
     */
    public CrmBatchSystemException(String errorCode, String msg, String... params) {
        super(errorCode, msg, params);
    }

    /**
     * 例外付きコンストラクタ.
     * 
     * @param errorCode エラーコード
     * @param cause 例外
     */
    public CrmBatchSystemException(String errorCode, Throwable cause) {
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
    public CrmBatchSystemException(String errorCode, Throwable cause, String message,
            String... params) {
        super(errorCode, cause, message, params);
    }

}
