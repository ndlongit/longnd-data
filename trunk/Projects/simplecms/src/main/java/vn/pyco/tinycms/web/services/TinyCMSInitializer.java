// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.internal.util.Orderer;
import org.apache.tapestry5.services.ApplicationInitializer;
import org.apache.tapestry5.services.ApplicationInitializerFilter;
import org.apache.tapestry5.services.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import vn.pyco.tinycms.services.BootManager;
import vn.pyco.tinycms.services.DataInitializer;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
public class TinyCMSInitializer implements ApplicationInitializerFilter {
    private static final Logger logger = LoggerFactory.getLogger(TinyCMSInitializer.class);
    private ApplicationContext _appContext;
    
    public TinyCMSInitializer(ApplicationContext context) {
        _appContext = context;
    }

    /*
     * @see org.apache.tapestry5.services.ApplicationInitializerFilter#initializeApplication(org.apache.tapestry5.services.Context, org.apache.tapestry5.services.ApplicationInitializer)
     */
    @Override
    public void initializeApplication(Context context, ApplicationInitializer initializer) {
        // Init boot from boot file path
        BootManager bootManager = BootManager.getInstance();
        bootManager.setBootFilePath(context.getRealFile("/WEB-INF/boot.properties").getAbsolutePath());
        
        //handle DataInitializer
        if (bootManager.isSetup()) {
            List<DataInitializer> dataInitializers = new ArrayList<DataInitializer>();
            String[] beanNames = _appContext.getBeanNamesForType(DataInitializer.class);
            for (String beanName : beanNames) {
                dataInitializers.add((DataInitializer) _appContext.getBean(beanName));
            }
            
            dataInitializers = sortInitializers(dataInitializers);
            for (DataInitializer dataInitializer : dataInitializers) {
                try {
                    dataInitializer.initialize();
                } catch (Exception e) {
                    logger.error("An error ocurred when initilizing entities.", e);
                }
            }
            
            bootManager.setSetup(false);
            bootManager.save();
        }
        
        initializer.initializeApplication(context);
    }

    private List<DataInitializer> sortInitializers(List<DataInitializer> dataInitializers) {
        Orderer<DataInitializer> orderedList = new Orderer<DataInitializer>(logger);
        for (DataInitializer initializer : dataInitializers) {
            if (initializer.getOrderConstrains() != null) {
                orderedList.add(initializer.getInitId(), initializer, initializer.getOrderConstrains());
            } else {
                orderedList.add(initializer.getInitId(), initializer);
            }
        }
        
        return orderedList.getOrdered();
    }
}
