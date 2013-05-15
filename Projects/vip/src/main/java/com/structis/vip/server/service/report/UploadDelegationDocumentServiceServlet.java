package com.structis.vip.server.service.report;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.structis.vip.server.core.Constants;
import com.structis.vip.server.util.CatalinaPropertiesUtil;

public class UploadDelegationDocumentServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHhhss");

	private static final Logger LOGGER = Logger.getLogger(UploadDelegationDocumentServiceServlet.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(request);
				String fileName = "";
				for (FileItem item : items) {
					// process only file upload - discard other form item types
					if (item.isFormField()) {
						continue;
					}

					fileName = item.getName();
					LOGGER.info("UPLOAD OTHER FILE PATH: [" + fileName + "]");
					// get only the file name not whole path
					if ((fileName != null) && (!"".equals(fileName.trim())) && (item.getSize() != 0)) {
						fileName = FilenameUtils.getName(fileName);
						LOGGER.info("UPLOAD OTHER FILE NAME: [" + fileName + "]");
					} else {
						LOGGER.info("EMPTY FILE PATH");
						continue;
					}
					
					LOGGER.info("RETURN OTHER FILE NAME: [" + item.getFieldName() + "]");
					
					File uploadedFile = new File(getDirectoryPath(request), item.getFieldName());
					if (uploadedFile.createNewFile()) {
						LOGGER.info("FILE CREATED");
						item.write(uploadedFile);
						response.setStatus(HttpServletResponse.SC_CREATED);
						response.getWriter().print(item.getFieldName());
						response.flushBuffer();
					} else {
						throw new IOException("The file already exists in repository.");
					}
				}
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"An error occurred while creating the file : " + e.getMessage());
			}
		} else {
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
					"Request contents type is not supported by the servlet.");
		}
	}

	private String getDirectoryPath(HttpServletRequest request) {
		return CatalinaPropertiesUtil.getVipDirectory(request.getSession().getServletContext().getRealPath("/")) + Constants.DELEGATION_DOCUMENT_FILE_PATH;
	}
}
