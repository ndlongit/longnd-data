/**
 * WsRelationPersonneType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsRelationPersonneType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String typeRelation;

    private java.lang.String idPersonne;

    public WsRelationPersonneType() {
    }

    public WsRelationPersonneType(java.lang.String typeRelation, java.lang.String idPersonne) {
        this.typeRelation = typeRelation;
        this.idPersonne = idPersonne;
    }

    /**
     * Gets the typeRelation value for this WsRelationPersonneType.
     * 
     * @return typeRelation
     */
    public java.lang.String getTypeRelation() {
        return this.typeRelation;
    }

    /**
     * Sets the typeRelation value for this WsRelationPersonneType.
     * 
     * @param typeRelation
     */
    public void setTypeRelation(java.lang.String typeRelation) {
        this.typeRelation = typeRelation;
    }

    /**
     * Gets the idPersonne value for this WsRelationPersonneType.
     * 
     * @return idPersonne
     */
    public java.lang.String getIdPersonne() {
        return this.idPersonne;
    }

    /**
     * Sets the idPersonne value for this WsRelationPersonneType.
     * 
     * @param idPersonne
     */
    public void setIdPersonne(java.lang.String idPersonne) {
        this.idPersonne = idPersonne;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsRelationPersonneType))
            return false;
        WsRelationPersonneType other = (WsRelationPersonneType) obj;
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
                && ((this.typeRelation == null && other.getTypeRelation() == null) || (this.typeRelation != null && this.typeRelation.equals(other
                        .getTypeRelation())))
                && ((this.idPersonne == null && other.getIdPersonne() == null) || (this.idPersonne != null && this.idPersonne.equals(other
                        .getIdPersonne())));
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
        if (this.getTypeRelation() != null) {
            _hashCode += this.getTypeRelation().hashCode();
        }
        if (this.getIdPersonne() != null) {
            _hashCode += this.getIdPersonne().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsRelationPersonneType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsRelationPersonneType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeRelation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "typeRelation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPersonne");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idPersonne"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
