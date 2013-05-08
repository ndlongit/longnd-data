package com.structis.fichesst.client.ecran;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.structis.fichesst.client.constant.ConstantClient;
import com.structis.fichesst.client.event.ExportFicheStEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.handler.ExportFicheStHandler;
import com.structis.fichesst.client.handler.SaveFicheStHandler;
import com.structis.fichesst.client.panel.BreadcrumbPanel;
import com.structis.fichesst.client.panel.FicheSTCenterPanel;
import com.structis.fichesst.client.panel.HeaderPanel;
import com.structis.fichesst.client.service.ClientFicheStServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.client.util.NameValuePair;
import com.structis.fichesst.client.util.ReportUtil;
import com.structis.fichesst.shared.dto.CautionFournieDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.LotTypeDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.SimpleDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public class FicheSTEcran extends AbstractEcran {
	
	private BreadcrumbPanel breadcrumbPanel;
	SimpleEventBus bus = new SimpleEventBus();
	private FicheSTCenterPanel centerPanel;
	private ChantierModel chantier;
	private Integer ficheStId = null;
	private HeaderPanel headerPanel;
	private RoleModel role;
	private UtilisateurGrpModel user;
	
	public FicheSTEcran(ChantierModel c, Integer fId, RoleModel roleModel, UtilisateurGrpModel utilisateurGrpModel) {
		super();
		this.chantier = c;
		this.ficheStId = fId;
		this.role = roleModel;
		this.user = utilisateurGrpModel;
		setScrolling();
		LayoutContainer mainContent = new LayoutContainer();
		mainContent.setLayout(new FitLayout());
		headerPanel = new HeaderPanel();
		headerPanel.setHeight(45);
		breadcrumbPanel = new BreadcrumbPanel(bus, chantier, role, user);
		setPadding(breadcrumbPanel);
		centerPanel = new FicheSTCenterPanel(bus, chantier, this.ficheStId, role, user);
		setPadding(centerPanel);
		mainContent.add(headerPanel);
		mainContent.add(breadcrumbPanel);
		mainContent.add(centerPanel);
		add(mainContent);
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
				DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(Constants.DATE_FORMAT);
				NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
				// bus.fireEvent(new DeductionGridUpdateEvent(centerPanel.getDeductionDtoList()));
				// Information Generals
				String chainter = navigation.getContext().getCurrentChantier().getNom();
				ComponentManager componentManager = ComponentManager.get();
				TextField<String> societte = (TextField<String>) componentManager.get("INFORMATION_PANEL_SOCIETE_ID");
				TextField<String> lot = (TextField<String>) componentManager.get("INFORMATION_PANEL_LOT_ID");
				ComboBox<LotTypeDto> lotType = (ComboBox<LotTypeDto>) componentManager.get("INFORMATION_PANEL_LOT_TYPE_ID");
				TextField<String> sitravaux = (TextField<String>) componentManager.get("INFORMATION_PANEL_SITRAVAUX_ID");
				NumberField montant = (NumberField) componentManager.get("INFORMATION_PANEL_MONTANT_ID");
				
				// Conditions Particilifers
				ComboBox<SimpleDto> simpleDto = (ComboBox<SimpleDto>) componentManager.get("INFORMATION_PANEL_PAYMENT_ID");
				NumberField rg = (NumberField) componentManager.get("INFORMATIONAL_PANEL_RG_ID");
				ComboBox<SimpleDto> decennalenecessaire = (ComboBox<SimpleDto>) componentManager.get("INFORMATIONAL_PANEL_DECENNALENECESSAIRE_ID");
				ComboBox<SimpleDto> demandedagrement = (ComboBox<SimpleDto>) componentManager.get("INFORMATIONAL_PANEL_DEMANDEDAGREMENT_ID");
				ComboBox<SimpleDto> dgdpresente = (ComboBox<SimpleDto>) componentManager.get("INFORMATIONAL_PANEL_DGDPRESENTE_ID");
				DateField dgdpresentedate = (DateField) componentManager.get("INFORMATIONAL_PANEL_DGDPRESENTEDATE_ID");
				// Presation
				NumberField pilotage = (NumberField) componentManager.get("INFORMATIONAL_PANEL_PILOTAGE_ID");
				NumberField assurances = (NumberField) componentManager.get("INFORMATIONAL_PANEL_ASSURANCES_ID");
				NumberField prorata = (NumberField) componentManager.get("INFORMATIONAL_PANEL_PRORATE_ID");
				NumberField canto = (NumberField) componentManager.get("INFORMATIONAL_PANEL_CANTO_ID");
				NumberField badge = (NumberField) componentManager.get("INFORMATIONAL_PANEL_BADGE_ID");
				NumberField grue = (NumberField) componentManager.get("INFORMATIONAL_PANEL_GRUE_ID");
				NumberField lift = (NumberField) componentManager.get("INFORMATIONAL_PANEL_LIFT_ID");
				NumberField benne = (NumberField) componentManager.get("INFORMATIONAL_PANEL_BENNE_ID");
				NumberField netoyage = (NumberField) componentManager.get("INFORMATIONAL_PANEL_NETOYAGE_ID");
				// Information Commemlaires
				LabelField conducteur = (LabelField) componentManager.get("INFORMATIONAL_PANEL_CONDUCTEUR_ID");
				DateField dateOfMarket = (DateField) componentManager.get("INFORMATIONAL_PANEL_DATEOFMARKET_ID");
				
				List<NameValuePair> values = new ArrayList<NameValuePair>();
				
				// Add General Information
				String lotype = lotType.getValue() != null ? lotType.getValue().getName() : "";
				String generaleInformation = chainter + Constants.SEPRATE + lot.getValue() + Constants.SEPRATE + sitravaux.getValue() + Constants.SEPRATE + societte.getValue() + Constants.SEPRATE
						+ lotype + Constants.SEPRATE + montant.getValue();
				values.add(new NameValuePair("generaleInformation", generaleInformation));
				
				// Add Conditions Particilifers
				String dgdpresentedate_ = dgdpresentedate.getValue() != null ? dateTimeFormat.format(dgdpresentedate.getValue()) : "null";
				String conditionsparticulieres = simpleDto.getValue().getLabel() + Constants.SEPRATE + rg.getValue() + Constants.SEPRATE + decennalenecessaire.getValue().getLabel()
						+ Constants.SEPRATE + demandedagrement.getValue().getLabel() + Constants.SEPRATE + dgdpresente.getValue().getLabel() + Constants.SEPRATE + dgdpresentedate_;
				
				values.add(new NameValuePair("conditionsparticulieres", conditionsparticulieres));
				// Add Prestations
				String prestations = pilotage.getValue() + Constants.SEPRATE + assurances.getValue() + Constants.SEPRATE + prorata.getValue() + Constants.SEPRATE + canto.getValue()
						+ Constants.SEPRATE + badge.getValue() + Constants.SEPRATE + grue.getValue() + Constants.SEPRATE + lift.getValue() + Constants.SEPRATE + benne.getValue() + Constants.SEPRATE
						+ netoyage.getValue();
				values.add(new NameValuePair("prestations", prestations));
				
				// Add Informationa Complementaires
				String date_of_market = "null";
				if (dateOfMarket.getValue() != null) {
					date_of_market = dateTimeFormat.format(dateOfMarket.getValue());
				}
				String informationscomplementaires = conducteur.getValue() + Constants.SEPRATE + date_of_market;
				values.add(new NameValuePair("informationscomplementaires", informationscomplementaires));
				
				List<CautionFournieDto> listCautionFournieDto = centerPanel.getCautionFournieDtoList();
				String cautionFournies = "";
				String caution_date = "";
				for (CautionFournieDto cautionFournieDto : listCautionFournieDto) {
					caution_date = cautionFournieDto.getDate() != null ? dateTimeFormat.format(cautionFournieDto.getDate()) : "";
					cautionFournies += caution_date + Constants.SEPRATE + numberFormat.format(cautionFournieDto.getAmount()) + Constants.SEPRATE;
				}
				values.add(new NameValuePair("cautionFournies", cautionFournies));
				
				// Add Gestion
				List<GestionDto> listGestion = centerPanel.getGestionDtoList();
				addGestionReport(listGestion, values);
				List<ProgressDto> listProcess = centerPanel.getProgressDtoList();
				addProcessReport(listProcess, values);
				
				addDeductionReport(centerPanel.getDeductionDtoList(), values);
				
				String exportPdfUrl = GWT.getHostPageBaseURL() + "all_fichest.pdf";
				ReportUtil.showReport(exportPdfUrl, values.toArray(new NameValuePair[values.size()]));
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void addDeductionReport(List<DeductionDto> lisDeduction, List<NameValuePair> values) {
		
		List<DeductionDto> listDeduction = lisDeduction;
		DeductionDto deductionDto = null;
		String deductions = "";
		double amount = 0.0;
		// Grid 1
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(Constants.DATE_FORMAT);
		NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
		for (int j = 0; j < listDeduction.size(); j++) {
			deductionDto = listDeduction.get(j);
			String deductionDate = deductionDto.getDate() != null ? dateTimeFormat.format(deductionDto.getDate()) : "";
			deductions += deductionDate + Constants.SEPRATE + numberFormat.format(deductionDto.getCanto()) + Constants.SEPRATE + numberFormat.format(deductionDto.getBadge()) + Constants.SEPRATE
					+ numberFormat.format(deductionDto.getGrue()) + Constants.SEPRATE + numberFormat.format(deductionDto.getLift()) + Constants.SEPRATE + numberFormat.format(deductionDto.getBenne())
					+ Constants.SEPRATE + numberFormat.format(deductionDto.getNettoyage()) + Constants.SEPRATE + numberFormat.format(deductionDto.getAutres()) + Constants.SEPRATE
					+ numberFormat.format(deductionDto.getProrata()) + Constants.SEPRATE + numberFormat.format(deductionDto.getRefacturations()) + Constants.SEPRATE;
		}
		if (deductions != null && deductions.length() > 0)
			deductions = deductions.substring(0, deductions.length() - 1);
		values.add(new NameValuePair(ConstantClient.DEDUCTIONDTO_ID_STR, deductions.toString()));
		
		TextField<String> txtTotalDeduction = (TextField<String>) ComponentManager.get().get("ACCOMPTES_PANEL_TOTALDEDUCTION_ID");
		String totaldeduction = "";
		if (txtTotalDeduction.getValue() != null && txtTotalDeduction.getValue().length() > 0)
			totaldeduction = txtTotalDeduction.getValue().substring(0, txtTotalDeduction.getValue().length() - 1);
		values.add(new NameValuePair("totaldeduction", totaldeduction));
		
		List<PenaltyDto> listPenalty = centerPanel.getPenaltyDtoList();
		PenaltyDto penaltyDto = null;
		String penaltys = "";
		// Grid 1
		for (int j = 0; j < listPenalty.size(); j++) {
			penaltyDto = listPenalty.get(j);
			/*
			 * penaltys += DateTimeFormat.getFormat(Constants.DATE_FORMAT).format(penaltyDto.getDate()) + Constants.SEPRATE + numberFormat.format(penaltyDto.getAmount()) + Constants.SEPRATE +
			 * penaltyDto.getComment() + Constants.SEPRATE;
			 */
			penaltys += append(penaltyDto.getDate(), penaltyDto.getAmount(), penaltyDto.getComment());
			amount += penaltyDto.getAmount();
		}
		if (penaltys != null && penaltys.length() > 0)
			penaltys = penaltys.substring(0, penaltys.length() - 1);
		values.add(new NameValuePair(ConstantClient.PENALTYDTO_ID_STR, penaltys));
		
		values.add(new NameValuePair("amount", amount + ""));
		TextArea txtAccomptesPanelComment = (TextArea) ComponentManager.get().get("ACCOMPTESPANEL_COMMENT");
		TextArea txtAccomptesPanelInternalComment = (TextArea) ComponentManager.get().get("ACCOMPTESPANEL_INTERNAL_COMMENT");
		values.add(new NameValuePair("deductionComment1", txtAccomptesPanelComment.getValue() == null ? "" : txtAccomptesPanelComment.getValue()));
		values.add(new NameValuePair("deductionComment2", txtAccomptesPanelInternalComment.getValue() == null ? "" : txtAccomptesPanelInternalComment.getValue()));
	}
	
	private void addGestionReport(List<GestionDto> listGestion, List<NameValuePair> values) {
		List<GestionDto> list = listGestion;
		Map<String, String> listgestion = new HashMap<String, String>();
		Map<String, String> listtotalgestion = new HashMap<String, String>();
		List<String> lstKeys = new ArrayList<String>();
		String keys = "";
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMarche() != null && !lstKeys.contains(list.get(i).getMarche().getLabel())) {
				lstKeys.add(list.get(i).getMarche().getLabel());
				keys += list.get(i).getMarche().getLabel() + Constants.SEPRATE;
			}
		}
		GestionDto gestionDto = null;
		String alltotalgestion = "";
		
		double allamount = 0.0;
		double allavenants = 0.0;
		double allarrete = 0.0;
		double allnonarrete = 0.0;
		double allprovision = 0.0;
		double alldevisrefuse = 0.0;
		double allreelactivitive = 0.0;
		double allamount2 = 0.0;
		double alltotalecart = 0.0;
		double alltotalfdc = 0.0;
		
		String summary = "";
		String budget = "";
		for (int i = 0; i < lstKeys.size(); i++) {
			String gestion = "";
			String totalgestion = "";
			double amount = 0.0;
			double avenants = 0.0;
			double arrete = 0.0;
			double nonarrete = 0.0;
			double provision = 0.0;
			double devisrefuse = 0.0;
			double reelactivitive = 0.0;
			double amount2 = 0.0;
			String gestionComment = "";
			double totalfdcSum = 0.0;
			double ecartSum = 0.0;
			
			for (int j = 0; j < list.size(); j++) {
				gestionDto = list.get(j);
				if (lstKeys.get(i).equalsIgnoreCase(gestionDto.getMarche().getLabel())) {
					String tmp = gestionDto.getMarche().getLabel() + "                                          " + gestionDto.getTraite();
					gestionComment = gestionDto.getComment() != null ? gestionDto.getComment() : "";
					/*
					 * gestion += gestionDto.getDevis() + Constants.SEPRATE + gestionDto.getStatut().getLabel() + Constants.SEPRATE + gestionDto.getLabel() + Constants.SEPRATE + gestionComment +
					 * Constants.SEPRATE + gestionDto.getAmount() + Constants.SEPRATE + tmp + Constants.SEPRATE + gestionDto.getArrete() + Constants.SEPRATE + gestionDto.getNonArrete() +
					 * Constants.SEPRATE + gestionDto.getProvision() + Constants.SEPRATE + gestionDto.getDevisRefuse() + Constants.SEPRATE + calculateTotalFdc(gestionDto) + Constants.SEPRATE +
					 * gestionDto.getReelActivitive() + Constants.SEPRATE + gestionDto.getType().getLabel() + Constants.SEPRATE + gestionDto.getLabel2() + Constants.SEPRATE + gestionDto.getAmount2() +
					 * Constants.SEPRATE + calculateEcart(gestionDto) + Constants.SEPRATE;
					 */
					gestion += append(gestionDto.getDevis(), gestionDto.getStatut().getLabel(), gestionDto.getLabel(), gestionComment, gestionDto.getAmount(), tmp, gestionDto.getArrete(),
							gestionDto.getNonArrete(), gestionDto.getProvision(), gestionDto.getDevisRefuse(), calculateTotalFdc(gestionDto), gestionDto.getReelActivitive(), gestionDto.getType()
									.getLabel(), gestionDto.getLabel2(), gestionDto.getAmount2(), calculateEcart(gestionDto));
					// Sum Group
					amount += gestionDto.getAmount();
					avenants += gestionDto.getTraite();
					arrete += gestionDto.getArrete();
					nonarrete += gestionDto.getNonArrete();
					provision += gestionDto.getProvision();
					devisrefuse += gestionDto.getDevisRefuse();
					reelactivitive += gestionDto.getReelActivitive();
					amount2 += gestionDto.getAmount2();
					totalfdcSum += calculateTotalFdc(gestionDto);
					ecartSum += calculateEcart(gestionDto);
				}
			}
			allamount += amount;
			allavenants += avenants;
			allarrete += arrete;
			allnonarrete += nonarrete;
			allprovision += provision;
			alldevisrefuse += devisrefuse;
			allreelactivitive += reelactivitive;
			allamount2 += amount2;
			alltotalecart += ecartSum;
			alltotalfdc += totalfdcSum;
			
			if (gestion != null && gestion.length() > 0) {
				// gestion = gestion.substring(0,gestion.length() - Constants.SEPRATE.length());
				listgestion.put(lstKeys.get(i), gestion);
			}
			/*
			 * totalgestion += numberFormat.format(amount) + Constants.SEPRATE + numberFormat.format(avenants) + Constants.SEPRATE + numberFormat.format(arrete) + Constants.SEPRATE +
			 * numberFormat.format(nonarrete) + Constants.SEPRATE + numberFormat.format(provision) + Constants.SEPRATE + numberFormat.format(devisrefuse) + Constants.SEPRATE +
			 * numberFormat.format(totalfdcSum) + Constants.SEPRATE + numberFormat.format(reelactivitive) + Constants.SEPRATE + numberFormat.format(amount2) + Constants.SEPRATE +
			 * numberFormat.format(ecartSum) + Constants.SEPRATE;
			 */
			totalgestion += append(amount, avenants, arrete, nonarrete, provision, devisrefuse, totalfdcSum, reelactivitive, amount2, ecartSum);
			listtotalgestion.put(lstKeys.get(i), totalgestion);
		}
		
		if (keys.length() > 0) {
			keys = keys.substring(0, keys.length() - Constants.SEPRATE.length());
		}
		
		/*
		 * alltotalgestion += numberFormat.format(allamount) + Constants.SEPRATE + numberFormat.format(allavenants) + Constants.SEPRATE + numberFormat.format(allarrete) + Constants.SEPRATE +
		 * numberFormat.format(allnonarrete) + Constants.SEPRATE + numberFormat.format(allprovision) + Constants.SEPRATE + numberFormat.format(alldevisrefuse) + Constants.SEPRATE +
		 * numberFormat.format(alltotalfdc) + Constants.SEPRATE + numberFormat.format(allreelactivitive) + Constants.SEPRATE + numberFormat.format(allamount2) + Constants.SEPRATE +
		 * numberFormat.format(alltotalecart) + Constants.SEPRATE;
		 */
		alltotalgestion += append(allamount, allavenants, allarrete, allnonarrete, allprovision, alldevisrefuse, alltotalfdc, allreelactivitive, allamount2, alltotalecart);
		values.add(new NameValuePair(ConstantClient.GESTIONDTO_ID_STR, listgestion.toString()));
		values.add(new NameValuePair("totalgestion", listtotalgestion.toString()));
		values.add(new NameValuePair("alltotalgestion", alltotalgestion));
		double totalObj = 0.0;
		double totalTF = 0.0;
		double totalTS = 0.0;
		double totalRD = 0.0;
		for (GestionDto gestion : listGestion) {
			Integer typeId = gestion.getType().getId();
			double value = gestion.getAmount2();
			switch (typeId) {
				case 1:
					totalObj += value;
					break;
				case 2:
					totalTF += value;
					break;
				case 3:
					totalTS += value;
					break;
				case 4:
					totalRD += value;
					break;
				default:
					break;
			}
		}
		
		/* summary += totalObj + Constants.SEPRATE + totalTF + Constants.SEPRATE + totalTS + Constants.SEPRATE + totalRD; */
		summary += append(totalObj, totalTF, totalTS, totalRD);
		values.add(new NameValuePair("summary", summary));
		
		NumberField budgetInitial = (NumberField) ComponentManager.get().get("GESTIONAL_PANEL_BUDGET_ID");
		NumberField dernierPoint = (NumberField) ComponentManager.get().get("GESTIONAL_PANEL_ECARDDERNIER_ID");
		DateField dernier = (DateField) ComponentManager.get().get("GESTION_PANEL_DATEDERNIER_ID");
		/* budget += budgetInitial.getValue() + Constants.SEPRATE + dernierPoint.getValue() + Constants.SEPRATE + dernier.getValue(); */
		budget += append(budgetInitial.getValue(), dernierPoint.getValue(), dernier.getValue());
		values.add(new NameValuePair("budget", budget));
		values.add(new NameValuePair("key_gestion", keys));
	}
	
	@SuppressWarnings("unchecked")
	private void addProcessReport(List<ProgressDto> listProcess, List<NameValuePair> values) {
		
		List<ProgressDto> list = listProcess;
		ProgressDto progressDto = null;
		String process = "";
		String totalsituation = "";
		// Grid 1
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat(Constants.DATE_FORMAT);
		NumberFormat numberFormat = NumberFormat.getFormat(Constants.NUMBER_FORMAT);
		String process_date = "";
		
		for (int j = 0; j < list.size(); j++) {
			progressDto = list.get(j);
			process_date = progressDto.getDate() != null ? dateTimeFormat.format(progressDto.getDate()) : "";
			process += (j + 1) + Constants.SEPRATE + progressDto.getLabel() + Constants.SEPRATE + process_date + Constants.SEPRATE + numberFormat.format(progressDto.getCumule()) + Constants.SEPRATE
					+ numberFormat.format(progressDto.getMois()) + Constants.SEPRATE + numberFormat.format(progressDto.getCumule2()) + Constants.SEPRATE + numberFormat.format(progressDto.getMois2())
					+ Constants.SEPRATE;
		}
		if (process != null && process.length() > 0) {
			process = process.substring(0, process.length() - Constants.SEPRATE.length());
			values.add(new NameValuePair(ConstantClient.PROCESSDTO_ID_STR, process));
		}
		
		TextField<String> txttotalsituation = (TextField<String>) ComponentManager.get().get("AVANCEMENTS_PANEL_TXTTOTALSITUATION_ID");
		totalsituation = txttotalsituation.getValue();
		values.add(new NameValuePair("totalsituation", totalsituation));
		
		// Grid 2
		TextField<String> txtdetail_des_retenues_appliques = (TextField<String>) ComponentManager.get().get("AVANCEMENTS_PANEL_TXTDETAIL_DES_RETENUES_APPLIQUES_ID");
		String detail_des_retenues_appliques = txtdetail_des_retenues_appliques.getValue();
		values.add(new NameValuePair("detail_des_retenues_appliques", detail_des_retenues_appliques));
		
		// Grid 3
		TextField<String> txtgrid_3_report = (TextField<String>) ComponentManager.get().get("AVANCEMENTS_PANEL_TXTGRID3REPORT_ID");
		String grid_3_report = txtgrid_3_report.getValue();
		values.add(new NameValuePair("grid_3_report", grid_3_report));
		
		TextField<String> txtEtatAvancement = (TextField<String>) ComponentManager.get().get("AVANCEMENTS_PANEL_TXT_ETATAVANCEMENT_ID");
		values.add(new NameValuePair("etatAvancement", txtEtatAvancement.getValue()));
		
		TextArea txtAvancementComment = (TextArea) ComponentManager.get().get("AVANCEMENTSPANEL_COMMENT");
		values.add(new NameValuePair("commentaire", txtAvancementComment.getValue() == null ? "" : txtAvancementComment.getValue()));
	}
	
	private double calculateEcart(GestionDto item) {
		if (item == null) {
			return 0.0;
		}
		Double amount2 = item.getAmount2();
		Double totalFdc = calculateTotalFdc(item);
		return amount2 - totalFdc;
	}
	
	private double calculateTotalFdc(GestionDto item) {
		Double avenant = item.getTraite();
		Double arrete = item.getArrete();
		Double nonArrete = item.getNonArrete();
		Double provision = item.getProvision();
		double calculatedValue = avenant + arrete + nonArrete + provision;
		return calculatedValue;
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
			if (deductionDto.getDate() != null || deductionDto.getCanto() != 0 || deductionDto.getBadge() != 0 || deductionDto.getGrue() != 0 || deductionDto.getLift() != 0
					|| deductionDto.getBenne() != 0 || deductionDto.getNettoyage() != 0 || deductionDto.getAutres() != 0 || deductionDto.getProrata() != 0 || deductionDto.getRefacturations() != 0) {
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
			if (gestionDto.getDevis() != null || gestionDto.getStatut() != null || gestionDto.getLabel() != null || gestionDto.getComment() != null || gestionDto.getTraite() != 0
					|| gestionDto.getMarche() != null || gestionDto.getTraite() != 0 || gestionDto.getArrete() != 0 || gestionDto.getNonArrete() != 0 || gestionDto.getProvision() != 0
					|| gestionDto.getDevisRefuse() != 0 || gestionDto.getReelActivitive() != 0 || gestionDto.getType() != null || gestionDto.getLabel2() != null || gestionDto.getTraite() != 0) {
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
	
	@Override
	protected void onLoad() {
		String societe_ = navigation.getContext().getMapSociete().get(ficheStId);
		if (societe_ == null)
			societe_ = "";
		breadcrumbPanel.setSociete(societe_);
	}
}
