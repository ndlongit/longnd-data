// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.ContentDao;
import vn.pyco.tinycms.model.Content;
import vn.pyco.tinycms.model.ContentData;
import vn.pyco.tinycms.services.ContentService;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Service(ContentService.SERVICE_ID)
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentDao _contentDao;
    
    /*
     * @see vn.pyco.tinycms.services.ContentService#buildContentDataMap(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> buildContentDataMap(int contentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Content content = _contentDao.get(contentId);
        if (content == null) {
            return Collections.EMPTY_MAP;
        }
        
        map.put("_info", content);
        List<ContentData> data = content.getData();
        for (ContentData aData : data) {
            map.put(aData.getCode(), aData.getData());
        }
        
        return map;
    }

    @Override
    @Transactional
    public void saveContent(Content content) {
        _contentDao.save(content);
    }

    @Override
    @Transactional
    public Content getObjectByCriteria(String name, String value) {
        String[] names = new String[1];
        names[0] = name;

        String[] values = new String[1];
        values[0] = value;
        return this.getObjectByCriteria(names, values);
    }

    @Override
    @Transactional
    public Content getObjectByCriteria(String[] names, String[] values) {
        return _contentDao.getObjectByCriteria(names, values);
    }
}
