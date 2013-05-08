// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services;

import vn.pyco.tinycms.model.Page;
import vn.pyco.tinycms.model.Site;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface PageRenderService {
    String renderPage(Site site, Page page);
}
