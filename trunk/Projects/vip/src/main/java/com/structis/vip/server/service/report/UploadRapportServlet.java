package com.structis.vip.server.service.report;

import javax.servlet.http.HttpServletRequest;

import com.structis.vip.server.core.Constants;
import com.structis.vip.server.util.CatalinaPropertiesUtil;

public class UploadRapportServlet extends UploadDocumentServiceServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected String getDirectoryPath(HttpServletRequest request) {
        return CatalinaPropertiesUtil.getVipDirectory(request.getSession().getServletContext().getRealPath("/")) + Constants.RAPPORT_FILE_PATH;
    }
}
