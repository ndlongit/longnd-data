/**
 * WsCommunautePersonneType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsCommunautePersonneType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String fonction;

    private java.lang.String idOrganisation;

    private java.lang.String organisation;

    public WsCommunautePersonneType() {
    }

    public WsCommunautePersonneType(java.lang.String fonction, java.lang.String idOrganisation, java.lang.String organisation) {
        this.fonction = fonction;
        this.idOrganisation = idOrganisation;
        this.organisation = organisation;
    }

    /**
     * Gets the fonction value for this WsCommunautePersonneType.
     * 
     * @return fonction
     */
    public java.lang.String getFonction() {
        return this.fonction;
    }

    /**
     * Sets the fonction value for this WsCommunautePersonneType.
     * 
     * @param fonction
     */
    public void setFonction(java.lang.String fonction) {
        this.fonction = fonction;
    }

    /**
     * Gets the idOrganisation value for this WsCommunautePersonneType.
     * 
     * @return idOrganisation
     */
    public java.lang.String getIdOrganisation() {
        return this.idOrganisation;
    }

    /**
     * Sets the idOrganisation value for this WsCommunautePersonneType.
     * 
     * @param idOrganisation
     */
    public void setIdOrganisation(java.lang.String idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    /**
     * Gets the organisation value for this WsCommunautePersonneType.
     * 
     * @return organisation
     */
    public java.lang.String getOrganisation() {
        return this.organisation;
    }

    /**
     * Sets the organisation value for this WsCommunautePersonneType.
     * 
     * @param organisation
     */
    public void setOrganisation(java.lang.String organisation) {
        this.organisation = organisation;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsCommunautePersonneType))
            return false;
        WsCommunautePersonneType other = (WsCommunautePersonneType) obj;
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
                && ((this.fonction == null && other.getFonction() == null) || (this.fonction != null && this.fonction.equals(other.getFonction())))
                && ((this.idOrganisation == null && other.getIdOrganisation() == null) || (this.idOrganisation != null && this.idOrganisation
                        .equals(other.getIdOrganisation())))
                && ((this.organisation == null && other.getOrganisation() == null) || (this.organisation != null && this.organisation.equals(other
                        .getOrganisation())));
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
        if (this.getFonction() != null) {
            _hashCode += this.getFonction().hashCode();
        }
        if (this.getIdOrganisation() != null) {
            _hashCode += this.getIdOrganisation().hashCode();
        }
        if (this.getOrganisation() != null) {
            _hashCode += this.getOrganisation().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsCommunautePersonneType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsCommunautePersonneType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fonction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fonction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idOrganisation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "organisation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
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
