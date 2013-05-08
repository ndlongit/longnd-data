package com.structis.fichesst.server.service.exportpdf;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.client.constant.ConstantClient;

@SuppressWarnings("serial")
public class ExportPdfListOfProcessDtoServlet extends ExportPdfServlet {
	
	public final static String FILE_NAME = "list_of_process.pdf";
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	@Override
	@Transactional(readOnly=true)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		//super.doPost(request, response);
		String filename = sdf.format(Calendar.getInstance().getTime())+ "_" + FILE_NAME;
		String generaleInformation = request.getParameter("generaleInformation");
		String processes = request.getParameter(ConstantClient.PROCESSDTO_ID_STR);	
		String totalsituation = request.getParameter("totalsituation");
		String grid2_report = request.getParameter("detail_des_retenues_appliques");
		String grid3_report = request.getParameter("grid_3_report");
		String commentaire = request.getParameter("commentaire");
		String etatAvancement = request.getParameter("etatAvancement");
		
		/*String localeString = request.getParameter("locale");
		Locale clientLocale = Locale.FRENCH;
		if(localeString != null){
			clientLocale = new Locale(localeString);
		}*/
		try {
			//response.setContentType("application/pdf" );
			response.setHeader ("Content-Disposition","inline;filename=" + filename);
			ExportPdfProcessDto pdf = new ExportPdfProcessDto(response.getOutputStream(), messageSource,clientLocale);
			
			pdf.setGeneraleInformation(generaleInformation);
			pdf.setProcesses(processes);
			pdf.setTotalsituation(totalsituation);
			pdf.setGrid2(grid2_report);
			pdf.setGrid3(grid3_report);
			pdf.setEtatAvancement(etatAvancement);
			pdf.setCommentaire(commentaire);
			pdf.setLocaltion(clientLocale);	
			pdf.process();
				
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
