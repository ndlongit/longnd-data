package com.structis.vip.server.service.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.structis.vip.client.message.Messages;
import com.structis.vip.server.bean.domain.Delegation;
import com.structis.vip.server.core.SpringGetter;
import com.structis.vip.server.i18n.GWTI18N;
import com.structis.vip.server.service.domain.DomDelegationService;
import com.structis.vip.server.util.CommonUtils;

public class PrintDelegationGridServiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private DomDelegationService domDelegationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.domDelegationService = (DomDelegationService) SpringGetter.getBean(config.getServletContext(), "domDelegationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String localeCode = request.getParameter("localeCode");
        String[] delegationList = request.getParameterValues("delegationId");

        Messages messages = GWTI18N.create(Messages.class, localeCode);

        List<Integer> delegationIds = new ArrayList<Integer>();
        if (delegationList != null && delegationList.length > 0) {
            for (String delegationId : delegationList) {
                delegationIds.add(Integer.parseInt(delegationId));
            }

            List<Delegation> delegations = this.domDelegationService.getAllDelegationById(delegationIds);

            response.setContentType("text/html; charset=UTF-8");
            response.setHeader("Vary", "Accept-Language");
            response.setCharacterEncoding("utf-8");

            PrintWriter out = response.getWriter();
            out.println("<html style=\"background:\none;\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"vip.css\">");
            out.println("<title>" + messages.delegationdetailbuttonprinter() + "</title></head>");
            out.println("<body onload=\"window.print();\">");

            out.println("<table class=\"grid-report\" cellspacing=\"0\" cellpadding=\"0\">");
            out.println("<thead>" + "<tr>" + "<th style=\"width: 15%;\">" + messages.perimetre() + "</th>" + "<th style=\"width: 15%;\">"
                    + messages.nature() + "</th>" + "<th style=\"width: 15%;\">" + messages.delegant() + "</th>" + "<th style=\"width: 15%;\">"
                    + messages.delegataire() + "</th>" + "<th style=\"width: 10%;\">" + messages.debutdevalidite() + "</th>"
                    + "<th style=\"width: 10%;\">" + messages.findevalidite() + "</th>" + "<th style=\"width: 8%;\">" + messages.delegationstatus()
                    + "</th>" + "<th style=\"width: 8%;\">" + messages.type() + "</th>" + "</tr>" + "</thead>");
            out.println("<tbody>");
            if (delegations != null && !delegations.isEmpty()) {
                for (Delegation item : delegations) {
                    out.println("<tr>");
                    out.println("<td>" + item.getPerimeter().getName() + "&nbsp;</td>");
                    out.println("<td>" + item.getDelegationNature().getName() + "&nbsp;</td>");
                    out.println("<td>" + item.getDelegant().getFullname() + "&nbsp;</td>");
                    out.println("<td>" + item.getDelegataire().getFullname() + "&nbsp;</td>");
                    out.println("<td>" + CommonUtils.formatDateToString(item.getStartDate()) + "&nbsp;</td>");
                    out.println("<td>" + CommonUtils.formatDateToString(item.getEndDate()) + "&nbsp;</td>");
                    out.println("<td>" + item.getDelegationStatus().getName() + "</td>");
                    out.println("<td>" + item.getDelegationType().getName() + "&nbsp;</td>");
                    out.println("</tr>");
                }
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</body></html>");
        }
    }
}
