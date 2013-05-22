/**
 * WsOrganisationRelationType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsOrganisationRelationType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String ci;

    private java.lang.String organisation;

    private java.lang.String idOrganisation;

    public WsOrganisationRelationType() {
    }

    public WsOrganisationRelationType(java.lang.String ci, java.lang.String organisation, java.lang.String idOrganisation) {
        this.ci = ci;
        this.organisation = organisation;
        this.idOrganisation = idOrganisation;
    }

    /**
     * Gets the ci value for this WsOrganisationRelationType.
     * 
     * @return ci
     */
    public java.lang.String getCi() {
        return this.ci;
    }

    /**
     * Sets the ci value for this WsOrganisationRelationType.
     * 
     * @param ci
     */
    public void setCi(java.lang.String ci) {
        this.ci = ci;
    }

    /**
     * Gets the organisation value for this WsOrganisationRelationType.
     * 
     * @return organisation
     */
    public java.lang.String getOrganisation() {
        return this.organisation;
    }

    /**
     * Sets the organisation value for this WsOrganisationRelationType.
     * 
     * @param organisation
     */
    public void setOrganisation(java.lang.String organisation) {
        this.organisation = organisation;
    }

    /**
     * Gets the idOrganisation value for this WsOrganisationRelationType.
     * 
     * @return idOrganisation
     */
    public java.lang.String getIdOrganisation() {
        return this.idOrganisation;
    }

    /**
     * Sets the idOrganisation value for this WsOrganisationRelationType.
     * 
     * @param idOrganisation
     */
    public void setIdOrganisation(java.lang.String idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsOrganisationRelationType))
            return false;
        WsOrganisationRelationType other = (WsOrganisationRelationType) obj;
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
                && ((this.ci == null && other.getCi() == null) || (this.ci != null && this.ci.equals(other.getCi())))
                && ((this.organisation == null && other.getOrganisation() == null) || (this.organisation != null && this.organisation.equals(other
                        .getOrganisation())))
                && ((this.idOrganisation == null && other.getIdOrganisation() == null) || (this.idOrganisation != null && this.idOrganisation
                        .equals(other.getIdOrganisation())));
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
        if (this.getCi() != null) {
            _hashCode += this.getCi().hashCode();
        }
        if (this.getOrganisation() != null) {
            _hashCode += this.getOrganisation().hashCode();
        }
        if (this.getIdOrganisation() != null) {
            _hashCode += this.getIdOrganisation().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsOrganisationRelationType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationRelationType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ci");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ci"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "organisation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idOrganisation"));
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
