package jp.co.inte.crm.web.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.web.dto.MmtrlflgDto;
import jp.co.inte.crm.web.dto.TslmtrlDto;
import jp.co.inte.crm.web.dto.TslmtrlParamDto;
import jp.co.inte.crm.web.form.N104Form;
import jp.co.inte.crm.web.service.MmtrlflgService;
import jp.co.inte.crm.web.service.MuserauthService;
import jp.co.inte.crm.web.service.TslmtrlService;
import jp.co.inte.cspfw.web.annotation.AuthMode;
import jp.co.inte.cspfw.web.annotation.FwWebAuth;
import jp.co.tis.xenlon.web.annotation.TokenSkip;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

/**
 * ネタフラグ編集(N104)のアクションクラス。
 * 
 * @since 1.0
 */
public class N104Action extends CrmBaseAction {

    /** アクションフォーム */
    @ActionForm
    @Resource
    protected N104Form n104Form;

    /** ネタフラグマスタ */
    @Resource
    protected MmtrlflgService mmtrlflgService;

    /** 売上ネタ */
    @Resource
    protected TslmtrlService tslmtrlService;

    /** 売上ネタ */
    @Resource
    protected MuserauthService muserauthService;

    /** ネタフラグ設定 */
    private static final String REDIRECT_TO_N103 = "../N103/N103.html";

    /** ネタフラグ編集 */
    private static final String REDIRECT_TO_N104 = "N104.html";

    /** ネタフラグ編集 */
    private static final String SCREEN_N104 = "N104";

    /** 売上ネタ一覧 */
    public List<TslmtrlDto> tslmtrlList;

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

        // 共通サービス
        initLoadData();

        // 権限チェック
        initCheckAuthority();

        return REDIRECT_TO_N104;
    }

    /***
     * 共通サービス
     */
    private void initLoadData() {
        // TODO:Dummy フラグNo
        n104Form.flgNo = new BigDecimal(1);
        TslmtrlParamDto tslmtrlParamDto = new TslmtrlParamDto();
        tslmtrlParamDto.setSlmtrlid("");

        // 法人サービス呼び出し
        MmtrlflgDto mmtrlflgDto = mmtrlflgService.findById(n104Form.flgNo);
        Beans.copy(mmtrlflgDto, n104Form).execute();

        // 売上ネタ一覧を返却する
        List<TslmtrlDto> tslmtrlList = tslmtrlService.getTslmtrlList(tslmtrlParamDto);
    }

    /**
     * 権限チェック
     */
    private void initCheckAuthority() {
        // TODO:Dummy フラグNo
        n104Form.ctrlAssignFrameDisabled = false;

        // MuserauthDto muserauthDto = muserauthService.findById(SCREEN_N104);

    }

    /**
     * 紐付登録
     * 
     * @return 遷移先メソッド
     */
    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String regist() {
        // TODO 紐付登録処理
        return REDIRECT_TO_N103;
    }

    /**
     * ボタン押下
     * 
     * @return 呼び出し元画面に遷移
     */
    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String cancel() {
        return REDIRECT_TO_N103;
    }

    /**
     * ボタン押下
     * 
     * @return ネタフラグ　クリアして登録
     */
    @Execute(validator = false)
    public String close() {
        // TODO 共通サービス, 画面遷移
        return REDIRECT_TO_N103;
    }
}
