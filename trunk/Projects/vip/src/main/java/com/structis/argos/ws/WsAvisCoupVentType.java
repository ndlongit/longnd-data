/**
 * WsAvisCoupVentType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsAvisCoupVentType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String ci;

    private java.lang.String designationChantier;

    private java.lang.String designationOrganisation;

    private java.lang.String designationOrganisationEntiteMere;

    private java.lang.String idOrganisation;

    private java.lang.String lien;

    private java.lang.String typeLien;

    private java.lang.String typeOrganisation;

    private java.lang.String idPersonne;

    public WsAvisCoupVentType() {
    }

    public WsAvisCoupVentType(java.lang.String ci, java.lang.String designationChantier, java.lang.String designationOrganisation,
            java.lang.String designationOrganisationEntiteMere, java.lang.String idOrganisation, java.lang.String lien, java.lang.String typeLien,
            java.lang.String typeOrganisation, java.lang.String idPersonne) {
        this.ci = ci;
        this.designationChantier = designationChantier;
        this.designationOrganisation = designationOrganisation;
        this.designationOrganisationEntiteMere = designationOrganisationEntiteMere;
        this.idOrganisation = idOrganisation;
        this.lien = lien;
        this.typeLien = typeLien;
        this.typeOrganisation = typeOrganisation;
        this.idPersonne = idPersonne;
    }

    /**
     * Gets the ci value for this WsAvisCoupVentType.
     * 
     * @return ci
     */
    public java.lang.String getCi() {
        return this.ci;
    }

    /**
     * Sets the ci value for this WsAvisCoupVentType.
     * 
     * @param ci
     */
    public void setCi(java.lang.String ci) {
        this.ci = ci;
    }

    /**
     * Gets the designationChantier value for this WsAvisCoupVentType.
     * 
     * @return designationChantier
     */
    public java.lang.String getDesignationChantier() {
        return this.designationChantier;
    }

    /**
     * Sets the designationChantier value for this WsAvisCoupVentType.
     * 
     * @param designationChantier
     */
    public void setDesignationChantier(java.lang.String designationChantier) {
        this.designationChantier = designationChantier;
    }

    /**
     * Gets the designationOrganisation value for this WsAvisCoupVentType.
     * 
     * @return designationOrganisation
     */
    public java.lang.String getDesignationOrganisation() {
        return this.designationOrganisation;
    }

    /**
     * Sets the designationOrganisation value for this WsAvisCoupVentType.
     * 
     * @param designationOrganisation
     */
    public void setDesignationOrganisation(java.lang.String designationOrganisation) {
        this.designationOrganisation = designationOrganisation;
    }

    /**
     * Gets the designationOrganisationEntiteMere value for this WsAvisCoupVentType.
     * 
     * @return designationOrganisationEntiteMere
     */
    public java.lang.String getDesignationOrganisationEntiteMere() {
        return this.designationOrganisationEntiteMere;
    }

    /**
     * Sets the designationOrganisationEntiteMere value for this WsAvisCoupVentType.
     * 
     * @param designationOrganisationEntiteMere
     */
    public void setDesignationOrganisationEntiteMere(java.lang.String designationOrganisationEntiteMere) {
        this.designationOrganisationEntiteMere = designationOrganisationEntiteMere;
    }

    /**
     * Gets the idOrganisation value for this WsAvisCoupVentType.
     * 
     * @return idOrganisation
     */
    public java.lang.String getIdOrganisation() {
        return this.idOrganisation;
    }

    /**
     * Sets the idOrganisation value for this WsAvisCoupVentType.
     * 
     * @param idOrganisation
     */
    public void setIdOrganisation(java.lang.String idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    /**
     * Gets the lien value for this WsAvisCoupVentType.
     * 
     * @return lien
     */
    public java.lang.String getLien() {
        return this.lien;
    }

    /**
     * Sets the lien value for this WsAvisCoupVentType.
     * 
     * @param lien
     */
    public void setLien(java.lang.String lien) {
        this.lien = lien;
    }

    /**
     * Gets the typeLien value for this WsAvisCoupVentType.
     * 
     * @return typeLien
     */
    public java.lang.String getTypeLien() {
        return this.typeLien;
    }

    /**
     * Sets the typeLien value for this WsAvisCoupVentType.
     * 
     * @param typeLien
     */
    public void setTypeLien(java.lang.String typeLien) {
        this.typeLien = typeLien;
    }

    /**
     * Gets the typeOrganisation value for this WsAvisCoupVentType.
     * 
     * @return typeOrganisation
     */
    public java.lang.String getTypeOrganisation() {
        return this.typeOrganisation;
    }

    /**
     * Sets the typeOrganisation value for this WsAvisCoupVentType.
     * 
     * @param typeOrganisation
     */
    public void setTypeOrganisation(java.lang.String typeOrganisation) {
        this.typeOrganisation = typeOrganisation;
    }

    /**
     * Gets the idPersonne value for this WsAvisCoupVentType.
     * 
     * @return idPersonne
     */
    public java.lang.String getIdPersonne() {
        return this.idPersonne;
    }

    /**
     * Sets the idPersonne value for this WsAvisCoupVentType.
     * 
     * @param idPersonne
     */
    public void setIdPersonne(java.lang.String idPersonne) {
        this.idPersonne = idPersonne;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsAvisCoupVentType))
            return false;
        WsAvisCoupVentType other = (WsAvisCoupVentType) obj;
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
                && ((this.designationChantier == null && other.getDesignationChantier() == null) || (this.designationChantier != null && this.designationChantier
                        .equals(other.getDesignationChantier())))
                && ((this.designationOrganisation == null && other.getDesignationOrganisation() == null) || (this.designationOrganisation != null && this.designationOrganisation
                        .equals(other.getDesignationOrganisation())))
                && ((this.designationOrganisationEntiteMere == null && other.getDesignationOrganisationEntiteMere() == null) || (this.designationOrganisationEntiteMere != null && this.designationOrganisationEntiteMere
                        .equals(other.getDesignationOrganisationEntiteMere())))
                && ((this.idOrganisation == null && other.getIdOrganisation() == null) || (this.idOrganisation != null && this.idOrganisation
                        .equals(other.getIdOrganisation())))
                && ((this.lien == null && other.getLien() == null) || (this.lien != null && this.lien.equals(other.getLien())))
                && ((this.typeLien == null && other.getTypeLien() == null) || (this.typeLien != null && this.typeLien.equals(other.getTypeLien())))
                && ((this.typeOrganisation == null && other.getTypeOrganisation() == null) || (this.typeOrganisation != null && this.typeOrganisation
                        .equals(other.getTypeOrganisation())))
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
        if (this.getCi() != null) {
            _hashCode += this.getCi().hashCode();
        }
        if (this.getDesignationChantier() != null) {
            _hashCode += this.getDesignationChantier().hashCode();
        }
        if (this.getDesignationOrganisation() != null) {
            _hashCode += this.getDesignationOrganisation().hashCode();
        }
        if (this.getDesignationOrganisationEntiteMere() != null) {
            _hashCode += this.getDesignationOrganisationEntiteMere().hashCode();
        }
        if (this.getIdOrganisation() != null) {
            _hashCode += this.getIdOrganisation().hashCode();
        }
        if (this.getLien() != null) {
            _hashCode += this.getLien().hashCode();
        }
        if (this.getTypeLien() != null) {
            _hashCode += this.getTypeLien().hashCode();
        }
        if (this.getTypeOrganisation() != null) {
            _hashCode += this.getTypeOrganisation().hashCode();
        }
        if (this.getIdPersonne() != null) {
            _hashCode += this.getIdPersonne().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsAvisCoupVentType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsAvisCoupVentType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ci");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ci"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designationChantier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designationChantier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designationOrganisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designationOrganisation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designationOrganisationEntiteMere");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designationOrganisationEntiteMere"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idOrganisation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lien");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lien"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeLien");
        elemField.setXmlName(new javax.xml.namespace.QName("", "typeLien"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeOrganisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "typeOrganisation"));
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
