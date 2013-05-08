package com.structis.fichesst.server.service.exportpdf;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.client.constant.ConstantClient;

@SuppressWarnings("serial")
public class ExportPdfListOfAccomptesServlet extends ExportPdfServlet {
	
	public final static String FILE_NAME = "list_of_accomptes.pdf";
	@Override
	@Transactional(readOnly=true)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		/*try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		super.doPost(request, response);*/
		String filename = sdf.format(Calendar.getInstance().getTime())+ "_" + FILE_NAME;
		String generaleInformation = request.getParameter("generaleInformation");
		String information = request.getParameter("information");
		String deductions = request.getParameter(ConstantClient.DEDUCTIONDTO_ID_STR);
		String paramPrestation = request.getParameter("paramPrestation");
		String totaldeduction = request.getParameter("totaldeduction");
		String penaltys = request.getParameter(ConstantClient.PENALTYDTO_ID_STR);
		String amount = request.getParameter("amount");
		String commentaire = request.getParameter("commentaire");
		String internalCommentaire = request.getParameter("internalCommentaire");
		//String localeString = request.getParameter("locale");
		/*Locale clientLocale = Locale.FRENCH;
		if(localeString != null){
			clientLocale = new Locale(localeString);
		}*/
		try {		
			//response.setContentType("application/pdf" );
			response.setHeader ("Content-Disposition","inline;filename=" + filename);
			ExportPdfAccomptes pdf = new ExportPdfAccomptes(response.getOutputStream(), messageSource,clientLocale);
			
			pdf.setGeneraleInformation(generaleInformation);
			pdf.setInformation(information);
			pdf.setDeductions(deductions);
			pdf.setTotaldeduction(totaldeduction);
			pdf.setPenaltys(penaltys);
			pdf.setAmount(amount);
			pdf.setCommentaire(commentaire);
			pdf.setInternalCommentaire(internalCommentaire);
			pdf.setPrestation(paramPrestation);
			pdf.setLocaltion(clientLocale);
			//process export
			pdf.process();
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
