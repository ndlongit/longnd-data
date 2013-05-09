package com.structis.fichesst.client.ecran;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.structis.fichesst.client.event.ExportAvancementEvent;
import com.structis.fichesst.client.event.ExportFicheStEvent;
import com.structis.fichesst.client.event.ExportGestionEvent;
import com.structis.fichesst.client.event.ExportSuiviDesAccomptesEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.handler.ExportAvancementHandler;
import com.structis.fichesst.client.handler.ExportFicheStHandler;
import com.structis.fichesst.client.handler.ExportGestionHandler;
import com.structis.fichesst.client.handler.ExportSuiviDesAccomptesHandler;
import com.structis.fichesst.client.handler.SaveFicheStHandler;
import com.structis.fichesst.client.panel.BreadcrumbPanel;
import com.structis.fichesst.client.panel.FicheSTCenterPanel;
import com.structis.fichesst.client.panel.HeaderPanel;
import com.structis.fichesst.client.service.ClientExportServiceAsync;
import com.structis.fichesst.client.service.ClientFicheStServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.CautionFournieDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class FicheSTEcran extends AbstractEcran {

	private final BreadcrumbPanel breadcrumbPanel;

	private final FicheSTCenterPanel centerPanel;

	private final ChantierModel chantier;

	private Integer ficheStId;

	private final HeaderPanel headerPanel;

	private final RoleModel role;

	private final UtilisateurGrpModel user;
	private final String societeName;

	public FicheSTEcran() {
		super();
		registerHandlers();

		chantier = navigation.getContext().getCurrentChantier();
		user = navigation.getContext().getUserModel();
		ficheStId = navigation.getContext().getFichestId();
		role = navigation.getContext().getRoleModel();
		societeName = navigation.getContext().getSocieteName();
		setScrolling();
		LayoutContainer mainContent = new LayoutContainer();
		mainContent.setLayout(new FitLayout());
		headerPanel = new HeaderPanel();
		headerPanel.setHeight(45);
		centerPanel = new FicheSTCenterPanel(bus, chantier, ficheStId, role, user);
		breadcrumbPanel = new BreadcrumbPanel(bus, chantier, societeName);
		mainContent.add(headerPanel);
		mainContent.add(breadcrumbPanel);
		mainContent.add(centerPanel);
		add(mainContent);
	}

	private void registerHandlers() {
		bus.addHandler(ExportGestionEvent.TYPE, new ExportGestionHandler() {

			@Override
			public void onExport(ExportGestionEvent event) {

				if (!centerPanel.isValid()) {
					return;
				}
				ClientExportServiceAsync clientExportService = ClientExportServiceAsync.Util.getInstance();
				clientExportService.exportGestionList(centerPanel.getGestionDtoList(), (FicheStDto) centerPanel.getModel(),
						new AsyncCallbackWithErrorResolution<String>() {
							@Override
							public void onSuccess(String fileName) {
								showDownloadDialog(fileName);
							}
						});

			}
		});
		bus.addHandler(ExportAvancementEvent.TYPE, new ExportAvancementHandler() {

			@Override
			public void onExport(ExportAvancementEvent event) {
				if (!centerPanel.isValid()) {
					return;
				}

				ClientExportServiceAsync.Util.getInstance().exportAvancements((FicheStDto) centerPanel.getModel(), centerPanel.getProgressDtoList(),
						centerPanel.getGestionDtoList(), centerPanel.getDeductionDtoList(), centerPanel.getPenaltyDtoList(),
						new AsyncCallbackWithErrorResolution<String>() {
							@Override
							public void onSuccess(String fileName) {
								showDownloadDialog(fileName);
							}
						});
			}
		});
		bus.addHandler(ExportSuiviDesAccomptesEvent.TYPE, new ExportSuiviDesAccomptesHandler() {

			@Override
			public void onExport(ExportSuiviDesAccomptesEvent event) {
				if (!centerPanel.isValid()) {
					return;
				}
				ClientExportServiceAsync instance = ClientExportServiceAsync.Util.getInstance();
				instance.exportAccomptes((FicheStDto) centerPanel.getModel(), centerPanel.getDeductionDtoList(), centerPanel.getPenaltyDtoList(),
						new AsyncCallbackWithErrorResolution<String>() {
							@Override
							public void onSuccess(String fileName) {
								showDownloadDialog(fileName);
							}
						});

			}
		});
		bus.addHandler(SaveFicheStEvent.TYPE, new SaveFicheStHandler() {
			@Override
			public void onSave(final SaveFicheStEvent event) {
				if (!centerPanel.isValid()) {
					return;
				}
				showSaving(FicheSTEcran.this);
				AsyncCallbackWithErrorResolution<FicheStDto> callback = new AsyncCallbackWithErrorResolution<FicheStDto>() {
					@Override
					public void onFailure(Throwable caught) {
						unmaskAll();
						super.onFailure(caught);
					}

					@Override
					public void onSuccess(FicheStDto result) {
						unmaskAll();
						result.getCautionFournies();
						ficheStId = result.getId();
						centerPanel.setFicheStId(ficheStId);
						breadcrumbPanel.setSociete(result.getSociete());
						centerPanel.commitDataChange();
						GuiUtil.showSuccessInfo();

						if (event.getNextPage() != null) {
							GuiUtil.gotoEcran(event.getNextPage());
						}
					}

					private void unmaskAll() {
						FicheSTEcran.this.unmask();
					}
				};

				saveOrUpdateFicheSt(callback);
			}

			private void saveOrUpdateFicheSt(AsyncCallbackWithErrorResolution<FicheStDto> callback) {
				FicheStDto model = (FicheStDto) centerPanel.getModel();
				model.setId(ficheStId);

				model.setCautionFournies(getCautionFournieSaved());
				model.setGestions(getGestionSaved());
				model.setDeductions(getDeductionSaved());
				model.setProgresses(getProgressSaved());
				model.setPenalties(getPenaltySaved());

				ClientFicheStServiceAsync clientFicheStService = ClientFicheStServiceAsync.Util.getInstance();

				if (ficheStId != null && ficheStId > 0) {
					clientFicheStService.update(model, callback);
				} else {
					clientFicheStService.save(model, callback);
				}
			}
		});

		bus.addHandler(ExportFicheStEvent.TYPE, new ExportFicheStHandler() {
			@SuppressWarnings("unchecked")
			@Override
			public void onExport(ExportFicheStEvent event) {
				if (!centerPanel.isValid()) {
					return;
				}
				ComponentManager componentMan = ComponentManager.get();
				TextField<String> txtTotalDeduction = (TextField<String>) componentMan.get("ACCOMPTES_PANEL_TOTALDEDUCTION_ID");
				String totaldeduction = "";
				if (txtTotalDeduction.getValue() != null && txtTotalDeduction.getValue().length() > 0) {
					totaldeduction = txtTotalDeduction.getValue().substring(0, txtTotalDeduction.getValue().length() - 1);
				}

				TextField<String> detailRetenuesField = (TextField<String>) componentMan.get("AVANCEMENTS_PANEL_TXTDETAIL_DES_RETENUES_APPLIQUES_ID");
				String detailRetenues = detailRetenuesField.getValue();

				TextField<String> grid3Field = (TextField<String>) componentMan.get("AVANCEMENTS_PANEL_TXTGRID3REPORT_ID");
				String grid3Str = grid3Field.getValue();

				TextField<String> txtEtatAvancement = (TextField<String>) componentMan.get("AVANCEMENTS_PANEL_TXT_ETATAVANCEMENT_ID");
				String etatAvancement = txtEtatAvancement.getValue();

				TextField<String> txttotalsituation = (TextField<String>) componentMan.get("AVANCEMENTS_PANEL_TXTTOTALSITUATION_ID");
				String totalsituation = txttotalsituation.getValue();

				ClientExportServiceAsync clientExportService = ClientExportServiceAsync.Util.getInstance();
				clientExportService.exportFicheSt((FicheStDto) centerPanel.getModel(), centerPanel.getCautionFournieDtoList(),
						centerPanel.getGestionDtoList(), centerPanel.getProgressDtoList(), centerPanel.getDeductionDtoList(),
						centerPanel.getPenaltyDtoList(), totaldeduction, detailRetenues, grid3Str, etatAvancement, totalsituation,
						new AsyncCallbackWithErrorResolution<String>() {
							@Override
							public void onSuccess(String fileName) {
								showDownloadDialog(fileName);
							}
						});
			}
		});

	}

	private List<CautionFournieDto> getCautionFournieSaved() {
		CautionFournieDto cautionFournieDto = null;
		List<CautionFournieDto> listCautionFournieSaved = new ArrayList<CautionFournieDto>();
		for (int i = 0; i < centerPanel.getCautionFournieDtoList().size(); i++) {
			cautionFournieDto = centerPanel.getCautionFournieDtoList().get(i);
			if (cautionFournieDto.getDate() != null || cautionFournieDto.getAmount() != 0) {
				listCautionFournieSaved.add(cautionFournieDto);
			}
		}
		return listCautionFournieSaved;
	}

	private List<DeductionDto> getDeductionSaved() {
		DeductionDto deductionDto = null;
		List<DeductionDto> listDeductionSaved = new ArrayList<DeductionDto>();
		for (int i = 0; i < centerPanel.getDeductionDtoList().size(); i++) {
			deductionDto = centerPanel.getDeductionDtoList().get(i);
			if (deductionDto.getDate() != null || deductionDto.getCanto() != 0 || deductionDto.getBadge() != 0 || deductionDto.getGrue() != 0
					|| deductionDto.getLift() != 0 || deductionDto.getBenne() != 0 || deductionDto.getNettoyage() != 0
					|| deductionDto.getAutres() != 0 || deductionDto.getProrata() != 0 || deductionDto.getRefacturations() != 0) {
				listDeductionSaved.add(deductionDto);
			}
		}
		return listDeductionSaved;
	}

	private List<GestionDto> getGestionSaved() {
		GestionDto gestionDto = null;
		List<GestionDto> listGestionSaved = new ArrayList<GestionDto>();
		for (int i = 0; i < centerPanel.getGestionDtoList().size(); i++) {
			gestionDto = centerPanel.getGestionDtoList().get(i);
			if (gestionDto.getDevis() != null || gestionDto.getStatut() != null || gestionDto.getLabel() != null || gestionDto.getComment() != null
					|| gestionDto.getTraite() != 0 || gestionDto.getMarche() != null || gestionDto.getTraite() != 0 || gestionDto.getArrete() != 0
					|| gestionDto.getNonArrete() != 0 || gestionDto.getProvision() != 0 || gestionDto.getDevisRefuse() != 0
					|| gestionDto.getReelActivitive() != 0 || gestionDto.getType() != null || gestionDto.getLabel2() != null
					|| gestionDto.getTraite() != 0) {
				if (gestionDto.getLock() == null)
					gestionDto.setLock(false);
				listGestionSaved.add(gestionDto);
			}
		}
		return listGestionSaved;
	}

	private List<PenaltyDto> getPenaltySaved() {
		PenaltyDto penaltyDto = null;
		List<PenaltyDto> listPenaltySaved = new ArrayList<PenaltyDto>();
		for (int i = 0; i < centerPanel.getPenaltyDtoList().size(); i++) {
			penaltyDto = centerPanel.getPenaltyDtoList().get(i);
			if (penaltyDto.getDate() != null || penaltyDto.getAmount() != 0 || penaltyDto.getComment() != null) {
				listPenaltySaved.add(penaltyDto);
			}
		}
		return listPenaltySaved;
	}

	private List<ProgressDto> getProgressSaved() {
		ProgressDto progressDto = null;
		List<ProgressDto> listProgressSaved = new ArrayList<ProgressDto>();
		for (int i = 0; i < centerPanel.getProgressDtoList().size(); i++) {
			progressDto = centerPanel.getProgressDtoList().get(i);
			if (progressDto.getLabel() != null || progressDto.getDate() != null || progressDto.getCumule() != 0 || progressDto.getCumule2() != 0) {
				listProgressSaved.add(progressDto);
			}
		}
		return listProgressSaved;
	}
}
