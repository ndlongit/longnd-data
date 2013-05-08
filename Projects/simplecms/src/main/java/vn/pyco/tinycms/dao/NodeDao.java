// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao;

import java.util.List;

import vn.pyco.commons.dao.GenericDao;
import vn.pyco.tinycms.model.Node;
import vn.pyco.tinycms.model.Page;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface NodeDao extends GenericDao<Node, Integer> {
    String SERVICE_ID = "nodeDao";

    Page getDefaultPage(String parentPath);
    
    Page getFirstPage(String parentPath);
    
    <T extends Node> T getByPath(String path, Class<T> type);
    
    <T extends Node> T getByAlias(String parentPath, String alias, Class<T> type);
    
    <T extends Node> List<T> getAllChilds(String parentPath);
}
