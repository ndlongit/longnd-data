package com.structis.vip.server.core;

import java.io.File;

public class Constants {

	public final static String ROLES = "roles";
	public final static String COMPARE_PREFIXE = "compare_";
	public final static String ADMIN = "admin";

	public static final String TEMPLATE_FILE_PATH = "docs" + File.separator + "templates";
	public static final String TEMP_FILE_PATH = "temp";
	public static final String DELEGATION_DOCUMENT_FILE_PATH = "docs" + File.separator + "others";
	public static final String TEMP_PDF_FILE_NAME = "temp.pdf";
	public static final String PDF_EXTENSION_FILE = ".pdf";
	public static final String DOC_EXTENSION_FILE = ".doc";

	public static final String SIGNED_DOCUMENT_FILE_PATH = "docs" + File.separator + "signed";
	
	public static final String SCRIPT_FILE_PATH = "docs" + File.separator + "script";

	public static final String ENTITE_ID_ETDE = "327";
	public static final String ENTITE_ID_BYEFE = "015";
	public static final String ENTITE_ID_BYTP = "016";
	public static final String[] ENTITE_BELONGS_BYEFE = {"015", "016"};

	// properties name for rubis in catalina
	public static final String CATALINA_RUBIS_ADDRESS_BY_ID_BYCN = "rubis.url.id.bycn";
	public static final String CATALINA_RUBIS_SOCIETE_ETDE = "rubis.url.etde";
	public static final String CATALINA_RUBIS_CODES_NAMES_ETDE = "rubis.etde.codesNames";
	public static final String CATALINA_RUBIS_CODES_NAMES_BYEFE = "rubis.byefe.codesNames";
	public static final String CATALINA_RUBIS_CODES_NAMES_BYTP = "rubis.bytp.codesNames";

	public static final String CATALINA_RUBIS_USERNAME = "rubis.username";
	public static final String CATALINA_RUBIS_PASSWORD = "rubis.password";

	// properties name argos in catalina
	public static final String CATALINA_ARGOS_URL = "argos.url";

	public static final String CATALINA_ARGOS_USERNAME = "argos.username";
	public static final String CATALINA_ARGOS_PASSWORD = "argos.password";

	// properties name reports in catalina
	public static final String CATALINA_REPORT_1_URL = "report.url.1";
	public static final String CATALINA_REPORT_2_URL = "report.url.2";
	public static final String CATALINA_REPORT_3_URL = "report.url.3";
	public static final String CATALINA_REPORT_4_URL = "report.url.4";
	public static final String CATALINA_REPORT_5_URL = "report.url.5";
	public static final String CATALINA_REPORT_6_URL = "report.url.6";
	public static final String CATALINA_REPORT_7_URL = "report.url.7";
	public static final String CATALINA_REPORT_8_URL = "report.url.8";
	
	public static final String CATALINA_VIP_DIRECTORY = "vip.directory";
	public static final String RAPPORT_FILE_PATH = "docs" + File.separator + "rapports";
	public static final String DOCUMENT_FILE_PATH = "docs" + File.separator + "documents";
	
	public static final String ENTITE_ETDE = "ETDE";
	public static final String ENTITE_BYEFE = "BYEFE";
	public static final String ENTITE_BYTP = "BYTP";
}
