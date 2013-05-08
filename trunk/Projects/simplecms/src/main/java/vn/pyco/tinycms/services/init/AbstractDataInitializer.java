// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.init;

import vn.pyco.tinycms.services.DataInitializer;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public abstract class AbstractDataInitializer implements DataInitializer {
    private String _initId;
    private String[] _orderConstraints;
    
    /*
     * @see vn.pyco.tinycms.services.DataInitializer#getInitId()
     */
    @Override
    public String getInitId() {
        return _initId;
    }
    
    /**
     * @param initId the initId to set
     */
    public void setInitId(String initId) {
        _initId = initId;
    }
    
    /*
     * @see vn.pyco.tinycms.services.DataInitializer#getOrderConstrains()
     */
    @Override
    public String[] getOrderConstrains() {
        return _orderConstraints;
    }
    
    /**
     * @param orderConstraints the orderConstraints to set
     */
    public void setOrderConstraints(String[] orderConstraints) {
        _orderConstraints = orderConstraints;
    }
}
