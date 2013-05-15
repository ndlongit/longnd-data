/**
 * WsSiteLightType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsSiteLightType  implements java.io.Serializable {
    private java.lang.String pays;

    private java.lang.String commentaire;

    private java.lang.String url;

    private java.lang.String nom;

    private java.lang.String actif;

    private java.lang.String connectivite;

    private java.lang.String idArgos;

    private java.lang.String dateModification;

    private java.lang.String coordGPS;

    private java.lang.String voie;

    private java.lang.String complement;

    private java.lang.String dateDesactivation;

    private java.lang.String etat;

    private java.lang.String ville;

    private java.lang.String idPays;

    private java.lang.String lieuDit;

    private java.lang.String codePostal;

    private com.structis.argos.ws.WsLienType[] listeLien;

    public WsSiteLightType() {
    }

    public WsSiteLightType(
           java.lang.String pays,
           java.lang.String commentaire,
           java.lang.String url,
           java.lang.String nom,
           java.lang.String actif,
           java.lang.String connectivite,
           java.lang.String idArgos,
           java.lang.String dateModification,
           java.lang.String coordGPS,
           java.lang.String voie,
           java.lang.String complement,
           java.lang.String dateDesactivation,
           java.lang.String etat,
           java.lang.String ville,
           java.lang.String idPays,
           java.lang.String lieuDit,
           java.lang.String codePostal,
           com.structis.argos.ws.WsLienType[] listeLien) {
           this.pays = pays;
           this.commentaire = commentaire;
           this.url = url;
           this.nom = nom;
           this.actif = actif;
           this.connectivite = connectivite;
           this.idArgos = idArgos;
           this.dateModification = dateModification;
           this.coordGPS = coordGPS;
           this.voie = voie;
           this.complement = complement;
           this.dateDesactivation = dateDesactivation;
           this.etat = etat;
           this.ville = ville;
           this.idPays = idPays;
           this.lieuDit = lieuDit;
           this.codePostal = codePostal;
           this.listeLien = listeLien;
    }


    /**
     * Gets the pays value for this WsSiteLightType.
     * 
     * @return pays
     */
    public java.lang.String getPays() {
        return pays;
    }


    /**
     * Sets the pays value for this WsSiteLightType.
     * 
     * @param pays
     */
    public void setPays(java.lang.String pays) {
        this.pays = pays;
    }


    /**
     * Gets the commentaire value for this WsSiteLightType.
     * 
     * @return commentaire
     */
    public java.lang.String getCommentaire() {
        return commentaire;
    }


    /**
     * Sets the commentaire value for this WsSiteLightType.
     * 
     * @param commentaire
     */
    public void setCommentaire(java.lang.String commentaire) {
        this.commentaire = commentaire;
    }


    /**
     * Gets the url value for this WsSiteLightType.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this WsSiteLightType.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the nom value for this WsSiteLightType.
     * 
     * @return nom
     */
    public java.lang.String getNom() {
        return nom;
    }


    /**
     * Sets the nom value for this WsSiteLightType.
     * 
     * @param nom
     */
    public void setNom(java.lang.String nom) {
        this.nom = nom;
    }


    /**
     * Gets the actif value for this WsSiteLightType.
     * 
     * @return actif
     */
    public java.lang.String getActif() {
        return actif;
    }


    /**
     * Sets the actif value for this WsSiteLightType.
     * 
     * @param actif
     */
    public void setActif(java.lang.String actif) {
        this.actif = actif;
    }


    /**
     * Gets the connectivite value for this WsSiteLightType.
     * 
     * @return connectivite
     */
    public java.lang.String getConnectivite() {
        return connectivite;
    }


    /**
     * Sets the connectivite value for this WsSiteLightType.
     * 
     * @param connectivite
     */
    public void setConnectivite(java.lang.String connectivite) {
        this.connectivite = connectivite;
    }


    /**
     * Gets the idArgos value for this WsSiteLightType.
     * 
     * @return idArgos
     */
    public java.lang.String getIdArgos() {
        return idArgos;
    }


    /**
     * Sets the idArgos value for this WsSiteLightType.
     * 
     * @param idArgos
     */
    public void setIdArgos(java.lang.String idArgos) {
        this.idArgos = idArgos;
    }


    /**
     * Gets the dateModification value for this WsSiteLightType.
     * 
     * @return dateModification
     */
    public java.lang.String getDateModification() {
        return dateModification;
    }


    /**
     * Sets the dateModification value for this WsSiteLightType.
     * 
     * @param dateModification
     */
    public void setDateModification(java.lang.String dateModification) {
        this.dateModification = dateModification;
    }


    /**
     * Gets the coordGPS value for this WsSiteLightType.
     * 
     * @return coordGPS
     */
    public java.lang.String getCoordGPS() {
        return coordGPS;
    }


    /**
     * Sets the coordGPS value for this WsSiteLightType.
     * 
     * @param coordGPS
     */
    public void setCoordGPS(java.lang.String coordGPS) {
        this.coordGPS = coordGPS;
    }


    /**
     * Gets the voie value for this WsSiteLightType.
     * 
     * @return voie
     */
    public java.lang.String getVoie() {
        return voie;
    }


    /**
     * Sets the voie value for this WsSiteLightType.
     * 
     * @param voie
     */
    public void setVoie(java.lang.String voie) {
        this.voie = voie;
    }


    /**
     * Gets the complement value for this WsSiteLightType.
     * 
     * @return complement
     */
    public java.lang.String getComplement() {
        return complement;
    }


    /**
     * Sets the complement value for this WsSiteLightType.
     * 
     * @param complement
     */
    public void setComplement(java.lang.String complement) {
        this.complement = complement;
    }


    /**
     * Gets the dateDesactivation value for this WsSiteLightType.
     * 
     * @return dateDesactivation
     */
    public java.lang.String getDateDesactivation() {
        return dateDesactivation;
    }


    /**
     * Sets the dateDesactivation value for this WsSiteLightType.
     * 
     * @param dateDesactivation
     */
    public void setDateDesactivation(java.lang.String dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }


    /**
     * Gets the etat value for this WsSiteLightType.
     * 
     * @return etat
     */
    public java.lang.String getEtat() {
        return etat;
    }


    /**
     * Sets the etat value for this WsSiteLightType.
     * 
     * @param etat
     */
    public void setEtat(java.lang.String etat) {
        this.etat = etat;
    }


    /**
     * Gets the ville value for this WsSiteLightType.
     * 
     * @return ville
     */
    public java.lang.String getVille() {
        return ville;
    }


    /**
     * Sets the ville value for this WsSiteLightType.
     * 
     * @param ville
     */
    public void setVille(java.lang.String ville) {
        this.ville = ville;
    }


    /**
     * Gets the idPays value for this WsSiteLightType.
     * 
     * @return idPays
     */
    public java.lang.String getIdPays() {
        return idPays;
    }


    /**
     * Sets the idPays value for this WsSiteLightType.
     * 
     * @param idPays
     */
    public void setIdPays(java.lang.String idPays) {
        this.idPays = idPays;
    }


    /**
     * Gets the lieuDit value for this WsSiteLightType.
     * 
     * @return lieuDit
     */
    public java.lang.String getLieuDit() {
        return lieuDit;
    }


    /**
     * Sets the lieuDit value for this WsSiteLightType.
     * 
     * @param lieuDit
     */
    public void setLieuDit(java.lang.String lieuDit) {
        this.lieuDit = lieuDit;
    }


    /**
     * Gets the codePostal value for this WsSiteLightType.
     * 
     * @return codePostal
     */
    public java.lang.String getCodePostal() {
        return codePostal;
    }


    /**
     * Sets the codePostal value for this WsSiteLightType.
     * 
     * @param codePostal
     */
    public void setCodePostal(java.lang.String codePostal) {
        this.codePostal = codePostal;
    }


    /**
     * Gets the listeLien value for this WsSiteLightType.
     * 
     * @return listeLien
     */
    public com.structis.argos.ws.WsLienType[] getListeLien() {
        return listeLien;
    }


    /**
     * Sets the listeLien value for this WsSiteLightType.
     * 
     * @param listeLien
     */
    public void setListeLien(com.structis.argos.ws.WsLienType[] listeLien) {
        this.listeLien = listeLien;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsSiteLightType)) return false;
        WsSiteLightType other = (WsSiteLightType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pays==null && other.getPays()==null) || 
             (this.pays!=null &&
              this.pays.equals(other.getPays()))) &&
            ((this.commentaire==null && other.getCommentaire()==null) || 
             (this.commentaire!=null &&
              this.commentaire.equals(other.getCommentaire()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.nom==null && other.getNom()==null) || 
             (this.nom!=null &&
              this.nom.equals(other.getNom()))) &&
            ((this.actif==null && other.getActif()==null) || 
             (this.actif!=null &&
              this.actif.equals(other.getActif()))) &&
            ((this.connectivite==null && other.getConnectivite()==null) || 
             (this.connectivite!=null &&
              this.connectivite.equals(other.getConnectivite()))) &&
            ((this.idArgos==null && other.getIdArgos()==null) || 
             (this.idArgos!=null &&
              this.idArgos.equals(other.getIdArgos()))) &&
            ((this.dateModification==null && other.getDateModification()==null) || 
             (this.dateModification!=null &&
              this.dateModification.equals(other.getDateModification()))) &&
            ((this.coordGPS==null && other.getCoordGPS()==null) || 
             (this.coordGPS!=null &&
              this.coordGPS.equals(other.getCoordGPS()))) &&
            ((this.voie==null && other.getVoie()==null) || 
             (this.voie!=null &&
              this.voie.equals(other.getVoie()))) &&
            ((this.complement==null && other.getComplement()==null) || 
             (this.complement!=null &&
              this.complement.equals(other.getComplement()))) &&
            ((this.dateDesactivation==null && other.getDateDesactivation()==null) || 
             (this.dateDesactivation!=null &&
              this.dateDesactivation.equals(other.getDateDesactivation()))) &&
            ((this.etat==null && other.getEtat()==null) || 
             (this.etat!=null &&
              this.etat.equals(other.getEtat()))) &&
            ((this.ville==null && other.getVille()==null) || 
             (this.ville!=null &&
              this.ville.equals(other.getVille()))) &&
            ((this.idPays==null && other.getIdPays()==null) || 
             (this.idPays!=null &&
              this.idPays.equals(other.getIdPays()))) &&
            ((this.lieuDit==null && other.getLieuDit()==null) || 
             (this.lieuDit!=null &&
              this.lieuDit.equals(other.getLieuDit()))) &&
            ((this.codePostal==null && other.getCodePostal()==null) || 
             (this.codePostal!=null &&
              this.codePostal.equals(other.getCodePostal()))) &&
            ((this.listeLien==null && other.getListeLien()==null) || 
             (this.listeLien!=null &&
              java.util.Arrays.equals(this.listeLien, other.getListeLien())));
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
        if (getPays() != null) {
            _hashCode += getPays().hashCode();
        }
        if (getCommentaire() != null) {
            _hashCode += getCommentaire().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getNom() != null) {
            _hashCode += getNom().hashCode();
        }
        if (getActif() != null) {
            _hashCode += getActif().hashCode();
        }
        if (getConnectivite() != null) {
            _hashCode += getConnectivite().hashCode();
        }
        if (getIdArgos() != null) {
            _hashCode += getIdArgos().hashCode();
        }
        if (getDateModification() != null) {
            _hashCode += getDateModification().hashCode();
        }
        if (getCoordGPS() != null) {
            _hashCode += getCoordGPS().hashCode();
        }
        if (getVoie() != null) {
            _hashCode += getVoie().hashCode();
        }
        if (getComplement() != null) {
            _hashCode += getComplement().hashCode();
        }
        if (getDateDesactivation() != null) {
            _hashCode += getDateDesactivation().hashCode();
        }
        if (getEtat() != null) {
            _hashCode += getEtat().hashCode();
        }
        if (getVille() != null) {
            _hashCode += getVille().hashCode();
        }
        if (getIdPays() != null) {
            _hashCode += getIdPays().hashCode();
        }
        if (getLieuDit() != null) {
            _hashCode += getLieuDit().hashCode();
        }
        if (getCodePostal() != null) {
            _hashCode += getCodePostal().hashCode();
        }
        if (getListeLien() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListeLien());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListeLien(), i);
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
        new org.apache.axis.description.TypeDesc(WsSiteLightType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsSiteLightType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pays");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pays"));
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
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url"));
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
        elemField.setFieldName("actif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("connectivite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "connectivite"));
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
        elemField.setFieldName("coordGPS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coordGPS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("voie");
        elemField.setXmlName(new javax.xml.namespace.QName("", "voie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complement");
        elemField.setXmlName(new javax.xml.namespace.QName("", "complement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateDesactivation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateDesactivation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("etat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "etat"));
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
        elemField.setFieldName("idPays");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idPays"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lieuDit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lieuDit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codePostal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codePostal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeLien");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeLien"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsLienType"));
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
