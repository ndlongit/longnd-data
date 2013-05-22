package com.structis.vip.client.navigation;

import static com.structis.vip.shared.security.Role.ANONYMOUS;

import com.structis.vip.client.message.Messages;
import com.structis.vip.shared.security.Role;

/**
 * Enum pour l'action, l'ajoute d'un action commence par ici Il est d�fini pour assurer que l'appel est fait sur une action existant Regle de nommage
 * :
 * <ul>
 * <li>Pour Enum : ACTION_ + <Nom d'action></li>
 * <li>Pour Label : action+ <Nom de label></li>
 * </ul>
 * 
 * Apr�s la cr�ation d'une enum, il faut cr�er une m�thode dans la classe {@link Messages}
 * 
 * @author b.brotosumpeno
 * 
 */
public enum Action {
    ACTION_ACCEUIL("actionAcceuil", ANONYMOUS), ACTION_CONTROL("actionControl", ANONYMOUS), ACTION_DELEGATION("actionDelegation", ANONYMOUS), ACTION_ADMIN(
            "actionAdmin", ANONYMOUS), ACTION_PILOTAGE("actionPilotage", ANONYMOUS), ACTION_CHOOSE_ENTITE("actionChooseEntite", ANONYMOUS), ACTION_LOGIN(
            "actionLogin", ANONYMOUS), ACTION_DOCUMENT("actionDocument", ANONYMOUS);

    private Action(String label, Role role) {
        this.label = label;
        this.role = role;
    }

    /**
     * Label de l'action, associer au ressource bundle
     */
    private final String label;

    /**
     * Role de chaque action
     */
    private final Role role;

    public String getLabel() {
        return this.label;
    }

    public Role getRole() {
        return this.role;
    }

}
