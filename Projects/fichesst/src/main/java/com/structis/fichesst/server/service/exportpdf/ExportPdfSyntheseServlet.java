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

import com.structis.fichesst.shared.util.Constants;

@SuppressWarnings("serial")
public class ExportPdfSyntheseServlet extends ExportPdfServlet {
	
	public final static String FILE_NAME = "list_of_synthese.pdf"; 
	/*@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("com.structis.fichesst.client.message.Messages");
	}
	*/
	@Override
	@Transactional(readOnly=true)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		/*try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e2) {
			e2.printStackTrace();
		}*/
		String filename = sdf.format(Calendar.getInstance().getTime())+ "_" + FILE_NAME;
		
		String paramChantier= request.getParameter("paramChantier");
		String paramProrata= request.getParameter("paramProrata");
	
		//+ Grid 1
		String paramKeyGrid1= request.getParameter("paramKeyGrid1");
		String paramMapGrid1 = request.getParameter("paramMapGrid1");
		String paramMapTotalGroupGrid1 = request.getParameter("paramMapTotalGroupGrid1");
		String paramTotalSumGrid1 = request.getParameter("paramTotalSumGrid1");
	
		//+ Grid 2
		String paramKeyGrid2 = request.getParameter("paramKeyGrid2");
		String paramMapGrid2 = request.getParameter("paramMapGrid2");
		String paramMapTotalGroupGrid2 = request.getParameter("paramMapTotalGroupGrid2");
		String paramTotalSumGrid2 = request.getParameter("paramTotalSumGrid2");
		
		//+ Grid 3
		String paramGrid3 = request.getParameter("paramGrid3");
		String paramTotalSumGrid3 = request.getParameter("paramTotalSumGrid3");
		
		//+Grid 4
		String paramGrid4 = request.getParameter("paramGrid4");
		String paramTotalSumGrid4 = request.getParameter("paramTotalSumGrid4");
	
		Properties props = new Properties();
		try {
			props.load(new StringReader(paramMapGrid1.substring(1, paramMapGrid1.length() - 1).replace(Constants.SEPRATE + " ", "\n")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}       
		Map<String, String> mapGrid1 = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet())
			mapGrid1.put((String)e.getKey(), (String)e.getValue());
		
		props = new Properties();
		try {
			props.load(new StringReader(paramMapTotalGroupGrid1.substring(1, paramMapTotalGroupGrid1.length() - 1).replace(Constants.SEPRATE + " ", "\n")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Map<String, String> mapTotalGroupGrid1 = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet())
			mapTotalGroupGrid1.put((String)e.getKey(), (String)e.getValue());
		
		props = new Properties();
		try {
			props.load(new StringReader(paramMapGrid2.substring(1, paramMapGrid2.length() - 1).replace(Constants.SEPRATE + " ", "\n")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}       
		Map<String, String> mapGrid2 = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet())
			mapGrid2.put((String)e.getKey(), (String)e.getValue());
		
		props = new Properties();
		try {
			props.load(new StringReader(paramMapTotalGroupGrid2.substring(1, paramMapTotalGroupGrid2.length() - 1).replace(Constants.SEPRATE + " ", "\n")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Map<String, String> mapTotalGroupGrid2 = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet())
			mapTotalGroupGrid2.put((String)e.getKey(), (String)e.getValue());
		/*String localeString = request.getParameter("locale");
		Locale clientLocale = Locale.FRENCH;
		if(localeString != null){
			clientLocale = new Locale(localeString);
		}*/
		try {	
			List<String> grid1 = new ArrayList<String>();
			List<String> totalRowGrid1 = new ArrayList<String>();
			String value = "";
			if(paramKeyGrid1!=null && paramKeyGrid1.length() > 0){
				String[] keyGrid1 = paramKeyGrid1.split(Constants.SEPRATE);
				for(int i=0;i < keyGrid1.length; i++){
					value = mapGrid1.get(keyGrid1[i]);
					grid1.add(value);
					value = mapTotalGroupGrid1.get(keyGrid1[i]);
					totalRowGrid1.add(value);
				}
			}
			List<String> grid2 = new ArrayList<String>();
			List<String> totalRowGrid2 = new ArrayList<String>();
			if(paramKeyGrid2!=null && paramKeyGrid2.length() > 0){
				String[] keyGrid2 = paramKeyGrid2.split(Constants.SEPRATE);
				for(int i=0;i < keyGrid2.length; i++){
					value = mapGrid2.get(keyGrid2[i]);
					grid2.add(value);
					value = mapTotalGroupGrid2.get(keyGrid2[i]);
					totalRowGrid2.add(value);
				}
			}
			//response.setContentType("application/pdf" );
			response.setHeader ("Content-Disposition","inline;filename=" + filename);
			ExportPdfSynthese pdf = new ExportPdfSynthese(response.getOutputStream(), messageSource,clientLocale);
			pdf.setInformationChantier(paramChantier);
			pdf.setInformationProrata(paramProrata);
			pdf.setGrid1(grid1);
			pdf.setTotalRowGrid1(totalRowGrid1);
			pdf.setSumTotalGrid1(paramTotalSumGrid1);
			pdf.setGrid2(grid2);
			pdf.setTotalRowGrid2(totalRowGrid2);	
			pdf.setSumTotalGrid2(paramTotalSumGrid2);
			pdf.setGrid3(paramGrid3);
			pdf.setSumGrid3(paramTotalSumGrid3);
			pdf.setGrid4(paramGrid4);
			pdf.setSumGrid4(paramTotalSumGrid4);
			//process export
			pdf.process();
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
