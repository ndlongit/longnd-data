package com.structis.fichesst.server.service.client;

import java.io.File;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.structis.fichesst.client.service.ClientExportService;
import com.structis.fichesst.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.fichesst.server.service.domain.GestionServiceImpl;
import com.structis.fichesst.server.service.exportpdf.ExportPdfAccomptes;
import com.structis.fichesst.server.service.exportpdf.ExportPdfFichest;
import com.structis.fichesst.server.service.exportpdf.ExportPdfGestions;
import com.structis.fichesst.server.service.exportpdf.ExportPdfProgresses;
import com.structis.fichesst.server.service.exportpdf.ExportPdfSynthese;
import com.structis.fichesst.shared.dto.CautionFournieDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.dto.TransfertPpSummaryDto;

@Service("clientExportService")
public class ClientExportServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientExportService {

	protected Locale clientLocale = Locale.FRENCH;

	private static final Logger LOGGER = Logger.getLogger(GestionServiceImpl.class);

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@Override
	public String exportGestionList(List<GestionDto> gestionList, FicheStDto ficheSt) {
		try {
			ExportPdfGestions exporter = new ExportPdfGestions(ficheSt, gestionList, messageSource, clientLocale);

			// process export
			File f = exporter.process();

			if( f != null ) {
				return f.getName();
			}
			else {
				return "";
			}
		}
		catch( Exception e ) {
			LOGGER.error(e.getMessage(), e);
			return "";
		}
	}

	@Override
	public String exportAccomptes(FicheStDto ficheStDto, List<DeductionDto> deductions, List<PenaltyDto> penalties) {
		try {
			ExportPdfAccomptes exporter = new ExportPdfAccomptes(
					messageSource, clientLocale, ficheStDto, deductions, penalties);
			File f = exporter.process();
			if( f != null ) {
				return f.getName();
			}
			else {
				return "";
			}
		}
		catch( Exception e ) {
			LOGGER.error(e.getMessage(), e);
			return "";
		}
	}

	@Override
	public String exportAvancements(FicheStDto ficheStDto, List<ProgressDto> progressDtos, List<GestionDto> gestionDtoList,
			List<DeductionDto> deductionDtoList, List<PenaltyDto> penaltyDtoList) {
		File f = null;
		try {
			ExportPdfProgresses exporter = new ExportPdfProgresses(
					messageSource, clientLocale, ficheStDto, progressDtos, gestionDtoList, deductionDtoList, penaltyDtoList);
			f = exporter.process();
		}

		catch( Exception e ) {
			LOGGER.error(e.getMessage(), e);
		}
		if( f == null ) {
			return "";
		}
		else {
			return f.getName();
		}
	}

	@Override
	public String exportSyntheseData(ChantierModel chantier, List<FicheStDto> ficheStList1, List<FicheStDto> ficheStList2,
			List<FicheStDto> ficheStList4, List<TransfertPpSummaryDto> listTransfertPpGrid) {

		try {
			ExportPdfSynthese pdf = new ExportPdfSynthese(
					chantier, ficheStList1, ficheStList2, ficheStList4, listTransfertPpGrid, messageSource, clientLocale);
			File f = pdf.process();
			if( f != null ) {
				return f.getName();
			}
			else {
				return "";
			}
		}
		catch( Exception e ) {
			LOGGER.error(e.getMessage(), e);
			return "";
		}
	}

	@Override
	public String exportFicheSt(FicheStDto ficheStDto, List<CautionFournieDto> cautionFournieDtoList,
			List<GestionDto> gestionDtoList, List<ProgressDto> progressDtoList, List<DeductionDto> deductionDtoList,
			List<PenaltyDto> penaltyDtoList, String totaldeduction, String detailRetenues, String grid3Str,
			String etatAvancement, String totalsituation) {

		try {
			ExportPdfFichest pdf = new ExportPdfFichest(
					messageSource, clientLocale, ficheStDto, cautionFournieDtoList, gestionDtoList, progressDtoList,
					deductionDtoList, penaltyDtoList, totaldeduction, detailRetenues, grid3Str, etatAvancement,
					totalsituation);

			File f = pdf.process();
			if( f != null ) {
				return f.getName();
			}
			else {
				return "";
			}
		}
		catch( Exception e ) {
			LOGGER.error(e.getMessage(), e);
			return "";
		}
	}
}
