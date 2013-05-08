package com.structis.fichesst.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.EtatAvancementHandler;
import com.structis.fichesst.shared.dto.GestionDto;
import com.structis.fichesst.shared.dto.ProgressDto;

public class EtatAvancementEvent extends GwtEvent<EtatAvancementHandler> {
	
	public static final Type<EtatAvancementHandler> TYPE = new Type<EtatAvancementHandler>();
	
	private double etatAvancement;
	private List<GestionDto> listGestion;
	private List<ProgressDto> listProcess;
	
	public EtatAvancementEvent(List<GestionDto> listGestion, List<ProgressDto> listProcess) {
		this.setListGestion(listGestion);
		this.setListProcess(listProcess);
	}
	
	@Override
	public GwtEvent.Type<EtatAvancementHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EtatAvancementHandler handler) {
		handler.onGetEtatAvancement(this);
		
	}

	public void setEtatAvancement(double etatAvancement) {
		this.etatAvancement = etatAvancement;
	}

	public double getEtatAvancement() {
		return 100;//etatAvancement;
	}

	public void setListGestion(List<GestionDto> listGestion) {
		this.listGestion = listGestion;
	}

	public List<GestionDto> getListGestion() {
		return listGestion;
	}

	public void setListProcess(List<ProgressDto> listProcess) {
		this.listProcess = listProcess;
	}

	public List<ProgressDto> getListProcess() {
		return listProcess;
	}
	
}
