// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.init;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.AuthorityDao;
import vn.pyco.tinycms.dao.UserDao;
import vn.pyco.tinycms.model.Authority;
import vn.pyco.tinycms.model.User;
import vn.pyco.tinycms.services.SecurityService;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class UserDataInitializer extends AbstractDataInitializer {
    @Autowired
    private UserDao _userDao;
    
    @Autowired
    private AuthorityDao _authorityDao;
    
    @Autowired
    private SecurityService _securityService;
    
    private Set<Authority> _initAuthorities;
    private Set<User> _initUsers;
    
    /**
     * @param initAuthorities the initAuthorities to set
     */
    @Required
    public void setInitAuthorities(Set<Authority> initAuthorities) {
        _initAuthorities = initAuthorities;
    }
    
    /**
     * @param initUsers the initUsers to set
     */
    @Required
    public void setInitUsers(Set<User> initUsers) {
        _initUsers = initUsers;
    }
    
    /*
     * @see vn.pyco.tinycms.services.DataInitializer#initialize()
     */
    @Override
    @Transactional(readOnly=false)
    public void initialize() throws Exception {
        // create default authorities
        for (Authority authority : _initAuthorities) {
            _authorityDao.save(authority);
        }
        
        // create default users
        for (User user : _initUsers) {
            _securityService.encodePassword(user);
            _userDao.save(user);
        }
    }
}
