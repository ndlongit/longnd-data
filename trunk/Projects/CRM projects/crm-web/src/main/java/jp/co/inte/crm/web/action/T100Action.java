package jp.co.inte.crm.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.web.dto.McalendarDto;
import jp.co.inte.crm.web.dto.TrltqttlDto;
import jp.co.inte.crm.web.dto.TrltqttlParamDto;
import jp.co.inte.crm.web.form.T100Form;
import jp.co.inte.crm.web.service.McalendarService;
import jp.co.inte.crm.web.service.TapproachplanhisService;
import jp.co.inte.crm.web.service.TrltmttlService;
import jp.co.inte.crm.web.service.TrltqttlService;
import jp.co.inte.crm.web.service.TslmtrlService;
import jp.co.inte.crm.web.service.TtargetService;
import jp.co.inte.cspfw.web.annotation.AuthMode;
import jp.co.inte.cspfw.web.annotation.FwWebAuth;
import jp.co.tis.xenlon.web.annotation.TokenSkip;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class T100Action extends CrmBaseAction {

    @Resource
    protected McalendarService caldarService;

    @Resource
    protected TrltqttlService trltqtService;

    @Resource
    protected TrltmttlService trltmtService;

    @Resource
    protected TtargetService targetService;

    @Resource
    protected TapproachplanhisService apprService;

    @Resource
    protected TslmtrlService tslmService;

    /** アクションフォーム */
    @ActionForm
    @Resource
    protected T100Form t100Form;

    private final String divcaldate_month = "1月 / 2週";

    private final String divcaldate_week = "week";

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
        TrltqttlParamDto trltqtt = new TrltqttlParamDto();

        McalendarDto calBuzMonthDto = caldarService.calcBuzDaysOfMonth(new Date(), divcaldate_month);
        List<TrltqttlDto> ls_trltqtldto = trltqtService.getResultTotalList(trltqtt);

        /*
         * McalendarDto calInfoDto = caldarService.getCalDate(new Date());
         * 
         * 
         * 
         * 
         * TrltmttlParamDto trltqttparamDto = new TrltmttlParamDto();
         * List<TrltmttlDto> ls_trltmttlDtos = trltmtService.getResultTotalList(trltqttparamDto);
         * 
         * McalendarDto calBuzWeekDto = caldarService.calcBizDaysOfWeek(new Date(), 1);
         * 
         * List<TapproachplanhisDto> ls_tapproachplanhisDto = apprService.getTapproachplanhisList("", new Date());
         * 
         * List<TslmtrlDto> ls_tslmtrlDto = tslmService.getTodayTslmtrlList("", new Date());
         */
        return "T100.html";
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

    /**
     * redirect to K100 screen
     * 
     * @return K100 string
     */
    @Execute(validator = false)
    public String transferBtn() {
        return "K100.html";
    }
}
