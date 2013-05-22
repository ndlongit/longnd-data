package com.structis.vip.client.util;

import java.util.HashMap;

import com.structis.vip.shared.model.DelegationModel;

public class ConstantsDelegationFields {

    public final static String DEL_ID = "del_id";
    public final static String DEL_PARENT_ID = "del_parent_id";
    public final static String DTY_ID = "dty_id";
    public final static String DEL_STARTDATE = "del_startDate";
    public final static String DEL_ENDDATE = "del_endDate";
    public final static String DEL_ISSIGNED = "del_isSigned";
    public final static String DEL_DELEGANT_ID = "del_delegant_id";

    public final static String DEL_DEMANDEURFIRSTNAME = "del_demandeurFirstname";
    public final static String DEL_DEMANDEURLASTNAME = "del_demandeurLastname";
    public final static String DEL_DELEGANTFIRSTNAME = "del_delegantFirstname";
    public final static String DEL_DELEGANTLASTNAME = "del_delegantLastname";
    public final static String DEL_DELEGATAIRE_ID = "del_delegataire_id";
    public final static String DEL_DELEGATAIREFIRSTNAME = "del_delegataireFirstname";
    public final static String DEL_DELEGATAIRELASTNAME = "del_delegataireLastname";
    public final static String DEL_DELEGATIONCONJOINTE = "del_delegationConjointe";
    public final static String DEL_DATE1 = "del_date1";
    public final static String DEL_PLACE1 = "del_place1";
    public final static String DEL_DATE2 = "del_date2";
    public final static String DEL_PLACE2 = "del_place2";
    public final static String DEL_DATE3 = "del_date3";
    public final static String DEL_PLACE3 = "del_place3";

    public final static String DEL_AMOUNT1 = "del_amount1";
    public final static String DEL_AMOUNT2 = "del_amount2";
    public final static String DEL_AMOUNT3 = "del_amount3";
    public final static String DEL_AMOUNT4 = "del_amount4";
    public final static String DEL_COMMENT1 = "del_comment1";
    public final static String DEL_COMMENT2 = "del_comment2";
    public final static String DST_ID = "dst_id";
    public final static String DNA_ID = "dna_id";
    public final static String PER_ID = "per_id";
    public final static String ETJ_ID = "etj_id";
    public final static String DEL_ETJ_ID = "del_etj_id";
    public final static String DEL_ETJ_NAME = "del_etj_name";
    public final static String DEL_ETJ_CAPITAL = "del_etj_capital";
    public final static String DEL_ETJ_REGISTRATIONID = "del_etj_registrationId";
    public final static String DEL_ETJREGISTRATIONADDRESS = "del_etj_registrationAddress";
    public final static String DEL_DELEGATAIREQUALITE = "del_delegataireQualite";
    public final static String DEL_DELEGANTQUALITE = "del_delegantQualite";
    public final static String DEL_PER_CHANTIERNAME = "del_per_chantierName";
    public final static String DEL_PER_CHANTIERID = "del_per_chantierID";
    public final static String DEL_PER_CHANTIERSTARTDATE = "del_per_chantierStartDate";
    public final static String DEL_PER_CHANTIERPLANNEDENDDATE = "del_per_chantierPlannedEndDate";
    public final static String DEL_PER_CHANTIERENDDATE = "del_per_chantierEndDate";
    public final static String DEL_PARTYDELEGEE = "del_partyDelegee";
    public final static String DEL_DEMANDEUR_ID = "del_demandeur_id";
    public final static String DEL_AMOUNT5 = "del_amount5";

    /*
     * public final static HashMap<String, Method> mapValue = new HashMap<String, Method>(); static { try { Integer[] input={new Integer(2),new
     * Integer(6)}; Class delegationModelClass = Class.forName("DelegationModel"); Class[] parametor = new Class[1]; parametor[0]=Integer.TYPE;
     * mapValue.put(DEL_ID, delegationModelClass.getMethod("getId",parametor)); Method method = mapValue.get(DEL_ID); String output =
     * (String)method.invoke(new DelegationModel(), input); } catch (Exception e) { e.printStackTrace(); } }
     */

    public final static String[] columnID = new String[] { DEL_ID, DEL_PARENT_ID, DTY_ID, DEL_STARTDATE, DEL_ENDDATE, DEL_ISSIGNED, DEL_DELEGANT_ID };
    public final static HashMap<String, Integer> map = new HashMap<String, Integer>();

    static {
        map.put(DEL_ID, 1);
        map.put(DEL_PARENT_ID, 2);
        map.put(DTY_ID, 3);
        map.put(DEL_STARTDATE, 4);
        map.put(DEL_ENDDATE, 5);
        map.put(DEL_ISSIGNED, 6);
        map.put(DEL_DELEGANT_ID, 7);
    }

    public static void printDocument(String filePath, DelegationModel model) {
    }

    public static String getValue(Integer columnId, DelegationModel model) {
        switch (columnId) {
        case 1:
            return model.getId().toString();
        case 2:
            return model.getParent().getId().toString();
        case 3:
            return model.getDelegationType().getId().toString();
        case 4:
            return model.getStartDate().toString();
        case 5:
            return model.getEndDate().toString();
        case 6:
            return model.getIsSigned().toString();
        case 7:
            return model.getDelegant().getId().toString();
        }
        return null;
    }
}
