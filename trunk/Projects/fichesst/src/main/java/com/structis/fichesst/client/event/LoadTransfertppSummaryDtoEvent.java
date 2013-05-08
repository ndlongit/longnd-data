package com.structis.fichesst.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.structis.fichesst.client.handler.LoadTransfertppSummaryDtoHandler;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.TransfertPpSummaryDto;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class LoadTransfertppSummaryDtoEvent extends GwtEvent<LoadTransfertppSummaryDtoHandler> {
	public static final Type<LoadTransfertppSummaryDtoHandler> TYPE=new Type<LoadTransfertppSummaryDtoHandler>();
	private TransfertPpSummaryDto transfertPpSummaryDto;
	private ChantierModel chantierModel;
	private RoleModel roleModel;
	private UtilisateurGrpModel userModel;
	public LoadTransfertppSummaryDtoEvent(TransfertPpSummaryDto transfertPpSummaryDto, ChantierModel chantierModel,RoleModel roleModel,UtilisateurGrpModel userModel){
		this.transfertPpSummaryDto=transfertPpSummaryDto;
		this.chantierModel=chantierModel;
		this.roleModel=roleModel;
		this.userModel=userModel;
	}

	public ChantierModel getChantierModel() {
		return chantierModel;
	}

	public void setChantierModel(ChantierModel chantierModel) {
		this.chantierModel = chantierModel;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	public UtilisateurGrpModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UtilisateurGrpModel userModel) {
		this.userModel = userModel;
	}

	@Override
	protected void dispatch(LoadTransfertppSummaryDtoHandler arg0) {
		arg0.onLoad(this);
	}

	public TransfertPpSummaryDto getTransfertPpSummaryDto() {
		return transfertPpSummaryDto;
	}
	public void setTransfertPpSummaryDto(TransfertPpSummaryDto transfertPpSummaryDto) {
		this.transfertPpSummaryDto = transfertPpSummaryDto;
	}
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoadTransfertppSummaryDtoHandler> getAssociatedType() {
		return TYPE;
	}

}
