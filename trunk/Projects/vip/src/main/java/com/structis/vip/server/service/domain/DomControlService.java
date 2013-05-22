package com.structis.vip.server.service.domain;

import java.util.Date;
import java.util.List;

import com.structis.vip.server.bean.domain.Control;
import com.structis.vip.server.service.domain.core.GenericEntityService;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public interface DomControlService extends GenericEntityService<Control, Integer> {

    Control insert(Control doc);

    Control update(Control doc);

    List<Control> findAll();

    List<Control> getControlsByEntite(String enId, String perimetreId, List<Integer> keyList, Date startDate, Date endDate, String codeProjet,
            List<String> caracteres, String controllerName, PerimetreTreeModel perimetreTreeModel, List<UserRoleModel> userRoles);

    List<Control> findByPerimetre(String perId);
}
