package com.structis.fichesst.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.DeductionGridUpdateHandler;
import com.structis.fichesst.shared.dto.DeductionDto;

public class DeductionGridUpdateEvent extends GwtEvent<DeductionGridUpdateHandler> {

	public static final Type<DeductionGridUpdateHandler> TYPE = new Type<DeductionGridUpdateHandler>();

	private List<DeductionDto> deductionDtoList;

	public DeductionGridUpdateEvent(List<DeductionDto> l) {
		this.deductionDtoList = l;
	}

	public List<DeductionDto> getDeductionDtoList() {
		return deductionDtoList;
	}

	public void setDeductionDtoList(List<DeductionDto> deductionDtoList) {
		this.deductionDtoList = deductionDtoList;
	}

	@Override
	public GwtEvent.Type<DeductionGridUpdateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DeductionGridUpdateHandler handler) {
		handler.onSave(this);
	}
}
