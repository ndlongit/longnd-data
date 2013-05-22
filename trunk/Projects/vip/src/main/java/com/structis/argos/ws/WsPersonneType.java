/**
 * WsPersonneType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsPersonneType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

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

    public WsPersonneType(com.structis.argos.ws.WsPersonneMissionType[] listeMission, java.lang.String filiere,
            java.lang.String designationEmployeur, java.lang.String dateNaissance, java.lang.String photo, java.lang.String type,
            java.lang.String url, java.lang.String abreviationEmployeur, java.lang.String nom, java.lang.String actif, java.lang.String initiales,
            java.lang.String idArgos, java.lang.String dateModification, java.lang.String prenom, com.structis.argos.ws.WsLienType[] listeLien,
            com.structis.argos.ws.WsDiplomeType[] listeDiplome, java.lang.String login, java.lang.String surnom, java.lang.String hierarchie,
            com.structis.argos.ws.WsCommunautePersonneType[] listeCommunaute, java.lang.String nomJeuneFille, java.lang.String dateDesactivation,
            java.lang.String dateEntreeGroupe, com.structis.argos.ws.WsTopicSiteType[] listeSite, java.lang.String dateFinMission,
            com.structis.argos.ws.WsRelationPersonneType[] listeRelationPersonne, java.lang.String qualite, java.lang.String domain,
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
        return this.listeMission;
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
        return this.filiere;
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
        return this.designationEmployeur;
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
        return this.dateNaissance;
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
        return this.photo;
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
        return this.type;
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
        return this.url;
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
        return this.abreviationEmployeur;
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
        return this.nom;
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
        return this.actif;
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
        return this.initiales;
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
        return this.idArgos;
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
        return this.dateModification;
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
        return this.prenom;
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
        return this.listeLien;
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
        return this.listeDiplome;
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
        return this.login;
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
        return this.surnom;
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
        return this.hierarchie;
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
        return this.listeCommunaute;
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
        return this.nomJeuneFille;
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
        return this.dateDesactivation;
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
        return this.dateEntreeGroupe;
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
        return this.listeSite;
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
        return this.dateFinMission;
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
        return this.listeRelationPersonne;
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
        return this.qualite;
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
        return this.domain;
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
        return this.idEmployeur;
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

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsPersonneType))
            return false;
        WsPersonneType other = (WsPersonneType) obj;
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
                && ((this.listeMission == null && other.getListeMission() == null) || (this.listeMission != null && java.util.Arrays.equals(
                        this.listeMission, other.getListeMission())))
                && ((this.filiere == null && other.getFiliere() == null) || (this.filiere != null && this.filiere.equals(other.getFiliere())))
                && ((this.designationEmployeur == null && other.getDesignationEmployeur() == null) || (this.designationEmployeur != null && this.designationEmployeur
                        .equals(other.getDesignationEmployeur())))
                && ((this.dateNaissance == null && other.getDateNaissance() == null) || (this.dateNaissance != null && this.dateNaissance
                        .equals(other.getDateNaissance())))
                && ((this.photo == null && other.getPhoto() == null) || (this.photo != null && this.photo.equals(other.getPhoto())))
                && ((this.type == null && other.getType() == null) || (this.type != null && this.type.equals(other.getType())))
                && ((this.url == null && other.getUrl() == null) || (this.url != null && this.url.equals(other.getUrl())))
                && ((this.abreviationEmployeur == null && other.getAbreviationEmployeur() == null) || (this.abreviationEmployeur != null && this.abreviationEmployeur
                        .equals(other.getAbreviationEmployeur())))
                && ((this.nom == null && other.getNom() == null) || (this.nom != null && this.nom.equals(other.getNom())))
                && ((this.actif == null && other.getActif() == null) || (this.actif != null && this.actif.equals(other.getActif())))
                && ((this.initiales == null && other.getInitiales() == null) || (this.initiales != null && this.initiales
                        .equals(other.getInitiales())))
                && ((this.idArgos == null && other.getIdArgos() == null) || (this.idArgos != null && this.idArgos.equals(other.getIdArgos())))
                && ((this.dateModification == null && other.getDateModification() == null) || (this.dateModification != null && this.dateModification
                        .equals(other.getDateModification())))
                && ((this.prenom == null && other.getPrenom() == null) || (this.prenom != null && this.prenom.equals(other.getPrenom())))
                && ((this.listeLien == null && other.getListeLien() == null) || (this.listeLien != null && java.util.Arrays.equals(this.listeLien,
                        other.getListeLien())))
                && ((this.listeDiplome == null && other.getListeDiplome() == null) || (this.listeDiplome != null && java.util.Arrays.equals(
                        this.listeDiplome, other.getListeDiplome())))
                && ((this.login == null && other.getLogin() == null) || (this.login != null && this.login.equals(other.getLogin())))
                && ((this.surnom == null && other.getSurnom() == null) || (this.surnom != null && this.surnom.equals(other.getSurnom())))
                && ((this.hierarchie == null && other.getHierarchie() == null) || (this.hierarchie != null && this.hierarchie.equals(other
                        .getHierarchie())))
                && ((this.listeCommunaute == null && other.getListeCommunaute() == null) || (this.listeCommunaute != null && java.util.Arrays.equals(
                        this.listeCommunaute, other.getListeCommunaute())))
                && ((this.nomJeuneFille == null && other.getNomJeuneFille() == null) || (this.nomJeuneFille != null && this.nomJeuneFille
                        .equals(other.getNomJeuneFille())))
                && ((this.dateDesactivation == null && other.getDateDesactivation() == null) || (this.dateDesactivation != null && this.dateDesactivation
                        .equals(other.getDateDesactivation())))
                && ((this.dateEntreeGroupe == null && other.getDateEntreeGroupe() == null) || (this.dateEntreeGroupe != null && this.dateEntreeGroupe
                        .equals(other.getDateEntreeGroupe())))
                && ((this.listeSite == null && other.getListeSite() == null) || (this.listeSite != null && java.util.Arrays.equals(this.listeSite,
                        other.getListeSite())))
                && ((this.dateFinMission == null && other.getDateFinMission() == null) || (this.dateFinMission != null && this.dateFinMission
                        .equals(other.getDateFinMission())))
                && ((this.listeRelationPersonne == null && other.getListeRelationPersonne() == null) || (this.listeRelationPersonne != null && java.util.Arrays
                        .equals(this.listeRelationPersonne, other.getListeRelationPersonne())))
                && ((this.qualite == null && other.getQualite() == null) || (this.qualite != null && this.qualite.equals(other.getQualite())))
                && ((this.domain == null && other.getDomain() == null) || (this.domain != null && this.domain.equals(other.getDomain())))
                && ((this.idEmployeur == null && other.getIdEmployeur() == null) || (this.idEmployeur != null && this.idEmployeur.equals(other
                        .getIdEmployeur())));
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
        if (this.getListeMission() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeMission()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeMission(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (this.getFiliere() != null) {
            _hashCode += this.getFiliere().hashCode();
        }
        if (this.getDesignationEmployeur() != null) {
            _hashCode += this.getDesignationEmployeur().hashCode();
        }
        if (this.getDateNaissance() != null) {
            _hashCode += this.getDateNaissance().hashCode();
        }
        if (this.getPhoto() != null) {
            _hashCode += this.getPhoto().hashCode();
        }
        if (this.getType() != null) {
            _hashCode += this.getType().hashCode();
        }
        if (this.getUrl() != null) {
            _hashCode += this.getUrl().hashCode();
        }
        if (this.getAbreviationEmployeur() != null) {
            _hashCode += this.getAbreviationEmployeur().hashCode();
        }
        if (this.getNom() != null) {
            _hashCode += this.getNom().hashCode();
        }
        if (this.getActif() != null) {
            _hashCode += this.getActif().hashCode();
        }
        if (this.getInitiales() != null) {
            _hashCode += this.getInitiales().hashCode();
        }
        if (this.getIdArgos() != null) {
            _hashCode += this.getIdArgos().hashCode();
        }
        if (this.getDateModification() != null) {
            _hashCode += this.getDateModification().hashCode();
        }
        if (this.getPrenom() != null) {
            _hashCode += this.getPrenom().hashCode();
        }
        if (this.getListeLien() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeLien()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeLien(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (this.getListeDiplome() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeDiplome()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeDiplome(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (this.getLogin() != null) {
            _hashCode += this.getLogin().hashCode();
        }
        if (this.getSurnom() != null) {
            _hashCode += this.getSurnom().hashCode();
        }
        if (this.getHierarchie() != null) {
            _hashCode += this.getHierarchie().hashCode();
        }
        if (this.getListeCommunaute() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeCommunaute()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeCommunaute(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (this.getNomJeuneFille() != null) {
            _hashCode += this.getNomJeuneFille().hashCode();
        }
        if (this.getDateDesactivation() != null) {
            _hashCode += this.getDateDesactivation().hashCode();
        }
        if (this.getDateEntreeGroupe() != null) {
            _hashCode += this.getDateEntreeGroupe().hashCode();
        }
        if (this.getListeSite() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeSite()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeSite(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (this.getDateFinMission() != null) {
            _hashCode += this.getDateFinMission().hashCode();
        }
        if (this.getListeRelationPersonne() != null) {
            for (int i = 0; i < java.lang.reflect.Array.getLength(this.getListeRelationPersonne()); i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(this.getListeRelationPersonne(), i);
                if (obj != null && !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (this.getQualite() != null) {
            _hashCode += this.getQualite().hashCode();
        }
        if (this.getDomain() != null) {
            _hashCode += this.getDomain().hashCode();
        }
        if (this.getIdEmployeur() != null) {
            _hashCode += this.getIdEmployeur().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsPersonneType.class, true);

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
