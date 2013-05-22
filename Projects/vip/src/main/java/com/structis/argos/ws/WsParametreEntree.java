/**
 * WsParametreEntree.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsParametreEntree implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String nomParametre;

    private java.lang.String valeurParametre;

    public WsParametreEntree() {
    }

    public WsParametreEntree(java.lang.String nomParametre, java.lang.String valeurParametre) {
        this.nomParametre = nomParametre;
        this.valeurParametre = valeurParametre;
    }

    /**
     * Gets the nomParametre value for this WsParametreEntree.
     * 
     * @return nomParametre
     */
    public java.lang.String getNomParametre() {
        return this.nomParametre;
    }

    /**
     * Sets the nomParametre value for this WsParametreEntree.
     * 
     * @param nomParametre
     */
    public void setNomParametre(java.lang.String nomParametre) {
        this.nomParametre = nomParametre;
    }

    /**
     * Gets the valeurParametre value for this WsParametreEntree.
     * 
     * @return valeurParametre
     */
    public java.lang.String getValeurParametre() {
        return this.valeurParametre;
    }

    /**
     * Sets the valeurParametre value for this WsParametreEntree.
     * 
     * @param valeurParametre
     */
    public void setValeurParametre(java.lang.String valeurParametre) {
        this.valeurParametre = valeurParametre;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsParametreEntree))
            return false;
        WsParametreEntree other = (WsParametreEntree) obj;
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
                && ((this.nomParametre == null && other.getNomParametre() == null) || (this.nomParametre != null && this.nomParametre.equals(other
                        .getNomParametre())))
                && ((this.valeurParametre == null && other.getValeurParametre() == null) || (this.valeurParametre != null && this.valeurParametre
                        .equals(other.getValeurParametre())));
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
        if (this.getNomParametre() != null) {
            _hashCode += this.getNomParametre().hashCode();
        }
        if (this.getValeurParametre() != null) {
            _hashCode += this.getValeurParametre().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsParametreEntree.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsParametreEntree"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomParametre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomParametre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valeurParametre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valeurParametre"));
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
