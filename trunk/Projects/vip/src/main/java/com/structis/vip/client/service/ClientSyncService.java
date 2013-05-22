package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.model.AddressModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.SynETDEModel;

@RemoteServiceRelativePath("springGwtServices/clientSyncService")
public interface ClientSyncService extends RemoteService {

    List<CollaborateurModel> syncRUBIS(String entityId);

    AddressModel getAddress(String idbycn);

    List<SynETDEModel> getRubsiCodesNameEtde();

    List<CollaborateurModel> syncRUBISWithItems(String entId, List<SynETDEModel> models);

    List<SynETDEModel> getRubsiCodesName(String entId, String entName);
}
