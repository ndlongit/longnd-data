/**
 * WsTopicSiteType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsTopicSiteType  implements java.io.Serializable {
    private java.lang.String idSite;

    private java.lang.String nom;

    private java.lang.String priorite;

    private java.lang.String localisation;

    private java.lang.String commentaire;

    public WsTopicSiteType() {
    }

    public WsTopicSiteType(
           java.lang.String idSite,
           java.lang.String nom,
           java.lang.String priorite,
           java.lang.String localisation,
           java.lang.String commentaire) {
           this.idSite = idSite;
           this.nom = nom;
           this.priorite = priorite;
           this.localisation = localisation;
           this.commentaire = commentaire;
    }


    /**
     * Gets the idSite value for this WsTopicSiteType.
     * 
     * @return idSite
     */
    public java.lang.String getIdSite() {
        return idSite;
    }


    /**
     * Sets the idSite value for this WsTopicSiteType.
     * 
     * @param idSite
     */
    public void setIdSite(java.lang.String idSite) {
        this.idSite = idSite;
    }


    /**
     * Gets the nom value for this WsTopicSiteType.
     * 
     * @return nom
     */
    public java.lang.String getNom() {
        return nom;
    }


    /**
     * Sets the nom value for this WsTopicSiteType.
     * 
     * @param nom
     */
    public void setNom(java.lang.String nom) {
        this.nom = nom;
    }


    /**
     * Gets the priorite value for this WsTopicSiteType.
     * 
     * @return priorite
     */
    public java.lang.String getPriorite() {
        return priorite;
    }


    /**
     * Sets the priorite value for this WsTopicSiteType.
     * 
     * @param priorite
     */
    public void setPriorite(java.lang.String priorite) {
        this.priorite = priorite;
    }


    /**
     * Gets the localisation value for this WsTopicSiteType.
     * 
     * @return localisation
     */
    public java.lang.String getLocalisation() {
        return localisation;
    }


    /**
     * Sets the localisation value for this WsTopicSiteType.
     * 
     * @param localisation
     */
    public void setLocalisation(java.lang.String localisation) {
        this.localisation = localisation;
    }


    /**
     * Gets the commentaire value for this WsTopicSiteType.
     * 
     * @return commentaire
     */
    public java.lang.String getCommentaire() {
        return commentaire;
    }


    /**
     * Sets the commentaire value for this WsTopicSiteType.
     * 
     * @param commentaire
     */
    public void setCommentaire(java.lang.String commentaire) {
        this.commentaire = commentaire;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsTopicSiteType)) return false;
        WsTopicSiteType other = (WsTopicSiteType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idSite==null && other.getIdSite()==null) || 
             (this.idSite!=null &&
              this.idSite.equals(other.getIdSite()))) &&
            ((this.nom==null && other.getNom()==null) || 
             (this.nom!=null &&
              this.nom.equals(other.getNom()))) &&
            ((this.priorite==null && other.getPriorite()==null) || 
             (this.priorite!=null &&
              this.priorite.equals(other.getPriorite()))) &&
            ((this.localisation==null && other.getLocalisation()==null) || 
             (this.localisation!=null &&
              this.localisation.equals(other.getLocalisation()))) &&
            ((this.commentaire==null && other.getCommentaire()==null) || 
             (this.commentaire!=null &&
              this.commentaire.equals(other.getCommentaire())));
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
        if (getIdSite() != null) {
            _hashCode += getIdSite().hashCode();
        }
        if (getNom() != null) {
            _hashCode += getNom().hashCode();
        }
        if (getPriorite() != null) {
            _hashCode += getPriorite().hashCode();
        }
        if (getLocalisation() != null) {
            _hashCode += getLocalisation().hashCode();
        }
        if (getCommentaire() != null) {
            _hashCode += getCommentaire().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsTopicSiteType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsTopicSiteType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idSite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nom"));
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
        elemField.setFieldName("localisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localisation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commentaire");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commentaire"));
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
