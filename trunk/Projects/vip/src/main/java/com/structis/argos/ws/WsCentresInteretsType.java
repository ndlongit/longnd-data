/**
 * WsCentresInteretsType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsCentresInteretsType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String designation;

    private java.lang.String idArgos;

    private com.structis.argos.ws.WsCentresInteretsType[] listeFille;

    public WsCentresInteretsType() {
    }

    public WsCentresInteretsType(java.lang.String designation, java.lang.String idArgos, com.structis.argos.ws.WsCentresInteretsType[] listeFille) {
        this.designation = designation;
        this.idArgos = idArgos;
        this.listeFille = listeFille;
    }

    /**
     * Gets the designation value for this WsCentresInteretsType.
     * 
     * @return designation
     */
    public java.lang.String getDesignation() {
        return this.designation;
    }

    /**
     * Sets the designation value for this WsCentresInteretsType.
     * 
     * @param designation
     */
    public void setDesignation(java.lang.String designation) {
        this.designation = designation;
    }

    /**
     * Gets the idArgos value for this WsCentresInteretsType.
     * 
     * @return idArgos
     */
    public java.lang.String getIdArgos() {
        return this.idArgos;
    }

    /**
     * Sets the idArgos value for this WsCentresInteretsType.
     * 
     * @param idArgos
     */
    public void setIdArgos(java.lang.String idArgos) {
        this.idArgos = idArgos;
    }

    /**
     * Gets the listeFille value for this WsCentresInteretsType.
     * 
     * @return listeFille
     */
    public com.structis.argos.ws.WsCentresInteretsType[] getListeFille() {
        return this.listeFille;
    }

    /**
     * Sets the listeFille value for this WsCentresInteretsType.
     * 
     * @param listeFille
     */
    public void setListeFille(com.structis.argos.ws.WsCentresInteretsType[] listeFille) {
        this.listeFille = listeFille;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsCentresInteretsType))
            return false;
        WsCentresInteretsType other = (WsCentresInteretsType) obj;
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
                && ((this.designation == null && other.getDesignation() == null) || (this.designation != null && this.designation.equals(other
                        .getDesignation())))
                && ((this.idArgos == null && other.getIdArgos() == null) || (this.idArgos != null && this.idArgos.equals(other.getIdArgos())))
                && ((this.listeFille == null && other.getListeFille() == null) || (this.listeFille != null && java.util.Arrays.equals(
                        this.listeFille, other.getListeFille())));
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
        if (this.getDesignation() != null) {
            _hashCode += this.getDesignation().hashCode();
        }
        if (this.getIdArgos() != null) {
            _hashCode += this.getIdArgos().hashCode();
        }
        if (this.getListeFille() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeFille()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeFille(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsCentresInteretsType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsCentresInteretsType"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsCentresInteretsType"));
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
