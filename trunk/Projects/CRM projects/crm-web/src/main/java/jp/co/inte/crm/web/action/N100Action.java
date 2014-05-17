package jp.co.inte.crm.web.action;

import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.common.entity.Tslmtrl;
import jp.co.inte.crm.web.dto.TslmtrlDto;
import jp.co.inte.crm.web.dto.TslmtrlParamDto;
import jp.co.inte.crm.web.form.N100Form;
import jp.co.inte.crm.web.service.TslmtrlService;
import jp.co.inte.cspfw.web.annotation.AuthMode;
import jp.co.inte.cspfw.web.annotation.FwWebAuth;
import jp.co.tis.xenlon.web.annotation.TokenSkip;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

/**
 * 法人担当者　顧客紐付画面(C213)のアクションクラス。
 * 
 * @since 1.0
 */
public class N100Action extends CrmBaseAction {

    /** 法人担当者一覧格納用DTO */
    public List<TslmtrlDto> tslmtrlList;

    /** アクションフォーム */
    @ActionForm
    @Resource
    protected N100Form N100Form;

    /** 法人サービス */
    @Resource
    protected TslmtrlService tslmtrlService;

    /**
     * 初期表示
     * 
     * @return 遷移先メソッド
     */
    @Override
    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String index() {
        /** 法人マスタ情報格納用DTO */
        TslmtrlParamDto tslmtrlParamDto = new TslmtrlParamDto();
        tslmtrlList = tslmtrlService.getTslmtrlList(tslmtrlParamDto);

        /*
         * ■init
         * ・ コード値一覧取得(グループ)
         * ・ コード値一覧取得(統括部)
         * ・ コード値一覧取得(部)
         * -- Waiting update the screen design document
         */

        /*
         * ■cgempselectBtn: click
         * ・ C207
         * -- Action like HTML now
         */

        /*
         * ■mcltselectBtn: click
         * ・ N102
         * -- Action like HTML now
         */

        /*
         * ■init
         * ・Default : 「受注」「提案」
         * -- Action like HTML now
         */

        /*
         * ■init
         * ・Default : All Check
         * -- Action like HTML now
         */

        /*
         * ■ init
         * ・Default : "+"
         * -- Action like HTML now
         */

        /*
         * ■ init
         * ・Month selection
         * Automatic 「1W」
         * ・「 FROM」 Only Search OK
         * -- Use Javascript to implement this funtion
         */

        return "N100.html";
    }

    /**
     * 紐付登録
     * 
     * @return 遷移先メソッド
     */
    @Execute(validator = false)
    public String regist() {
        // TODO 法人登録処理
        String strSaleID = N100Form.slmtrlid;
        // N101Action.index(strSaleID);

        return "N100.html";
    }

    /**
     * 法人削除処理
     * 
     * @return 遷移先メソッド
     */
    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String delete() {
        // Initial
        TslmtrlParamDto tslmtrlParamDto = new TslmtrlParamDto();
        TslmtrlDto tslmtrlDto = new TslmtrlDto();
        Tslmtrl tslmtrl = new Tslmtrl();

        tslmtrlParamDto.setSlmtrlid(N100Form.slmtrlid);
        Beans.copy(tslmtrlParamDto, tslmtrl).execute();

        // 入力チェック
        tslmtrlList = tslmtrlService.getTslmtrlList(tslmtrlParamDto);

        if (tslmtrlList.isEmpty()) {
            // nothing to do
        } else {
            // Execute delete
            int intResult = tslmtrlService.delete(tslmtrl);
        }
        return "N100.html";
    }

    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String search() {
        /* Check input condition by Javascript */
        /** 法人マスタ情報格納用DTO */

        TslmtrlParamDto tslmtrlParamDto = new TslmtrlParamDto();
        Beans.copy(tslmtrlParamDto, N100Form).execute();
        // 法人担当者サービス呼び出し
        tslmtrlList = tslmtrlService.getTslmtrlList(tslmtrlParamDto);

        return "N100.html";
    }

    @Execute(validator = false)
    public String C207Create() {
        return "C207.html";
    }

    @Execute(validator = false)
    public String N102Create() {
        return "N102.html";
    }

}
