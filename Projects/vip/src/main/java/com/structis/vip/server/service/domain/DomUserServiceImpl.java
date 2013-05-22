package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.bean.domain.UserRole;
import com.structis.vip.server.dao.UserDao;
import com.structis.vip.server.dao.UserRoleDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;
import com.structis.vip.server.util.Encryptor;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.exception.UserException;

@Service("domUserService")
public class DomUserServiceImpl extends GenericEntityServiceImpl<User, Integer> implements DomUserService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomUserServiceImpl.class);

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("userRoleDao")
    private UserRoleDao userRoleDao;

    @Override
    public GenericDao<User, Integer> getDao() {
        return this.userDao;
    }

    @Override
    public User getNew() {
        return new User();
    }

    @Override
    public User getNewWithDefaults() {
        return this.getNew();
    }

    @Override
    public List<User> findUsers() {
        return this.find();
    }

    @Override
    public List<User> findUsersByEntite(String entiteId) {
        return this.userDao.findUsersByEntite(entiteId);
    }

    @Override
    public User findUserById(final int id) {
        User domUser = this.getByPrimaryKey(id);
        if (domUser != null) {
            domUser.setUserRoles(this.userRoleDao.findByUserId(domUser.getId()));
        }
        return domUser;
    }

    @Override
    public User findUserByUserName(final String userName, final String domain) {
        User domUser = this.userDao.findUserByUserName(userName, domain);
        if (domUser != null) {
            domUser.setUserRoles(this.userRoleDao.findByUserId(domUser.getId()));
        }
        return domUser;
    }

    @Override
    public User insert(final User user) throws UserException {
        String userName = user.getUserName();
        String domain = null;
        if (user.getDomain() != null) {
            domain = user.getDomain().getName();
        }
        if (this.findUserByUserName(userName, domain) == null) {
            // if (findUserByNameDomainAndEntite(userName, domain, user.getEntite().getEntId()) == null){
            // encrypt password
            user.setPassword(Encryptor.encrypt(user.getPassword()));
            User userInsert = this.userDao.insert(user);
            if (userInsert != null) {
                if (user.getUserRoles() != null) {
                    for (UserRole userRole : user.getUserRoles()) {
                        userRole.setUser(userInsert);
                    }
                    this.userRoleDao.insert(user.getUserRoles());
                }
            }
            return userInsert;
        } else {
            throw new UserException(ExceptionType.USER_DUPLICATED);
        }
    }

    @Override
    public User update(User user) throws UserException {
        String userName = user.getUserName();
        String domain = null;
        if (user.getDomain() != null) {
            domain = user.getDomain().getName();
        }
        User findUser = this.findUserByUserName(userName, domain);
        if ((findUser == null) || (findUser.getId().equals(user.getId()))) {
            if (user.getPassword() != null) {
                if (!user.getPassword().equals(findUser.getPassword())) {
                    user.setPassword(Encryptor.encrypt(user.getPassword()));
                }
            }
            User userUpdate = this.userDao.update(user);
            if (userUpdate != null) {
                if (this.userRoleDao.deleteByUserId(userUpdate.getId())) {
                    if (user.getUserRoles() != null) {
                        for (UserRole userRole : user.getUserRoles()) {
                            userRole.setId(null);
                        }
                        this.userRoleDao.insert(user.getUserRoles());
                    }
                }
            }
            return userUpdate;
        } else {
            throw new UserException(ExceptionType.USER_DUPLICATED);
        }
    }

    @Override
    public Boolean deleteWithRoles(User user) {
        if (user != null) {
            if (this.checkBreakIntegrityData(user)) {
                return false;
            } else {
                if (this.userRoleDao.deleteByUserId(user.getId())) {
                    User domUser = this.getByPrimaryKey(user.getId());
                    if (domUser != null) {
                        this.delete(domUser);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private boolean checkBreakIntegrityData(User user) {
        return this.userDao.checkBreakIntegrityData(user);
    }

    @Override
    public User getAuthorization(String name, Integer domainId, String password) {
        User domUser = this.userDao.getAuthorization(name, domainId, password);
        if (domUser != null) {
            domUser.setUserRoles(this.userRoleDao.findByUserId(domUser.getId()));
        }
        return domUser;
    }

    @Override
    public ExceptionType changePassword(Integer userId, String currentPassword, String newPassword) throws UserException {
        User user = this.getByPrimaryKey(userId);
        if (user == null) {
            throw new UserException();
        } else {
            String encrypt = Encryptor.encrypt(currentPassword);
            if (encrypt != null && !encrypt.equals(user.getPassword())) {
                return ExceptionType.USER_PASSWORD_INCORRECT;
            } else {
                user.setPassword(Encryptor.encrypt(newPassword));
                this.userDao.update(user);
                return null;
            }
        }
    }

    @Override
    public List<User> findByPerimetre(String perimetreId) {
        return this.userDao.findByPerimetre(perimetreId);
    }

    @Override
    public User updateNoRoles(User user) {
        User userUpdate = this.userDao.update(user);
        return userUpdate;
    }

    @Override
    public List<User> findUsersByEntiteAndUser(String entiteId, Integer userId) {
        return this.userDao.findUsersByEntiteAndUser(entiteId, userId);
    }
}
