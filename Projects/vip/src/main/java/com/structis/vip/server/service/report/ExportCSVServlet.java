package com.structis.vip.server.service.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.service.ClientControlService;
import com.structis.vip.client.service.ClientUserService;
import com.structis.vip.server.core.Constants;
import com.structis.vip.server.core.SpringGetter;
import com.structis.vip.server.util.CatalinaPropertiesUtil;
import com.structis.vip.shared.ControlFilter;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.KeyValueModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserModel;

public class ExportCSVServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // private final Messages messages = GWT.create(Messages.class);

    protected SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    HttpServletRequest request;
    HttpServletResponse response;
    @Autowired
    ClientControlService clientControlService;

    @Autowired
    ClientUserService clientUserService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.clientUserService = (ClientUserService) SpringGetter.getBean(config.getServletContext(), "clientUserService");
        this.clientControlService = (ClientControlService) SpringGetter.getBean(config.getServletContext(), "clientControlService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
        String codeProject = request.getParameter("codeProject");
        String controllerName = request.getParameter("controllerName");
        String caracteres = request.getParameter("caracteres");
        String endDate = request.getParameter("endDate");
        String startDate = request.getParameter("startDate");
        String entId = request.getParameter("entId");
        String perId = request.getParameter("perId");
        String controlTypes = request.getParameter("controlTypes");
        String userId = request.getParameter("userId");

        Integer iUserId = Integer.parseInt(userId);
        UserModel user = this.clientUserService.findUserById(iUserId);

        PerimetreModel pm = new PerimetreModel();
        pm.setPerId(perId);
        PerimetreTreeModel treeModel = new PerimetreTreeModel(pm, user.getUserRoles());
        treeModel.setEntiteId(entId);
        treeModel.setLevel(0);
        treeModel.setIsEntite(false);
        SimpleDateFormat fmt = new SimpleDateFormat(ConstantClient.DATE_FORMAT);

        Date dStartDate = null;
        Date dEndDate = null;
        try {
            dStartDate = startDate == null ? null : fmt.parse(startDate);
            dEndDate = endDate == null ? null : fmt.parse(endDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        ControlFilter filter = new ControlFilter();
        EntiteModel em = new EntiteModel();
        em.setEntId(entId);
        filter.setEntite(em);

        filter.setPerimetre(pm);

        List<ControlTypeModel> iControlTypes = new ArrayList<ControlTypeModel>();
        String[] types = controlTypes.split(",");
        for (String t : types) {
            if (null != t && t.length() > 0) {
                ControlTypeModel cm = new ControlTypeModel();
                cm.setId(Integer.parseInt(t));
                iControlTypes.add(cm);
            }
        }

        filter.setTypes(iControlTypes);

        filter.setStartDate(dStartDate);
        filter.setEndDate(dEndDate);
        filter.setPerimetreTreeModel(treeModel);
        filter.setCodeProject(codeProject);
        List<KeyValueModel> sCaracteres = new ArrayList<KeyValueModel>();
        String[] cs = caracteres.split(",");
        for (String t : cs) {
            if (null != t && t.length() > 0) {
                KeyValueModel km = new KeyValueModel();
                km.setKey(t);
                km.setValue(t);
                sCaracteres.add(km);
            }
        }

        filter.setCaracteres(sCaracteres);
        filter.setControllerName(controllerName);
        filter.setUserRoles(user.getUserRoles());

        List<ControlModel> controls = this.clientControlService.getControls(filter);
        ServletOutputStream out = null;
        FileInputStream fileInputStream = null;

        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "file; filename=export.csv");

            PrintWriter responseOutputStream = response.getWriter();
            StringBuffer header = new StringBuffer();
            header.append("Libellé du Chantier");

            header.append(",").append("Code Projet").append(",").append("Type de Contrôle").append(",").append("Date du Contrôle").append(",")
                    .append("Nom du Contrôleur Interne").append(",").append("Nom du Contrôleur Externe");

            responseOutputStream.println(header.toString());

            for (ControlModel cm : controls) {
                StringBuffer line = new StringBuffer();
                String perName = cm.getPerimetre().getName() == null ? "" : cm.getPerimetre().getName();
                String externName = cm.getExtControlNames() == null ? "" : cm.getExtControlNames().replaceAll("<br>", ";");
                String codeProjet = cm.getCodeProject() == null ? "" : cm.getCodeProject();
                String controlType = cm.getControlType().getLabel() == null ? "" : cm.getControlType().getLabel();
                String controlDate = cm.getDate() == null ? "" : fmt.format(cm.getDate());
                String internName = cm.getCollaborateur() == null ? "" : cm.getCollaborateur().getLastname() + " "
                        + cm.getCollaborateur().getFirstname();

                line.append(perName).append(",").append(codeProjet).append(",").append(controlType).append(",").append(controlDate).append(",")
                        .append(internName).append(",").append(externName);
                responseOutputStream.println(line.toString());
                line = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    protected String getFilePath(String fileName) {
        String pathContext = this.request.getSession().getServletContext().getRealPath(File.separator);
        return CatalinaPropertiesUtil.getVipDirectory(pathContext) + Constants.DELEGATION_DOCUMENT_FILE_PATH + File.separator + fileName;
    }
}
