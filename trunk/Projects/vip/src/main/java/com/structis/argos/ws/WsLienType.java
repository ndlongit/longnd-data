/**
 * WsLienType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsLienType  implements java.io.Serializable {
    private java.lang.String valeur;

    private java.lang.String semantique;

    private java.lang.String priorite;

    private java.lang.String idSemantique;

    private java.lang.String prioriteSemantique;

    private java.lang.String commentaire;

    private java.lang.String idTypeSemantique;

    public WsLienType() {
    }

    public WsLienType(
           java.lang.String valeur,
           java.lang.String semantique,
           java.lang.String priorite,
           java.lang.String idSemantique,
           java.lang.String prioriteSemantique,
           java.lang.String commentaire,
           java.lang.String idTypeSemantique) {
           this.valeur = valeur;
           this.semantique = semantique;
           this.priorite = priorite;
           this.idSemantique = idSemantique;
           this.prioriteSemantique = prioriteSemantique;
           this.commentaire = commentaire;
           this.idTypeSemantique = idTypeSemantique;
    }


    /**
     * Gets the valeur value for this WsLienType.
     * 
     * @return valeur
     */
    public java.lang.String getValeur() {
        return valeur;
    }


    /**
     * Sets the valeur value for this WsLienType.
     * 
     * @param valeur
     */
    public void setValeur(java.lang.String valeur) {
        this.valeur = valeur;
    }


    /**
     * Gets the semantique value for this WsLienType.
     * 
     * @return semantique
     */
    public java.lang.String getSemantique() {
        return semantique;
    }


    /**
     * Sets the semantique value for this WsLienType.
     * 
     * @param semantique
     */
    public void setSemantique(java.lang.String semantique) {
        this.semantique = semantique;
    }


    /**
     * Gets the priorite value for this WsLienType.
     * 
     * @return priorite
     */
    public java.lang.String getPriorite() {
        return priorite;
    }


    /**
     * Sets the priorite value for this WsLienType.
     * 
     * @param priorite
     */
    public void setPriorite(java.lang.String priorite) {
        this.priorite = priorite;
    }


    /**
     * Gets the idSemantique value for this WsLienType.
     * 
     * @return idSemantique
     */
    public java.lang.String getIdSemantique() {
        return idSemantique;
    }


    /**
     * Sets the idSemantique value for this WsLienType.
     * 
     * @param idSemantique
     */
    public void setIdSemantique(java.lang.String idSemantique) {
        this.idSemantique = idSemantique;
    }


    /**
     * Gets the prioriteSemantique value for this WsLienType.
     * 
     * @return prioriteSemantique
     */
    public java.lang.String getPrioriteSemantique() {
        return prioriteSemantique;
    }


    /**
     * Sets the prioriteSemantique value for this WsLienType.
     * 
     * @param prioriteSemantique
     */
    public void setPrioriteSemantique(java.lang.String prioriteSemantique) {
        this.prioriteSemantique = prioriteSemantique;
    }


    /**
     * Gets the commentaire value for this WsLienType.
     * 
     * @return commentaire
     */
    public java.lang.String getCommentaire() {
        return commentaire;
    }


    /**
     * Sets the commentaire value for this WsLienType.
     * 
     * @param commentaire
     */
    public void setCommentaire(java.lang.String commentaire) {
        this.commentaire = commentaire;
    }


    /**
     * Gets the idTypeSemantique value for this WsLienType.
     * 
     * @return idTypeSemantique
     */
    public java.lang.String getIdTypeSemantique() {
        return idTypeSemantique;
    }


    /**
     * Sets the idTypeSemantique value for this WsLienType.
     * 
     * @param idTypeSemantique
     */
    public void setIdTypeSemantique(java.lang.String idTypeSemantique) {
        this.idTypeSemantique = idTypeSemantique;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsLienType)) return false;
        WsLienType other = (WsLienType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valeur==null && other.getValeur()==null) || 
             (this.valeur!=null &&
              this.valeur.equals(other.getValeur()))) &&
            ((this.semantique==null && other.getSemantique()==null) || 
             (this.semantique!=null &&
              this.semantique.equals(other.getSemantique()))) &&
            ((this.priorite==null && other.getPriorite()==null) || 
             (this.priorite!=null &&
              this.priorite.equals(other.getPriorite()))) &&
            ((this.idSemantique==null && other.getIdSemantique()==null) || 
             (this.idSemantique!=null &&
              this.idSemantique.equals(other.getIdSemantique()))) &&
            ((this.prioriteSemantique==null && other.getPrioriteSemantique()==null) || 
             (this.prioriteSemantique!=null &&
              this.prioriteSemantique.equals(other.getPrioriteSemantique()))) &&
            ((this.commentaire==null && other.getCommentaire()==null) || 
             (this.commentaire!=null &&
              this.commentaire.equals(other.getCommentaire()))) &&
            ((this.idTypeSemantique==null && other.getIdTypeSemantique()==null) || 
             (this.idTypeSemantique!=null &&
              this.idTypeSemantique.equals(other.getIdTypeSemantique())));
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
        if (getValeur() != null) {
            _hashCode += getValeur().hashCode();
        }
        if (getSemantique() != null) {
            _hashCode += getSemantique().hashCode();
        }
        if (getPriorite() != null) {
            _hashCode += getPriorite().hashCode();
        }
        if (getIdSemantique() != null) {
            _hashCode += getIdSemantique().hashCode();
        }
        if (getPrioriteSemantique() != null) {
            _hashCode += getPrioriteSemantique().hashCode();
        }
        if (getCommentaire() != null) {
            _hashCode += getCommentaire().hashCode();
        }
        if (getIdTypeSemantique() != null) {
            _hashCode += getIdTypeSemantique().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsLienType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsLienType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valeur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valeur"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("semantique");
        elemField.setXmlName(new javax.xml.namespace.QName("", "semantique"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priorite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSemantique");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idSemantique"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prioriteSemantique");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prioriteSemantique"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commentaire");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commentaire"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTypeSemantique");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idTypeSemantique"));
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
