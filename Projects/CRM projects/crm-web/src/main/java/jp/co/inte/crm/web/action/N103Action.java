package jp.co.inte.crm.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.web.dto.TslmtrlDto;
import jp.co.inte.crm.web.dto.TslmtrlParamDto;
import jp.co.inte.crm.web.form.N103Form;
import jp.co.inte.crm.web.service.MmtrlflgxdeptService;
import jp.co.inte.crm.web.service.TslmtrlService;
import jp.co.inte.cspfw.web.annotation.AuthMode;
import jp.co.inte.cspfw.web.annotation.FwWebAuth;
import jp.co.tis.xenlon.web.annotation.TokenSkip;

import org.apache.commons.cli.Option;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

/**
 * 法人担当者　顧客紐付画面(N103)のアクションクラス。
 * 
 * @since 1.0
 */
public class N103Action extends CrmBaseAction {

    public List<TslmtrlDto> tslmtrlList;

    @Resource
    protected TslmtrlService tslmtrlService;

    @Resource
    protected MmtrlflgxdeptService mmtrlflgxdeptService;

    /** アクションフォーム */
    @ActionForm
    @Resource
    protected N103Form n103Form;

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

        tslmtrlList = tslmtrlService.getTslmtrlList(new TslmtrlParamDto());

        // mmtrlflgxdeptService.findAll();

        List<Option> opPlds = new ArrayList<Option>();

        Option opPld1 = new Option("1", "PLD 1");

        opPlds.add(opPld1);

        Option opPld2 = new Option("2", "PLD 2");

        opPlds.add(opPld2);

        Option opPld3 = new Option("3", "PLD 3");

        opPlds.add(opPld3);

        List<Option> opRdbs = new ArrayList<Option>();

        Option opRdb1 = new Option("1", "RDB 1");

        opRdbs.add(opRdb1);

        Option opRdb2 = new Option("2", "RDB 2");

        opRdbs.add(opRdb2);

        List<Option> opCbxs = new ArrayList<Option>();

        Option opCbx1 = new Option("1", "CBX 1");

        opCbxs.add(opRdb1);

        Option opCbx2 = new Option("2", "CBX 2");

        opCbxs.add(opRdb2);

        n103Form.opPld = opPlds;
        n103Form.opRdb = opRdbs;
        n103Form.opCbx = opCbxs;

        return "N103.html";
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
        System.out.println("test" + n103Form.selectedPld);
        System.out.println("cdname" + n103Form.cdname);
        return "N103.html";
    }
}
