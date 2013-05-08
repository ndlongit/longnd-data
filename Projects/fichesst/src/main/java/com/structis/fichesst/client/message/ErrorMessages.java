package com.structis.fichesst.client.message;

import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * Interface qui contient les constants associ�s � resource ActionMessages pour mapper le code d'erreur <-> messages RDG,
 * quatre digit avec le 1er digit comme le suivant :
 * <ul>
 * <li>t : Erreur technique</li>
 * <li>f : Erreur fonctionnelle</li>
 * </ul>
 * 
 * @author b.brotosumpeno
 */
public interface ErrorMessages extends ConstantsWithLookup {

	// Technical error
	String t001();
	String t002();
	String t003();
	String t004();
	String t005();
	String t006();
	String t007();
	String t008();
	String t009();
	String t010();
	String t011();
	
	// Functional error
	String f001();
	String f002();
	String f003();
	String f004();
	String f005();
	String f006();
	String f007();
	String f008();
	String f009();
	String f010();
	String f011();
	String f012();
	String f013();
}
