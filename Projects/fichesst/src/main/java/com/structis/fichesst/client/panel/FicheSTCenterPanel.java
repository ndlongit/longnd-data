package com.structis.fichesst.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.structis.fichesst.client.event.DeductionGridUpdateEvent;
import com.structis.fichesst.client.event.GestionGridUpdateEvent;
import com.structis.fichesst.client.event.PenaltyGridUpdateEvent;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.handler.DeductionGridUpdateHandler;
import com.structis.fichesst.client.handler.GestionGridUpdateHandler;
import com.structis.fichesst.client.handler.PenaltyGridUpdateHandler;
import com.structis.fichesst.client.service.ClientFicheStServiceAsync;
import com.structis.fichesst.shared.dto.CautionFournieDto;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.DeductionDto;
import com.structis.fichesst.shared.dto.FicheStDto;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.PenaltyDto;
import com.structis.fichesst.shared.dto.ProgressDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class FicheSTCenterPanel extends AbstractPanel {

	private ChantierModel chantier;
	
	private Integer ficheStId = null;

	private InformationPanel informationPanel = null;

	private GestionPanel gestionPanel = null;

	private AvancementsPanel avancementsPanel = null;

	private AccomptesPanel accomptesPanel = null;
	
	private RoleModel role;
	private UtilisateurGrpModel user;

	public FicheSTCenterPanel(SimpleEventBus b, ChantierModel c, Integer fId,RoleModel roleModel,UtilisateurGrpModel utilisateurGrpModel) {
		super();
		this.bus = b;
		this.chantier = c;
		this.ficheStId = fId;
		this.role = roleModel;
		this.user = utilisateurGrpModel;
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		FicheStDto model = new FicheStDto();
		model.initData();
		model.getLot().setChantier(this.chantier);
		setModel(model);
		TableLayout layout = new TableLayout();
		layout.setWidth("100%");
		setLayout(layout);
		informationPanel = new InformationPanel(bus,ficheStId,role,user);		
		informationPanel.setModel(model);
		informationPanel.setId("INFORMATIONPANEL_ID");
		add(informationPanel);
		gestionPanel = new GestionPanel(bus,role,user);
		add(gestionPanel);
		accomptesPanel = new AccomptesPanel(bus,ficheStId,role,user);
		accomptesPanel.setModel(model);
		add(accomptesPanel);
		avancementsPanel = new AvancementsPanel(bus,role,user);
		avancementsPanel.setModel(model);
		add(avancementsPanel);
		if( ficheStId != null && ficheStId > 0 ) {
			ClientFicheStServiceAsync clientFicheStService = ClientFicheStServiceAsync.Util.getInstance();
			clientFicheStService.find(ficheStId, new AsyncCallbackWithErrorResolution<FicheStDto>() {
				@Override
				public void onSuccess(final FicheStDto result) {
					if(result == null) {
						return;
					}
					
					FicheSTCenterPanel.this.setModel(result);
					informationPanel.setModel(result);
					informationPanel.setCautionFournieDtoList(result.getCautionFournies());
					
					gestionPanel.setGestionDtoList(result.getGestions());
					avancementsPanel.setModel(result);
					accomptesPanel.setModel(result);
					
					avancementsPanel.setProgressLabelText(result.getAvctBAPercentage());
				}

				@Override
				public void onFailure(Throwable caught) {
					super.onFailure(caught);
				}
			});
		}
		LayoutContainer printButtonsPanel = new PrintFicheStButtonsPanel(bus,chantier,role,user);
		add(printButtonsPanel);
		bus.addHandler(GestionGridUpdateEvent.TYPE, new GestionGridUpdateHandler() {
			@Override
			public void onSave(GestionGridUpdateEvent event) {
				List<GestionDto> gestionDtoList = event.getGestionDtoList();
				List<DeductionDto> deductionDtoList = accomptesPanel.getDeductionDtoList();
				List<PenaltyDto> penaltyDtoList = accomptesPanel.getPenaltyDtoList();
				updateAvancementPanel(gestionDtoList, deductionDtoList, penaltyDtoList);
			}
		});
		bus.addHandler(DeductionGridUpdateEvent.TYPE, new DeductionGridUpdateHandler() {
			@Override
			public void onSave(DeductionGridUpdateEvent event) {
				List<GestionDto> gestionDtoList = gestionPanel.getGestionDtoList();
				List<DeductionDto> deductionDtoList = event.getDeductionDtoList();
				List<PenaltyDto> penaltyDtoList = accomptesPanel.getPenaltyDtoList();
				updateAvancementPanel(gestionDtoList, deductionDtoList, penaltyDtoList);
			}
		});
		bus.addHandler(PenaltyGridUpdateEvent.TYPE, new PenaltyGridUpdateHandler() {
			@Override
			public void onSave(PenaltyGridUpdateEvent event) {
				List<DeductionDto> deductionDtoList = accomptesPanel.getDeductionDtoList();
				List<GestionDto> gestionDtoList = gestionPanel.getGestionDtoList();
				List<PenaltyDto> penaltyDtoList = event.getPenaltyDtoList();
				updateAvancementPanel(gestionDtoList, deductionDtoList, penaltyDtoList);
			}
		});
	}
	protected void updateAvancementPanel(List<GestionDto> gestionDtoList, List<DeductionDto> deductionDtoList,
			List<PenaltyDto> penaltyDtoList) {
		avancementsPanel.updateDataGrid(gestionDtoList, deductionDtoList, penaltyDtoList);
	}
	public List<CautionFournieDto> getCautionFournieDtoList() {
		return informationPanel.getCautionFournieDtoList();
	}
	public List<GestionDto> getGestionDtoList() {
		return gestionPanel.getGestionDtoList();
	}
	public Integer getFicheStId() {
		return ficheStId;
	}
	public void setFicheStId(Integer ficheStId) {
		this.ficheStId = ficheStId;
	}

	public List<DeductionDto> getDeductionDtoList() {
		return accomptesPanel.getDeductionDtoList();
	}

	public List<PenaltyDto> getPenaltyDtoList() {
		return accomptesPanel.getPenaltyDtoList();
	}
	public List<ProgressDto> getProgressDtoList() {
		return avancementsPanel.getProgressDtoList();
	}
	public boolean isValid() {
		return(informationPanel != null && informationPanel.isValid() && gestionPanel != null && gestionPanel.isValid());
	}
	@Override
	public void commitDataChange() {
		informationPanel.commitDataChange();
		gestionPanel.commitDataChange();
		avancementsPanel.commitDataChange();
		accomptesPanel.commitDataChange();
	}
}
