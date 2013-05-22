package com.structis.vip.client.exception;

import com.google.gwt.core.client.GWT;
import com.structis.vip.client.message.Messages;
import com.structis.vip.shared.exception.ExceptionType;

public class ExceptionMessageHandler {

    private final static Messages messages = GWT.create(Messages.class);

    public static String getErrorMessage(ExceptionType code) {
        switch (code) {
        case DEL_INSERT_DUPLICATE:
            return messages.delegationduplicateerror();
        case DEL_UPDATE_DUPLICATE:
            return messages.delegationduplicateerror();
        case DEL_SAME_DELEGANT_DELEGANTAIRE:
            return messages.delegationsamedelegantdelegantaireerror();
        case DEL_START_AFTER_END_DATE:
            return messages.delegationstartafterenddateerror();
        case DEL_TEMP_OF_TEMP:
            return messages.delegationtemporaryoftemporaryerror();
        case DEL_UNIQUE_DELEGANT:
            return messages.delegationuniquedeleganterror();
        case DELEGATION_MODEL_EXIST:
            return messages.delegationmodelexisterror();
        case DELEGATION_MODEL_USED_IN_RULES:
            return messages.delegationmodelruleexisterror();
        case DELEGATION_TYPE_SAME_NAME:
            return messages.delegationtypeinsertduplicated();
        case PERIMETRE_HAS_CHILDREN:
            return messages.perimetredeletehaschildren();
        case PERIMETRE_USED_IN_DELEGATION:
            return messages.perimetredeleteisuseddelegation();
        case PERIMETRE_USED_IN_COLLABORATEUR:
            return messages.perimetredeleteisusedcollaborateur();
        case PERIMETRE_USED_IN_ROLE:
            return messages.perimetredeleteisusedrole();
        case PERIMETRE_USED_IN_USER:
            return messages.perimetredeleteisuseduser();
        case USER_DUPLICATED:
            return messages.userduplicated();
        case USER_NOT_AUTHORIZED:
            return messages.usernotauthorized();
        case DEL_DELETE_DELEGATION_IS_PARENT:
            return messages.delegationmodeldeleteerror();
        case COLLABORATEUR_USED_IN_DELEGATION:
            return messages.collaboraturedeleteisuseddelegation();
        case ENTITE_JUR_DELETE_EXIST_IN_PERIMETRE:
            return messages.entiteJuridiquemsgdeleteexisterror();
        case ENTITE_JUR_DELETE_EXIST_IN_DELEGATION:
            return messages.entiteJuridiquemsgdeleteexistdelegationerror();
        case ENTITE_JUR_DELETE_DEFAULT:
            return messages.entiteJuridiquemsgdeletedefaulterror();
        case LANGUAGE_DELETE_EXIST_IN_PERIMETRE:
            return messages.languagemsgdeleteexistperimetre();
        case LANGUAGE_DELETE_EXIST_IN_DELEGATION_MODEL:
            return messages.languagemsgdeleteexistdelegationmodel();
        case LANGUAGE_DELETE_EXIST_IN_DOCUMENT_MODEL:
            return messages.languagemsgdeleteexistdocumentmodel();
        case LANGUAGE_DELETE_EXIST_IN_FIELD:
            return messages.languagemsgdeleteexistfield();
        case LANGUAGE_DELETE_DEFAULT:
            return messages.languagemsgdeletedefault();
        case LANGUAGE_DELETE_EXIST_IN_ENTITE:
            return messages.languagemsgdeleteexistentite();
        case CHANTIER_TYPE_DELETE_EIXST:
            return messages.chantiertypemsgdeleteexist();
        case PERIMETRE_TYPE_DELETE_EIXST:
            return messages.perimetretypemsgdeleteexist();
        case NATURE_DELETE_EIXIST_IN_DELEGATION:
            return messages.naturemsgdeleteexistdelegation();
        case NATURE_DELETE_EIXIST_IN_DELEGATION_MODEL:
            return messages.naturemsgdeleteexistdelegationmodel();
        case DOCUMENT_DELETE_EXIST_IN_DELEGATION_MODEL:
            return messages.documentdeleteisuseddelegationmodel();
        case DOCUMENT_DELETE_EXIST_IN_DELEGATION:
            return messages.documentdeleteisuseddelegation();
        case DOCUMENT_INSERT_DUPLICATE_NAME:
            return messages.documentinsertduplicatename();
        default:
            return messages.commonerror();
        }
    }
}
