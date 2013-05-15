/**
 * WsArbreOrganisationSimplifiedType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsArbreOrganisationSimplifiedType  implements java.io.Serializable {
    private java.lang.String designation;

    private java.lang.String idArgos;

    private com.structis.argos.ws.WsArbreOrganisationSimplifiedType[] listeFille;

    public WsArbreOrganisationSimplifiedType() {
    }

    public WsArbreOrganisationSimplifiedType(
           java.lang.String designation,
           java.lang.String idArgos,
           com.structis.argos.ws.WsArbreOrganisationSimplifiedType[] listeFille) {
           this.designation = designation;
           this.idArgos = idArgos;
           this.listeFille = listeFille;
    }


    /**
     * Gets the designation value for this WsArbreOrganisationSimplifiedType.
     * 
     * @return designation
     */
    public java.lang.String getDesignation() {
        return designation;
    }


    /**
     * Sets the designation value for this WsArbreOrganisationSimplifiedType.
     * 
     * @param designation
     */
    public void setDesignation(java.lang.String designation) {
        this.designation = designation;
    }


    /**
     * Gets the idArgos value for this WsArbreOrganisationSimplifiedType.
     * 
     * @return idArgos
     */
    public java.lang.String getIdArgos() {
        return idArgos;
    }


    /**
     * Sets the idArgos value for this WsArbreOrganisationSimplifiedType.
     * 
     * @param idArgos
     */
    public void setIdArgos(java.lang.String idArgos) {
        this.idArgos = idArgos;
    }


    /**
     * Gets the listeFille value for this WsArbreOrganisationSimplifiedType.
     * 
     * @return listeFille
     */
    public com.structis.argos.ws.WsArbreOrganisationSimplifiedType[] getListeFille() {
        return listeFille;
    }


    /**
     * Sets the listeFille value for this WsArbreOrganisationSimplifiedType.
     * 
     * @param listeFille
     */
    public void setListeFille(com.structis.argos.ws.WsArbreOrganisationSimplifiedType[] listeFille) {
        this.listeFille = listeFille;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsArbreOrganisationSimplifiedType)) return false;
        WsArbreOrganisationSimplifiedType other = (WsArbreOrganisationSimplifiedType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.designation==null && other.getDesignation()==null) || 
             (this.designation!=null &&
              this.designation.equals(other.getDesignation()))) &&
            ((this.idArgos==null && other.getIdArgos()==null) || 
             (this.idArgos!=null &&
              this.idArgos.equals(other.getIdArgos()))) &&
            ((this.listeFille==null && other.getListeFille()==null) || 
             (this.listeFille!=null &&
              java.util.Arrays.equals(this.listeFille, other.getListeFille())));
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
        if (getDesignation() != null) {
            _hashCode += getDesignation().hashCode();
        }
        if (getIdArgos() != null) {
            _hashCode += getIdArgos().hashCode();
        }
        if (getListeFille() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListeFille());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListeFille(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsArbreOrganisationSimplifiedType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsArbreOrganisationSimplifiedType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idArgos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idArgos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeFille");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeFille"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsArbreOrganisationSimplifiedType"));
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
