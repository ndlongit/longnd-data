package com.structis.vip.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.PerimetreException;
import com.structis.vip.shared.exception.SynchronizationException;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.PerimetreTypeModel;
import com.structis.vip.shared.model.UserRoleModel;

@RemoteServiceRelativePath("springGwtServices/clientPerimetreService")
public interface ClientPerimetreService extends RemoteService {
	List<PerimetreModel> findPerimetreByEntite(String entiteId);

	List<PerimetreModel> findFirstLevelPerimetreByEntite(String emId);
	
	List<PerimetreModel> findFirstLevelPerimetreByUserRoles(String emId, boolean isAdmin, List<UserRoleModel> userRoles);

	List<PerimetreModel> getTreeModel(String entiteId, String perimetreId);

	Boolean insert(PerimetreModel perimetreModel);

	String insert(String perimetreParentId, PerimetreModel perimetreModel);

	Boolean update(PerimetreModel perimetreModel);

	PerimetreModel findById(String id);

	Boolean deleteById(String id) throws PerimetreException;

	List<PerimetreModel> findFirstLevelPerimetre();

	Map<String, List<PerimetreModel>> sync(String entiteId, String parentId, List<PerimetreTypeModel> types)
			throws SynchronizationException;

	List<PerimetreTreeModel> getTreeModelByParent(String entiteId, List<UserRoleModel> userRoles, PerimetreTreeModel perimetreTree);
	
	List<PerimetreTreeModel> getTreeModelById(String perimetreId, List<UserRoleModel> userRoles);
	Integer hasReferenceInDelegationOrControlOrPerimetre(String perId);
	Integer hasReferenceInUserCollaborateur(String perId);
	void clearReferenceToPerimetreInUserCollaborateur(String perId);
	List<PerimetreModel> findByPerimetreParent(String perId);
}
