package com.structis.fichesst.server.service.exportpdf;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.client.constant.ConstantClient;
import com.structis.fichesst.shared.util.Constants;

@SuppressWarnings("serial")
public class ExportPdfAllFichestServlet extends ExportPdfServlet {
	
	public final static String FILE_NAME = "all_fichest.pdf";
	//@Autowired
	//ResourceBundleMessageSource messageSource;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//messageSource = new ResourceBundleMessageSource();
	   // messageSource.setBasename("com.structis.fichesst.client.message.Messages");
	}
	@Override
	@Transactional(readOnly=true)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String filename = sdf.format(Calendar.getInstance().getTime())+ "_" + FILE_NAME;
		String generaleInformation = request.getParameter("generaleInformation");
		String conditionsparticulieres = request.getParameter("conditionsparticulieres");
		String prestations = request.getParameter("prestations");
		String informationscomplementaires = request.getParameter("informationscomplementaires");
		String cautionFournies = request.getParameter("cautionFournies");
		//Parameters for gestional report
		String key_gestion = request.getParameter("key_gestion");
		String gestions = request.getParameter(ConstantClient.GESTIONDTO_ID_STR);
		String totalgestion= request.getParameter("totalgestion");
		String alltotalgestion = request.getParameter("alltotalgestion");	
		String summary = request.getParameter("summary");
		String budget = request.getParameter("budget");
		Properties props = new Properties();
		try {
			props.load(new StringReader(gestions.substring(1, gestions.length() - 1).replace(Constants.SEPRATE + " ", "\n")));
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}       
		Map<String, String> mapGestion = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			mapGestion.put((String)e.getKey(), (String)e.getValue());
		}	
		props = new Properties();
		try {
			props.load(new StringReader(totalgestion.substring(1, totalgestion.length() - 1).replace(Constants.SEPRATE + " ", "\n")));
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}       
		Map<String, String> mapTotalGestion = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			mapTotalGestion.put((String)e.getKey(), (String)e.getValue());
		}
		List<String> listTotalGestion = new ArrayList<String>();
		List<String> listGestion = new ArrayList<String>();
		String value = "";
		if( key_gestion != null && key_gestion.trim() != "" ) {
			String key[] = key_gestion.split(Constants.SEPRATE);
			for( int i = 0 ; i < key.length ; i++ ) {
				value = mapGestion.get(key[i]);
				if( i == key.length - 1 )
					value = value.substring(0, value.length() - 1);
				value = value.substring(0, value.length() - 1);
				listGestion.add(value);
				value = mapTotalGestion.get(key[i]);
				if( i == key.length - 1 )
					value = value.substring(0, value.length() - 1);
				value = value.substring(0, value.length() - 1);
				listTotalGestion.add(value);
			}
		}
		
		//Parameters for report Process
		String processes = request.getParameter(ConstantClient.PROCESSDTO_ID_STR);	
		String totalsituation = request.getParameter("totalsituation");
		//Grid 2
		String grid2_report = request.getParameter("detail_des_retenues_appliques");
		//Grid 3
		String grid3_report = request.getParameter("grid_3_report");
		String commentaire = request.getParameter("commentaire");
		
		//Deducation
		String information = request.getParameter("information");	
		String deductions = request.getParameter(ConstantClient.DEDUCTIONDTO_ID_STR);
		String totaldeduction = request.getParameter("totaldeduction");
		String penaltys = request.getParameter(ConstantClient.PENALTYDTO_ID_STR);
		String amount = request.getParameter("amount");
		String deductionComment1 = request.getParameter("deductionComment1");
		String deductionComment2 = request.getParameter("deductionComment2");
		String etatAvancement = request.getParameter("etatAvancement");
		
		try {
			//response.setContentType("application/pdf" );
			response.setHeader ("Content-Disposition","inline;filename=" + filename);
		
			ExportPdfFichest pdf = new ExportPdfFichest(response.getOutputStream(), messageSource,clientLocale);	
			pdf.setGeneraleInformation(generaleInformation);
			pdf.setConditionsparticulieres(conditionsparticulieres);
			pdf.setPrestations(prestations);
			pdf.setInformationscomplementaires(informationscomplementaires);
			pdf.setCautionFournies(cautionFournies);
			//Grid 2
			pdf.setGestions(listGestion);
			pdf.setTotalgestions(listTotalGestion);
			pdf.setAlltotalgestion(alltotalgestion);
			pdf.setLocaltion(clientLocale);	
			pdf.setSummary(summary);
			pdf.setBudget(budget);
			//Grid 3
			pdf.setProcesses(processes);
			pdf.setTotalsituation(totalsituation);
			pdf.setGrid2(grid2_report);
			pdf.setGrid3(grid3_report);
			pdf.setLocaltion(clientLocale);	
			pdf.setCommentaire(commentaire);
			//Grid 4
			pdf.setInformation(information);
			pdf.setDeductions(deductions);
			pdf.setPenaltys(penaltys);
			pdf.setAmount(amount);
			pdf.setTotaldeduction(totaldeduction);
			pdf.setDeductionComment1(deductionComment1);
			pdf.setDeductionComment2(deductionComment2);
			pdf.setEtatAvancement(etatAvancement);
			
			pdf.process();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
