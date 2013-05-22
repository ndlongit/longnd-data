package com.structis.vip.server.mapper;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionException;

import com.structis.vip.server.core.ConstantError;
import com.structis.vip.shared.exception.FunctionalException;
import com.structis.vip.shared.exception.ServiceException;
import com.structis.vip.shared.exception.TechnicalException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class ExceptionMapper {

    public static ServiceException map(Throwable ex) {
        ServiceException result = null;

        /*
         * Si l'erreur est d�j� sous forme TechnicalException ou Functional Exception On l'aisse passer
         */
        if (ex instanceof TechnicalException || ex instanceof FunctionalException) {
            result = (ServiceException) ex;
        }

        /*
         * Probl�me de cr�ation de transaction comme : - r�seau - mauvaise url, username et password - time out
         */
        else if (ex instanceof TransactionException) {
            result = new TechnicalException(ex, ConstantError.ERR_TRANSACTION);
        }

        /*
         * Probl�me de donn�es non conforme : - violation des constraint
         */
        else if (ex instanceof DataIntegrityViolationException || ex instanceof ConstraintViolationException) {
            result = new TechnicalException(ConstantError.ERR_DONNES_INCORRECT);
        }

        /*
         * Probl�me de data access comme : - la base n'est pas correct
         */
        else if (ex instanceof DataAccessException) {
            result = new TechnicalException(ex, ConstantError.ERR_DATA_ACCESS);
        }
        /*
         * Session expire
         */
        /*
         * else if (ex instanceof AuthenticationCredentialsNotFoundException){ result = new TechnicalException(ex, ConstantError.ERR_SESSION_EXPIRE);
         * }
         */
        /*
         * Access refuse
         */
        /*
         * else if (ex instanceof AccessDeniedException){ result = new FunctionalException(ex, ConstantError.ERR_ACCES_REFUSE); }
         */

        /*
         * Autre erreurs : erreur inconnu
         */
        else {
            result = new TechnicalException(ex, ConstantError.ERR_INCONNU);
        }

        return result;
    }

}
