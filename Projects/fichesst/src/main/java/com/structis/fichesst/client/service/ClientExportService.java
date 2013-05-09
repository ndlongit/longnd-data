package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.fichesst.shared.dto.CautionFournieDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.dto.TransfertPpSummaryDto;

@RemoteServiceRelativePath("springGwtServices/clientExportService")
public interface ClientExportService extends RemoteService {

	String exportGestionList(List<GestionDto> gestionList, FicheStDto ficheSt);

	String exportAccomptes(FicheStDto ficheStDto, List<DeductionDto> deductions, List<PenaltyDto> penalties);

	String exportSyntheseData(ChantierModel chantier, List<FicheStDto> ficheStList1, List<FicheStDto> ficheStList2, List<FicheStDto> ficheStList4,
			List<TransfertPpSummaryDto> listTransfertPpGrid);

	String exportAvancements(FicheStDto ficheStDto, List<ProgressDto> progressDtos, List<GestionDto> gestionDtoList,
			List<DeductionDto> deductionDtoList, List<PenaltyDto> models);

	String exportFicheSt(FicheStDto model, List<CautionFournieDto> cautionFournieDtoList, List<GestionDto> gestionDtoList,
			List<ProgressDto> progressDtoList, List<DeductionDto> deductionDtoList, List<PenaltyDto> penaltyDtoList,
			String totaldeduction, String detailRetenues, String grid3Str, String etatAvancement, String totalsituation);
}
