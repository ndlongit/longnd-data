package com.structis.vip.server.core;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.structis.vip.shared.exception.ServiceException;

/**
 * Classe qui fait l'injection de spring pour les servlets. Pour que un bean de spring soit injecter, il suffit de mettre l'annotation @Autowired
 * 
 * @author b.brotosumpeno
 * 
 */
public class DependencyInjectionRemoteServiceServlet extends RemoteServiceServlet {

    private static final long serialVersionUID = 1L;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    /* Spring Bean */
    @Autowired
    private ManagerTemplateIfc managerTemplate;

    @Override
    public final void init() throws ServletException {
        logger.debug("Logger dependency load");
        super.init();
        DependencyHelper.doDependencyInjection(this.getServletContext(), this);

        // Appel special pour le mapper
        this.managerTemplate = (ManagerTemplateIfc) SpringGetter.getBean(this.getServletContext(), "managerTemplate");
    }

    @Override
    protected void doUnexpectedFailure(Throwable e) {
        // logger les erreurs
        logger.error("Erreur inattendu", e);

        if (e != null && e.getCause() instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e.getCause();

            try {
                this.getThreadLocalResponse().reset();
            } catch (IllegalStateException ex) {
                throw new RuntimeException("Unable to report failure", serviceException);
            }
            ServletContext servletContext = this.getServletContext();
            HttpServletResponse response = this.getThreadLocalResponse();
            try {
                response.setContentType("text/plain");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                try {
                    response.getOutputStream().write(serviceException.getCode().getBytes());
                } catch (IllegalStateException ex) {
                    response.getWriter().write(serviceException.getCode());
                }
            } catch (IOException ex) {
                servletContext.log("respondWithUnexpectedFailure failed while sending the previous failure to the client", ex);
            }
        } else {
            super.doUnexpectedFailure(e);
        }
    }

    /**
     * Appel le manager template
     * 
     * @param callBack
     *            le manager callback
     * @param param
     *            les parametres avant le map
     * @return resultat apres le map
     */
    protected Object callManager(ManagerCallBack callBack, Object... param) {
        return this.managerTemplate.callManager(callBack, param);
    }

}
