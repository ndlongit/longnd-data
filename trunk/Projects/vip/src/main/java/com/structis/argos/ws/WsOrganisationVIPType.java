/**
 * WsOrganisationVIPType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsOrganisationVIPType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String idArgos;

    private java.lang.String designation;

    private java.lang.String idTypeOrg;

    private java.lang.String designationTypeOrg;

    private java.lang.String idOrgParente;

    private java.lang.String designationOrgParente;

    public WsOrganisationVIPType() {
    }

    public WsOrganisationVIPType(java.lang.String idArgos, java.lang.String designation, java.lang.String idTypeOrg,
            java.lang.String designationTypeOrg, java.lang.String idOrgParente, java.lang.String designationOrgParente) {
        this.idArgos = idArgos;
        this.designation = designation;
        this.idTypeOrg = idTypeOrg;
        this.designationTypeOrg = designationTypeOrg;
        this.idOrgParente = idOrgParente;
        this.designationOrgParente = designationOrgParente;
    }

    /**
     * Gets the idArgos value for this WsOrganisationVIPType.
     * 
     * @return idArgos
     */
    public java.lang.String getIdArgos() {
        return this.idArgos;
    }

    /**
     * Sets the idArgos value for this WsOrganisationVIPType.
     * 
     * @param idArgos
     */
    public void setIdArgos(java.lang.String idArgos) {
        this.idArgos = idArgos;
    }

    /**
     * Gets the designation value for this WsOrganisationVIPType.
     * 
     * @return designation
     */
    public java.lang.String getDesignation() {
        return this.designation;
    }

    /**
     * Sets the designation value for this WsOrganisationVIPType.
     * 
     * @param designation
     */
    public void setDesignation(java.lang.String designation) {
        this.designation = designation;
    }

    /**
     * Gets the idTypeOrg value for this WsOrganisationVIPType.
     * 
     * @return idTypeOrg
     */
    public java.lang.String getIdTypeOrg() {
        return this.idTypeOrg;
    }

    /**
     * Sets the idTypeOrg value for this WsOrganisationVIPType.
     * 
     * @param idTypeOrg
     */
    public void setIdTypeOrg(java.lang.String idTypeOrg) {
        this.idTypeOrg = idTypeOrg;
    }

    /**
     * Gets the designationTypeOrg value for this WsOrganisationVIPType.
     * 
     * @return designationTypeOrg
     */
    public java.lang.String getDesignationTypeOrg() {
        return this.designationTypeOrg;
    }

    /**
     * Sets the designationTypeOrg value for this WsOrganisationVIPType.
     * 
     * @param designationTypeOrg
     */
    public void setDesignationTypeOrg(java.lang.String designationTypeOrg) {
        this.designationTypeOrg = designationTypeOrg;
    }

    /**
     * Gets the idOrgParente value for this WsOrganisationVIPType.
     * 
     * @return idOrgParente
     */
    public java.lang.String getIdOrgParente() {
        return this.idOrgParente;
    }

    /**
     * Sets the idOrgParente value for this WsOrganisationVIPType.
     * 
     * @param idOrgParente
     */
    public void setIdOrgParente(java.lang.String idOrgParente) {
        this.idOrgParente = idOrgParente;
    }

    /**
     * Gets the designationOrgParente value for this WsOrganisationVIPType.
     * 
     * @return designationOrgParente
     */
    public java.lang.String getDesignationOrgParente() {
        return this.designationOrgParente;
    }

    /**
     * Sets the designationOrgParente value for this WsOrganisationVIPType.
     * 
     * @param designationOrgParente
     */
    public void setDesignationOrgParente(java.lang.String designationOrgParente) {
        this.designationOrgParente = designationOrgParente;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsOrganisationVIPType))
            return false;
        WsOrganisationVIPType other = (WsOrganisationVIPType) obj;
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
                && ((this.idArgos == null && other.getIdArgos() == null) || (this.idArgos != null && this.idArgos.equals(other.getIdArgos())))
                && ((this.designation == null && other.getDesignation() == null) || (this.designation != null && this.designation.equals(other
                        .getDesignation())))
                && ((this.idTypeOrg == null && other.getIdTypeOrg() == null) || (this.idTypeOrg != null && this.idTypeOrg
                        .equals(other.getIdTypeOrg())))
                && ((this.designationTypeOrg == null && other.getDesignationTypeOrg() == null) || (this.designationTypeOrg != null && this.designationTypeOrg
                        .equals(other.getDesignationTypeOrg())))
                && ((this.idOrgParente == null && other.getIdOrgParente() == null) || (this.idOrgParente != null && this.idOrgParente.equals(other
                        .getIdOrgParente())))
                && ((this.designationOrgParente == null && other.getDesignationOrgParente() == null) || (this.designationOrgParente != null && this.designationOrgParente
                        .equals(other.getDesignationOrgParente())));
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
        if (this.getIdArgos() != null) {
            _hashCode += this.getIdArgos().hashCode();
        }
        if (this.getDesignation() != null) {
            _hashCode += this.getDesignation().hashCode();
        }
        if (this.getIdTypeOrg() != null) {
            _hashCode += this.getIdTypeOrg().hashCode();
        }
        if (this.getDesignationTypeOrg() != null) {
            _hashCode += this.getDesignationTypeOrg().hashCode();
        }
        if (this.getIdOrgParente() != null) {
            _hashCode += this.getIdOrgParente().hashCode();
        }
        if (this.getDesignationOrgParente() != null) {
            _hashCode += this.getDesignationOrgParente().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsOrganisationVIPType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationVIPType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("idTypeOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idTypeOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designationTypeOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designationTypeOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrgParente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idOrgParente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designationOrgParente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designationOrgParente"));
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
