package jp.co.inte.crm.web.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.common.entity.Tslmtrl;
import jp.co.inte.crm.web.dto.McltDto;
import jp.co.inte.crm.web.dto.McltstshisDto;
import jp.co.inte.crm.web.dto.MuserauthDto;
import jp.co.inte.crm.web.dto.TslmtrlDto;
import jp.co.inte.crm.web.dto.TslmtrlParamDto;
import jp.co.inte.crm.web.form.N101Form;
import jp.co.inte.crm.web.service.McltService;
import jp.co.inte.crm.web.service.McltstshisService;
import jp.co.inte.crm.web.service.MuserauthService;
import jp.co.inte.crm.web.service.TslmtrlService;
import jp.co.inte.cspfw.web.annotation.AuthMode;
import jp.co.inte.cspfw.web.annotation.FwWebAuth;
import jp.co.tis.xenlon.web.annotation.TokenSkip;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

/**
 * 売上・ネタ 作成/編集画面(N101)のアクションクラス。
 * 
 * @since 1.0
 */
public class N101Action extends CrmBaseAction {

    /** アクションフォーム */
    @ActionForm
    @Resource
    protected N101Form n101Form;

    /** 権限情報サービス */
    @Resource
    protected MuserauthService mUserAuthService;

    /** 法人サービス */
    @Resource
    protected TslmtrlService tslmtrlService;

    /** 顧客詳細情報サービス */
    @Resource
    protected McltService mcltService;

    /** 顧客ステータスサービス */
    @Resource
    protected McltstshisService mcltstshisService;

    /** 権限情報格納用DTO */
    public MuserauthDto mUserAuthDto;

    /** 売上ネタParameter格納用DTO */
    TslmtrlParamDto tslmtrlParamDto;

    /** 売上ネタ格納用DTO */
    TslmtrlDto tslmtrlDto;

    /** 顧客マスタ格納用DTO */
    McltDto mcltDto;

    /** 顧客ステータス格納用DTO */
    McltstshisDto mcltstshisDto;

    /** 売上ネタ一覧格納用DTO */
    public List<TslmtrlDto> tslmtrlList;

    /** 顧客ステータス履歴区分 */
    private static final String cltStsNm1 = "新規";

    private static final String cltStsNm2 = "見込客";

    private static final String cltStsNm3 = "既存";

    private static final String cltStsNm4 = "ブランク";

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
        // TODO:Dummy ID
        String slmtrlid = "HC00000001";
        // Screen ID
        String scrId = "N101";
        // Client ID
        String cltid = "CC00000001";
        // History ID
        BigDecimal historyid = new BigDecimal(0);

        // 権限情報サービス呼び出し
        // mUserAuthDto = mUserAuthService.findById(scrId);

        // 顧客マスタサービス呼び出し
        mcltDto = mcltService.findById(cltid);

        // Set data test START
        mcltDto.cltnm = "cltnm";
        mcltDto.bonm = "bonm";
        mcltDto.cltcd = "cltcd";
        mcltDto.cltid = "cltid";
        mcltDto.midbizcd = 1111;
        mcltDto.midbiznm = "midbiznm";
        mcltDto.cltrank = 2222;
        // Set data test END

        Beans.copy(mcltDto, n101Form).execute();

        // 顧客ステータスサービス呼び出し
        mcltstshisDto = mcltstshisService.findById(cltid, historyid);
        // 1:新規,2:見込客,3:既存,4:ブランク';
        if (mcltstshisDto.cltstshiscd != null) {
            switch (mcltstshisDto.cltstshiscd) {
            case "1":
                n101Form.cltstsnm = "新規";
                break;
            case "2":
                n101Form.cltstsnm = "見込客";
                break;
            case "3":
                n101Form.cltstsnm = "既存";
                break;
            case "4":
                n101Form.cltstsnm = "ブランク";
                break;
            }
        }
        // Set data test START
        n101Form.cltstsnm = "cltstsnm";
        // Set data test END

        // 法人サービス呼び出し
        tslmtrlDto = tslmtrlService.findById(slmtrlid);
        Beans.copy(tslmtrlDto, n101Form).excludes(cltid);

        return "N101.html";
    }

    /**
     * 法人登録処理
     * 
     * @return 遷移先メソッド
     */
    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String regist() {
        // TODO 法人登録処理
        return "Y101.html";
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
        tslmtrlParamDto = new TslmtrlParamDto();
        tslmtrlDto = new TslmtrlDto();
        Tslmtrl tslmtrl = new Tslmtrl();

        tslmtrlParamDto.setSlmtrlid(n101Form.slmtrlid);
        Beans.copy(tslmtrlParamDto, tslmtrl).execute();

        // 入力チェック
        tslmtrlList = tslmtrlService.getTslmtrlList(tslmtrlParamDto);

        if (tslmtrlList.isEmpty()) {
            // show error message 0CRM0002 (日付（提案日・クロージング日）)

        } else {
            // show popup message QCRM0001(売上ネタID(slmtrlid))
            // Execute delete
            int intResult = tslmtrlService.delete(tslmtrl);
            if (intResult == 0) {
                // show msg SCRM0001
            }
        }
        return "Y101.html";
    }
}
