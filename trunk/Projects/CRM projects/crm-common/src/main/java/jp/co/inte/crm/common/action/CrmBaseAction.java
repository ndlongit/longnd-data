package jp.co.inte.crm.common.action;

import java.lang.reflect.Method;

import jp.co.inte.cspfw.web.action.AbstractFwAction;

/**
 * 
 * CRM-System BaseAction Class.
 * 
 */
public class CrmBaseAction extends AbstractFwAction {

    @Override
    public String beforeExecute(Method method, Object form) throws Exception {

        logger.debugLog(
                "AbstractAction beforeExecute",
                "X-Forwarded-Proto=[" + request.getHeader("X-Forwarded-Proto") + "]");

        return super.beforeExecute(method, form);
    }

    @Override
    public String index() {
        return null;
    }

}
