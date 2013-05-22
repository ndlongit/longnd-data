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

    private boolean bureauDistributeur = false;
    private boolean codePostal = false;
    private boolean complementAdresse = false;
    private boolean idbycn = false;
    private boolean numeroRue = false;
    private boolean pays = false;
    private boolean ville = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(this.ADDRESS_TAG)) {
            this.address = new AddressModel();
        }

        if (qName.equalsIgnoreCase(this.BUREAUDISTRIBUTEUR_TAG)) {
            this.bureauDistributeur = true;
        }

        if (qName.equalsIgnoreCase(this.CODEPOSTAL_TAG)) {
            this.codePostal = true;
        }

        if (qName.equalsIgnoreCase(this.COMPLEMENTADRESSE_TAG)) {
            this.complementAdresse = true;
        }

        if (qName.equalsIgnoreCase(this.ID_BYCN_TAG)) {
            this.idbycn = true;
        }

        if (qName.equalsIgnoreCase(this.NUMBERORUE_TAG)) {
            this.numeroRue = true;
        }

        if (qName.equalsIgnoreCase(this.PAYS_TAG)) {
            this.pays = true;
        }

        if (qName.equalsIgnoreCase(this.VILLE_TAG)) {
            this.ville = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(this.BUREAUDISTRIBUTEUR_TAG)) {
            this.bureauDistributeur = false;
        }

        if (qName.equalsIgnoreCase(this.CODEPOSTAL_TAG)) {
            this.codePostal = false;
        }

        if (qName.equalsIgnoreCase(this.COMPLEMENTADRESSE_TAG)) {
            this.complementAdresse = false;
        }

        if (qName.equalsIgnoreCase(this.ID_BYCN_TAG)) {
            this.idbycn = false;
        }

        if (qName.equalsIgnoreCase(this.NUMBERORUE_TAG)) {
            this.numeroRue = false;
        }

        if (qName.equalsIgnoreCase(this.PAYS_TAG)) {
            this.pays = false;
        }

        if (qName.equalsIgnoreCase(this.VILLE_TAG)) {
            this.ville = false;
        }

        if (qName.equalsIgnoreCase(this.ADDRESS_TAG)) {
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.bureauDistributeur) {
            this.address.setBureauDistributeur(new String(ch, start, length));
        }

        if (this.codePostal) {
            this.address.setCodePostal(new String(ch, start, length));
        }

        if (this.complementAdresse) {
            this.address.setComplementAdresse(new String(ch, start, length));
        }

        if (this.idbycn) {
            this.address.setIdbycn(new String(ch, start, length));
        }

        if (this.numeroRue) {
            this.address.setNumeroRue(new String(ch, start, length));
        }

        if (this.pays) {
            this.address.setPays(new String(ch, start, length));
        }

        if (this.ville) {
            this.address.setVille(new String(ch, start, length));
        }
    }

    public AddressModel getAddress() {
        return this.address;
    }
}
