import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import vn.pyco.tinycms.services.impl.PropertyTypeServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class,
                                TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = { "applicationContext-dataSourceCommon.xml" })
public abstract class AbstractUnitTests {

    // dependency injected by Spring
    @Autowired
     protected PropertyTypeServiceImpl propertyTypeService;

    @Test
    public void testFindOwners() {
        propertyTypeService.findAll();
    }

    // more tests...
}
