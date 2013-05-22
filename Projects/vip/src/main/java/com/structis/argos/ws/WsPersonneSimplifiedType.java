/**
 * WsPersonneSimplifiedType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsPersonneSimplifiedType implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private java.lang.String nom;

    private java.lang.String idArgos;

    private java.lang.String prenom;

    private java.lang.String photo;

    private java.lang.String url;

    private java.lang.String domain;

    private java.lang.String mail;

    private java.lang.String login;

    public WsPersonneSimplifiedType() {
    }

    public WsPersonneSimplifiedType(java.lang.String nom, java.lang.String idArgos, java.lang.String prenom, java.lang.String photo,
            java.lang.String url, java.lang.String domain, java.lang.String mail, java.lang.String login) {
        this.nom = nom;
        this.idArgos = idArgos;
        this.prenom = prenom;
        this.photo = photo;
        this.url = url;
        this.domain = domain;
        this.mail = mail;
        this.login = login;
    }

    /**
     * Gets the nom value for this WsPersonneSimplifiedType.
     * 
     * @return nom
     */
    public java.lang.String getNom() {
        return this.nom;
    }

    /**
     * Sets the nom value for this WsPersonneSimplifiedType.
     * 
     * @param nom
     */
    public void setNom(java.lang.String nom) {
        this.nom = nom;
    }

    /**
     * Gets the idArgos value for this WsPersonneSimplifiedType.
     * 
     * @return idArgos
     */
    public java.lang.String getIdArgos() {
        return this.idArgos;
    }

    /**
     * Sets the idArgos value for this WsPersonneSimplifiedType.
     * 
     * @param idArgos
     */
    public void setIdArgos(java.lang.String idArgos) {
        this.idArgos = idArgos;
    }

    /**
     * Gets the prenom value for this WsPersonneSimplifiedType.
     * 
     * @return prenom
     */
    public java.lang.String getPrenom() {
        return this.prenom;
    }

    /**
     * Sets the prenom value for this WsPersonneSimplifiedType.
     * 
     * @param prenom
     */
    public void setPrenom(java.lang.String prenom) {
        this.prenom = prenom;
    }

    /**
     * Gets the photo value for this WsPersonneSimplifiedType.
     * 
     * @return photo
     */
    public java.lang.String getPhoto() {
        return this.photo;
    }

    /**
     * Sets the photo value for this WsPersonneSimplifiedType.
     * 
     * @param photo
     */
    public void setPhoto(java.lang.String photo) {
        this.photo = photo;
    }

    /**
     * Gets the url value for this WsPersonneSimplifiedType.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return this.url;
    }

    /**
     * Sets the url value for this WsPersonneSimplifiedType.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }

    /**
     * Gets the domain value for this WsPersonneSimplifiedType.
     * 
     * @return domain
     */
    public java.lang.String getDomain() {
        return this.domain;
    }

    /**
     * Sets the domain value for this WsPersonneSimplifiedType.
     * 
     * @param domain
     */
    public void setDomain(java.lang.String domain) {
        this.domain = domain;
    }

    /**
     * Gets the mail value for this WsPersonneSimplifiedType.
     * 
     * @return mail
     */
    public java.lang.String getMail() {
        return this.mail;
    }

    /**
     * Sets the mail value for this WsPersonneSimplifiedType.
     * 
     * @param mail
     */
    public void setMail(java.lang.String mail) {
        this.mail = mail;
    }

    /**
     * Gets the login value for this WsPersonneSimplifiedType.
     * 
     * @return login
     */
    public java.lang.String getLogin() {
        return this.login;
    }

    /**
     * Sets the login value for this WsPersonneSimplifiedType.
     * 
     * @param login
     */
    public void setLogin(java.lang.String login) {
        this.login = login;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsPersonneSimplifiedType))
            return false;
        WsPersonneSimplifiedType other = (WsPersonneSimplifiedType) obj;
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (this.__equalsCalc != null) {
            return (this.__equalsCalc == obj);
        }
        this.__equalsCalc = obj;
        boolean _equals;
        _equals = true && ((this.nom == null && other.getNom() == null) || (this.nom != null && this.nom.equals(other.getNom())))
                && ((this.idArgos == null && other.getIdArgos() == null) || (this.idArgos != null && this.idArgos.equals(other.getIdArgos())))
                && ((this.prenom == null && other.getPrenom() == null) || (this.prenom != null && this.prenom.equals(other.getPrenom())))
                && ((this.photo == null && other.getPhoto() == null) || (this.photo != null && this.photo.equals(other.getPhoto())))
                && ((this.url == null && other.getUrl() == null) || (this.url != null && this.url.equals(other.getUrl())))
                && ((this.domain == null && other.getDomain() == null) || (this.domain != null && this.domain.equals(other.getDomain())))
                && ((this.mail == null && other.getMail() == null) || (this.mail != null && this.mail.equals(other.getMail())))
                && ((this.login == null && other.getLogin() == null) || (this.login != null && this.login.equals(other.getLogin())));
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
        if (this.getNom() != null) {
            _hashCode += this.getNom().hashCode();
        }
        if (this.getIdArgos() != null) {
            _hashCode += this.getIdArgos().hashCode();
        }
        if (this.getPrenom() != null) {
            _hashCode += this.getPrenom().hashCode();
        }
        if (this.getPhoto() != null) {
            _hashCode += this.getPhoto().hashCode();
        }
        if (this.getUrl() != null) {
            _hashCode += this.getUrl().hashCode();
        }
        if (this.getDomain() != null) {
            _hashCode += this.getDomain().hashCode();
        }
        if (this.getMail() != null) {
            _hashCode += this.getMail().hashCode();
        }
        if (this.getLogin() != null) {
            _hashCode += this.getLogin().hashCode();
        }
        this.__hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(WsPersonneSimplifiedType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsPersonneSimplifiedType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nom"));
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
        elemField.setFieldName("prenom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prenom"));
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
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url"));
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
        elemField.setFieldName("mail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("login");
        elemField.setXmlName(new javax.xml.namespace.QName("", "login"));
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
