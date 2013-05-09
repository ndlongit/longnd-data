package com.structis.fichesst.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FileDownloadServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String localFileName = request.getParameter("fileName");
		String downloadFileName = request.getParameter("downloadFileName");
		if( downloadFileName == null || downloadFileName == "" ) {
			downloadFileName = "Export.pdf";
		}

		File file = findTempFileByName(localFileName);
		if(file == null) {
			return;
		}

		FileInputStream fileToDownload = new FileInputStream(file);
		ServletOutputStream output = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=" + downloadFileName);
		response.setContentLength(fileToDownload.available());
		response.setCharacterEncoding("utf-8");

		int c;
		while( (c = fileToDownload.read()) != -1 ) {
			output.write(c);
		}

		output.flush();
		output.close();
		fileToDownload.close();
	}

	protected static File findTempFileByName(String fileName) {
		String tempDir = System.getProperty("java.io.tmpdir");
		String fileStr = tempDir + File.separator + fileName;

		File f = new File(fileStr);
		if( f.exists() ) {
			return f;
		}
		else {
			return null;
		}
	}
}
