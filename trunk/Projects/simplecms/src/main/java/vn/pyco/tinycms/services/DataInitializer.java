// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public interface DataInitializer {
    String getInitId();
    
    String[] getOrderConstrains();
    
    void initialize() throws Exception;
}
