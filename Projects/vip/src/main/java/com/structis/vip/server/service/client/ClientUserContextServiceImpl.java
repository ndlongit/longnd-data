package com.structis.vip.server.service.client;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.structis.vip.client.service.ClientUserContextService;
import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.service.domain.DomUserService;
import com.structis.vip.server.service.util.SessionService;
import com.structis.vip.shared.exception.AppException;
import com.structis.vip.shared.exception.AppExceptionFactory;
import com.structis.vip.shared.exception.AppExceptionType;
import com.structis.vip.shared.model.UserModel;

@Service("clientUserContextService")
@SuppressWarnings("serial")
public class ClientUserContextServiceImpl extends DependencyInjectionRemoteServiceServlet implements ClientUserContextService {

    private static final Logger LOGGER = Logger.getLogger(ClientUserContextServiceImpl.class);

    @Autowired
    private DozerBeanMapper dozerMapper;

    @Autowired
    private DomUserService domUserService;

    @Autowired
    private SessionService sessionService;

    @Override
    public UserModel checkUserByKerberosSSO() throws AppException {
        LOGGER.debug("Connection d'un utilisateur");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        if (request.getUserPrincipal() != null && !"".equalsIgnoreCase(request.getUserPrincipal().getName())) {
            String userLogin = request.getUserPrincipal().getName();
            LOGGER.debug("Login de l'utilisateur récupèré avec succès via le SSO Kerberos - " + userLogin);
            return this.findUserOnDomainBC(userLogin.substring(0, userLogin.indexOf("@")).toLowerCase());
        }
        LOGGER.debug("Login de l'utilisateur non trouvé via le SSO kerberos - ouverture de l'écran de login");
        return null;
    }

    @Override
    public UserModel findUserOnDomainBC(String login) throws AppException {
        UserModel clientUserContext;
        User domUser = this.domUserService.findUserByUserName(login, null);
        if (domUser != null) {
            LOGGER.debug("Utilisateur trouvé, initialisation des rôles");
            clientUserContext = this.mapToModel(domUser);
            return this.setUserContext(clientUserContext);
        }
        throw AppExceptionFactory.getInstance().getAppException(AppExceptionType.USER_UNKNOWN);
    }

    private UserModel setUserContext(UserModel userContext) throws AppException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = attr.getRequest().getSession();

        if (userContext != null) {
            if (!this.sessionService.openSession(httpSession, userContext)) {
                throw AppExceptionFactory.getInstance().getAppException(AppExceptionType.USER_ALREADY_CONNECTED);
            }
            userContext.setSessionTimeout(httpSession.getMaxInactiveInterval());
            userContext.setLastAccessedTime((new Date()).getTime());
        }
        return userContext;
    }

    private UserModel mapToModel(final User user) {
        return this.dozerMapper.map(user, UserModel.class);
    }

    @Override
    public void disconnectUser(UserModel userContext) {
        this.sessionService.killSession(userContext);

    }

}
