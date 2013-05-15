package com.structis.vip.server.service.report;

import java.io.File;

import com.structis.vip.server.core.Constants;
import com.structis.vip.server.util.CatalinaPropertiesUtil;

public class PrintControlRapportServlet extends PrintDelegationDocumentServiceServlet{
	@Override
	protected String getFilePath(String fileName) {		
		return CatalinaPropertiesUtil.getVipDirectory(request.getSession().getServletContext().getRealPath("/"))  + Constants.RAPPORT_FILE_PATH + File.separator + fileName;
	}
}
