package com.structis.vip.server.util;

import java.io.File;

import org.apache.log4j.Logger;

import com.structis.vip.server.core.ServerConstant;

public class CatalinaPropertiesUtil {

    public static final String RUBIS_ADDRESS_BY_ID_BYCN_URL = "http://bcnsweb088.bouygues-construction.com:9000/hr-appli-externe/services/adresse?idbycn=";
    public static final String RUBIS_SOCIETE_ETDE_URL = "http://bcnsweb088.bouygues-construction.com:9000/hr-appli-externe/services/societe?type=etde&code=";
    public static final String RUBIS_ETDE_CODES_NAMES = "043<>ETDE,473<>MARC FAVRE,548<>EXPRIMM,779<>COGEMEX,788<>AXIONE,812<>ETDE LS,896<>ETDE FONDATIONS,925<>SERMA";
    public static final String RUBIS_BYEFE_CODES_NAMES = "022<>DV Construction,015<>GFC Construction,628<>MIRAGLIA,051<>NORPAC,027<>PERTUY Construction,024<>QUILLE Construction,404<>RICHELMI,371<>CIRMAD Centre Sud Ouest,323<>CIRMAD Est,332<>CIRMAD Grand Sud,175<>CIRMAD Nord,121<>CIRMAD Prospectives";
    private static final String RUBIS_BYTP_CODES_NAMES = "633<>BCSN,703<>BYTP,327<>BYTP RF,566<>NOVI";

    public static final String RUBIS_SERVER_USERNAME = "VIP";
    public static final String RUBIS_SERVER_PASSWORD = "VIP";

    public static final String ARGOS_URL = "http://argoswsauth.bouygues-construction.com:8082/Argos/services/wsArgosPortAuth";
    public static final String ARGOS_SERVER_USERNAME = "Vip";
    public static final String ARGOS_SERVER_PASSWORD = "xYLdaE";

    private static final Logger LOGGER = Logger.getLogger(CatalinaPropertiesUtil.class);

    public static String getRubisEtdeUrl() {
        String strResult = System.getProperty(ServerConstant.CATALINA_RUBIS_SOCIETE_ETDE);
        LOGGER.info("RUBIS ETDE URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = RUBIS_SOCIETE_ETDE_URL;
        }
        return strResult;
    }

    public static String getRubisCodesNamesEtde() {
        String strResult = System.getProperty(ServerConstant.CATALINA_RUBIS_CODES_NAMES_ETDE);
        LOGGER.info("RUBIS CODES NAMES ETDE:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = RUBIS_ETDE_CODES_NAMES;
        }
        return strResult;
    }

    public static String getRubisIdBycnUrl() {
        String strResult = System.getProperty(ServerConstant.CATALINA_RUBIS_ADDRESS_BY_ID_BYCN);
        LOGGER.info("RUBIS ID BYCN URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = RUBIS_ADDRESS_BY_ID_BYCN_URL;
        }
        return strResult;
    }

    public static String getRubisUsername() {
        String strResult = System.getProperty(ServerConstant.CATALINA_RUBIS_USERNAME);
        LOGGER.info("RUBIS USERNAME:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = RUBIS_SERVER_USERNAME;
        }
        return strResult;
    }

    public static String getRubisPassword() {
        String strResult = System.getProperty(ServerConstant.CATALINA_RUBIS_PASSWORD);
        LOGGER.info("RUBIS PASSWORD:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = RUBIS_SERVER_PASSWORD;
        }
        return strResult;
    }

    public static String getArgosUrl() {
        String strResult = System.getProperty(ServerConstant.CATALINA_ARGOS_URL);
        LOGGER.info("ARGOS URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = ARGOS_URL;
        }
        return strResult;
    }

    public static String getArgosUsername() {
        String strResult = System.getProperty(ServerConstant.CATALINA_ARGOS_USERNAME);
        LOGGER.info("ARGOS USERNAME:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = ARGOS_SERVER_USERNAME;
        }
        return strResult;
    }

    public static String getArgosPassword() {
        String strResult = System.getProperty(ServerConstant.CATALINA_ARGOS_PASSWORD);
        LOGGER.info("ARGOS PASSWORD:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = ARGOS_SERVER_PASSWORD;
        }
        return strResult;
    }

    public static String getReport1Url() {
        String strResult = System.getProperty(ServerConstant.CATALINA_REPORT_1_URL);
        LOGGER.info("REPORT 1 URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = "";
        }
        return strResult;
    }

    public static String getReport2Url() {
        String strResult = System.getProperty(ServerConstant.CATALINA_REPORT_2_URL);
        LOGGER.info("REPORT 2 URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = "";
        }
        return strResult;
    }

    public static String getReport3Url() {
        String strResult = System.getProperty(ServerConstant.CATALINA_REPORT_3_URL);
        LOGGER.info("REPORT 3 URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = "";
        }
        return strResult;
    }

    public static String getReport4Url() {
        String strResult = System.getProperty(ServerConstant.CATALINA_REPORT_4_URL);
        LOGGER.info("REPORT 4 URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = "";
        }
        return strResult;
    }

    public static String getReport5Url() {
        String strResult = System.getProperty(ServerConstant.CATALINA_REPORT_5_URL);
        LOGGER.info("REPORT 5 URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = "";
        }
        return strResult;
    }

    public static String getReport6Url() {
        String strResult = System.getProperty(ServerConstant.CATALINA_REPORT_6_URL);
        LOGGER.info("REPORT 6 URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = "";
        }
        return strResult;
    }

    public static String getReport7Url() {
        String strResult = System.getProperty(ServerConstant.CATALINA_REPORT_7_URL);
        LOGGER.info("REPORT 7 URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = "";
        }
        return strResult;
    }

    public static String getReport8Url() {
        String strResult = System.getProperty(ServerConstant.CATALINA_REPORT_8_URL);
        LOGGER.info("REPORT 8 URL:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = "";
        }
        return strResult;
    }

    public static String getVipDirectory(String pathContext) {
        String strResult = System.getProperty(ServerConstant.CATALINA_VIP_DIRECTORY);
        LOGGER.info("VIP DIRECTORY:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            if (pathContext != null) {
                strResult = pathContext;
            } else {
                strResult = "";
            }
        }
        if ((strResult != null) && (!"".equals(strResult)) && (!strResult.endsWith(File.separator))) {
            strResult = strResult + File.separator;
        }
        return strResult;
    }

    public static String getRubisCodesNamesByefe() {
        String strResult = System.getProperty(ServerConstant.CATALINA_RUBIS_CODES_NAMES_BYEFE);
        LOGGER.info("RUBIS CODES NAMES BYTP:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = RUBIS_BYEFE_CODES_NAMES;
        }
        return strResult;
    }

    public static String getRubisCodesNamesBytp() {
        String strResult = System.getProperty(ServerConstant.CATALINA_RUBIS_CODES_NAMES_BYTP);
        LOGGER.info("RUBIS CODES NAMES ETDE:" + strResult);
        if ((strResult == null) || ("".equals(strResult.trim()))) {
            strResult = RUBIS_BYTP_CODES_NAMES;
        }
        return strResult;
    }
}
