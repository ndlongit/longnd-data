package jp.co.inte.crm.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.web.dto.MhoDto;
import jp.co.inte.crm.web.dto.N102ResultDto;
import jp.co.inte.crm.web.form.N102Form;
import jp.co.inte.crm.web.service.McltflgService;
import jp.co.inte.crm.web.service.MhoService;
import jp.co.inte.cspfw.web.annotation.AuthMode;
import jp.co.inte.cspfw.web.annotation.FwWebAuth;
import jp.co.tis.xenlon.web.annotation.TokenSkip;

import org.apache.commons.cli.Option;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

/**
 * 法人担当者　顧客紐付画面(N102)のアクションクラス。
 * 
 * @since 1.0
 */
public class N102Action extends CrmBaseAction {

    private static final String HTML_PAGE = "N102.html";

    /** 法人担当者一覧格納用DTO */
    public List<N102ResultDto> listN102ResultDto;

    /** 法人マスタ情報格納用DTO */
    public MhoDto mhodto;

    /** アクションフォーム */
    @ActionForm
    @Resource
    protected N102Form n102Form;

    /** 法人サービス */
    @Resource
    protected MhoService mhoService;

    /** 法人担当者サービス */
    @Resource
    protected McltflgService mcltflgService;

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

        listN102ResultDto = SearchItems();

        n102Form.searchvewPld = initListOption(true);

        n102Form.s_prefnmPld = initListOption(false);

        n102Form.s_grpnm = initListOption(true);

        n102Form.s_ctrldeptnm = initListOption(true);

        n102Form.s_depnm = initListOption(true);

        n102Form.midbizPld = initListOption(true);

        n102Form.cltrankPld = initListOption(true);

        return HTML_PAGE;
    }

    /**
     * 紐付登録
     * 
     * @return 遷移先メソッド
     */
    @Execute(validator = false)
    public String regist() {
        // TODO 紐付登録処理
        return HTML_PAGE;
    }

    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String search() {
        // TODO:Dummy 法人ID
        String hoid = "HC00000001";

        // 法人サービス呼び出し
        MhoDto mhodto = mhoService.findById(hoid);
        Beans.copy(mhodto, n102Form).execute();

        n102Form.crtpgid = "N0000000002";

        // 法人担当者サービス呼び出し
        // mhocgList = mhocgService.getMhocgList(hoid);

        return HTML_PAGE;
    }

    // Make search items
    private List<N102ResultDto> SearchItems()
    {
        List<N102ResultDto> listN102ResultItems = new ArrayList<N102ResultDto>();

        N102ResultDto n102ResultDto1 = new N102ResultDto();

        n102ResultDto1.mcltflgno = "1";
        n102ResultDto1.selradio = false;
        n102ResultDto1.cltcd = "1";
        n102ResultDto1.cltnm = "1";
        n102ResultDto1.cltstsnm = "1";
        n102ResultDto1.claimstsnm = "1";
        n102ResultDto1.prefnm = "1";
        n102ResultDto1.adr01 = "1";
        n102ResultDto1.cltcgnm = "1";
        n102ResultDto1.telnum = "1";
        n102ResultDto1.bonm = "1";
        n102ResultDto1.cltrank = "1";
        n102ResultDto1.apolloid = "1";
        n102ResultDto1.ctynm = "1";
        n102ResultDto1.adr02 = "1";
        n102ResultDto1.empnm = "1";
        n102ResultDto1.grpnm = "1";

        listN102ResultItems.add(n102ResultDto1);

        N102ResultDto n102ResultDto2 = new N102ResultDto();

        n102ResultDto2.mcltflgno = "2";
        n102ResultDto2.selradio = true;
        n102ResultDto2.cltcd = "2";
        n102ResultDto2.cltnm = "2";
        n102ResultDto2.cltstsnm = "2";
        n102ResultDto2.claimstsnm = "2";
        n102ResultDto2.prefnm = "2";
        n102ResultDto2.adr01 = "2";
        n102ResultDto2.cltcgnm = "2";
        n102ResultDto2.telnum = "2";
        n102ResultDto2.bonm = "2";
        n102ResultDto2.cltrank = "2";
        n102ResultDto2.apolloid = "2";
        n102ResultDto2.ctynm = "2";
        n102ResultDto2.adr02 = "2";
        n102ResultDto2.empnm = "2";
        n102ResultDto2.grpnm = "2";

        listN102ResultItems.add(n102ResultDto2);

        return listN102ResultItems;
    }

    private List<Option> initListOption(boolean isAddRequire)
    {
        List<Option> opPlds = new ArrayList<Option>();

        if (isAddRequire)
        {
            Option opPld = new Option("0", "-- 選択してください --");
            opPlds.add(opPld);
        }

        Option opPld1 = new Option("1", "PLD 1");

        opPlds.add(opPld1);

        Option opPld2 = new Option("2", "PLD 2");

        opPlds.add(opPld2);

        Option opPld3 = new Option("3", "PLD 3");

        opPlds.add(opPld3);

        return opPlds;
    }
}
