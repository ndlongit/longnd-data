package com.structis.vip.server.dao;

import java.util.Date;
import java.util.List;

import com.structis.vip.server.bean.domain.Control;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public interface ControlDao extends GenericDao<Control, Integer> {
	public Control insert(Control nature);

	public Control update(Control nature);

	public List<Control> getControlsByEntite(String enId, String perimetreId,
			List<Integer> keyList, Date startDate, Date endDate,
			String codeProjet, List<String> caracteres, String controllerName, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles);

	public List<Control> getControls(String string, String string2,
			List<Integer> object, Date parse, Date now, String codeProjet, List<String> caracteres, String controllerName);
	
	public List<Integer> getControlsHasExternName(String controllerName);
	public List<Control> getControlsByPerimetre(String perId);
}
