/**
 * WsOrganisationSimplifiedType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsOrganisationSimplifiedType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String url;

    private com.structis.argos.ws.WsOrganisationRelationType[] listeOrganisationRelation;

    private java.lang.String idArgos;

    private java.lang.String designation;

    private java.lang.String alias1;

    private java.lang.String nature;

    private java.lang.String abreviation;

    public WsOrganisationSimplifiedType() {
    }

    public WsOrganisationSimplifiedType(java.lang.String url, com.structis.argos.ws.WsOrganisationRelationType[] listeOrganisationRelation,
            java.lang.String idArgos, java.lang.String designation, java.lang.String alias1, java.lang.String nature, java.lang.String abreviation) {
        this.url = url;
        this.listeOrganisationRelation = listeOrganisationRelation;
        this.idArgos = idArgos;
        this.designation = designation;
        this.alias1 = alias1;
        this.nature = nature;
        this.abreviation = abreviation;
    }

    /**
     * Gets the url value for this WsOrganisationSimplifiedType.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return this.url;
    }

    /**
     * Sets the url value for this WsOrganisationSimplifiedType.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }

    /**
     * Gets the listeOrganisationRelation value for this WsOrganisationSimplifiedType.
     * 
     * @return listeOrganisationRelation
     */
    public com.structis.argos.ws.WsOrganisationRelationType[] getListeOrganisationRelation() {
        return this.listeOrganisationRelation;
    }

    /**
     * Sets the listeOrganisationRelation value for this WsOrganisationSimplifiedType.
     * 
     * @param listeOrganisationRelation
     */
    public void setListeOrganisationRelation(com.structis.argos.ws.WsOrganisationRelationType[] listeOrganisationRelation) {
        this.listeOrganisationRelation = listeOrganisationRelation;
    }

    /**
     * Gets the idArgos value for this WsOrganisationSimplifiedType.
     * 
     * @return idArgos
     */
    public java.lang.String getIdArgos() {
        return this.idArgos;
    }

    /**
     * Sets the idArgos value for this WsOrganisationSimplifiedType.
     * 
     * @param idArgos
     */
    public void setIdArgos(java.lang.String idArgos) {
        this.idArgos = idArgos;
    }

    /**
     * Gets the designation value for this WsOrganisationSimplifiedType.
     * 
     * @return designation
     */
    public java.lang.String getDesignation() {
        return this.designation;
    }

    /**
     * Sets the designation value for this WsOrganisationSimplifiedType.
     * 
     * @param designation
     */
    public void setDesignation(java.lang.String designation) {
        this.designation = designation;
    }

    /**
     * Gets the alias1 value for this WsOrganisationSimplifiedType.
     * 
     * @return alias1
     */
    public java.lang.String getAlias1() {
        return this.alias1;
    }

    /**
     * Sets the alias1 value for this WsOrganisationSimplifiedType.
     * 
     * @param alias1
     */
    public void setAlias1(java.lang.String alias1) {
        this.alias1 = alias1;
    }

    /**
     * Gets the nature value for this WsOrganisationSimplifiedType.
     * 
     * @return nature
     */
    public java.lang.String getNature() {
        return this.nature;
    }

    /**
     * Sets the nature value for this WsOrganisationSimplifiedType.
     * 
     * @param nature
     */
    public void setNature(java.lang.String nature) {
        this.nature = nature;
    }

    /**
     * Gets the abreviation value for this WsOrganisationSimplifiedType.
     * 
     * @return abreviation
     */
    public java.lang.String getAbreviation() {
        return this.abreviation;
    }

    /**
     * Sets the abreviation value for this WsOrganisationSimplifiedType.
     * 
     * @param abreviation
     */
    public void setAbreviation(java.lang.String abreviation) {
        this.abreviation = abreviation;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsOrganisationSimplifiedType))
            return false;
        WsOrganisationSimplifiedType other = (WsOrganisationSimplifiedType) obj;
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
                && ((this.url == null && other.getUrl() == null) || (this.url != null && this.url.equals(other.getUrl())))
                && ((this.listeOrganisationRelation == null && other.getListeOrganisationRelation() == null) || (this.listeOrganisationRelation != null && java.util.Arrays
                        .equals(this.listeOrganisationRelation, other.getListeOrganisationRelation())))
                && ((this.idArgos == null && other.getIdArgos() == null) || (this.idArgos != null && this.idArgos.equals(other.getIdArgos())))
                && ((this.designation == null && other.getDesignation() == null) || (this.designation != null && this.designation.equals(other
                        .getDesignation())))
                && ((this.alias1 == null && other.getAlias1() == null) || (this.alias1 != null && this.alias1.equals(other.getAlias1())))
                && ((this.nature == null && other.getNature() == null) || (this.nature != null && this.nature.equals(other.getNature())))
                && ((this.abreviation == null && other.getAbreviation() == null) || (this.abreviation != null && this.abreviation.equals(other
                        .getAbreviation())));
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
        if (this.getUrl() != null) {
            _hashCode += this.getUrl().hashCode();
        }
        if (this.getListeOrganisationRelation() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeOrganisationRelation()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeOrganisationRelation(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (this.getIdArgos() != null) {
            _hashCode += this.getIdArgos().hashCode();
        }
        if (this.getDesignation() != null) {
            _hashCode += this.getDesignation().hashCode();
        }
        if (this.getAlias1() != null) {
            _hashCode += this.getAlias1().hashCode();
        }
        if (this.getNature() != null) {
            _hashCode += this.getNature().hashCode();
        }
        if (this.getAbreviation() != null) {
            _hashCode += this.getAbreviation().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsOrganisationSimplifiedType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationSimplifiedType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeOrganisationRelation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeOrganisationRelation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationRelationType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idArgos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idArgos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alias1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alias1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nature");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abreviation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abreviation"));
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
