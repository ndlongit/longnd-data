package com.structis.fichesst.server.service.exportpdf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.bean.domain.CautionFournie;
import com.structis.fichesst.server.bean.domain.FicheSt;
import com.structis.fichesst.server.core.SpringGetter;
import com.structis.fichesst.server.service.domain.CautionFournieService;
import com.structis.fichesst.server.service.domain.FicheStService;
import com.structis.fichesst.shared.util.Constants;

@SuppressWarnings("serial")
public class ExportPdfCustomFichestServlet extends ExportPdfServlet {

	public final static String FILE_NAME = "all_fichest.pdf";
	private FicheStService ficheStService;
	private CautionFournieService cautionFournieService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ficheStService = (FicheStService) SpringGetter.getBean(config.getServletContext(), "ficheStService");
		cautionFournieService = (CautionFournieService) SpringGetter.getBean(config.getServletContext(), "cautionFournieService");
	}

	@Override
	@Transactional(readOnly = true)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String filename = sdf.format(Calendar.getInstance().getTime()) + "_" + FILE_NAME;
		List<FicheSt> listFicheSt = new ArrayList<FicheSt>();

		String fichestIds = request.getParameter("fichestIds");
		String hasGestion = request.getParameter("isHasGestion");
		if (fichestIds != null && fichestIds.length() > 0) {
			FicheSt ficheSt = null;
			List<CautionFournie> listCautionFournie = null;
			fichestIds = fichestIds.substring(0, fichestIds.length() - Constants.SEPRATE.length());
			String[] arr = fichestIds.split(Constants.SEPRATE);
			for (int i = 0; i < arr.length; i++) {
				if("null".equalsIgnoreCase(arr[i])){
					continue;
				}
				
				ficheSt = ficheStService.find(Integer.parseInt(arr[i]));
				if (ficheSt != null) {
					listCautionFournie = cautionFournieService.findByFicheStId(ficheSt.getId());
					ficheSt.setCautionFournies(listCautionFournie);
					listFicheSt.add(ficheSt);
				}
			}
		}
		try {
			// response.setContentType("application/pdf" );
			response.setHeader("Content-Disposition", "inline;filename=" + filename);
			ExportPdfCustomFichest pdf = new ExportPdfCustomFichest(response.getOutputStream(), messageSource, clientLocale);
			pdf.setListFicheSt(listFicheSt);
			pdf.setHasGestion(hasGestion);
			pdf.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
