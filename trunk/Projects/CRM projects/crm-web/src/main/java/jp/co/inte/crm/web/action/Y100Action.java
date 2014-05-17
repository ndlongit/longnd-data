package jp.co.inte.crm.web.action;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jp.co.inte.crm.common.action.CrmBaseAction;
import jp.co.inte.crm.web.dto.MhoDto;
import jp.co.inte.crm.web.dto.TapproachdtlDto;
import jp.co.inte.crm.web.dto.TapproachdtlParamDto;
import jp.co.inte.crm.web.form.Y100Form;
import jp.co.inte.crm.web.service.TapproachdtlService;
import jp.co.inte.cspfw.web.annotation.AuthMode;
import jp.co.inte.cspfw.web.annotation.FwWebAuth;
import jp.co.tis.xenlon.web.annotation.TokenSkip;

import org.apache.log4j.Logger;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class Y100Action extends CrmBaseAction {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private static final String MAIN_PAGE = "Y100.html";

    private static final String RESULT_TABLE = "Y100_tapproachplanhis.html";

    public MhoDto mhodto;

    @ActionForm
    @Resource
    protected Y100Form y100Form;

    public int itemsPerPage = 20;

    public int numOfPages = 1;

    public int currentPage = 1;

    @Resource
    protected TapproachdtlService tapproachdtlService;

    public String showcnt = "0-0";

    public int searchrescnt;

    public List<TapproachdtlDto> results;

    @Override
    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String index() {
        initData();
        return MAIN_PAGE;
    }

    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String search() {
        return RESULT_TABLE;
    }

    @Execute(validator = false)
    @FwWebAuth(mode = AuthMode.ANONYMOUS)
    @TokenSkip
    public String deleteMultiple() {
        if (!isNullOrEmpty(y100Form.ids)) {
            // tapproachdtlService.deledeteByIDs(y100Form.ids);
        }
        return RESULT_TABLE;
    }

    private void initData() {
        try {
            TapproachdtlParamDto tapproachdtlParamDto = new TapproachdtlParamDto();
            results = tapproachdtlService.getApproachplanhisList(tapproachdtlParamDto);
            if (results != null) {
                searchrescnt = results.size();
                numOfPages = (int) Math.ceil(searchrescnt * 1.0 / itemsPerPage);
                int start = (currentPage - 1) * itemsPerPage + 1;
                int end = start + itemsPerPage - 1;
                end = Math.min(searchrescnt, end);
                showcnt = start + "-" + end;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Check if an object (String, Collection, Map, Object...) is null or empty
     * 
     * @param value
     *            : The value to check
     * @return: true if the checked value is null or empty, false if otherwise
     */
    @SuppressWarnings("rawtypes")
    private static boolean isNullOrEmpty(Object value) {
        if (value == null) {
            return true;
        }

        if (value instanceof String) {
            return ("".equals(value.toString().trim()));
        } else if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        } else if (value instanceof Map) {
            return ((Map) value).isEmpty();
        } else {
            return false;
        }
    }
}
