package com.structis.fichesst.server.service.exportpdf;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.client.constant.ConstantClient;
import com.structis.fichesst.shared.util.Constants;

@SuppressWarnings("serial")
public class ExportPdfListOfGestionDtoServlet extends ExportPdfServlet {
	
	public final static String FILE_NAME = "list_of_risques.pdf";
	
	@Override
	@Transactional(readOnly=true)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String filename = sdf.format(Calendar.getInstance().getTime())+ "_" + FILE_NAME;
		String generaleInformation= request.getParameter("generaleInformation");
		
		String keys = request.getParameter("keys");
		String gestions = request.getParameter(ConstantClient.GESTIONDTO_ID_STR);
		
		String totalgestion= request.getParameter("totalgestion");
		String alltotalgestion= request.getParameter("alltotalgestion");
		
		String summary = request.getParameter("summary");
		String budget = request.getParameter("budget");
		
		Properties props = new Properties();
		try {
			props.load(new StringReader(gestions.substring(1, gestions.length() - 1).replace(Constants.SEPRATE + " ", "\n")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}       
		Map<String, String> mapGestion = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			mapGestion.put((String)e.getKey(), (String)e.getValue());
		}
		
		props = new Properties();
		try {
			props.load(new StringReader(totalgestion.substring(1, totalgestion.length() - 1).replace(Constants.SEPRATE + " ", "\n")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}       
		Map<String, String> mapTotalGestion = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			mapTotalGestion.put((String)e.getKey(), (String)e.getValue());
		}

		try {		
			List<String> listTotalGestion = new ArrayList<String>();
			List<String> listGestion = new ArrayList<String>();
			String value = "";
			String key[] = keys.split(Constants.SEPRATE);
			
			for(int i=0;i < key.length; i++){
				value = mapGestion.get(key[i]);
				if(i == key.length -1)
					value = value.substring(0,value.length() - 1);
				value = value.substring(0,value.length() - 1);
				listGestion.add(value);
				value = mapTotalGestion.get(key[i]);
				if(i == key.length -1)
					value = value.substring(0,value.length() - 1);
				value = value.substring(0,value.length() - 1);
				listTotalGestion.add(value);
			}
			response.setHeader ("Content-Disposition","inline;filename=" + filename);
			ExportPdfGestionDto pdf = new ExportPdfGestionDto(response.getOutputStream(), messageSource,clientLocale);
			
			pdf.setGeneraleInformation(generaleInformation);
			pdf.setGestions(listGestion);
			pdf.setTotalgestions(listTotalGestion);
			pdf.setAlltotalgestion(alltotalgestion);
			pdf.setLocaltion(clientLocale);	
			pdf.setSummary(summary);
			pdf.setBudget(budget);
			
			//process export
			pdf.process();			
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}	
}
