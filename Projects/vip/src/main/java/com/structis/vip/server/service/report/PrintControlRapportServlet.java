package com.structis.vip.server.service.report;

import java.io.File;

import com.structis.vip.server.core.ServerConstant;
import com.structis.vip.server.util.CatalinaPropertiesUtil;

public class PrintControlRapportServlet extends PrintDelegationDocumentServiceServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected String getFilePath(String fileName) {
        return CatalinaPropertiesUtil.getVipDirectory(this.request.getSession().getServletContext().getRealPath("/"))
                + ServerConstant.RAPPORT_FILE_PATH + File.separator + fileName;
    }
}
