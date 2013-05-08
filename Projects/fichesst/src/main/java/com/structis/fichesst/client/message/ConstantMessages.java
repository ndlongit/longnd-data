package com.structis.fichesst.client.message;

import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * Cette interface est pour stocker les messages qui contient {}
 * 
 * @author b.brotosumpeno
 */
public interface ConstantMessages extends ConstantsWithLookup {

	/* ### Les actions */
	@Key("common.pagingdisplaymessages")
	String commonPagingDisplayMessages();

	@Key("common.textfielddisplaymessages")
	String commonTextFieldDisplayMessages();

	@Key("common.fieldnotdecimal")
	String commonFieldNotDecimal();

	@Key("common.negative")
	String commonNegativeMessages();

	@Key("risk.statut.draf")
	String B();

	@Key("risk.statut.sent")
	String E();

	@Key("risk.statut.going")
	String C();

	@Key("risk.statut.resold")
	String L();

	@Key("doc.annexe.type.url")
	String typeURL();

	@Key("doc.annexe.type.fichier")
	String typeFichier();

	/* ### Workflow of risk status */
	@Key("risk.statut.Emettre")
	String emettreStatut();

	@Key("risk.statut.Reponse")
	String reponseStatut();

	@Key("risk.statut.RefuserReponse")
	String refuserReponseStatut();

	@Key("risk.statut.Lever")
	String leverStatut();

	@Key("risk.statut.AnnulerLevee")
	String annulerLeveeStatut();

	/* ### Default value for grids */
	@Key("tous")
	String Tous();

	@Key("nom.prenom")
	String NomOuPrenom();

	/* ### Menu administration */
	@Key("administration.projet")
	String administrationProject();

	@Key("administration.service")
	String administrationService();

	@Key("administration.user")
	String administrationUser();

	@Key("administration.detection")
	String administrationDetection();

	@Key("administration.reference")
	String administrationReference();

	/* Menu in grid user */
	@Key("listuser.menu.detail")
	String listUserMenuDetail();

	@Key("listuser.menu.modifier")
	String listUserMenuModifier();

	@Key("listuser.menu.supprimer")
	String listUserMenuSupprimer();

	@Key("listuser.search.user")
	String listUserSearchUser();

	/* ### Paging toolbar */
	@Key("paging.value.10")
	String pagingValue10();

	@Key("paging.value.20")
	String pagingValue20();

	@Key("paging.value.30")
	String pagingValue30();

	@Key("paging.value.40")
	String pagingValue40();

	@Key("paging.value.50")
	String pagingValue50();
}
