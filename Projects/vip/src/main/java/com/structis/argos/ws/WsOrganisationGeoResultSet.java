/**
 * WsOrganisationGeoResultSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsOrganisationGeoResultSet implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String messageErreur;

    private int codeRetour;

    private com.structis.argos.ws.WsOrganisationGeoType[] listeRetour;

    public WsOrganisationGeoResultSet() {
    }

    public WsOrganisationGeoResultSet(java.lang.String messageErreur, int codeRetour, com.structis.argos.ws.WsOrganisationGeoType[] listeRetour) {
        this.messageErreur = messageErreur;
        this.codeRetour = codeRetour;
        this.listeRetour = listeRetour;
    }

    /**
     * Gets the messageErreur value for this WsOrganisationGeoResultSet.
     * 
     * @return messageErreur
     */
    public java.lang.String getMessageErreur() {
        return this.messageErreur;
    }

    /**
     * Sets the messageErreur value for this WsOrganisationGeoResultSet.
     * 
     * @param messageErreur
     */
    public void setMessageErreur(java.lang.String messageErreur) {
        this.messageErreur = messageErreur;
    }

    /**
     * Gets the codeRetour value for this WsOrganisationGeoResultSet.
     * 
     * @return codeRetour
     */
    public int getCodeRetour() {
        return this.codeRetour;
    }

    /**
     * Sets the codeRetour value for this WsOrganisationGeoResultSet.
     * 
     * @param codeRetour
     */
    public void setCodeRetour(int codeRetour) {
        this.codeRetour = codeRetour;
    }

    /**
     * Gets the listeRetour value for this WsOrganisationGeoResultSet.
     * 
     * @return listeRetour
     */
    public com.structis.argos.ws.WsOrganisationGeoType[] getListeRetour() {
        return this.listeRetour;
    }

    /**
     * Sets the listeRetour value for this WsOrganisationGeoResultSet.
     * 
     * @param listeRetour
     */
    public void setListeRetour(com.structis.argos.ws.WsOrganisationGeoType[] listeRetour) {
        this.listeRetour = listeRetour;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsOrganisationGeoResultSet))
            return false;
        WsOrganisationGeoResultSet other = (WsOrganisationGeoResultSet) obj;
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (this.__equalsCalc != null) {
            return (this.__equalsCalc == obj);
        }
        this.__equalsCalc = obj;
        boolean _equals;
        _equals = true
                && ((this.messageErreur == null && other.getMessageErreur() == null) || (this.messageErreur != null && this.messageErreur
                        .equals(other.getMessageErreur())))
                && this.codeRetour == other.getCodeRetour()
                && ((this.listeRetour == null && other.getListeRetour() == null) || (this.listeRetour != null && java.util.Arrays.equals(
                        this.listeRetour, other.getListeRetour())));
        this.__equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;

    @Override
    public synchronized int hashCode() {
        if (this.__hashCodeCalc) {
            return 0;
        }
        this.__hashCodeCalc = true;
        int _hashCode = 1;
        if (this.getMessageErreur() != null) {
            _hashCode += this.getMessageErreur().hashCode();
        }
        _hashCode += this.getCodeRetour();
        if (this.getListeRetour() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeRetour()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeRetour(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsOrganisationGeoResultSet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationGeoResultSet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageErreur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageErreur"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeRetour");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codeRetour"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeRetour");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeRetour"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationGeoType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType, java.lang.Class _javaType,
            javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType, java.lang.Class _javaType,
            javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

}
