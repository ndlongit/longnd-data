/**
 * WsOrganisationSimplifiedResultType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsOrganisationSimplifiedResultType  implements java.io.Serializable {
    private java.lang.String messageErreur;

    private int codeRetour;

    private com.structis.argos.ws.WsOrganisationSimplifiedType[] listeRetour;

    public WsOrganisationSimplifiedResultType() {
    }

    public WsOrganisationSimplifiedResultType(
           java.lang.String messageErreur,
           int codeRetour,
           com.structis.argos.ws.WsOrganisationSimplifiedType[] listeRetour) {
           this.messageErreur = messageErreur;
           this.codeRetour = codeRetour;
           this.listeRetour = listeRetour;
    }


    /**
     * Gets the messageErreur value for this WsOrganisationSimplifiedResultType.
     * 
     * @return messageErreur
     */
    public java.lang.String getMessageErreur() {
        return messageErreur;
    }


    /**
     * Sets the messageErreur value for this WsOrganisationSimplifiedResultType.
     * 
     * @param messageErreur
     */
    public void setMessageErreur(java.lang.String messageErreur) {
        this.messageErreur = messageErreur;
    }


    /**
     * Gets the codeRetour value for this WsOrganisationSimplifiedResultType.
     * 
     * @return codeRetour
     */
    public int getCodeRetour() {
        return codeRetour;
    }


    /**
     * Sets the codeRetour value for this WsOrganisationSimplifiedResultType.
     * 
     * @param codeRetour
     */
    public void setCodeRetour(int codeRetour) {
        this.codeRetour = codeRetour;
    }


    /**
     * Gets the listeRetour value for this WsOrganisationSimplifiedResultType.
     * 
     * @return listeRetour
     */
    public com.structis.argos.ws.WsOrganisationSimplifiedType[] getListeRetour() {
        return listeRetour;
    }


    /**
     * Sets the listeRetour value for this WsOrganisationSimplifiedResultType.
     * 
     * @param listeRetour
     */
    public void setListeRetour(com.structis.argos.ws.WsOrganisationSimplifiedType[] listeRetour) {
        this.listeRetour = listeRetour;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsOrganisationSimplifiedResultType)) return false;
        WsOrganisationSimplifiedResultType other = (WsOrganisationSimplifiedResultType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageErreur==null && other.getMessageErreur()==null) || 
             (this.messageErreur!=null &&
              this.messageErreur.equals(other.getMessageErreur()))) &&
            this.codeRetour == other.getCodeRetour() &&
            ((this.listeRetour==null && other.getListeRetour()==null) || 
             (this.listeRetour!=null &&
              java.util.Arrays.equals(this.listeRetour, other.getListeRetour())));
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
        if (getMessageErreur() != null) {
            _hashCode += getMessageErreur().hashCode();
        }
        _hashCode += getCodeRetour();
        if (getListeRetour() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListeRetour());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListeRetour(), i);
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
        new org.apache.axis.description.TypeDesc(WsOrganisationSimplifiedResultType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationSimplifiedResultType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageErreur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageErreur"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeRetour");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codeRetour"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeRetour");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeRetour"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationSimplifiedType"));
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
