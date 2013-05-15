/**
 * WsOrganisationLightType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsOrganisationLightType  implements java.io.Serializable {
    private java.lang.String objet;

    private java.lang.String url;

    private com.structis.argos.ws.WsOrganisationRelationType[] listeOrganisationRelation;

    private java.lang.String actif;

    private java.lang.String idArgos;

    private java.lang.String dateModification;

    private java.lang.String abreviation;

    private java.lang.String site;

    private com.structis.argos.ws.WsPersonneMissionType[] listePersonneMission;

    private java.lang.String dateDesactivation;

    private java.lang.String alias5;

    private java.lang.String alias4;

    private java.lang.String alias3;

    private java.lang.String alias2;

    private java.lang.String designation;

    private java.lang.String alias1;

    private java.lang.String employeur;

    private java.lang.String codeBouygues;

    private java.lang.String nature;

    public WsOrganisationLightType() {
    }

    public WsOrganisationLightType(
           java.lang.String objet,
           java.lang.String url,
           com.structis.argos.ws.WsOrganisationRelationType[] listeOrganisationRelation,
           java.lang.String actif,
           java.lang.String idArgos,
           java.lang.String dateModification,
           java.lang.String abreviation,
           java.lang.String site,
           com.structis.argos.ws.WsPersonneMissionType[] listePersonneMission,
           java.lang.String dateDesactivation,
           java.lang.String alias5,
           java.lang.String alias4,
           java.lang.String alias3,
           java.lang.String alias2,
           java.lang.String designation,
           java.lang.String alias1,
           java.lang.String employeur,
           java.lang.String codeBouygues,
           java.lang.String nature) {
           this.objet = objet;
           this.url = url;
           this.listeOrganisationRelation = listeOrganisationRelation;
           this.actif = actif;
           this.idArgos = idArgos;
           this.dateModification = dateModification;
           this.abreviation = abreviation;
           this.site = site;
           this.listePersonneMission = listePersonneMission;
           this.dateDesactivation = dateDesactivation;
           this.alias5 = alias5;
           this.alias4 = alias4;
           this.alias3 = alias3;
           this.alias2 = alias2;
           this.designation = designation;
           this.alias1 = alias1;
           this.employeur = employeur;
           this.codeBouygues = codeBouygues;
           this.nature = nature;
    }


    /**
     * Gets the objet value for this WsOrganisationLightType.
     * 
     * @return objet
     */
    public java.lang.String getObjet() {
        return objet;
    }


    /**
     * Sets the objet value for this WsOrganisationLightType.
     * 
     * @param objet
     */
    public void setObjet(java.lang.String objet) {
        this.objet = objet;
    }


    /**
     * Gets the url value for this WsOrganisationLightType.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this WsOrganisationLightType.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the listeOrganisationRelation value for this WsOrganisationLightType.
     * 
     * @return listeOrganisationRelation
     */
    public com.structis.argos.ws.WsOrganisationRelationType[] getListeOrganisationRelation() {
        return listeOrganisationRelation;
    }


    /**
     * Sets the listeOrganisationRelation value for this WsOrganisationLightType.
     * 
     * @param listeOrganisationRelation
     */
    public void setListeOrganisationRelation(com.structis.argos.ws.WsOrganisationRelationType[] listeOrganisationRelation) {
        this.listeOrganisationRelation = listeOrganisationRelation;
    }


    /**
     * Gets the actif value for this WsOrganisationLightType.
     * 
     * @return actif
     */
    public java.lang.String getActif() {
        return actif;
    }


    /**
     * Sets the actif value for this WsOrganisationLightType.
     * 
     * @param actif
     */
    public void setActif(java.lang.String actif) {
        this.actif = actif;
    }


    /**
     * Gets the idArgos value for this WsOrganisationLightType.
     * 
     * @return idArgos
     */
    public java.lang.String getIdArgos() {
        return idArgos;
    }


    /**
     * Sets the idArgos value for this WsOrganisationLightType.
     * 
     * @param idArgos
     */
    public void setIdArgos(java.lang.String idArgos) {
        this.idArgos = idArgos;
    }


    /**
     * Gets the dateModification value for this WsOrganisationLightType.
     * 
     * @return dateModification
     */
    public java.lang.String getDateModification() {
        return dateModification;
    }


    /**
     * Sets the dateModification value for this WsOrganisationLightType.
     * 
     * @param dateModification
     */
    public void setDateModification(java.lang.String dateModification) {
        this.dateModification = dateModification;
    }


    /**
     * Gets the abreviation value for this WsOrganisationLightType.
     * 
     * @return abreviation
     */
    public java.lang.String getAbreviation() {
        return abreviation;
    }


    /**
     * Sets the abreviation value for this WsOrganisationLightType.
     * 
     * @param abreviation
     */
    public void setAbreviation(java.lang.String abreviation) {
        this.abreviation = abreviation;
    }


    /**
     * Gets the site value for this WsOrganisationLightType.
     * 
     * @return site
     */
    public java.lang.String getSite() {
        return site;
    }


    /**
     * Sets the site value for this WsOrganisationLightType.
     * 
     * @param site
     */
    public void setSite(java.lang.String site) {
        this.site = site;
    }


    /**
     * Gets the listePersonneMission value for this WsOrganisationLightType.
     * 
     * @return listePersonneMission
     */
    public com.structis.argos.ws.WsPersonneMissionType[] getListePersonneMission() {
        return listePersonneMission;
    }


    /**
     * Sets the listePersonneMission value for this WsOrganisationLightType.
     * 
     * @param listePersonneMission
     */
    public void setListePersonneMission(com.structis.argos.ws.WsPersonneMissionType[] listePersonneMission) {
        this.listePersonneMission = listePersonneMission;
    }


    /**
     * Gets the dateDesactivation value for this WsOrganisationLightType.
     * 
     * @return dateDesactivation
     */
    public java.lang.String getDateDesactivation() {
        return dateDesactivation;
    }


    /**
     * Sets the dateDesactivation value for this WsOrganisationLightType.
     * 
     * @param dateDesactivation
     */
    public void setDateDesactivation(java.lang.String dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }


    /**
     * Gets the alias5 value for this WsOrganisationLightType.
     * 
     * @return alias5
     */
    public java.lang.String getAlias5() {
        return alias5;
    }


    /**
     * Sets the alias5 value for this WsOrganisationLightType.
     * 
     * @param alias5
     */
    public void setAlias5(java.lang.String alias5) {
        this.alias5 = alias5;
    }


    /**
     * Gets the alias4 value for this WsOrganisationLightType.
     * 
     * @return alias4
     */
    public java.lang.String getAlias4() {
        return alias4;
    }


    /**
     * Sets the alias4 value for this WsOrganisationLightType.
     * 
     * @param alias4
     */
    public void setAlias4(java.lang.String alias4) {
        this.alias4 = alias4;
    }


    /**
     * Gets the alias3 value for this WsOrganisationLightType.
     * 
     * @return alias3
     */
    public java.lang.String getAlias3() {
        return alias3;
    }


    /**
     * Sets the alias3 value for this WsOrganisationLightType.
     * 
     * @param alias3
     */
    public void setAlias3(java.lang.String alias3) {
        this.alias3 = alias3;
    }


    /**
     * Gets the alias2 value for this WsOrganisationLightType.
     * 
     * @return alias2
     */
    public java.lang.String getAlias2() {
        return alias2;
    }


    /**
     * Sets the alias2 value for this WsOrganisationLightType.
     * 
     * @param alias2
     */
    public void setAlias2(java.lang.String alias2) {
        this.alias2 = alias2;
    }


    /**
     * Gets the designation value for this WsOrganisationLightType.
     * 
     * @return designation
     */
    public java.lang.String getDesignation() {
        return designation;
    }


    /**
     * Sets the designation value for this WsOrganisationLightType.
     * 
     * @param designation
     */
    public void setDesignation(java.lang.String designation) {
        this.designation = designation;
    }


    /**
     * Gets the alias1 value for this WsOrganisationLightType.
     * 
     * @return alias1
     */
    public java.lang.String getAlias1() {
        return alias1;
    }


    /**
     * Sets the alias1 value for this WsOrganisationLightType.
     * 
     * @param alias1
     */
    public void setAlias1(java.lang.String alias1) {
        this.alias1 = alias1;
    }


    /**
     * Gets the employeur value for this WsOrganisationLightType.
     * 
     * @return employeur
     */
    public java.lang.String getEmployeur() {
        return employeur;
    }


    /**
     * Sets the employeur value for this WsOrganisationLightType.
     * 
     * @param employeur
     */
    public void setEmployeur(java.lang.String employeur) {
        this.employeur = employeur;
    }


    /**
     * Gets the codeBouygues value for this WsOrganisationLightType.
     * 
     * @return codeBouygues
     */
    public java.lang.String getCodeBouygues() {
        return codeBouygues;
    }


    /**
     * Sets the codeBouygues value for this WsOrganisationLightType.
     * 
     * @param codeBouygues
     */
    public void setCodeBouygues(java.lang.String codeBouygues) {
        this.codeBouygues = codeBouygues;
    }


    /**
     * Gets the nature value for this WsOrganisationLightType.
     * 
     * @return nature
     */
    public java.lang.String getNature() {
        return nature;
    }


    /**
     * Sets the nature value for this WsOrganisationLightType.
     * 
     * @param nature
     */
    public void setNature(java.lang.String nature) {
        this.nature = nature;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsOrganisationLightType)) return false;
        WsOrganisationLightType other = (WsOrganisationLightType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objet==null && other.getObjet()==null) || 
             (this.objet!=null &&
              this.objet.equals(other.getObjet()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.listeOrganisationRelation==null && other.getListeOrganisationRelation()==null) || 
             (this.listeOrganisationRelation!=null &&
              java.util.Arrays.equals(this.listeOrganisationRelation, other.getListeOrganisationRelation()))) &&
            ((this.actif==null && other.getActif()==null) || 
             (this.actif!=null &&
              this.actif.equals(other.getActif()))) &&
            ((this.idArgos==null && other.getIdArgos()==null) || 
             (this.idArgos!=null &&
              this.idArgos.equals(other.getIdArgos()))) &&
            ((this.dateModification==null && other.getDateModification()==null) || 
             (this.dateModification!=null &&
              this.dateModification.equals(other.getDateModification()))) &&
            ((this.abreviation==null && other.getAbreviation()==null) || 
             (this.abreviation!=null &&
              this.abreviation.equals(other.getAbreviation()))) &&
            ((this.site==null && other.getSite()==null) || 
             (this.site!=null &&
              this.site.equals(other.getSite()))) &&
            ((this.listePersonneMission==null && other.getListePersonneMission()==null) || 
             (this.listePersonneMission!=null &&
              java.util.Arrays.equals(this.listePersonneMission, other.getListePersonneMission()))) &&
            ((this.dateDesactivation==null && other.getDateDesactivation()==null) || 
             (this.dateDesactivation!=null &&
              this.dateDesactivation.equals(other.getDateDesactivation()))) &&
            ((this.alias5==null && other.getAlias5()==null) || 
             (this.alias5!=null &&
              this.alias5.equals(other.getAlias5()))) &&
            ((this.alias4==null && other.getAlias4()==null) || 
             (this.alias4!=null &&
              this.alias4.equals(other.getAlias4()))) &&
            ((this.alias3==null && other.getAlias3()==null) || 
             (this.alias3!=null &&
              this.alias3.equals(other.getAlias3()))) &&
            ((this.alias2==null && other.getAlias2()==null) || 
             (this.alias2!=null &&
              this.alias2.equals(other.getAlias2()))) &&
            ((this.designation==null && other.getDesignation()==null) || 
             (this.designation!=null &&
              this.designation.equals(other.getDesignation()))) &&
            ((this.alias1==null && other.getAlias1()==null) || 
             (this.alias1!=null &&
              this.alias1.equals(other.getAlias1()))) &&
            ((this.employeur==null && other.getEmployeur()==null) || 
             (this.employeur!=null &&
              this.employeur.equals(other.getEmployeur()))) &&
            ((this.codeBouygues==null && other.getCodeBouygues()==null) || 
             (this.codeBouygues!=null &&
              this.codeBouygues.equals(other.getCodeBouygues()))) &&
            ((this.nature==null && other.getNature()==null) || 
             (this.nature!=null &&
              this.nature.equals(other.getNature())));
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
        if (getObjet() != null) {
            _hashCode += getObjet().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getListeOrganisationRelation() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListeOrganisationRelation());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListeOrganisationRelation(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getActif() != null) {
            _hashCode += getActif().hashCode();
        }
        if (getIdArgos() != null) {
            _hashCode += getIdArgos().hashCode();
        }
        if (getDateModification() != null) {
            _hashCode += getDateModification().hashCode();
        }
        if (getAbreviation() != null) {
            _hashCode += getAbreviation().hashCode();
        }
        if (getSite() != null) {
            _hashCode += getSite().hashCode();
        }
        if (getListePersonneMission() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListePersonneMission());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListePersonneMission(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDateDesactivation() != null) {
            _hashCode += getDateDesactivation().hashCode();
        }
        if (getAlias5() != null) {
            _hashCode += getAlias5().hashCode();
        }
        if (getAlias4() != null) {
            _hashCode += getAlias4().hashCode();
        }
        if (getAlias3() != null) {
            _hashCode += getAlias3().hashCode();
        }
        if (getAlias2() != null) {
            _hashCode += getAlias2().hashCode();
        }
        if (getDesignation() != null) {
            _hashCode += getDesignation().hashCode();
        }
        if (getAlias1() != null) {
            _hashCode += getAlias1().hashCode();
        }
        if (getEmployeur() != null) {
            _hashCode += getEmployeur().hashCode();
        }
        if (getCodeBouygues() != null) {
            _hashCode += getCodeBouygues().hashCode();
        }
        if (getNature() != null) {
            _hashCode += getNature().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsOrganisationLightType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationLightType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeOrganisationRelation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeOrganisationRelation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsOrganisationRelationType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actif"));
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
        elemField.setFieldName("dateModification");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateModification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abreviation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abreviation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("site");
        elemField.setXmlName(new javax.xml.namespace.QName("", "site"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listePersonneMission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listePersonneMission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsPersonneMissionType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateDesactivation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateDesactivation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alias5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alias5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alias4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alias4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alias3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alias3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alias2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alias2"));
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
        elemField.setFieldName("alias1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alias1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "employeur"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeBouygues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codeBouygues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nature");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nature"));
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
