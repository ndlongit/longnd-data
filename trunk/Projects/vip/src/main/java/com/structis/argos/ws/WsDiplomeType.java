/**
 * WsDiplomeType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsDiplomeType  implements java.io.Serializable {
    private java.lang.String dateDiplome;

    private java.lang.String intituleDiplome;

    public WsDiplomeType() {
    }

    public WsDiplomeType(
           java.lang.String dateDiplome,
           java.lang.String intituleDiplome) {
           this.dateDiplome = dateDiplome;
           this.intituleDiplome = intituleDiplome;
    }


    /**
     * Gets the dateDiplome value for this WsDiplomeType.
     * 
     * @return dateDiplome
     */
    public java.lang.String getDateDiplome() {
        return dateDiplome;
    }


    /**
     * Sets the dateDiplome value for this WsDiplomeType.
     * 
     * @param dateDiplome
     */
    public void setDateDiplome(java.lang.String dateDiplome) {
        this.dateDiplome = dateDiplome;
    }


    /**
     * Gets the intituleDiplome value for this WsDiplomeType.
     * 
     * @return intituleDiplome
     */
    public java.lang.String getIntituleDiplome() {
        return intituleDiplome;
    }


    /**
     * Sets the intituleDiplome value for this WsDiplomeType.
     * 
     * @param intituleDiplome
     */
    public void setIntituleDiplome(java.lang.String intituleDiplome) {
        this.intituleDiplome = intituleDiplome;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsDiplomeType)) return false;
        WsDiplomeType other = (WsDiplomeType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dateDiplome==null && other.getDateDiplome()==null) || 
             (this.dateDiplome!=null &&
              this.dateDiplome.equals(other.getDateDiplome()))) &&
            ((this.intituleDiplome==null && other.getIntituleDiplome()==null) || 
             (this.intituleDiplome!=null &&
              this.intituleDiplome.equals(other.getIntituleDiplome())));
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
        if (getDateDiplome() != null) {
            _hashCode += getDateDiplome().hashCode();
        }
        if (getIntituleDiplome() != null) {
            _hashCode += getIntituleDiplome().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsDiplomeType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsDiplomeType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateDiplome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateDiplome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intituleDiplome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "intituleDiplome"));
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
