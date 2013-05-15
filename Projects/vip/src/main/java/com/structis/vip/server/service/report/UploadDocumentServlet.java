package com.structis.vip.server.service.report;

import javax.servlet.http.HttpServletRequest;

import com.structis.vip.server.core.Constants;
import com.structis.vip.server.util.CatalinaPropertiesUtil;

public class UploadDocumentServlet extends UploadDocumentServiceServlet {

	@Override
	protected String getDirectoryPath(HttpServletRequest request) {
		return CatalinaPropertiesUtil.getVipDirectory(request.getSession().getServletContext().getRealPath("/")) + Constants.DOCUMENT_FILE_PATH;
	}
}
