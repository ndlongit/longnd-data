/**
 * WsCommunautePersonneType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsCommunautePersonneType  implements java.io.Serializable {
    private java.lang.String fonction;

    private java.lang.String idOrganisation;

    private java.lang.String organisation;

    public WsCommunautePersonneType() {
    }

    public WsCommunautePersonneType(
           java.lang.String fonction,
           java.lang.String idOrganisation,
           java.lang.String organisation) {
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
        return fonction;
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
        return idOrganisation;
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
        return organisation;
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
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsCommunautePersonneType)) return false;
        WsCommunautePersonneType other = (WsCommunautePersonneType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fonction==null && other.getFonction()==null) || 
             (this.fonction!=null &&
              this.fonction.equals(other.getFonction()))) &&
            ((this.idOrganisation==null && other.getIdOrganisation()==null) || 
             (this.idOrganisation!=null &&
              this.idOrganisation.equals(other.getIdOrganisation()))) &&
            ((this.organisation==null && other.getOrganisation()==null) || 
             (this.organisation!=null &&
              this.organisation.equals(other.getOrganisation())));
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
        if (getFonction() != null) {
            _hashCode += getFonction().hashCode();
        }
        if (getIdOrganisation() != null) {
            _hashCode += getIdOrganisation().hashCode();
        }
        if (getOrganisation() != null) {
            _hashCode += getOrganisation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsCommunautePersonneType.class, true);

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
