// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.AuthorityDao;
import vn.pyco.tinycms.dao.UserDao;
import vn.pyco.tinycms.model.Authority;
import vn.pyco.tinycms.model.User;
import vn.pyco.tinycms.services.SecurityService;
import vn.pyco.tinycms.services.UserService;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Service(UserService.SERVICE_ID)
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao _userDao;
    
    @Autowired
    private AuthorityDao _authorityDao;
    
    @Autowired
    private SecurityService _securityService;
    
    /*
     * @seeorg.springframework.security.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("There is no User with login/username: " + username);
        }
        return new UserDetailsImpl(user);
    }
    
    /*
     * @see vn.pyco.tinycms.services.UserService#getUserByUsername(java.lang.String)
     */
    @Override
    public User getUserByUsername(String username) {
        String login = username.toLowerCase();
        User user = _userDao.getByUsername(login);     
        
        return user;
    }
    
    /*
     * @see vn.pyco.tinycms.services.AuthorityService#getByName(java.lang.String)
     */
    @Override
    public Authority getAuthorityByName(String authority) {
        return _authorityDao.getByName(authority);
    }
    
    /*
     * @see vn.pyco.tinycms.services.UserService#getCurrentUser()
     */
    @Override
    public User getCurrentUser() {
        String username = _securityService.getCurrentUsername();
        if (username != null) {
            return getUserByUsername(username);
        }
        
        return null;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void saveUser(User user) {
        _userDao.save(user);
    }
}
