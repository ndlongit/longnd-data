/**
 * 
 */
package com.structis.vip.server.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.structis.vip.shared.model.UserModel;

@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    private static final Logger LOGGER = Logger.getLogger(SessionServiceImpl.class);

    private int sessionTimeout = 180000;

    private static List<String> appUserContext = new ArrayList<String>();

    @Override
    public boolean openSession(HttpSession httpSession, UserModel userContext) {
        synchronized (appUserContext) {
            if (appUserContext.contains(userContext.getUserName())) {
                LOGGER.debug("User with login : " + userContext.getUserName() + " déjà enregistré");
                return false;
            }
            LOGGER.debug("--> Initialisation de la session - Session Login " + userContext.getUserName() + " session Timeout : "
                    + this.sessionTimeout);
            httpSession.setMaxInactiveInterval(this.sessionTimeout);
            appUserContext.add(userContext.getUserName());
            return true;
        }
    }

    @Override
    public void killSession(UserModel userContext) {
        LOGGER.debug("Kill session with login : " + userContext.getUserName());
        synchronized (appUserContext) {
            if (appUserContext.contains(userContext.getUserName())) {
                LOGGER.debug("Session Login : " + userContext.getUserName() + " were killed");
                appUserContext.remove(userContext.getUserName());
            } else {
                LOGGER.debug("Session non trouvé... celui récupèré du client est clientUserContext " + userContext.getUserName());
                for (String logged : appUserContext) {
                    LOGGER.debug("Session en Cours : " + logged);
                }
            }
        }

    }

    public int getSessionTimeout() {
        return this.sessionTimeout;
    }
}
