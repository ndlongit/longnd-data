/**
 * WsParametreVIPETDE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsParametreVIPETDE implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String tabOrganizationType;

    private java.lang.String idParent;

    public WsParametreVIPETDE() {
    }

    public WsParametreVIPETDE(java.lang.String tabOrganizationType, java.lang.String idParent) {
        this.tabOrganizationType = tabOrganizationType;
        this.idParent = idParent;
    }

    /**
     * Gets the tabOrganizationType value for this WsParametreVIPETDE.
     * 
     * @return tabOrganizationType
     */
    public java.lang.String getTabOrganizationType() {
        return this.tabOrganizationType;
    }

    /**
     * Sets the tabOrganizationType value for this WsParametreVIPETDE.
     * 
     * @param tabOrganizationType
     */
    public void setTabOrganizationType(java.lang.String tabOrganizationType) {
        this.tabOrganizationType = tabOrganizationType;
    }

    /**
     * Gets the idParent value for this WsParametreVIPETDE.
     * 
     * @return idParent
     */
    public java.lang.String getIdParent() {
        return this.idParent;
    }

    /**
     * Sets the idParent value for this WsParametreVIPETDE.
     * 
     * @param idParent
     */
    public void setIdParent(java.lang.String idParent) {
        this.idParent = idParent;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsParametreVIPETDE))
            return false;
        WsParametreVIPETDE other = (WsParametreVIPETDE) obj;
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
                && ((this.tabOrganizationType == null && other.getTabOrganizationType() == null) || (this.tabOrganizationType != null && this.tabOrganizationType
                        .equals(other.getTabOrganizationType())))
                && ((this.idParent == null && other.getIdParent() == null) || (this.idParent != null && this.idParent.equals(other.getIdParent())));
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
        if (this.getTabOrganizationType() != null) {
            _hashCode += this.getTabOrganizationType().hashCode();
        }
        if (this.getIdParent() != null) {
            _hashCode += this.getIdParent().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsParametreVIPETDE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsParametreVIPETDE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tabOrganizationType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tabOrganizationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idParent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idParent"));
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
