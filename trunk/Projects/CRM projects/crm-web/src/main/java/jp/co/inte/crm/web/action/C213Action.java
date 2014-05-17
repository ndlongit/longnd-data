package jp.co.inte.crm.web.action;

import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.web.dto.MhoDto;
import jp.co.inte.crm.web.dto.MhocgDto;
import jp.co.inte.crm.web.form.C213Form;
import jp.co.inte.crm.web.service.MhoService;
import jp.co.inte.crm.web.service.MhocgService;
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
public class C213Action extends CrmBaseAction {

    /** 法人担当者一覧格納用DTO */
    public List<MhocgDto> mhocgList;

    /** 法人マスタ情報格納用DTO */
    public MhoDto mhodto;

    /** アクションフォーム */
    @ActionForm
    @Resource
    protected C213Form c213Form;

    /** 法人サービス */
    @Resource
    protected MhoService mhoService;

    /** 法人担当者サービス */
    @Resource
    protected MhocgService mhocgService;

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
        // TODO:Dummy 法人ID
        String hoid = "HC00000001";

        // 法人サービス呼び出し
        MhoDto mhodto = mhoService.findById(hoid);
        Beans.copy(mhodto, c213Form).execute();

        // 法人担当者サービス呼び出し
        mhocgList = mhocgService.getMhocgList(hoid);

        return "C213.html";
    }

    /**
     * 紐付登録
     * 
     * @return 遷移先メソッド
     */
    @Execute(validator = false)
    public String regist() {
        // TODO 紐付登録処理
        return "C213.html";
    }
}
