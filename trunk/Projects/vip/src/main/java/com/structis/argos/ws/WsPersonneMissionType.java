/**
 * WsPersonneMissionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsPersonneMissionType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int priorite;

    private java.lang.String idOrganisation;

    private java.lang.String fonction;

    private java.lang.String organisation;

    private java.lang.String idPersonne;

    private java.lang.String idPersonneCourt;

    public WsPersonneMissionType() {
    }

    public WsPersonneMissionType(int priorite, java.lang.String idOrganisation, java.lang.String fonction, java.lang.String organisation,
            java.lang.String idPersonne, java.lang.String idPersonneCourt) {
        this.priorite = priorite;
        this.idOrganisation = idOrganisation;
        this.fonction = fonction;
        this.organisation = organisation;
        this.idPersonne = idPersonne;
        this.idPersonneCourt = idPersonneCourt;
    }

    /**
     * Gets the priorite value for this WsPersonneMissionType.
     * 
     * @return priorite
     */
    public int getPriorite() {
        return this.priorite;
    }

    /**
     * Sets the priorite value for this WsPersonneMissionType.
     * 
     * @param priorite
     */
    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    /**
     * Gets the idOrganisation value for this WsPersonneMissionType.
     * 
     * @return idOrganisation
     */
    public java.lang.String getIdOrganisation() {
        return this.idOrganisation;
    }

    /**
     * Sets the idOrganisation value for this WsPersonneMissionType.
     * 
     * @param idOrganisation
     */
    public void setIdOrganisation(java.lang.String idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    /**
     * Gets the fonction value for this WsPersonneMissionType.
     * 
     * @return fonction
     */
    public java.lang.String getFonction() {
        return this.fonction;
    }

    /**
     * Sets the fonction value for this WsPersonneMissionType.
     * 
     * @param fonction
     */
    public void setFonction(java.lang.String fonction) {
        this.fonction = fonction;
    }

    /**
     * Gets the organisation value for this WsPersonneMissionType.
     * 
     * @return organisation
     */
    public java.lang.String getOrganisation() {
        return this.organisation;
    }

    /**
     * Sets the organisation value for this WsPersonneMissionType.
     * 
     * @param organisation
     */
    public void setOrganisation(java.lang.String organisation) {
        this.organisation = organisation;
    }

    /**
     * Gets the idPersonne value for this WsPersonneMissionType.
     * 
     * @return idPersonne
     */
    public java.lang.String getIdPersonne() {
        return this.idPersonne;
    }

    /**
     * Sets the idPersonne value for this WsPersonneMissionType.
     * 
     * @param idPersonne
     */
    public void setIdPersonne(java.lang.String idPersonne) {
        this.idPersonne = idPersonne;
    }

    /**
     * Gets the idPersonneCourt value for this WsPersonneMissionType.
     * 
     * @return idPersonneCourt
     */
    public java.lang.String getIdPersonneCourt() {
        return this.idPersonneCourt;
    }

    /**
     * Sets the idPersonneCourt value for this WsPersonneMissionType.
     * 
     * @param idPersonneCourt
     */
    public void setIdPersonneCourt(java.lang.String idPersonneCourt) {
        this.idPersonneCourt = idPersonneCourt;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsPersonneMissionType))
            return false;
        WsPersonneMissionType other = (WsPersonneMissionType) obj;
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
                && this.priorite == other.getPriorite()
                && ((this.idOrganisation == null && other.getIdOrganisation() == null) || (this.idOrganisation != null && this.idOrganisation
                        .equals(other.getIdOrganisation())))
                && ((this.fonction == null && other.getFonction() == null) || (this.fonction != null && this.fonction.equals(other.getFonction())))
                && ((this.organisation == null && other.getOrganisation() == null) || (this.organisation != null && this.organisation.equals(other
                        .getOrganisation())))
                && ((this.idPersonne == null && other.getIdPersonne() == null) || (this.idPersonne != null && this.idPersonne.equals(other
                        .getIdPersonne())))
                && ((this.idPersonneCourt == null && other.getIdPersonneCourt() == null) || (this.idPersonneCourt != null && this.idPersonneCourt
                        .equals(other.getIdPersonneCourt())));
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
        _hashCode += this.getPriorite();
        if (this.getIdOrganisation() != null) {
            _hashCode += this.getIdOrganisation().hashCode();
        }
        if (this.getFonction() != null) {
            _hashCode += this.getFonction().hashCode();
        }
        if (this.getOrganisation() != null) {
            _hashCode += this.getOrganisation().hashCode();
        }
        if (this.getIdPersonne() != null) {
            _hashCode += this.getIdPersonne().hashCode();
        }
        if (this.getIdPersonneCourt() != null) {
            _hashCode += this.getIdPersonneCourt().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsPersonneMissionType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsPersonneMissionType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priorite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idOrganisation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fonction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fonction"));
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
        elemField.setFieldName("idPersonne");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idPersonne"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPersonneCourt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idPersonneCourt"));
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
