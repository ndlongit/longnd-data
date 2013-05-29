package test;

import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.User;
import org.java.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/application-context.xml" })
public class TestCase01 {

    @Autowired
    private UserService userService;

    @Test
    public void test() throws DataConstraintException, Exception {
        User entity = new User();
        userService.save(entity);
    }
}
