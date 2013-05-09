package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.shared.dto.CautionFournieDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.dto.TransfertPpSummaryDto;

public interface ClientExportServiceAsync {

	public static class Util {

		private static ClientExportServiceAsync instance = GWT.create(ClientExportService.class);

		public static ClientExportServiceAsync getInstance() {
			return instance;
		}
	}

	void exportGestionList(List<GestionDto> gestionList, FicheStDto ficheSt, AsyncCallback<String> callback);

	void exportAccomptes(FicheStDto ficheStDto, List<DeductionDto> deductions, List<PenaltyDto> penalties, AsyncCallback<String> callback);

	void exportSyntheseData(ChantierModel chantier, List<FicheStDto> ficheStList1, List<FicheStDto> ficheStList2, List<FicheStDto> ficheStList4,
			List<TransfertPpSummaryDto> listTransfertPpGrid, AsyncCallback<String> callback);

	void exportAvancements(FicheStDto ficheStDto, List<ProgressDto> progressDtos, List<GestionDto> gestionDtoList,
			List<DeductionDto> deductionDtoList, List<PenaltyDto> models, AsyncCallback<String> callback);

	void exportFicheSt(FicheStDto model, List<CautionFournieDto> cautionFournieDtoList, List<GestionDto> gestionDtoList,
			List<ProgressDto> progressDtoList, List<DeductionDto> deductionDtoList, List<PenaltyDto> penaltyDtoList, String totaldeduction,
			String detailRetenues, String grid3Str, String etatAvancement, String totalsituation, AsyncCallback<String> callback);
}
