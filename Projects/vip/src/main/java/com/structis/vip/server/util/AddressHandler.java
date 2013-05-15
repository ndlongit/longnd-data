package com.structis.vip.server.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.structis.vip.shared.model.AddressModel;

public class AddressHandler extends DefaultHandler {
	private final String ADDRESS_TAG = "Adresse";
	private final String BUREAUDISTRIBUTEUR_TAG = "bureauDistributeur";
    private final String CODEPOSTAL_TAG = "codePostal";
    private final String COMPLEMENTADRESSE_TAG = "complementAdresse";
    private final String ID_BYCN_TAG = "idbycn";
    private final String NUMBERORUE_TAG = "numeroRue";
    private final String PAYS_TAG = "pays";
    private final String VILLE_TAG = "ville";

    private AddressModel address = null;

    private boolean adresse = false;
    private boolean bureauDistributeur = false;
    private boolean codePostal = false;
    private boolean complementAdresse = false;
    private boolean idbycn = false;
    private boolean numeroRue = false;
    private boolean pays = false;
    private boolean ville = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if(qName.equalsIgnoreCase(ADDRESS_TAG)) {
    		adresse = true;
    		
    		address = new AddressModel();
        }
    	
        if(qName.equalsIgnoreCase(BUREAUDISTRIBUTEUR_TAG)) {
        	bureauDistributeur = true;
        }

        if(qName.equalsIgnoreCase(CODEPOSTAL_TAG)) {
        	codePostal = true;
        }

        if(qName.equalsIgnoreCase(COMPLEMENTADRESSE_TAG)) {
        	complementAdresse = true;
        }

        if(qName.equalsIgnoreCase(ID_BYCN_TAG)) {
        	idbycn = true;
        }

        if(qName.equalsIgnoreCase(NUMBERORUE_TAG)) {
        	numeroRue = true;
        }

        if(qName.equalsIgnoreCase(PAYS_TAG)) {
        	pays = true;
        }

        if(qName.equalsIgnoreCase(VILLE_TAG)) {
        	ville = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase(BUREAUDISTRIBUTEUR_TAG)) {
        	bureauDistributeur = false;
        }

        if(qName.equalsIgnoreCase(CODEPOSTAL_TAG)) {
        	codePostal = false;
        }

        if(qName.equalsIgnoreCase(COMPLEMENTADRESSE_TAG)) {
        	complementAdresse = false;
        }

        if(qName.equalsIgnoreCase(ID_BYCN_TAG)) {
        	idbycn = false;
        }

        if(qName.equalsIgnoreCase(NUMBERORUE_TAG)) {
        	numeroRue = false;
        }

        if(qName.equalsIgnoreCase(PAYS_TAG)) {
        	pays = false;
        }

        if(qName.equalsIgnoreCase(VILLE_TAG)) {
        	ville = false;
        }
        
        if(qName.equalsIgnoreCase(ADDRESS_TAG)) {
        	adresse = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(bureauDistributeur) {
        	address.setBureauDistributeur(new String(ch, start, length));
        }

        if(codePostal) {
        	address.setCodePostal(new String(ch, start, length));
        }

        if(complementAdresse) {
        	address.setComplementAdresse(new String(ch, start, length));
        }

        if(idbycn) {
        	address.setIdbycn(new String(ch, start, length));
        }
        
        if(numeroRue) {
        	address.setNumeroRue(new String(ch, start, length));
        }
        
        if(pays) {
        	address.setPays(new String(ch, start, length));
        }
        
        if(ville) {
        	address.setVille(new String(ch, start, length));
        }
    }

    public AddressModel getAddress() {
    	return this.address;
    }
}
