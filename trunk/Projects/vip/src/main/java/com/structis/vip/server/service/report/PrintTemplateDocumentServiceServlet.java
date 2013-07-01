package com.structis.vip.server.service.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import com.structis.vip.server.core.ServerConstant;
import com.structis.vip.server.util.CatalinaPropertiesUtil;

public class PrintTemplateDocumentServiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    HttpServletRequest request;
    HttpServletResponse response;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;

        String fileName = URLDecoder.decode(request.getParameter("fileName"), "utf-8");
        if (fileName != null && !"".equals(fileName)) {
            ServletOutputStream out = null;
            FileInputStream fileInputStream = null;

            try {
                File tempPdfFile = new File(this.getFilePath(fileName));

                String fileExtension = FilenameUtils.getExtension(fileName);
                response.setContentType(this.filterContentType(fileExtension));
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                out = response.getOutputStream();

                fileInputStream = new FileInputStream(tempPdfFile);
                OutputStream responseOutputStream = response.getOutputStream();
                int bytes;
                while ((bytes = fileInputStream.read()) != -1) {
                    responseOutputStream.write(bytes);
                }
            } catch (Exception e) {
                throw new ServletException(e.getMessage());
            } finally {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    private String getFilePath(String fileName) {
        String pathContext = this.request.getSession().getServletContext().getRealPath(File.separator);
        return CatalinaPropertiesUtil.getVipDirectory(pathContext) + ServerConstant.TEMPLATE_FILE_PATH + File.separator + fileName;
    }

    private String filterContentType(String fileExtension) {
        String ret = "";

        if ("gif".equalsIgnoreCase(fileExtension)) {
            ret = "image/gif";
        } else if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension) || "jpe".equalsIgnoreCase(fileExtension)) {
            ret = "image/jpeg";
        } else if ("png".equalsIgnoreCase(fileExtension)) {
            ret = "image/x-png";
        } else if ("bmp".equalsIgnoreCase(fileExtension)) {
            ret = "image/x-ms-bmp";
        } else if ("pdf".equalsIgnoreCase(fileExtension)) {
            ret = "application/pdf";
        } else if ("doc".equalsIgnoreCase(fileExtension)) {
            ret = "application/msword";
        }

        return ret;
    }
}
