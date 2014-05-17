package jp.co.inte.crm.web.action;

import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.web.dto.MhoDto;
import jp.co.inte.crm.web.dto.MhocgDto;
import jp.co.inte.crm.web.form.T103Form;
import jp.co.inte.crm.web.service.MhocgService;
import jp.co.inte.crm.web.service.MmtrlflgService;
import jp.co.inte.cspfw.web.annotation.AuthMode;
import jp.co.inte.cspfw.web.annotation.FwWebAuth;
import jp.co.tis.xenlon.web.annotation.TokenSkip;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

/**
 * ネタフラグ編集(N104)のアクションクラス。
 * 
 * @since 1.0
 */
public class T103Action extends CrmBaseAction {

    /** 法人担当者一覧格納用DTO */
    public List<MhocgDto> mhocgList;

    /** 法人マスタ情報格納用DTO */
    public MhoDto mhodto;

    /** アクションフォーム */
    @ActionForm
    @Resource
    protected T103Form t103Form;

    /** 法人サービス */
    @Resource
    protected MmtrlflgService mmtrlflgService;

    /** 法人担当者サービス */
    @Resource
    protected MhocgService mhocgService;

    private static final String REDIRECT_TO_T100 = "T100.html";

    private static final String REDIRECT_TO_T101 = "T101.html";

    private static final String REDIRECT_TO_T103 = "T103.html";

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
        t103Form.tnoticecontents = "1";
        t103Form.tnoticelink = "2";
        t103Form.registuser = "インテ太郎";
        t103Form.registday = "2013/12/26";
        t103Form.tnoticeid = "";

        // TODO:法人サービス呼び出し
        // Mmtrlflg mmtrlflg = mmtrlflgService.findById(flgno);
        // Beans.copy(mmtrlflg, n104Form).execute();

        // 法人担当者サービス呼び出し
        // mhocgList = mhocgService.getMhocgList(hoid);

        return REDIRECT_TO_T103;
    }

    /**
     * 紐付登録
     * 
     * @return string
     */
    @Execute(validator = false)
    public String regist() {
        // TODO 紐付登録処理
        return REDIRECT_TO_T100;
    }

    /**
     * お知らせ欄　削除
     * 
     * @return string
     */
    @Execute(validator = false)
    public String delete() {
        // TODO お知らせ欄　削除
        return REDIRECT_TO_T101;
    }

    /**
     * ボタン押下
     * 
     * @return 呼び出し元画面に遷移
     */
    @Execute(validator = false)
    public String cancel() {
        // TODO 呼び出し元画面に遷移
        return REDIRECT_TO_T101;
    }
}
