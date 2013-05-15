/**
 * WsPersonneMissionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsPersonneMissionType  implements java.io.Serializable {
    private int priorite;

    private java.lang.String idOrganisation;

    private java.lang.String fonction;

    private java.lang.String organisation;

    private java.lang.String idPersonne;

    private java.lang.String idPersonneCourt;

    public WsPersonneMissionType() {
    }

    public WsPersonneMissionType(
           int priorite,
           java.lang.String idOrganisation,
           java.lang.String fonction,
           java.lang.String organisation,
           java.lang.String idPersonne,
           java.lang.String idPersonneCourt) {
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
        return priorite;
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
        return idOrganisation;
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
        return fonction;
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
        return organisation;
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
        return idPersonne;
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
        return idPersonneCourt;
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
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsPersonneMissionType)) return false;
        WsPersonneMissionType other = (WsPersonneMissionType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.priorite == other.getPriorite() &&
            ((this.idOrganisation==null && other.getIdOrganisation()==null) || 
             (this.idOrganisation!=null &&
              this.idOrganisation.equals(other.getIdOrganisation()))) &&
            ((this.fonction==null && other.getFonction()==null) || 
             (this.fonction!=null &&
              this.fonction.equals(other.getFonction()))) &&
            ((this.organisation==null && other.getOrganisation()==null) || 
             (this.organisation!=null &&
              this.organisation.equals(other.getOrganisation()))) &&
            ((this.idPersonne==null && other.getIdPersonne()==null) || 
             (this.idPersonne!=null &&
              this.idPersonne.equals(other.getIdPersonne()))) &&
            ((this.idPersonneCourt==null && other.getIdPersonneCourt()==null) || 
             (this.idPersonneCourt!=null &&
              this.idPersonneCourt.equals(other.getIdPersonneCourt())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getPriorite();
        if (getIdOrganisation() != null) {
            _hashCode += getIdOrganisation().hashCode();
        }
        if (getFonction() != null) {
            _hashCode += getFonction().hashCode();
        }
        if (getOrganisation() != null) {
            _hashCode += getOrganisation().hashCode();
        }
        if (getIdPersonne() != null) {
            _hashCode += getIdPersonne().hashCode();
        }
        if (getIdPersonneCourt() != null) {
            _hashCode += getIdPersonneCourt().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsPersonneMissionType.class, true);

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
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
