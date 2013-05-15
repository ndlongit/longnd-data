/**
 * WsPersonneType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsPersonneType  implements java.io.Serializable {
    private com.structis.argos.ws.WsPersonneMissionType[] listeMission;

    private java.lang.String filiere;

    private java.lang.String designationEmployeur;

    private java.lang.String dateNaissance;

    private java.lang.String photo;

    private java.lang.String type;

    private java.lang.String url;

    private java.lang.String abreviationEmployeur;

    private java.lang.String nom;

    private java.lang.String actif;

    private java.lang.String initiales;

    private java.lang.String idArgos;

    private java.lang.String dateModification;

    private java.lang.String prenom;

    private com.structis.argos.ws.WsLienType[] listeLien;

    private com.structis.argos.ws.WsDiplomeType[] listeDiplome;

    private java.lang.String login;

    private java.lang.String surnom;

    private java.lang.String hierarchie;

    private com.structis.argos.ws.WsCommunautePersonneType[] listeCommunaute;

    private java.lang.String nomJeuneFille;

    private java.lang.String dateDesactivation;

    private java.lang.String dateEntreeGroupe;

    private com.structis.argos.ws.WsTopicSiteType[] listeSite;

    private java.lang.String dateFinMission;

    private com.structis.argos.ws.WsRelationPersonneType[] listeRelationPersonne;

    private java.lang.String qualite;

    private java.lang.String domain;

    private java.lang.String idEmployeur;

    public WsPersonneType() {
    }

    public WsPersonneType(
           com.structis.argos.ws.WsPersonneMissionType[] listeMission,
           java.lang.String filiere,
           java.lang.String designationEmployeur,
           java.lang.String dateNaissance,
           java.lang.String photo,
           java.lang.String type,
           java.lang.String url,
           java.lang.String abreviationEmployeur,
           java.lang.String nom,
           java.lang.String actif,
           java.lang.String initiales,
           java.lang.String idArgos,
           java.lang.String dateModification,
           java.lang.String prenom,
           com.structis.argos.ws.WsLienType[] listeLien,
           com.structis.argos.ws.WsDiplomeType[] listeDiplome,
           java.lang.String login,
           java.lang.String surnom,
           java.lang.String hierarchie,
           com.structis.argos.ws.WsCommunautePersonneType[] listeCommunaute,
           java.lang.String nomJeuneFille,
           java.lang.String dateDesactivation,
           java.lang.String dateEntreeGroupe,
           com.structis.argos.ws.WsTopicSiteType[] listeSite,
           java.lang.String dateFinMission,
           com.structis.argos.ws.WsRelationPersonneType[] listeRelationPersonne,
           java.lang.String qualite,
           java.lang.String domain,
           java.lang.String idEmployeur) {
           this.listeMission = listeMission;
           this.filiere = filiere;
           this.designationEmployeur = designationEmployeur;
           this.dateNaissance = dateNaissance;
           this.photo = photo;
           this.type = type;
           this.url = url;
           this.abreviationEmployeur = abreviationEmployeur;
           this.nom = nom;
           this.actif = actif;
           this.initiales = initiales;
           this.idArgos = idArgos;
           this.dateModification = dateModification;
           this.prenom = prenom;
           this.listeLien = listeLien;
           this.listeDiplome = listeDiplome;
           this.login = login;
           this.surnom = surnom;
           this.hierarchie = hierarchie;
           this.listeCommunaute = listeCommunaute;
           this.nomJeuneFille = nomJeuneFille;
           this.dateDesactivation = dateDesactivation;
           this.dateEntreeGroupe = dateEntreeGroupe;
           this.listeSite = listeSite;
           this.dateFinMission = dateFinMission;
           this.listeRelationPersonne = listeRelationPersonne;
           this.qualite = qualite;
           this.domain = domain;
           this.idEmployeur = idEmployeur;
    }


    /**
     * Gets the listeMission value for this WsPersonneType.
     * 
     * @return listeMission
     */
    public com.structis.argos.ws.WsPersonneMissionType[] getListeMission() {
        return listeMission;
    }


    /**
     * Sets the listeMission value for this WsPersonneType.
     * 
     * @param listeMission
     */
    public void setListeMission(com.structis.argos.ws.WsPersonneMissionType[] listeMission) {
        this.listeMission = listeMission;
    }


    /**
     * Gets the filiere value for this WsPersonneType.
     * 
     * @return filiere
     */
    public java.lang.String getFiliere() {
        return filiere;
    }


    /**
     * Sets the filiere value for this WsPersonneType.
     * 
     * @param filiere
     */
    public void setFiliere(java.lang.String filiere) {
        this.filiere = filiere;
    }


    /**
     * Gets the designationEmployeur value for this WsPersonneType.
     * 
     * @return designationEmployeur
     */
    public java.lang.String getDesignationEmployeur() {
        return designationEmployeur;
    }


    /**
     * Sets the designationEmployeur value for this WsPersonneType.
     * 
     * @param designationEmployeur
     */
    public void setDesignationEmployeur(java.lang.String designationEmployeur) {
        this.designationEmployeur = designationEmployeur;
    }


    /**
     * Gets the dateNaissance value for this WsPersonneType.
     * 
     * @return dateNaissance
     */
    public java.lang.String getDateNaissance() {
        return dateNaissance;
    }


    /**
     * Sets the dateNaissance value for this WsPersonneType.
     * 
     * @param dateNaissance
     */
    public void setDateNaissance(java.lang.String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }


    /**
     * Gets the photo value for this WsPersonneType.
     * 
     * @return photo
     */
    public java.lang.String getPhoto() {
        return photo;
    }


    /**
     * Sets the photo value for this WsPersonneType.
     * 
     * @param photo
     */
    public void setPhoto(java.lang.String photo) {
        this.photo = photo;
    }


    /**
     * Gets the type value for this WsPersonneType.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this WsPersonneType.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the url value for this WsPersonneType.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this WsPersonneType.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the abreviationEmployeur value for this WsPersonneType.
     * 
     * @return abreviationEmployeur
     */
    public java.lang.String getAbreviationEmployeur() {
        return abreviationEmployeur;
    }


    /**
     * Sets the abreviationEmployeur value for this WsPersonneType.
     * 
     * @param abreviationEmployeur
     */
    public void setAbreviationEmployeur(java.lang.String abreviationEmployeur) {
        this.abreviationEmployeur = abreviationEmployeur;
    }


    /**
     * Gets the nom value for this WsPersonneType.
     * 
     * @return nom
     */
    public java.lang.String getNom() {
        return nom;
    }


    /**
     * Sets the nom value for this WsPersonneType.
     * 
     * @param nom
     */
    public void setNom(java.lang.String nom) {
        this.nom = nom;
    }


    /**
     * Gets the actif value for this WsPersonneType.
     * 
     * @return actif
     */
    public java.lang.String getActif() {
        return actif;
    }


    /**
     * Sets the actif value for this WsPersonneType.
     * 
     * @param actif
     */
    public void setActif(java.lang.String actif) {
        this.actif = actif;
    }


    /**
     * Gets the initiales value for this WsPersonneType.
     * 
     * @return initiales
     */
    public java.lang.String getInitiales() {
        return initiales;
    }


    /**
     * Sets the initiales value for this WsPersonneType.
     * 
     * @param initiales
     */
    public void setInitiales(java.lang.String initiales) {
        this.initiales = initiales;
    }


    /**
     * Gets the idArgos value for this WsPersonneType.
     * 
     * @return idArgos
     */
    public java.lang.String getIdArgos() {
        return idArgos;
    }


    /**
     * Sets the idArgos value for this WsPersonneType.
     * 
     * @param idArgos
     */
    public void setIdArgos(java.lang.String idArgos) {
        this.idArgos = idArgos;
    }


    /**
     * Gets the dateModification value for this WsPersonneType.
     * 
     * @return dateModification
     */
    public java.lang.String getDateModification() {
        return dateModification;
    }


    /**
     * Sets the dateModification value for this WsPersonneType.
     * 
     * @param dateModification
     */
    public void setDateModification(java.lang.String dateModification) {
        this.dateModification = dateModification;
    }


    /**
     * Gets the prenom value for this WsPersonneType.
     * 
     * @return prenom
     */
    public java.lang.String getPrenom() {
        return prenom;
    }


    /**
     * Sets the prenom value for this WsPersonneType.
     * 
     * @param prenom
     */
    public void setPrenom(java.lang.String prenom) {
        this.prenom = prenom;
    }


    /**
     * Gets the listeLien value for this WsPersonneType.
     * 
     * @return listeLien
     */
    public com.structis.argos.ws.WsLienType[] getListeLien() {
        return listeLien;
    }


    /**
     * Sets the listeLien value for this WsPersonneType.
     * 
     * @param listeLien
     */
    public void setListeLien(com.structis.argos.ws.WsLienType[] listeLien) {
        this.listeLien = listeLien;
    }


    /**
     * Gets the listeDiplome value for this WsPersonneType.
     * 
     * @return listeDiplome
     */
    public com.structis.argos.ws.WsDiplomeType[] getListeDiplome() {
        return listeDiplome;
    }


    /**
     * Sets the listeDiplome value for this WsPersonneType.
     * 
     * @param listeDiplome
     */
    public void setListeDiplome(com.structis.argos.ws.WsDiplomeType[] listeDiplome) {
        this.listeDiplome = listeDiplome;
    }


    /**
     * Gets the login value for this WsPersonneType.
     * 
     * @return login
     */
    public java.lang.String getLogin() {
        return login;
    }


    /**
     * Sets the login value for this WsPersonneType.
     * 
     * @param login
     */
    public void setLogin(java.lang.String login) {
        this.login = login;
    }


    /**
     * Gets the surnom value for this WsPersonneType.
     * 
     * @return surnom
     */
    public java.lang.String getSurnom() {
        return surnom;
    }


    /**
     * Sets the surnom value for this WsPersonneType.
     * 
     * @param surnom
     */
    public void setSurnom(java.lang.String surnom) {
        this.surnom = surnom;
    }


    /**
     * Gets the hierarchie value for this WsPersonneType.
     * 
     * @return hierarchie
     */
    public java.lang.String getHierarchie() {
        return hierarchie;
    }


    /**
     * Sets the hierarchie value for this WsPersonneType.
     * 
     * @param hierarchie
     */
    public void setHierarchie(java.lang.String hierarchie) {
        this.hierarchie = hierarchie;
    }


    /**
     * Gets the listeCommunaute value for this WsPersonneType.
     * 
     * @return listeCommunaute
     */
    public com.structis.argos.ws.WsCommunautePersonneType[] getListeCommunaute() {
        return listeCommunaute;
    }


    /**
     * Sets the listeCommunaute value for this WsPersonneType.
     * 
     * @param listeCommunaute
     */
    public void setListeCommunaute(com.structis.argos.ws.WsCommunautePersonneType[] listeCommunaute) {
        this.listeCommunaute = listeCommunaute;
    }


    /**
     * Gets the nomJeuneFille value for this WsPersonneType.
     * 
     * @return nomJeuneFille
     */
    public java.lang.String getNomJeuneFille() {
        return nomJeuneFille;
    }


    /**
     * Sets the nomJeuneFille value for this WsPersonneType.
     * 
     * @param nomJeuneFille
     */
    public void setNomJeuneFille(java.lang.String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }


    /**
     * Gets the dateDesactivation value for this WsPersonneType.
     * 
     * @return dateDesactivation
     */
    public java.lang.String getDateDesactivation() {
        return dateDesactivation;
    }


    /**
     * Sets the dateDesactivation value for this WsPersonneType.
     * 
     * @param dateDesactivation
     */
    public void setDateDesactivation(java.lang.String dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }


    /**
     * Gets the dateEntreeGroupe value for this WsPersonneType.
     * 
     * @return dateEntreeGroupe
     */
    public java.lang.String getDateEntreeGroupe() {
        return dateEntreeGroupe;
    }


    /**
     * Sets the dateEntreeGroupe value for this WsPersonneType.
     * 
     * @param dateEntreeGroupe
     */
    public void setDateEntreeGroupe(java.lang.String dateEntreeGroupe) {
        this.dateEntreeGroupe = dateEntreeGroupe;
    }


    /**
     * Gets the listeSite value for this WsPersonneType.
     * 
     * @return listeSite
     */
    public com.structis.argos.ws.WsTopicSiteType[] getListeSite() {
        return listeSite;
    }


    /**
     * Sets the listeSite value for this WsPersonneType.
     * 
     * @param listeSite
     */
    public void setListeSite(com.structis.argos.ws.WsTopicSiteType[] listeSite) {
        this.listeSite = listeSite;
    }


    /**
     * Gets the dateFinMission value for this WsPersonneType.
     * 
     * @return dateFinMission
     */
    public java.lang.String getDateFinMission() {
        return dateFinMission;
    }


    /**
     * Sets the dateFinMission value for this WsPersonneType.
     * 
     * @param dateFinMission
     */
    public void setDateFinMission(java.lang.String dateFinMission) {
        this.dateFinMission = dateFinMission;
    }


    /**
     * Gets the listeRelationPersonne value for this WsPersonneType.
     * 
     * @return listeRelationPersonne
     */
    public com.structis.argos.ws.WsRelationPersonneType[] getListeRelationPersonne() {
        return listeRelationPersonne;
    }


    /**
     * Sets the listeRelationPersonne value for this WsPersonneType.
     * 
     * @param listeRelationPersonne
     */
    public void setListeRelationPersonne(com.structis.argos.ws.WsRelationPersonneType[] listeRelationPersonne) {
        this.listeRelationPersonne = listeRelationPersonne;
    }


    /**
     * Gets the qualite value for this WsPersonneType.
     * 
     * @return qualite
     */
    public java.lang.String getQualite() {
        return qualite;
    }


    /**
     * Sets the qualite value for this WsPersonneType.
     * 
     * @param qualite
     */
    public void setQualite(java.lang.String qualite) {
        this.qualite = qualite;
    }


    /**
     * Gets the domain value for this WsPersonneType.
     * 
     * @return domain
     */
    public java.lang.String getDomain() {
        return domain;
    }


    /**
     * Sets the domain value for this WsPersonneType.
     * 
     * @param domain
     */
    public void setDomain(java.lang.String domain) {
        this.domain = domain;
    }


    /**
     * Gets the idEmployeur value for this WsPersonneType.
     * 
     * @return idEmployeur
     */
    public java.lang.String getIdEmployeur() {
        return idEmployeur;
    }


    /**
     * Sets the idEmployeur value for this WsPersonneType.
     * 
     * @param idEmployeur
     */
    public void setIdEmployeur(java.lang.String idEmployeur) {
        this.idEmployeur = idEmployeur;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsPersonneType)) return false;
        WsPersonneType other = (WsPersonneType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listeMission==null && other.getListeMission()==null) || 
             (this.listeMission!=null &&
              java.util.Arrays.equals(this.listeMission, other.getListeMission()))) &&
            ((this.filiere==null && other.getFiliere()==null) || 
             (this.filiere!=null &&
              this.filiere.equals(other.getFiliere()))) &&
            ((this.designationEmployeur==null && other.getDesignationEmployeur()==null) || 
             (this.designationEmployeur!=null &&
              this.designationEmployeur.equals(other.getDesignationEmployeur()))) &&
            ((this.dateNaissance==null && other.getDateNaissance()==null) || 
             (this.dateNaissance!=null &&
              this.dateNaissance.equals(other.getDateNaissance()))) &&
            ((this.photo==null && other.getPhoto()==null) || 
             (this.photo!=null &&
              this.photo.equals(other.getPhoto()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.abreviationEmployeur==null && other.getAbreviationEmployeur()==null) || 
             (this.abreviationEmployeur!=null &&
              this.abreviationEmployeur.equals(other.getAbreviationEmployeur()))) &&
            ((this.nom==null && other.getNom()==null) || 
             (this.nom!=null &&
              this.nom.equals(other.getNom()))) &&
            ((this.actif==null && other.getActif()==null) || 
             (this.actif!=null &&
              this.actif.equals(other.getActif()))) &&
            ((this.initiales==null && other.getInitiales()==null) || 
             (this.initiales!=null &&
              this.initiales.equals(other.getInitiales()))) &&
            ((this.idArgos==null && other.getIdArgos()==null) || 
             (this.idArgos!=null &&
              this.idArgos.equals(other.getIdArgos()))) &&
            ((this.dateModification==null && other.getDateModification()==null) || 
             (this.dateModification!=null &&
              this.dateModification.equals(other.getDateModification()))) &&
            ((this.prenom==null && other.getPrenom()==null) || 
             (this.prenom!=null &&
              this.prenom.equals(other.getPrenom()))) &&
            ((this.listeLien==null && other.getListeLien()==null) || 
             (this.listeLien!=null &&
              java.util.Arrays.equals(this.listeLien, other.getListeLien()))) &&
            ((this.listeDiplome==null && other.getListeDiplome()==null) || 
             (this.listeDiplome!=null &&
              java.util.Arrays.equals(this.listeDiplome, other.getListeDiplome()))) &&
            ((this.login==null && other.getLogin()==null) || 
             (this.login!=null &&
              this.login.equals(other.getLogin()))) &&
            ((this.surnom==null && other.getSurnom()==null) || 
             (this.surnom!=null &&
              this.surnom.equals(other.getSurnom()))) &&
            ((this.hierarchie==null && other.getHierarchie()==null) || 
             (this.hierarchie!=null &&
              this.hierarchie.equals(other.getHierarchie()))) &&
            ((this.listeCommunaute==null && other.getListeCommunaute()==null) || 
             (this.listeCommunaute!=null &&
              java.util.Arrays.equals(this.listeCommunaute, other.getListeCommunaute()))) &&
            ((this.nomJeuneFille==null && other.getNomJeuneFille()==null) || 
             (this.nomJeuneFille!=null &&
              this.nomJeuneFille.equals(other.getNomJeuneFille()))) &&
            ((this.dateDesactivation==null && other.getDateDesactivation()==null) || 
             (this.dateDesactivation!=null &&
              this.dateDesactivation.equals(other.getDateDesactivation()))) &&
            ((this.dateEntreeGroupe==null && other.getDateEntreeGroupe()==null) || 
             (this.dateEntreeGroupe!=null &&
              this.dateEntreeGroupe.equals(other.getDateEntreeGroupe()))) &&
            ((this.listeSite==null && other.getListeSite()==null) || 
             (this.listeSite!=null &&
              java.util.Arrays.equals(this.listeSite, other.getListeSite()))) &&
            ((this.dateFinMission==null && other.getDateFinMission()==null) || 
             (this.dateFinMission!=null &&
              this.dateFinMission.equals(other.getDateFinMission()))) &&
            ((this.listeRelationPersonne==null && other.getListeRelationPersonne()==null) || 
             (this.listeRelationPersonne!=null &&
              java.util.Arrays.equals(this.listeRelationPersonne, other.getListeRelationPersonne()))) &&
            ((this.qualite==null && other.getQualite()==null) || 
             (this.qualite!=null &&
              this.qualite.equals(other.getQualite()))) &&
            ((this.domain==null && other.getDomain()==null) || 
             (this.domain!=null &&
              this.domain.equals(other.getDomain()))) &&
            ((this.idEmployeur==null && other.getIdEmployeur()==null) || 
             (this.idEmployeur!=null &&
              this.idEmployeur.equals(other.getIdEmployeur())));
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
        if (getListeMission() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListeMission());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListeMission(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFiliere() != null) {
            _hashCode += getFiliere().hashCode();
        }
        if (getDesignationEmployeur() != null) {
            _hashCode += getDesignationEmployeur().hashCode();
        }
        if (getDateNaissance() != null) {
            _hashCode += getDateNaissance().hashCode();
        }
        if (getPhoto() != null) {
            _hashCode += getPhoto().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getAbreviationEmployeur() != null) {
            _hashCode += getAbreviationEmployeur().hashCode();
        }
        if (getNom() != null) {
            _hashCode += getNom().hashCode();
        }
        if (getActif() != null) {
            _hashCode += getActif().hashCode();
        }
        if (getInitiales() != null) {
            _hashCode += getInitiales().hashCode();
        }
        if (getIdArgos() != null) {
            _hashCode += getIdArgos().hashCode();
        }
        if (getDateModification() != null) {
            _hashCode += getDateModification().hashCode();
        }
        if (getPrenom() != null) {
            _hashCode += getPrenom().hashCode();
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
        if (getListeDiplome() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListeDiplome());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListeDiplome(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLogin() != null) {
            _hashCode += getLogin().hashCode();
        }
        if (getSurnom() != null) {
            _hashCode += getSurnom().hashCode();
        }
        if (getHierarchie() != null) {
            _hashCode += getHierarchie().hashCode();
        }
        if (getListeCommunaute() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListeCommunaute());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListeCommunaute(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNomJeuneFille() != null) {
            _hashCode += getNomJeuneFille().hashCode();
        }
        if (getDateDesactivation() != null) {
            _hashCode += getDateDesactivation().hashCode();
        }
        if (getDateEntreeGroupe() != null) {
            _hashCode += getDateEntreeGroupe().hashCode();
        }
        if (getListeSite() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListeSite());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListeSite(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDateFinMission() != null) {
            _hashCode += getDateFinMission().hashCode();
        }
        if (getListeRelationPersonne() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListeRelationPersonne());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListeRelationPersonne(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getQualite() != null) {
            _hashCode += getQualite().hashCode();
        }
        if (getDomain() != null) {
            _hashCode += getDomain().hashCode();
        }
        if (getIdEmployeur() != null) {
            _hashCode += getIdEmployeur().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsPersonneType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsPersonneType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeMission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeMission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsPersonneMissionType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filiere");
        elemField.setXmlName(new javax.xml.namespace.QName("", "filiere"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("designationEmployeur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "designationEmployeur"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateNaissance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateNaissance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("photo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "photo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
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
        elemField.setFieldName("abreviationEmployeur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abreviationEmployeur"));
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
        elemField.setFieldName("initiales");
        elemField.setXmlName(new javax.xml.namespace.QName("", "initiales"));
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
        elemField.setFieldName("prenom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prenom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeLien");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeLien"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsLienType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeDiplome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeDiplome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsDiplomeType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("login");
        elemField.setXmlName(new javax.xml.namespace.QName("", "login"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surnom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "surnom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hierarchie");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hierarchie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeCommunaute");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeCommunaute"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsCommunautePersonneType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomJeuneFille");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomJeuneFille"));
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
        elemField.setFieldName("dateEntreeGroupe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateEntreeGroupe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeSite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeSite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsTopicSiteType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateFinMission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateFinMission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listeRelationPersonne");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listeRelationPersonne"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsRelationPersonneType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qualite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "qualite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domain");
        elemField.setXmlName(new javax.xml.namespace.QName("", "domain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idEmployeur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idEmployeur"));
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
