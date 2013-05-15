package com.structis.vip.shared.exception;

/**
 * Exception propre à l'application
 * Permet d'éviter de multiple appel
 * client serveur dans des cas fonctionnel
 * nécessitant un retour particulier, mais aussi
 * la prise en compte de cas "d'erreur"
 * Voir ClientLoginServiceImpl, pour exemple
 * @author th.bernard
 */
@SuppressWarnings("serial")
public class AppException extends Exception {

	private AppExceptionType type;

	public AppException() {
	}

	public AppException(AppExceptionType type) {
		super();
		this.type = type;
	}

	public AppExceptionType getType() {
		return type;
	}

	public void setType(AppExceptionType type) {
		this.type = type;
	}
	
}
