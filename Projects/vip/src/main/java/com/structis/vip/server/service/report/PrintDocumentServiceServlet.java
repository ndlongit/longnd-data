package com.structis.vip.server.service.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.structis.vip.server.bean.domain.Collaborateur;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.bean.domain.DocumentMdl;
import com.structis.vip.server.bean.domain.FieField;
import com.structis.vip.server.core.DelegationConstants;
import com.structis.vip.server.core.ServerConstant;
import com.structis.vip.server.core.SpringGetter;
import com.structis.vip.server.service.domain.DomDelegationService;
import com.structis.vip.server.service.domain.DomDocumentModelService;
import com.structis.vip.server.service.domain.DomFieldService;
import com.structis.vip.server.util.CatalinaPropertiesUtil;
import com.structis.vip.server.util.CommonUtils;
import com.structis.vip.server.util.PDFUtil;

public class PrintDocumentServiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static final String GET_METHOD_PREFIX = "get";
    HttpServletRequest request;
    HttpServletResponse response;

    private DomDocumentModelService domDocumentModelService;

    private DomDelegationService domDelegationService;

    private DomFieldService fieldService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.domDocumentModelService = (DomDocumentModelService) SpringGetter.getBean(config.getServletContext(), "domDocumentModelService");
        this.domDelegationService = (DomDelegationService) SpringGetter.getBean(config.getServletContext(), "domDelegationService");
        this.fieldService = (DomFieldService) SpringGetter.getBean(config.getServletContext(), "domFieldService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;

        String domId = request.getParameter("domId");
        String delId = request.getParameter("delId");
        DocumentMdl documentMdl = null;
        Delegation delegation = null;
        List<Collaborateur> delegataires = null;

        if (domId != null && !"".equals(domId)) {
            documentMdl = this.domDocumentModelService.findById(Integer.parseInt(domId));

            if (delId != null && !"".equals(delId)) {
                delegation = this.domDelegationService.findById(Integer.parseInt(delId));
            }
            if (delegation.getEntite() != null) {
                if (!delegation.getEntite().getEntId().equals(com.structis.vip.shared.SharedConstant.ENTITE_ID_ETDE)) {
                    delegataires = this.domDelegationService.findDelegatairesByDelegation(Integer.parseInt(delId));
                }
            }
            if (documentMdl != null) {
                this.export(delegation, documentMdl, delegataires);
            }
        }
    }

    private void export(Delegation delegation, DocumentMdl documentMdl, List<Collaborateur> delegataires) throws ServletException, IOException {
        String randomFile = CommonUtils.randomString(12);
        String outDocTempFile = randomFile + ServerConstant.DOC_EXTENSION_FILE;
        String outPdfTempFile = randomFile + ServerConstant.PDF_EXTENSION_FILE;

        String isPrint = this.request.getParameter("isPrint");

        String fileName = documentMdl.getFilename();

        int delegationType = delegation.getDelegationType().getId();
        if (delegationType == DelegationConstants.DEL_TYPE_TEMPO) {
            if ((documentMdl.getTempFilename() != null) && (!"".equals(documentMdl.getTempFilename().trim()))) {
                fileName = documentMdl.getTempFilename();
            }
        }

        String filePath = this.getRealFilePath(fileName);

        String pdfFile = FilenameUtils.removeExtension(fileName) + ServerConstant.PDF_EXTENSION_FILE;

        ServletOutputStream out = null;
        FileOutputStream outTemp = null;
        FileInputStream fileInputStream = null;
        FileInputStream inTemp = null;

        POIFSFileSystem fs;
        try {
            inTemp = new FileInputStream(filePath);
            fs = new POIFSFileSystem(inTemp);

            HWPFDocument doc = new HWPFDocument(fs);
            Range range = doc.getRange();

            if (delegation != null) {
                String entId = delegation.getEntite().getEntId();
                List<FieField> fieFields = this.fieldService.findByEntite(entId);
                if (fieFields != null) {
                    for (FieField fieField : fieFields) {
                        String variable = fieField.getDbField();
                        String matching = "<" + variable + ">";
                        String replacement = this.invokeValue(delegation, variable);
                        range.replaceText(matching, replacement);

                        if (delegation.getEntite() == null) {
                            continue;
                        }

                        if (!com.structis.vip.shared.SharedConstant.ENTITE_ID_ETDE.equals(delegation.getEntite().getEntId())) {
                            if (this.belongsToExceptionFields(variable)) {
                                for (int idx = 0; idx < delegataires.size(); idx++) {
                                    String nestedMatching = "<" + variable + "[" + idx + "]" + ">";
                                    String nestedReplacement = this.invokeValueDelegataire(delegation, variable, idx, delegataires);
                                    range.replaceText(nestedMatching, nestedReplacement);
                                }
                            }
                        }
                    }
                }
            }

            outTemp = new FileOutputStream(this.getTempFilePath(outDocTempFile));
            doc.write(outTemp);
            inTemp.close();
            outTemp.close();

            PDFUtil.createPdfWithWord(this.getTempFilePath(outDocTempFile), this.request.getSession().getServletContext().getRealPath("/"));

            this.response.setContentType("application/pdf");
            if (!"true".equals(isPrint)) {
                this.response.setHeader("Content-Disposition", "attachment; filename=\"" + pdfFile + "\"");
            }
            out = this.response.getOutputStream();

            File tempPdfFile = new File(this.getTempFilePath(outPdfTempFile));
            fileInputStream = new FileInputStream(tempPdfFile);
            OutputStream responseOutputStream = this.response.getOutputStream();
            int bytes;
            while ((bytes = fileInputStream.read()) != -1) {
                responseOutputStream.write(bytes);
            }

        } catch (Exception ioe) {
            ioe.printStackTrace();
            throw new ServletException(ioe.getMessage());
        } finally {
            if (outTemp != null) {
                outTemp.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (inTemp != null) {
                inTemp.close();
            }
            if (out != null) {
                out.close();
                new File(this.getTempFilePath(outDocTempFile)).delete();
                new File(this.getTempFilePath(outPdfTempFile)).delete();
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private String invokeValueDelegataire(Delegation delegation, String fieldName, int idx, List<Collaborateur> delegataires) {
        fieldName = fieldName.substring("delegataire".length());
        String firstLetter = fieldName.substring(0, 1);
        String remainingPart = fieldName.substring(1, fieldName.length());
        String methodName = GET_METHOD_PREFIX + firstLetter.toUpperCase() + remainingPart;

        String ret = "";
        try {

            // Get the class type of the bean.
            Class beanClass = delegataires.get(idx).getClass();

            // Get the method to be invoked
            Method beanMethod = beanClass.getMethod(methodName, null);

            // Invoke the method against the bean with the parameter arguments
            Object obj = beanMethod.invoke(delegataires.get(idx), null);
            if (obj != null) {
                if (obj instanceof Date) {
                    ret = CommonUtils.formatDateToString((Date) obj);
                } else if (obj instanceof Float) { // tdo
                    ret = CommonUtils.formatFloat((Float) obj);
                } else {
                    ret = obj.toString();
                }
            }
        } catch (Exception e) {

        }
        return ret;
    }

    private boolean belongsToExceptionFields(String field) {
        return (field.equalsIgnoreCase("delegataireFirstname") || field.equalsIgnoreCase("delegataireLastname"));
    }

    private String getRealFilePath(String fileName) {
        String pathContext = this.request.getSession().getServletContext().getRealPath(File.separator);
        return CatalinaPropertiesUtil.getVipDirectory(pathContext) + ServerConstant.TEMPLATE_FILE_PATH + File.separator + fileName;
    }

    private String getTempFilePath(String fileName) {
        String realContextPath = this.request.getSession().getServletContext().getRealPath(File.separator);
        return realContextPath + ServerConstant.TEMP_FILE_PATH + File.separator + fileName;
    }

    private String invokeValue(Delegation bean, String fieldName) {
        String firstLetter = fieldName.substring(0, 1);
        String remainingPart = fieldName.substring(1, fieldName.length());
        String methodName = GET_METHOD_PREFIX + firstLetter.toUpperCase() + remainingPart;

        String ret = "";
        try {

            // Get the class type of the bean.
            Class beanClass = bean.getClass();

            // Get the method to be invoked
            Method beanMethod = beanClass.getMethod(methodName, null);

            // Invoke the method against the bean with the parameter arguments
            Object obj = beanMethod.invoke(bean, null);
            if (obj != null) {
                if (obj instanceof Date) {
                    ret = CommonUtils.formatDateToString((Date) obj);
                } else if (obj instanceof Float) { // tdo
                    ret = CommonUtils.formatFloat((Float) obj);
                } else {
                    ret = obj.toString();
                }
            }
        } catch (Exception e) {

        }
        return ret;
    }
}
