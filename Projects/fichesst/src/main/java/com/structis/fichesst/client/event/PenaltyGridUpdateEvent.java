package com.structis.fichesst.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.PenaltyGridUpdateHandler;
import com.structis.fichesst.shared.dto.PenaltyDto;

public class PenaltyGridUpdateEvent extends GwtEvent<PenaltyGridUpdateHandler> {

	public static final Type<PenaltyGridUpdateHandler> TYPE = new Type<PenaltyGridUpdateHandler>();

	private List<PenaltyDto> penaltyDtoList;

	public PenaltyGridUpdateEvent(List<PenaltyDto> l) {
		this.penaltyDtoList = l;
	}

	public List<PenaltyDto> getPenaltyDtoList() {
		return penaltyDtoList;
	}

	public void setPenaltyDtoList(List<PenaltyDto> penaltyDtoList) {
		this.penaltyDtoList = penaltyDtoList;
	}

	@Override
	public GwtEvent.Type<PenaltyGridUpdateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PenaltyGridUpdateHandler handler) {
		handler.onSave(this);
	}
}
