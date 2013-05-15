/**
 * WsOrganisationGeoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsOrganisationGeoType  implements java.io.Serializable {
    private java.lang.String idArgos;

    private java.lang.String type;

    private java.lang.String ci;

    private java.lang.String designation;

    private java.lang.String adresse;

    private java.lang.String codepostal;

    private java.lang.String ville;

    private java.lang.String pays;

    private java.lang.String idparent;

    private java.lang.String designationparent;

    public WsOrganisationGeoType() {
    }

    public WsOrganisationGeoType(
           java.lang.String idArgos,
           java.lang.String type,
           java.lang.String ci,
           java.lang.String designation,
           java.lang.String adresse,
           java.lang.String codepostal,
           java.lang.String ville,
           java.lang.String pays,
           java.lang.String idparent,
           java.lang.String designationparent) {
           this.idArgos = idArgos;
           this.type = type;
           this.ci = ci;
           this.designation = designation;
           this.adresse = adresse;
           this.codepostal = codepostal;
           this.ville = ville;
           this.pays = pays;
           this.idparent = idparent;
           this.designationparent = designationparent;
    }


    /**
     * Gets the idArgos value for this WsOrganisationGeoType.
     * 
     * @return idArgos
     */
    public java.lang.String getIdArgos() {
        return idArgos;
    }


    /**
     * Sets the idArgos value for this WsOrganisationGeoType.
     * 
     * @param idArgos
     */
    public void setIdArgos(java.lang.String idArgos) {
        this.idArgos = idArgos;
    }


    /**
     * Gets the type value for this WsOrganisationGeoType.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this WsOrganisationGeoType.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the ci value for this WsOrganisationGeoType.
     * 
     * @return ci
     */
    public java.lang.String getCi() {
        return ci;
    }


    /**
     * Sets the ci value for this WsOrganisationGeoType.
     * 
     * @param ci
     */
    public void setCi(java.lang.String ci) {
        this.ci = ci;
    }


    /**
     * Gets the designation value for this WsOrganisationGeoType.
     * 
     * @return designation
     */
    public java.lang.String getDesignation() {
        return designation;
    }


    /**
     * Sets the designation value for this WsOrganisationGeoType.
     * 
     * @param designation
     */
    public void setDesignation(java.lang.String designation) {
        this.designation = designation;
    }


    /**
     * Gets the adresse value for this WsOrganisationGeoType.
     * 
     * @return adresse
     */
    public java.lang.String getAdresse() {
        return adresse;
    }


    /**
     * Sets the adresse value for this WsOrganisationGeoType.
     * 
     * @param adresse
     */
    public void setAdresse(java.lang.String adresse) {
        this.adresse = adresse;
    }


    /**
     * Gets the codepostal value for this WsOrganisationGeoType.
     * 
     * @return codepostal
     */
    public java.lang.String getCodepostal() {
        return codepostal;
    }


    /**
     * Sets the codepostal value for this WsOrganisationGeoType.
     * 
     * @param codepostal
     */
    public void setCodepostal(java.lang.String codepostal) {
        this.codepostal = codepostal;
    }


    /**
     * Gets the ville value for this WsOrganisationGeoType.
     * 
     * @return ville
     */
    public java.lang.String getVille() {
        return ville;
    }


    /**
     * Sets the ville value for this WsOrganisationGeoType.
     * 
     * @param ville
     */
    public void setVille(java.lang.String ville) {
        this.ville = ville;
    }


    /**
     * Gets the pays value for this WsOrganisationGeoType.
     * 
     * @return pays
     */
    public java.lang.String getPays() {
        return pays;
    }


    /**
     * Sets the pays value for this WsOrganisationGeoType.
     * 
     * @param pays
     */
    public void setPays(java.lang.String pays) {
        this.pays = pays;
    }


    /**
     * Gets the idparent value for this WsOrganisationGeoType.
     * 
     * @return idparent
     */
    public java.lang.String getIdparent() {
        return idparent;
    }


    /**
     * Sets the idparent value for this WsOrganisationGeoType.
     * 
     * @param idparent
     */
    public void setIdparent(java.lang.String idparent) {
        this.idparent = idparent;
    }


    /**
     * Gets the designationparent value for this WsOrganisationGeoType.
     * 
     * @return designationparent
     */
    public java.lang.String getDesignationparent() {
        return designationparent;
    }


    /**
     * Sets the designationparent value for this WsOrganisationGeoType.
     * 
     * @param designationparent
     */
    public void setDesignationparent(java.lang.String designationparent) {
        this.designationparent = designationparent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsOrganisationGeoType)) return false;
        WsOrganisationGeoType other = (WsOrganisationGeoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idArgos==null && other.getIdArgos()==null) || 
             (this.idArgos!=null &&
              this.idArgos.equals(other.getIdArgos()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.ci==null && other.getCi()==null) || 
             (this.ci!=null &&
              this.ci.equals(other.getCi()))) &&
            ((this.designation==null && other.getDesignation()==null) || 
             (this.designation!=null &&
              this.designation.equals(other.getDesignation()))) &&
            ((this.adresse==null && other.getAdresse()==null) || 
             (this.adresse!=null &&
              this.adresse.equals(other.getAdresse()))) &&
            ((this.codepostal==null && other.getCodepostal()==null) || 
             (this.codepostal!=null &&
              this.codepostal.equals(other.getCodepostal()))) &&
            ((this.ville==null && other.getVille()==null) || 
             (this.ville!=null &&
              this.ville.equals(other.getVille()))) &&
            ((this.pays==null && other.getPays()==null) || 
             (this.pays!=null &&
              this.pays.equals(other.getPays()))) &&
            ((this.idparent==null && other.getIdparent()==null) || 
             (this.idparent!=null &&
              this.idparent.equals(other.getIdparent()))) &&
            ((this.designationparent==null && other.getDesignationparent()==null) || 
             (this.designationparent!=null &&
              this.designationparent.equals(other.getDesignationparent())));
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
        if (getIdArgos() != null) {
            _hashCode += getIdArgos().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getCi() != null) {
            _hashCode += getCi().hashCode();
        }
        if (getDesignation() != null) {
            _hashCode += getDesignation().hashCode();
        }
        if (getAdresse() != null) {
            _hashCode += getAdresse().hashCode();
        }
        if (getCodepostal() != null) {
            _hashCode += getCodepostal().hashCode();
        }
        if (getVille() != null) {
            _hashCode += getVille().hashCode();
        }
        if (getPays() != null) {
            _hashCode += getPays().hashCode();
        }
        if (getIdparent() != null) {
            _hashCode += getIdparent().hashCode();
        }
        if (getDesignationparent() != null) {
            _hashCode += getDesignationparent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsOrganisationGeoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationGeoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idArgos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idArgos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ci");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ci"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adresse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adresse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codepostal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codepostal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ville");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ville"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pays");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pays"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idparent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idparent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designationparent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designationparent"));
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
