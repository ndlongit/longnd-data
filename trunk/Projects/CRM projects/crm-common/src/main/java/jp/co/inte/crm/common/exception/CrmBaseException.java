package jp.co.inte.crm.common.exception;

import jp.co.inte.cspfw.exception.AbstractFwException;

/**
 * 
 * CRM-System BaseInterceptor Class.
 * 
 */
public class CrmBaseException extends AbstractFwException {

    /**
     * シリアルバージョンID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ.
     * 
     * @param code エラーコード
     * @param message メッセージ
     * @param params メッセージパラメータ
     */
    public CrmBaseException(String code, String message, String[] params) {

        super(code, message, params);

    }

    /**
     * コンストラクタ.
     * 
     * @param code エラーコード
     * @param cause 原因
     */
    public CrmBaseException(String code, Throwable cause) {

        super(code, cause);

    }

    /**
     * コンストラクタ.
     * 
     * @param code エラーコード
     * @param cause 原因
     * @param message メッセージ
     * @param params メッセージパラメータ
     */
    public CrmBaseException(String code, Throwable cause, String message, String[] params) {

        super(code, cause, message, params);

    }

}
