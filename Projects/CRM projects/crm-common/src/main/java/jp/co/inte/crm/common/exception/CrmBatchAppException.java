package jp.co.inte.crm.common.exception;

import jp.co.inte.cspfw.exception.AbstractFwSiteAppException;

/**
 * 個別サイトバッチ用　アプリ例外の親クラス.
 * 
 */
public class CrmBatchAppException extends AbstractFwSiteAppException {

    /** シリアルバージョンID. */
    private static final long serialVersionUID = 1L;

    /**
     * メッセージ付きコンストラクタ.
     * 
     * @param errorCode エラーコード
     * @param msg メッセージ
     * @param params メッセージプレースホルダー置換パラメーター
     */
    public CrmBatchAppException(String errorCode, String msg, String... params) {
        super(errorCode, msg, params);
    }

    /**
     * 例外付きコンストラクタ.
     * 
     * @param errorCode エラーコード
     * @param cause 例外
     */
    public CrmBatchAppException(String errorCode, Throwable cause) {
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
    public CrmBatchAppException(String errorCode, Throwable cause, String message,
            String... params) {
        super(errorCode, cause, message, params);
    }
}
