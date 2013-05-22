package com.structis.vip.server.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.structis.vip.server.bean.domain.Collaborateur;

public class CollaborateurHandler extends DefaultHandler {

    private final String PERSONNE_COMPLEXE_TAG = "PersonneComplexe";
    private final String DATE_ENTREE_TAG = "dateEntree";
    private final String DATE_MAJ_TAG = "dateMaj";
    private final String DATE_SORTIE_TAG = "dateSortie";
    private final String ID_BYCN_TAG = "idBycn";
    private final String NIVEAU_HIERACHIQUE_TAG = "niveauHierachique";
    private final String SORTI_TAG = "sorti";
    private final String CIVILITE_TAG = "civilite";
    private final String DATE_NAISSANCE_TAG = "dateNaissance";
    private final String LIEU_NAISSANE_TAG = "lieuNaissane";
    private final String NATIONALITE_PRINCIPALE_TAG = "nationnalitePrincipale";
    private final String NOM_TAG = "nom";
    private final String PAYS_NAISSANCE_TAG = "paysNaissance";
    private final String PRENOM_TAG = "prenom";

    private List<Collaborateur> collaborateurs = new ArrayList<Collaborateur>();
    private Collaborateur collaborateur = null;

    private boolean dateEntree = false;
    private boolean dateMaj = false;
    private boolean dateSortie = false;
    private boolean idBycn = false;
    private boolean niveauHierachique = false;
    private boolean sorti = false;
    private boolean civilite = false;
    private boolean dateNaissance = false;
    private boolean lieuNaissane = false;
    private boolean nationnalitePrincipale = false;
    private boolean nom = false;
    private boolean paysNaissance = false;
    private boolean prenom = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(this.PERSONNE_COMPLEXE_TAG)) {
            this.collaborateur = new Collaborateur();
        }

        if (qName.equalsIgnoreCase(this.DATE_ENTREE_TAG)) {
            this.dateEntree = true;
        }

        if (qName.equalsIgnoreCase(this.DATE_MAJ_TAG)) {
            this.dateMaj = true;
        }

        if (qName.equalsIgnoreCase(this.DATE_SORTIE_TAG)) {
            this.dateSortie = true;
        }

        if (qName.equalsIgnoreCase(this.ID_BYCN_TAG)) {
            this.idBycn = true;
        }

        if (qName.equalsIgnoreCase(this.NIVEAU_HIERACHIQUE_TAG)) {
            this.niveauHierachique = true;
        }

        if (qName.equalsIgnoreCase(this.SORTI_TAG)) {
            this.sorti = true;
        }

        if (qName.equalsIgnoreCase(this.CIVILITE_TAG)) {
            this.civilite = true;
        }

        if (qName.equalsIgnoreCase(this.DATE_NAISSANCE_TAG)) {
            this.dateNaissance = true;
        }

        if (qName.equalsIgnoreCase(this.LIEU_NAISSANE_TAG)) {
            this.lieuNaissane = true;
        }

        if (qName.equalsIgnoreCase(this.NATIONALITE_PRINCIPALE_TAG)) {
            this.nationnalitePrincipale = true;
        }

        if (qName.equalsIgnoreCase(this.NOM_TAG)) {
            this.nom = true;
        }

        if (qName.equalsIgnoreCase(this.PAYS_NAISSANCE_TAG)) {
            this.paysNaissance = true;
        }

        if (qName.equalsIgnoreCase(this.PRENOM_TAG)) {
            this.prenom = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(this.DATE_ENTREE_TAG)) {
            this.dateEntree = false;
        }

        if (qName.equalsIgnoreCase(this.DATE_MAJ_TAG)) {
            this.dateMaj = false;
        }

        if (qName.equalsIgnoreCase(this.DATE_SORTIE_TAG)) {
            this.dateSortie = false;
        }

        if (qName.equalsIgnoreCase(this.ID_BYCN_TAG)) {
            this.idBycn = false;
        }

        if (qName.equalsIgnoreCase(this.NIVEAU_HIERACHIQUE_TAG)) {
            this.niveauHierachique = false;
        }

        if (qName.equalsIgnoreCase(this.SORTI_TAG)) {
            this.sorti = false;
        }

        if (qName.equalsIgnoreCase(this.CIVILITE_TAG)) {
            this.civilite = false;
        }

        if (qName.equalsIgnoreCase(this.DATE_NAISSANCE_TAG)) {
            this.dateNaissance = false;
        }

        if (qName.equalsIgnoreCase(this.LIEU_NAISSANE_TAG)) {
            this.lieuNaissane = false;
        }

        if (qName.equalsIgnoreCase(this.NATIONALITE_PRINCIPALE_TAG)) {
            this.nationnalitePrincipale = false;
        }

        if (qName.equalsIgnoreCase(this.NOM_TAG)) {
            this.nom = false;
        }

        if (qName.equalsIgnoreCase(this.PAYS_NAISSANCE_TAG)) {
            this.paysNaissance = false;
        }

        if (qName.equalsIgnoreCase(this.PRENOM_TAG)) {
            this.prenom = false;
        }

        if (qName.equalsIgnoreCase(this.PERSONNE_COMPLEXE_TAG)) {
            this.collaborateurs.add(this.collaborateur);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);

        if (this.dateEntree) {
            this.collaborateur.setDateEntree(CommonUtils.parseToDate(value));
        }

        if (this.dateMaj) {
            this.collaborateur.setDateMajRubis(CommonUtils.parseToDate(value));
        }

        if (this.dateSortie) {
            this.collaborateur.setDateSortie(CommonUtils.parseToDate(value));
        }

        if (this.idBycn) {
            this.collaborateur.setIdBycn(value);
        }

        if (this.niveauHierachique) {
            this.collaborateur.setNiveauHierarchique(value);
        }

        if (this.sorti) {
            this.collaborateur.setSorti(value);
        }

        if (this.civilite) {
            this.collaborateur.setCivilite(value);
        }

        if (this.dateNaissance) {
            this.collaborateur.setDateNaissance(CommonUtils.parseToDate(value));
        }

        if (this.lieuNaissane) {
            this.collaborateur.setLieuNaissance(value);
        }

        if (this.nationnalitePrincipale) {
            this.collaborateur.setNationality(value);
        }

        if (this.nom) {
            this.collaborateur.setLastname(value);
        }

        if (this.paysNaissance) {
        }

        if (this.prenom) {
            this.collaborateur.setFirstname(value);
        }
    }

    public List<Collaborateur> getCollaborateurs() {
        return this.collaborateurs;
    }
}
