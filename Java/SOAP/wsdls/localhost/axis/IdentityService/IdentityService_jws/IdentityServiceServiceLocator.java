/**
 * IdentityServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.axis.IdentityService.IdentityService_jws;

public class IdentityServiceServiceLocator extends org.apache.axis.client.Service implements localhost.axis.IdentityService.IdentityService_jws.IdentityServiceService {

    public IdentityServiceServiceLocator() {
    }


    public IdentityServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IdentityServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IdentityService
    private java.lang.String IdentityService_address = "http://localhost:8080/axis/IdentityService/IdentityService.jws";

    public java.lang.String getIdentityServiceAddress() {
        return IdentityService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IdentityServiceWSDDServiceName = "IdentityService";

    public java.lang.String getIdentityServiceWSDDServiceName() {
        return IdentityServiceWSDDServiceName;
    }

    public void setIdentityServiceWSDDServiceName(java.lang.String name) {
        IdentityServiceWSDDServiceName = name;
    }

    public localhost.axis.IdentityService.IdentityService_jws.IdentityService getIdentityService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IdentityService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIdentityService(endpoint);
    }

    public localhost.axis.IdentityService.IdentityService_jws.IdentityService getIdentityService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.axis.IdentityService.IdentityService_jws.IdentityServiceSoapBindingStub _stub = new localhost.axis.IdentityService.IdentityService_jws.IdentityServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getIdentityServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIdentityServiceEndpointAddress(java.lang.String address) {
        IdentityService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.axis.IdentityService.IdentityService_jws.IdentityService.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.axis.IdentityService.IdentityService_jws.IdentityServiceSoapBindingStub _stub = new localhost.axis.IdentityService.IdentityService_jws.IdentityServiceSoapBindingStub(new java.net.URL(IdentityService_address), this);
                _stub.setPortName(getIdentityServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("IdentityService".equals(inputPortName)) {
            return getIdentityService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/axis/IdentityService/IdentityService.jws", "IdentityServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/axis/IdentityService/IdentityService.jws", "IdentityService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IdentityService".equals(portName)) {
            setIdentityServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
