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

    private boolean personneComplexe = false;
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
    	if(qName.equalsIgnoreCase(PERSONNE_COMPLEXE_TAG)) {
    		personneComplexe = true;
    		
    		collaborateur = new Collaborateur();
        }
    	
        if(qName.equalsIgnoreCase(DATE_ENTREE_TAG)) {
        	dateEntree = true;
        }

        if(qName.equalsIgnoreCase(DATE_MAJ_TAG)) {
            dateMaj = true;
        }

        if(qName.equalsIgnoreCase(DATE_SORTIE_TAG)) {
        	dateSortie = true;
        }

        if(qName.equalsIgnoreCase(ID_BYCN_TAG)) {
        	idBycn = true;
        }

        if(qName.equalsIgnoreCase(NIVEAU_HIERACHIQUE_TAG)) {
            niveauHierachique = true;
        }

        if(qName.equalsIgnoreCase(SORTI_TAG)) {
        	sorti = true;
        }

        if(qName.equalsIgnoreCase(CIVILITE_TAG)) {
            civilite = true;
        }
        
        if(qName.equalsIgnoreCase(DATE_NAISSANCE_TAG)) {
        	dateNaissance = true;
        }
        
        if(qName.equalsIgnoreCase(LIEU_NAISSANE_TAG)) {
        	lieuNaissane = true;
        }
        
        if(qName.equalsIgnoreCase(NATIONALITE_PRINCIPALE_TAG)) {
        	nationnalitePrincipale = true;
        }
        
        if(qName.equalsIgnoreCase(NOM_TAG)) {
        	nom = true;
        }
        
        if(qName.equalsIgnoreCase(PAYS_NAISSANCE_TAG)) {
        	paysNaissance = true;
        }
        
        if(qName.equalsIgnoreCase(PRENOM_TAG)) {
        	prenom = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase(DATE_ENTREE_TAG)) {
        	dateEntree = false;
        }

        if(qName.equalsIgnoreCase(DATE_MAJ_TAG)) {
            dateMaj = false;
        }

        if(qName.equalsIgnoreCase(DATE_SORTIE_TAG)) {
            dateSortie = false;
        }

        if(qName.equalsIgnoreCase(ID_BYCN_TAG)) {
            idBycn = false;
        }

        if(qName.equalsIgnoreCase(NIVEAU_HIERACHIQUE_TAG)) {
            niveauHierachique = false;
        }

        if(qName.equalsIgnoreCase(SORTI_TAG)) {
            sorti = false;
        }

        if(qName.equalsIgnoreCase(CIVILITE_TAG)) {
            civilite = false;
        }
        
        if(qName.equalsIgnoreCase(DATE_NAISSANCE_TAG)) {
            dateNaissance = false;
        }
        
        if(qName.equalsIgnoreCase(LIEU_NAISSANE_TAG)) {
            lieuNaissane = false;
        }
        
        if(qName.equalsIgnoreCase(NATIONALITE_PRINCIPALE_TAG)) {
            nationnalitePrincipale = false;
        }
        
        if(qName.equalsIgnoreCase(NOM_TAG)) {
            nom = false;
        }
        
        if(qName.equalsIgnoreCase(PAYS_NAISSANCE_TAG)) {
            paysNaissance = false;
        }
        
        if(qName.equalsIgnoreCase(PRENOM_TAG)) {
            prenom = false;
        }
        
        if(qName.equalsIgnoreCase(PERSONNE_COMPLEXE_TAG)) {
        	collaborateurs.add(collaborateur);
    		personneComplexe = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    	String value = new String(ch, start, length);
    	
        if(dateEntree) {
        	collaborateur.setDateEntree(CommonUtils.parseToDate(value));
        }

        if(dateMaj) {
        	collaborateur.setDateMajRubis(CommonUtils.parseToDate(value));
        }

        if(dateSortie) {
        	collaborateur.setDateSortie(CommonUtils.parseToDate(value));
        }

        if(idBycn) {
        	collaborateur.setIdBycn(value);
        }
        
        if(niveauHierachique) {
        	collaborateur.setNiveauHierarchique(value);
        }
        
        if(sorti) {
        	collaborateur.setSorti(value);
        }
        
        if(civilite) {
        	collaborateur.setCivilite(value);
        }
        
        if(dateNaissance) {
        	collaborateur.setDateNaissance(CommonUtils.parseToDate(value));
        }
        
        if(lieuNaissane) {
        	collaborateur.setLieuNaissance(value);
        }
        
        if(nationnalitePrincipale) {
        	collaborateur.setNationality(value);
        }
        
        if(nom) {
        	collaborateur.setLastname(value);
        }
        
        if(paysNaissance) {
        }
        
        if(prenom) {
        	collaborateur.setFirstname(value);
        }
    }

    public List<Collaborateur> getCollaborateurs() {
    	return this.collaborateurs;
    }
}
