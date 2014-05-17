package jp.co.inte.crm.web.action;

import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.web.dto.MhoDto;
import jp.co.inte.crm.web.dto.MhocgDto;
import jp.co.inte.crm.web.form.L100Form;
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
public class L100Action extends CrmBaseAction {

    /** 法人担当者一覧格納用DTO */
    public List<MhocgDto> mhocgList;

    /** 法人マスタ情報格納用DTO */
    public MhoDto mhodto;

    /** アクションフォーム */
    @ActionForm
    @Resource
    protected L100Form l100Form;

    /** 法人サービス */
    @Resource
    protected MmtrlflgService mmtrlflgService;

    /** 法人担当者サービス */
    @Resource
    protected MhocgService mhocgService;

    private static final String REDIRECT_TO_L100 = "L100.html";

    private static final String REDIRECT_TO_T100 = "T100.html";

    private static final String REDIRECT_TO_T101 = "T101.html";

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
        l100Form.loginid = "111133333";
        l100Form.loginpw = "2222";

        l100Form.introduction = "初めに";
        l100Form.question = "よくある質問";
        l100Form.release = "リリース予定";
        l100Form.obstacle = "障害情報";
        l100Form.use = "使い方(動画①、動画②)";
        l100Form.inquiry = "お問い合わせはこちらに（メール）";
        l100Form.notice = "2014/01/18 システムメンテナンス終了のお知らせ";
        l100Form.errmsg = "システムメンテナンスは完了しました。ご協力ありがとうございました。";

        // TODO:法人サービス呼び出し
        // Mmtrlflg mmtrlflg = mmtrlflgService.findById(flgno);
        // Beans.copy(mmtrlflg, n104Form).execute();

        // 法人担当者サービス呼び出し
        // mhocgList = mhocgService.getMhocgList(hoid);

        return REDIRECT_TO_L100;
    }

    /**
     * 紐付登録
     * 
     * @return 遷移先メソッド
     */
    @Execute(validator = false)
    public String login() {
        // TODO 紐付登録処理
        return REDIRECT_TO_T100;
    }
}
