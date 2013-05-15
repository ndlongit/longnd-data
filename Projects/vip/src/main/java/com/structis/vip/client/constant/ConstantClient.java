package com.structis.vip.client.constant;

import com.google.gwt.core.client.GWT;

/**
 * Divers constants pour l'application client
 * 
 * @author b.brotosumpeno
 *
 */
public class ConstantClient {
	public final static int PAGE_SIZE_10 = 10;
	public final static int PAGE_SIZE_20 = 20;
	public final static int PAGE_SIZE_30 = 30;
	public final static int PAGE_SIZE_40 = 40;
	public final static int DEFAULT_PAGE_SIZE_50 = 50;
	
	public final static String LANGUAGE_CODE_FRENCH = "fr";
	public final static String LANGUAGE_CODE_ENGLISH = "en";
	public final static String DEFAULT_LANGUAGE_CODE = "fr";
	
	//Des constants correspond � l'id HTML 
	public final static String ADMIN = "appAdmin";
	public final static String VERSION = "appVersion";
	public final static String START_ARIANE = "appStartAriane";
	public final static String MENU_HORIZONTAL_1 = "appMenuHorizontal1";
	public final static String MENU_HORIZONTAL_2 = "appMenuHorizontal2";
	public final static String MENU_HORIZONTAL_3 = "appMenuHorizontal3";
	public final static String FIL_ARIANE = "appFilAriane";
	public final static String CONTENT = "appContent";
	
	// Divers constants
	public final static String FLECH = " >  ";
	public final static String SIMPLE_COMBO_VALUE = "value";
	public final static String POINT = ".";
	public final static String EMPTY = ".";
	public final static String[] TYPE_IMAGES = {".jpg", ".bmp"};
	
	// Action constant
	public final static String ACTION_UPLOAD_LABEL_DO = ".uploadLabel";
	public final static String ACTION_UPLOAD_FCG_DO = ".uploadFcg";
	public final static String ACTION_UPLOAD_ACV_DO = ".uploadAcv";
	public final static String ACTION_UPLOAD_DOC_DO = ".uploadDoc";
	public final static String ACTION_REPORT_FCG = ".reportFcg";
	public final static String ACTION_REPORT_PRODUIT = ".reportProduit";
	public final static String ACTION_EXPORT_NEMO = ".exportNemo";
	public final static String ACTION_COMPARE_PRODUIT = ".compareProduit";
	public final static String ACTION_EXPORT_FICHE_INIES = ".exportFicheInies";
	
	// Etat d'un produit
	public final static String ETAT_CREATION = "C";
	public final static String ETAT_MODIFICATION = "M";
	public final static String ETAT_INACTIF = "S";
	public final static String ETAT_ACTIF = "A";
	
	// Misc
	public static final String CANCEL_HISTORY = "cancel";
	public static final String PAGINATION = "paging";
	
	// Search
	public static final String SEARCH_PREFIXE = "search.";
	public static final String SEARCH_RUBRIQUE = "search.rubrique.id";
	public static final String RUBRIQUE_ID = "rubrique.id";
	public static final String SEARCH_TYPEOUVRAGE = "search.produitTypeOuvrages.typeOuvrage.id";
	public static final String TYPEOUVRAGE_ID = "produitTypeOuvrages.typeOuvrage.id";
	public static final String SEARCH_MOT_CLES = "search.motcles";
	public static final String MOT_CLES = "motcles";
	
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE_FORMAT_DDMM = "dd/MM";

	public static final int LANGUAGE_ID_FR = 1;
	public static final int LANGUAGE_ID_GB = 2;
	
	public static final String PDF_EXTENSION_FILE = ".pdf";
	public static final String DOC_EXTENSION_FILE = ".doc";
	
	// MUST be synchronized with database ===============================
	public static final String PERIMETER_TYPE_NAME_IS_CHANTIER = "Chantier";
	public static final String[] PERIMETRE_TYPE_IS_CHANTIER = new String[]{"0000000030", "0000000031", "0000000032"};
	
	public static final int DELEGATION_STATUS_IS_P = 6;
	public static final int DELEGATION_STATUS_IS_V = 7;
	public static final int DELEGATION_STATUS_IS_D = 8;
	public static final int DELEGATION_STATUS_IS_O = 9;
	public static final int DELEGATION_STATUS_IS_T = 10;
	
	public static final int DELEGATION_TYPE_IS_PRINCIPAL = 1;
	public static final int DELEGATION_TYPE_IS_SOUS_DELEGATION = 2;
	public static final int DELEGATION_TYPE_IS_TEMPORAIRE = 3;
	
	public static final String ENTITE_ID_IS_ETDE = "327";
	public static final String ENTITE_ID_IS_BYEFE = "015";
	public static final String[] ENTITE_ID_BELONGS_BYEFE = {"015", "016"};
	
	public static final String PERIMETRE_ID_IS_TOP = "STRUCTURE___________0000002685";
	
	public static final String PERIMETRE_TYPE_UO = "0000000003";
	
	public static final String DOCUMENT_TEMPLATE_PATH = GWT.getHostPageBaseURL() + "docs/templates/";
	
	public static final String ADMIN_TREE_ID = "ADMIN_PERIMETRE_TREE_ID";
	public static final String ADMIN_COLLA_DELEGANT_BUTTON_ID = "ADMIN_COLLA_DELEGANT_BUTTON_ID";
	public static final String ADMIN_COLLA_DELEGATAIRE_BUTTON_ID = "ADMIN_COLLA_DELEGATAIRE_BUTTON_ID";
	public static final String ADMIN_COLLA_GRID_ID = "ADMIN_COLLA_GRID_ID";
	
	public static final int COLLABORATEUR_TYPE_DG = 3;
	public static final int COLLABORATEUR_TYPE_DGD = 4;
	//===================================================================
	
	public static final String CONTROL_TREE_ID = "CONTROL_TREE_ID";
	public static final String CONTROL_TREE_PANEL_ID = "CONTROL_TREE_PANEL_ID";
	public static final int NOCHANGE_MODE = 0;
	public static final int EDIT_MODE = 2;
	public static final int NEW_MODE = 1;
	public static final Object TYPE_SITE = "Site";
	public static String PATH_LABEL_ID = "PATH_LABEL_ID";
	public static final String COLLABORATEUR_TYPE_MANDATAIRE_SOCIAL = "Mandataire social";
	
	public static final int CHANTIER_CLASSIC = 9;
}
