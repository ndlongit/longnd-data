package jp.co.inte.crm.common.dto;

import jp.co.inte.cspfw.dto.AbstractUserLoginDto;

/**
 * 
 * デモサイト用ユーザログイン情報
 * 
 * 
 * 
 * @since 1.0
 */

public class UserLoginDto extends AbstractUserLoginDto {

    /**
     * 
     * シリアルバージョンID。
     */

    private static final long serialVersionUID = 1L;

    /**
     * 
     * ユーザ名。
     */

    private String username;

    /**
     * 
     * ユーザ名を取得する。
     * 
     * 
     * 
     * @return ユーザ名
     */

    public String getUsername() {

        return this.username;

    }

    /**
     * 
     * ユーザ名をセットする。
     * 
     * 
     * 
     * @param username ユーザ名
     */

    public void setUsername(String username) {

        this.username = username;

    }

}
