/**
 * WsArgosPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public interface WsArgosPort extends java.rmi.Remote {

    public java.lang.String creerOrganisation(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsArbreOrganisationResultType getArbreOrganisation(com.structis.argos.ws.WsParametreEntree[] in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsArbreOrganisationResultType getArbreOrganisationByString(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsArbreOrganisationResultSimplifiedType getArbreOrganisationByStringSimplified(java.lang.String in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsAvisCoupVentResultType getAvisCoupVent(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getChantierById(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationSimplifiedResultType getChantierByIdSimplified(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsAdresseGroupResultType getListAdresseChantierByIds(java.lang.String in0, java.lang.String in1)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneLightListResultType getListAdminCommunaute() throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneLightListResultType getListAdminMetier() throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getListChantierByIds(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getListChantier(com.structis.argos.ws.WsParametreEntree[] in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getListChantierByString(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationSimplifiedResultType getListChantierByStringSimplified(java.lang.String in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getListOrganisation(com.structis.argos.ws.WsParametreEntree[] in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationVIPResultType getListVIPETDE(com.structis.argos.ws.WsParametreVIPETDE in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getListOrganisationByString(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationSimplifiedResultType getListOrganisationSimplifiedByString(java.lang.String in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getListOrganisationEmployeur() throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneLightListResultType getListPersonne(com.structis.argos.ws.WsParametreEntree[] in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneResultType getListPersonneByIds(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneLightListResultType getListPersonneByString(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsCentresInteretsResultType getListCentresInterets() throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsCentresInteretsResultType getListCentresInteretsByTopic(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneBCUListResultType getListPersonneBCUByString(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneSimplifiedListResultType getListPersonneSimplifiedByString(java.lang.String in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsAdresseGroupResultType getListAdressePersonneByIds(java.lang.String in0, java.lang.String in1)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneLightListResultType getListRapprohementRubis(java.lang.String in0, java.lang.String in1,
            java.lang.String in2) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsSiteLightResultType getListSite(com.structis.argos.ws.WsParametreEntree[] in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsSiteLightResultType getListSiteByString(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getOrganisationById(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationSimplifiedResultType getOrganisationByIdSimplified(java.lang.String in0)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneResultType getPersonneById(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsSiteLightResultType getSiteById(java.lang.String in0) throws java.rmi.RemoteException;

    public java.lang.String lierOrganisation(java.lang.String in0) throws java.rmi.RemoteException;

    public java.lang.String setImpPersonne(java.lang.String in0) throws java.rmi.RemoteException;

    public java.lang.String getChantiersByOrganisation(java.lang.String in0, java.lang.String in1, java.lang.String in2)
            throws java.rmi.RemoteException;

    public java.lang.String getBuildingsByOrganisation(java.lang.String in0, int in1) throws java.rmi.RemoteException;

    public java.lang.String connect(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;

    public java.lang.String createLink(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4,
            java.lang.String in5) throws java.rmi.RemoteException;

    public java.lang.String updatePersonIS(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
            throws java.rmi.RemoteException;

    public java.lang.String getListPersonneCtlAD(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;

    public java.lang.String getToutePersonne(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;

    public java.lang.String createPerson(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
            throws java.rmi.RemoteException;

    public java.lang.String updatePersonIdentity(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
            java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException;

    public java.lang.String updatePersonInformations(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
            java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException;

    public java.lang.String deactivatePerson(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;

    public java.lang.String getLinkType(java.lang.String in0) throws java.rmi.RemoteException;

    public java.lang.String updateLink(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4,
            java.lang.String in5) throws java.rmi.RemoteException;

    public java.lang.String deleteLink(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;

    public java.lang.String getLink(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;

    public java.lang.String createAddress(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
            java.lang.String in4, java.lang.String in5, java.lang.String in6, java.lang.String in7, java.lang.String in8, java.lang.String in9,
            java.lang.String in10) throws java.rmi.RemoteException;

    public java.lang.String updateAddress(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
            java.lang.String in4, java.lang.String in5, java.lang.String in6, java.lang.String in7, java.lang.String in8, java.lang.String in9,
            java.lang.String in10, java.lang.String in11) throws java.rmi.RemoteException;

    public java.lang.String deleteAddress(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;

    public java.lang.String getAddress(java.lang.String in0) throws java.rmi.RemoteException;

    public java.lang.String getCountry(java.lang.String in0) throws java.rmi.RemoteException;

    public java.lang.String createAssignment(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
            throws java.rmi.RemoteException;

    public java.lang.String updateAssignment(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
            throws java.rmi.RemoteException;

    public java.lang.String deleteAssignment(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;

    public java.lang.String getAssignment(java.lang.String in0) throws java.rmi.RemoteException;

    public java.lang.String getSite(java.lang.String in0) throws java.rmi.RemoteException;

    public java.lang.String createSite(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4)
            throws java.rmi.RemoteException;

    public java.lang.String updateSite(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4)
            throws java.rmi.RemoteException;

    public java.lang.String deleteSite(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getNouveauxChantiersByUO(java.lang.String in0) throws java.rmi.RemoteException;

    public java.lang.String updatePersonISbyCtlAD(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3,
            java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException;

    public java.lang.String updatePersonIDBYCN(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3)
            throws java.rmi.RemoteException;

    public java.lang.String getListeRelationPersonne(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationGeoResultSet getListeOrganisationByCritere(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsArbreOrganisationType[] getListeOrganisationFille(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsPersonneMissionType[] getListePersonneMission(java.lang.String in0) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsAdresseResultType rechercheAdresses(java.lang.String idobject, java.lang.String lang)
            throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsLienResultType rechercheLiens(java.lang.String idobject, java.lang.String lang) throws java.rmi.RemoteException;

    public com.structis.argos.ws.WsOrganisationLightResultType getSimpleListOrganisationByString(java.lang.String in0)
            throws java.rmi.RemoteException;

    public java.lang.String createXMLFileForADTelecomUpdate(java.lang.String filepath, java.lang.String filename, java.lang.String datedebut)
            throws java.rmi.RemoteException;
}
