/**
 * WsArgosPortServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.structis.argos.ws;

public class WsArgosPortServiceLocator extends org.apache.axis.client.Service implements com.structis.argos.ws.WsArgosPortService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public WsArgosPortServiceLocator() {
    }

    public WsArgosPortServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsArgosPortServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wsArgosPort
    private java.lang.String wsArgosPort_address = "http://argoswsauth.bouygues-construction.com:8082/Argos/services/wsArgosPortAuth";

    @Override
    public java.lang.String getwsArgosPortAddress() {
        return this.wsArgosPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsArgosPortWSDDServiceName = "wsArgosPort";

    public java.lang.String getwsArgosPortWSDDServiceName() {
        return this.wsArgosPortWSDDServiceName;
    }

    public void setwsArgosPortWSDDServiceName(java.lang.String name) {
        this.wsArgosPortWSDDServiceName = name;
    }

    @Override
    public com.structis.argos.ws.WsArgosPort getwsArgosPort() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(this.wsArgosPort_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return this.getwsArgosPort(endpoint);
    }

    @Override
    public com.structis.argos.ws.WsArgosPort getwsArgosPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.structis.argos.ws.WsArgosPortSoapBindingStub _stub = new com.structis.argos.ws.WsArgosPortSoapBindingStub(portAddress, this);
            _stub.setPortName(this.getwsArgosPortWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwsArgosPortEndpointAddress(java.lang.String address) {
        this.wsArgosPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has no port for the given interface, then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.structis.argos.ws.WsArgosPort.class.isAssignableFrom(serviceEndpointInterface)) {
                com.structis.argos.ws.WsArgosPortSoapBindingStub _stub = new com.structis.argos.ws.WsArgosPortSoapBindingStub(new java.net.URL(
                        this.wsArgosPort_address), this);
                _stub.setPortName(this.getwsArgosPortWSDDServiceName());
                return _stub;
            }
        } catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
                + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation. If this service has no port for the given interface, then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return this.getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("wsArgosPort".equals(inputPortName)) {
            return this.getwsArgosPort();
        } else {
            java.rmi.Remote _stub = this.getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    @Override
    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.argos.structis.com", "WsArgosPortService");
    }

    private java.util.HashSet ports = null;

    @Override
    public java.util.Iterator getPorts() {
        if (this.ports == null) {
            this.ports = new java.util.HashSet();
            this.ports.add(new javax.xml.namespace.QName("http://ws.argos.structis.com", "wsArgosPort"));
        }
        return this.ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("wsArgosPort".equals(portName)) {
            this.setwsArgosPortEndpointAddress(address);
        } else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        this.setEndpointAddress(portName.getLocalPart(), address);
    }

}
