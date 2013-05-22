/**
 * WsAdresseGroupType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsAdresseGroupType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String idObjet;

    private com.structis.argos.ws.WsAdresseType[] listeAdresse;

    public WsAdresseGroupType() {
    }

    public WsAdresseGroupType(java.lang.String idObjet, com.structis.argos.ws.WsAdresseType[] listeAdresse) {
        this.idObjet = idObjet;
        this.listeAdresse = listeAdresse;
    }

    /**
     * Gets the idObjet value for this WsAdresseGroupType.
     * 
     * @return idObjet
     */
    public java.lang.String getIdObjet() {
        return this.idObjet;
    }

    /**
     * Sets the idObjet value for this WsAdresseGroupType.
     * 
     * @param idObjet
     */
    public void setIdObjet(java.lang.String idObjet) {
        this.idObjet = idObjet;
    }

    /**
     * Gets the listeAdresse value for this WsAdresseGroupType.
     * 
     * @return listeAdresse
     */
    public com.structis.argos.ws.WsAdresseType[] getListeAdresse() {
        return this.listeAdresse;
    }

    /**
     * Sets the listeAdresse value for this WsAdresseGroupType.
     * 
     * @param listeAdresse
     */
    public void setListeAdresse(com.structis.argos.ws.WsAdresseType[] listeAdresse) {
        this.listeAdresse = listeAdresse;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsAdresseGroupType))
            return false;
        WsAdresseGroupType other = (WsAdresseGroupType) obj;
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
                && ((this.idObjet == null && other.getIdObjet() == null) || (this.idObjet != null && this.idObjet.equals(other.getIdObjet())))
                && ((this.listeAdresse == null && other.getListeAdresse() == null) || (this.listeAdresse != null && java.util.Arrays.equals(
                        this.listeAdresse, other.getListeAdresse())));
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
        if (this.getIdObjet() != null) {
            _hashCode += this.getIdObjet().hashCode();
        }
        if (this.getListeAdresse() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeAdresse()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeAdresse(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsAdresseGroupType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsAdresseGroupType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idObjet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idObjet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeAdresse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeAdresse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsAdresseType"));
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
