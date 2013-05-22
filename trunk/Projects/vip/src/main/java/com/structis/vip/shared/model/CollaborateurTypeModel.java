package com.structis.vip.shared.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.structis.vip.client.constant.ConstantClient;

public class CollaborateurTypeModel extends BaseModelDataActivable {

    private static final long serialVersionUID = 1L;

    public static final String COT_ID = "id";
    public static final String COT_NAME = "name";
    public static final String COT_DESCRIPTION = "description";
    public static final String COT_ENTITE = "entite";
    public static final String COT_GROUP = "group";
    public static final String COT_GROUP_NAME = "groupName";
    private static final String[] group1 = { "PDG", "DG", "DGD" };
    private static final String[] group2 = { "DGA", "DP", "DR", "DE" };
    private static final String[] group3 = { "DP", "DE" }; // Gerant

    private static List<String> groupPDG_DG_DGD = new ArrayList<String>(Arrays.asList(group1));
    private static List<String> groupDGA_DP_DR_DE = new ArrayList<String>(Arrays.asList(group2));
    private static List<String> groupGerant = new ArrayList<String>(Arrays.asList(group3));

    @SuppressWarnings("unused")
    private EntiteModel entiteModel;

    @SuppressWarnings("unused")
    private DelegantTypeGroupModel typeGroup;

    @Override
    public Integer getId() {
        return this.get(COT_ID);
    }

    @Override
    public void setId(Integer id) {
        this.set(COT_ID, id);
    }

    public String getName() {
        return this.get(COT_NAME);
    }

    public void setName(String name) {
        this.set(COT_NAME, name);
    }

    public String getDescription() {
        return this.get(COT_DESCRIPTION);
    }

    public void setDescription(String description) {
        this.set(COT_DESCRIPTION, description);
    }

    public EntiteModel getEntite() {
        return this.get(COT_ENTITE);
    }

    public void setEntite(EntiteModel entite) {
        this.set(COT_ENTITE, entite);
    }

    public static boolean belongsGroupPDG_DG_DGD(String colType) {
        return groupPDG_DG_DGD.contains(colType);
    }

    public static boolean belongsGroupDGA_DP_DR_DE(String colType) {
        return groupDGA_DP_DR_DE.contains(colType);
    }

    public static boolean belongsGroupGerant(String colType) {
        return groupGerant.contains(colType);
    }

    public static boolean belongsGroupDirecteurGeneral(String colType) {
        if (colType == null)
            return false;
        return colType.indexOf("DG") >= 0;
    }

    public static boolean belongsMandataireSocial(DelegantTypeGroupModel colGroup) {
        if (colGroup == null)
            return false;
        return ConstantClient.COLLABORATEUR_TYPE_MANDATAIRE_SOCIAL.equals(colGroup.getName());
    }

    public static boolean belongsMandataireSocial(String colGroup) {
        return ConstantClient.COLLABORATEUR_TYPE_MANDATAIRE_SOCIAL.equals(colGroup);
    }

    public static boolean belongsManagerLargePerimetre(String colGroup) {
        return !ConstantClient.COLLABORATEUR_TYPE_MANDATAIRE_SOCIAL.equals(colGroup);
    }

    // public Integer getGroup() {
    // return get(COT_GROUP);
    // }
    //
    // public void setGroup(Integer group) {
    // set(COT_GROUP, group);
    // }
    public DelegantTypeGroupModel getGroup() {
        return this.get(COT_GROUP);
    }

    public void setGroup(DelegantTypeGroupModel group) {
        this.set(COT_GROUP, group);
    }

    public String getGroupName() {
        return this.get(COT_GROUP_NAME);
    }

    public void setGroupName(String groupName) {
        this.set(COT_GROUP_NAME, groupName);
    }

}
