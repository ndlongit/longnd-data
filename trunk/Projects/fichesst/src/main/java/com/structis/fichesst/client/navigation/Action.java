package com.structis.fichesst.client.navigation;

import static com.structis.fichesst.shared.security.Droits.anonymous;

import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.shared.security.Droits;

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
	ACTION_DEMO("actionDemo", anonymous), ACTION_ERROR("actionError", anonymous), ACTION_ACCEUIL("actionAcceuil", anonymous), ACTION_ADMIN(
			"actionAdmin", anonymous), ACTION_SYNTHESE("actionSynthese", anonymous), ACTION_SYNTHESE_CREATE("actionSyntheseCreate", anonymous), ACTION_USER(
			"actionUser", anonymous), ACTION_CREATE_FICHESST("actionCreateFICHESST", anonymous), ;

	private Action(String label, Droits role) {
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
	private final Droits role;

	public String getLabel() {
		return label;
	}

	public Droits getRole() {
		return role;
	}

}
